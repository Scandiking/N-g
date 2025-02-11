// Register.jsx is a component that lets users sign up for Nag

import React from 'react';
import { Box, Card, CardContent, CardMedia, Container, Stack, Typography, TextField, Tooltip, Button } from "@mui/material";
import Naglogo from "./assets/Nag-logo.png";

function Register() {
    return (
        <Container sx={{ p: 3}}>
            <Card>
                <Box sx={{p:2, display:"flex", justifyContent:"center", width:'100%'}}>
                <CardMedia
                    sx={{
                        width: 100,
                        height: 100,
                        objectFit: "cover",
                        borderRadius: 1,
                        m: 1
                    }}
                    image={Naglogo}
                    title="Nag logo"
                />
                </Box>
                <CardContent>
                    <Typography variant="h3">
                        {"Create a new Næg account"}
                    </Typography>
                    <Stack spacing={2}>
                        <Tooltip title="Enter your first name" placement="top-start" arrow>
                            <TextField id="first-name" label="First Name" variant="outlined"/>
                        </Tooltip>
                        <Tooltip title="Enter your last name" placement="top-start" arrow>
                            <TextField id="last-name" label="Last Name" variant="outlined"/>
                        </Tooltip>
                        <Tooltip title="Enter your e-mail address" placement="top-start" arrow>
                            <TextField id="email" label="E-mail adress" variant="outlined"/>
                        </Tooltip>
                        <Tooltip title="Enter your phone number" placement="top-start" arrow>
                            <TextField id="phone-num" label="Phone No." variant="outlined"/>
                        </Tooltip>
                        <Tooltip title="Enter your password" placement="top-start" arrow>
                            <TextField id="user-pw" label="Password" variant="outlined"/>
                        </Tooltip>
                        <Tooltip title="Click or tap this to board onto Næg!" placement="top-start" arrow>
                            <Button variant="contained">Sign up</Button>
                        </Tooltip>
                    </Stack>


                </CardContent>
            </Card>
        </Container>
    );
}

export default Register;