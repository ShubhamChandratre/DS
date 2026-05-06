import java.util.Scanner;

class TokenRing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of processes (n): ");
        int n = sc.nextInt();
        
        boolean[] need = new boolean[n];
        System.out.println("Enter which processes need Critical Section (true/false):");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + i + " needs CS? (true/false): ");
            need[i] = sc.nextBoolean();
        }
        
        int token = 0;


        System.out.println("Token starts at process " + token);

        // run 2 rounds
        for (int r = 0; r < 2; r++) {
            System.out.println("\nRound " + (r + 1));

            for (int i = 0; i < n; i++) {

                System.out.println("Token at process " + token);

                if (need[token]) {
                    System.out.println("Process " + token + " using Critical Section");
                    need[token] = false;   // reset
                } else {
                    System.out.println("Process " + token + " does not need CS");
                }

                // pass token
                token = (token + 1) % n;
            }
        }

        System.out.println("\nExecution Completed");
        sc.close();
    }
}