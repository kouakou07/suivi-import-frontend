import { useEffect, useState } from "react";
import { BeatLoader } from "react-spinners";
import moment from 'moment';
import httpClient from "../../../../hooks/httpClient";
import myRoute from "../../../../hooks/myRoute";
import Layout from "../../../../template/Layout";
import Wrapper from "../../../../component/Wrapper";
import Pagination from "../../../../component/Pagination";
import noNetWork, { problemOccur, ToastOperation } from "../../../../component/AlertReport";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";
import Input from "../../../../component/Input";
import jsPDF from "jspdf";
import autoTable from "jspdf-autotable";
import { saveAs } from "file-saver";
import * as XLSX from "xlsx";

const ListeFournisseur = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [fournisseurs, setFournisseurs] = useState<any>({});
    const [page, setPage] = useState('0');
    const [refresh, setRefresh] = useState(0);
    const [codeFournisseurFilter, setCodeFournisseurFilter] = useState('');

    useEffect(() => {
        setIsLoading(true);
        httpClient.get(myRoute.listeFournisseurAvecPagination.replace('{page}', page))
        .then(res => {
            setFournisseurs(res.data);
            setIsLoading(false);
        }).catch(err => {
            setIsLoading(false);
            if (err.response === undefined) {
                noNetWork();
            } else {
                problemOccur();
            }
        });
    }, [refresh, page]);

    const onRemove = (id: number) => {
        ToastOperation.fire()
        .then(choice => {
            if(choice.isConfirmed){
                setIsLoading(true);
                httpClient.post(myRoute.supprimerFournisseur.replace("{id}", id.toString()))
                .then(res => {
                    setIsLoading(false);
                    Swal.fire({
                        title: 'Le fournisseur à été supprimé avec succès',
                        icon: 'success',
                        backdrop: false
                    });
                    setRefresh(refresh + 1);
                })
                .catch(err => {
                    setIsLoading(false);
                    if(err.response == undefined){
                        noNetWork();
                    }else{
                        problemOccur();
                    }
                });
            }
        });
    };

    // Filtrage côté frontend (optionnel, adapte selon besoin)
    const filteredFournisseurs = (fournisseurs?.content || []).filter((f: any) =>
        codeFournisseurFilter === "" ||
        (f.codeFournisseur ?? "").toLowerCase().includes(codeFournisseurFilter.toLowerCase())
    );

    // Export CSV
    const exportCSV = () => {
        const ws = XLSX.utils.json_to_sheet(filteredFournisseurs);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, "Fournisseurs");
        const csv = XLSX.write(wb, { bookType: "csv", type: "array" });
        const blob = new Blob([csv], { type: "text/csv;charset=utf-8;" });
        saveAs(blob, "fournisseurs.csv");
    };

    // Export PDF
    const exportPDF = () => {
        const doc = new jsPDF();
        doc.text("Liste des fournisseurs", 14, 10);
        autoTable(doc, {
            head: [[
                "Code Fournisseur", "Intitulé", "Date Création", "Nom Contact", "Téléphone",
                "Fax", "Email", "SIRET", "Adresse", "Ville", "Pays"
            ]],
            body: filteredFournisseurs.map((f: any) => [
                f.codeFournisseur ?? "",
                f.intituleFournisseur ?? "",
                f.dateCreation ? moment(f.dateCreation).format('DD/MM/YYYY') : "",
                f.nomContact ?? "",
                f.telephone ?? "",
                f.telecopie ?? "",
                f.email ?? "",
                f.siret ?? "",
                f.adresse ?? "",
                f.ville ?? "",
                f.pays ?? ""
            ]),
            startY: 20,
        });
        doc.save("fournisseurs.pdf");
    };

    return (
        <Layout title="Liste des fournisseurs">
            <Wrapper title="Recherche un fournisseur">
                <form className="form-inline mb-3 align-items-center" onSubmit={e => e.preventDefault()}>
                    <Input
                        label="Code fournisseur"
                        placeholder="Entrez le code fournisseur"
                        name="codeFournisseur"
                        data={{ codeFournisseurFilter }}
                        update={() => setCodeFournisseurFilter(codeFournisseurFilter)}
                        report={undefined}
                    />
                    <button type="submit" className="btn btn-primary ml-2">
                     Rechercher
                    </button>
                    <button type="button" className="btn btn-success ml-2" onClick={exportCSV}>
                        Exporter CSV
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
                        <Wrapper title="Les fournisseurs">
                            <div className="table-responsive">
                                <table className="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Code Fournisseur</th>
                                            <th>Intitulé</th>
                                            <th>Date Création</th>
                                            <th>Nom Contact</th>
                                            <th>Téléphone</th>
                                            <th>Fax</th>
                                            <th>Email</th>
                                            <th>SIRET</th>
                                            <th>Adresse</th>
                                            <th>Ville</th>
                                            <th>Pays</th>
                                            <th className="text-right">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {filteredFournisseurs.map((fournisseur: any) => (
                                            <tr key={fournisseur.id}>
                                                <td>{fournisseur.codeFournisseur ?? ""}</td>
                                                <td>{fournisseur.intituleFournisseur ?? ""}</td>
                                                <td>{fournisseur.dateCreation ? moment(fournisseur.dateCreation).format('DD/MM/YYYY') : ""}</td>
                                                <td>{fournisseur.nomContact ?? ""}</td>
                                                <td>{fournisseur.telephone ?? ""}</td>
                                                <td>{fournisseur.telecopie ?? ""}</td>
                                                <td>{fournisseur.email ?? ""}</td>
                                                <td>{fournisseur.siret ?? ""}</td>
                                                <td>{fournisseur.adresse ?? ""}</td>
                                                <td>{fournisseur.ville ?? ""}</td>
                                                <td>{fournisseur.pays ?? ""}</td>
                                                <td className="text-right">
                                                    <Link className="text-success" to={"/suivi-import/info/" + fournisseur.id + "/fournisseur"}><i className="fa fa-eye"></i> </Link>
                                                    <Link to={"#"} onClick={() => onRemove(fournisseur.id)} className="text-danger"><i className="fa fa-trash"></i> </Link>
                                                </td>
                                            </tr>
                                        ))}
                                        {filteredFournisseurs.length === 0 && (
                                            <tr>
                                                <td colSpan={12} className="text-center">Aucun fournisseur trouvé</td>
                                            </tr>
                                        )}
                                    </tbody>
                                </table>
                            </div>
                            <Pagination update={setPage} paginator={fournisseurs} />
                        </Wrapper>
                    </div>
                </div>
            }
        </Layout>
    );
};

export default ListeFournisseur;