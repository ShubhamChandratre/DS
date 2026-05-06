import java.rmi.*;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        try{
            String url="rmi://localhost/Server";
            ServerIntf obj=(ServerIntf) Naming.lookup(url);

            String s1="";
            String s2="";
            System.out.println("Enter 1st string:");
            s1=sc.next();
            System.out.println("Enter 2nd string:");
            s2=sc.next();

            System.out.println("Lexiographically Largest:"+obj.compareLargest(s1,s2));
        }
        catch(Exception e){
            System.out.println("Error:"+e.getMessage());
        }
    }
}