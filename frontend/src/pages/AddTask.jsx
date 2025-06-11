// src/pages/AddTask.jsx
import React, { useState, useEffect } from 'react';
import {
    Box,
    TextField,
    FormControl,
    FormLabel,
    RadioGroup,
    FormControlLabel,
    Radio,
    Tooltip,
    Button,
    Autocomplete,
    Collapse,
    Chip,
    Dialog,
    DialogTitle,
    DialogContent,
    Snackbar,
    Alert,
    CircularProgress
} from '@mui/material';
import { LocalizationProvider, DatePicker, TimeClock } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs from 'dayjs';

const AddTask = ({ open, onClose }) => {
    const [taskTitle, setTaskTitle] = useState('');
    const [taskDescription, setTaskDescription] = useState('');
    const [notificationType, setNotificationType] = useState("1");
    const [customDates, setCustomDates] = useState([]);
    const [currentDate, setCurrentDate] = useState(null);
    const [currentTime, setCurrentTime] = useState(null);
    const [selectedRoom, setSelectedRoom] = useState('');
    const [selectedPerson, setSelectedPerson] = useState(null);
    const [loading, setLoading] = useState(false);

    const [rooms, setRooms] = useState([]);
    const [people, setPeople] = useState([]);
    const [snackbar, setSnackbar] = useState({
        open: false,
        message: '',
        severity: 'success'
    });

    useEffect(() => {
        if (open) {
            fetchRooms();
            fetchPeople();
        }
    }, [open]);

    const fetchRooms = async () => {
        try {
            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) token = 'Bearer ' + token;
            const res = await fetch('http://localhost:8080/api/rooms', {
                headers: { Authorization: token || '' }
            });
            if (res.ok) setRooms(await res.json());
        } catch (err) {
            console.error('Error fetching rooms:', err);
        }
    };

    const fetchPeople = async () => {
        try {
            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) token = 'Bearer ' + token;
            const res = await fetch('http://localhost:8080/api/persons', {
                headers: { Authorization: token || '' }
            });
            if (res.ok) setPeople(await res.json());
        } catch (err) {
            console.error('Error fetching people:', err);
        }
    };

    const addCustom = () => {
        if (currentDate && currentTime) {
            const dt = currentDate
                .set('hour', currentTime.hour())
                .set('minute', currentTime.minute());
            setCustomDates([...customDates, dt]);
            setCurrentDate(null);
            setCurrentTime(null);
        }
    };

    const removeDate = d => setCustomDates(customDates.filter(x => x !== d));

    const assignTask = async () => {
        if (!taskTitle.trim()) {
            setSnackbar({ open: true, message: 'Please provide a task title', severity: 'error' });
            return;
        }
        if (!selectedPerson) {
            setSnackbar({ open: true, message: 'Please assign the task to a person', severity: 'error' });
            return;
        }

        setLoading(true);
        try {
            const taskData = {
                title: taskTitle.trim(),
                description: taskDescription.trim(),
                dueDate: customDates[0]?.toISOString() || null,
                notiFreqId: parseInt(notificationType),
                completed: false
            };
            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) token = 'Bearer ' + token;

            const res = await fetch('http://localhost:8080/api/tasks', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: token || ''
                },
                body: JSON.stringify(taskData)
            });
            if (!res.ok) {
                const text = await res.text();
                throw new Error(`${res.status} - ${text}`);
            }
            const created = await res.json();

            if (selectedPerson !== 'Everyone in room') {
                const phoneNo = typeof selectedPerson === 'object'
                    ? selectedPerson.phoneNo
                    : selectedPerson;
                await assignTaskToPerson(created.taskId, phoneNo);
            } else {
                await assignTaskToRoom(created.taskId, selectedRoom);
            }

            resetForm();
            setSnackbar({ open: true, message: 'Task created and assigned successfully!', severity: 'success' });
            onClose();
        } catch (err) {
            console.error('Error creating task:', err);
            setSnackbar({ open: true, message: `Failed: ${err.message}`, severity: 'error' });
        } finally {
            setLoading(false);
        }
    };

    const assignTaskToPerson = async (taskId, phoneNo) => {
        try {
            const dto = { taskId, phoneNo };
            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) token = 'Bearer ' + token;
            await fetch('http://localhost:8080/api/taskforpersons', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: token || ''
                },
                body: JSON.stringify(dto)
            });
        } catch (err) {
            console.error('Error assigning to person:', err);
        }
    };

    const assignTaskToRoom = async (taskId, roomId) => {
        try {
            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) token = 'Bearer ' + token;
            const res = await fetch('http://localhost:8080/api/roomforpersons', {
                headers: { Authorization: token || '' }
            });
            if (res.ok) {
                const all = await res.json();
                const members = all.filter(rp => rp.roomId === roomId);
                for (const m of members) {
                    await assignTaskToPerson(taskId, m.phoneNo);
                }
            }
        } catch (err) {
            console.error('Error assigning to room:', err);
        }
    };

    const resetForm = () => {
        setTaskTitle('');
        setTaskDescription('');
        setNotificationType('1');
        setCustomDates([]);
        setCurrentDate(null);
        setCurrentTime(null);
        setSelectedRoom('');
        setSelectedPerson(null);
    };

    const handleCloseSnackbar = () => {
        setSnackbar(prev => ({ ...prev, open: false }));
    };

    const getPeopleOptions = () => {
        if (!selectedRoom) return people;
        // if room selected, filter as needed, then add the special option
        return [...people.filter(() => true), 'Everyone in room'];
    };

    return (
        <>
            <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
                <DialogTitle>Add a new task</DialogTitle>
                <DialogContent>
                    <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 1 }}>
                        <Tooltip title="Adds a title to your task">
                            <TextField
                                label="Task title"
                                value={taskTitle}
                                onChange={e => setTaskTitle(e.target.value)}
                                required fullWidth disabled={loading}
                            />
                        </Tooltip>
                        <Tooltip title="Details">
                            <TextField
                                label="Task description"
                                multiline rows={3}
                                value={taskDescription}
                                onChange={e => setTaskDescription(e.target.value)}
                                fullWidth disabled={loading}
                            />
                        </Tooltip>
                        <FormControl disabled={loading}>
                            <FormLabel>Notification schedule</FormLabel>
                            <RadioGroup value={notificationType} onChange={e => setNotificationType(e.target.value)}>
                                <FormControlLabel value="1" control={<Radio />} label="Næg classic" />
                                <FormControlLabel value="2" control={<Radio />} label="Easy" />
                                <FormControlLabel value="3" control={<Radio />} label="Medium" />
                                <FormControlLabel value="4" control={<Radio />} label="Obnoxious" />
                                <FormControlLabel value="5" control={<Radio />} label="Custom" />
                            </RadioGroup>
                        </FormControl>
                        <Collapse in={notificationType === '5'}>
                            <Box sx={{ mt: 2, p: 2, border: '1px solid #ddd', borderRadius: 2 }}>
                                <LocalizationProvider dateAdapter={AdapterDayjs}>
                                    <DatePicker
                                        label="Select date"
                                        value={currentDate}
                                        onChange={setCurrentDate}
                                        disabled={loading}
                                        sx={{ mb: 1, width: '100%' }}
                                    />
                                    <TimeClock
                                        value={currentTime}
                                        onChange={setCurrentTime}
                                        ampm={false}
                                        disabled={loading}
                                        sx={{ mb: 1 }}
                                    />
                                </LocalizationProvider>
                                <Button variant="outlined" onClick={addCustom} disabled={loading || !currentDate || !currentTime}>
                                    Add notification
                                </Button>
                                <Box sx={{ mt: 1, display: 'flex', flexWrap: 'wrap', gap: 1 }}>
                                    {customDates.map((d, i) => (
                                        <Chip
                                            key={i}
                                            label={d.format('DD-MM-YYYY HH:mm')}
                                            onDelete={() => removeDate(d)}
                                            disabled={loading}
                                        />
                                    ))}
                                </Box>
                            </Box>
                        </Collapse>
                        <Autocomplete
                            options={rooms}
                            getOptionLabel={opt => opt.roomName || opt}
                            value={selectedRoom}
                            onChange={(e, v) => {
                                setSelectedRoom(v?.roomId || v || '');
                                setSelectedPerson(null);
                            }}
                            disabled={loading}
                            renderInput={params => <TextField {...params} label="Room" />}
                        />
                        <Autocomplete
                            options={getPeopleOptions()}
                            getOptionLabel={opt => {
                                if (opt === 'Everyone in room') return opt;
                                const fn = opt.firstName || '';
                                const ln = opt.lastName || '';
                                return `${fn} ${ln}`.trim();
                            }}
                            value={selectedPerson}
                            onChange={(e, v) => setSelectedPerson(v)}
                            disabled={loading}
                            renderInput={params => <TextField {...params} label="Assign to" required />}
                        />
                        <Button
                            variant="contained"
                            onClick={assignTask}
                            disabled={loading || !taskTitle.trim() || !selectedPerson}
                            sx={{ mt: 2 }}
                        >
                            {loading
                                ? <>
                                    <CircularProgress size={20} sx={{ mr: 1 }} />
                                    Creating task...
                                </>
                                : 'Add task'}
                        </Button>
                    </Box>
                </DialogContent>
            </Dialog>
            <Snackbar
                open={snackbar.open}
                autoHideDuration={6000}
                onClose={handleCloseSnackbar}
                anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
            >
                <Alert onClose={handleCloseSnackbar} severity={snackbar.severity} sx={{ width: '100%' }}>
                    {snackbar.message}
                </Alert>
            </Snackbar>
        </>
    );
};

export default AddTask;
