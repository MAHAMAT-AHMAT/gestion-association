import api from "../config/axios";

export type Fournisseur = {
  id?: number;
  typeFournisseur: "ECOLE" | "SANTE" | "LOISIRS" | "AUTRE";
  nom: string;
  adresse: string;
  telephone: string;
  email: string;
  notes?: string;
};

const URL = "/fournisseurs";

export const getFournisseurs = () => api.get<Fournisseur[]>(URL);

export const addFournisseur = (f: Fournisseur) => api.post(URL, f);

export const updateFournisseur = (f: Fournisseur) => api.put(`${URL}/${f.id}`, f);

export const deleteFournisseur = (id: number) => api.delete(`${URL}/${id}`);

export const getFournisseurById = (id: number) => api.get<Fournisseur>(`${URL}/${id}`);
