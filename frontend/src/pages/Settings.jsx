import React, {useContext} from 'react';
import {
    RadioGroup,
    FormControlLabel,
    Radio,
    Switch,
    FormGroup,
    Stack,
    Card,
    CardContent,
    Container,
} from '@mui/material';
import AutoModeIcon from '@mui/icons-material/AutoMode';
import LightModeIcon from '@mui/icons-material/LightMode';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import KayakingIcon from '@mui/icons-material/Kayaking';
import AcUnitIcon from '@mui/icons-material/AcUnit';
import {ThemeContext} from './ThemeContext'; // Correct import


const Settings = () => {
    const {mode, setMode} = useContext(ThemeContext); // Access mode and setMode from context

    return (
        <div>
            <Container sx={{p: 3}}>
                <Stack spacing={2}>


                    <Card
                        sx={{
                            display: 'flex',
                            mb: 2,
                            p: 2,
                            borderRadius: 2,
                            boxShadow: 3,
                        }}
                    >
                        <CardContent>
                            <h2>Theme Settings</h2>
                            <RadioGroup
                                value={mode}
                                onChange={(e) => setMode(e.target.value)}
                                column
                            >
                                <FormControlLabel
                                    value="auto"
                                    control={<Radio/>}
                                    label={
                                        <>
                                            <AutoModeIcon sx={{mx: 1}}/>
                                            Auto
                                        </>
                                    }
                                />
                                <FormControlLabel
                                    value="light"
                                    control={<Radio/>}
                                    label={
                                        <>
                                            <LightModeIcon sx={{mx: 1}}/>
                                            Light
                                        </>
                                    }
                                />
                                <FormControlLabel
                                    value="dark"
                                    control={<Radio/>}
                                    label={
                                        <>
                                            <DarkModeIcon sx={{mx: 1}}/>
                                            Dark
                                        </>
                                    }
                                />
                                <FormControlLabel
                                    value="pacificPunch"
                                    control={<Radio/>}
                                    label={
                                        <>
                                            <KayakingIcon sx={{mx: 1}}/>
                                            Pacific Punch
                                        </>
                                    }
                                />

                                <FormControlLabel
                                    value="arctic"
                                    control={<Radio/>}
                                    label={
                                        <>
                                            <AcUnitIcon sx={{mx: 1}}/>
                                            Artic Athmosphere
                                        </>
                                    }
                                />
                            </RadioGroup>

                        </CardContent>
                    </Card>

                    <Card
                        sx={{
                            display: 'flex',
                            mb: 2,
                            p: 2,
                            borderRadius: 2,
                            boxShadow: 3,
                        }}
                    >
                        <CardContent>
                            <h2>Task settings</h2>

                            <FormGroup sx={{mb: 2}}>
                                <FormControlLabel control={<Switch/>}
                                                  label="Require task completion approval from task assigner"/>
                            </FormGroup>


                        </CardContent>
                    </Card>

                    <Card
                        sx={{
                            display: 'flex',
                            mb: 2,
                            p: 2,
                            borderRadius: 2,
                            boxShadow: 3,
                        }}
                    >
                        <CardContent>
                            <h2>People settings</h2>
                            <FormGroup sx={{mb: 2}}>
                                <FormControlLabel control={<Switch/>} label="Show full name instead of first name"/>

                            </FormGroup>
                        </CardContent>
                    </Card>


                    <Card
                        sx={{
                            display: 'flex',
                            mb: 2,
                            p: 2,
                            borderRadius: 2,
                            boxShadow: 3,
                        }}
                    >
                        <CardContent>
                            <h2>Room settings</h2>
                            <FormGroup sx={{mb: 2}}>
                                <FormControlLabel control={<Switch/>} label="Enable room temperature"/>
                            </FormGroup>
                        </CardContent>
                    </Card>


                </Stack>
            </Container>
        </div>
    );
};

export default Settings;
