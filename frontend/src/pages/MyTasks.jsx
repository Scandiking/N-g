// src/pages/MyTasks.jsx
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Container from "@mui/material/Container";
import Stack from "@mui/material/Stack";
import InfoIcon from "@mui/icons-material/Info";

function MyTasks() {
    const [tasks, setTasks] = useState([]);
    const navigate = useNavigate();

    // 1) Fetch all tasks on mount
    useEffect(() => {
        const fetchTasks = async () => {
            try {
                const res = await fetch("http://localhost:8080/api/tasks", {
                    headers: {
                        "Content-Type": "application/json",
                        // Include auth token if your endpoints are secured:
                        // "Authorization": localStorage.getItem("token")
                    },
                });
                if (!res.ok) throw new Error("Failed to load tasks");
                const data = await res.json();
                // Only show incomplete tasks
                setTasks(data.filter((t) => !t.completed));
            } catch (err) {
                console.error(err);
            }
        };
        fetchTasks();
    }, []);

    // 2) Mark a task as done (PUT /api/tasks/{id})
    const handleComplete = async (task) => {
        try {
            const updated = {
                ...task,
                completed: true
            };
            const res = await fetch(
                `http://localhost:8080/api/tasks/${task.taskId}`,
                {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json",
                        // "Authorization": localStorage.getItem("token")
                    },
                    body: JSON.stringify(updated),
                }
            );
            if (!res.ok) throw new Error("Could not mark done");
            // Remove from local list
            setTasks((prev) => prev.filter((t) => t.taskId !== task.taskId));
        } catch (err) {
            console.error(err);
        }
    };

    // 3) Navigate to detail page (you already have a TaskController#get by id)
    const handleInfo = (taskId) => {
        navigate(`/tasks/${taskId}`);
    };

    return (
        <Container sx={{ mt: 4, p: 3 }}>
            <Grid container spacing={3}>
                {tasks.map((task) => (
                    <Grid item xs={12} sm={6} md={4} key={task.taskId}>
                        <Card sx={{ p: 2, boxShadow: 3 }}>
                            <CardContent>
                                <Typography variant="h6">{task.title}</Typography>
                                <Typography sx={{ mb: 2 }}>{task.description}</Typography>
                                <Stack direction="row" gap={1}>
                                    <Button
                                        variant="contained"
                                        color="success"
                                        onClick={() => handleComplete(task)}
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
        </Container>
    );
}

export default MyTasks;
