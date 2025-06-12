import React, { useState } from 'react';
import {
    Box,
    TextField,
    Button,
    Typography,
    Paper,
    Snackbar,
    Alert,
    CircularProgress,
    Tooltip
} from '@mui/material';

const AddRoom = () => {
    const [roomTitle, setRoomTitle] = useState('');
    const [roomDescription, setRoomDescription] = useState('');
    const [loading, setLoading] = useState(false);
    const [snackbar, setSnackbar] = useState({
        open: false,
        message: '',
        severity: 'success'
    });

    const handleAddRoom = async () => {
        // Validation
        if (!roomTitle.trim()) {
            setSnackbar({
                open: true,
                message: 'Please enter a room title',
                severity: 'error'
            });
            return;
        }

        setLoading(true);

        try {
            // Create room object matching Room entity structure
            const roomData = {
                roomName: roomTitle.trim(),
                roomDescr: roomDescription.trim() || null,
                roomAdmin: localStorage.getItem('username'), // Email as stored during login
                roomPicture: null
            };

            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) {
                token = `Bearer ${token}`;
            }

            // POST to Spring Boot /api/rooms endpoint
            const response = await fetch('http://localhost:8080/api/rooms', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token || ''
                },
                body: JSON.stringify(roomData)
            });

            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(`Server responded with ${response.status}: ${errorText}`);
            }

            const newRoom = await response.json();
            console.log('Room created successfully:', newRoom);

            // Reset form on success
            setRoomTitle('');
            setRoomDescription('');

            setSnackbar({
                open: true,
                message: `Room "${newRoom.roomName}" created successfully!`,
                severity: 'success'
            });

        } catch (error) {
            console.error('Error creating room:', error);
            setSnackbar({
                open: true,
                message: `Failed to create room: ${error.message}`,
                severity: 'error'
            });
        } finally {
            setLoading(false);
        }
    };

    const handleCloseSnackbar = () => {
        setSnackbar({ ...snackbar, open: false });
    };

    return (
        <>
            <Paper elevation={3} sx={{ padding: 4, margin: '20px auto', maxWidth: 600}}>
                <Typography variant="h4" component="h1" gutterBottom>
                    Add a New Room
                </Typography>

                <Box component="form" sx={{ display: 'flex', flexDirection: 'column', gap: 2}}>
                    <Tooltip title="Enter the name of the room you want to create" placement="top">
                        <TextField
                            label="Room Title"
                            variant="outlined"
                            value={roomTitle}
                            onChange={(e) => setRoomTitle(e.target.value)}
                            fullWidth
                            required
                            disabled={loading}
                        />
                    </Tooltip>

                    <Tooltip title="Describe what this room is for (optional)" placement="top">
                        <TextField
                            label="Room Description"
                            variant="outlined"
                            value={roomDescription}
                            onChange={(e) => setRoomDescription(e.target.value)}
                            multiline
                            rows={4}
                            fullWidth
                            disabled={loading}
                            placeholder="Optional - describe the purpose of this room"
                        />
                    </Tooltip>

                    <Tooltip title="Create this room and become its administrator" placement="top">
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={handleAddRoom}
                            disabled={loading || !roomTitle.trim()}
                            sx={{ mt: 2 }}
                        >
                            {loading ? (
                                <>
                                    <CircularProgress size={20} sx={{ mr: 1 }} />
                                    Creating room...
                                </>
                            ) : (
                                'Add Room'
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

export default AddRoom;