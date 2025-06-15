import api from "../config/axios";

export interface NiveauScolaire {
  id?: number;
  nomNiveau: string;
  ordre: number;
}

export const getNiveauScolaires = () => api.get<NiveauScolaire[]>("/niveauScolaires");
export const getNiveauScolaireById = (id: number) => api.get<NiveauScolaire>(`/niveauScolaires/${id}`);
export const createNiveauScolaire = (n: NiveauScolaire) => api.post("/niveauScolaires", n);
export const updateNiveauScolaire = (id: number, n: NiveauScolaire) => api.put(`/niveauScolaires/${id}`, n);
export const deleteNiveauScolaire = (id: number) => api.delete(`/niveauScolaires/${id}`);
