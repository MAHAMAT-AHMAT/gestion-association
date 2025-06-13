import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getTransactions, deleteTransaction, Transaction } from "../../api/transactionApi";

export default function TransactionList() {
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [loading, setLoading] = useState(true);

  const fetchTransactions = async () => {
    setLoading(true);
    try {
      const res = await getTransactions();
      setTransactions(res.data);
    } catch (error) {
      alert("Erreur lors du chargement des transactions");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTransactions();
  }, []);

  const handleDelete = async (id?: number) => {
    if (!id) return;
    if (window.confirm("Supprimer cette transaction ?")) {
      try {
        await deleteTransaction(id);
        await fetchTransactions();
      } catch (error) {
        alert("Erreur lors de la suppression de la transaction");
        console.error(error);
      }
    }
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-semibold text-gray-800">Liste des transactions</h1>
        <Link
          to="/transactions/new"
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
        >
          + Ajouter une transaction
        </Link>
      </div>
      {loading ? (
        <div className="flex justify-center items-center">
          <div className="animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-blue-500"></div>
          <span className="ml-2 text-gray-600">Chargement...</span>
        </div>
      ) : (
        <div className="w-full overflow-x-auto bg-white rounded-lg shadow-md">
          <table className="min-w-full border-collapse border border-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Don</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Donateur</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Année</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Date paiement</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Référence</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Mode</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Actions</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {transactions.map((t) => (
                <tr key={t.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 border border-gray-200 text-sm">
                    {t.don && "montant" in t.don && "devise" in t.don
                      ? `${t.don.montant ?? ""} ${t.don.devise ?? ""}`
                      : "-"}
                  </td>
                  <td className="px-6 py-4 border border-gray-200 text-sm">
                    {t.don && "donateurNom" in t.don
                      ? t.don.donateurNom ?? "-"
                      : "-"}
                  </td>
                  <td className="px-6 py-4 border border-gray-200 text-sm">
                    {t.annee && "libelle" in t.annee
                      ? t.annee.libelle
                      : "-"}
                  </td>
                  <td className="px-6 py-4 border border-gray-200 text-sm">
                    {t.datePaiement}
                  </td>
                  <td className="px-6 py-4 border border-gray-200 text-sm">
                    {t.reference || "-"}
                  </td>
                  <td className="px-6 py-4 border border-gray-200 text-sm">
                    {t.modePaiement}
                  </td>
                  <td className="px-6 py-4 border border-gray-200 text-sm font-medium">
                    <div className="flex space-x-2">
                      <Link
                        to={`/transactions/edit/${t.id}`}
                        className="px-3 py-1 bg-blue-500 text-white rounded-md hover:bg-blue-600"
                      >
                        Modifier
                      </Link>
                      <button
                        className="px-3 py-1 bg-red-500 text-white rounded-md hover:bg-red-600"
                        onClick={() => handleDelete(t.id)}
                      >
                        Supprimer
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
              {transactions.length === 0 && (
                <tr>
                  <td colSpan={7} className="px-6 py-4 text-center text-sm text-gray-500 border border-gray-200">
                    Aucune transaction trouvée.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
