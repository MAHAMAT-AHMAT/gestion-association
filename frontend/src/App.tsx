import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import NotFound from "./pages/OtherPage/NotFound";
import AppLayout from "./layout/AppLayout";
import { ScrollToTop } from "./components/common/ScrollToTop";
import Home from "./pages/Dashboard/Home";

// Imports automatiques des pages
import UtilisateurList from "./pages/Parametres/Utilisateurs/UtilisateurList";
import UtilisateurForm from "./pages/Parametres/Utilisateurs/UtilisateurForm";

import TransactionList from "./pages/Transactions/TransactionList";
import TransactionForm from "./pages/Transactions/TransactionForm";

import ParentList from "./pages/Parametres/Parents/ParentList";
import ParentForm from "./pages/Parametres/Parents/ParentForm";

import NiveauScolaireList from "./pages/Parametres/NiveauScolaires/NiveauScolaireList";
import NiveauScolaireForm from "./pages/Parametres/NiveauScolaires/NiveauScolaireForm";

import FournisseurList from "./pages/Achats/Fournisseurs/FournisseurList";
import FournisseurForm from "./pages/Achats/Fournisseurs/FournisseurForm";

import FactureList from "./pages/Ventes/Factures/FactureList";
import FactureForm from "./pages/Ventes/Factures/FactureForm";

import EleveList from "./pages/Eleves/EleveList";
import EleveForm from "./pages/Eleves/EleveForm";

import DonList from "./pages/Parametres/Dons/DonList";
import DonForm from "./pages/Parametres/Dons/DonForm";

import DonateurList from "./pages/Ventes/Donateurs/DonateurList";
import DonateurForm from "./pages/Ventes/Donateurs/DonateurForm";

import DocumentList from "./pages/Documents/DocumentList";
import DocumentForm from "./pages/Documents/DocumentForm";

import DepenseList from "./pages/Achats/Depenses/DepenseList";
import DepenseForm from "./pages/Achats/Depenses/DepenseForm";

import BourseList from "./pages/Parametres/Bourses/BourseList";
import BourseForm from "./pages/Parametres/Bourses/BourseForm";

import AnneeScolaireList from "./pages/Parametres/AnneeScolaires/AnneeScolaireList";
import AnneeScolaireForm from "./pages/Parametres/AnneeScolaires/AnneeScolaireForm";

export default function App() {
  return (
    <Router>
      <ScrollToTop />
      <Routes>
        <Route element={<AppLayout />}>
          <Route index path="/" element={<Home />} />

          {/* Routes Générées */}
          <Route path="/utilisateurs" element={<UtilisateurList />} />
          <Route path="/utilisateurs/new" element={<UtilisateurForm />} />
          <Route path="/utilisateurs/edit/:id" element={<UtilisateurForm />} />

          <Route path="/transactions" element={<TransactionList />} />
          <Route path="/transactions/new" element={<TransactionForm />} />
          <Route path="/transactions/edit/:id" element={<TransactionForm />} />

          <Route path="/parents" element={<ParentList />} />
          <Route path="/parents/new" element={<ParentForm />} />
          <Route path="/parents/edit/:id" element={<ParentForm />} />

          <Route path="/niveaux-scolaires" element={<NiveauScolaireList />} />
          <Route
            path="/niveaux-scolaires/new"
            element={<NiveauScolaireForm />}
          />
          <Route
            path="/niveaux-scolaires/edit/:id"
            element={<NiveauScolaireForm />}
          />

          <Route path="/fournisseurs" element={<FournisseurList />} />
          <Route path="/fournisseurs/new" element={<FournisseurForm />} />
          <Route path="/fournisseurs/edit/:id" element={<FournisseurForm />} />

          <Route path="/factures" element={<FactureList />} />
          <Route path="/factures/new" element={<FactureForm />} />
          <Route path="/factures/edit/:id" element={<FactureForm />} />

          <Route path="/eleves" element={<EleveList />} />
          <Route path="/eleves/new" element={<EleveForm />} />
          <Route path="/eleves/edit/:id" element={<EleveForm />} />

          <Route path="/dons" element={<DonList />} />
          <Route path="/dons/new" element={<DonForm />} />
          <Route path="/dons/edit/:id" element={<DonForm />} />

          <Route path="/donateurs" element={<DonateurList />} />
          <Route path="/donateurs/new" element={<DonateurForm />} />
          <Route path="/donateurs/edit/:id" element={<DonateurForm />} />

          <Route path="/documents" element={<DocumentList />} />
          <Route path="/documents/new" element={<DocumentForm />} />
          <Route path="/documents/edit/:id" element={<DocumentForm />} />

          <Route path="/depenses" element={<DepenseList />} />
          <Route path="/depenses/new" element={<DepenseForm />} />
          <Route path="/depenses/edit/:id" element={<DepenseForm />} />

          <Route path="/bourses" element={<BourseList />} />
          <Route path="/bourses/new" element={<BourseForm />} />
          <Route path="/bourses/edit/:id" element={<BourseForm />} />

          <Route path="/annees-scolaires" element={<AnneeScolaireList />} />
          <Route path="/annees-scolaires/new" element={<AnneeScolaireForm />} />
          <Route
            path="/annees-scolaires/edit/:id"
            element={<AnneeScolaireForm />}
          />
        </Route>

        {/* Fallback 404 */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}
