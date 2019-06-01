package ru.pifagors;


import javafx.beans.property.SimpleStringProperty;

import java.io.*;

public class DocData implements Externalizable {
    private SimpleStringProperty dtSklad = new SimpleStringProperty("");
    private SimpleStringProperty dtSupplier = new SimpleStringProperty("");
    private SimpleStringProperty dtNomNakl = new SimpleStringProperty("");
    private SimpleStringProperty dtDateNakl = new SimpleStringProperty("");
    private SimpleStringProperty ddDrags = new SimpleStringProperty("");
    private SimpleStringProperty ddSeria = new SimpleStringProperty("");
    private SimpleStringProperty ddSrok = new SimpleStringProperty("");
    private SimpleStringProperty ddKol = new SimpleStringProperty("");
    private SimpleStringProperty box = new SimpleStringProperty("");

    public DocData(String dtSklad, String dtSupplier, String dtNomNakl, String dtDateNakl, String ddDrags, String ddSeria, String ddSrok, String ddKol, String box) {
        this.dtSklad = new SimpleStringProperty(dtSklad);
        this.dtSupplier = new SimpleStringProperty(dtSupplier);
        this.dtNomNakl = new SimpleStringProperty(dtNomNakl);
        this.dtDateNakl = new SimpleStringProperty(dtDateNakl);
        this.ddDrags = new SimpleStringProperty(ddDrags);
        this.ddSeria = new SimpleStringProperty(ddSeria);
        this.ddSrok = new SimpleStringProperty(ddSrok);
        this.ddKol = new SimpleStringProperty(ddKol);
        this.box = new SimpleStringProperty(box);
    }

    public DocData() {
    }

    public String getDtSklad() {
        return dtSklad.get();
    }

    public void setDtSklad(String dtSklad) {
        this.dtSklad.set(dtSklad);
    }

    public String getDtSupplier() {
        return dtSupplier.get();
    }

    public void setDtSupplier(String dtSupplier) {
        this.dtSupplier.set(dtSupplier);
    }

    public String getDtNomNakl() {
        return dtNomNakl.get();
    }

    public void setDtNomNakl(String dtNomNakl) {
        this.dtNomNakl.set(dtNomNakl);
    }

    public String getDtDateNakl() {
        return dtDateNakl.get();
    }

    public void setDtDateNakl(String dtDateNakl) {
        this.dtDateNakl.set(dtDateNakl);
    }

    public String getDdDrags() {
        return ddDrags.get();
    }

    public void setDdDrags(String ddDrags) {
        this.ddDrags.set(ddDrags);
    }

    public String getDdSeria() {
        return ddSeria.get();
    }

    public void setDdSeria(String ddSeria) {
        this.ddSeria.set(ddSeria);
    }

    public String getDdSrok() {
        return ddSrok.get();
    }

    public void setDdSrok(String ddSrok) {
        this.ddSrok.set(ddSrok);
    }

    public String getDdKol() {
        return ddKol.get();
    }

    public void setDdKol(String ddKol) {
        this.ddKol.set(ddKol);
    }

    public String getBox() {
        return box.get();
    }

    public void setBox(String box) {
        this.box.set(box);
    }

    public SimpleStringProperty skladProperty() {return dtSklad;}
    public SimpleStringProperty supplierProperty() {return dtSupplier;}
    public SimpleStringProperty nomNaklProperty() {return dtNomNakl;}
    public SimpleStringProperty dateNaklProperty() {return dtDateNakl;}
    public SimpleStringProperty dragsProperty() {return ddDrags;}
    public SimpleStringProperty seriaProperty() {return ddSeria;}
    public SimpleStringProperty srokProperty() {return ddSrok;}
    public SimpleStringProperty kolProperty() {return ddKol;}
    public SimpleStringProperty boxProperty() {return box;}

    @Override
    public String toString() {
        return "DocData{" +
                "dtSklad=" + dtSklad +
                ", dtSupplier=" + dtSupplier +
                ", dtNomNakl=" + dtNomNakl +
                ", dtDateNakl=" + dtDateNakl +
                ", ddDrags=" + ddDrags +
                ", ddSeria=" + ddSeria +
                ", ddSrok=" + ddSrok +
                ", ddKol=" + ddKol +
                ", box=" + box +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String strSklad = dtSklad.get();
        out.writeObject(strSklad);
        String strSupplier = dtSupplier.get();
        out.writeObject(strSupplier);
        String strNomNakl = dtNomNakl.get();
        out.writeObject(strNomNakl);
        String strDateNakl = dtDateNakl.get();
        out.writeObject(strDateNakl);
        String strDrags = ddDrags.get();
        out.writeObject(strDrags);
        String strSeria = ddSeria.get();
        out.writeObject(strSeria);
        String strSrok = ddSrok.get();
        out.writeObject(strSrok);
        String strKol = ddKol.get();
        out.writeObject(strKol);
        String strBox = box.get();
        out.writeObject(strBox);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        dtSklad = new SimpleStringProperty((String) in.readObject());
        dtSupplier = new SimpleStringProperty((String) in.readObject());
        dtNomNakl = new SimpleStringProperty((String) in.readObject());
        dtDateNakl = new SimpleStringProperty((String) in.readObject());
        ddDrags = new SimpleStringProperty((String) in.readObject());
        ddSeria = new SimpleStringProperty((String) in.readObject());
        ddSrok = new SimpleStringProperty((String) in.readObject());
        ddKol = new SimpleStringProperty((String) in.readObject());
        box = new SimpleStringProperty((String) in.readObject());
    }



}
