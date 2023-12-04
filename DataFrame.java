import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DataFrame{

    String[][] rows;

    //Construtor a ser executado
    protected DataFrame(String filename) throws FileNotFoundException{
            File fileName = new File(filename);
            String filePath = String.valueOf(fileName.getAbsoluteFile());
            rows = readCSV(filePath);
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
    public void removeColumns(int columnIndex) {
        int[] matrixSize = getSize(false);
        int numRows = matrixSize[1];
        int numCols = matrixSize[0] - 1;
        String[][] resizedRows = new String[numRows][numCols];

        //Primeiro loop responsável por percorrer as linhas da matriz
        for (int i = 0; i < numRows; i++) {
            int j = 0;
            for (int k = 0; k < rows[i].length; k++) {
                if (k != columnIndex) {
                    // Checa se a variável J está dentro da dimensão do dataframe
                    if (j < numCols) {
                        resizedRows[i][j++] = rows[i][k];
                    } else {
                        // Caso ele encotre a coluna que deseja remover, ele continua o loop até o final
                        continue;
                    }
                }
            }
        }
        // Substitui as linhas da matriz do dataframe pelo dataframe redimensionado
        rows = resizedRows;
    }

    public void createColumn(ArrayList<String> columnValues) {
        int[] matrixSize = getSize(false);
        int numRows = matrixSize[1];
        int numCols = matrixSize[0] + 1;
        String[][] resizedRows = new String[numRows][numCols];

        //Primeiro loop responsável por percorrer as linhas da matriz
        //Caso o array seja vazio, ele substitui os valores da coluna criada por zeros
        if (columnValues.isEmpty()){
            for (int i = 0; i < numRows; i++){
                for (int j = 0; j <= numCols; j++){
                    if (j == numCols){
                        resizedRows[i][j] = "0";
                    } else {
                        resizedRows[i][j] = rows[i][j];
                    }
                }
            }
        //Condicional responsável por adicionar o array novo
        } else {
            try {
                for (int i = 0; i < numRows; i++){
                    for (int j = 0; j<= numCols; j++){
                        if (j == numCols){
                            resizedRows[i][j] = columnValues.get(j);
                        } else {
                            resizedRows[i][j] = rows[i][j];
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException ("Array não é da mesma dimensão do DataFrame, dimensão do DataFrame: " + Arrays.toString(getSize(true)) + " dimensão do Array: " + columnValues.size());
            }
        }

        // Substitui as linhas da matriz do dataframe pelo dataframe redimensionado
        rows = resizedRows;
    }

    public int[] getSize(boolean print){
        int columnsSize = rows[0].length;
        int rowsSize = rows.length;
        if (print){
            System.out.println("Número de linhas: " + rowsSize);
            System.out.println("Número de colunas: " + columnsSize);
        }
        int[] Size = new int[2];
        Size[0] = columnsSize;
        Size[1] = rowsSize;
        return Size;
    }
    //Método responsável por renomear as colunas
    public void renameColumns(int columnIndex, HashMap<String, String> dict){
        int[] matrixSize = getSize(false);
        int numRows = matrixSize[1];
        for (int i = 0; i < numRows; i++){
            //Verifica se algum valor está dentro do hashmap e sustitui linha a linha
            if (dict.containsKey(rows[i][columnIndex])){
                rows[i][columnIndex] = dict.get(rows[i][columnIndex]);
                }
            }
        }

    public void showColumn(int columnIndex){
        int[] matrixSize = getSize(false);
        int numRows = matrixSize[1];
        for (int i = 0; i < numRows; i++){
            System.out.println(rows[i][columnIndex]);
        }
    }

}