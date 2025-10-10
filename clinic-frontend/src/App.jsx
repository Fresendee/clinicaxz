import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Patients from './pages/Patients';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/patients" element={<Patients />} />
            </Routes>
        </Router>
    );
}

export default App;
