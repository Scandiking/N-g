import React, { useState, useEffect } from "react";
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import Stack from '@mui/material/Stack';
import InfoIcon from '@mui/icons-material/Info';
import { TransitionGroup } from 'react-transition-group';
import Collapse from '@mui/material/Collapse';

export default function MyTasks() {
    const [tasks, setTasks] = useState([]);

    // Fetch tasks on mount
    useEffect(() => {
        fetchTasks();
    }, []);

    const fetchTasks = async () => {
        const token = localStorage.getItem('token');
        const auth = token?.startsWith('Bearer ') ? token : `Bearer ${token}`;
        const res = await fetch('http://localhost:8080/api/tasks', {
            headers: { 'Authorization': auth || '' }
        });
        if (res.ok) {
            setTasks(await res.json());
        } else {
            console.error('Failed to fetch tasks', await res.text());
        }
    };

    const handleComplete = (id) => {
        // optionally: call DELETE or PATCH here
        setTasks(prev => prev.filter(t => t.taskId !== id));
    };

    return (
        <Container sx={{ mt: 4, p: 3 }}>
            <TransitionGroup component={Grid} container spacing={3}>
                {tasks.map(task => (
                    <Collapse key={task.taskId}>
                        <Grid item xs={12} sm={6} md={4}>
                            <Card sx={{ p: 2, boxShadow: 3 }}>
                                <CardContent>
                                    <Typography variant="h6">{task.title}</Typography>
                                    <Typography>{task.description}</Typography>
                                    <Stack direction="row" gap={1} sx={{ mt: 2 }}>
                                        <Button
                                            variant="contained"
                                            color="success"
                                            onClick={() => handleComplete(task.taskId)}
                                        >
                                            Mark as Done
                                        </Button>
                                        <Button variant="outlined" startIcon={<InfoIcon />}>
                                            Info
                                        </Button>
                                    </Stack>
                                </CardContent>
                            </Card>
                        </Grid>
                    </Collapse>
                ))}
            </TransitionGroup>
        </Container>
    );
}
