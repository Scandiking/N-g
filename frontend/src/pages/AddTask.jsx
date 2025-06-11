import React, {useState} from 'react'
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
    Switch,
    Dialog,
    DialogTitle,
    DialogContent
} from '@mui/material';
import {LocalizationProvider, DatePicker, TimeClock} from '@mui/x-date-pickers';
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from 'dayjs';


const AddTask = ({open, onClose}) => {
    const [taskTitle, setTaskTitle] = useState('');
    const [taskDescription, setTaskDescription] = useState('');
    const [notificationType, setNotificationType] = useState("1");
    const [customDates, setCustomDates] = useState([]);
    const [currentDate, setCurrentDate] = useState(null);
    const [currentTime, setCurrentTime] = useState(null);
    const [selectedRoom, setSelectedRoom] = useState('');
    const [selectedPerson, setSelectedPerson] = useState('');

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

    // Create and assign task in one POST request (industry standard
    const assignTask = async () => {
        if (!taskTitle || !selectedPerson) {
            alert("Please provide a task title and assign it to a person.");
            return;
        }

        const body = {
            title: taskTitle,
            description: taskDescription,
            notiFreqId: notificationType,
            dueDate: customDates[0] ? customDates[0].toISOString() : null
        };

        try {
            let token = localStorage.getItem('token');
            if (token && !token.startsWith('Bearer ')) token = 'Bearer ' + token;

            const res = await fetch('http:localhost:8080/api/tasks', {
                method: 'POST',
                headers: {'Content-Type': 'application/json', 'Authorization': token},
                body: JSON.stringify(body),
            });
            if (!res.ok) throw new Error(`Server svarte ${res.status}`);

            // rydd skjema
            setTaskTitle('');
            setTaskDescription('');
            setNotificationType('1');
            setCustomDates([]);
            setSelectedRoom('');
            setSelectedPerson('');
            onClose();
        } catch (e) {
            alert(e.message);
        }
    };


    return (
        <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
            <DialogTitle>Add a new task</DialogTitle>
            <DialogContent>
                <Box sx={{display: 'flex', flexDirection: 'column', gap: 2}}>
                    <Tooltip title="Adds a title to your task"><TextField
                        label="Task title" value={taskTitle} onChange={e => setTaskTitle(e.target.value)} required
                        fullWidth/>
                    </Tooltip>

                    <Tooltip title="Details"><TextField
                        label="Task description" multiline rows={3}
                        value={taskDescription} onChange={e => setTaskDescription(e.target.value)} fullWidth/>
                    </Tooltip>

                    <FormControl>
                        <FormLabel>Notification schedule</FormLabel>
                        <RadioGroup value={notificationType} onChange={e => setNotificationType(e.target.value)}>
                            <FormControlLabel value="1" control={<Radio/>} label="Næg classic"/>
                            <FormControlLabel value="2" control={<Radio/>} label="Easy"/>
                            <FormControlLabel value="3" control={<Radio/>} label="Medium"/>
                            <FormControlLabel value="4" control={<Radio/>} label="Obnoxious"/>
                            <FormControlLabel value="5" control={<Radio/>} label="Custom"/>
                        </RadioGroup>
                    </FormControl>

                    <Collapse in={notificationType === '5'}>
                        <Box sx={{mt: 2, p: 2, border: '1px solid #ddd', borderRadius: 2}}>
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                                <DatePicker label="Select date" value={currentDate} onChange={setCurrentDate}
                                            sx={{mb: 1, width: '100%'}}/>
                                <TimeClock value={currentTime} onChange={setCurrentTime} ampm={false} sx={{mb: 1}}/>
                            </LocalizationProvider>
                            <Button variant="outlined" onClick={addCustom}>Add notification</Button>
                            <Box sx={{mt: 1, display: 'flex', flexWrap: 'wrap', gap: 1}}>
                                {customDates.map((d, i) =>
                                    <Chip key={i} label={d.format('DD-MM-YYYY HH:mm')} onDelete={() => removeDate(d)}/>
                                )}
                            </Box>
                        </Box>
                    </Collapse>

                    <Autocomplete options={['Dormitory', 'Campus group']}
                                  value={selectedRoom} onChange={(e, v) => setSelectedRoom(v)}
                                  renderInput={p => <TextField {...p} label="Room" required/>}/>

                    <Autocomplete options={['Jonas', 'Kenneth', 'Kristian', 'Lucas', 'Mia', 'Everyone in room']}
                                  value={selectedPerson} onChange={(e, v) => setSelectedPerson(v)}
                                  renderInput={p => <TextField {...p} label="Person" required/>}/>


                    <Button variant="contained" onClick={assignTask}>Add task</Button>
                </Box>
            </DialogContent>
        </Dialog>

    );
};

export default AddTask;