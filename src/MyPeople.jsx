import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import CardMedia from '@mui/material/CardMedia';
import { useState } from "react";

const samplePeople = [
    { id: 1, first_name: 'Ingrid', last_name: 'Hansen', rooms: ['Dormitory', 'School'] },
    { id: 2, first_name: 'Magnus', last_name: 'Lund', rooms: ['Dormitory', 'School'] },
    { id: 3, first_name: 'Sofie', last_name: 'Berg', rooms: ['Dormitory'] },
    { id: 4, first_name: 'Simen', last_name: 'Nygaard', rooms: ['Dormitory'] },
    { id: 5, first_name: 'Emma', last_name: 'Aas', rooms: ['Dormitory', 'School'] },
];

function MyPeople() {
    const [people, setPeople] = useState(samplePeople);

    return (
        <Container sx={{ mt: 4 }}>
            <Grid container spacing={3}>
                {people.map((person) => (
                    <Grid item xs={12} sm={6} md={4} key={person.id}>
                        <Card sx={{ display: "flex", alignItems: "center", p: 2 }}>
                            {/* Profile image */}
                            <CardMedia
                                component="img"
                                alt="profile picture"
                                title="profile picture"
                                image="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"
                                sx = {{
                                    width: 80,
                                    height: 80,
                                    borderRadius: "10%",
                                    objectFit: "cover",
                                    mr: 2
                                }}
                            />
                            {/* PERSON INFO */}
                            <CardContent sx={{ flex: 1 }}>

                                <Typography variant="h6">
                                    {person.first_name} {person.last_name}
                                </Typography>
                                <Typography color="text.secondary">
                                    Rooms: {person.rooms.join(', ')}
                                </Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
}

export default MyPeople;
