import MyLink from "../../component/MyLink";



const CorrecteurRoute = () => {

    return(
        <>
            <li className="active">
                <a href="#correcteurUser" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-dollar yellow_color"></i> <span>Remises rejetees</span></a>
                <ul className="collapse list-unstyled" id="correcteurUser">
                    <MyLink label="Rejets" link="/banking/correcteur/remise/rejete"  />
                </ul>
            </li>
            <li className="active">
                <a href="#rapportBank" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-file yellow_color"></i> <span>Gestion des rapports</span></a>
                <ul className="collapse list-unstyled" id="rapportBank">
                    <MyLink label="Faire un rapport" link="/banking/rapport/bank"  />
                </ul>
            </li>

            {/*<li className="active">
                <a href="#correcteurRapport" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle"><i className="fa fa-dollar yellow_color"></i> <span>Rapport</span></a>
                <ul className="collapse list-unstyled" id="correcteurRapport">
                    <MyLink label="Rapport" link="/banking/correcteur/rapport"  />
                </ul>
    </li>*/}
        </>
    );
}
export default CorrecteurRoute;