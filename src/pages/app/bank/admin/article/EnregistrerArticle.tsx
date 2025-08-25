import React, { useEffect, useState } from "react";
import { HttpStatusCode } from "axios";
import Swal from "sweetalert2";
import { Link } from "react-router-dom";
import httpClient, { writeErrors } from "../../../../hooks/httpClient";
import myRoute from "../../../../hooks/myRoute";
import noNetWork, { problemOccur } from "../../../../component/AlertReport";
import Layout from "../../../../template/Layout";
import Wrapper from "../../../../component/Wrapper";
import Input from "../../../../component/Input";
import { BeatLoader } from "react-spinners";
import AutoComplete from "../../../../component/AutoComplete";

const EnregistrerArticle = () => {

  const [articleData, setArticleData] = useState({
    codeFamille: "",
    libelleFamille: "",
    referenceArticle: "",
    designationArticle: "",
    suiviStock: "",
    fournisseurPrincipal: "",
  });

  const [errors, setErrors] = useState({
    codeFamille: undefined,
    libelleFamille: undefined,
    referenceArticle: undefined,
    designationArticle: undefined,
    suiviStock: undefined,
    fournisseurPrincipal: undefined,
  });

  const [isSubmitLoading, setIsSubmitLoading] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [fournisseurs, setFournisseurs] = useState<any[]>([]);

  useEffect(() => {
    setIsLoading(true);
    httpClient.get(myRoute.listeFournisseur)
      .then((res) => {
        setFournisseurs(res?.data || []);
        setIsLoading(false);
      })
      .catch((err) => {
        if (!err.response) {
          noNetWork();
        } else {
          problemOccur();
        }
      });
  }, []);

  const handleFournisseurChange = (selectedCode: string) => {
    setArticleData(prev => ({
      ...prev,
      fournisseurPrincipal: selectedCode
    }));
  };

  const onSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitLoading(true);
    setErrors({
      codeFamille: undefined,
      libelleFamille: undefined,
      referenceArticle: undefined,
      designationArticle: undefined,
      suiviStock: undefined,
      fournisseurPrincipal: undefined,
    });

    httpClient
      .post(myRoute.creerArticle, articleData)
      .then(() => {
        setErrors({
          codeFamille: undefined,
          libelleFamille: undefined,
          referenceArticle: undefined,
          designationArticle: undefined,
          suiviStock: undefined,
          fournisseurPrincipal: undefined,
        });
        setArticleData({
          codeFamille: "",
          libelleFamille: "",
          referenceArticle: "",
          designationArticle: "",
          suiviStock: "",
          fournisseurPrincipal: "",
        });
        setIsSubmitLoading(false);
        Swal.fire({
          title: "Création Article",
          text: "L'article a été créé avec succès.",
          icon: "success",
          backdrop: false,
        });
      })
      .catch((err) => {
        setIsSubmitLoading(false);
        if (!err.response) {
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
    <Layout title="Enregistrer un article">
      <div className="row">
        <div className="col-md-10 col-lg-8">
          <Wrapper title="Nouvel article">
            <div className="text-right mb-3">
              <Link className="btn btn-link" to="/suivi-import/liste-article">
                Liste des articles
              </Link>
            </div>
            <form onSubmit={onSubmit}>
              <Input
                label="Code Famille"
                name="codeFamille"
                data={articleData}
                update={setArticleData}
                report={errors.codeFamille}
                required
              />
              <Input
                label="Libellé Famille"
                name="libelleFamille"
                data={articleData}
                update={setArticleData}
                report={errors.libelleFamille}
                required
              />
              <Input
                label="Référence Article"
                name="referenceArticle"
                data={articleData}
                update={setArticleData}
                report={errors.referenceArticle}
                required
              />
              <Input
                label="Désignation"
                name="designationArticle"
                data={articleData}
                update={setArticleData}
                report={errors.designationArticle}
                required
              />
              <Input
                label="Suivi Stock"
                name="suiviStock"
                data={articleData}
                update={setArticleData}
                report={errors.suiviStock}
                required
              />
             <AutoComplete
              label="Choisir le fournisseur principal *"
              name="fournisseurPrincipal"
              value={articleData.fournisseurPrincipal}
              onChange={handleFournisseurChange}
              options={fournisseurs}
              displayField={(f) => `${f.codeFournisseur} - ${f.intituleFournisseur}`}
              valueField="codeFournisseur"
              placeholder="Rechercher un fournisseur..."
              report={errors.fournisseurPrincipal}
              disabled={isSubmitLoading}
              required
            />
              <div className="mt-3">
                {isSubmitLoading && (
                  <div className="text-center mb-2">
                    <BeatLoader />
                  </div>
                )}
                <button
                  disabled={isSubmitLoading}
                  type="submit"
                  className="btn btn-primary"
                >
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

export default EnregistrerArticle;
