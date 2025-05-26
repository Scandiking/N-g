import React, { useContext } from 'react';
import {
    RadioGroup,
    FormControlLabel,
    Radio,
    Grid2,
    Box,
    Switch,
    FormGroup,
    Stack,
    Card,
    CardContent,
    Button,
    ButtonGroup,
    Container,
    Slider,
} from '@mui/material';
import AutoModeIcon from '@mui/icons-material/AutoMode';
import LightModeIcon from '@mui/icons-material/LightMode';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import KayakingIcon from '@mui/icons-material/Kayaking';
import AcUnitIcon from '@mui/icons-material/AcUnit';
import { ThemeContext } from './ThemeContext'; // Correct import



const Settings = () => {
    const { mode, setMode } = useContext(ThemeContext); // Access mode and setMode from context

    return (
        <div>
            <Container sx={{ p: 3 }}>
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
                <h2>Task settings</h2>

                            <FormGroup sx={{ mb: 2 }}>
                                <FormControlLabel control={<Switch/>} label="Make image proofs of completed tasks obligatory"/>
                                <FormControlLabel control={<Switch/>} label="Prompt task assigner for validation of completed task"/>
                                <FormControlLabel control={<Switch/>} label="Toggle punishment by each task"/>
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
                        <FormGroup sx={{ mb: 2 }}>
                            <FormControlLabel control={<Switch/>} label="Show full name"/>
                            <FormControlLabel control={<Switch/>} label="Require photo of face"/>
                            <FormControlLabel control={<Switch/>} label="Let room admin assign privileges to individual users"/>
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
                            <FormGroup sx={{ mb: 2 }}>
                                <FormControlLabel control={<Switch/>} label="Enable support for several moderators"/>
                                <FormControlLabel control={<Switch/>} label="Enable assigning whole room to task"/>
                                <FormControlLabel control={<Switch/>} label="Enable room temperature"/>
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
                <h2>Theme Settings</h2>
                <RadioGroup
                    value={mode}
                    onChange={(e) => setMode(e.target.value)}
                    row
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

                            <FormGroup sx={{ mb: 2 }}>
                                <FormControlLabel control={<Switch defaultChecked/>} label="Enable animations"/>
                                <FormControlLabel control={<Switch/>} label="Enable blablabla"/>
                                <FormControlLabel control={<Switch/>} label="Enable blebleble"/>
                            </FormGroup>

                            <Grid2>
                                <FormGroup sx={{ mb: 2 }}>
                                    <FormControlLabel control={<Switch defaultChecked/>} label="Påslått eksempelbryter"/>
                                    <FormControlLabel control={<Switch/>} label="Avslått eksempelbryter"/>
                                </FormGroup>
                                <Button variant="contained">Eksempel</Button>
                                <Button variant="text">Eksempel</Button>
                                <Button variant="outlined">Eksempel</Button>
                                <ButtonGroup variant="contained">
                                    <Button>Eksempel 1</Button>
                                    <Button>Eksempel 2</Button>
                                    <Button>Eksempel 3</Button>
                                </ButtonGroup>
                                <Slider/>

                            </Grid2>

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
                <h2>Payment settings</h2>
                <FormGroup>
                    <FormControlLabel control={<Switch/>} label="Enable payment punishments"/>
                    <FormControlLabel control={<Switch/>} label="Enable payment punishments limits"/>
                    <FormControlLabel control={<Switch/>} label="Make payment punishment into donations to Free Software Foundation"/>
                </FormGroup>

                            <Button variant="contained">Button</Button>
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
                            <h2>Privacy settings</h2>
                            <FormGroup>
                                <FormControlLabel control={<Switch/>} label="Enable P2P data handling"/>
                                <FormControlLabel control={<Switch/>} label="Enable local network traffic only"/>
                                <FormControlLabel control={<Switch/>} label="Sandbox app from global system (requires root privileges"/>
                                <FormControlLabel control={<Switch/>} label="Enable AES-256 bit + cryptographic riddle unlock"/>
                                <FormControlLabel control={<Switch/>} label="Require DNA Sample & Retina Scan for Unlock"/>
                                <FormControlLabel control={<Switch/>} label="Enable blockchain-based decentralized multi-signature unlock"/>
                                <FormControlLabel control={<Switch/>} label="Enable hardware key requirement"/>
                            </FormGroup>

                            <Button variant="contained">Button</Button>
                        </CardContent>

                    </Card>



                </Stack>
            </Container>
        </div>
    );
};

export default Settings;
