import api from "../config/axios";

export interface AnneeScolaire {
  id?: number;
  libelle: string;
  dateDebut: string;
  dateFin: string;
  estActive: boolean;
}

export const getAnneeScolaires = () => api.get<AnneeScolaire[]>("/anneeScolaires");
export const getAnneeScolaireById = (id: number) => api.get<AnneeScolaire>(`/anneeScolaires/${id}`);
export const createAnneeScolaire = (anneeScolaire: AnneeScolaire) => api.post("/anneeScolaires", anneeScolaire);
export const updateAnneeScolaire = (id: number, anneeScolaire: AnneeScolaire) => api.put(`/anneeScolaires/${id}`, anneeScolaire);
export const deleteAnneeScolaire = (id: number) => api.delete(`/anneeScolaires/${id}`);