import { useEffect, useState } from "react";
import { getPatients } from "../services/patientService";

function Patients() {
    const [patients, setPatients] = useState([]);

    useEffect(() => {
        getPatients().then(data => setPatients(data));
    }, []);

    return (
        <div>
            <h1>Pacientes</h1>
            <ul>
                {patients.map(p => (
                    <li key={p.id}>{p.name} - {p.cpf}</li>
                ))}
            </ul>
        </div>
    );
}

export default Patients;
