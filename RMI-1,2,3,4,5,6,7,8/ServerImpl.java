import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf
{
    public ServerImpl() throws RemoteException{
    }

    public double Addition(double num1,double num2) throws RemoteException{
        return num1+num2;
    }
}

/*
1. Addition: return num1+num2
2. Multiplication: return num1*num2
3. Division: return num1/num2
4. Subtraction: return num1-num2
5. Power of 2: return (int)Math.pov(2,num1)
6. C to F: return (num1*9/5)+32
7. Miles to Km: return (num1*1.60934)
8. Hello Name: return "Hello"+name
 */