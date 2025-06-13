import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
  getUtilisateurs,
  deleteUtilisateur,
  Utilisateur,
} from "../../../api/utilisateurApi";

export default function UtilisateurList() {
  const [utilisateurs, setUtilisateurs] = useState<Utilisateur[]>([]);
  const [loading, setLoading] = useState(true);

  const fetchUtilisateurs = async () => {
    setLoading(true);
    try {
      const res = await getUtilisateurs();
      setUtilisateurs(res.data);
    } catch (error) {
      console.error("Erreur lors du chargement des utilisateurs :", error);
      alert("Erreur lors du chargement des utilisateurs. Veuillez réessayer.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUtilisateurs();
  }, []);

  const handleDelete = async (id?: number) => {
    if (!id) return;
    if (window.confirm("Supprimer cet utilisateur ?")) {
      try {
        await deleteUtilisateur(id);
        await fetchUtilisateurs();
      } catch (error) {
        console.error("Erreur lors de la suppression :", error);
        alert(
          "Erreur lors de la suppression de l'utilisateur. Veuillez réessayer."
        );
      }
    }
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-semibold text-gray-800">
          Liste des utilisateurs
        </h1>
        <Link
          to="/utilisateurs/new"
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
        >
          + Ajouter un utilisateur
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
                  Nom utilisateur
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b border-gray-200">
                  Mot de passe
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b border-gray-200">
                  Email
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b border-gray-200">
                  Rôle
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b border-gray-200">
                  Photo
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b border-gray-200">
                  Actions
                </th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {utilisateurs.map((user) => (
                <tr key={user.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {user.nomUtilisateur}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {user.motDePasse || "-"}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {user.email}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {user.role}
                  </td>
    
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900 border border-gray-200">
                    {user.photo &&
                    typeof user.photo === "string" &&
                    user.photo.trim() !== "" ? (
                      <img
                        src={
                          user.photo.startsWith("http")
                            ? user.photo
                            : `http://localhost:9000${
                                user.photo.startsWith("/") ? "" : "/"
                              }${user.photo}`
                        }
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
                        to={`/utilisateurs/edit/${user.id}`}
                        className="px-3 py-1 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
                      >
                        Modifier
                      </Link>
                      <button
                        className="px-3 py-1 bg-red-500 text-white rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
                        onClick={() => handleDelete(user.id)}
                      >
                        Supprimer
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
              {utilisateurs.length === 0 && (
                <tr>
                  <td
                    colSpan={8}
                    className="px-6 py-4 text-center text-sm text-gray-500 border border-gray-200"
                  >
                    Aucun utilisateur trouvé.
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
