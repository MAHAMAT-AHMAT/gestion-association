import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  getTransactionById,
  createTransaction,
  updateTransaction,
  getDons,
  getAnnees,
  Transaction,
  Don,
  AnneeScolaire,
} from "../../api/transactionApi";

const initial: Transaction = {
  don: undefined,
  annee: undefined,
  datePaiement: "", 
  reference: "",
  modePaiement: "VIREMENT",
};

export default function TransactionForm() {
  const { id } = useParams<{ id?: string }>();
  const navigate = useNavigate();

  const [transaction, setTransaction] = useState<Transaction>(initial);
  const [dons, setDons] = useState<Don[]>([]);
  const [annees, setAnnees] = useState<AnneeScolaire[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    getDons().then((res) => setDons(res.data));
    getAnnees().then((res) => setAnnees(res.data));
    if (id) {
      setLoading(true);
      getTransactionById(Number(id))
        .then((res) => {
          const data = res.data;
          setTransaction({
            ...data,
            don: data.don ? { id: data.don.id } : undefined,
            annee: data.annee ? { id: data.annee.id } : undefined,
          });
        })
        .catch((err) => console.error(err))
        .finally(() => setLoading(false));
    }
  }, [id]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setTransaction((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleDonChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setTransaction((prev) => ({
      ...prev,
      don: e.target.value ? { id: Number(e.target.value) } : undefined,
    }));
  };

  const handleAnneeChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setTransaction((prev) => ({
      ...prev,
      annee: e.target.value ? { id: Number(e.target.value) } : undefined,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      if (id) {
        await updateTransaction(Number(id), transaction);
      } else {
        await createTransaction(transaction);
      }
      navigate("/transactions");
    } catch (err) {
      alert("Erreur lors de l'enregistrement");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="p-6 max-w-3xl mx-auto bg-white rounded-lg shadow-md">
      <h1 className="text-3xl font-semibold text-gray-800 mb-6">
        {id ? "Modifier la transaction" : "Ajouter une transaction"}
      </h1>
      {loading && id ? (
        <div className="flex justify-center items-center">
          <div className="animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-blue-500"></div>
          <span className="ml-2 text-gray-600">Chargement...</span>
        </div>
      ) : (
        <form onSubmit={handleSubmit} className="space-y-6">
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Don associé
              </label>
              <select
                name="don"
                value={transaction.don?.id ?? ""}
                onChange={handleDonChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
                <option value="">Sélectionner un don</option>
                {dons.map((d) => (
                  <option key={d.id} value={d.id}>
                    {(d.donateur
                      ? `${d.donateur.nom ?? ""} `.trim()
                      : "Sans nom") +
                      " - " +
                      d.montant +
                      " " +
                      d.devise}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Année scolaire
              </label>
              <select
                name="annee"
                value={transaction.annee?.id ?? ""}
                onChange={handleAnneeChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              >
                <option value="">Sélectionner une année</option>
                {annees.map((a) => (
                  <option key={a.id} value={a.id}>
                    {a.libelle}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Date de paiement
              </label>
              <input
                type="date"
                name="datePaiement"
                value={transaction.datePaiement || ""}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Référence
              </label>
              <input
                name="reference"
                value={transaction.reference || ""}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Mode de paiement
              </label>
              <select
                name="modePaiement"
                value={transaction.modePaiement}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              >
                <option value="VIREMENT">Virement</option>
                <option value="CASH">Cash</option>
                <option value="CHEQUE">Chèque</option>
              </select>
            </div>
          </div>
          <div className="flex gap-4 mt-2">
            <button
              type="submit"
              className="px-6 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
              disabled={loading}
            >
              {id ? "Mettre à jour" : "Enregistrer"}
            </button>
            <button
              type="button"
              className="px-6 py-2 bg-gray-200 text-gray-700 rounded-md hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-offset-2"
              onClick={() => navigate("/transactions")}
              disabled={loading}
            >
              Annuler
            </button>
          </div>
        </form>
      )}
    </div>
  );
}
