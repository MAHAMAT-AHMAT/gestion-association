import api from "../config/axios";

// Interface utilisée pour l'AFFICHAGE (retour du backend)
export type Parent = {
  id?: number;
  nom: string;
  prenom: string;
  profession?: string;
  telephone?: string;
  email?: string;
  lienParente: "PERE" | "MERE" | "SOEUR" | "FRERE" | "AUTRE";
  eleve?: { id: number; nom: string; prenom: string } | null;
};

// Interface utilisée pour l'ENVOI (création / édition)
export type ParentFormData = {
  id?: number;
  nom: string;
  prenom: string;
  profession?: string;
  telephone?: string;
  email?: string;
  lienParente: "PERE" | "MERE" | "SOEUR" | "FRERE" | "AUTRE";
  eleveId?: number; // juste l'ID, suffisant pour le backend
  eleve?: { id: number; nom: string; prenom: string };
};

// Récupérer tous les parents
export const getParents = () => api.get<Parent[]>("/parents");
// Récupérer un parent par ID
export const getParentById = (id: number) => api.get<Parent>(`/parents/${id}`);
// Créer un parent
export const createParent = (data: ParentFormData) =>
  api.post<Parent>("/parents", data);
// Modifier un parent
export const updateParent = (id: number, data: ParentFormData) =>
  api.put<Parent>(`/parents/${id}`, data);
// Supprimer un parent
export const deleteParent = (id: number) => api.delete(`/parents/${id}`);
