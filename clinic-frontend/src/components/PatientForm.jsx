import React, { useState } from 'react';
import { createPatient } from '../api/patientApi';

const PatientForm = ({ onPatientCreated }) => {
    const [name, setName] = useState('');
    const [cpf, setCpf] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const newPatient = await createPatient({ name, cpf });
        if (newPatient) {
            onPatientCreated(newPatient);
            setName('');
            setCpf('');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Nome:</label>
                <input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />
            </div>
            <div>
                <label>CPF:</label>
                <input
                    type="text"
                    value={cpf}
                    onChange={(e) => setCpf(e.target.value)}
                    required
                />
            </div>
            <button type="submit">Adicionar Paciente</button>
        </form>
    );
};

export default PatientForm;
