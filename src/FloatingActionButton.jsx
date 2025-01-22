import React, { useState } from 'react';
import { SpeedDial, SpeedDialAction, SpeedDialIcon } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import SettingsIcon from '@mui/icons-material/Settings';

const FloatingActionButton = () => {
    const [open, setOpen] = useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <SpeedDial
            ariaLabel="Options"
            sx={{ position: 'fixed', bottom: 16, right: 16 }}
            icon={<SpeedDialIcon openIcon={<AddIcon />} />}
            onClose={handleClose}
            onOpen={handleOpen}
            open={open}
            FabProps={{
                onClick: () => setOpen(!open), // Toggle open state on click
            }}
        >
            <SpeedDialAction
                icon={<EditIcon />}
                tooltipTitle="Create new task"
                onClick={() => console.log('Edit clicked')}
            />
            <SpeedDialAction
                icon={<DeleteIcon />}
                tooltipTitle="Delete"
                onClick={() => console.log('Delete clicked')}
            />
            <SpeedDialAction
                icon={<SettingsIcon />}
                tooltipTitle="Settings"
                onClick={() => console.log('Settings clicked')}
            />
        </SpeedDial>

    );
};

export default FloatingActionButton;
