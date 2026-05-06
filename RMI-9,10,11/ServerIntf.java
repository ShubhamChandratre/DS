import java.rmi.*;

public interface ServerIntf extends Remote{
    String compareLargest(String s1, String s2) throws RemoteException;
}

//to run
//javac *java
//rmiregistry
//java Server
//java Client