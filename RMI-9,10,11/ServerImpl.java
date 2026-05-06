import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf
{
    public ServerImpl() throws RemoteException {}

    public String compareLargest(String s1, String s2) throws RemoteException
    {
        return s1.compareTo(s2) > 0? s1:s2;
    }

    public int countVowels(String word) throws RemoteException {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : word.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public long factorial(int n) throws RemoteException {
        if (n < 0) {
            return -1;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}