import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  createDon,
  updateDon,
  getDonById,
  Don,
  getDonateurs,
  getAnneeScolaires,
} from "../../../api/donApi";

const initial: Don = {
  montant: 0,
  devise: "MAD",
  date: "",
  description: "",
  estRecurrent: false,
  statut: "PROMIS",
  donateur: undefined,
  annee: undefined,
};

export default function DonForm() {
  const { id } = useParams<{ id?: string }>();
  const navigate = useNavigate();
  const [don, setDon] = useState<Don>(initial);
  const [donateurs, setDonateurs] = useState<
    { id: number; nom?: string; prenom?: string }[]
  >([]);
  const [annees, setAnnees] = useState<{ id: number; libelle?: string }[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    Promise.all([
      getDonateurs().then((res) => setDonateurs(res.data)),
      getAnneeScolaires().then((res) => setAnnees(res.data)),
      id
        ? getDonById(Number(id)).then((res) => {
            setDon({
              ...res.data,
              donateur: res.data.donateur
                ? { id: res.data.donateur.id }
                : undefined,
              annee: res.data.annee ? { id: res.data.annee.id } : undefined,
              estRecurrent: !!res.data.estRecurrent,
            });
          })
        : Promise.resolve(),
    ]).finally(() => setLoading(false));
  }, [id]);

  const handleChange = (
    e: React.ChangeEvent<
      HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement
    >
  ) => {
    const { name, value, type } = e.target;
    if (name === "donateur" || name === "annee") {
      setDon((prev) => ({
        ...prev,
        [name]: value ? { id: Number(value) } : undefined,
      }));
    } else if (name === "montant") {
      setDon((prev) => ({
        ...prev,
        montant: Number(value),
      }));
    } else if (type === "checkbox") {
      const target = e.target as HTMLInputElement; // Cast to HTMLInputElement for checked
      setDon((prev) => ({
        ...prev,
        [name]: target.checked,
      }));
    } else {
      setDon((prev) => ({
        ...prev,
        [name]: value,
      }));
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      if (id) {
        await updateDon(Number(id), don);
      } else {
        await createDon(don);
      }
      navigate("/dons");
    } catch (error) {
      alert("Erreur lors de la sauvegarde");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="p-6 max-w-3xl mx-auto bg-white rounded-lg shadow-md">
      <h1 className="text-3xl font-semibold text-gray-800 mb-6">
        {id ? "Modifier le don" : "Ajouter un don"}
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
                Donateur
              </label>
              <select
                name="donateur"
                value={don.donateur?.id || ""}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              >
                <option value="">-- Choisir --</option>
                {donateurs.map((d) => (
                  <option key={d.id} value={d.id}>
                    {d.nom} {d.prenom}
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
                value={don.annee?.id || ""}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              >
                <option value="">-- Choisir --</option>
                {annees.map((a) => (
                  <option key={a.id} value={a.id}>
                    {a.libelle}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Montant
              </label>
              <input
                type="number"
                name="montant"
                value={don.montant}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
                min={0}
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Devise
              </label>
              <select
                name="devise"
                value={don.devise}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
                <option value="MAD">MAD</option>
                <option value="EUR">EUR</option>
                <option value="USD">USD</option>
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Date
              </label>
              <input
                type="date"
                name="date"
                value={don.date}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              />
            </div>
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Description
            </label>
            <textarea
              name="description"
              value={don.description}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              rows={2}
            />
          </div>
          <div className="flex items-center gap-6">
            <label className="flex items-center">
              <input
                type="checkbox"
                name="estRecurrent"
                checked={don.estRecurrent}
                onChange={handleChange}
                className="h-4 w-4 text-blue-600 border-gray-300 rounded"
              />
              <span className="ml-2 text-sm text-gray-700">Récurrent</span>
            </label>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Statut
              </label>
              <select
                name="statut"
                value={don.statut}
                onChange={handleChange}
                className="px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
                <option value="PROMIS">Promis</option>
                <option value="RECU">Reçu</option>
                <option value="AFFECTE">Affecté</option>
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
              onClick={() => navigate("/dons")}
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
