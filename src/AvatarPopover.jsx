import React, { useState } from "react";
import { Avatar, Popover, Typography, List, ListItem, ListItemText, Divider } from "@mui/material";

const AvatarPopover = () => {
    const [anchorEl, setAnchorEl] = useState(null);

    const handleAvatarClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
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
                        <ListItem button>
                            <ListItemText primary="Logout" />
                        </ListItem>
                    </List>
                </div>
            </Popover>
        </>
    );
};

export default AvatarPopover;
