import MyLink from "../../component/MyLink";

const AdminRoute = () => {

    return(
        <>
            <li className="active">
                <a href="#admiUser" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-users yellow_color"></i> <span>Gestion des utilisateurs</span></a>
                <ul className="collapse list-unstyled" id="admiUser">
                    <MyLink label="Utilsateurs" link="/banking/utilisateurs"  />
                    <MyLink label="Creation de compte" link="/banking/creer-utilisateurs" />
                </ul>
            </li>
            <li className="active">
                <a href="#agence" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-building yellow_color"></i> <span>Gestion des agences</span></a>
                <ul className="collapse list-unstyled" id="agence">
                    <MyLink label="Agences" link="/banking/agence"  />
                    <MyLink label="Enregister une agence" link="/banking/agence/creer" />
                </ul>
            </li>
            <li className="active">
                <a href="#rapportBank" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-file yellow_color"></i> <span>Gestion des rapports</span></a>
                <ul className="collapse list-unstyled" id="rapportBank">
                    <MyLink label="Faire un rapport" link="/banking/rapport/bank"  />
                </ul>
            </li>
        </>
    );
};

export default AdminRoute;