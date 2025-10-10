import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div>
            <h1>Bem-vindo à Clínica</h1>
            <Link to="/patients">Ver pacientes</Link>
        </div>
    );
};

export default Home;
