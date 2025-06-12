import React, {useState, useEffect} from "react";
import {Box, Card, CardContent, Typography, Avatar, Grid, Chip, CircularProgress, Alert} from "@mui/material";
import {Person, Email, Phone, Security} from "@mui/icons-material";

const MyAccount = () => {
    const [profile, setProfile] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetchProfile();
    }, []);

    const fetchProfile = async () => {
        try {
            const token = localStorage.getItem("token");

            if (!token) {
                setError("No authentication token found.");
                setLoading(false);
                return;
            }

            const response = await fetch("http://localhost:8080/api/profile", {
                method: "GET",
                headers: {
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": "application/json"
                },
            });

            if (response.ok) {
                const data = await response.json();
                setProfile(data);
            } else {
                setError("Failed to load profile.");
            }
        } catch (err) {
            setError("Network error: " + err.message);
        } finally {
            setLoading(false);
        }
    };

    if (loading) {
        return (
            <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
                <CircularProgress/>
            </Box>
        );
    }

    if (error) {
        return (
            <Box p={3}>
                <Alert severity="error">{error}</Alert>
            </Box>
        );
    }

    if (!profile) {
        return (
            <Box p={3}>
                <Alert severity="warning">No profile data available</Alert>
            </Box>
        );
    }

    const getInitials = () => {
        if (profile.firstName && profile.lastName) {
            return `${profile.firstName[0]}${profile.lastName[0]}`.toUpperCase();
        }
        return profile.username ? profile.username[0].toUpperCase() : "U";
    };

    return (
        <Box p={3}>
            <Typography variant="h4" gutterBottom>
                My Profile
            </Typography>

            <Card>
                <CardContent>
                    <Box display="flex" alignItems="center" mb={3}>
                        <Avatar
                            sx={{
                                width: 80,
                                height: 80,
                                mr: 3,
                                bgcolor: "primary.main",
                                fontSize: "2rem"
                            }}
                        >
                            {getInitials()}
                        </Avatar>
                        <Box>
                            <Typography variant="h5">
                                {profile.firstName && profile.lastName
                                    ? `${profile.firstName} ${profile.lastName}`
                                    : profile.username
                                }
                            </Typography>
                            <Chip label={profile.role}
                                  color={profile.role === 'ADMIN' ? 'secondary' : 'primary'}
                                  size="small"
                                  sx={{mt: 1}}
                            />
                        </Box>
                    </Box>

                    <Grid container spacing={3}>
                        <Grid item xs={12} md={6}>
                            <Box display="flex" alignItems="center" mb={2}>
                                <Person sx={{mr: 2, color: "text.secondary"}}/>
                                <Box>
                                    <Typography variant="subtitle2" color="text.secondary">
                                        Full Name
                                    </Typography>
                                    <Typography variant="body1">
                                        {profile.firstName && profile.lastName
                                            ? `${profile.firstName} ${profile.lastName}`
                                            : 'Not provided'
                                        }
                                    </Typography>
                                </Box>
                            </Box>
                        </Grid>

                        <Grid item xs={12} md={6}>
                            <Box display="flex" alignItems="center" mb={2}>
                                <Email sx={{mr: 2, color: "text.secondary"}}/>
                                <Box>
                                    <Typography variant="subtitle2" color="text.secondary">
                                        Email
                                    </Typography>
                                    <Typography variant="body1">
                                        {profile.email || 'Not provided'}
                                    </Typography>
                                </Box>
                            </Box>
                        </Grid>

                        <Grid item xs={12} md={6}>
                            <Box display="flex" alignItems="center" mb={2}>
                                <Phone sx={{mr: 2, color: 'text.secondary'}}/>
                                <Box>
                                    <Typography variant="subtitle2" color="text.secondary">
                                        Phone Number
                                    </Typography>
                                    <Typography variant="body1">
                                        {profile.phoneNo || 'Not provided'}
                                    </Typography>
                                </Box>
                            </Box>
                        </Grid>

                        <Grid item xs={12} md={6}>
                            <Box display="flex" alignItems="center" mb={2}>
                                <Security sx={{mr: 2, color: 'text.secondary'}}/>
                                <Box>
                                    <Typography variant="subtitle2" color="text.secondary">
                                        Username
                                    </Typography>
                                    <Typography variant="body1">
                                        {profile.username}
                                    </Typography>
                                </Box>
                            </Box>
                        </Grid>


                    </Grid>


                </CardContent>
            </Card>
        </Box>
    )
}

export default MyAccount;