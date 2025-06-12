import React, { useState, useEffect } from 'react';
import {
    Box, Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText,
    Typography, Divider, Avatar, Chip, IconButton, Menu, MenuItem,
    Badge, Collapse
} from '@mui/material';
import {
    Home as HomeIcon,
    Group as GroupIcon,
    Person as PersonIcon,
    Settings as SettingsIcon,
    Logout as LogoutIcon,
    Menu as MenuIcon,
    ExpandLess,
    ExpandMore,
    Add as AddIcon,
    Star as StarIcon
} from '@mui/icons-material';
import { useNavigate, useLocation } from 'react-router-dom';

function Navigation({ user, onLogout }) {
    const navigate = useNavigate();
    const location = useLocation();
    const [mobileOpen, setMobileOpen] = useState(false);
    const [userMenuAnchor, setUserMenuAnchor] = useState(null);
    const [rooms, setRooms] = useState([]);
    const [roomsExpanded, setRoomsExpanded] = useState(true);

    useEffect(() => {
        fetchUserRooms();
    }, []);

    const fetchUserRooms = async () => {
        try {
            const token = localStorage.getItem('token');
            const response = await fetch('http://localhost:8080/api/rooms', {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const roomData = await response.json();
                setRooms(roomData);
            }
        } catch (error) {
            console.error('Failed to fetch rooms:', error);
        }
    };

    const handleDrawerToggle = () => {
        setMobileOpen(!mobileOpen);
    };

    const handleNavigation = (path) => {
        navigate(path);
        setMobileOpen(false);
    };

    const handleUserMenuOpen = (event) => {
        setUserMenuAnchor(event.currentTarget);
    };

    const handleUserMenuClose = () => {
        setUserMenuAnchor(null);
    };

    const isActive = (path) => {
        return location.pathname === path || location.pathname.startsWith(path);
    };

    const drawerContent = (
        <Box sx={{ width: 280, height: '100%' }}>
            {/* User Profile Section */}
            <Box sx={{ p: 2, bgcolor: 'primary.main', color: 'white' }}>
                <Box display="flex" alignItems="center" justifyContent="space-between">
                    <Box display="flex" alignItems="center">
                        <Avatar sx={{ bgcolor: 'white', color: 'primary.main', mr: 2 }}>
                            {user?.name?.charAt(0) || 'U'}
                        </Avatar>
                        <Box>
                            <Typography variant="subtitle1" noWrap>
                                {user?.name || 'User'}
                            </Typography>
                            <Typography variant="body2" sx={{ opacity: 0.8 }} noWrap>
                                {user?.email || 'user@example.com'}
                            </Typography>
                        </Box>
                    </Box>
                    <IconButton
                        size="small"
                        sx={{ color: 'white' }}
                        onClick={handleUserMenuOpen}
                    >
                        <SettingsIcon />
                    </IconButton>
                </Box>
            </Box>

            {/* Main Navigation */}
            <List sx={{ pt: 2 }}>
                <ListItem disablePadding>
                    <ListItemButton
                        selected={isActive('/home')}
                        onClick={() => handleNavigation('/home')}
                    >
                        <ListItemIcon>
                            <HomeIcon />
                        </ListItemIcon>
                        <ListItemText primary="Home" />
                    </ListItemButton>
                </ListItem>

                <ListItem disablePadding>
                    <ListItemButton
                        selected={isActive('/people')}
                        onClick={() => handleNavigation('/people')}
                    >
                        <ListItemIcon>
                            <PersonIcon />
                        </ListItemIcon>
                        <ListItemText primary="Add People" />
                    </ListItemButton>
                </ListItem>

                <Divider sx={{ my: 1 }} />

                {/* Rooms Section */}
                <ListItem disablePadding>
                    <ListItemButton onClick={() => setRoomsExpanded(!roomsExpanded)}>
                        <ListItemIcon>
                            <GroupIcon />
                        </ListItemIcon>
                        <ListItemText primary="Rooms" />
                        <Badge badgeContent={rooms.length} color="primary" sx={{ mr: 1 }}>
                            {roomsExpanded ? <ExpandLess /> : <ExpandMore />}
                        </Badge>
                    </ListItemButton>
                </ListItem>

                <Collapse in={roomsExpanded} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        <ListItem disablePadding>
                            <ListItemButton
                                sx={{ pl: 4 }}
                                selected={isActive('/rooms') && location.pathname === '/rooms'}
                                onClick={() => handleNavigation('/rooms')}
                            >
                                <ListItemIcon>
                                    <GroupIcon />
                                </ListItemIcon>
                                <ListItemText primary="All Rooms" />
                            </ListItemButton>
                        </ListItem>

                        {rooms.slice(0, 5).map((room) => (
                            <ListItem key={room.roomId} disablePadding>
                                <ListItemButton
                                    sx={{ pl: 4 }}
                                    selected={isActive(`/rooms/${room.roomId}`)}
                                    onClick={() => handleNavigation(`/rooms/${room.roomId}`)}
                                >
                                    <ListItemIcon>
                                        <Avatar sx={{ width: 24, height: 24, fontSize: 12 }}>
                                            {room.roomName.charAt(0).toUpperCase()}
                                        </Avatar>
                                    </ListItemIcon>
                                    <ListItemText
                                        primary={room.roomName}
                                        primaryTypographyProps={{
                                            variant: 'body2',
                                            noWrap: true
                                        }}
                                    />
                                    {room.isAdmin && (
                                        <StarIcon sx={{ fontSize: 16, color: 'gold' }} />
                                    )}
                                </ListItemButton>
                            </ListItem>
                        ))}

                        {rooms.length > 5 && (
                            <ListItem disablePadding>
                                <ListItemButton
                                    sx={{ pl: 4 }}
                                    onClick={() => handleNavigation('/rooms')}
                                >
                                    <ListItemText
                                        primary={`+${rooms.length - 5} more rooms`}
                                        primaryTypographyProps={{
                                            variant: 'body2',
                                            color: 'text.secondary'
                                        }}
                                    />
                                </ListItemButton>
                            </ListItem>
                        )}

                        <ListItem disablePadding>
                            <ListItemButton
                                sx={{ pl: 4 }}
                                onClick={() => handleNavigation('/rooms/create')}
                            >
                                <ListItemIcon>
                                    <AddIcon />
                                </ListItemIcon>
                                <ListItemText
                                    primary="Create Room"
                                    primaryTypographyProps={{
                                        variant: 'body2',
                                        color: 'primary.main'
                                    }}
                                />
                            </ListItemButton>
                        </ListItem>
                    </List>
                </Collapse>

                <Divider sx={{ my: 1 }} />

                <ListItem disablePadding>
                    <ListItemButton
                        selected={isActive('/user-management')}
                        onClick={() => handleNavigation('/user-management')}
                    >
                        <ListItemIcon>
                            <PersonIcon />
                        </ListItemIcon>
                        <ListItemText primary="User Management" />
                    </ListItemButton>
                </ListItem>

                <ListItem disablePadding>
                    <ListItemButton
                        selected={isActive('/settings')}
                        onClick={() => handleNavigation('/settings')}
                    >
                        <ListItemIcon>
                            <SettingsIcon />
                        </ListItemIcon>
                        <ListItemText primary="Settings" />
                    </ListItemButton>
                </ListItem>
            </List>

            {/* Logout at bottom */}
            <Box sx={{ position: 'absolute', bottom: 0, width: '100%', p: 2 }}>
                <ListItem disablePadding>
                    <ListItemButton onClick={onLogout}>
                        <ListItemIcon>
                            <LogoutIcon />
                        </ListItemIcon>
                        <ListItemText primary="Logout" />
                    </ListItemButton>
                </ListItem>
            </Box>

            {/* User Menu */}
            <Menu
                anchorEl={userMenuAnchor}
                open={Boolean(userMenuAnchor)}
                onClose={handleUserMenuClose}
            >
                <MenuItem onClick={() => {
                    handleUserMenuClose();
                    handleNavigation('/profile');
                }}>
                    <PersonIcon sx={{ mr: 1 }} />
                    Profile
                </MenuItem>
                <MenuItem onClick={() => {
                    handleUserMenuClose();
                    handleNavigation('/settings');
                }}>
                    <SettingsIcon sx={{ mr: 1 }} />
                    Settings
                </MenuItem>
                <Divider />
                <MenuItem onClick={() => {
                    handleUserMenuClose();
                    onLogout();
                }}>
                    <LogoutIcon sx={{ mr: 1 }} />
                    Logout
                </MenuItem>
            </Menu>
        </Box>
    );

    return (
        <>
            {/* Mobile Menu Button */}
            <IconButton
                color="inherit"
                aria-label="open drawer"
                edge="start"
                onClick={handleDrawerToggle}
                sx={{ mr: 2, display: { sm: 'none' } }}
            >
                <MenuIcon />
            </IconButton>

            {/* Mobile Drawer */}
            <Drawer
                variant="temporary"
                open={mobileOpen}
                onClose={handleDrawerToggle}
                ModalProps={{
                    keepMounted: true, // Better open performance on mobile.
                }}
                sx={{
                    display: { xs: 'block', sm: 'none' },
                    '& .MuiDrawer-paper': { boxSizing: 'border-box', width: 280 },
                }}
            >
                {drawerContent}
            </Drawer>

            {/* Desktop Drawer */}
            <Drawer
                variant="permanent"
                sx={{
                    display: { xs: 'none', sm: 'block' },
                    '& .MuiDrawer-paper': { boxSizing: 'border-box', width: 280 },
                }}
                open
            >
                {drawerContent}
            </Drawer>
        </>
    );
}