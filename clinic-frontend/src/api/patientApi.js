import axios from 'axios';

const API_URL = 'http://localhost:8080'; // backend Spring Boot

export const getPatients = async () => {
    try {
        const response = await axios.get(`${API_URL}/patients`);
        return response.data;
    } catch (error) {
        console.error('Erro ao buscar pacientes', error);
        return [];
    }
};

export const createPatient = async (patient) => {
    try {
        const response = await axios.post(`${API_URL}/patients`, patient);
        return response.data;
    } catch (error) {
        console.error('Erro ao criar paciente', error);
    }
};
