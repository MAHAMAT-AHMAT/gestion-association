import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  getUtilisateurById,
  createUtilisateur,
  updateUtilisateur,
  uploadUtilisateurPhoto,
  UtilisateurFormData,
} from "../../../api/utilisateurApi";

const initial: UtilisateurFormData = {
  nomUtilisateur: "",
  motDePasse: "",
  photo: "",
  email: "",
  role: "DONATEUR", // valeur par défaut
};

export default function UtilisateurForm() {
  const { id } = useParams<{ id?: string }>();
  const navigate = useNavigate();
  const [utilisateur, setUtilisateur] = useState<UtilisateurFormData>(initial);
  const [loading, setLoading] = useState(false);
  const [photoFile, setPhotoFile] = useState<File | null>(null);

  useEffect(() => {
    if (id) {
      setLoading(true);
      getUtilisateurById(Number(id))
        .then((res) => {
          const data = res.data;
          setUtilisateur({
            nomUtilisateur: data.nomUtilisateur,
            motDePasse: "",
            photo: data.photo || "",
            email: data.email,
            role: data.role,
          });
        })
        .catch((error) =>
          console.error("Erreur lors du chargement de l'utilisateur :", error)
        )
        .finally(() => setLoading(false));
    }
  }, [id]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setUtilisateur((prev) => ({
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

    let photoPath = utilisateur.photo || "";

    if (photoFile) {
      try {
        const res = await uploadUtilisateurPhoto(photoFile);
        photoPath = res.data;
      } catch (error) {
        console.error("Erreur lors de l'upload de la photo", error);
        alert("Erreur lors de l’upload de la photo");
        setLoading(false);
        return;
      }
    }

    const utilisateurToSave = { ...utilisateur, photo: photoPath };
    try {
      if (id) {
        await updateUtilisateur(Number(id), utilisateurToSave);
      } else {
        await createUtilisateur(utilisateurToSave);
      }
      navigate("/utilisateurs");
    } catch (error) {
      console.error("Erreur lors de la sauvegarde :", error);
      alert("Erreur lors de la sauvegarde de l'utilisateur. Veuillez réessayer.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="p-6 max-w-3xl mx-auto bg-white rounded-lg shadow-md">
      <h1 className="text-3xl font-semibold text-gray-800 mb-6">
        {id ? "Modifier l'utilisateur" : "Ajouter un utilisateur"}
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
                Nom d'utilisateur
              </label>
              <input
                name="nomUtilisateur"
                value={utilisateur.nomUtilisateur}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Mot de passe {id ? "(laisser vide pour ne pas changer)" : ""}
              </label>
              <input
                name="motDePasse"
                type="text"
                value={utilisateur.motDePasse || ""}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                autoComplete="new-password"
                placeholder={id ? "Laisser vide pour conserver l'ancien" : ""}
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Email
              </label>
              <input
                name="email"
                type="email"
                value={utilisateur.email}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
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
              {(photoFile || utilisateur.photo) && (
                <img
                  src={
                    photoFile
                      ? URL.createObjectURL(photoFile)
                      : utilisateur.photo
                      ? utilisateur.photo.startsWith("http")
                        ? utilisateur.photo
                        : `http://localhost:9000${utilisateur.photo}`
                      : ""
                  }
                  alt="Aperçu"
                  className="mt-4 w-32 h-32 object-cover rounded-lg border shadow-sm"
                />
              )}
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Rôle
              </label>
              <select
                name="role"
                value={utilisateur.role}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              >
                <option value="ADMIN">Admin</option>
                <option value="SUPERVISEUR">Superviseur</option>
                <option value="COMPTABLE">Comptable</option>
                <option value="DONATEUR">Donateur</option>
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
              onClick={() => navigate("/utilisateurs")}
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
