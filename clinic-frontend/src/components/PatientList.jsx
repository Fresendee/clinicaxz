import React from 'react';

const PatientList = ({ patients }) => {
    return (
        <ul>
            {patients.map((p) => (
                <li key={p.id}>
                    {p.name} - {p.cpf}
                </li>
            ))}
        </ul>
    );
};

export default PatientList;
