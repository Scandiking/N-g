import React, { useState, useEffect } from 'react';
import {
    Box,
    Typography,
    Card,
    CardContent,
    Avatar,
    Grid,
    Chip,
    CircularProgress,
    Alert
} from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';

// Sample people data (matching current UI)
const samplePeople = [
    {
        phoneNo: "sample1",
        firstName: "Ingrid",
        lastName: "Hansen",
        mail: "ingrid.hansen@example.com",
        rooms: ["Dormitory", "School"]
    },
    {
        phoneNo: "sample2",
        firstName: "Magnus",
        lastName: "Lund",
        mail: "magnus.lund@example.com",
        rooms: ["Dormitory", "School"]
    },
    {
        phoneNo: "sample3",
        firstName: "Sofie",
        lastName: "Berg",
        mail: "sofie.berg@example.com",
        rooms: ["Dormitory"]
    }
];

const MyPeople = () => {
    const [backendPeople, setBackendPeople] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Combine sample people with backend people
    const allPeople = [...samplePeople, ...backendPeople];

    useEffect(() => {
        console.log('MyPeople useEffect: Fetching people...');
        fetchPeople();
    }, []);

    const fetchPeople = async () => {
        try {
            const token = localStorage.getItem('token');
            console.log('Fetching people with token:', token ? 'Token exists' : 'No token');

            let authHeader = '';
            if (token) {
                authHeader = token.startsWith('Bearer ') ? token : `Bearer ${token}`;
            }

            const response = await fetch('http://localhost:8080/api/persons', {
                headers: {
                    'Authorization': authHeader,
                    'Content-Type': 'application/json'
                }
            });

            console.log('People API response status:', response.status);

            if (response.ok) {
                const peopleData = await response.json();
                console.log('Backend people data received:', peopleData);

                // Transform backend data to match our format
                const transformedPeople = peopleData.map(person => ({
                    ...person,
                    rooms: [] // Will be populated later if needed
                }));

                setBackendPeople(transformedPeople);
            } else {
                console.warn('Failed to fetch people:', response.status, response.statusText);
                setError(`Failed to load people: ${response.status}`);
            }
        } catch (error) {
            console.error('Error fetching people:', error);
            setError('Failed to connect to server');
        } finally {
            setLoading(false);
        }
    };

    // Get initials for avatar
    const getInitials = (firstName, lastName) => {
        return `${firstName?.charAt(0) || ''}${lastName?.charAt(0) || ''}`.toUpperCase();
    };

    // Check if person is from backend (user-created)
    const isUserCreated = (person) => {
        return !person.phoneNo.startsWith('sample');
    };

    // Debug logging
    console.log('MyPeople render - allPeople:', allPeople.length);
    console.log('MyPeople render - sample:', samplePeople.length, 'backend:', backendPeople.length);

    return (
        <Box sx={{ padding: 3 }}>
            <Typography variant="h4" component="h1" gutterBottom>
                My persons
            </Typography>

            {loading && (
                <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                    <CircularProgress />
                    <Typography sx={{ ml: 2 }}>Loading people...</Typography>
                </Box>
            )}

            {error && (
                <Alert severity="warning" sx={{ mb: 2 }}>
                    {error} - Showing sample data only
                </Alert>
            )}

            <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                Showing {allPeople.length} people ({samplePeople.length} sample + {backendPeople.length} your people)
            </Typography>

            <Grid container spacing={2}>
                {allPeople.map((person, index) => (
                    <Grid item xs={12} sm={6} md={4} key={person.phoneNo || index}>
                        <Card
                            sx={{
                                height: '100%',
                                position: 'relative',
                                border: isUserCreated(person) ? '2px solid #4caf50' : '2px solid #ff9800',
                                backgroundColor: isUserCreated(person) ? 'white' : '#fff3e0'
                            }}
                        >
                            <CardContent sx={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
                                {/* User-created vs Sample indicator */}
                                {isUserCreated(person) ? (
                                    <Chip
                                        label="Your Person"
                                        size="small"
                                        color="success"
                                        sx={{
                                            position: 'absolute',
                                            top: 8,
                                            right: 8,
                                            fontSize: '0.7em'
                                        }}
                                    />
                                ) : (
                                    <Chip
                                        label="👤 SAMPLE PERSON"
                                        size="small"
                                        color="warning"
                                        sx={{
                                            position: 'absolute',
                                            top: 8,
                                            right: 8,
                                            fontSize: '0.7em',
                                            fontWeight: 'bold'
                                        }}
                                    />
                                )}

                                {/* Avatar and name */}
                                <Box sx={{ display: 'flex', alignItems: 'center', mb: 2, mt: 1 }}>
                                    <Avatar
                                        sx={{
                                            width: 60,
                                            height: 60,
                                            mr: 2,
                                            bgcolor: isUserCreated(person) ? '#4caf50' : '#ff9800'
                                        }}
                                    >
                                        {person.profilePicture ? (
                                            <img
                                                src={person.profilePicture}
                                                alt={`${person.firstName} ${person.lastName}`}
                                                style={{ width: '100%', height: '100%', objectFit: 'cover' }}
                                            />
                                        ) : (
                                            <Typography variant="h6">
                                                {getInitials(person.firstName, person.lastName)}
                                            </Typography>
                                        )}
                                    </Avatar>
                                    <Box>
                                        <Typography variant="h6" component="h2">
                                            {person.firstName} {person.lastName}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            {person.phoneNo}
                                        </Typography>
                                        {person.mail && (
                                            <Typography variant="body2" color="text.secondary">
                                                {person.mail}
                                            </Typography>
                                        )}
                                    </Box>
                                </Box>

                                {/* Rooms */}
                                {person.rooms && person.rooms.length > 0 && (
                                    <Box>
                                        <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
                                            Rooms: {person.rooms.join(', ')}
                                        </Typography>
                                    </Box>
                                )}

                                {/* Additional info for backend users */}
                                {isUserCreated(person) && (
                                    <Box sx={{ mt: 'auto', pt: 2 }}>
                                        <Typography variant="caption" color="text.secondary">
                                            Added via AddPeople
                                        </Typography>
                                        {person.birthDate && (
                                            <Typography variant="caption" display="block" color="text.secondary">
                                                Born: {new Date(person.birthDate).toLocaleDateString()}
                                            </Typography>
                                        )}
                                    </Box>
                                )}

                                {/* Sample person note */}
                                {!isUserCreated(person) && (
                                    <Box sx={{ mt: 'auto', pt: 2 }}>
                                        <Typography variant="caption" color="text.secondary">
                                            Demo person for testing
                                        </Typography>
                                    </Box>
                                )}
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>

            {allPeople.length === 0 && !loading && (
                <Box sx={{ textAlign: 'center', mt: 4 }}>
                    <PersonIcon sx={{ fontSize: 60, color: 'text.secondary', mb: 2 }} />
                    <Typography variant="h6" color="text.secondary">
                        No people found
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        Add people using the AddPeople page
                    </Typography>
                </Box>
            )}
        </Box>
    );
};

export default MyPeople;