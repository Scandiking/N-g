/* OVERVIEW PAGE TO MANAGE ROOMS */
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import Chip from "@mui/material/Chip";
import Container from "@mui/material/Container";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import CircularProgress from "@mui/material/CircularProgress";
import Alert from "@mui/material/Alert";
import { useState, useEffect } from "react";

// Sample rooms - reduced to 3 with clear naming
const sampleRooms = [
    {
        roomId: 9001, // Changed to high ID to avoid conflicts
        roomName: "Dormitory",
        roomDescr: "Student dorm of Apalveien 111",
        roomAdmin: "sample@example.com",
        members: [10, 11]
    },
    {
        roomId: 9002,
        roomName: "Project group",
        roomDescr: "Group with assignments for the APP2000 course",
        roomAdmin: "sample@example.com",
        members: [11]
    },
    {
        roomId: 9003,
        roomName: "Gym",
        roomDescr: "High-intensity interval training running group",
        roomAdmin: "sample@example.com",
        members: []
    },
];

// List of users (for sample rooms)
const UsersInRooms = [
    { id: 10, first_name: "Dorothy", last_name: "Jensen", role: "Admin" },
    { id: 11, first_name: "Sam", last_name: "Olsen", role: "Member" },
];

function MyRooms() {
    const [backendRooms, setBackendRooms] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    // Combine sample rooms with backend rooms
    const allRooms = [...sampleRooms, ...backendRooms];

    // Check if room is user-created (backend)
    const isUserCreated = (room) => {
        return backendRooms.some(br => br.roomId === room.roomId);
    };

    // Fetch rooms from backend when component mounts
    useEffect(() => {
        fetchRooms();
    }, []);

    const fetchRooms = async () => {
        try {
            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) {
                token = `Bearer ${token}`;
            }

            const response = await fetch('http://localhost:8080/api/rooms', {
                headers: {
                    'Authorization': token || '',
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const roomData = await response.json();
                setBackendRooms(roomData);
            } else {
                console.warn('Failed to load backend rooms, showing sample data only');
                // Don't set error - just show sample rooms if backend fails
            }
        } catch (error) {
            console.warn('Network error while loading rooms:', error);
            // Don't set error - just show sample rooms if backend fails
        } finally {
            setLoading(false);
        }
    };

    return (
        <Container sx={{ mt: 4, p: 3 }}>
            <Typography variant="h4" sx={{ mb: 3 }}>
                My Rooms
            </Typography>

            {loading ? (
                <Container sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                    <CircularProgress />
                </Container>
            ) : (
                <>
                    <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                        Showing {allRooms.length} rooms ({sampleRooms.length} sample + {backendRooms.length} your rooms)
                    </Typography>

                    <Grid container spacing={3}>
                        {allRooms.map((room) => (
                            <Grid item xs={12} sm={6} md={4} key={`${room.roomId}-${room.roomName}`}>
                                <Card sx={{
                                    p: 2,
                                    boxShadow: 3,
                                    position: 'relative',
                                    border: isUserCreated(room) ? '2px solid #4caf50' : '2px solid #ff9800',
                                    backgroundColor: isUserCreated(room) ? 'white' : '#fff3e0',
                                    height: '100%',  // ✅ LA TIL: Samme høyde på alle kort
                                    display: 'flex',
                                    flexDirection: 'column'
                                }}>
                                    <CardContent sx={{
                                        display: 'flex',
                                        flexDirection: 'column',
                                        height: '100%'
                                    }}>
                                        {/* Room type indicator */}
                                        {isUserCreated(room) ? (
                                            <Chip
                                                label="Your Room"
                                                color="success"
                                                size="small"
                                                sx={{
                                                    position: 'absolute',
                                                    top: 8,
                                                    right: 8,
                                                    fontSize: '0.7em'
                                                }}
                                            />
                                        ) : (
                                            <Chip
                                                label="🏠 SAMPLE ROOM"
                                                color="warning"
                                                size="small"
                                                sx={{
                                                    position: 'absolute',
                                                    top: 8,
                                                    right: 8,
                                                    fontSize: '0.7em',
                                                    fontWeight: 'bold'
                                                }}
                                            />
                                        )}

                                        <Typography variant="h6" sx={{ mt: 1 }}>
                                            {room.roomName}
                                        </Typography>

                                        <Typography color="text.secondary" sx={{ mb: 2 }}>
                                            {room.roomDescr || room.description || 'No description provided'}
                                        </Typography>

                                        <Typography variant="subtitle2" sx={{ mb: 1 }}>
                                            Administrator:
                                        </Typography>
                                        <Chip
                                            label={room.roomAdmin}
                                            color="primary"
                                            size="small"
                                            sx={{ mb: 2 }}
                                        />

                                        <Typography variant="h6">Members</Typography>
                                        <List>
                                            {/* Handle sample room members */}
                                            {room.members && room.members.length > 0 ? (
                                                room.members.map((memberId) => {
                                                    const user = UsersInRooms.find((u) => u.id === memberId);
                                                    return user ? (
                                                        <ListItem key={user.id} sx={{display:"flex", alignItems: "center"}}>
                                                            <ListItemText primary={`${user.first_name} ${user.last_name}`}/>
                                                            <Chip label={user.role} color={user.role === "Admin" ? "primary" : "default"}></Chip>
                                                        </ListItem>
                                                    ) : null;
                                                })
                                            ) : (
                                                <ListItem>
                                                    <ListItemText
                                                        primary={isUserCreated(room)
                                                            ? "Loading members..."
                                                            : "No members yet"
                                                        }
                                                        secondary={isUserCreated(room)
                                                            ? "Feature coming soon"
                                                            : "Demo room for testing"
                                                        }
                                                    />
                                                </ListItem>
                                            )}
                                        </List>
                                    </CardContent>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                </>
            )}
        </Container>
    );
}

export default MyRooms;