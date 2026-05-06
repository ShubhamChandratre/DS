import java.util.*;

public class TokenRing
{

    static boolean[] alive;
    static int n;

    static void election(int start)
    {
        System.out.println("Start Election from"+ start);

        int i=start;
        int max=start;

        do{

            if(alive[i]){
                System.out.println(start+"->"+i);
                if(i>max) max=i;
            }
            i=(i+1)%n;
        } while(i!=start);

        System.out.println("\nCoordinator (Ring leader):"+max);

        i=(max+1)%n;

        while(i!=max)
        {
            if(alive[i]){
                System.out.println(max+"=>"+i);
            }
            i=(i+1)%n;
        }
    }

    static void showStatus()
    {
        System.out.println("Status");
        for(int i=0;i<n;i++)
        {
            System.out.println(i+(alive[i]?"UP":"DOWN"));
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter Number of Processes:");
        n=sc.nextInt();

        alive=new boolean[n];
        Arrays.fill(alive,true);

        int choice;


        System.out.println("MENU---------------");
        System.out.println("1. UP a Process");
        System.out.println("2. DOWN a Process");
        System.out.println("3. Elect a Leader");
        System.out.println("4. Show Status");
        System.out.println("5. EXIT");

        do{

            System.out.println("Enter your choice:");
            choice=sc.nextInt();

            switch(choice){

                case 1:
                    System.out.println("Enter process to UP:");
                    alive[sc.nextInt()]=true;
                    break;

                case 2:
                    System.out.println("Enter Process to DOWN:");
                    alive[sc.nextInt()]=false;
                    break;

                case 3:
                    System.out.println("Start Election from:");
                    int p=sc.nextInt();

                    if(!alive[p]){
                        System.out.println("Process is DOWN");
                    }
                    else{
                        election(p);
                    }
                    break;

                case 4:
                    showStatus();
                    break;

                case 5:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid Input");
            }
        }while(choice!=5);

    }
}