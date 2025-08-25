import { useEffect, useState } from "react";
import httpClient from "../../../../hooks/httpClient";
import myRoute from "../../../../hooks/myRoute";
import noNetWork, { problemOccur, ToastOperation } from "../../../../component/AlertReport";
import Swal from "sweetalert2";
import Layout from "../../../../template/Layout";
import { BeatLoader } from "react-spinners";
import Wrapper from "../../../../component/Wrapper";
import Pagination from "../../../../component/Pagination";
import { Link } from "react-router-dom";
import Input from "../../../../component/Input";
import jsPDF from "jspdf";
import * as XLSX from "xlsx";
import autoTable from "jspdf-autotable";
import { saveAs } from "file-saver";
import AutoComplete from "../../../../component/AutoComplete";


const ListeArticle = () => {

        const [isLoading, setIsLoading] = useState(true);
        const [articles, setArticles] = useState<any>({});
        const [page, setPage] = useState('0');
         const [refresh, setRefresh] = useState(0);
          const [codeArticleFilter, setCodeArticleFilter] = useState('');

         useEffect(() => {
        setIsLoading(true);
        httpClient.get(myRoute.listeArticleAvecPagination.replace('{page}', page))
        .then(res => {
             console.log(res?.data);
            setArticles(res?.data);
            setIsLoading(false);
        })
        .catch(err => {
            setIsLoading(false);
            if (err.response === undefined) {
                noNetWork();
            } else {
                problemOccur();
            }
        });
      }, [refresh, page]);

const onRemove = (id: number) => {
    ToastOperation.fire().then(choice => {
        if (choice.isConfirmed) {
            setIsLoading(true);
            httpClient.post(myRoute.supprimerArticle.replace("{id}", id.toString()))
                .then(() => {
                    setIsLoading(false);
                    Swal.fire({
                        title: 'L\'article a été supprimé avec succès',
                        icon: 'success',
                        backdrop: false
                    });
                    setRefresh(refresh + 1);
                })
                .catch(err => {
                    setIsLoading(false);
                    if (err.response == undefined) {
                        noNetWork();
                    } else {
                        problemOccur();
                    }
                });
        }
    });
};

  const onSearchSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        setPage('0'); // reset page to 0 at new search
        setRefresh(refresh + 1); // trigger refresh to reload data with filter
    };

    const exportExcel = () => {
  const ws = XLSX.utils.json_to_sheet(articles?.content || []);
  const wb = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(wb, ws, "Articles");
  const excelBuffer = XLSX.write(wb, { bookType: "xlsx", type: "array" });
  const data = new Blob([excelBuffer], { type: "application/octet-stream" });
  saveAs(data, "articles.xlsx");
};

const exportPDF = () => {
  const doc = new jsPDF();
  doc.text("Liste des articles", 14, 10);
  autoTable(doc, {
    head: [[
      "ID", "Code Famille", "Libellé Famille", "Référence Article",
      "Référence Fournisseur", "Désignation", "Suivi Stock", "Fournisseur Principal"
    ]],
    body: (articles?.content || []).map((article: any) => [
      article.id ?? "",
      article.codeFamille ?? "",
      article.libelleFamille ?? "",
      article.referenceArticle ?? "",
      article.afRefFourniss ?? "",
      article.designationArticle ?? "",
      article.suiviStock ?? "",
      article.fournisseurPrincipal ?? ""
    ]),
    startY: 20,
  });
  doc.save("articles.pdf");
};

return (
 <Layout title="Liste des articles">
  <Wrapper title="Recherche un article">
  <form onSubmit={onSearchSubmit} className="form-inline mb-3 align-items-center">
    <Input
      label="Code article"
      placeholder="Entrez le code article"
      name="codeArticleFilter"
      data={{ codeArticleFilter }}
      update={() => setCodeArticleFilter(codeArticleFilter)}
      report={undefined}
    />
    <button type="submit" className="btn btn-primary ml-2">
      Rechercher
    </button>
    <button type="button" className="btn btn-success ml-2" onClick={exportExcel}>
      Exporter Excel
    </button>
    <button type="button" className="btn btn-danger ml-2" onClick={exportPDF}>
      Exporter PDF
    </button>
  </form>
</Wrapper>

    {isLoading && 
      <div className="text-center">
        <BeatLoader />
      </div>
    }
    {!isLoading && 
      <div className="row">
        <div className="col-12">
          <Wrapper title="Les articles">
            <div className="table-responsive">
              <table className="table table-striped">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Code Famille</th>
                    <th>Libellé Famille</th>
                    <th>Référence Article</th>
                    <th>Référence Fournisseur</th>
                    <th>Désignation</th>
                    <th>Suivi Stock</th>
                    <th>Fournisseur Principal</th>
                    <th className="text-right">Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {articles?.content?.map((article: any) => (
                    <tr key={article.id}>
                      <td>{article.id ?? ""}</td>
                      <td>{article.codeFamille ?? ""}</td>
                      <td>{article.libelleFamille ?? ""}</td>
                      <td>{article.referenceArticle ?? ""}</td>
                      <td>{article.afRefFourniss ?? ""}</td>
                      <td>{article.designationArticle ?? ""}</td>
                      <td>{article.suiviStock ?? ""}</td>
                      <td>{article.fournisseurPrincipal ?? ""}</td>
                      <td className="text-right">
                        <Link className="text-success" to={"/suivi-import/article/info/" + article.id}><i className="fa fa-eye"></i></Link>
                        <Link to={"#"} onClick={() => onRemove(article.id)} className="text-danger"><i className="fa fa-trash"></i></Link>
                      </td>
                    </tr>
                  ))}
                  {articles?.content?.length === 0 && (
                    <tr>
                      <td colSpan={9} className="text-center">Aucun article trouvé</td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
            <Pagination update={setPage} paginator={articles} />
          </Wrapper>
        </div>
      </div>
    }
  </Layout>
    );
}
export default ListeArticle;