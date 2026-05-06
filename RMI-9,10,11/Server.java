import java.rmi.*;

public class Server
{
    public static void main(String[] args)
    {
        try{
            ServerImpl simp=new ServerImpl();
            Naming.rebind("Server",simp);
            System.out.println("Server is ready");
        }
        catch(Exception e){
            System.out.println("Error:"+e.getMessage());
        }
    }
}