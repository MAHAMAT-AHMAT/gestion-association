import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
  getDons,
  deleteDon,
  Don,
  getDonateurs,
  getAnneeScolaires,
} from "../../../api/donApi";
import { useSearch } from "../../../context/SearchContext";

export default function DonList() {
  const [dons, setDons] = useState<Don[]>([]);
  const [donateurs, setDonateurs] = useState<
    { id: number; nom?: string; prenom?: string }[]
  >([]);
  const [annees, setAnnees] = useState<{ id: number; libelle?: string }[]>([]);
  const [loading, setLoading] = useState(true);
  const { search } = useSearch();

  const fetchAll = async () => {
    setLoading(true);
    try {
      const [resDons, resDonateurs, resAnnees] = await Promise.all([
        getDons(),
        getDonateurs(),
        getAnneeScolaires(),
      ]);
      setDons(resDons.data);
      setDonateurs(resDonateurs.data);
      setAnnees(resAnnees.data);
    } catch (error) {
      console.error("Erreur lors du chargement :", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAll();
  }, []);

  const getDonateurLabel = (donateurObj?: {
    id?: number;
    nom?: string;
    prenom?: string;
  }) => {
    if (!donateurObj?.id) return "";
    const donor = donateurs.find((d) => d.id === donateurObj.id);
    return donor
      ? `${donor.nom || ""} ${donor.prenom || ""}`.trim()
      : "Inconnu";
  };

  const getAnneeLabel = (anneeObj?: { id?: number; libelle?: string }) => {
    if (!anneeObj?.id) return "";
    if (anneeObj.libelle) return anneeObj.libelle;
    const a = annees.find((aa) => aa.id === anneeObj.id);
    return a ? a.libelle : "";
  };

  const handleDelete = async (id: number) => {
    if (window.confirm("Supprimer ce don ?")) {
      try {
        await deleteDon(id);
        fetchAll();
      } catch (error) {
        console.error("Erreur lors de la suppression :", error);
      }
    }
  };

  // Filter dons by donateur nom/prenom, montant, or statut
  const filteredDons = dons.filter((don) => {
    const donateurLabel = getDonateurLabel(don.donateur).toLowerCase();
    return search
      ? donateurLabel.startsWith(search.toLowerCase()) ||
          String(don.montant).startsWith(search) ||
          don.statut.toLowerCase().startsWith(search.toLowerCase())
      : true;
  });

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-semibold text-gray-800">Liste des dons</h1>
        <Link
          to="/dons/new"
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
        >
          + Ajouter un don
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
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">
                  Donateur
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">
                  Année scolaire
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">
                  Montant
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">
                  Devise
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">
                  Date
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">
                  Récurrent
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">
                  Statut
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">
                  Actions
                </th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {filteredDons.map((don) => (
                <tr key={don.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {getDonateurLabel(don.donateur)}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {getAnneeLabel(don.annee)}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {don.montant}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {don.devise}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {don.date}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {don.estRecurrent ? "Oui" : "Non"}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {don.statut === "PROMIS" && (
                      <span className="inline-block px-2 py-1 text-xs font-semibold bg-yellow-100 text-yellow-800 rounded">
                        Promis
                      </span>
                    )}
                    {don.statut === "RECU" && (
                      <span className="inline-block px-2 py-1 text-xs font-semibold bg-green-100 text-green-800 rounded">
                        Reçu
                      </span>
                    )}
                    {don.statut === "AFFECTE" && (
                      <span className="inline-block px-2 py-1 text-xs font-semibold bg-blue-100 text-blue-800 rounded">
                        Affecté
                      </span>
                    )}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium border border-gray-200">
                    <div className="flex space-x-2">
                      <Link
                        to={`/dons/edit/${don.id}`}
                        className="px-3 py-1 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
                      >
                        Modifier
                      </Link>
                      <button
                        className="px-3 py-1 bg-red-500 text-white rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
                        onClick={() => handleDelete(don.id!)}
                      >
                        Supprimer
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
              {filteredDons.length === 0 && (
                <tr>
                  <td
                    colSpan={8}
                    className="px-6 py-4 text-center text-sm text-gray-500 border border-gray-200"
                  >
                    Aucun don trouvé.
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
