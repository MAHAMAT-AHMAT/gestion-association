import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  getParentById,
  createParent,
  updateParent,
} from "../../../api/parentApi";
import { getEleves, Eleve } from "../../../api/eleveApi";

// Typage pour la saisie (POST/PUT)
type ParentFormData = {
  nom: string;
  prenom: string;
  profession?: string;
  telephone?: string;
  email?: string;
  lienParente: "PERE" | "MERE" | "SOEUR" | "FRERE" | "AUTRE";
  eleveId?: number;
};

const initial: ParentFormData = {
  nom: "",
  prenom: "",
  profession: "",
  telephone: "",
  email: "",
  lienParente: "PERE",
  eleveId: undefined,
};

export default function ParentForm() {
  const { id } = useParams<{ id?: string }>();
  const navigate = useNavigate();
  const [parent, setParent] = useState<ParentFormData>(initial);
  const [loading, setLoading] = useState(false);
  const [eleves, setEleves] = useState<Eleve[]>([]);

  useEffect(() => {
    getEleves()
      .then((res) => setEleves(res.data))
      .catch((error) =>
        console.error("Erreur lors du chargement des élèves :", error)
      );
    if (id) {
      setLoading(true);
      getParentById(Number(id))
        .then((res) => {
          const data = res.data;
          setParent({
            nom: data.nom,
            prenom: data.prenom,
            profession: data.profession,
            telephone: data.telephone,
            email: data.email,
            lienParente: data.lienParente,
            eleveId: data.eleve?.id,
          });
        })
        .catch((error) =>
          console.error("Erreur lors du chargement du parent :", error)
        )
        .finally(() => setLoading(false));
    }
  }, [id]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setParent((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleEleveChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const eleveId = Number(e.target.value) || undefined;
    setParent((prev) => ({
      ...prev,
      eleveId,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      if (id) {
        await updateParent(Number(id), parent);
      } else {
        await createParent(parent);
      }
      navigate("/parents");
    } catch (error) {
      console.error("Erreur lors de la sauvegarde :", error);
      alert("Erreur lors de la sauvegarde du parent. Veuillez réessayer.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="p-6 max-w-3xl mx-auto bg-white rounded-lg shadow-md">
      <h1 className="text-3xl font-semibold text-gray-800 mb-6">
        {id ? "Modifier le parent" : "Ajouter un parent"}
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
                Nom
              </label>
              <input
                name="nom"
                value={parent.nom}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Prénom
              </label>
              <input
                name="prenom"
                value={parent.prenom}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Profession
              </label>
              <input
                name="profession"
                value={parent.profession || ""}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Téléphone
              </label>
              <input
                name="telephone"
                value={parent.telephone || ""}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Email
              </label>
              <input
                name="email"
                type="email"
                value={parent.email || ""}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Lien de parenté
              </label>
              <select
                name="lienParente"
                value={parent.lienParente}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              >
                <option value="PERE">Père</option>
                <option value="MERE">Mère</option>
                <option value="SOEUR">Soeur</option>
                <option value="FRERE">Frère</option>
                <option value="AUTRE">Autre</option>
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Élève associé
              </label>
              <select
                name="eleveId"
                value={parent.eleveId ?? ""}
                onChange={handleEleveChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              >
                <option value="">Sélectionner un élève</option>
                {eleves.map((el) => (
                  <option key={el.id} value={el.id}>
                    {el.nom} {el.prenom}
                  </option>
                ))}
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
              onClick={() => navigate("/parents")}
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
