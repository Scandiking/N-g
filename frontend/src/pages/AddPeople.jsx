import React, { useState, useEffect } from 'react';
import {
    Box,
    TextField,
    Button,
    Typography,
    Paper,
    FormControlLabel,
    Radio,
    FormLabel,
    FormControl,
    RadioGroup,
    Tooltip,
    Snackbar,
    Alert,
    CircularProgress,
    Autocomplete
} from '@mui/material';

// Sample rooms for dropdown (matching MyRooms pattern)
const sampleRooms = [
    { roomId: 1, roomName: "Dormitory" },
    { roomId: 2, roomName: "Project group" },
    { roomId: 3, roomName: "Gym" },
];

const AddPeople = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNo, setPhoneNo] = useState('');
    const [email, setEmail] = useState('');
    const [selectedRoom, setSelectedRoom] = useState('');
    const [loading, setLoading] = useState(false);
    const [backendRooms, setBackendRooms] = useState([]); // ✅ Renamed for clarity
    const [snackbar, setSnackbar] = useState({
        open: false,
        message: '',
        severity: 'success'
    });

    // Combine sample rooms with backend rooms
    const allRooms = [...sampleRooms, ...backendRooms];

    // Fetch available rooms when component mounts
    useEffect(() => {
        console.log('AddPeople useEffect: Fetching rooms...');
        fetchRooms();
    }, []);

    const fetchRooms = async () => {
        try {
            const token = localStorage.getItem('token');
            console.log('Fetching rooms with token:', token ? 'Token exists' : 'No token');

            let authHeader = '';
            if (token) {
                authHeader = token.startsWith('Bearer ') ? token : `Bearer ${token}`;
            }

            const response = await fetch('http://localhost:8080/api/rooms', {
                headers: {
                    'Authorization': authHeader,
                    'Content-Type': 'application/json'
                }
            });

            console.log('Rooms API response status:', response.status);

            if (response.ok) {
                const roomData = await response.json();
                console.log('Backend rooms data received:', roomData);
                setBackendRooms(roomData); // ✅ Updated to use backendRooms
            } else {
                console.warn('Failed to fetch backend rooms:', response.status, response.statusText);
                // Don't show error - just use sample rooms
            }
        } catch (error) {
            console.error('Error fetching backend rooms:', error);
            // Don't show error - just use sample rooms
        }
    };

    const handleAddPeople = async () => {
        // Validation
        if (!firstName.trim() || !lastName.trim() || !phoneNo.trim()) {
            setSnackbar({
                open: true,
                message: 'Please fill in all required fields',
                severity: 'error'
            });
            return;
        }

        setLoading(true);

        try {
            // Create person object matching PersonDTO structure
            const personData = {
                phoneNo: phoneNo.trim(),
                firstName: firstName.trim(),
                lastName: lastName.trim(),
                mail: email.trim() || `${firstName.toLowerCase()}.${lastName.toLowerCase()}@example.com`,
                profilePicture: null,
                birthDate: null
            };

            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) {
                token = `Bearer ${token}`;
            }

            // POST to Spring Boot /api/persons endpoint
            const response = await fetch('http://localhost:8080/api/persons', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token || ''
                },
                body: JSON.stringify(personData)
            });

            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(`Server responded with ${response.status}: ${errorText}`);
            }

            const newPerson = await response.json();
            console.log('Person created successfully:', newPerson);

            // If room is selected, create room-person relationship
            if (selectedRoom) {
                await createRoomPersonRelationship(newPerson.phoneNo, selectedRoom);
            }

            // Reset form on success
            setFirstName('');
            setLastName('');
            setPhoneNo('');
            setEmail('');
            setSelectedRoom('');

            setSnackbar({
                open: true,
                message: `${newPerson.firstName} ${newPerson.lastName} added successfully!`,
                severity: 'success'
            });

        } catch (error) {
            console.error('Error adding person:', error);
            setSnackbar({
                open: true,
                message: `Failed to add person: ${error.message}`,
                severity: 'error'
            });
        } finally {
            setLoading(false);
        }
    };

    const createRoomPersonRelationship = async (phoneNo, roomId) => {
        try {
            const relationshipData = {
                roomId: roomId,
                phoneNo: phoneNo,
                score: 0 // Default score
            };

            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) {
                token = `Bearer ${token}`;
            }

            const response = await fetch('http://localhost:8080/api/roomforpersons', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token || ''
                },
                body: JSON.stringify(relationshipData)
            });

            if (!response.ok) {
                console.warn('Failed to assign person to room:', response.status);
            } else {
                console.log('Person assigned to room successfully');
            }
        } catch (error) {
            console.error('Error creating room-person relationship:', error);
        }
    };

    const handleCloseSnackbar = () => {
        setSnackbar({ ...snackbar, open: false });
    };

    // Debug logging
    console.log('AddPeople render - allRooms:', allRooms);
    console.log('AddPeople render - sample rooms:', sampleRooms.length, 'backend rooms:', backendRooms.length);

    return (
        <>
            <Paper elevation={4} sx={{padding: 4, margin: '40px auto', maxWidth: 600}}>
                <Typography variant="h4" component="h1" gutterBottom>
                    Add person
                </Typography>

                <Box component="form" sx={{display: 'flex', flexDirection: 'column', gap: 2}}>
                    <Tooltip title="Enter the first name of the person you are adding" placement="top">
                        <TextField
                            label="First name"
                            variant="outlined"
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            fullWidth
                            required
                            disabled={loading}
                        />
                    </Tooltip>

                    <Tooltip title="Enter the last name of the person you are adding" placement="top">
                        <TextField
                            label="Last name"
                            variant="outlined"
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            fullWidth
                            required
                            disabled={loading}
                        />
                    </Tooltip>

                    <Tooltip title="Enter the phone number of the person you are adding" placement="top">
                        <TextField
                            label="Phone No."
                            variant="outlined"
                            value={phoneNo}
                            onChange={(e) => setPhoneNo(e.target.value)}
                            fullWidth
                            required
                            disabled={loading}
                            placeholder="e.g., 4712345678"
                        />
                    </Tooltip>

                    <Tooltip title="Enter email address (optional)" placement="top">
                        <TextField
                            label="Email"
                            variant="outlined"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            fullWidth
                            disabled={loading}
                            placeholder="Optional - will auto-generate if empty"
                        />
                    </Tooltip>

                    <Tooltip title="Assign person to a room (optional)" placement="top">
                        <Autocomplete
                            options={allRooms}
                            getOptionLabel={(option) => {
                                // Handle different object structures
                                if (typeof option === 'string') return option;
                                if (option.roomName) return option.roomName;
                                if (option.name) return option.name;
                                return `Room ${option.roomId || option}`;
                            }}
                            value={allRooms.find(room => room.roomId === selectedRoom) || null}
                            onChange={(event, newValue) => {
                                console.log('Room selected:', newValue);
                                if (newValue) {
                                    setSelectedRoom(newValue.roomId || newValue);
                                } else {
                                    setSelectedRoom('');
                                }
                            }}
                            disabled={loading}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="Assign to room"
                                    placeholder="Select a room (optional)"
                                    helperText={`${allRooms.length} room(s) available (${sampleRooms.length} sample + ${backendRooms.length} your rooms)`}
                                />
                            )}
                            renderOption={(props, option) => (
                                <li {...props}>
                                    <span>
                                        {option.roomName || option.name || option}
                                        {/* Mark user-created rooms */}
                                        {backendRooms.some(br => br.roomId === option.roomId) && (
                                            <span style={{
                                                marginLeft: '8px',
                                                padding: '2px 6px',
                                                backgroundColor: '#4caf50',
                                                color: 'white',
                                                borderRadius: '4px',
                                                fontSize: '0.7em'
                                            }}>
                                                Your Room
                                            </span>
                                        )}
                                    </span>
                                </li>
                            )}
                            noOptionsText="No rooms found"
                        />
                    </Tooltip>

                    <Tooltip title="Add this person to your Næg system" placement="top">
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={handleAddPeople}
                            disabled={loading || !firstName.trim() || !lastName.trim() || !phoneNo.trim()}
                            sx={{ mt: 2 }}
                        >
                            {loading ? (
                                <>
                                    <CircularProgress size={20} sx={{ mr: 1 }} />
                                    Adding person...
                                </>
                            ) : (
                                'Add person'
                            )}
                        </Button>
                    </Tooltip>
                </Box>
            </Paper>

            {/* Success/Error feedback */}
            <Snackbar
                open={snackbar.open}
                autoHideDuration={6000}
                onClose={handleCloseSnackbar}
                anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
            >
                <Alert
                    onClose={handleCloseSnackbar}
                    severity={snackbar.severity}
                    sx={{ width: '100%' }}
                >
                    {snackbar.message}
                </Alert>
            </Snackbar>
        </>
    );
};

export default AddPeople;