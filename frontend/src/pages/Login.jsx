import Card from '@mui/material/Card';
import {Box, Container, Stack, Button} from '@mui/material';
import {useNavigate} from 'react-router-dom';
import Naglogo from "../assets/Naeg-logo-2.png";

const Login = () => {
    const navigate = useNavigate();

    return (
        <Container style={{textAlign: 'center', marginTop: '100px'}}>
            <h1>Næg - register today</h1>
            <h3>Join the like five people who uses this app</h3>

            <Box>
                <img src={Naglogo} alt="Næg logo" style={{height: 250, padding: '10px'}}/>
            </Box>

            <Stack spacing={2} direction="row" justifyContent="center">

                <Button variant="outlined" size="large" onClick={() => navigate('/register')}>
                    Register</Button>
                <Button variant="contained" size="large" onClick={() => navigate('/LoginForm')}>Log in</Button>
            </Stack>

        </Container>


    );
};

export default Login;