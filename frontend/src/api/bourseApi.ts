import api from "../config/axios";

export type Bourse = {
  id?: number;
  montant: number;
  dateDebut: string;
  dateFin: string;
  notes?: string;
  statut: "ACTIVE" | "TERMINEE" | "SUSPENDUE";
  frequence: "MENSUELLE" | "TRIMESTRIELLE" | "ANNUELLE";
  eleve?: { id: number; nom: string; prenom: string } | null;
  don?: { id: number; montant: number; devise: string; donateurNom: string } | null;
  annee?: { id: number; libelle: string } | null;
};

export type BourseFormData = {
  id?: number;
  montant: number;
  dateDebut: string;
  dateFin: string;
  notes?: string;
  statut: "ACTIVE" | "TERMINEE" | "SUSPENDUE";
  frequence: "MENSUELLE" | "TRIMESTRIELLE" | "ANNUELLE";
  eleveId?: number;
  donId?: number;
  anneeId?: number;
};

export const getBourses = () => api.get<Bourse[]>("/bourses");
export const getBourseById = (id: number) => api.get<Bourse>(`/bourses/${id}`);
export const createBourse = (data: BourseFormData) => api.post<Bourse>("/bourses", data);
export const updateBourse = (id: number, data: BourseFormData) => api.put<Bourse>(`/bourses/${id}`, data);
export const deleteBourse = (id: number) => api.delete(`/bourses/${id}`);

// For selects
export const getEleves = () => api.get<{ id: number; nom: string; prenom: string }[]>("/eleves");
export const getDons = () => api.get<{ id: number; montant: number; devise: string; donateurNom: string }[]>("/dons");
export const getAnnees = () => api.get<{ id: number; libelle: string }[]>("/anneeScolaires");