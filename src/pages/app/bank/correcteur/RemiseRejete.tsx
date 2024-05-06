import { useEffect, useState } from "react";
import Layout from "../../../template/Layout";
import httpClient from "../../../hooks/httpClient";
import myRoute from "../../../hooks/myRoute";
import noNetWork, { problemOccur } from "../../../component/AlertReport";
import { BeatLoader } from "react-spinners";
import Wrapper from "../../../component/Wrapper";
import moment from "moment";
import RemiseEtat from "../../../component/RemiseEtat";
import { Link } from "react-router-dom";
import Pagination from "../../../component/Pagination";


const RemiseRejete = () => {

    const [isLoading, setIsLoading] = useState(true);
    const [page, setPage] = useState('0');
    const [remises, setRemises] = useState<any>({});

    useEffect(() => {
        httpClient.get(myRoute.listRemiseToBeCorrected.replace("{page}", page))
        .then(res => {
            setIsLoading(false);
            setRemises(res.data);
        })
        .catch(err => {
            setIsLoading(false);
            if(err.response == undefined){
                noNetWork();
            }else{
                problemOccur();
            }
        })
    }, [page]);

    return(
        <Layout title="Liste des remises a corriger">
            {isLoading == true && <div className="text-center">
                <BeatLoader />
            </div>}
            {isLoading == false && <div className="row">
                <div className="col-12">
                    <Wrapper title="Les remises contenant des cheques rejetes">
                        <div className="table-responsive">
                            <table className="table">
                                <thead>
                                    <tr>
                                        <th>Reference</th>
                                        <th>Sequence</th>
                                        <th>Montant</th>
                                        <th>Nombre de cheques</th>
                                        <th>Agence remettant</th>
                                        <th>Compte remettant</th>
                                        <th>Intitule compte remettant</th>
                                        <th>Date Ajout</th>
                                        <th>Etat</th>
                                        <th className="text-right">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {remises?.content?.map((remise: any) => {

                                        return(
                                            <tr key={remise.id}>
                                                <td>{remise.reference}</td>
                                                <td>{remise.sequence}</td>
                                                <td>{remise.montant}</td>
                                                <td>{remise.nbreCheques}</td>
                                                <td>{remise.agence.code}</td>
                                                <td>{remise.compteRemettant.numeroCmpt}</td>
                                                <td>{remise.compteRemettant.intitule}</td>
                                                <td>{moment(remise.dateCreation).format("DD/MM/YYYY HH:mm")}</td>
                                                <td><RemiseEtat etat={remise.etat} /></td>
                                                <td className="text-right">
                                                    <Link className="text-success" to={"/banking/correcteur/remise/" + remise.id +"/info"}><i className="fa fa-eye"></i> </Link>
                                                </td>
                                            </tr>
                                        )
                                    })}
                                </tbody>
                            </table>
                        </div>
                        <Pagination update={setPage} paginator={remises} />
                    </Wrapper>
                </div>
            </div>}
        </Layout>
    );
}
export default RemiseRejete;