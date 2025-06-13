import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getEleves, deleteEleve, Eleve } from "../../api/eleveApi";

export default function EleveList() {
  const [eleves, setEleves] = useState<Eleve[]>([]);
  const [loading, setLoading] = useState(true);

  const fetchEleves = async () => {
    setLoading(true);
    try {
      const res = await getEleves();
      setEleves(res.data);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchEleves();
  }, []);

  const handleDelete = async (id?: number) => {
    if (!id) return;
    if (window.confirm("Supprimer cet élève ?")) {
      await deleteEleve(id);
      fetchEleves();
    }
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-semibold text-gray-800">Liste des élèves</h1>
        <Link
          to="/eleves/new"
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
        >
          + Ajouter un élève
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
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Nom</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Prénom</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Date Naissance</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Statut</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Photo</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase border-b border-gray-200">Actions</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {eleves.map((eleve) => (
                <tr key={eleve.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">{eleve.nom}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">{eleve.prenom}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">{eleve.dateNaissance}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">{eleve.statut}</td>
                  <td className="px-6 py-4 whitespace-nowrap border border-gray-200">
                    {eleve.photo ? (
                      <img
                        src={`http://localhost:9000${eleve.photo}`}
                        alt="photo"
                        className="w-12 h-12 rounded-full object-cover"
                      />
                    ) : (
                      <span className="text-gray-400">Aucune</span>
                    )}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium border border-gray-200">
                    <div className="flex space-x-2">
                      <Link
                        to={`/eleves/edit/${eleve.id}`}
                        className="px-3 py-1 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
                      >
                        Modifier
                      </Link>
                      <button
                        className="px-3 py-1 bg-red-500 text-white rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
                        onClick={() => handleDelete(eleve.id)}
                      >
                        Supprimer
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
              {eleves.length === 0 && (
                <tr>
                  <td
                    colSpan={6}
                    className="px-6 py-4 text-center text-sm text-gray-500 border border-gray-200"
                  >
                    Aucun élève trouvé.
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
