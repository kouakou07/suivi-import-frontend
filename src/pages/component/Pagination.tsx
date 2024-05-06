import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import PageProps from "../../types/PageProps";

const Pagination = ({paginator, update}: PageProps) => {

    const [paging, setPaging] = useState(null);
    const [pages, setPages] = useState<Array<any>>([]);
    const [isLoading, setIsLoading] = useState(true);
    useEffect(() => {
        if(Object.keys(paginator).length != 0){
            setPaging(paginator);
            let output: Array<any> = [];
            for (let index = paginator.pageable.pageNumber + 1; index < paginator.pageable.pageNumber + 5; index++) {
                output.push(index);
            }
            setPages(output);
        }
       
    }, [paginator]);


    const pageSelected = (num: number) => {
        update(num);
    }

    if(Object.keys(paginator).length == 0){ 
        return null;
    }
   
    return(
        <nav className="mt-3" aria-label="paginator">
            <ul className="pagination">
                <li className={"page-item " + (paginator.first == true ? "disabled" : "") }>
                    <button type="button" onClick={() => pageSelected(paginator.pageable.pageNumber - 1)} className="page-link">Precedent</button>
                </li>
                {pages.map(item => (

                    item < paginator.totalPages && <button type="button" key={item}  onClick={() => pageSelected(item)} className="page-link">{item + 1}</button>
                ))}
                <li className={"page-item "+ (paginator.last == true ? "disabled": "")  }>
                    <button type="button" className="page-link" onClick={() => pageSelected(paginator.pageable.pageNumber + 1)}>Suivant</button>
                </li>
            </ul>
        </nav>
    );
}

export default Pagination;