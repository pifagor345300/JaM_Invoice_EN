package ru.pifagors.model;

import com.intersys.objects.*;
import ru.pifagors.DocData;
import ru.pifagors.Main;

import java.sql.*;
import java.text.DecimalFormat;


public class JDBCQuery {
    private static final int DEFAULT_PORT = 1972;


    public static void Query(String docNumber, String docDate) {

        try {
            int port = determinePort();

            String url = "jdbc:Cache://192.168.0.100:" + port + "/USER";
            String user = "_SYSTEM";
            String password = "SYS";

            String stQuery = "select Kpr->LastObj->Fullname as Sklad, \n" +
                    " Docs->Titles->CodeKpr->LastObj->FullName as Perem,\n" +
                    " Docs->Titles->CodeOfSupplier->LastObj->FullName as Prod,\n" +
                    " Docs->Titles->NDok as NDok,\n" +
                    " Docs->Titles->DateReg as DDok,\n" +
                    " Drugs->LastObj->FullName as Drugs,\n" +
                    " ProductSerial as Serial,\n" +
                    " SrokGodn as Srok,\n" +
                    " Quantity as Qant\n" +
                    " from DocData\n" +
                    " where Destroyed = 0 and FlInOut = -1 and\n" +
                    " Docs->Titles->NDok = " + docNumber + " and Docs->Titles->DateReg = " + docDate + "\n" +
                    "order by Drugs";
//            String stQuery = "select * from SprNDS";


            Class.forName("com.intersys.jdbc.CacheDriver");
            Connection dbconnection = DriverManager.getConnection(url, user, password);
            Statement stmt = dbconnection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(stQuery);
            ResultSetMetaData rsmd = rs.getMetaData();

            int colnum = rsmd.getColumnCount();
            System.out.println(colnum);

            if (!rs.next()) Main.isQuery = false;
//            while (rs.next()) {
            System.out.println(rs.getFetchSize());
            do {
                DocData docData = new DocData();
                for (int i = 1; i <= colnum; i++) {
                    docData.setDtSklad(rs.getString("Sklad"));

                    //Перемещение или продажа
                    String Sklad = rs.getString("Prod");
                    String Perem = rs.getString("Perem");
                    if (Perem.equals("")) {
                        docData.setDtSupplier(Sklad);
                    } else {
                        docData.setDtSupplier(Perem);
                    }

                    docData.setDtNomNakl(rs.getString("NDok"));
                    docData.setDtDateNakl(rs.getString("DDok"));
                    docData.setDdDrags(rs.getString("Drugs"));
                    docData.setDdSeria(rs.getString("Serial"));
                    docData.setDdSrok(rs.getString("Srok"));
                    float f = Float.parseFloat(rs.getString("Qant"));
                    String formattedDouble = new DecimalFormat("#0.00").format(f);
                    docData.setDdKol(formattedDouble);
                    docData.setBox("-");

//                    System.out.print(rs.getString(i) + "  ");
                }
                Main.docDataList.add(docData);
//                System.out.println();
            } while (rs.next());


            dbconnection.close();
        } catch (Exception ex) {
            System.out.println("Caught exception: " +
                    ex.getClass().getName()
                    + ": " + ex.getMessage());
        }
    }

    private final static int determinePort()
            throws CacheException {
        String pt = System.getProperty("com.intersys.port");
        if (pt == null) {
            return DEFAULT_PORT;
        }

        int port = 0;
        try {
            port = Integer.parseInt(pt);
        } catch (NumberFormatException x) {
            throw new CacheException("Invalid default port specified in " +
                    "system properties: " + pt);
        }

        return port;
    }

}
