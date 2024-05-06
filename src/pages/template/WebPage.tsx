import { FunctionComponent } from "react";
import BodyProps from "../../types/BodyProps";


const WebPage: FunctionComponent<BodyProps> = ({title, children}) => {

    return(
         <div className="midde_cont">
             <div className="container-fluid">
               <div className="row column_title">
                  <div className="col-md-12">
                     <div className="page_title">
                        <h2>{title}</h2>
                     </div>
                  </div>
               </div>
               {children}
            </div>
                  
            {/* <div className="container-fluid">
               <div className="footer">
                  <p>Copyright © 2018 Designed by html.design. All rights reserved.<br/><br/>
                     Distributed By: <a href="https://themewagon.com/">ThemeWagon</a>
                  </p>
               </div>
            </div> */}
         </div>
    );
}

export default WebPage;