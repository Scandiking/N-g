// App.jsx - FIKSET VERSION med proper spacing

import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, useLocation, Navigate } from 'react-router-dom';

// Import av egne komponenter
import Header from './Header';
import AddTask from './AddTask';
import AddPeople from "./AddPeople";
import AddRoom from "./AddRoom";
import MyTasks from "./MyTasks";
import MyPeople from "./MyPeople";
import MyRooms from "./MyRooms";
import Statistics from "./Statistics";
import Payments from "./Payments";
import Settings from "./Settings";
import Login from './Login';
import Register from './Register';

// Komponenter fra MUI framework
import { Grid, Container, Typography, Button, Paper, Stack, Tooltip, Box, CircularProgress, Alert, Chip, Toolbar } from '@mui/material';
import FloatingActionButton from "./FloatingActionButton";

// Lyst/mørkt tema, kontekst
import ThemeContextProvider from "./ThemeContext";
import ThemeProvider from './ThemeProvider';
import { AuthProvider } from './AuthContext';

// For UX appearing swifter and more responsive to user by adding visual responses in form of animations
import { motion, AnimatePresence } from "framer-motion";

import Home from "./Home";

/*
sampleTasks er listen med eksempeloppgaver du ser på landingssiden.
 */
const sampleTasks = [
    {
        id: 1,
        title: 'Støvsuge kjøkkenet',
        description: 'Fjern smuler og hundehår osv.'
    },
    {
        id: 2,
        title: 'Ta ut søpla',
        description: 'Kyllingemballasje lukter guffent fort. Må fjernes. Restavfall, matavfall, papp/papir.'
    },
    {
        id: 3,
        title: 'Vaske speilet',
        description: 'Speilet er fullt av tannkrem(?) igjen'
    }
];

