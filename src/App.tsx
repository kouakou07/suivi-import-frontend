import { useState } from 'react'
import reactLogo from './assets/react.svg'
import Layout from './pages/template/Layout';
import SideBar from './pages/template/SideBar';
import TopBar from './pages/template/TopBar';
import { Route, Routes } from 'react-router-dom';
import BankRoute from './pages/app/bank/BankRoute';
import Login from './pages/app/generic/Login';
import ProtectedRoute from './pages/app/generic/ProtectedRoute';
import Index from './pages/app/generic/Index';
import Profil from './pages/app/generic/Profil';
import Disconnect from './pages/app/generic/Disconnect';
import EcobankUser from './pages/app/bank/admin/EcobankUsers';
import EcobankUserCreation from './pages/app/bank/admin/EcobankUserCreation';
import EcobankUserInfo from './pages/app/bank/admin/EcobankUserInfo';
import Agence from './pages/app/bank/agence/Agence';
import AgenceCreation from './pages/app/bank/agence/AgenceCreation';
import UserAgence from './pages/app/bank/agence/UserAgence';
import CreateUserAgence from './pages/app/bank/agence/CreateUserAgence';
import InfoUserAgence from './pages/app/bank/agence/InfoUserAgence';
import BorneAgence from './pages/app/bank/agence/BorneAgence';
import AddBorneAgence from './pages/app/bank/agence/AddBorneAgence';
import InfoBorne from './pages/app/bank/agence/InfoBorne';
import CompteAgence from './pages/app/bank/agence/CompteAgence';
import AddCompteAgence from './pages/app/bank/agence/AddCompteAgence';
import InfoCompteAgence from './pages/app/bank/agence/InfoCompteAgence';
import ListNewRemise from './pages/app/bank/validateur/ListNewRemise';
import InfoRemiseValidator from './pages/app/bank/validateur/InfoRemise';
import ListMotif from './pages/app/bank/It/ListMotif';
import ListBanque from './pages/app/bank/It/ListBanque';
import RemiseRejete from './pages/app/bank/correcteur/RemiseRejete';
import InfoRemise from './pages/app/bank/correcteur/InfoRemise';
import Correction from './pages/app/bank/correcteur/Correction';
import ListReadyIntegration from './pages/app/bank/integrateur/ListReadyIntegration';
import SessionCreation from './pages/app/bank/It/SessionCreation';
import TentativeLoginCreation from './pages/app/bank/It/TentativeLoginCreation';
import InfoRemiseIntegrateur from './pages/app/bank/integrateur/InfoRemiseIntegrateur';
import Audit from './pages/app/bank/It/Audit';
import RapportBank from './pages/app/bank/rapport/RapportBank';
import TrackCheck from './pages/app/bank/rapport/TrackCheck';
import IntegrationWebClearing from './pages/app/bank/It/IntegrationWebClearing';
import PasswordBorneAgence from './pages/app/bank/agence/PasswordBorneAgence';
import GenerateLotFile from './pages/app/bank/integrateur/GenerateLotFile';


function App() {

  return (
     <Routes>
        <Route path='/suivi-import/*' element={
          <ProtectedRoute>
              <Routes>
                <Route index path='/' element={<Index/>} />
                <Route path='/profil' element={<Profil/>} />
                <Route path='/utilisateurs' element={<EcobankUser/>} />
                <Route path='/creer-utilisateurs' element={<EcobankUserCreation/>} />
                <Route path='/info/:userId/utilisateur' element={<EcobankUserInfo />} />
                <Route path='/agence' element={<Agence/>} />
                <Route path='/agence/creer' element={<AgenceCreation/>} />
                <Route path='/agence/:agence/info' element={<UserAgence/>} />
                <Route path='/agence/:agence/userCreate' element={<CreateUserAgence/>} />
                <Route path='/agence/:agence/info/:userId/utilisateur' element={<InfoUserAgence/>} />
                <Route path='/agence/:agence/bornes' element={<BorneAgence/>} />
                <Route path='/agence/:agence/addBorne' element={<AddBorneAgence/>} />
                <Route path='/agence/:agence/borne/:borneId' element={<InfoBorne/>} />
                <Route path='/agence/:agence/borne/:borneId/password' element={<PasswordBorneAgence/>} />
                <Route path='/agence/:agence/comptes' element={<CompteAgence/>} />
                <Route path='/agence/:agence/addCompte' element={<AddCompteAgence/>} />
                <Route path='/agence/:agence/compte/:compteId' element={<InfoCompteAgence/>} />

                {/* Validateur*/}
                <Route path='/validation/newRemise' element={<ListNewRemise/>} />
                <Route path='/validation/integration' element={<ListReadyIntegration/>} />
                <Route path='/validation/remise/:remiseId/info' element={<InfoRemiseValidator />} />
                <Route path='/integrateur/validation/remise/:remiseId/info' element={<InfoRemiseIntegrateur />} />
                <Route path='/integrateur/lot/generation' element={<GenerateLotFile/>} />
                
                {/** Motif */}
                <Route path='/parametre/motifs'element={<ListMotif />} />
                {/* Banques */}
                <Route path='/parametre/banques' element={<ListBanque />} />
                {/* session et inactivité */}
                <Route path='/parametre/session-inactivite' element={<SessionCreation />} />
                <Route path='/parametre/tentative-login' element={<TentativeLoginCreation />} />

                {/** Integration dans web */}
                <Route path='/parametre/integration' element={<IntegrationWebClearing/>} />

                {/** correction */}
                <Route path='/correcteur/remise/rejete' element={<RemiseRejete />} />
                <Route path='/correcteur/remise/:remiseId/info' element={<InfoRemise />} />
                {<Route path='/correcteur/remise/:remiseId/corriger' element={<Correction />} />}
                
                {/* Audit */}
                <Route path='/audit' element={<Audit/>} />

                {/* Rapport */}
                <Route path='/rapport/bank' element={<RapportBank/>} />
                {/* Suivi des cheques */}
                <Route path='/suivi/check' element={<TrackCheck />}  />

                <Route path='*' element={<div>not found404</div>} />
              </Routes>
          </ProtectedRoute>
        } />
        <Route path="/" element={<Login/>} />
        <Route path="/deconnection" element={<Disconnect/>} />
     </Routes>
  );
}

export default App
