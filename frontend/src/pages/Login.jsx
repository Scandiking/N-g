import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
    Card,
    CardContent,
    Box,
    Container,
    Stack,
    Button,
    TextField,
    Alert,
    CircularProgress,
    Typography
} from '@mui/material';
import Naglogo from "../assets/Naeg-logo-2.png";

const Login = () => {
    const [credentials, setCredentials] = useState({ username: '', password: '' });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (field) => (event) => {
        setCredentials({ ...credentials, [field]: event.target.value });
        setError(''); // Clear error when user starts typing
    };

    const handleLogin = async () => {
        if (!credentials.username.trim() || !credentials.password.trim()) {
            setError('Please enter both username and password');
            return;
        }

        setLoading(true);
        setError('');

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: credentials.username.trim(),
                    password: credentials.password.trim()
                })
            });

            if (response.ok) {
                const data = await response.json();

                // Store JWT token in localStorage
                if (data.token) {
                    localStorage.setItem('token', data.token);
                    localStorage.setItem('username', credentials.username);

                    console.log('Login successful:', data);

                    // Redirect to home page
                    navigate('/');
                } else {
                    setError('Login successful but no token received');
                }
            } else {
                // Handle different error status codes
                if (response.status === 401) {
                    setError('Invalid username or password');
                } else if (response.status === 403) {
                    setError('Access forbidden');
                } else {
                    const errorText = await response.text();
                    setError(`Login failed: ${errorText || 'Unknown error'}`);
                }
            }
        } catch (error) {
            console.error('Login error:', error);
            setError('Network error. Please check if the server is running.');
        } finally {
            setLoading(false);
        }
    };

    const handleKeyPress = (event) => {
        if (event.key === 'Enter') {
            handleLogin();
        }
    };

    return (
        <Container style={{ textAlign: 'center', marginTop: '100px' }}>
            <Typography variant="h3" gutterBottom>
                Welcome to Næg
            </Typography>
            <Typography variant="h6" color="textSecondary" gutterBottom>
                Join the like five people who uses this app
            </Typography>

            <Box sx={{ mb: 3 }}>
                <img src={Naglogo} alt="Næg logo" style={{ height: 250, padding: '10px' }} />
            </Box>

            <Card sx={{ maxWidth: 400, mx: 'auto', mt: 3 }}>
                <CardContent sx={{ p: 4 }}>
                    <Typography variant="h5" gutterBottom>
                        Sign In
                    </Typography>

                    {error && (
                        <Alert severity="error" sx={{ mb: 2 }}>
                            {error}
                        </Alert>
                    )}

                    <TextField
                        fullWidth
                        label="Email"
                        variant="outlined"
                        margin="normal"
                        value={credentials.username}
                        onChange={handleChange('username')}
                        onKeyPress={handleKeyPress}
                        disabled={loading}
                        autoComplete="username"
                    />

                    <TextField
                        fullWidth
                        label="Password"
                        type="password"
                        variant="outlined"
                        margin="normal"
                        value={credentials.password}
                        onChange={handleChange('password')}
                        onKeyPress={handleKeyPress}
                        disabled={loading}
                        autoComplete="current-password"
                    />

                    <Button
                        fullWidth
                        variant="contained"
                        size="large"
                        onClick={handleLogin}
                        disabled={loading || !credentials.username.trim() || !credentials.password.trim()}
                        sx={{ mt: 3, mb: 2 }}
                    >
                        {loading ? <CircularProgress size={24} /> : 'Sign In'}
                    </Button>

                    <Box sx={{ mt: 2, p: 2, backgroundColor: 'grey.100', borderRadius: 1 }}>
                        <Typography variant="body2" color="textSecondary">
                            <strong>Test Accounts:</strong><br />
                            Username: <code>user</code> Password: <code>user</code><br />
                            Username: <code>admin</code> Password: <code>admin</code>
                        </Typography>
                    </Box>
                </CardContent>
            </Card>

            <Stack spacing={2} direction="row" justifyContent="center" sx={{ mt: 3 }}>
                <Button variant="outlined" size="large" onClick={() => navigate('/register')}>
                    Don't have an account? Register
                </Button>
            </Stack>
        </Container>
    );
};

export default Login;