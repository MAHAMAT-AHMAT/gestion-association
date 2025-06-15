import api from "../config/axios";

export interface Donateur {
  id?: number;
  nom: string;
  prenom: string;
  telephone: string;
  email: string;
  pays: string;
  nationalite: string;
  numeroCin: string;
  photo?: string;
  ville: string;
  adresse: string;
  codePostal: string;
  dateCreation?: string;
  dateModification?: string;
  estActif: boolean;
  typeDonateur: "MORAL" | "FINANCIER" | "LES_DEUX";
  typeEntite: "PERSONNE" | "ENTREPRISE" | "AUTRE";
}

export const getDonateurs = () => api.get<Donateur[]>("/donateurs");
export const getDonateurById = (id: number) => api.get<Donateur>(`/donateurs/${id}`);
export const createDonateur = (donateur: Donateur) => api.post("/donateurs", donateur);
export const updateDonateur = (id: number, donateur: Donateur) => api.put(`/donateurs/${id}`, donateur);
export const deleteDonateur = (id: number) => api.delete(`/donateurs/${id}`);
// ---- Ce que tu dois AJOUTER ----
export const uploadPhotoDonateur = (file: File) => {
  const formData = new FormData();
  formData.append("file", file);
  return api.post<string>("/donateurs/upload-photo", formData, {
    headers: { "Content-Type": "multipart/form-data" }
  });
};