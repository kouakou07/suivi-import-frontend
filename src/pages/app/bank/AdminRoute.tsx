import MyLink from "../../component/MyLink";

const AdminRoute = () => {

    return(
        <>
            <li className="active">
                <a href="#admiUser" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-users yellow_color"></i> <span>Gestion des utilisateurs</span></a>
                <ul className="collapse list-unstyled" id="admiUser">
                    <MyLink label="Liste des utilsateurs" link="/suivi-import/utilisateurs"/>
                    <MyLink label="Creation de compte utilisateur" link="/suivi-import/creer-utilisateurs" />
                </ul>
            </li>
             <li className="active">
                <a href="#fournisseur" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-users yellow_color"></i> <span>Gestion des fournisseurs</span></a>
                <ul className="collapse list-unstyled" id="fournisseur">
                    <MyLink label="Liste des fournisseurs" link="/suivi-import/liste-fournisseurs"  />
                    <MyLink label="Creation d'un fournisseur" link="/suivi-import/creer-fournisseur" />
                </ul>
            </li>
               <li className="active">
                <a href="#agence" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-building yellow_color"></i> <span>Gestion des articles</span></a>
                <ul className="collapse list-unstyled" id="agence">
                    <MyLink label="Liste des articles" link="/suivi-import/liste-article"  />
                    <MyLink label="Enregister un article" link="/suivi-import/creer-article" />
                </ul>
            </li>
            <li className="active">
                <a href="#agence" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-building yellow_color"></i> <span>Gestion des commandes</span></a>
                <ul className="collapse list-unstyled" id="agence">
                    <MyLink label="Liste des commandes" link="/suivi-import/agence"  />
                    <MyLink label="Enregister une commande" link="/suivi-import/agence/creer" />
                </ul>
            </li>
              <li className="active">
                <a href="#rapportBank" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-file yellow_color"></i> <span>Parametres</span></a>
                <ul className="collapse list-unstyled" id="rapportBank">
                    <MyLink label="Faire un rapport" link="/suivi-import/rapport/bank"  />
                </ul>
            </li>
            {/* <li className="active">
                <a href="#rapportBank" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-file yellow_color"></i> <span>Gestion des rapports</span></a>
                <ul className="collapse list-unstyled" id="rapportBank">
                    <MyLink label="Faire un rapport" link="/suivi-import/rapport/bank"  />
                </ul>
            </li> */}
        </>
    );
};

export default AdminRoute;