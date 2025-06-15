import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  createNiveauScolaire,
  updateNiveauScolaire,
  getNiveauScolaireById,
  NiveauScolaire,
} from "../../../api/niveauScolaireApi";

const initial: NiveauScolaire = {
  nomNiveau: "",
  ordre: 0,
};

export default function NiveauScolaireForm() {
  const { id } = useParams<{ id?: string }>();
  const navigate = useNavigate();
  const [niveau, setNiveau] = useState<NiveauScolaire>(initial);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (id) {
      setLoading(true);
      getNiveauScolaireById(Number(id))
        .then((res) => setNiveau(res.data))
        .finally(() => setLoading(false));
    }
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value, type } = e.target;
    setNiveau((prev) => ({
      ...prev,
      [name]: type === "number" ? Number(value) : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (id) {
      await updateNiveauScolaire(Number(id), niveau);
    } else {
      await createNiveauScolaire(niveau);
    }
    navigate("/niveaux-scolaires");
  };

  return (
    <div className="p-6 max-w-3xl mx-auto bg-white rounded-lg shadow-md">
      <h1 className="text-3xl font-semibold text-gray-800 mb-6">
        {id ? "Modifier le niveau scolaire" : "Ajouter un niveau scolaire"}
      </h1>
      {loading ? (
        <div className="flex justify-center items-center">
          <div className="animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-blue-500"></div>
          <span className="ml-2 text-gray-600">Chargement...</span>
        </div>
      ) : (
        <form onSubmit={handleSubmit} className="space-y-6">
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Nom du niveau
              </label>
              <input
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="nomNiveau"
                value={niveau.nomNiveau}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Ordre d’affichage
              </label>
              <input
                type="number"
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="ordre"
                value={niveau.ordre}
                onChange={handleChange}
                min={0}
                required
              />
            </div>
          </div>
          <div className="flex gap-4">
            <button
              type="submit"
              className="px-6 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
            >
              {id ? "Mettre à jour" : "Enregistrer"}
            </button>
            <button
              type="button"
              className="px-6 py-2 bg-gray-200 text-gray-700 rounded-md hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-offset-2"
              onClick={() => navigate("/niveaux-scolaires")}
            >
              Annuler
            </button>
          </div>
        </form>
      )}
    </div>
  );
}
