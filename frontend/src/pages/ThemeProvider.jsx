import React, { useContext, useMemo } from 'react';
import { ThemeProvider as MuiThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { ThemeContext } from './ThemeContext';

const ThemeProvider = ({ children }) => {
    const { getThemeMode } = useContext(ThemeContext);

    // PACIFIC PUNCH THEME
    const pacificPunchTheme = {
        palette: {
            primary: { main: '#C94B6C' },
            secondary: { main: '#00bcd4' },
            background: { default: '#a69987', paper: '#dbcfbf' },
            text: { primary: '#4a4a4a' },
        },
        typography: {
            fontFamily: 'Roboto, Arial, sans-serif',
        },
    };

    // ANOTHER THEME
    const arcticTheme = {
        palette: {
            primary: { main: '#c4d6d1' },
            secondary: { main: '#DAE2DA' },
            background: { default: '#dae2da', paper: '#efede4' },
            text: { primary: '#1D3557' },
        },
        typography: {
            fontFamily: 'Roboto, Arial, sans-serif',
        },
    };

    const theme = useMemo(() => {
        const mode = getThemeMode();

        if (mode === 'pacificPunch') {
            return createTheme(pacificPunchTheme);
        }

        if (mode === 'arctic') {
            return createTheme(arcticTheme);
        }

        return createTheme({
            palette: {
                mode,
            },
        });
    }, [getThemeMode]);

    return (
        <MuiThemeProvider theme={theme}>
            <CssBaseline />
            {children}
        </MuiThemeProvider>
    );
};

export default ThemeProvider;
