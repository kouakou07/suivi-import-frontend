import React, { useState } from "react";

import { HttpStatusCode } from "axios";
import Swal from "sweetalert2";
import { Link } from "react-router-dom";
import httpClient, { encodeData, writeErrors } from "../../../../hooks/httpClient";
import myRoute from "../../../../hooks/myRoute";
import noNetWork, { problemOccur } from "../../../../component/AlertReport";
import Layout from "../../../../template/Layout";
import Wrapper from "../../../../component/Wrapper";
import Input from "../../../../component/Input";
import { BeatLoader } from "react-spinners";

const EnregistrerFournisseur = () => {
    const [fournisseurData, setFournisseurData] = useState({
        codeFournisseur: '',
        intituleFournisseur: '',
        dateCreation: '',    // au format YYYY-MM-DD attendu par l’API
        nomContact: '',
        telephone: '',
        telecopie: '',
        email: '',
        siret: '',
        adresse: '',
        ville: '',
        pays: '',
    });

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

    const [isSubmitLoading, setIsSubmitLoading] = useState(false);

    const onSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        setIsSubmitLoading(true);

        httpClient.post(myRoute.creerFournisseur, fournisseurData)
            .then(() => {
                setErrors({
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
                setFournisseurData({
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
                });
                setIsSubmitLoading(false);
                Swal.fire({
                    title: "Création Fournisseur",
                    text: "Le fournisseur a été créé avec succès.",
                    icon: "success",
                    backdrop: false,
                });
            })
            .catch((err) => {
                setIsSubmitLoading(false);
                if (err.response == undefined) {
                    noNetWork();
                } else {
                    if (err.response.status === HttpStatusCode.BadRequest) {
                        const errs = writeErrors({ ...errors }, err.response.data);
                        setErrors(errs);
                    } else {
                        problemOccur();
                    }
                }
            });
    };

    return (
        <Layout title="Enregistrer un fournisseur">
            <div className="row">
                <div className="col-md-10 col-lg-8">
                    <Wrapper title="Nouveau fournisseur">
                        <div className="text-right mb-3">
                            <Link className="btn btn-link" to="/suivi-import/liste-fournisseurs">Liste des fournisseurs</Link>
                        </div>
                        <form onSubmit={onSubmit}>
                            <Input label="Code Fournisseur" name="codeFournisseur" data={fournisseurData} update={setFournisseurData} report={errors.codeFournisseur} />
                            <Input label="Intitulé Fournisseur" name="intituleFournisseur" data={fournisseurData} update={setFournisseurData} report={errors.intituleFournisseur} />
                            <Input label="Date de création" name="dateCreation" type="date" data={fournisseurData} update={setFournisseurData} report={errors.dateCreation} />
                            <Input label="Nom Contact" name="nomContact" data={fournisseurData} update={setFournisseurData} report={errors.nomContact} />
                            <Input label="Téléphone" name="telephone" data={fournisseurData} update={setFournisseurData} report={errors.telephone} />
                            <Input label="Fax (Télécopie)" name="telecopie" data={fournisseurData} update={setFournisseurData} report={errors.telecopie} />
                            <Input label="Email" type="email" name="email" data={fournisseurData} update={setFournisseurData} report={errors.email} />
                            <Input label="SIRET" name="siret" data={fournisseurData} update={setFournisseurData} report={errors.siret} />
                            <Input label="Adresse" name="adresse" data={fournisseurData} update={setFournisseurData} report={errors.adresse} />
                            <Input label="Ville" name="ville" data={fournisseurData} update={setFournisseurData} report={errors.ville} />
                            <Input label="Pays" name="pays" data={fournisseurData} update={setFournisseurData} report={errors.pays} />

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
        </Layout>
    );
};

export default EnregistrerFournisseur;
