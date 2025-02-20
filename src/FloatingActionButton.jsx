import Fab from "@mui/material/Fab";
import AddIcon from "@mui/icons-material/Add";

function FloatingActionButton({ onAddTask }) {
    return (
        <Fab
            color="primary"
            sx={{ position: "fixed", bottom: 16, right: 16 }}
            onClick={onAddTask} // Open modal when clicked
        >
            <AddIcon />
        </Fab>
    );
}

export default FloatingActionButton;
