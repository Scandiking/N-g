import React, { useState } from 'react'
import { Box, TextField, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio, Tooltip, Button, Autocomplete, Collapse, Chip, Switch, Dialog, DialogTitle, DialogContent} from '@mui/material';
import { LocalizationProvider, DatePicker, TimeClock } from '@mui/x-date-pickers';
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";

/* Picture Proof Toggle Component */
function PictureProofToggle({ requireProof, setRequireProof }) {
    return (
        <FormControlLabel
            control={
            <Switch
                checked={requireProof}
                onChange={(e) => setRequireProof(e.target.checked)}
                color="primary"
                />
            }
            label="Require picture proof of completed task"
            />
    );
}

const AddTask = ({ open, onClose }) => {
    const [taskTitle, setTaskTitle] = useState('');
    const [taskDescription, setTaskDescription] = useState('');
    const [notificationType, setNotificationType] = useState("1");
    const [customDates, setCustomDates] = useState([]);
    const [currentDate, setCurrentDate] = useState(null);
    const [currentTime, setCurrentTime] = useState(null);
    const [requireProof, setRequireProof] = useState(false);
    const [selectedRoom, setSelectedRoom] = useState('');
    const [selectedPerson, setSelectedPerson] = useState('');

    const handleAddCustomDate = () => {
        if (currentDate && currentTime) {
            const combinedDateTime = currentDate.hour(currentTime.hour())
            setCustomDates([...customDates, combinedDateTime]);
            setCurrentDate(null);
            setCurrentTime(null);
        }
    };

    const handleRemoveDate = (dateToRemove) => {
        setCustomDates(customDates.filter(date => date !== dateToRemove))
    };

const AssignTask = () => {
    if (!taskTitle || !selectedPerson) {
        alert('Please provide a task title and assign it to a person.');
        return;
    }

    const task = {
        title: taskTitle,
        description: taskDescription,
        notificationType,
        customDates,
        room: selectedRoom,
        assignedTo: selectedPerson,
        requireProof,
    };

    console.log('Task assigned:', task);

    // Eksempel: send task to backend API
    // fetch('/api/tasks', {
    //    method: 'POST',
    //    headers: { 'Content-Type': 'application/json' },
    //    body: JSON.stringify(task),
    // })
    //    .then(response => response.json())
    //    .then(data => console.log('Task saved:', data))
    //   .catch(error => console.error('Error saving task:', error));

    // Clear the form
    setTaskTitle('');
    setTaskDescription('');
    setNotificationType('1');
    setCustomDates([]);
    setSelectedRoom('');
    setSelectedPerson('');
    setRequireProof(false);

    onClose();
};

    return (

      <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>

          <DialogTitle>Add a New Task</DialogTitle>
          <DialogContent>

              <Box component="form" sx={{ display:'flex', flexDirection: 'column', gap: 2}}>
                  <Tooltip placement="top" title="Adds a title to your task" arrow>
                      <TextField
                          label="Task Title"
                          variant="outlined"
                          value={taskTitle}
                          onChange={(e) => setTaskTitle(e.target.value)}
                          fullWidth
                          required
                          />
                  </Tooltip>

                  <Tooltip placement="top" title="Lets you specify details about the task" arrow>
                      <TextField
                          label="Task Description"
                          variant="outlined"
                          value={taskDescription}
                          onChange={(e) => setTaskDescription(e.target.value)}
                          multiline
                          rows={4}
                          fullWidth
                      />
                  </Tooltip>

                  {/* NOTIFICATION FREQUENCY */}
                  <FormControl>
                      <FormLabel>Notification schedule</FormLabel>
                      <RadioGroup value={notificationType} onChange={(e) => setNotificationType(e.target.value)}>
                          <Tooltip title="Ramping up notification frequency exponentially" arrow>
                              <FormControlLabel value="1" control={<Radio />} label="Næg Classic" />
                          </Tooltip>
                          <Tooltip title="Once a day" arrow>
                              <FormControlLabel value="2" control={<Radio />} label="Easy" />
                          </Tooltip>
                          <Tooltip title="Every three hours" arrow>
                              <FormControlLabel value="3" control={<Radio />} label="Medium" />
                          </Tooltip>
                          <Tooltip title="Every 15 minutes" arrow>
                              <FormControlLabel value="4" control={<Radio />} label="Obnoxious" />
                          </Tooltip>
                          <Tooltip title="Customize notification schedule" arrow>
                              <FormControlLabel value="5" control={<Radio />} label="Custom..." />
                          </Tooltip>
                      </RadioGroup>
                  </FormControl>

                  {/* CUSTOM NOTIFICATION SECTION */}
                  <Collapse in={notificationType === "5"}>
                      <Box sx={{ mt: 2, p: 2, border: "1px solid #ddd", borderRadius: 2 }}>
                          <LocalizationProvider dateAdapter={AdapterDayjs}>
                              <DatePicker label="Select Date" value={currentDate} onChange={setCurrentDate} sx={{ width: "100%" }} />
                              <TimeClock value={currentTime} onChange={setCurrentTime} sx={{ width: "100%" }} ampm={false} />
                          </LocalizationProvider>
                          <Button sx={{ mt: 2 }} variant="outlined" onClick={handleAddCustomDate}>Add Notification</Button>
                          <Box sx={{ mt: 2, display: 'flex', flexWrap: 'wrap', gap: 1 }}>
                              {customDates.map((date, index) => (
                                  <Chip key={index} label={date.format('DD-MM-YYYY HH:mm')} onDelete={() => handleRemoveDate(date)} />
                              ))}
                          </Box>
                      </Box>
                  </Collapse>

                  {/* ROOM TASK ASSIGNED TO */}
                  <Tooltip title="Which room is the task meant for?" placement="top" arrow>
                      <Autocomplete
                          disablePortal
                          options={['Dormitory', 'Campus group']}
                          fullWidth
                          required
                          renderInput={(params) => <TextField {...params} label="Room" />}
                      />
                  </Tooltip>

                  {/* PERSON TASK IS ASSIGNED TO */}
                  <Tooltip title="Which person is the task assigned to?" placement="top" arrow>
                      <Autocomplete
                          disablePortal
                          options={['Jonas', 'Kenneth', 'Kristian', 'Lucas', 'Mia', 'Everyone in room']}
                          fullWidth
                          required
                          renderInput={(params) => <TextField {...params} label="Persons" />}
                      />
                  </Tooltip>

                  {/* PICTURE PROOF TOGGLE */}
                  <Box sx={{ mt: 2 }}>
                      <PictureProofToggle requireProof={requireProof} setRequireProof={setRequireProof} />
                  </Box>

                  <Button variant="secondary">Add picture</Button>

                  <Tooltip title="Lets user know about the task" placement="top" arrow>
                      <Button variant="contained" color="primary" onClick={onClose}>
                          Add Task
                      </Button>
                  </Tooltip>
              </Box>
          </DialogContent>

      </Dialog>

    );
};

export default AddTask;