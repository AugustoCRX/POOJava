import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            DataFrame df = new DataFrame("E:/code/java/SINAN-VIOL-2017-2019.csv");
            System.out.println("Arquivo encontrado!");
            df.getSize(true);
            df.removeColumns(df,1);
            df.showColumns();
            df.getSize(true);
        } catch (FileNotFoundException exc) {
            System.out.println("Arquivo não encontrado");
        }
    }
}
