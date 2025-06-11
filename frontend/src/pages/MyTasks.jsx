// src/pages/MyTasks.jsx
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AddTask from './AddTask';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import Stack from '@mui/material/Stack';
import InfoIcon from '@mui/icons-material/Info';

function MyTasks() {
    const [tasks, setTasks] = useState([]);
    const [addOpen, setAddOpen] = useState(false);
    const navigate = useNavigate();

    // 1) Load tasks on mount
    useEffect(() => {
        const fetchTasks = async () => {
            try {
                const token = localStorage.getItem('token');
                const res = await fetch('http://localhost:8080/api/tasks', {
                    headers: {
                        'Content-Type': 'application/json',
                        ...(token ? { Authorization: token.startsWith('Bearer ') ? token : `Bearer ${token}` } : {})
                    },
                });
                if (!res.ok) throw new Error('Failed to load tasks');
                const data = await res.json();
                // Only show tasks not yet completed
                setTasks(data.filter(t => !t.completed));
            } catch (err) {
                console.error(err);
            }
        };
        fetchTasks();
    }, []);

    // 2) Mark a task done and remove from list
    const handleComplete = async (taskId) => {
        try {
            const token = localStorage.getItem('token');
            const res = await fetch(
                `http://localhost:8080/api/tasks/${taskId}/done`,
                {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                        ...(token ? { Authorization: token.startsWith('Bearer ') ? token : `Bearer ${token}` } : {})
                    },
                }
            );
            if (!res.ok) throw new Error('Could not mark done');
            setTasks(prev => prev.filter(t => t.taskId !== taskId));
        } catch (err) {
            console.error(err);
        }
    };

    // 3) Navigate to detail page
    const handleInfo = (taskId) => {
        navigate(`/tasks/${taskId}`);
    };

    return (
        <Container sx={{ mt: 4, p: 3 }}>
            {/* Button to open "Add Task" dialog */}
            <Button
                variant="contained"
                color="primary"
                sx={{ mb: 2 }}
                onClick={() => setAddOpen(true)}
            >
                + Add Task
            </Button>

            <Grid container spacing={3}>
                {tasks.map(task => (
                    <Grid item xs={12} sm={6} md={4} key={task.taskId}>
                        <Card sx={{ p: 2, boxShadow: 3 }}>
                            <CardContent>
                                <Typography variant="h6">{task.title}</Typography>
                                <Typography sx={{ mb: 2 }}>
                                    {task.description}
                                </Typography>
                                <Stack direction="row" gap={1}>
                                    <Button
                                        variant="contained"
                                        color="success"
                                        onClick={() => handleComplete(task.taskId)}
                                    >
                                        Mark as Done
                                    </Button>
                                    <Button
                                        variant="outlined"
                                        startIcon={<InfoIcon />}
                                        onClick={() => handleInfo(task.taskId)}
                                    >
                                        Info
                                    </Button>
                                </Stack>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>

            {/* AddTask dialog */}
            <AddTask open={addOpen} onClose={() => setAddOpen(false)} />
        </Container>
    );
}

export default MyTasks;
