import {Box, Container, Stack, Button, InputLabel, TextField} from '@mui/material';
import {useNavigate} from 'react-router-dom';
import Naglogo from "../assets/Naeg-logo-2.png";
import {useState} from "react";

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    {/* Asynchronous process to wait for fetching the token by posting the username and pw to /login */
    }
    const handleLogin = async () => {
        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({username, password}),
        });
        {/* If the response is ok, the token is set to localStorage */
        }
        if (response.ok) {
            const token = response.headers.get('Authorization');
            // Store token for later use (e.g. localStorage)
            localStorage.setItem('token', token);
            // Redirect or update UI as needed
            navigate('/mytasks');
        } else {
            // Else user gets a HCI-like browser alert.
            alert('Login failed. The account either does not exist or you entered the wrong credentials.');
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
            <Stack spacing={0} direction="row" justifyContent="center">
                <Button variant="contained" onClick={handleLogin}>
                    Log in
                </Button>
            </Stack>
        </Container>
    );
};

export default LoginForm;