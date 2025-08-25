import { Link, Navigate, useParams } from "react-router-dom";
import myRoute from "../../../../hooks/myRoute";
import httpClient, { encodeData, writeErrors } from "../../../../hooks/httpClient";
import { useEffect, useState } from "react";
import noNetWork, { problemOccur, Toast } from "../../../../component/AlertReport";
import { HttpStatusCode } from "axios";
import Layout from "../../../../template/Layout";
import { BeatLoader } from "react-spinners";
import Wrapper from "../../../../component/Wrapper";
import Input from "../../../../component/Input";

const InfoFournisseur = () => {

    const [isLoading, setIsLoading] = useState(true);
    const [isSubmitLoading, setIsSubmitLoading] = useState(false);
     const [notFound, setNotFound] = useState(false);
    const {fournisseurId} = useParams();

    const [fournisseurData, setFournisseurData] = useState({
        id: 0,
       codeFournisseur: '',
        intituleFournisseur: '',
        dateCreation: '',
        nomContact: '',
        telephone: '',
        telecopie: '',
        email: '',
        siret: '',
        adresse: '',
        ville: '',
        pays: '',
        status: 0, 
    })

    const [errors, setErrors] = useState({
        codeFournisseur: undefined,
        intituleFournisseur: undefined,
        dateCreation: undefined,
        nomContact: undefined,
        telephone: undefined,
        telecopie: undefined,
        email: undefined,
        siret: undefined,
        adresse: undefined,
        ville: undefined,
        pays: undefined,
    });

    useEffect(() => {
    if (!fournisseurId) return;

    setIsLoading(true);

    httpClient.get(myRoute.infoFournisseur.replace('{fournisseurId}', fournisseurId))
    .then(res => {
        const four = res.data || {};
    //    console.log("Réponse complète:", res.data);
        setFournisseurData(prev => ({
            ...prev,
            id: four.id || 0,
            codeFournisseur: four?.codeFournisseur || '',
            intituleFournisseur: four?.intituleFournisseur || '',
            dateCreation: four.dateCreation ? four.dateCreation.substring(0, 10) : '',
            nomContact: four.nomContact || '',
            telephone: four.telephone || '',
            telecopie: four.telecopie || '',
            email: four.email || '',
            siret: four.siret || '',
            adresse: four.adresse || '',
            ville: four.ville || '',
            pays: four.pays || '',
            status: four.status ?? 0
        }));

        setIsLoading(false);
    })
    .catch(err => {
        setIsLoading(false);
        if (!err.response) {
            noNetWork();
        } else if (err.response.status === HttpStatusCode.NotFound) {
            setNotFound(true);
        } else {
            problemOccur();
        }
    });
    }, [fournisseurId]);

        const onSubmit = (e: React.FormEvent) =>{
        e.preventDefault();
        setIsSubmitLoading(true);
        setErrors(writeErrors({...errors}, {}));
        httpClient.post(myRoute.editionFournisseur, fournisseurData)
        .then(res => {
            console.log("Réponse de l'édition:", res.data);
            setIsSubmitLoading(false);
            Toast.fire({
                icon: 'success',
                title: 'Fournisseur modifié avec succès'
            });
        })
        .catch(err => {
            setIsSubmitLoading(false);
            if(err.response == undefined){
                noNetWork();
            }else{
                if(err.response.status == HttpStatusCode.BadRequest){
                    setErrors(writeErrors({...errors}, err.response.data));
                }
                else if(err.response.status == HttpStatusCode.NotFound){
                    setNotFound(true);
                }else{
                    problemOccur();
                }
            }
        })
    }

   if(notFound == true){
           return <Navigate to={"/suivi-import/liste-fournisseurs"} />
       }

   return(
          <Layout title="Modifier un fournisseur">
            {isLoading ? (
                <div className="text-center">
                    <BeatLoader />
                </div>
            ) : (
                <div className="row">
                    <div className="col-md-10 col-lg-8">
                        <Wrapper title="Edition fournisseur">
                            <div className="text-right mb-3">
                                <Link className="btn btn-link" to="/suivi-import/liste-fournisseurs">
                                    Liste des fournisseurs
                                </Link>
                            </div>
                            <form onSubmit={onSubmit}>
                                <Input label="Code Fournisseur" name="codeFournisseur" data={fournisseurData} update={setFournisseurData} report={errors.codeFournisseur} />
                                <Input label="Intitulé Fournisseur" name="intituleFournisseur" data={fournisseurData} update={setFournisseurData} report={errors.intituleFournisseur} />
                                <Input label="Date de création" type="date" name="dateCreation" data={fournisseurData} update={setFournisseurData} report={errors.dateCreation} />
                                <Input label="Nom Contact" name="nomContact" data={fournisseurData} update={setFournisseurData} report={errors.nomContact} />
                                <Input label="Téléphone" name="telephone" data={fournisseurData} update={setFournisseurData} report={errors.telephone} />
                                <Input label="Fax (Télécopie)" name="telecopie" data={fournisseurData} update={setFournisseurData} report={errors.telecopie} />
                                <Input label="Email" type="email" name="email" data={fournisseurData} update={setFournisseurData} report={errors.email} />
                                <Input label="SIRET" name="siret" data={fournisseurData} update={setFournisseurData} report={errors.siret} />
                                <Input label="Adresse" name="adresse" data={fournisseurData} update={setFournisseurData} report={errors.adresse} />
                                <Input label="Ville" name="ville" data={fournisseurData} update={setFournisseurData} report={errors.ville} />
                                <Input label="Pays" name="pays" data={fournisseurData} update={setFournisseurData} report={errors.pays} />
                                {/* Ajoute ici un SwitchButton ou autre pour gérer le status si applicable */}
                                <div className="mt-3">
                                    {isSubmitLoading && (
                                        <div className="text-center mb-2">
                                            <BeatLoader />
                                        </div>
                                    )}
                                    <button disabled={isSubmitLoading} type="submit" className="btn btn-primary">
                                        Enregistrer
                                    </button>
                                </div>
                            </form>
                        </Wrapper>
                    </div>
                </div>
            )}
        </Layout>
    );
}
export default InfoFournisseur;