//Remote Interface
//This function changes based on the question

import java.rmi.*;

public interface ServerIntf extends Remote
{
    public double Addition(double num1, double num2) throws RemoteException;
}

//to run
//javac *java
//rmiregistry
//java Server
//java Client