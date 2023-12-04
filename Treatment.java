import java.io.*;
import java.util.*;


class Treatment extends DataFrame {
    protected Treatment(String filename) throws FileNotFoundException {
        super(filename);
    }

//     Método responsável por tratar a coluna
    public void createColumn(DataFrame df) {
        int[] matrixSize = df.getSize(false);
        int numRows = matrixSize[1];
        ArrayList<String> column1 = new ArrayList<String>();
        ArrayList<String> column2 = new ArrayList<String>();
        //Código responsável por criar filtrar as colunas relacionadas a nascimento
        //As colunas de nascimento possuem formato de 4013
        //Sendo que o primeiro digito é igual a: 4 - Ano 3 - Mês 2 - Hora 1 - Minutos
        //O resto dos números corresponde a idade da pessoa.
        for (int i = 1; i < numRows; i++) {
            String matrix_str = (String) df.rows[i][19];
            List<String> myList = new ArrayList<String>(Arrays.asList(matrix_str.split("")));
            column1.add((String) myList.get(0));
            if (Objects.equals(myList.get(1), "0") && !Objects.equals(myList.get(2), "0")) {
                column2.add(myList.get(2) + myList.get(3));
            } else if (Objects.equals(myList.get(2), "0")) {
                column2.add(myList.get(3));
            } else {
                column2.add(myList.get(1) + myList.get(2) + myList.get(3));
            }
        }
            HashMap<String, String> renameMapFirstDigit = new HashMap<String, String>();
            renameMapFirstDigit.put("1","Minutos");
            renameMapFirstDigit.put("2", "Horas");
            renameMapFirstDigit.put("3","Meses");
            renameMapFirstDigit.put("4","Anos");
            column1.replaceAll(renameMapFirstDigit::get);
            column1.add(0, "TIPO DE IDADE");
            column2.add(0,"IDADE");
            df.removeColumns(19);
            df.createColumn(column1);
            df.createColumn(column2);
            df.showColumns();
    }



}
