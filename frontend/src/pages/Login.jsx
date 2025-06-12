import React, { useState, useEffect } from 'react';
import { useNavigate, Link as RouterLink } from 'react-router-dom';
import {
    Container,
    Paper,
    TextField,
    Button,
    Typography,
    Box,
    Alert,
    Link,
    CircularProgress
} from '@mui/material';
import { useAuth } from './AuthContext';

const Login = () => {
    const navigate = useNavigate();
    const { login, isAuthenticated, loading } = useAuth();

    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    // Redirect if already authenticated
    useEffect(() => {
        if (isAuthenticated && !loading) {
            navigate('/');
        }
    }, [isAuthenticated, loading, navigate]);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
        // Clear error when user starts typing
        if (error) setError('');
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setIsLoading(true);

        try {
            const result = await login(formData.email, formData.password);

            if (result.success) {
                // AuthContext will handle the redirect via useEffect above
                console.log('Login successful');
            } else {
                setError(result.error || 'Login failed');
            }
        } catch (error) {
            console.error('Login error:', error);
            setError('An unexpected error occurred');
        } finally {
            setIsLoading(false);
        }
    };

    // Show loading spinner while checking authentication
    if (loading) {
        return (
            <Container component="main" maxWidth="sm">
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <CircularProgress />
                    <Typography sx={{ mt: 2 }}>Checking authentication...</Typography>
                </Box>
            </Container>
        );
    }

    return (
        <Container component="main" maxWidth="sm">
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                {/* Logo */}
                <Box
                    sx={{
                        width: 120,
                        height: 120,
                        background: 'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)',
                        borderRadius: '20px',
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center',
                        mb: 3,
                        boxShadow: '0 4px 20px rgba(33, 150, 243, 0.3)'
                    }}
                >
                    <Typography
                        variant="h2"
                        sx={{
                            color: 'white',
                            fontWeight: 'bold',
                            fontFamily: 'monospace'
                        }}
                    >
                        N
                    </Typography>
                </Box>

                <Paper elevation={3} sx={{ padding: 4, width: '100%', maxWidth: 400 }}>
                    <Typography component="h1" variant="h4" align="center" gutterBottom>
                        Sign In
                    </Typography>

                    {error && (
                        <Alert severity="error" sx={{ mb: 2 }}>
                            {error}
                        </Alert>
                    )}

                    <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1 }}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="email"
                            label="Email"
                            name="email"
                            autoComplete="email"
                            autoFocus
                            value={formData.email}
                            onChange={handleChange}
                            disabled={isLoading}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            autoComplete="current-password"
                            value={formData.password}
                            onChange={handleChange}
                            disabled={isLoading}
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                            disabled={isLoading}
                        >
                            {isLoading ? <CircularProgress size={24} /> : 'SIGN IN'}
                        </Button>

                        <Box textAlign="center">
                            <Link component={RouterLink} to="/register" variant="body2">
                                Don't have an account? Sign up
                            </Link>
                        </Box>
                    </Box>
                </Paper>

                {/* Test accounts info */}
                <Paper
                    elevation={1}
                    sx={{
                        mt: 3,
                        p: 2,
                        backgroundColor: 'grey.50',
                        width: '100%',
                        maxWidth: 400
                    }}
                >
                    <Typography variant="subtitle2" align="center" gutterBottom>
                        Test Accounts:
                    </Typography>
                    <Typography variant="body2" align="center">
                        Username: <strong>user</strong> Password: <strong>user</strong>
                    </Typography>
                    <Typography variant="body2" align="center">
                        Username: <strong>admin</strong> Password: <strong>admin</strong>
                    </Typography>
                </Paper>
            </Box>
        </Container>
    );
};

export default Login;