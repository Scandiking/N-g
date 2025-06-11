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

const AddPeople = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNo, setPhoneNo] = useState('');
    const [email, setEmail] = useState('');
    const [selectedRoom, setSelectedRoom] = useState('');
    const [loading, setLoading] = useState(false);
    const [rooms, setRooms] = useState([]);
    const [snackbar, setSnackbar] = useState({
        open: false,
        message: '',
        severity: 'success'
    });

    // Fetch available rooms when component mounts
    useEffect(() => {
        fetchRooms();
    }, []);

    const fetchRooms = async () => {
        try {
            const token = localStorage.getItem('token');
            const response = await fetch('http://localhost:8080/api/rooms', {
                headers: {
                    'Authorization': token && !token.startsWith('Bearer ')
                        ? `Bearer ${token}`
                        : token || ''
                }
            });

            if (response.ok) {
                const roomData = await response.json();
                setRooms(roomData);
            }
        } catch (error) {
            console.error('Error fetching rooms:', error);
            setSnackbar({
                open: true,
                message: 'Failed to load rooms',
                severity: 'warning'
            });
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
                            options={rooms}
                            getOptionLabel={(option) => option.roomName || option}
                            value={selectedRoom}
                            onChange={(event, newValue) => {
                                setSelectedRoom(newValue?.roomId || newValue || '');
                            }}
                            disabled={loading}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="Assign to room"
                                    placeholder="Select a room (optional)"
                                />
                            )}
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