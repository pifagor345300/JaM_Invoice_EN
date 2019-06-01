package ru.pifagors.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.converter.DateStringConverter;
import ru.pifagors.DocData;
import ru.pifagors.model.LineNumbersCellFactory;
import ru.pifagors.Main;
import ru.pifagors.model.Serializ;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class Controller {
    public String s;
    @FXML
    private Button seachButton;
    @FXML
    private Button copyButton;
    @FXML
    private Button printButton;
    @FXML
    private TextField txtNumNakl;
    @FXML
    private DatePicker txtDateN;
    @FXML
    private TextField txtNumberBox;
    @FXML
    private TableView tableNakl;
    @FXML
    private TableColumn<DocData, String> columnNomPP;
    @FXML
    private TableColumn<DocData, String> columnName;
    @FXML
    private TableColumn<DocData, String> columnSeria;
    @FXML
    private TableColumn<DocData, String> columnKol;
    @FXML
    private TableColumn<DocData, String> columnBox;


    @FXML
    private void initialize() {
        tableNakl.setEditable(true);
        columnName.setCellValueFactory(new PropertyValueFactory<DocData, String>("ddDrags"));
        columnSeria.setCellValueFactory(new PropertyValueFactory<DocData, String>("ddSeria"));
        columnKol.setCellValueFactory(new PropertyValueFactory<DocData, String>("ddKol"));
        columnKol.setCellFactory(TextFieldTableCell.forTableColumn());
        columnKol.setOnEditCommit(
                new EventHandler<CellEditEvent<DocData, String>>() {
                    @Override
                    public void handle(CellEditEvent<DocData, String> t) {
                        ((DocData) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDdKol(t.getNewValue());
                    }
                }
        );
        columnBox.setCellValueFactory(new PropertyValueFactory<DocData, String>("box"));
        columnNomPP.setCellFactory(new LineNumbersCellFactory());

        tableNakl.setItems(Main.docDataObservableList);

        txtDateN.setValue(LocalDate.now());

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        DateStringConverter converter = new DateStringConverter(dateFormat);

        TextFormatter<Date> formatter = new TextFormatter<>(converter, null, c -> {

            if (c.isContentChange()) {
                // auto parse
                if (c.getControlNewText().length() == 10) {
                    try {
                        dateFormat.parse(c.getControlNewText());
                    } catch (ParseException ex) {
                        c.getControl().setStyle("-fx-background-color: red;");
                    }
                }
                else{
                    c.getControl().setStyle(null);
                }
            }
            if (c.isAdded()) {
                // length restriction
                if (c.getControlNewText().length() > 10)
                {
                    return null;
                }

                // auto mask
                int caretPosition = c.getCaretPosition();
                if (caretPosition == 2 || caretPosition == 5) {
                    c.setText(c.getText() + ".");
                    c.setCaretPosition(c.getControlNewText().length());
                    c.setAnchor(c.getControlNewText().length());
                }
            }
            return c;
        });
        txtDateN.getEditor().setTextFormatter(formatter);

        txtDateN.setOnAction(event -> {
            LocalDate date = txtDateN.getValue();
//            System.out.println("Selected date: " + date);
        });


    }




    @FXML
    private void edit() {

            DocData selectedDocData = (DocData) tableNakl.getSelectionModel().getSelectedItem();
            String str = selectedDocData.getBox();
        if (str.equals("-")) {
            String strBox = txtNumberBox.getText();
            selectedDocData.setBox(strBox);
        }
    }

    @FXML
    private void toNull() {
        DocData selectedDocData = (DocData) tableNakl.getSelectionModel().getSelectedItem();
        selectedDocData.setBox("-");
    }

    public void btnDo(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;
        DocData selectedDocData = (DocData) tableNakl.getSelectionModel().getSelectedItem();
        switch (clickedButton.getId()) {
            case "seachButton":
                s = txtDateN.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Main.workNakls(txtNumNakl.getText(), s);
                tableNakl.setItems(Main.docDataObservableList);
                break;

            case "printButton":
                Main.reportsGenerator();
                Serializ.serWrite(txtNumNakl.getText(), s);
                break;

            case "copyButton":
                Main.docDataObservableList.add(new DocData(selectedDocData.getDtSklad(), selectedDocData.getDtSupplier(), selectedDocData.getDtNomNakl(),
                        selectedDocData.getDtDateNakl(), selectedDocData.getDdDrags(), selectedDocData.getDdSeria(), selectedDocData.getDdSrok(),
                        "0", "-"));
                Main.docDataList = new ArrayList<>(Main.docDataObservableList);
                break;
        }
    }


}
