import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';


const sampleTasks = [
    {
        id: 1,
        title: 'Støvsuge kjøkkenet',
        description: 'Fjern smuler og hundehår osv.'
    },
    {
        id: 2,
        title: 'Ta ut søpla',
        description: 'Kyllingemballasje lukter guffent fort. Må fjernes. Restavfall, matavfall, papp/papir.'
    },
    {
        id: 3,
        title: 'Vaske speilet',
        description: 'Speilet er fullt av tannkrem(?) igjen'
    },
    {
        id: 4,
        title: 'Ta ut av oppvaskmaskinen',
        description: 'Det er rent, skapene er tomme for asjetter og glass'
    },
    {
        id: 5,
        title:'Sette inn i oppvaskmaskinen',
        description:'Benken er full av skitne glass og tallerkner'
    },
];

function TaskInfo() {
    return (
        <div>
        <Grid container spacing={2}>
            <Grid size={8}>
                <Card>size=8</Card>
            </Grid>
            <Grid size={4}>
                <Card>size=4</Card>
            </Grid>
            <Grid size={4}>
                <Card>size=4</Card>
            </Grid>
            <Grid size={8}>
                <Card>size=8</Card>
            </Grid>
        </Grid>
        </div>
    )
}

export default TaskInfo;