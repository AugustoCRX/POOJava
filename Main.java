import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            DataFrame df = new DataFrame("SINAN-VIOL-2017-2019.csv");
            System.out.println("Arquivo encontrado!");
            df.getSize(true);
            df.removeColumns(1);
            df.showColumns();
            df.getSize(true);
//            HashMap<String, String> renameHash = new HashMap<String, String>();
//            df.renameColumns(0, renameHash);
//            df.showColumn(0);
        } catch (FileNotFoundException exc) {
            System.out.println("Arquivo não encontrado");
        }
    }
}
