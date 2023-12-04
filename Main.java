import java.io.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        try {
            long start = System.nanoTime();
            System.out.println("Iniciando o código...");
            DataFrame df = new DataFrame("Faculdade/POO/SINAN-VIOL-2017-2019.csv");
            System.out.println(df.rows[0][19]);
            System.out.println("Arquivo encontrado!");
            df.getSize(true);
            df.showColumns();
            df.removeColumns(1);
            df.showColumns();
            df.getSize(true);
            Treatment t1 = new Treatment("Faculdade/POO/SINAN-VIOL-2017-2019.csv");
            t1.createColumn(t1);
            HashMap<String, String> renameHash = new HashMap<>();
            renameHash.put("TO", "Valor a ser substituido");
            df.renameColumns(0, renameHash);
            df.showColumn(0);
            t1.createCSV();
            long end = System.nanoTime();
            long duration = end - start;
            double timeElapsed = TimeUnit.NANOSECONDS.toSeconds(duration);
            System.out.println("\nO tempo de execução de operação e criação do arquivo tratado é de: " + timeElapsed + " segundos");
        } catch (FileNotFoundException exc) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