function AppContent() {
    const location = useLocation();
    const [tasks, setTasks] = useState(sampleTasks);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [tasksRefreshTrigger, setTasksRefreshTrigger] = useState(0);

    // ✅ NY: State for assigned tasks (hjemmeside)
    const [assignedTasks, setAssignedTasks] = useState([]);
    const [loadingAssigned, setLoadingAssigned] = useState(true);
    const [assignedError, setAssignedError] = useState(null);

    const handleComplete = (id) => {
        setTasks((prevTasks) => prevTasks.filter((task) => task.id !== id));
    };

    const handleTaskCreated = () => {
        console.log('New task created, triggering refresh...');
        setTasksRefreshTrigger(prev => prev + 1);
        // ✅ Refresh assigned tasks også
        fetchAssignedTasks();
    };

    // ✅ NY: Fetch assigned tasks for home page
    const fetchAssignedTasks = async () => {
        try {
            const token = localStorage.getItem('token');

            if (!token) {
                setLoadingAssigned(false);
                return;
            }

            let authHeader = token.startsWith('Bearer ') ? token : `Bearer ${token}`;

            const response = await fetch('http://localhost:8080/api/taskforpersons/my-tasks', {
                headers: {
                    'Authorization': authHeader,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const assignedData = await response.json();
                console.log('Assigned tasks received:', assignedData);
                setAssignedTasks(assignedData);
                setAssignedError(null);
            } else {
                console.warn('Failed to fetch assigned tasks:', response.status);
                setAssignedError('Failed to load your assigned tasks');
            }
        } catch (error) {
            console.error('Error fetching assigned tasks:', error);
            setAssignedError('Failed to connect to server');
        } finally {
            setLoadingAssigned(false);
        }
    };

    // ✅ NY: Fetch assigned tasks on component mount
    useEffect(() => {
        fetchAssignedTasks();
    }, []);

    const getStatusColor = (status) => {
        switch (status?.toLowerCase()) {
            case 'completed': return 'success';
            case 'in_progress': return 'warning';
            case 'pending': return 'default';
            default: return 'default';
        }
    };

    const getPriorityColor = (priority) => {
        switch (priority?.toLowerCase()) {
            case 'high': return 'error';
            case 'medium': return 'warning';
            case 'low': return 'info';
            default: return 'default';
        }
    };

    return (
        <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
            {/* ✅ FIKSET: Legg til Toolbar spacer for fixed AppBar */}
            <Toolbar />

            {/* floating action button triggers modal */}
            {location.pathname !== '/settings' && (
                <FloatingActionButton onAddTask={() => setIsModalOpen(true)} />
            )}

            <AddTask
                open={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onTaskCreated={handleTaskCreated}
            />

            {/* ✅ FIKSET: Wrap routes i Container med proper spacing */}
            <Container maxWidth="xl" sx={{ mt: 2, flex: 1 }}>
                <Routes>
                    <Route path="/" element={
                        <Box sx={{ p: 3 }}>
                            <Typography variant="h4" component="h1" gutterBottom>
                                Your Assigned Tasks
                            </Typography>

                            {loadingAssigned ? (
                                <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                                    <CircularProgress />
                                    <Typography sx={{ ml: 2 }}>Loading your tasks...</Typography>
                                </Box>
                            ) : assignedError ? (
                                <Alert severity="info" sx={{ mb: 3 }}>
                                    {assignedError}. Showing sample tasks instead.
                                </Alert>
                            ) : null}

                            {assignedTasks.length === 0 && !loadingAssigned && !assignedError ? (
                                <Box sx={{ textAlign: 'center', mt: 4, mb: 4 }}>
                                    <Typography variant="h6" color="text.secondary" gutterBottom>
                                        🎉 No tasks assigned to you!
                                    </Typography>
                                    <Typography variant="body1" color="text.secondary">
                                        You're free to enjoy your time, or create new tasks to assign to others.
                                    </Typography>
                                </Box>
                            ) : assignedTasks.length > 0 ? (
                                <>
                                    <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
                                        You have {assignedTasks.length} assigned task{assignedTasks.length !== 1 ? 's' : ''}
                                    </Typography>

                                    <Grid container spacing={3}>
                                        {assignedTasks.map((taskAssignment, index) => {
                                            const task = taskAssignment.task; // TaskForPersonDTO har nested task
                                            return (
                                                <Grid item xs={12} sm={6} md={4} key={`assigned-${task?.taskId || index}`}>
                                                    <motion.div
                                                        initial={{ opacity: 0, y: 50 }}
                                                        animate={{ opacity: 1, y: 0 }}
                                                        exit={{ opacity: 0, y: -50 }}
                                                        transition={{ duration: 0.3 }}
                                                    >
                                                        <Paper sx={{
                                                            p: 3,
                                                            height: "100%",
                                                            display:'flex',
                                                            flexDirection:'column',
                                                            justifyContent: 'space-between',
                                                            position: 'relative',
                                                            border: '2px solid #2196f3',
                                                            backgroundColor: '#e3f2fd'
                                                        }} elevation={3}>
                                                            {/* Assigned task indicator */}
                                                            <Chip
                                                                label="📋 ASSIGNED TO YOU"
                                                                size="small"
                                                                color="primary"
                                                                sx={{
                                                                    position: 'absolute',
                                                                    top: 12,
                                                                    right: 12,
                                                                    fontSize: '0.7em',
                                                                    fontWeight: 'bold'
                                                                }}
                                                            />

                                                            <Box>
                                                                <Typography variant="h6" component="div" gutterBottom sx={{ mt: 1 }}>
                                                                    {task?.title || 'Untitled Task'}
                                                                </Typography>

                                                                {/* Task status and priority */}
                                                                {task && (
                                                                    <Box sx={{ display: 'flex', gap: 1, mb: 2, flexWrap: 'wrap' }}>
                                                                        <Chip
                                                                            label={task.status || 'pending'}
                                                                            color={getStatusColor(task.status)}
                                                                            size="small"
                                                                        />
                                                                        {task.priority && (
                                                                            <Chip
                                                                                label={`${task.priority} priority`}
                                                                                color={getPriorityColor(task.priority)}
                                                                                size="small"
                                                                                variant="outlined"
                                                                            />
                                                                        )}
                                                                    </Box>
                                                                )}

                                                                <Typography variant="body1" component="p" sx={{ mb: 2, minHeight: 48 }}>
                                                                    {task?.description || 'No description provided'}
                                                                </Typography>

                                                                {task?.dueDate && (
                                                                    <Typography variant="caption" color="text.secondary" display="block" sx={{ mb: 1 }}>
                                                                        Due: {new Date(task.dueDate).toLocaleDateString()}
                                                                    </Typography>
                                                                )}
                                                            </Box>

                                                            <Stack spacing={2} direction="row">
                                                                <Button
                                                                    variant="contained"
                                                                    color="success"
                                                                    fullWidth
                                                                    disabled={task?.completed}
                                                                >
                                                                    {task?.completed ? 'Completed' : 'Mark Complete'}
                                                                </Button>
                                                                <Button variant="outlined" fullWidth>
                                                                    Task Details
                                                                </Button>
                                                            </Stack>
                                                        </Paper>
                                                    </motion.div>
                                                </Grid>
                                            );
                                        })}
                                    </Grid>
                                </>
                            ) : null}

                            {/* Fallback: Show sample tasks if no assigned tasks or error */}
                            {(assignedError || (!loadingAssigned && assignedTasks.length === 0)) && (
                                <>
                                    <Typography variant="h5" sx={{ mt: 4, mb: 2 }}>
                                        Sample Tasks (Demo)
                                    </Typography>
                                    <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
                                        Showing {tasks.length} sample tasks for demonstration
                                    </Typography>
                                    <AnimatePresence>
                                        {tasks.length > 0 ? (
                                            <Grid container spacing={3}>
                                                {tasks.map((task) => (
                                                    <Grid item xs={12} sm={6} md={4} key={task.id}>
                                                        <motion.div
                                                            initial={{ opacity: 0, y: 50 }}
                                                            animate={{ opacity: 1, y: 0 }}
                                                            exit={{ opacity: 0, y: -50 }}
                                                            transition={{ duration: 0.3 }}
                                                        >
                                                            <Paper sx={{
                                                                p: 3,
                                                                height: "100%",
                                                                display:'flex',
                                                                flexDirection:'column',
                                                                justifyContent: 'space-between',
                                                                position: 'relative',
                                                                border: '2px solid #ff9800',
                                                                backgroundColor: '#fff3e0'
                                                            }} elevation={3}>
                                                                <Chip
                                                                    label="📝 SAMPLE TASK"
                                                                    size="small"
                                                                    color="warning"
                                                                    sx={{
                                                                        position: 'absolute',
                                                                        top: 12,
                                                                        right: 12,
                                                                        fontSize: '0.7em',
                                                                        fontWeight: 'bold'
                                                                    }}
                                                                />

                                                                <Box>
                                                                    <Typography variant="h6" component="div" gutterBottom sx={{ mt: 1 }}>
                                                                        <Tooltip title="Tittelen på oppgaven" placement="top" arrow>
                                                                            {task.title}
                                                                        </Tooltip>
                                                                    </Typography>
                                                                    <Typography variant="body1" component="p" sx={{ mb: 2, minHeight: 48 }}>
                                                                        <Tooltip title="Utfyllende om oppgaven" placement="top" arrow>
                                                                            {task.description}
                                                                        </Tooltip>
                                                                    </Typography>
                                                                </Box>

                                                                <Stack spacing={2} direction="row">
                                                                    <Tooltip title="Gjør oppgaven og trykk her for å slippe næggingen" placement="top" arrow>
                                                                        <Button
                                                                            variant="contained"
                                                                            color="success"
                                                                            onClick={() => handleComplete(task.id)}
                                                                            fullWidth
                                                                        >
                                                                            Complete task
                                                                        </Button>
                                                                    </Tooltip>
                                                                    <Tooltip title="Data om data. Hvem er oppgaven fra? Når lagde den?" placement="top" arrow>
                                                                        <Button variant="outlined" fullWidth>Task info</Button>
                                                                    </Tooltip>
                                                                </Stack>
                                                            </Paper>
                                                        </motion.div>
                                                    </Grid>
                                                ))}
                                            </Grid>
                                        ) : (
                                            <motion.div
                                                initial={{ opacity: 0, scale: 0.8 }}
                                                animate={{ opacity: 1, scale: 1 }}
                                                transition={{ duration: 0.5 }}
                                                style={{ textAlign: 'center', marginTop: '50px' }}
                                            >
                                                <Typography variant="h4" component="div" color="success.main">
                                                    You are free!
                                                </Typography>
                                                <Typography variant="body1" sx={{ mt: 2 }}>
                                                    Enjoy your time - you've earned it!
                                                </Typography>
                                            </motion.div>
                                        )}
                                    </AnimatePresence>
                                </>
                            )}
                        </Box>
                    } />

                    {/* Routes (for sidebar) to take you to the components */}
                    <Route path="/add-task" element={<Navigate to="/" />} />
                    <Route path="/add-people" element={<AddPeople />} />
                    <Route path="/add-room" element={<AddRoom />} />
                    <Route path="/mypeople" element={<MyPeople />} />
                    <Route path="/myrooms" element={<MyRooms />} />
                    <Route path="/mytasks" element={<MyTasks refreshTrigger={tasksRefreshTrigger} />} />
                    <Route path="/statistics" element={<Statistics />} />
                    <Route path="/payments" element={<Payments />} />
                    <Route path="/settings" element={<Settings />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/home" element={<Home />} />
                </Routes>
            </Container>
        </Box>
    );
}

function App() {
    const [isModalOpen, setIsModalOpen] = useState(false);

    return (
        <AuthProvider> {/* Provides authentication state */}
            <ThemeContextProvider> {/* Provides theme state (light/dark/auto) */}
                <ThemeProvider> {/* Applies Material UI theme based on theme context */}
                    <BrowserRouter>
                        <Header onAddTask={() => setIsModalOpen(true)} />
                        <FloatingActionButton onAddTask={() => setIsModalOpen(true)} />

                        <AddTask open={isModalOpen} onClose={() => setIsModalOpen(false)} />
                        <AppContent isModalOpen={isModalOpen} setIsModalOpen={setIsModalOpen} />

                    </BrowserRouter>
                </ThemeProvider>
            </ThemeContextProvider>
        </AuthProvider>
    );
}

export default App;