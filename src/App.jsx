import React, { useState } from 'react';
import { BrowserRouter, Routes, Route, useLocation } from 'react-router-dom';

// Import av egne komponenter
import Header from './Header';
import AddTask from './AddTask';
import AddPeople from "./AddPeople";
import AddRoom from "./AddRoom";
import Statistics from "./Statistics";
import Payments from "./Payments";
import Settings from "./Settings";

// Komponenter fra MUI framework
import { Box, Grid, Typography, Button, Paper, Stack, Tooltip } from '@mui/material';
import FloatingActionButton from "./FloatingActionButton";

// Lyst/mørkt tema, kontekst
import ThemeContextProvider from "./ThemeContext";
import ThemeProvider from './ThemeProvider';

// For UX appearing swifter and more responsive to user by adding visual responses in form of animations
import { motion, AnimatePresence } from "framer-motion"; // Import Framer Motion


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
    },
    {
        id: 4,
        title: 'Ta ut av oppvaskmaskinen',
        description: 'Det er rent, skapene er tomme for asjetter og glass'
    },
    {
        id: 5,
        title:'Sette inn i oppvaskmaskinen',
        description:'Benken er full av skitne glass og tallerkner'
    },
];

function AppContent() {
    const location = useLocation();
    const [tasks, setTasks] = useState(sampleTasks);

    const handleComplete = (id) => {
        setTasks((prevTasks) => prevTasks.filter((task) => task.id !== id));
    };

    return (
        <>
            {/* Render the FAB only on specific routes if needed */}
            {location.pathname !== '/settings' && <FloatingActionButton />}
            <Routes>
                <Route path="/" element={


                        <Box sx={{ display: 'flex', justifyContent: 'center', p: 3 }}>
                            <Box sx={{ maxWidth: '1200px', width: '100%' }}>
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
                                                        <Paper sx={{ p: 3, height: "100%", display:'flex', flexDirection:'column', justifyContent: 'start' }} elevation={3}>
                                                            <Typography variant="h6" component="div" gutterBottom>
                                                                <Tooltip title="Tittelen på oppgaven" placement="top" arrow>
                                                                    {task.title}
                                                                </Tooltip>
                                                            </Typography>

                                                            <Typography variant="body1" component="p" sx={{ mb: 2 }}>
                                                                <Tooltip title="Utfyllende om oppgaven" placement="top" arrow>
                                                                    {task.description}
                                                                </Tooltip>
                                                            </Typography>

                                                            <Stack spacing={2} direction="row">
                                                                <Tooltip title="Gjør oppgaven og trykk her for å slippe næggingen" placement="top" arrow>
                                                                    <Button
                                                                        variant="contained"
                                                                        color="success"
                                                                        onClick={() => handleComplete(task.id)}
                                                                    >
                                                                        Complete task
                                                                    </Button>
                                                                </Tooltip>
                                                                <Tooltip title="Data om data. Hvem er oppgaven fra? Når lagde den?" placement="top" arrow>
                                                                    <Button variant="outlined">Task info</Button>
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
                                            <Typography variant="h4" component="div" color="success.main">
                                                You are free!
                                            </Typography>
                                            <Typography variant="body1" sx={{ mt: 2 }}>
                                                Enjoy your time - you've earned it!
                                            </Typography>
                                        </motion.div>
                                    )}
                                </AnimatePresence>
                            </Box>



                    </Box>

                } />

                {/* Routes (for sidebar) to take you to the components */}
                <Route path="/add-task" element={<AddTask />} />
                <Route path="/add-people" element={<AddPeople />} />
                <Route path="/add-room" element={<AddRoom />} />
                <Route path="/statistics" element={<Statistics />} />
                <Route path="/payments" element={<Payments />} />
                <Route path="/settings" element={<Settings />} />
            </Routes>
        </>
    );
}

function App() {
    return (
        <ThemeContextProvider> {/* Provides theme state (light/dark/auto) */}
            <ThemeProvider> {/* Applies Material UI theme based on theme context */}
                <BrowserRouter>
                    <Header />
                    <AppContent />
                </BrowserRouter>
            </ThemeProvider>
        </ThemeContextProvider>
    );
}

export default App;
