import MyLink from "../../component/MyLink";


const ItRoute = () => {
    
    return(
        <>
            <li className="active">
                <a href="#itUser" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-cog yellow_color"></i> <span>Parametres</span></a>
                <ul className="collapse list-unstyled" id="itUser">
                    <MyLink label="Les motifs de rejet" link="/banking/parametre/motifs"  />
                    <MyLink label="Les banques" link="/banking/parametre/banques" />
                    <MyLink label="Repertoire d'integration" link="/banking/parametre/integration" />
                    <MyLink label="Session | Inactivité" link="/banking/parametre/session-inactivite" />
                    <MyLink label="Tentative login" link="/banking/parametre/tentative-login" />
                </ul>
            </li> 
            <li className="active">
                <a href="#itUserAudit" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-eye yellow_color"></i> <span>Activites utilisateurs</span></a>
                <ul className="collapse list-unstyled" id="itUserAudit">
                    <MyLink label="Audit" link="/banking/audit"  />
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