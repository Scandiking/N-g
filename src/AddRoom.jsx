import React, { useState } from 'react';
import {Box, TextField, Button, Typography, Paper} from '@mui/material';

const AddRoom = () => {
    const [roomTitle, setRoomTitle] = useState('');
    const [roomDescription, setRoomDescription] = useState('');

    const handleAddRoom = () => {
        console.log('Room added', { roomTitle, roomDescription });
        // Reset the form after adding the task
        setRoomTitle('');
        setRoomDescription('');
    };

    return (

        <Paper elevation={3} sx={{ padding: 4, margin: '20px auto', maxWidth: 600}}>
            <Typography variant="h4" component="h1" gutterButton>
                Add a New Room
            </Typography>
            <Box component="form" sx={{ display: 'flex', flexDirection: 'column', gap: 2}}>
                <TextField
                    label="Room Title"
                    variant="outlined"
                    value={roomTitle}
                    onChange={(e) => setRoomTitle(e.target.value)}
                    fullWidth
                    required
                />
                <TextField
                    label="Room Description"
                    variant="outlined"
                    value={roomDescription}
                    onChange={(e) => setRoomDescription(e.target.value)}
                    multiline
                    rows={4}
                    fullWidth
                    required
                />

                <Button variant="contained" color="primary" onClick={handleAddRoom}>
                    Add Room
                </Button>
            </Box>
        </Paper>

    );
};

export default AddRoom;