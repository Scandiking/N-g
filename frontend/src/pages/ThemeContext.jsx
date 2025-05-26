import React, { createContext, useState, useEffect, useCallback } from 'react';

export const ThemeContext = createContext();

const ThemeContextProvider = ({ children }) => {
    const [mode, setMode] = useState('auto');

    const getThemeMode = useCallback(() => {
        if (mode === 'auto') {
            return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
        }
        return mode;
    }, [mode]);

    useEffect(() => {
        document.body.setAttribute('data-theme', getThemeMode());
    }, [getThemeMode]);

    return (
        <ThemeContext.Provider value={{ mode, setMode, getThemeMode }}>
            {children}
        </ThemeContext.Provider>
    );
};

export default ThemeContextProvider;
