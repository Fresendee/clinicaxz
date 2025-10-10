import React, { useEffect, useState } from 'react';
import { getPatients } from '../api/patientApi';
import PatientList from '../components/PatientList';
import PatientForm from '../components/PatientForm';
import { Link } from 'react-router-dom';

const Patients = () => {
    const [patients, setPatients] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const data = await getPatients();
            setPatients(data);
        };
        fetchData();
    }, []);

    const handlePatientCreated = (newPatient) => {
        setPatients([...patients, newPatient]);
    };

    return (
        <div>
            <h1>Pacientes</h1>
            <PatientForm onPatientCreated={handlePatientCreated} />
            <PatientList patients={patients} />
            <Link to="/">Voltar</Link>
        </div>
    );
};

export default Patients;
