import React, { useState, useEffect, useMemo } from 'react';
import {
    Box,
    Typography,
    Card,
    CardContent,
    CardActions,
    Button,
    Grid,
    Chip,
    CircularProgress,
    Alert,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Autocomplete,
    TextField,
    IconButton,
    Tooltip,
    Snackbar
} from '@mui/material';
import {
    Assignment as AssignmentIcon,
    Person as PersonIcon,
    CheckCircle as CheckIcon,
    AccessTime as ClockIcon,
    Close as CloseIcon
} from '@mui/icons-material';

// Sample tasks data
const sampleTasks = [
    {
        taskId: 9001,  // ✅ ENDRET: Høye tall som ikke kolliderer med backend
        taskName: "Clean kitchen",
        taskDescription: "Deep clean the kitchen including appliances",
        status: "pending",
        priority: "high",
        dueDate: "2025-06-15",
        assignedTo: null
    },
    {
        taskId: 9002,  // ✅ ENDRET
        taskName: "Fix washing machine",
        taskDescription: "The washing machine is making strange noises",
        status: "in_progress",
        priority: "medium",
        dueDate: "2025-06-20",
        assignedTo: "Ingrid Hansen"
    },
    {
        taskId: 9003,  // ✅ ENDRET
        taskName: "Organize study group",
        taskDescription: "Plan weekly study sessions for the group",
        status: "completed",
        priority: "low",
        dueDate: "2025-06-10",
        assignedTo: "Magnus Lund"
    },
    {
        taskId: 9004,  // ✅ ENDRET
        taskName: "Buy groceries",
        taskDescription: "Weekly grocery shopping for shared items",
        status: "pending",
        priority: "medium",
        dueDate: "2025-06-14",
        assignedTo: null
    }
];

// Sample people for assignment (matching MyPeople data)
const samplePeople = [
    { phoneNo: "sample1", firstName: "Ingrid", lastName: "Hansen" },
    { phoneNo: "sample2", firstName: "Magnus", lastName: "Lund" },
    { phoneNo: "sample3", firstName: "Sofie", lastName: "Berg" },
    { phoneNo: "sample4", firstName: "Simen", lastName: "Nygaard" },
    { phoneNo: "sample5", firstName: "Emma", lastName: "Aas" }
];

