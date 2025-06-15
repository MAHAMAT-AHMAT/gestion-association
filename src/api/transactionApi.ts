import api from "../config/axios";

// Types de base pour affichage et édition
export type Don = {
  id: number;
  montant: number;
  devise: string;
  donateurNom?: string;
  donateur?: { id: number; nom?: string; prenom?: string }; // <-- Pour accès au nom/prénom
};

export type AnneeScolaire = {
  id: number;
  libelle: string;
};

export type Transaction = {
  id?: number;
  don?: Don | { id: number } | null;
  annee?: AnneeScolaire | { id: number } | null;
  datePaiement: string;
  reference?: string;
  modePaiement: "VIREMENT" | "CASH" | "CHEQUE";
};

export const getTransactions = () => api.get<Transaction[]>("/transactions");
export const getTransactionById = (id: number) => api.get<Transaction>(`/transactions/${id}`);
export const createTransaction = (data: Transaction) => api.post<Transaction>("/transactions", data);
export const updateTransaction = (id: number, data: Transaction) => api.put<Transaction>(`/transactions/${id}`, data);
export const deleteTransaction = (id: number) => api.delete(`/transactions/${id}`);

// Pour les selects :
export const getDons = () => api.get<Don[]>("/dons");
export const getAnnees = () => api.get<AnneeScolaire[]>("/anneeScolaires");
