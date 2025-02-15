import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Badge from '@mui/material/Badge';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Modal from '@mui/material/Modal';
import Box from '@mui/material/Box';
import AddTask from './AddTask';
import AddPeople from './AddPeople';
import AddRoom from './AddRoom';
import {Avatar, Divider, ListItemIcon, Popover, SwipeableDrawer} from "@mui/material";
import HomeIcon from '@mui/icons-material/Home';
import TaskIcon from '@mui/icons-material/Task';
import PeopleIcon from '@mui/icons-material/People';
import RoomIcon from '@mui/icons-material/Room';
import LeaderboardIcon from '@mui/icons-material/Leaderboard';
import SettingsIcon from '@mui/icons-material/Settings';
import PaymentsIcon from '@mui/icons-material/Payments';
import {AccountCircle, Logout} from "@mui/icons-material";
import InfoIcon from '@mui/icons-material/Info';
import Naglogo from './assets/Naeg-logo-2.png'

// import Statistics from './Statistics';
// import Settings from './Settings';

const Header = () => {
    const [isDrawerOpen, setIsDrawerOpen] = useState(false);
    const [popoverAnchorEl, setPopoverAnchorEl] = useState(null);
    const [isAddTaskModalOpen, setIsAddTaskModalOpen] = useState(false);
    const [isAddPeopleModalOpen, setIsAddPeopleModalOpen] = useState(false);
    const [isAddRoomsModalOpen, setIsAddRoomsModalOpen] = useState(false);
    const [isSettingsModalOpen, setIsSettingsModalOpen] = useState(false);
    const navigate = useNavigate();

    // Function to toggle the drawer state
    const toggleDrawer = (open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }
        setIsDrawerOpen(open);
    };

    // Popover handlers
    const handleAvatarClick = (event) => {
        setPopoverAnchorEl(event.currentTarget);
    };

    const handlePopoverClose = () => {
        setPopoverAnchorEl(null);
    };

    const isPopoverOpen = Boolean(popoverAnchorEl);

    const toggleAddTaskModal = (open) => {
        setIsAddTaskModalOpen(open);
    };

    const toggleAddPeopleModal = (open) => {
        setIsAddPeopleModalOpen(open);
    }

    const toggleAddRoomsModal = (open) => {
        setIsAddRoomsModalOpen(open);
    }

    const toggleSettingsModal = (open) => {
        setIsSettingsModalOpen(open);
    }

    return (
        <>
            <AppBar position="fixed">
                <Toolbar>
                    <IconButton edge="start" color="inherit" aria-label="menu" onClick={toggleDrawer(true)}>
                        <MenuIcon />
                    </IconButton>

                    <img
                        src={Naglogo}
                        alt="trk"
                        style={{ height: 40 }}
                    />
                    {/* Avatar with popover */}
                    <Avatar
                        alt="Fornavn Etternavn"
                        src="https://th.bing.com/th/id/R.3d3fb5d7fd683782784cb712de8d8c71?rik=eWBkaFbes%2fMRDQ&riu=http%3a%2f%2fclipart-library.com%2fimages_k%2fsmiley-face-png-transparent%2fsmiley-face-png-transparent-21.png&ehk=iSLreF%2fX7uU5Her%2fa5Z4Nej%2baPAjy2i5OR23iEGjOSY%3d&risl=&pid=ImgRaw&r=0"
                        onClick={handleAvatarClick}
                        sx={{cursor: 'pointer', marginLeft:"auto" }}
                        />
                </Toolbar>
            </AppBar>

            {/* Popover for avatar */}
            <Popover
                open={isPopoverOpen}
                anchorEl={popoverAnchorEl}
                onClose={handlePopoverClose}
                anchorOrigin={{
                    vertical:'bottom',
                    horizontal: 'right'
                }}
            >
                <Box sx={{ padding: '10px', width: '250px' }}>
                    { /* USER INFO*/ }
                    <Typography variant="subtitle1" fontweight="bold">
                        Fornavn Etternavn
                    </Typography>
                    <Typography variant="body2" color="textSecondary">
                        name_surname@example.com
                    </Typography>
                    <Divider sx={{ my: 1}} />

                    {/* OPTIONS LIST */}
                    <List>
                        <ListItem button onClick={() => navigate('/profile')}>
                            <ListItemIcon>
                                <AccountCircle/>
                            </ListItemIcon>
                            <ListItemText primary="My account" />
                        </ListItem>
                        <ListItem button onClick={() => navigate('/Settings')}>
                            <ListItemIcon>
                                <SettingsIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Settings" />
                        </ListItem>
                        <ListItem button onClick={() => navigate('TaskInfo')}>
                            <ListItemIcon>
                                <InfoIcon/>
                            </ListItemIcon>
                        </ListItem>
                        <ListItem button onClick={() => navigate('Login')}>
                            <ListItemIcon>
                                <Logout/>
                            </ListItemIcon>
                            <ListItemText primary="Log out" />
                        </ListItem>
                    </List>
                </Box>

            </Popover>




            {/* This box creates space equal to the AppBar height */}
            {/* Trenger denne boksen for at kortene ikke skal havne under app-baren */}
            <Box sx={{ marginTop: '64px' }}> {/*Adjust the marginTop value if needed*/}</Box>

            { /* Dette er sideskuffen med manipulering */ }
            <SwipeableDrawer
                swipeAreaWidth={10}
                anchor="left"
                open={isDrawerOpen}
                onClose={toggleDrawer(false)}>

                {/* I sideskuffen er det en liste med valg*/}
                <List>
                    <ListItem
                        button
                        onClick = {() => {
                            setIsDrawerOpen(false);
                            navigate("/");
                        }}
                        >
                        <ListItemIcon>
                            <HomeIcon />
                        </ListItemIcon>
                        <ListItemText primary="Home"/>
                    </ListItem>

                    <Divider/>

                    <ListItem
                        // Legg til oppgave
                        button
                        onClick={() => {
                            setIsDrawerOpen(false);
                            toggleAddTaskModal(true);
                            console.log('Add Task Modal open', isAddTaskModalOpen);
                        }}
                    >

                        <ListItemIcon>
                            <Badge
                                anchorOrigin={{ vertical:"top",horizontal:"right" }}
                                badgeContent={"+"}
                            >
                            <TaskIcon />
                            </Badge>
                        </ListItemIcon>
                        <ListItemText primary="Add tasks" />
                    </ListItem>
                    <ListItem
                        // Legg til folk
                        button
                        onClick={() => {
                            setIsDrawerOpen(false);
                            toggleAddPeopleModal(true);
                        }}
                    >
                        <ListItemIcon>
                            <Badge
                                anchorOrigin={{ vertical:"top",horizontal:"right" }}
                                badgeContent={"+"}
                            >
                            <PeopleIcon/>
                                </Badge>
                        </ListItemIcon>
                        <ListItemText primary="Add people" />
                    </ListItem>

                    <ListItem
                        button
                        onClick={ () => {
                            setIsDrawerOpen(false);
                            toggleAddRoomsModal(true);
                        }}
                        >

                        <ListItemIcon>
                            <Badge
                                anchorOrigin={{ vertical:"top",horizontal:"right" }}
                                badgeContent={"+"}
                            >
                            <RoomIcon/>
                                </Badge>
                        </ListItemIcon>
                        <ListItemText primary="Add room"/>
                    </ListItem>

                    <Divider/>

                    {/* TASKS */}
                    <ListItem>
                        <ListItemIcon>
                                <TaskIcon/>
                        </ListItemIcon>
                        <ListItemText primary="My tasks" />
                    </ListItem>
                    {/* PEOPLE */}
                    <ListItem>
                        <ListItemIcon>
                            <PeopleIcon/>
                        </ListItemIcon>
                        <ListItemText primary="My people" />
                    </ListItem>
                    {/* ROOMS */}
                    <ListItem>
                        <ListItemIcon>
                            <RoomIcon/>
                        </ListItemIcon>
                        <ListItemText primary="My rooms" />
                    </ListItem>

                    <Divider/>

                    <ListItem
                        // Statistikk
                        button
                        onClick={() => {
                            setIsDrawerOpen(false);
                            console.log('Statistics clicked');
                            navigate('/statistics');
                        // Add nagivation or additional functionality here}
                        }}
                    >
                        <ListItemIcon>
                            <LeaderboardIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Statistics"/>
                    </ListItem>

                    <ListItem
                        // Betalinger
                        button
                        onClick={() => {
                            setIsDrawerOpen(false);
                            console.log('Betalinger');
                            navigate('/payments');
                        }}
                    >

                    <ListItemIcon>
                        <PaymentsIcon/>
                    </ListItemIcon>
                    <ListItemText primary="Payments"/>
                    </ListItem>

                    <ListItem
                        // Innstillinger
                        button
                        onClick={() => {
                            setIsDrawerOpen(false);
                            navigate('/settings');
                        }}
                    >
                        <ListItemIcon>
                            <SettingsIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Settings" />
                    </ListItem>

                </List>
            </SwipeableDrawer>


            { /* MODAL-VALG */}
            { /* Legg til oppgave */}
            <Modal
                open={isAddTaskModalOpen}
                onClose={() => toggleAddTaskModal(false)}
                aria-labelledby="add-task-modal"
                aria-describedby="add-task-modal-description"
            >
                <Box
                    sx={{
                        position: 'absolute',
                        top: '50%',
                        left: '50%',
                        transform: 'translate(-50%, -50%)',
                        width: 400,
                        p: 4,
                        borderRadius: 8
                    }}
                >
                    <AddTask />
                </Box>
            </Modal>

            <Modal
                // Legg til folk
                open={isAddPeopleModalOpen}
                onClose={() => toggleAddPeopleModal(false)}
                aria-labelledby="add-people-modal"
                aria-describedby="add-people-modal-description"
            >
                <Box
                    sx={{
                        position: 'absolute',
                        top: '50%',
                        left: '50%',
                        transform: 'translate(-50%, -50%)',
                        width: 400,
                        p: 4
                    }}
                >
                    <AddPeople />
                </Box>
            </Modal>

            <Modal
                // Legg til rom
                open={isAddRoomsModalOpen}
                onClose = {() => toggleAddRoomsModal(false)}
                aria-labelledby = "add-rooms-modal"
                aria-describedby="add-rooms-modal-description"
            >
                <Box
                    sx={{
                        position: 'absolute',
                        top: '50%',
                        left: '50%',
                        transform: 'translate(-50%, -50%)',
                        width: 400,
                        p: 4
                    }}
                >
                    <AddRoom />
                </Box>
            </Modal>

            <Modal
                // Settings
                open={isSettingsModalOpen}
                onClose = {() => toggleSettingsModal(false)}
                aria-labelledby = "settings-modal"
                aria-describedby="settings-modal-description"
            >
                <List>
                    <ListItem>
                        <ListItemText>
                            hehe
                        </ListItemText>
                    </ListItem>
                </List>
            </Modal>
        </>
    );
};

export default Header;