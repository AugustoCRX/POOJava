import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


class Treatment extends DataFrame {
    protected Treatment(String filename) throws FileNotFoundException {
        super(filename);
    }

//     Método responsável por tratar a coluna
    public void createColumn(DataFrame df) {
        int[] matrixSize = df.getSize(false);
        int numRows = matrixSize[1];
        int numCols = matrixSize[0] + 1;
        List<String> column1 = new ArrayList<String>();
        List<String> column2 = new ArrayList<String>();
        for (int i = 1; i < numRows; i++) {
            List<String> myList = new ArrayList<String>(Arrays.asList(df.rows[i][19].split("")));
            System.out.println(i);
            for (int j = 0; j < 4; j++){
                if (j == 0){
                column1.add(myList.get(j));
            } else {
                column2.add(myList.get(j));
                }
            }
        }
        System.out.println(column1);
//        System.out.println(column2);
    }


}
