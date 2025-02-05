import React, { useState } from 'react';
import { Box, TextField, Button, Typography, Paper, Autocomplete, Radio, FormControlLabel, FormControl, FormLabel, RadioGroup, Tooltip} from '@mui/material';

const AddTask = () => {
    const [taskTitle, setTaskTitle] = useState('');
    const [taskDescription, setTaskDescription] = useState('');

    const handleAddTask = () => {
        console.log('Task added', { taskTitle, taskDescription });
        // Reset the form after adding the task
        setTaskTitle('');
        setTaskDescription('');
    };

    return (
        <Paper
            elevation={3}
            sx={{
                padding: 4,
                margin: '20 auto',
                maxWidth: 600
            }}
        >
            <Typography variant="h4" component="h1" gutterBottom>
                Add a New Task
            </Typography>
            <Box component="form" sx={{ display: 'flex', flexDirection: 'column', gap: 2}}>
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
                    <FormControl>
                        <FormLabel id="demo-row-radio-buttons-group-label">Notification frequency</FormLabel>
                        <RadioGroup>
                            <Tooltip title="Ramping up notification frequency exponentially" placement="top" arrow>
                                <FormControlLabel value="1" control={<Radio />} label="Næg Classic" />
                            </Tooltip>

                            <Tooltip title="Once a day" placement="top" arrow>
                            <FormControlLabel value="2" control={<Radio />} label="Easy" />
                            </Tooltip>

                            <Tooltip title="Every three hours" placement="top" arrow>
                            <FormControlLabel value="3" control={<Radio />} label="Medium" />
                            </Tooltip>

                            <Tooltip title="Every 15 minutes" placement="top" arrow>
                            <FormControlLabel value="4" control={<Radio />} label="Obnoxious" />
                            </Tooltip>

                            <Tooltip title="Customize notification schedule" placement="top" arrow>
                                <FormControlLabel value="5" control={<Radio />} label="Custom..."/>
                            </Tooltip>
                        </RadioGroup>
                    </FormControl>

                <Tooltip title="Which person is the task assigned to?" placement="top" arrow>
                <Autocomplete
                    disablePortal
                    options={['Jonas','Kenneth','Kristian','Lucas','Mia']}
                    fullWidth
                    required
                    renderInput={(params) => <TextField {...params} label="Persons" />}
                    />
                </Tooltip>

                <Button variant="secondary">Legg til bilde</Button>

                <Tooltip title="Lets user know about the task" placement="top" arrow>
                <Button variant="contained" color="primary" onClick={handleAddTask}>
                    Add Task
                </Button>
                </Tooltip>
            </Box>
        </Paper>

    );
};

export default AddTask;