// ✅ NY: La til refreshTrigger prop
const MyTasks = ({ refreshTrigger }) => {
    const [backendTasks, setBackendTasks] = useState([]);
    const [backendPeople, setBackendPeople] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Assignment dialog state
    const [assignDialogOpen, setAssignDialogOpen] = useState(false);
    const [selectedTask, setSelectedTask] = useState(null);
    const [selectedPerson, setSelectedPerson] = useState(null);
    const [assigning, setAssigning] = useState(false);

    // Snackbar for feedback
    const [snackbar, setSnackbar] = useState({
        open: false,
        message: '',
        severity: 'success'
    });

    // Combine sample and backend data, avoiding duplicates - MEMOIZED
    const allTasks = useMemo(() => {
        // Get all taskIds from sample tasks
        const sampleTaskIds = sampleTasks.map(task => task.taskId);

        // Filter backend tasks to exclude ones that match sample taskIds
        const uniqueBackendTasks = backendTasks.filter(task =>
            !sampleTaskIds.includes(task.taskId)
        );

        console.log('Memoized filtering - sampleTaskIds:', sampleTaskIds);
        console.log('Memoized filtering - uniqueBackendTasks:', uniqueBackendTasks.length);

        return [...sampleTasks, ...uniqueBackendTasks];
    }, [backendTasks]); // Only recalculate when backendTasks changes
    const allPeople = [...samplePeople, ...backendPeople];

    // ✅ NY: Oppdatert useEffect som lytter på refreshTrigger
    useEffect(() => {
        console.log('MyTasks useEffect: Fetching data... (refreshTrigger:', refreshTrigger, ')');
        fetchTasks();
        fetchPeople();
    }, [refreshTrigger]); // ✅ La til refreshTrigger i dependency array

    const fetchTasks = async () => {
        try {
            const token = localStorage.getItem('token');

            if (!token) {
                console.log('No token found, using sample data only');
                setLoading(false);
                return;
            }

            let authHeader = token.startsWith('Bearer ') ? token : `Bearer ${token}`;

            const response = await fetch('http://localhost:8080/api/tasks', {
                headers: {
                    'Authorization': authHeader,
                    'Content-Type': 'application/json'
                }
            });

            console.log('Tasks API response status:', response.status);

            if (response.ok) {
                const tasksData = await response.json();
                console.log('Backend tasks data received:', tasksData);
                setBackendTasks(tasksData);
            } else {
                console.warn('Failed to fetch tasks:', response.status);
            }
        } catch (error) {
            console.error('Error fetching tasks:', error);
            setError('Failed to load tasks from server');
        } finally {
            setLoading(false);
        }
    };

    const fetchPeople = async () => {
        try {
            const token = localStorage.getItem('token');

            if (!token) return;

            let authHeader = token.startsWith('Bearer ') ? token : `Bearer ${token}`;

            const response = await fetch('http://localhost:8080/api/persons', {
                headers: {
                    'Authorization': authHeader,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const peopleData = await response.json();
                console.log('Backend people data for assignment:', peopleData);
                setBackendPeople(peopleData);
            }
        } catch (error) {
            console.error('Error fetching people:', error);
        }
    };

    const handleAssignClick = (task) => {
        console.log('Opening assignment dialog for task:', task);
        setSelectedTask(task);
        setSelectedPerson(null);
        setAssignDialogOpen(true);
    };

    const handleAssignTask = async () => {
        if (!selectedTask || !selectedPerson) {
            setSnackbar({
                open: true,
                message: 'Please select a person to assign the task to',
                severity: 'error'
            });
            return;
        }

        setAssigning(true);

        try {
            // Create task assignment
            const assignmentData = {
                taskId: selectedTask.taskId,
                phoneNo: selectedPerson.phoneNo,
                assignedDate: new Date().toISOString().split('T')[0] // Today's date
            };

            const token = localStorage.getItem('token');
            let authHeader = token?.startsWith('Bearer ') ? token : `Bearer ${token || ''}`;

            const response = await fetch('http://localhost:8080/api/taskforpersons', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': authHeader
                },
                body: JSON.stringify(assignmentData)
            });

            if (response.ok) {
                const result = await response.json();
                console.log('Task assigned successfully:', result);

                // Update local task data to reflect assignment
                const personName = `${selectedPerson.firstName} ${selectedPerson.lastName}`;

                // Update backend tasks if this was a backend task
                setBackendTasks(prev => prev.map(task =>
                    task.taskId === selectedTask.taskId
                        ? { ...task, assignedTo: personName }
                        : task
                ));

                setSnackbar({
                    open: true,
                    message: `Task "${selectedTask.taskName}" assigned to ${personName}!`,
                    severity: 'success'
                });

                // Close dialog
                setAssignDialogOpen(false);
                setSelectedTask(null);
                setSelectedPerson(null);

            } else {
                const errorText = await response.text();
                throw new Error(`Server responded with ${response.status}: ${errorText}`);
            }

        } catch (error) {
            console.error('Error assigning task:', error);
            setSnackbar({
                open: true,
                message: `Failed to assign task: ${error.message}`,
                severity: 'error'
            });
        } finally {
            setAssigning(false);
        }
    };

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

    const formatDate = (dateString) => {
        if (!dateString) return 'No due date';
        return new Date(dateString).toLocaleDateString();
    };

    const isUserCreated = (task) => {
        return !sampleTasks.some(st => st.taskId === task.taskId);
    };

    // Debug logging - MOVED BEFORE RETURN
    console.log('MyTasks render - sampleTasks:', sampleTasks.length);
    console.log('MyTasks render - backendTasks:', backendTasks.length, backendTasks);
    console.log('MyTasks render - allTasks:', allTasks.length, allTasks);
    console.log('MyTasks render - refreshTrigger:', refreshTrigger); // ✅ NY debug log

    return (
        <Box sx={{ padding: 3 }}>
            <Typography variant="h4" component="h1" gutterBottom>
                My Tasks
            </Typography>

            {loading && (
                <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                    <CircularProgress />
                    <Typography sx={{ ml: 2 }}>Loading tasks...</Typography>
                </Box>
            )}

            {error && (
                <Alert severity="warning" sx={{ mb: 2 }}>
                    {error} - Showing sample data only
                </Alert>
            )}

            <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                Showing {allTasks.length} tasks ({sampleTasks.length} sample + {allTasks.length - sampleTasks.length} unique backend tasks)
                {/* ✅ NY: Vis refresh trigger status */}
                {refreshTrigger > 0 && (
                    <span style={{ marginLeft: 8, color: '#4caf50' }}>
                        (Refreshed {refreshTrigger} times)
                    </span>
                )}
            </Typography>

            <Grid container spacing={2}>
                {allTasks.map((task, index) => (
                    <Grid item xs={12} sm={6} md={4} key={`${isUserCreated(task) ? 'user' : 'sample'}-${task.taskId}-${index}`}>
                        <Card
                            sx={{
                                height: '100%',
                                display: 'flex',
                                flexDirection: 'column',
                                border: isUserCreated(task) ? '2px solid #4caf50' : '1px solid #e0e0e0'
                            }}
                        >
                            <CardContent sx={{ flexGrow: 1 }}>
                                {/* User-created indicator */}
                                {isUserCreated(task) && (
                                    <Chip
                                        label="Your Task"
                                        size="small"
                                        color="success"
                                        sx={{
                                            position: 'absolute',
                                            top: 8,
                                            right: 8,
                                            fontSize: '0.7em'
                                        }}
                                    />
                                )}

                                <Typography variant="h6" component="h2" gutterBottom>
                                    {task.taskName || task.title}
                                </Typography>

                                {(task.taskDescription || task.description) && (
                                    <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                                        {task.taskDescription || task.description}
                                    </Typography>
                                )}

                                {/* Status and Priority chips */}
                                <Box sx={{ display: 'flex', gap: 1, mb: 2, flexWrap: 'wrap' }}>
                                    <Chip
                                        label={task.status || 'pending'}
                                        color={getStatusColor(task.status)}
                                        size="small"
                                        icon={task.status === 'completed' ? <CheckIcon /> : <ClockIcon />}
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

                                {/* Due date */}
                                {task.dueDate && (
                                    <Typography variant="caption" color="text.secondary" display="block">
                                        Due: {formatDate(task.dueDate)}
                                    </Typography>
                                )}

                                {/* Assignment status */}
                                {task.assignedTo ? (
                                    <Box sx={{ mt: 1, display: 'flex', alignItems: 'center' }}>
                                        <PersonIcon sx={{ fontSize: 16, mr: 0.5, color: 'success.main' }} />
                                        <Typography variant="caption" color="success.main">
                                            Assigned to: {task.assignedTo}
                                        </Typography>
                                    </Box>
                                ) : (
                                    <Typography variant="caption" color="text.secondary" sx={{ mt: 1, display: 'block' }}>
                                        Not assigned
                                    </Typography>
                                )}
                            </CardContent>

                            <CardActions>
                                <Button
                                    size="small"
                                    startIcon={<AssignmentIcon />}
                                    onClick={() => handleAssignClick(task)}
                                    disabled={task.status === 'completed'}
                                >
                                    {task.assignedTo ? 'Reassign' : 'Assign to'}
                                </Button>
                            </CardActions>
                        </Card>
                    </Grid>
                ))}
            </Grid>

            {allTasks.length === 0 && !loading && (
                <Box sx={{ textAlign: 'center', mt: 4 }}>
                    <AssignmentIcon sx={{ fontSize: 60, color: 'text.secondary', mb: 2 }} />
                    <Typography variant="h6" color="text.secondary">
                        No tasks found
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        Create tasks using the + button
                    </Typography>
                </Box>
            )}

            {/* Assignment Dialog */}
            <Dialog
                open={assignDialogOpen}
                onClose={() => setAssignDialogOpen(false)}
                maxWidth="sm"
                fullWidth
            >
                <DialogTitle>
                    <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                        Assign Task to Person
                        <IconButton onClick={() => setAssignDialogOpen(false)}>
                            <CloseIcon />
                        </IconButton>
                    </Box>
                </DialogTitle>

                <DialogContent>
                    {selectedTask && (
                        <Box sx={{ mb: 3 }}>
                            <Typography variant="h6" gutterBottom>
                                {selectedTask.taskName || selectedTask.title}
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                {selectedTask.taskDescription || selectedTask.description}
                            </Typography>
                        </Box>
                    )}

                    <Autocomplete
                        options={allPeople}
                        getOptionLabel={(option) => `${option.firstName} ${option.lastName}`}
                        value={selectedPerson}
                        onChange={(event, newValue) => setSelectedPerson(newValue)}
                        renderInput={(params) => (
                            <TextField
                                {...params}
                                label="Select person"
                                placeholder="Choose who to assign this task to"
                                helperText={`${allPeople.length} people available (${samplePeople.length} sample + ${backendPeople.length} your people)`}
                                fullWidth
                            />
                        )}
                        renderOption={(props, option) => (
                            <li {...props}>
                                <Box>
                                    <Typography>
                                        {option.firstName} {option.lastName}
                                    </Typography>
                                    <Typography variant="caption" color="text.secondary">
                                        {option.phoneNo}
                                        {!option.phoneNo.startsWith('sample') && (
                                            <span style={{ marginLeft: 8, color: '#4caf50' }}>
                                                (Your Person)
                                            </span>
                                        )}
                                    </Typography>
                                </Box>
                            </li>
                        )}
                    />
                </DialogContent>

                <DialogActions>
                    <Button onClick={() => setAssignDialogOpen(false)}>
                        Cancel
                    </Button>
                    <Button
                        onClick={handleAssignTask}
                        variant="contained"
                        disabled={!selectedPerson || assigning}
                        startIcon={assigning ? <CircularProgress size={16} /> : <AssignmentIcon />}
                    >
                        {assigning ? 'Assigning...' : 'Assign Task'}
                    </Button>
                </DialogActions>
            </Dialog>

            {/* Success/Error feedback */}
            <Snackbar
                open={snackbar.open}
                autoHideDuration={6000}
                onClose={() => setSnackbar({ ...snackbar, open: false })}
                anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
            >
                <Alert
                    onClose={() => setSnackbar({ ...snackbar, open: false })}
                    severity={snackbar.severity}
                    sx={{ width: '100%' }}
                >
                    {snackbar.message}
                </Alert>
            </Snackbar>
        </Box>
    );
};

export default MyTasks;