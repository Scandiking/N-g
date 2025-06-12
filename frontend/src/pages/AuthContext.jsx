// AuthContext.jsx - FIKSET VERSION
import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    // Fetch current user info from backend
    const fetchUserInfo = async () => {
        try {
            const token = localStorage.getItem('token');
            if (!token) {
                setLoading(false);
                return;
            }

            let authHeader = token.startsWith('Bearer ') ? token : `Bearer ${token}`;

            const response = await fetch('http://localhost:8080/api/users/me', {
                headers: {
                    'Authorization': authHeader,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const userData = await response.json();
                setUser(userData);
                setIsAuthenticated(true);
            } else {
                // Token might be invalid
                localStorage.removeItem('token');
                setUser(null);
                setIsAuthenticated(false);
            }
        } catch (error) {
            console.error('Error fetching user info:', error);
            setUser(null);
            setIsAuthenticated(false);
        } finally {
            setLoading(false);
        }
    };

    // ✅ FIKSET: Login function med riktig URL
    const login = async (email, password) => {
        try {
            // ✅ ENDRET FRA /api/auth/signin TIL /login
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                // ✅ ENDRET: Backend forventer {username, password}, ikke {email, password}
                body: JSON.stringify({ username: email, password: password })
            });

            if (response.ok) {
                const data = await response.json();
                // ✅ ENDRET: Backend returnerer 'token', ikke 'jwt'
                localStorage.setItem('token', data.token);
                localStorage.setItem('username', data.username); // Lagre username også
                await fetchUserInfo(); // Fetch user info after successful login
                return { success: true };
            } else {
                const errorData = await response.json();
                return { success: false, error: errorData.error || 'Invalid credentials' };
            }
        } catch (error) {
            console.error('Login error:', error);
            return { success: false, error: 'Network error' };
        }
    };

    // Logout function
    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('username');
        setUser(null);
        setIsAuthenticated(false);
        // Redirect to login page
        window.location.href = '/login';
    };

    // Check for existing token on app start
    useEffect(() => {
        fetchUserInfo();
    }, []);

    const value = {
        user,
        loading,
        isAuthenticated,
        login,
        logout,
        refreshUser: fetchUserInfo
    };

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
};