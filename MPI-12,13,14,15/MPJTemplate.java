import mpi.*;

public class MPJTemplate {

    public static void main(String[] args) throws Exception {

        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[] sendData = new int[size];
        int[] recvData = new int[1];

        int operation = 4; // 1=SUM, 2=MULTIPLY, 3=AVERAGE, 4=RECIPROCAL

        // ---------------- INPUT (ROOT PROCESS) ----------------
        if(rank == 0) {
            System.out.print("Input Array: ");
            for(int i = 0; i < size; i++) {
                sendData[i] = (i + 1) * 10;  // 10,20,30,...
                System.out.print(sendData[i] + " ");
            }
            System.out.println("\n");
        }

        // ---------------- DISTRIBUTION ----------------
        MPI.COMM_WORLD.Scatter(
            sendData, 0, 1, MPI.INT,
            recvData, 0, 1, MPI.INT,
            0
        );

        // Each process prints what it received
        System.out.println("Process " + rank + " received: " + recvData[0]);

        // ---------------- OPERATIONS ----------------

        // SUM
        if(operation == 1) {
            int[] result = new int[1];

            MPI.COMM_WORLD.Reduce(
                recvData, 0,
                result, 0,
                1,
                MPI.INT,
                MPI.SUM,
                0
            );

            if(rank == 0) {
                System.out.println("\nFinal Result (Sum) = " + result[0]);
            }
        }

        // MULTIPLICATION
        else if(operation == 2) {
            int[] result = new int[1];

            MPI.COMM_WORLD.Reduce(
                recvData, 0,
                result, 0,
                1,
                MPI.INT,
                MPI.PROD,
                0
            );

            if(rank == 0) {
                System.out.println("\nFinal Result (Multiplication) = " + result[0]);
            }
        }

        // AVERAGE
        else if(operation == 3) {
            int[] result = new int[1];

            MPI.COMM_WORLD.Reduce(
                recvData, 0,
                result, 0,
                1,
                MPI.INT,
                MPI.SUM,
                0
            );

            if(rank == 0) {
                double avg = (double) result[0] / size;
                System.out.println("\nFinal Result (Average) = " + avg);
            }
        }

        // RECIPROCAL
        else if(operation == 4) {
            double[] local = new double[1];
            double[] gathered = new double[size];

            local[0] = 1.0 / recvData[0];

            MPI.COMM_WORLD.Gather(
                local, 0, 1, MPI.DOUBLE,
                gathered, 0, 1, MPI.DOUBLE,
                0
            );

            if(rank == 0) {
                System.out.print("\nFinal Result (Reciprocal Array): ");
                for(double v : gathered) {
                    System.out.print(v + " ");
                }
                System.out.println();
            }
        }

        MPI.Finalize();
    }
}

/* to run
1. compile code
2. Javac -cp .:$MPJ_HOME/lib/mpj.jar MPJTemplate.java
3. run program - mpjrun.sh -np 4 -cp . MPJTemplate