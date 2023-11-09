import java.io.*;
import java.util.ArrayList;

public class DataFrame{

    public String[][] rows;

    public DataFrame(String path) throws FileNotFoundException{
            rows = readCSV(path);
    }

    public String[][] readCSV(String path) throws FileNotFoundException {
        ArrayList<String[]> rows = new ArrayList<>();
    
        // Verifica se o arquivo existe
        if (!new File(path).exists()) {
            throw new FileNotFoundException("Arquivo não encontrado");
        }
    
        // Buffer responsável por ler os bytes do arquivo
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String row;
            while ((row = buffer.readLine()) != null) {
                String[] columns = row.split(",");
                rows.add(columns);
            }
        }
    
        // Tratamento do erro de I/O gerado dentrodo buffer
        catch (IOException exc) {
            System.out.println("Input invalido");
        }
    
        // Converte o Array para uma matriz bidimencional
        String[][] matrix = new String[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            matrix[i] = rows.get(i);
        }
    
        return matrix;
    }

    public void showColumns(){
        //Coleta a primeira coluna do dataframe
        String[] columnNames = rows[0];

        // Da print na primeira coluna
        for (String columnName : columnNames) {
            System.out.print(columnName + " ");
        }
        System.out.println();
    }

    //Para remover uma coluna do dataframe, é necessário recriar a matriz com 1 dimensão a menos.
    public void removeColumns(DataFrame matrix, int columnIndex) {
        int[] matrixSize = getSize(false);
        int numRows = matrixSize[1];
        int numCols = matrixSize[0] - 1; // Subtract one to remove the specified column
        String[][] resizedRows = new String[numRows][numCols];

        //Primeiro loop responsável por percorrer as linhas da matriz
        for (int i = 0; i < numRows; i++) {
            int j = 0;
            for (int k = 0; k < matrix.rows[i].length; k++) {
                if (k != columnIndex) {
                    // Checa se a variável J está dentro da dimensão do dataframe
                    if (j < numCols) {
                        resizedRows[i][j++] = matrix.rows[i][k];
                    } else {
                        // Caso ele encotre a coluna que deseja remover, ele continua o loop até o final
                        continue;
                    }
                }
            }
        }
        // Substitui as linhas da matriz do dataframe pelo dataframe redimensionado
        matrix.rows = resizedRows;
    }
    
    public int[] getSize(boolean print){
        int columnsSize = rows[0].length;
        int rowsSize = rows.length;
        if (print==true){
            System.out.println("Número de linhas: " + rowsSize);
            System.out.println("Número de colunas: " + columnsSize);
        }
        int[] Size = new int[2];
        Size[0] = columnsSize;
        Size[1] = rowsSize;
        return Size;
    }
}