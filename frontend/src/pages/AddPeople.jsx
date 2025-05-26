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

    const handleAddPeople = () => {
    // Reset the form after adding name
    setFirstName('');
    setLastName('');
    setPhoneNo('');
    };

    return (
        <Paper elevation={4} sx={{padding: 4, margin: '40px auto', maxWidth: 600}}>
            <Typography variant="h4" component="h1">
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
                />
                </Tooltip>

                <FormControl>
                <FormLabel>What room person belongs to:</FormLabel>
                <FormControlLabel control={<Radio />} label="Dormitory"/>
                <FormControlLabel control={<Radio />} label="Study group"/>
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