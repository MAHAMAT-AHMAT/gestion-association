// src/api/eleveApi.ts
import api from "../config/axios";

// Types utilisés
export interface Eleve {
  id?: number;
  nom: string;
  prenom: string;
  dateNaissance: string;
  lieuNaissance: string;
  fournisseur?: { id: number } | null;
  niveau?: { id: number } | null;
  annee?: { id: number } | null;
  informationsMedicales?: string;
  photo?: string;
  statut: "ORPHELIN" | "DEMUNIS" | "HANDICAPE" | "AUTRE";
}

export const getEleves = () => api.get<Eleve[]>("/eleves");
export const getEleveById = (id: number) => api.get<Eleve>(`/eleves/${id}`);
export const createEleve = (eleve: Eleve) => api.post<Eleve>("/eleves", eleve);
export const updateEleve = (id: number, eleve: Eleve) => api.put<Eleve>(`/eleves/${id}`, eleve);
export const deleteEleve = (id: number) => api.delete(`/eleves/${id}`);

// Upload photo
export const uploadElevePhoto = async (file: File): Promise<string> => {
  const formData = new FormData();
  formData.append("file", file);
  const res = await api.post("/eleves/upload-photo", formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
  return res.data;
};
