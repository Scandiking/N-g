import React, { useState } from 'react';
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
    Tooltip
} from '@mui/material';

const AddPeople = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNo, setPhoneNo] = useState('');
    const [room, setRoom] = useState('Dormitory');

    const handleAddPeople = async () => {
        const response = await fetch('http://localhost:8080/people', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ firstName, lastName, phoneNo, room }),
        });

        if (response.ok) {
            // Clear form on success
            setFirstName('');
            setLastName('');
            setPhoneNo('');
            setRoom('Dormitory');
            alert('Person added successfully!');
        } else {
            alert('Failed to add person.');
        }
    };

    return (
        <Paper elevation={4} sx={{ padding: 4, margin: '40px auto', maxWidth: 600 }}>
            <Typography variant="h4" component="h1">
                Add person
            </Typography>
            <Box component="form" sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                <Tooltip title="Enter the first name of the person you are adding" placement="top">
                    <TextField
                        label="First name"
                        variant="outlined"
                        value={firstName}
                        onChange={e => setFirstName(e.target.value)}
                        fullWidth
                        required
                    />
                </Tooltip>

                <Tooltip title="Enter the last name of the person you are adding" placement="top">
                    <TextField
                        label="Last name"
                        variant="outlined"
                        value={lastName}
                        onChange={e => setLastName(e.target.value)}
                        fullWidth
                        required
                    />
                </Tooltip>

                <Tooltip title="Enter the phone number of the person you are adding" placement="top">
                    <TextField
                        label="Phone No."
                        variant="outlined"
                        value={phoneNo}
                        onChange={e => setPhoneNo(e.target.value)}
                        fullWidth
                        required
                    />
                </Tooltip>

                <FormControl>
                    <FormLabel>What room does this person belong to?</FormLabel>
                    <FormControlLabel
                        control={
                            <Radio
                                checked={room === 'Dormitory'}
                                onChange={() => setRoom('Dormitory')}
                            />
                        }
                        label="Dormitory"
                    />
                    <FormControlLabel
                        control={
                            <Radio
                                checked={room === 'Study group'}
                                onChange={() => setRoom('Study group')}
                            />
                        }
                        label="Study group"
                    />
                </FormControl>

                <Tooltip title="Sends notification to user" placement="top">
                    <Button variant="contained" color="primary" onClick={handleAddPeople}>
                        Add person
                    </Button>
                </Tooltip>
            </Box>
        </Paper>
    );
};

export default AddPeople;
