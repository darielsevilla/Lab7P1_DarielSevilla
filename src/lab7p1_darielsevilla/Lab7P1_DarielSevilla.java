package lab7p1_darielsevilla;

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.security.SecureRandom;

public class Lab7P1_DarielSevilla {

    static Scanner lea = new Scanner(System.in);
    static SecureRandom random = new SecureRandom();

    public static int[][] newMatriz(int len, int wid) {
        int[][] resp = new int[len][wid];

        for (int i = 0; i < resp.length; i++) {
            for (int j = 0; j < resp[i].length; j++) {
                resp[i][j] = random.nextInt(10);
            }
            
            
        }

        return resp;
    }

    public static String print(int[][] par) {
        String resp = "";
        for (int i = 0; i < par.length; i++) {
            for (int j = 0; j < par[i].length; j++) {
                resp += String.format("[%d]", par[i][j]);
            }
            resp += "\n";
        }
        return resp;
    }

    public static int[][] portrait(int[][] par) {
        int[][] rotar = new int[par[0].length][par.length];

        for (int i = 0; i < rotar.length; i++) {
            for (int j = 0; j < rotar[i].length; j++) {
                rotar[i][j] = par[par.length - (j + 1)][i];
            }
        }
        return rotar;
    }

    public static int numeroMagico(int[][] par) {
        int resp;
        int op1 = 0;
        int op2 = 1;
        for (int i = 0; i < par.length; i++) {
            for (int j = 0; j < par[i].length; j++) {
                if (i == 0 || i == par.length - 1 || j == 0 || j == par[i].length - 1) {
                    op1 += par[i][j];
                } else {
                    op2 *= par[i][j];
                }
            }
        }

        String format = String.format("Op1: %d\nOp2: %d", op1, op2);
        JOptionPane.showMessageDialog(null, format);
        resp = op1 + op2;
        return resp;
    }

    public static int subsecuencia(String cad1, String cad2) {
        int resp = 0;
        String word1 = "-" + cad1;
        word1 = word1.toLowerCase();
        String word2 = "-" + cad2;
        word2 = word2.toLowerCase();
        
        int[][] temp = new int[word1.length()][word2.length()];
        
        
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[i].length; j++) {
                if (word1.charAt(i) == '-' && word2.charAt(j) == '-') {
                    temp[i][j] = 0;
                } else if (word1.charAt(i) != word2.charAt(j)) {
                    if (i == 0) {
                        temp[i][j] = temp[i][j - 1];
                    } else if (j == 0) {
                        temp[i][j] = temp[i - 1][j];
                    } else {
                        temp[i][j] = Math.max(temp[i - 1][j], temp[i][j - 1]);
                    }
                } else {
                    temp[i][j] = temp[i - 1][j - 1] + 1;
                }
            }
        }
        JOptionPane.showMessageDialog(null, print(temp));
        resp = temp[temp.length - 1][temp[0].length - 1];
        return resp;
    }

    public static boolean checkInt(String par) {
        try {
            Integer.parseInt(par);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int menu() {
        int op = 0;
        String men = "\n\nMenu Opciones\n";
        men += "1-Portrait\n";
        men += "2-Numero magico\n";
        men += "3-Subsecuencia\n";
        men += "4-Salida\n";
        men += "Ingrese opcion menu: ";
        String check = JOptionPane.showInputDialog(men);
        if (checkInt(check)) {
            op = Integer.parseInt(check);
        }

        return op;
    }

    public static void main(String[] args) {

        int menu;
        do {
            JOptionPane.showMessageDialog(null, "Laboratorio 7");
            menu = menu();
            //creacion de matriz aleatoria
            int fil = 0;
            int col = 0;
            String resp;
            if (menu == 1 || menu == 2) {
                do {
                    resp = JOptionPane.showInputDialog("Ingrse numero de filas de matriz: ");
                    if (checkInt(resp)) {
                        fil = Integer.parseInt(resp);
                    }
                } while (fil <= 0);

                do {
                    if(col == fil){
                        JOptionPane.showMessageDialog(null, "el valor de filas o columnas no puede ser el mismo");
                    }
                    resp = JOptionPane.showInputDialog("Ingrse numero de columnas de matriz: ");
                    if (checkInt(resp)) {
                        col = Integer.parseInt(resp);
                    }
                } while (col <= 0 || col == fil);
                
            }
            int[][] mat1 = newMatriz(fil, col);

            switch (menu) {
                case 1:

                    //impresion de salida
                    int[][] mat2 = portrait(mat1);
                    resp = String.format("Matriz generada: \n%s \n Matriz rotada: \n%s", print(mat1), print(mat2));
                    JOptionPane.showMessageDialog(null, resp);
                    break;
                case 2:
                    resp = String.format("Matriz generada:\n%s", print(mat1));
                    JOptionPane.showMessageDialog(null, resp);
                    int magic = numeroMagico(mat1);
                    resp = String.format("Numero magico: %d", magic);
                    JOptionPane.showMessageDialog(null, resp);

                    break;
                case 3:
                    String cad1 = "";
                    String cad2 = "";
                    int error = 0;
                    //validacion primera entrada
                    do {
                        error = 0;
                        cad1 = JOptionPane.showInputDialog("Ingrese primera cadena: ");
                        
                        for (int i = 0; i < cad1.length(); i++) {
                            if (Character.isAlphabetic(cad1.charAt(i)) == false) {
                                error++;
                            }
                        }

                        if (error > 0) {
                            JOptionPane.showMessageDialog(null, "Cadena no valida. Intente de nuevo");
                        }
                    } while (error != 0);

                    //validacion segunda entada
                    do {
                        error = 0;
                        cad2 = JOptionPane.showInputDialog("Ingrese segunda cadena: ");
                        
                        for (int i = 0; i < cad2.length(); i++) {
                            if (Character.isAlphabetic(cad2.charAt(i)) == false) {
                                error++;
                            }
                        }

                        if (error > 0) {
                            JOptionPane.showMessageDialog(null,"Cadena no valida. Intente de nuevo");
                        }
                    } while (error != 0);

                    int respuesta = subsecuencia(cad1, cad2);
                    resp = String.format("El size de la subsecuencia mas grande es igual a: %d", respuesta);
                    JOptionPane.showMessageDialog(null, resp);
                    break;
            }
        } while (menu != 4);
    }

}
