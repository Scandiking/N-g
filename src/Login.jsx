import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { BrowserRouter, Routes, Route, useLocation } from 'react-router-dom';
import Button from '@mui/material/Button'
import Container from '@mui/material/Container'
import Stack from '@mui/material/Stack';
import Card from '@mui/material/Card';

const Login = () => {
    return (

            <Container style={{ textAlign: 'center', marginTop: '100px' }}>

                <h1>Næg - register today</h1>
                <h3>Join the like five people who uses this app</h3>

                <Stack spacing={2} direction="row" justifyContent="center">
                    <Button variant="outlined" size="large">Register</Button>
                    <Button variant="contained" size="large">Log in</Button>
                </Stack>

            </Container>



    );
};

export default Login;