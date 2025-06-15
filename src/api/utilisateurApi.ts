import api from "../config/axios";

// Pour l'affichage (GET)
export type Utilisateur = {
  id?: number;
  nomUtilisateur: string;
  motDePasse?: string;
  photo?: string;
  email: string;
  dateCreation?: string;
  derniereConnexion?: string;
  role: "ADMIN" | "SUPERVISEUR" | "COMPTABLE" | "DONATEUR";
};

// Pour l’envoi (POST/PUT)
export type UtilisateurFormData = {
  nomUtilisateur: string;
  motDePasse?: string;
  photo?: string;
  email: string;
  role: "ADMIN" | "SUPERVISEUR" | "COMPTABLE" | "DONATEUR";
};

// API CRUD
export const getUtilisateurs = () => api.get<Utilisateur[]>("/utilisateurs");
export const getUtilisateurById = (id: number) => api.get<Utilisateur>(`/utilisateurs/${id}`);
export const createUtilisateur = (data: UtilisateurFormData) => api.post<Utilisateur>("/utilisateurs", data);
export const updateUtilisateur = (id: number, data: UtilisateurFormData) => api.put<Utilisateur>(`/utilisateurs/${id}`, data);
export const deleteUtilisateur = (id: number) => api.delete(`/utilisateurs/${id}`);

// Pour upload photo
export const uploadUtilisateurPhoto = (file: File) => {
  const formData = new FormData();
  formData.append("file", file);
  return api.post<string>("/utilisateurs/upload-photo", formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};
