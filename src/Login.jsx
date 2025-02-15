import Card from '@mui/material/Card';
import { Container, Stack, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const navigate = useNavigate();

    return (
            <Container style={{ textAlign: 'center', marginTop: '100px' }}>
                <h1>Næg - register today</h1>
                <h3>Join the like five people who uses this app</h3>

                <Stack spacing={2} direction="row" justifyContent="center">
                    <Button variant="outlined" size="large" onClick={() => navigate('/register')}>
                        Register</Button>
                    <Button variant="contained" size="large">Log in</Button>
                </Stack>

            </Container>



    );
};

export default Login;