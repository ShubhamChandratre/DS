import java.io.*;
import java.net.*;

public class WebServiceClient
{
    public static void main(String[] args)
    {
        System.out.println("Starting Client");
        callService("http://localhost:8080/calc?a=5&b=10&op=add");
        callService("http://localhost:8080/si?p=1000&r=5&t=3");
        callService("http://localhost:8080/hello?name=Shubham");
        callService("http://localhost:8080/km?miles=26.2");
        callService("http://localhost:8080/km?miles=10");

    }

    static void callService(String urlStr) {
        try {
            URL url = new URL(urlStr);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
            );

            System.out.println(urlStr + " -> " + in.readLine());
            in.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}