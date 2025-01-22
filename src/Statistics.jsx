import React from 'react';
import { CardMedia, Card, CardContent, Box, Typography } from "@mui/material";
import DonaldDuckImage from './DonaldDuck.png';
import DaisyDuckImage from './DaisyDuck.png';
import MickeyMouseImage from './MickeyMouse.png';
import MinnieMouseImage from './MinnieMouse.png';
import GoofyImage from './Goofy.png';

const sampleStatistics = [
    {
        id: 1,
        title: "Most completed tasks overall",
        description: "{User} have completed most tasks in total ever",
        image: DonaldDuckImage,
    },
    {
        id: 2,
        title: "Fastest task completer",
        description: "{User} have completed tasks the fastest in an average of inception",
        image: DaisyDuckImage,
    },
    {
        id: 3,
        title: "Least nagged",
        description: "Just do it. {User} gets stuff done.",
        image: MickeyMouseImage,
    },
    {
        id: 4,
        title: "Most nagged",
        description: "{User} is perhaps related to sloths. Remember sloth is a death sin.",
        image: MinnieMouseImage,
    },
    {
        id: 5,
        title: "Slowest task completer",
        description: "{User} have completed tasks the slowest in an average of inception. Get with the times.",
        image: GoofyImage,
    },
];

function Statistics() {
    return (
        <Box sx={{ p: 3 }}>
            {sampleStatistics.map(stat => (
                <Card
                    key={stat.id}
                    sx={{
                        display: 'flex',
                        mb: 2,
                        p: 2,
                        borderRadius: 2,
                        boxShadow: 3,
                    }}
                >
                    {/* CardMedia for the image */}
                    <CardMedia
                        component="img"
                        alt={stat.title}
                        image={stat.image}
                        sx={{
                            width: 100,
                            height: 100,
                            objectFit: 'cover',
                            borderRadius: 1,
                            m: 1,
                        }}
                    />
                    {/* CardContent for the text */}
                    <CardContent>
                        <Typography variant="h6" component="div" gutterBottom>
                            {stat.title}
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                            {stat.description}
                        </Typography>
                    </CardContent>
                </Card>
            ))}
        </Box>
    );
}

export default Statistics;
