import MyLink from "../../component/MyLink";


const ItRoute = () => {
    
    return(
        <>
            <li className="active">
                <a href="#itUser" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-cog yellow_color"></i> <span>Parametres</span></a>
                <ul className="collapse list-unstyled" id="itUser">
            
                    <MyLink label="Session | Inactivité" link="/suivi-import/parametre/session-inactivite" />
                    <MyLink label="Tentative login" link="/suivi-import/parametre/tentative-login" />
                </ul>
            </li> 
            <li className="active">
                <a href="#itUserAudit" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-eye yellow_color"></i> <span>Activites utilisateurs</span></a>
                <ul className="collapse list-unstyled" id="itUserAudit">
                    <MyLink label="Audit" link="/suivi-import/audit"  />
                </ul>
            </li> 
            <li className="active">
                <a href="#rapportBank" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-file yellow_color"></i> <span>Gestion des rapports</span></a>
                <ul className="collapse list-unstyled" id="rapportBank">
                    <MyLink label="Suivi des cheques" link="/banking/suivi/check"  />
                    <MyLink label="Faire un rapport" link="/banking/rapport/bank"  />
                </ul>
            </li>
        </>
    );
}
export default ItRoute;