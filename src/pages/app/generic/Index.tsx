import { useEffect, useState } from "react";
import Card from "../../component/Card";
import Layout from "../../template/Layout";
import httpClient from "../../hooks/httpClient";
import myRoute from "../../hooks/myRoute";
import noNetWork, { problemOccur } from "../../component/AlertReport";
import { HttpStatusCode } from "axios";


const Index = ({ }) => {
    // Nombre de nouvelle remise | remise rejete | remise valide | remise integer

    const [stats, setStats] = useState({
        newRemise: 0,
        remiseRejete: 0,
        remiseValide: 0,
        remiseIntegre: 0
    });

    useEffect(() => {
        const tokenAuth = localStorage.getItem("authToken");
        httpClient.get(myRoute.home, {
            headers: {
               "Authorization": "Bearer "+ tokenAuth
            }
        })
        .then(res => {
            setStats(res.data);
        })
        .catch(err => {
            if(err.response == undefined){
                noNetWork();
            }else{
               problemOccur();
               console.log(err);
               
            }
        })
    }, [])

    return (
        <Layout title="Welcome">
            <div className="row column1">
                <Card title={"Nouvelle remise"} number={stats.newRemise} fa="fa-money" colorStyle={"#FF9800"} />
                <Card title={"Remise rejeté"} number={stats.remiseRejete} fa="fa-times-circle" colorStyle={"red"} />
                <Card title={"Remise validé"} number={stats.remiseValide} fa="fa-check-circle" colorStyle={"green"} />
                <Card title={"Remise integré"} number={stats.remiseIntegre} fa="fa-cloud" colorStyle={"#0089FA"} />

            </div>
        </Layout>
    );
}
export default Index;