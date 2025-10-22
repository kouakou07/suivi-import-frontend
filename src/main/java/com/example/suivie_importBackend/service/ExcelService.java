package com.example.suivie_importBackend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {
    

//    public String[] rowTitles(){
//
//        return new String[]{
//            "Reference Remise",
//            "Borne",
//            "Code Banque",
//            "Code Agence",
//            "Numero Compte",
//            "Rib",
//            "Montant",
//            "Code Rejet",
//            "Motif rejet",
//            "Code banque remettant",
//            "Code Agence remettant",
//            "Compte remettant",
//            "Action",
//            "Login user",
//            "Nom complet"
//        };
//    }


//    public String[] checkDataString(ChequeM check, UserM user, Integer etat){
//
//
//        return new String[]{
//            check.getRemise().getReference(),
//            check.getRemise().getBorne().getLibelle(),
//            check.getCodeBanque(),
//            check.getCodeAgence(),
//            check.getReferenceClient(),
//            check.getRib(),
//            check.getMontant().toString(),
//            check.getCodeRejet(),
//            check.getLibelleRejet(),
//            check.getRemise().getCompteRemettant().getCodeBank(),
//            check.getRemise().getCompteRemettant().getCodeAgence(),
//            check.getRemise().getCompteRemettant().getNumeroCmpt(),
//            TrackCheck.EtatInString(etat),
//            user.getUsername(),
//            user.getNom() + " "+ user.getPrenom()
//        };
//    }

//    public void writeExcelFile(String path, List<CheckActivityM> chequesActivies){
//        try{
//            XSSFWorkbook bWorkbook = new XSSFWorkbook();
//            XSSFSheet bSheet = bWorkbook.createSheet("monitor");
//            Row row = bSheet.createRow(0);
//            String[] names = this.rowTitles();
//            int column = 0;
//            for (String name : names) {
//                Cell cell = row.createCell(column);
//                cell.setCellValue(name);
//                column++;
//            }
//            Integer rowCount = 1;
//            for (CheckActivityM checkActivity : chequesActivies) {
//                ChequeM check = checkActivity.getCheque();
//                Row rowData = bSheet.createRow(rowCount);
//                String[] checkData = this.checkDataString(check, checkActivity.getUser(), checkActivity.getEtat());
//                int columnCount = 0;
//                for (String info : checkData) {
//                    Cell cel =  rowData.createCell(columnCount);
//                    cel.setCellValue(info);
//                    columnCount++;
//                }
//                rowCount++;
//            }
//
//            bWorkbook.write(new FileOutputStream(new File(path)));
//            bWorkbook.close();
//        }catch(Exception e){
//
//        }
//    }


}
