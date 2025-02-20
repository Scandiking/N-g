import React from 'react';
import { Box, Card, CardMedia, Container, Typography, Button } from "@mui/material";
import Bankkort1 from "./assets/Bankkort1.png"
import Bankkort2 from "./assets/Bankkort2.png"
import Bankkort3 from "./assets/Bankkort3.png"
import Bankkort4 from "./assets/Bankkort4.png"

const ExampleCards = [
    {
        id: 1,
        type: "Sparebank1 Bedrift",
        ccno: 1234123412341234,
        accno: "4444 51 01236",
        image:Bankkort1
    },
    {
        id: 2,
        type: "S-banken #1",
        ccno: 5678567856785678,
        accno: "5555 51 23456",
        image:Bankkort2 // Replace with the actual path to the image
    },
    {
        id: 2,
        type: "S-banken #2",
        ccno: 5678567856785678,
        accno: "5555 51 23456",
        image:Bankkort3 // Replace with the actual path to the image
    },
    {
        id: 2,
        type: "Visa Business",
        ccno: 5678567856785678,
        accno: "5555 51 23456",
        image:Bankkort4 // Replace with the actual path to the image
    }
];

function Payments() {
    return (
        <div>
            <Container sx={{ p: 3 }}>
                {/* Card 1: Add/Delete/Modify Card Actions */}
                <Card
                    sx={{
                        display: 'flex',
                        flexDirection: 'row',
                        gap: 1,
                        mb: 2,
                        p: 2,
                        borderRadius: 2,
                        boxShadow: 3,
                    }}
                >
                    <Button variant="contained" color="success">
                        Add new card
                    </Button>
                    <Button variant="contained" color="warning">
                        Delete card
                    </Button>
                    <Button variant="contained" color="secondary">
                        Modify card
                    </Button>
                </Card>

                {/* Card 2: Balance + How to Avoid Charges */}
                <Card
                    sx={{
                        display: 'flex',
                        flexDirection: 'row',
                        alignItems: 'center',
                        justifyContent: 'space-between',
                        mb: 2,
                        p: 2,
                        borderRadius: 2,
                        boxShadow: 3,
                    }}
                >
                    <Typography variant="h6">Balance: -$8.32</Typography>
                    <Button variant="outlined" color="info" sx={{ mt: 1, p: 1 }}>
                        How to avoid getting charged?
                    </Button>
                </Card>

                {/* Cards 3 and Onward: Debit and Credit Cards */}
                {ExampleCards.map((card) => (
                    <Card
                        key={card.id}
                        sx={{
                            display: 'flex',
                            flexDirection: 'row',
                            alignItems: 'flex-start',
                            mb: 2,
                            p: 2,
                            borderRadius: 2,
                            boxShadow: 3,
                        }}
                    >
                        <CardMedia
                            component="img"
                            image={card.image}
                            alt={`${card.type} card`}
                            sx={{
                                width: 100, // Adjust the width as needed
                                height: 'auto',
                                mr: 2, // Add margin to the right of the image
                            }}
                        />

                        {/* CARD TEXT */}
                        <Box>
                        <Typography variant="h6" sx={{ fontWeight: 'bold' }}>
                            {card.type.charAt(0).toUpperCase() + card.type.slice(1)} Card
                        </Typography>
                        <Typography variant="body1">
                            Card Number: **** **** **** {String(card.ccno).slice(-4)}
                        </Typography>
                        <Typography variant="body2" color="textSecondary">
                            Account Number: {card.accno}
                        </Typography>
                        </Box>
                    </Card>
                ))}
            </Container>
        </div>
    );
}

export default Payments;
