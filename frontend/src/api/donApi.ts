import api from "../config/axios";

export type Don = {
  id?: number;
  donateur?: { id: number; nom?: string; prenom?: string };
  annee?: { id: number; libelle?: string };
  montant: number;
  devise: string;
  date: string;
  description?: string;
  estRecurrent: boolean;
  statut: "PROMIS" | "RECU" | "AFFECTE";
};

export const getDons = () => api.get("/dons");
export const getDonById = (id: number) => api.get(`/dons/${id}`);
export const createDon = (data: Don) => api.post("/dons", data);
export const updateDon = (id: number, data: Don) => api.put(`/dons/${id}`, data);
export const deleteDon = (id: number) => api.delete(`/dons/${id}`);

export const getDonateurs = () => api.get("/donateurs");
export const getAnneeScolaires = () => api.get("/anneeScolaires");