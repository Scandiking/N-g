import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import {
    AppBar,
    Toolbar,
    Typography,
    Button,
    Box,
    Drawer,
    List,
    ListItem,
    ListItemButton,
    ListItemIcon,
    ListItemText,
    IconButton,
    Avatar,
    Menu,
    MenuItem,
    Divider
} from '@mui/material';
import {
    Menu as MenuIcon,
    People as PeopleIcon,
    Home as HomeIcon,
    Assessment as AssessmentIcon,
    Payment as PaymentIcon,
    Settings as SettingsIcon,
    Task as TaskIcon,
    MeetingRoom as RoomIcon,
    Logout
} from '@mui/icons-material';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import { useAuth } from './AuthContext';

const Header = ({ onAddTask }) => {
    const navigate = useNavigate();
    const location = useLocation();
    const { user, isAuthenticated, logout, loading } = useAuth();

    const [drawerOpen, setDrawerOpen] = useState(false);
    const [anchorEl, setAnchorEl] = useState(null);

    const handleUserMenuOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleUserMenuClose = () => {
        setAnchorEl(null);
    };

    const handleLogout = () => {
        handleUserMenuClose();
        logout();
    };

    const menuItems = [
        { text: 'Home', icon: <HomeIcon />, path: '/' },
        { text: 'My Tasks', icon: <TaskIcon />, path: '/mytasks' },
        { text: 'My People', icon: <PeopleIcon />, path: '/mypeople' },
        { text: 'My Rooms', icon: <RoomIcon />, path: '/myrooms' },
        { text: 'Add People', icon: <PeopleIcon />, path: '/add-people' },
        { text: 'Add Room', icon: <RoomIcon />, path: '/add-room' },
        { text: 'Statistics', icon: <AssessmentIcon />, path: '/statistics' },
        { text: 'Payments', icon: <PaymentIcon />, path: '/payments' },
        { text: 'Settings', icon: <SettingsIcon />, path: '/settings' }
    ];

    const toggleDrawer = (open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }
        setDrawerOpen(open);
    };

    const handleMenuClick = (path) => {
        navigate(path);
        setDrawerOpen(false);
    };

    // Don't show header on login/register pages
    if (location.pathname === '/login' || location.pathname === '/register') {
        return null;
    }

    // Helper function to get user display name
    const getUserDisplayName = () => {
        if (!user) return '';

        if (user.person && (user.person.firstName || user.person.lastName)) {
            return `${user.person.firstName || ''} ${user.person.lastName || ''}`.trim();
        }

        return user.username || user.email || '';
    };

    // Helper function to get user initials for avatar
    const getUserInitials = () => {
        if (!user) return '?';

        if (user.person && user.person.firstName && user.person.lastName) {
            return `${user.person.firstName.charAt(0)}${user.person.lastName.charAt(0)}`.toUpperCase();
        }

        if (user.username) {
            return user.username.charAt(0).toUpperCase();
        }

        if (user.email) {
            return user.email.charAt(0).toUpperCase();
        }

        return '?';
    };

    return (
        <>
            <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
                <Toolbar>
                    <IconButton
                        color="inherit"
                        aria-label="open drawer"
                        edge="start"
                        onClick={toggleDrawer(true)}
                        sx={{ mr: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>

                    <Typography
                        variant="h6"
                        component="div"
                        sx={{ flexGrow: 1, cursor: 'pointer' }}
                        onClick={() => navigate('/')}
                    >
                        NAG Task Manager
                    </Typography>

                    {/* User info section */}
                    {isAuthenticated && !loading && user ? (
                        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                            <Box sx={{ display: { xs: 'none', sm: 'block' }, textAlign: 'right' }}>
                                <Typography variant="body2" sx={{ lineHeight: 1.2 }}>
                                    {getUserDisplayName()}
                                </Typography>
                                <Typography variant="caption" color="inherit" sx={{ opacity: 0.8 }}>
                                    {user.email}
                                </Typography>
                            </Box>

                            <IconButton
                                color="inherit"
                                onClick={handleUserMenuOpen}
                                sx={{ ml: 1 }}
                            >
                                <Avatar sx={{ width: 32, height: 32, bgcolor: 'primary.dark' }}>
                                    {getUserInitials()}
                                </Avatar>
                            </IconButton>
                        </Box>
                    ) : loading ? (
                        <Box sx={{ display: 'flex', alignItems: 'center' }}>
                            <Typography variant="body2">Loading...</Typography>
                        </Box>
                    ) : (
                        <Button color="inherit" onClick={() => navigate('/login')}>
                            Login
                        </Button>
                    )}
                </Toolbar>
            </AppBar>

            {/* User menu dropdown */}
            <Menu
                anchorEl={anchorEl}
                open={Boolean(anchorEl)}
                onClose={handleUserMenuClose}
                onClick={handleUserMenuClose}
                PaperProps={{
                    elevation: 3,
                    sx: {
                        overflow: 'visible',
                        filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
                        mt: 1.5,
                        minWidth: 200,
                        '& .MuiAvatar-root': {
                            width: 32,
                            height: 32,
                            ml: -0.5,
                            mr: 1,
                        },
                    },
                }}
                transformOrigin={{ horizontal: 'right', vertical: 'top' }}
                anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
            >
                {user && (
                    <MenuItem disabled sx={{ flexDirection: 'column', alignItems: 'flex-start', py: 1.5 }}>
                        <Typography variant="subtitle2" sx={{ fontWeight: 'bold' }}>
                            {getUserDisplayName()}
                        </Typography>
                        <Typography variant="caption" color="text.secondary">
                            {user.email}
                        </Typography>
                        {user.person?.phoneNo && (
                            <Typography variant="caption" color="text.secondary">
                                {user.person.phoneNo}
                            </Typography>
                        )}
                    </MenuItem>
                )}
                <Divider />
                <MenuItem onClick={() => navigate('/profile')}>
                    <ListItemIcon>
                        <AccountBoxIcon fontSize="small" />
                    </ListItemIcon>
                    My account
                </MenuItem>
                <MenuItem onClick={() => navigate('/settings')}>
                    <ListItemIcon>
                        <SettingsIcon fontSize="small" />
                    </ListItemIcon>
                    Settings
                </MenuItem>
                <MenuItem onClick={handleLogout}>
                    <ListItemIcon>
                        <Logout fontSize="small" />
                    </ListItemIcon>
                    Logout
                </MenuItem>
            </Menu>

            {/* Sidebar Drawer */}
            <Drawer
                anchor="left"
                open={drawerOpen}
                onClose={toggleDrawer(false)}
            >
                <Toolbar /> {/* Spacer for AppBar */}
                <Box
                    sx={{ width: 250 }}
                    role="presentation"
                    onClick={toggleDrawer(false)}
                    onKeyDown={toggleDrawer(false)}
                >
                    <List>

                        {menuItems.map((item) => (
                            <ListItem key={item.text} disablePadding>
                                <ListItemButton
                                    onClick={() => handleMenuClick(item.path)}
                                    selected={location.pathname === item.path}
                                >
                                    <ListItemIcon>
                                        {item.icon}
                                    </ListItemIcon>
                                    <ListItemText primary={item.text} />
                                </ListItemButton>
                            </ListItem>
                        ))}
                    </List>
                </Box>
            </Drawer>
        </>
    );
};

export default Header;