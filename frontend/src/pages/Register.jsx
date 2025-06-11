// Register.jsx - Functional component that lets users sign up for Nag

import React, { useState } from 'react';
import { Box, Card, CardContent, CardMedia, Container, Stack, Typography, TextField, Tooltip, Button, Alert, CircularProgress } from "@mui/material";
import { useNavigate } from 'react-router-dom';
import Naglogo from "../assets/Nag-logo.png";

function Register() {
    const navigate = useNavigate();

    // Form state
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        phoneNo: '',
        password: ''
    });

    // UI state
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    // Handle input changes
    const handleInputChange = (field) => (event) => {
        setFormData(prev => ({
            ...prev,
            [field]: event.target.value
        }));
        // Clear error when user starts typing
        setError('');
    };

    // Validate form data
    const validateForm = () => {
        if (!formData.firstName.trim()) {
            setError('First name is required');
            return false;
        }
        if (!formData.lastName.trim()) {
            setError('Last name is required');
            return false;
        }
        if (!formData.email.trim()) {
            setError('Email is required');
            return false;
        }
        if (!formData.email.includes('@')) {
            setError('Please enter a valid email address');
            return false;
        }
        if (!formData.phoneNo.trim()) {
            setError('Phone number is required');
            return false;
        }
        if (formData.phoneNo.trim().length < 8) {
            setError('Phone number must be at least 8 digits');
            return false;
        }
        if (!formData.password.trim()) {
            setError('Password is required');
            return false;
        }
        if (formData.password.length < 6) {
            setError('Password must be at least 6 characters');
            return false;
        }
        return true;
    };

    // Handle form submission
    const handleSubmit = async (event) => {
        event.preventDefault();

        // Validate form
        if (!validateForm()) {
            return;
        }

        setLoading(true);
        setError('');

        try {
            const response = await fetch('http://localhost:8080/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    firstName: formData.firstName.trim(),
                    lastName: formData.lastName.trim(),
                    email: formData.email.trim().toLowerCase(),
                    phoneNo: formData.phoneNo.trim(),
                    password: formData.password
                })
            });

            const data = await response.json();

            if (response.ok) {
                // Success!
                setSuccess(`Welcome ${data.name}! Account created successfully.`);

                // Clear form
                setFormData({
                    firstName: '',
                    lastName: '',
                    email: '',
                    phoneNo: '',
                    password: ''
                });

                // Redirect to login after 2 seconds
                setTimeout(() => {
                    navigate('/login');
                }, 2000);

            } else {
                // Error from backend
                setError(data.error || 'Registration failed. Please try again.');
            }

        } catch (error) {
            console.error('Registration error:', error);
            setError('Network error. Please check your connection and try again.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <Container sx={{ p: 10, display:"flex", justifyContent:"center", width: '100%' }}>
            <Card sx={{ maxWidth: 400, width: '100%' }}>
                <Box sx={{p:2, display:"flex", justifyContent:"center", width:'100%'}}>
                    <CardMedia
                        sx={{
                            width: 100,
                            height: 100,
                            objectFit: "contain",
                            borderRadius: 1,
                            m: 1
                        }}
                        image={Naglogo}
                        title="Næg logo"
                    />
                </Box>
                <CardContent>
                    <Typography variant="h3" sx={{ flexGrow: 1, p: 2, textAlign: 'center' }} >
                        {"Create a new Næg account"}
                    </Typography>

                    {/* Success Message */}
                    {success && (
                        <Alert severity="success" sx={{ mb: 2 }}>
                            {success}
                        </Alert>
                    )}

                    {/* Error Message */}
                    {error && (
                        <Alert severity="error" sx={{ mb: 2 }}>
                            {error}
                        </Alert>
                    )}

                    <form onSubmit={handleSubmit}>
                        <Stack spacing={2}>
                            <Tooltip title="Enter your first name" placement="top-start" arrow>
                                <TextField
                                    id="first-name"
                                    label="First Name"
                                    variant="outlined"
                                    value={formData.firstName}
                                    onChange={handleInputChange('firstName')}
                                    disabled={loading}
                                    required
                                />
                            </Tooltip>

                            <Tooltip title="Enter your last name" placement="top-start" arrow>
                                <TextField
                                    id="last-name"
                                    label="Last Name"
                                    variant="outlined"
                                    value={formData.lastName}
                                    onChange={handleInputChange('lastName')}
                                    disabled={loading}
                                    required
                                />
                            </Tooltip>

                            <Tooltip title="Enter your e-mail address" placement="top-start" arrow>
                                <TextField
                                    id="email"
                                    label="E-mail address"
                                    variant="outlined"
                                    type="email"
                                    value={formData.email}
                                    onChange={handleInputChange('email')}
                                    disabled={loading}
                                    required
                                />
                            </Tooltip>

                            <Tooltip title="Enter your phone number" placement="top-start" arrow>
                                <TextField
                                    id="phone-num"
                                    label="Phone No."
                                    variant="outlined"
                                    value={formData.phoneNo}
                                    onChange={handleInputChange('phoneNo')}
                                    disabled={loading}
                                    required
                                />
                            </Tooltip>

                            <Tooltip title="Enter your password (minimum 6 characters)" placement="top-start" arrow>
                                <TextField
                                    id="user-pw"
                                    label="Password"
                                    variant="outlined"
                                    type="password"
                                    value={formData.password}
                                    onChange={handleInputChange('password')}
                                    disabled={loading}
                                    required
                                />
                            </Tooltip>

                            <Tooltip title="Click or tap this to board onto Næg!" placement="top-start" arrow>
                                <Button
                                    variant="contained"
                                    type="submit"
                                    disabled={loading}
                                    sx={{ py: 1.5 }}
                                >
                                    {loading ? (
                                        <>
                                            <CircularProgress size={20} sx={{ mr: 1 }} />
                                            Creating Account...
                                        </>
                                    ) : (
                                        'Sign up'
                                    )}
                                </Button>
                            </Tooltip>

                            {/* Link to Login */}
                            <Box sx={{ textAlign: 'center', pt: 2 }}>
                                <Typography variant="body2" color="text.secondary">
                                    Already have an account?{' '}
                                    <Button
                                        variant="text"
                                        onClick={() => navigate('/login')}
                                        disabled={loading}
                                    >
                                        Sign in here
                                    </Button>
                                </Typography>
                            </Box>
                        </Stack>
                    </form>
                </CardContent>
            </Card>
        </Container>
    );
}

export default Register;