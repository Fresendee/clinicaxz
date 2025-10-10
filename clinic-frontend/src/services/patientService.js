const API_URL = "http://localhost:8080/patients"; // ajuste a rota do backend

export const getPatients = async () => {
    const response = await fetch(API_URL);
    if (!response.ok) throw new Error("Erro ao buscar pacientes");
    return response.json();
};

export const createPatient = async (patient) => {
    const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(patient)
    });
    if (!response.ok) throw new Error("Erro ao criar paciente");
    return response.json();
};
