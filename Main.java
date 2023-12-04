import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
//            DataFrame df = new DataFrame("SINAN-VIOL-2017-2019.csv");
//            System.out.println(df.rows[0][19]);
//            System.out.println("Arquivo encontrado!");
//            df.getSize(true);
//            df.showColumns();
//            df.removeColumns(1);
//            df.showColumns();
//            df.getSize(true);
            Treatment t1 = new Treatment("SINAN-VIOL-2017-2019.csv");
            t1.createColumn(t1);
//            HashMap<String, String> renameHash = new HashMap<String, String>();
//            renameHash.put("TO", "Valor a ser substituido");
//            df.renameColumns(0, renameHash);
//            df.showColumn(0);
        } catch (FileNotFoundException exc) {
            System.out.println("Arquivo não encontrado");
        }
    }
}
