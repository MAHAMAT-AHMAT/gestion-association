import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
  getNiveauScolaires,
  deleteNiveauScolaire,
  NiveauScolaire,
} from "../../../api/niveauScolaireApi";

export default function NiveauScolaireList() {
  const [niveaux, setNiveaux] = useState<NiveauScolaire[]>([]);
  const [loading, setLoading] = useState(true);

  const fetchNiveaux = async () => {
    setLoading(true);
    try {
      const response = await getNiveauScolaires();
      setNiveaux(response.data);
    } catch (error) {
      console.error("Erreur lors du chargement :", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchNiveaux();
  }, []);

  const handleDelete = async (id: number) => {
    if (window.confirm("Supprimer ce niveau scolaire ?")) {
      try {
        await deleteNiveauScolaire(id);
        fetchNiveaux();
      } catch (error) {
        console.error("Erreur lors de la suppression :", error);
      }
    }
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-semibold text-gray-800">
          Liste des niveaux scolaires
        </h1>
        <Link
          to="/niveaux-scolaires/new"
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
        >
          + Ajouter un niveau scolaire
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
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b border-gray-200">
                  Nom du niveau
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b border-gray-200">
                  Ordre
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b border-gray-200">
                  Actions
                </th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {niveaux.map((n) => (
                <tr key={n.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {n.nomNiveau}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {n.ordre}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium border border-gray-200">
                    <div className="flex space-x-2">
                      <Link
                        to={`/niveaux-scolaires/edit/${n.id}`}
                        className="px-3 py-1 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
                      >
                        Modifier
                      </Link>
                      <button
                        className="px-3 py-1 bg-red-500 text-white rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
                        onClick={() => handleDelete(n.id!)}
                      >
                        Supprimer
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
              {niveaux.length === 0 && (
                <tr>
                  <td
                    colSpan={3}
                    className="px-6 py-4 text-center text-sm text-gray-500 border border-gray-200"
                  >
                    Aucun niveau scolaire trouvé.
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
