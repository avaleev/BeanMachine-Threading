package Threading;

import java.util.concurrent.TimeUnit;

public class Threading {
    static final int sizea = 2000;
    static final int sizeb = 2000;

    static double[][] matrixA = new double[sizea][sizeb];
    static double[][] matrixB = new double[sizea][sizeb];

    static double[][] matrixC = new double[sizea][sizeb];
    static double[][] matrixD = new double[sizea][sizeb];

    public static void main(String[] args){
        for(int i = 0; i < sizea; i++){
            for(int j = 0; j < sizeb; j++){
                matrixA[i][j] = Math.random() * 20;
                matrixB[i][j] = Math.random() * 20;
            }
        }
        System.out.println("Matrixes have been initialized.");

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                long start = System.nanoTime();
                matrixC = parallelAddMatrix(matrixA, matrixB);
                long elapsedTime = System.nanoTime() - start;

                TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
                System.out.println("Matrixes have been added parallely. It took " + elapsedTime/1e9 + " seconds.");
            }
        });
        thread1.start();

        long start = System.nanoTime();
        matrixD = parallelAddMatrix(matrixA, matrixB);
        long elapsedTime = System.nanoTime() - start;

        System.out.println("Matrixes have been added sequentially. It took " + elapsedTime/1e9 + " seconds.");
    }

    public static double[][] parallelAddMatrix(double[][] a, double[][] b){
        double[][] result = new double[sizea][sizeb];

        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }
}
