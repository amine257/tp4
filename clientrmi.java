import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            MatrixService matrixService = (MatrixService) registry.lookup("MatrixService");

            Scanner myInput = new Scanner(System.in);
            String line1 ="";
            String line2 ="";
            int[][] matrixA = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
            int[][] matrixB = { {9, 8, 7}, {6, 5, 4}, {3, 2, 1} };

            while(true) {
                System.out.println("Matrix A:");
                printMatrix(matrixA);
                System.out.println("Matrix B:");
                printMatrix(matrixB);
                System.out.println("Enter 2 Strings");
                line1 =myInput.nextLine();
                line2 =myInput.nextLine();

                Matrix a = new Matrix(matrixA);
                Matrix b = new Matrix(matrixB);
                Matrix c = matrixService.multiply(a, b); // calculate matrix C = A * B

                System.out.println(""+result);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    static void printMatrix(int M[][], int rowSize, int colSize) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++)
                System.out.print(M[i][j] + " ");

            System.out.println();
        }
    }
}
