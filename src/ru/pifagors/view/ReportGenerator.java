package ru.pifagors.view;

import java.awt.*;
import java.io.File;

import java.io.IOException;
import java.util.*;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import ru.pifagors.Main;
import ru.pifagors.DocData;

public class ReportGenerator {
//    public static ArrayList<DocData> docDataList = new ArrayList<>();
    private final String PROJECT_PATH = "";
    private final String REPORT_pdf = ".\\report.pdf";
    private final String REPORT_pattern = ".\\jrxml\\docVklad.jrxml";

    public void create() throws JRException {
//        JDBCQueryZat dataBeanMaker = new JDBCQueryZat();
//        ArrayList<DocData> dataBeanList = dataBeanMaker.queryZat();

//        Main dataBeanMaker = new Main();
        ArrayList<DocData> dataBeanList = Main.docDataList;
        Collections.sort(dataBeanList, new Comparator<DocData>() {
            @Override
            public int compare(DocData p1, DocData p2) {
                // return p1.age+"".compareTo(p2.age+""); //sort by age
                return p1.getBox().compareTo(p2.getBox()); // if you want to short by name
            }
        });

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("DATE", new Date());

        File reportPattern = new File(PROJECT_PATH + REPORT_pattern);
        JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//		JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader() .getResourceAsStream(pathForPattern));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, PROJECT_PATH + REPORT_pdf);

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("report.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }

}
