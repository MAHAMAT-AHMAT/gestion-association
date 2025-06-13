// src/pages/Eleves/EleveForm.tsx
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  getEleveById,
  createEleve,
  updateEleve,
  uploadElevePhoto,
  Eleve,
} from "../../api/eleveApi";

const initial: Eleve = {
  nom: "",
  prenom: "",
  dateNaissance: "",
  lieuNaissance: "",
  fournisseur: null,
  niveau: null,
  annee: null,
  informationsMedicales: "",
  photo: "",
  statut: "ORPHELIN",
};

export default function EleveForm() {
  const { id } = useParams<{ id?: string }>();
  const navigate = useNavigate();
  const [eleve, setEleve] = useState<Eleve>(initial);
  const [loading, setLoading] = useState(false);
  const [photoFile, setPhotoFile] = useState<File | null>(null);

  useEffect(() => {
    if (id) {
      setLoading(true);
      getEleveById(Number(id))
        .then((res) => setEleve(res.data))
        .finally(() => setLoading(false));
    }
  }, [id]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setEleve((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      setPhotoFile(e.target.files[0]);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    let photoPath = eleve.photo || "";

    if (photoFile) {
      try {
        photoPath = await uploadElevePhoto(photoFile);
      } catch {
        alert("Erreur lors de l’upload de la photo");
        setLoading(false);
        return;
      }
    }

    const eleveToSave = { ...eleve, photo: photoPath };

    try {
      if (id) {
        await updateEleve(Number(id), eleveToSave);
      } else {
        await createEleve(eleveToSave);
      }
      navigate("/eleves");
    } catch {
      alert("Erreur lors de la sauvegarde");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="p-6 max-w-3xl mx-auto bg-white rounded-lg shadow-md">
      <h1 className="text-3xl font-semibold text-gray-800 mb-6">
        {id ? "Modifier l'élève" : "Ajouter un élève"}
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
                name="nom"
                value={eleve.nom}
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
                value={eleve.prenom}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Date de naissance
              </label>
              <input
                type="date"
                name="dateNaissance"
                value={eleve.dateNaissance}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Lieu de naissance
              </label>
              <input
                name="lieuNaissance"
                value={eleve.lieuNaissance}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Informations médicales
            </label>
            <textarea
              name="informationsMedicales"
              value={eleve.informationsMedicales}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              rows={2}
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Statut
            </label>
            <select
              name="statut"
              value={eleve.statut}
              onChange={handleChange}
              required
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="ORPHELIN">Orphelin</option>
              <option value="DEMUNIS">Démunis</option>
              <option value="HANDICAPE">Handicapé</option>
              <option value="AUTRE">Autre</option>
            </select>
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
            {(photoFile || eleve.photo) && (
              <img
                src={
                  photoFile
                    ? URL.createObjectURL(photoFile)
                    : eleve.photo
                    ? `http://localhost:9000${eleve.photo}`
                    : ""
                }
                alt="Aperçu"
                className="mt-4 w-32 h-32 object-cover rounded-lg border shadow-sm"
              />
            )}
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
              onClick={() => navigate("/eleves")}
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
