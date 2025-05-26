/* OVERVIEW PAGE TO MANAGE TASKS */
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import Stack from '@mui/material/Stack';
import InfoIcon from '@mui/icons-material/Info';
import { useState } from "react";

const sampleTasks = [
    { id: 1, title: 'Støvsuge kjøkkenet', description: 'Fjern smuler og hundehår osv.' },
    { id: 2, title: 'Ta ut søpla', description: 'Kyllingemballasje lukter guffent fort. Må fjernes. Restavfall, matavfall, papp/papir.' },
    { id: 3, title: 'Vaske speilet', description: 'Speilet er fullt av tannkrem(?) igjen' },
    { id: 4, title: 'Ta ut av oppvaskmaskinen', description: 'Det er rent, skapene er tomme for asjetter og glass' },
    { id: 5, title: 'Sette inn i oppvaskmaskinen', description: 'Benken er full av skitne glass og tallerkner' },
];

function MyTasks() {
    const [tasks, setTasks] = useState(sampleTasks);

    // Function to remove completed tasks
    const handleComplete = (id) => {
        setTasks((prevTasks) => prevTasks.filter((task) => task.id !== id));
    };

    return (
        <Container sx={{ mt: 4, p: 3 }}>
            <Grid container spacing={3}>
                {tasks.map((task) => (
                    <Grid item xs={12} sm={6} md={4} key={task.id}>
                        <Card sx={{ p: 2, boxShadow: 3 }}>
                            <CardContent>
                                <Typography variant="h6">{task.title}</Typography>
                                <Typography>{task.description}</Typography>
                                <Stack direction="row" gap={1}>
                                    <Button variant="contained" color="success" sx={{ mt: 2 }} onClick={() => handleComplete(task.id)}>
                                        Mark as Done
                                    </Button>
                                    <Button variant="outlined" startIcon={<InfoIcon />} sx={{mt:2}}>
                                        Info
                                    </Button>

                                </Stack>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}

            </Grid>
        </Container>
    );
}

export default MyTasks;