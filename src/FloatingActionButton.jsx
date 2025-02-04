import React, { useState } from 'react';
import { SpeedDial, SpeedDialAction, SpeedDialIcon, Dialog, DialogTitle, DialogContent, DialogActions, Button } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import SettingsIcon from '@mui/icons-material/Settings';
import AddTask from './AddTask'; // ✅ Import AddTask component
import Box from '@mui/material/Box';

const FloatingActionButton = () => {
    const [open, setOpen] = useState(false); // SpeedDial open state
    const [isModalOpen, setIsModalOpen] = useState(false); // Modal open state

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    // Open AddTask modal and close SpeedDial
    const handleModalOpen = () => {
        setIsModalOpen(true);
        setOpen(false); // Close SpeedDial when opening modal
    };

    const handleModalClose = () => setIsModalOpen(false);

    return (
        <>
            {/* Floating SpeedDial Button */}
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
                    onClick={handleModalOpen} // ✅ Open the AddTask modal
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

            {/* AddTask Modal */}

        </>
    );
};

export default FloatingActionButton;
