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
import { useState } from "react";

// Sample rooms with assigned user IDs
const sampleRooms = [
    { id: 1, name: "Dormitory", description: "Student dorm of Apalveien 111", members: [10, 11] },
    { id: 2, name: "Project group", description: "Group with assignments for the APP2000 course", members: [11] },
    { id: 3, name: "Gym", description: "High-intensity interval training running group.", members: [] },
];

// List of users
const UsersInRooms = [
    { id: 10, first_name: "Dorothy", last_name: "Jensen", role: "Admin" },
    { id: 11, first_name: "Sam", last_name: "Olsen", role: "Member" },
]

function MyRooms() {
    const [rooms, setRooms] = useState(sampleRooms);

    return (
        <Container sx={{ mt: 4, p: 3 }}>
            <Grid container spacing={3}>
                {rooms.map((room) => (
                    <Grid item xs={12} sm={6} md={4} key={room.id}>
                        <Card sx={{ p: 2}}>
                            <CardContent>
                                <Typography variant="h6">{room.name}</Typography>
                                <Typography color="text.secondary">
                                    {room.description}
                                </Typography>
                                <Typography variant="h6">Members</Typography>
                                <List>
                                    {room.members.length > 0 ? (
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
                                            <ListItemText primary="No members yet"/>
                                        </ListItem>
                                        )}
                                </List>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Container>
    )
}

export default MyRooms;