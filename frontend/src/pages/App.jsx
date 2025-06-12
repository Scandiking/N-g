// src/pages/App.jsx
import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, useLocation, Navigate } from 'react-router-dom';

// All of these components live side-by-side in src/pages/
import Header from './Header';
import FloatingActionButton from './FloatingActionButton';
import AddTask from './AddTask';
import AddPeople from './AddPeople';
import AddRoom from './AddRoom';
import MyTasks from './MyTasks';
import MyPeople from './MyPeople';
import MyRooms from './MyRooms';
import Home from './Home';
import Statistics from './Statistics';
import Payments from './Payments';
import Settings from './Settings';
import Login from './Login';
import Register from './Register';
import ThemeContextProvider from './ThemeContext';
import ThemeProvider from './ThemeProvider';

// MUI & Framer Motion
import {
    Container,
    Grid,
    Typography,
    Paper,
    Stack,
    Tooltip,
    Button
} from '@mui/material';
import CardMedia from '@mui/material/CardMedia';
import { motion, AnimatePresence } from 'framer-motion';

export default function App() {
    const [isModalOpen, setIsModalOpen] = useState(false);

    return (
        <ThemeContextProvider>
            <ThemeProvider>
                <BrowserRouter>
                    <Header onAddTask={() => setIsModalOpen(true)} />
                    <FloatingActionButton onAddTask={() => setIsModalOpen(true)} />
                    <AddTask open={isModalOpen} onClose={() => setIsModalOpen(false)} />

                    <AppContent
                        isModalOpen={isModalOpen}
                        setIsModalOpen={setIsModalOpen}
                    />
                </BrowserRouter>
            </ThemeProvider>
        </ThemeContextProvider>
    );
}

function AppContent({ isModalOpen, setIsModalOpen }) {
    const location = useLocation();
    const [tasks, setTasks] = useState([]);

    // Fetch tasks once on mount
    useEffect(() => {
        (async () => {
            const token = localStorage.getItem('token');
            const auth = token?.startsWith('Bearer ') ? token : `Bearer ${token}`;
            const res = await fetch('http://localhost:8080/api/tasks', {
                headers: { Authorization: auth || '' },
            });
            if (res.ok) setTasks(await res.json());
            else console.error('Failed fetching tasks:', await res.text());
        })();
    }, []);

    const handleComplete = (id) => {
        // Optionally also sync this change with the backend
        setTasks(prev => prev.filter(t => t.taskId !== id));
    };

    return (
        <>
            {location.pathname !== '/settings' && (
                <FloatingActionButton onAddTask={() => setIsModalOpen(true)} />
            )}

            <Routes>
                <Route
                    path="/"
                    element={
                        <Container sx={{ p: 3 }}>
                            <AnimatePresence>
                                {tasks.length > 0 ? (
                                    <Grid container spacing={3}>
                                        {tasks.map(task => (
                                            <Grid item xs={12} sm={6} md={4} key={task.taskId}>
                                                <motion.div
                                                    initial={{ opacity: 0, y: 50 }}
                                                    animate={{ opacity: 1, y: 0 }}
                                                    exit={{ opacity: 0, y: -50 }}
                                                    transition={{ duration: 0.3 }}
                                                >
                                                    <Paper
                                                        sx={{
                                                            p: 3,
                                                            height: '100%',
                                                            display: 'flex',
                                                            flexDirection: 'column',
                                                        }}
                                                        elevation={3}
                                                    >
                                                        <Typography variant="h6" gutterBottom>
                                                            <Tooltip title="Tittelen på oppgaven" arrow>
                                                                {task.title}
                                                            </Tooltip>
                                                        </Typography>
                                                        <CardMedia
                                                            component="img"
                                                            sx={{
                                                                height: 140,
                                                                backgroundColor: 'grey.200',
                                                                borderRadius: 2,
                                                                mb: 2,
                                                            }}
                                                            title="Picture"
                                                        />
                                                        <Typography variant="body1" sx={{ mb: 2 }}>
                                                            <Tooltip title="Utfyllende om oppgaven" arrow>
                                                                {task.description}
                                                            </Tooltip>
                                                        </Typography>
                                                        <Stack direction="row" spacing={1}>
                                                            <Tooltip title="Complete task" arrow>
                                                                <Button
                                                                    variant="contained"
                                                                    color="success"
                                                                    onClick={() => handleComplete(task.taskId)}
                                                                >
                                                                    Complete task
                                                                </Button>
                                                            </Tooltip>
                                                            <Tooltip title="Task info" arrow>
                                                                <Button variant="outlined">
                                                                    Task info
                                                                </Button>
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
                                        style={{
                                            textAlign: 'center',
                                            marginTop: '50px',
                                        }}
                                    >
                                        <Typography variant="h4" color="success.main">
                                            You are free!
                                        </Typography>
                                        <Typography sx={{ mt: 2 }}>
                                            Enjoy your time – you've earned it!
                                        </Typography>
                                    </motion.div>
                                )}
                            </AnimatePresence>
                        </Container>
                    }
                />

                <Route path="/add-task" element={<Navigate to="/" />} />
                <Route path="/add-people" element={<AddPeople />} />
                <Route path="/add-room" element={<AddRoom />} />
                <Route path="/mypeople" element={<MyPeople />} />
                <Route path="/myrooms" element={<MyRooms />} />
                <Route path="/mytasks" element={<MyTasks />} />
                <Route path="/statistics" element={<Statistics />} />
                <Route path="/payments" element={<Payments />} />
                <Route path="/settings" element={<Settings />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/home" element={<Home />} />
            </Routes>
        </>
    );
}
