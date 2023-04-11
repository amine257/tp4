import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements MatrixService {
    
    public static void main(String[] args) {
        try {
            Server obj = new Server();
            MatrixService stub = (MatrixService) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.createRegistry(8000);
            registry.bind("MatrixService", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public Matrix multiply(Matrix a, Matrix b) throws RemoteException {
        return a.multiply(b);
    }
}

interface MatrixService extends java.rmi.Remote {
    Matrix multiply(Matrix a, Matrix b) throws java.rmi.RemoteException;
}

class Matrix implements java.io.Serializable {
    private int[][] data;

    public Matrix(int[][] data) {
        this.data = data;
    }

    public Matrix multiply(Matrix b) {
        int rowA = data.length;
        int colA = data[0].length;
        int colB = b.data[0].length;

        int[][] result = new int[rowA][colB];

        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                for (int k = 0; k < colA; k++) {
                    result[i][j] += data[i][k] * b.data[k][j];
                }
            }
        }

        return new Matrix(result);
    }
}
