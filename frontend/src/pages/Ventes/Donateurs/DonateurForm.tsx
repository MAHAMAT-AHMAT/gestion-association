import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  getDonateurById,
  createDonateur,
  updateDonateur,
  Donateur,
} from "../../../api/donateurApi";
import api from "../../../config/axios";

const initial: Donateur = {
  nom: "",
  prenom: "",
  telephone: "",
  email: "",
  pays: "",
  nationalite: "",
  numeroCin: "",
  ville: "",
  adresse: "",
  codePostal: "",
  estActif: true,
  typeDonateur: "MORAL",
  typeEntite: "PERSONNE",
  photo: "",
};

export default function DonateurForm() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [donateur, setDonateur] = useState<Donateur>(initial);
  const [loading, setLoading] = useState(false);
  const [photoFile, setPhotoFile] = useState<File | null>(null);

  useEffect(() => {
    if (id) {
      setLoading(true);
      getDonateurById(Number(id))
        .then((res) => setDonateur(res.data))
        .finally(() => setLoading(false));
    }
  }, [id]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value, type } = e.target;
    const checked = (e.target as HTMLInputElement).checked;
    setDonateur((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      setPhotoFile(e.target.files[0]);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    let photoPath = donateur.photo || "";

    if (photoFile) {
      try {
        const formData = new FormData();
        formData.append("file", photoFile);
        const res = await api.post("/donateurs/upload-photo", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        });
        photoPath = res.data;
      } catch (error) {
        console.error("Erreur lors de l'upload de la photo", error);
        alert("Erreur lors de l’upload de la photo");
        return;
      }
    }

    const donateurToSave = { ...donateur, photo: photoPath };
    if (id) {
      await updateDonateur(Number(id), donateurToSave);
    } else {
      await createDonateur(donateurToSave);
    }
    navigate("/donateurs");
  };

  return (
    <div className="p-6 max-w-3xl mx-auto bg-white rounded-lg shadow-md">
      <h1 className="text-3xl font-semibold text-gray-800 mb-6">
        {id ? "Modifier le donateur" : "Ajouter un donateur"}
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
                Nom
              </label>
              <input
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="nom"
                value={donateur.nom}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Prénom
              </label>
              <input
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="prenom"
                value={donateur.prenom}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Téléphone
              </label>
              <input
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="telephone"
                value={donateur.telephone}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Email
              </label>
              <input
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="email"
                type="email"
                value={donateur.email}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Pays
              </label>
              <input
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="pays"
                value={donateur.pays}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Nationalité
              </label>
              <input
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="nationalite"
                value={donateur.nationalite}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                N° CIN
              </label>
              <input
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="numeroCin"
                value={donateur.numeroCin}
                onChange={handleChange}
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Ville
              </label>
              <input
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="ville"
                value={donateur.ville}
                onChange={handleChange}
              />
            </div>
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Adresse
            </label>
            <input
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              name="adresse"
              value={donateur.adresse}
              onChange={handleChange}
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Code postal
            </label>
            <input
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              name="codePostal"
              value={donateur.codePostal}
              onChange={handleChange}
            />
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Type Donateur
              </label>
              <select
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="typeDonateur"
                value={donateur.typeDonateur}
                onChange={handleChange}
                required
              >
                <option value="MORAL">Moral</option>
                <option value="FINANCIER">Financier</option>
                <option value="LES_DEUX">Les deux</option>
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Type Entité
              </label>
              <select
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                name="typeEntite"
                value={donateur.typeEntite}
                onChange={handleChange}
                required
              >
                <option value="PERSONNE">Personne</option>
                <option value="ENTREPRISE">Entreprise</option>
                <option value="AUTRE">Autre</option>
              </select>
            </div>
          </div>
          <div>
            <label className="inline-flex items-center">
              <input
                type="checkbox"
                name="estActif"
                checked={donateur.estActif}
                onChange={handleChange}
                className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
              />
              <span className="ml-2 text-sm text-gray-700">Actif</span>
            </label>
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Photo
            </label>
            <input
              type="file"
              accept="image/*"
              onChange={handleFileChange}
              className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-md file:border-0 file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"
            />
            {(photoFile || donateur.photo) && (
              <img
                src={
                  photoFile
                    ? URL.createObjectURL(photoFile)
                    : donateur.photo
                    ? `http://localhost:9000${donateur.photo}`
                    : ""
                }
                alt="Aperçu"
                className="mt-4 w-32 h-32 object-cover rounded-lg border shadow-sm"
              />
            )}
          </div>
          <div className="flex gap-4">
            <button
              type="submit"
              className="px-6 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
            >
              Enregistrer
            </button>
            <button
              type="button"
              className="px-6 py-2 bg-gray-200 text-gray-700 rounded-md hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-offset-2"
              onClick={() => navigate("/donateurs")}
            >
              Annuler
            </button>
          </div>
        </form>
      )}
    </div>
  );
}