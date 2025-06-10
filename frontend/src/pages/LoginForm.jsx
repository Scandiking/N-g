import {Box, Container, Stack, Button, InputLabel, TextField} from '@mui/material';
import {useNavigate} from 'react-router-dom';
import Naglogo from "../assets/Naeg-logo-2.png";
import {useState} from "react";

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({username, password}),
        });
        if (response.ok) {
            const token = response.headers.get('Authorization');
            // Store token for later use (e.g. localStorage)
            localStorage.setItem('token', token);
            // Redirect or update UI as needed
            navigate('/mytasks');
        } else {
            // Handle error
            alert('Login failed');
        }
    };

    return (
        <Container style={{textAlign: 'center', marginTop: '100px'}}>
            <h1>Log into Næg</h1>
            <Box>
                <TextField
                    label="Username"
                    value={username}
                    onChange={e => setUsername(e.target.value)}
                    margin="normal"
                />
                <br/>
                <TextField
                    label="Password"
                    type="password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    margin="normal"
                />
            </Box>
            <Stack spacing={2} direction="row" justifyContent="center">
                <Button variant="outlined" size="large" onClick={handleLogin}>
                    Log in
                </Button>
            </Stack>
        </Container>
    );
};

export default LoginForm;