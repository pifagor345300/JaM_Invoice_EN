package ru.pifagors.model;

import ru.pifagors.DocData;

import java.util.ArrayList;

public class JDBCQueryZat {

    public ArrayList<DocData> queryZat(){
        ArrayList<DocData> docDataList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            DocData docData = new DocData(
            ("Slad" + i),
            ("Supplier" + i),
            ("NomN 7770001"),
            ("2018-12-10"),
            ("Метамфетамин №1" + i),
            ("123456" + i),
            ("Goden 202" + i),
            ("10" + i),
                    ("1002")

            );
            docDataList.add(docData);

        }
        for (int i = 3; i < 6; i++) {
            DocData docData = new DocData(
                    ("Slad" + i),
                    ("Supplier" + i),
                    ("NomN 7770001"),
                    ("2018-12-10"),
                    ("Analgin №1" + i),
                    ("123456" + i),
                    ("Goden 202" + i),
                    ("10" + i),
                    ("1001")

            );
            docDataList.add(docData);

        }
        DocData docData = new DocData(
                ("Slad" + 77),
                ("Supplier" + 77),
                ("NomN 7770001"),
                ("2018-12-10"),
                ("Ketorol №1" + 77),
                ("123456" + 77),
                ("Goden 20" + 77),
                ("10" + 77),
                ("1001")
        );
        docDataList.add(docData);
        return docDataList;
    }
}
