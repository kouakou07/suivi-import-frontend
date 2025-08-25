import { Link, Navigate, useParams } from "react-router-dom";
import myRoute from "../../../../hooks/myRoute";
import httpClient, { writeErrors } from "../../../../hooks/httpClient";
import { useEffect, useState } from "react";
import noNetWork, { problemOccur, Toast } from "../../../../component/AlertReport";
import { HttpStatusCode } from "axios";
import Layout from "../../../../template/Layout";
import { BeatLoader } from "react-spinners";
import Wrapper from "../../../../component/Wrapper";
import Input from "../../../../component/Input";

const InfoArticle = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [isSubmitLoading, setIsSubmitLoading] = useState(false);
    const [notFound, setNotFound] = useState(false);
    const { articleId } = useParams();

    const [articleData, setArticleData] = useState({
        id: 0,
        codeFamille: '',
        libelleFamille: '',
        referenceArticle: '',
        afRefFourniss: '',
        designationArticle: '',
        suiviStock: '',
        fournisseurPrincipal: '',
    });

    const [errors, setErrors] = useState({
        codeFamille: undefined,
        libelleFamille: undefined,
        referenceArticle: undefined,
        afRefFourniss: undefined,
        designationArticle: undefined,
        suiviStock: undefined,
        fournisseurPrincipal: undefined,
    });

    useEffect(() => {
        if (!articleId) return;

        setIsLoading(true);

        httpClient.get(myRoute.infoArticle.replace('{articleId}', articleId))
            .then(res => {
                const art = res.data || {};
                setArticleData({
                    id: art.id || 0,
                    codeFamille: art.codeFamille || '',
                    libelleFamille: art.libelleFamille || '',
                    referenceArticle: art.referenceArticle || '',
                    afRefFourniss: art.afRefFourniss || '',
                    designationArticle: art.designationArticle || '',
                    suiviStock: art.suiviStock || '',
                    fournisseurPrincipal: art.fournisseurPrincipal || '',
                });
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
    }, [articleId]);

    const onSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        setIsSubmitLoading(true);
        setErrors(writeErrors({ ...errors }, {}));

        httpClient.post(myRoute.editionArticle, articleData)
            .then(res => {
                setIsSubmitLoading(false);
                Toast.fire({
                    icon: 'success',
                    title: 'Article modifié avec succès'
                });
            })
            .catch(err => {
                setIsSubmitLoading(false);
                if (!err.response) {
                    noNetWork();
                } else if (err.response.status === HttpStatusCode.BadRequest) {
                    setErrors(writeErrors({ ...errors }, err.response.data));
                } else if (err.response.status === HttpStatusCode.NotFound) {
                    setNotFound(true);
                } else {
                    problemOccur();
                }
            });
    };

    if (notFound) {
        return <Navigate to={"/suivi-import/liste-article"} />;
    }

    return (
        <Layout title="Modifier un article">
            {isLoading ? (
                <div className="text-center">
                    <BeatLoader />
                </div>
            ) : (
                <div className="row">
                    <div className="col-md-10 col-lg-8">
                        <Wrapper title="Modifier article">
                            <div className="text-right mb-3">
                                <Link className="btn btn-link" to="/suivi-import/liste-article">
                                    Liste des articles
                                </Link>
                            </div>
                            <form onSubmit={onSubmit}>
                                <Input label="Code Famille" name="codeFamille" data={articleData} update={setArticleData} report={errors.codeFamille} />
                                <Input label="Libellé Famille" name="libelleFamille" data={articleData} update={setArticleData} report={errors.libelleFamille} />
                                <Input label="Référence Article" name="referenceArticle" data={articleData} update={setArticleData} report={errors.referenceArticle} />
                                <Input label="Référence Fournisseur" name="afRefFourniss" data={articleData} update={setArticleData} report={errors.afRefFourniss} />
                                <Input label="Désignation" name="designationArticle" data={articleData} update={setArticleData} report={errors.designationArticle} />
                                <Input label="Suivi Stock" name="suiviStock" data={articleData} update={setArticleData} report={errors.suiviStock} />
                                <Input label="Fournisseur Principal" name="fournisseurPrincipal" data={articleData} update={setArticleData} report={errors.fournisseurPrincipal} />
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
};

export default InfoArticle;
