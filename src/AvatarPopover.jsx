import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Avatar, Popover, Typography, List, ListItem, ListItemText, Divider } from "@mui/material";

const AvatarPopover = () => {
    const [anchorEl, setAnchorEl] = useState(null);
    const navigate = useNavigate();

    const handleAvatarClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleLogout = () => {
        // Perform logout actions (e.g. clear authentication data)
        setAnchorEl(null); // Close the popover
        navigate("/Login"); // Redirect to login
    };

    const open = Boolean(anchorEl);
    const id = open ? "avatar-popover" : undefined;

    return (
        <>
            {/* Avatar in App Bar */}
            <Avatar
                alt="User Name"
                src="https://via.placeholder.com/40"
                onClick={handleAvatarClick}
                sx={{ cursor: "pointer" }}
            />

            {/* Popover */}
            <Popover
                id={id}
                open={open}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "left",
                }}
                transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                }}
            >
                <div style={{ padding: "16px", width: "250px" }}>
                    {/* User Info */}
                    <Typography variant="subtitle1" fontWeight="bold">
                        User Name
                    </Typography>
                    <Typography variant="body2" color="textSecondary">
                        user@example.com
                    </Typography>
                    <Divider style={{ margin: "8px 0" }} />

                    {/* Options List */}
                    <List>
                        <ListItem button>
                            <ListItemText primary="My Account" />
                        </ListItem>
                        <ListItem button>
                            <ListItemText primary="Settings" />
                        </ListItem>
                        <ListItem button onClick={handleLogout}> {/*Logout click handler*/}
                            <ListItemText primary="Log out" />
                        </ListItem>
                    </List>
                </div>
            </Popover>
        </>
    );
};

export default AvatarPopover;
