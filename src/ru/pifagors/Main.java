package ru.pifagors;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.pifagors.model.JDBCQuery;
import ru.pifagors.model.Serializ;
import ru.pifagors.view.ReportGenerator;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Main extends Application {
    public static ArrayList<DocData> docDataList = new ArrayList<>();
    public static String dName;
    public static String dDate;
    public static String docNumber;
    public static String docDate;
    public static boolean isQuery = true;
    public static ObservableList<DocData> docDataObservableList = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/Jam.fxml"));
        primaryStage.setTitle("JaM+    (c) Lashmanov A. E. (Doctor)");
        primaryStage.getIcons().add(new Image("file:Res/JaM_32.png"));
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch(args);

    }

    public static void workNakls(String dName, String dDate) throws IOException, ClassNotFoundException {
        boolean isNakl = false;

        docNumber = "\"" + dName + "\"";
        docDate = "\"" + dDate + "\"";

        File folder = new File(".\\TEMP");
        for (File f : folder.listFiles()) {
            if ((dName + "_" + dDate + ".dat").equals(f.getName())) {
                System.out.println("File found!");
                Serializ.serRead(dName, dDate);
                isNakl = true;
                break;
            }
        }

        if (!isNakl) {
            System.out.println("File not found - Run the query");
            docDataList.clear();
            JDBCQuery.Query(docNumber, docDate);
            docDataObservableList = FXCollections.observableArrayList(docDataList);
            if (!isQuery) {
                System.out.println("Invoice not found!");
                JOptionPane.showMessageDialog(null,
                        "This invoice is not in the program! Check Number and Date of Invoice!",
                        "Invoice not found!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            Serializ.serWrite(dName, dDate);

        }


//        for (DocData docData : docDataList) {
//            System.out.println(docData.getDtSklad() + " " + docData.getDtSupplier() + " " + docData.getDtNomNakl() + " " + docData.getDtDateNakl() + " " +
//                    docData.getDdDrags() + " " + docData.getDdSeria() + " " + docData.getDdSrok() + " " + docData.getDdKol() + " BOX: " + docData.getBox());
//
//        }

    }

    public static void reportsGenerator() {


        System.out.println("Start report generation.");
        try {
            new ReportGenerator().create();
            System.out.println("Report generation completed.");
        } catch (Exception e) {
            System.err.println("An error occurred during generation: " + e);
        }
    }

}
