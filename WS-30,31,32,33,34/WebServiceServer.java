import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

public class WebServiceServer
{
    public static void main(String[] args) throws Exception
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);

        //1. calc
        server.createContext("/calc", new HttpHandler() {
            public void handle(HttpExchange t) throws IOException{

                String[] params = t.getRequestURI().getQuery().split("&");

                double a =Double.parseDouble(params[0].split("=")[1]);
                double b =Double.parseDouble(params[1].split("=")[1]);
                String op =params[2].split("=")[1];

                switch(op){

                    case "add":
                        sendResponse(t,"Sum is:"+(a+b));
                        break;

                    case "sub":
                        sendResponse(t,"Subtraction is:"+(a-b));
                        break;

                    case "mul":
                        sendResponse(t,"Product is:"+(a*b));
                        break;
                    
                    case "div":
                        sendResponse(t,"Division is:"+(a/b));
                        break;
                    
                    default:
                        response="Invalid Input";
                }
            }
        });

        //2.Simple Interest
        server.createContext("/si", new HttpHandler(){
            public void handle(HttpExchange t) throws IOException {

                String[] params=t.getRequestURI().getQuery().split("&");
                double p= Double.parseDouble(params[0].split("=")[1]);
                double r= Double.parseDouble(params[1].split("=")[1]);
                double time= Double.parseDouble(params[2].split("=")[1]);

                sendResponse(t,"Simple Interese is:"+(p*r*time/100));
            }
        });

        //3. Hello Name
        server.createContext("/hello", new HttpHandler() {
            public void handle(HttpExchange t) throws IOException{

                String name=t.getRequestURI().getQuery().split("=")[1];
                sendResponse(t, "Hello "+name);
            }
        });

        //4, 5. Miles to Km
        server.createContext("/km", new HttpHandler(){
            public void handle(HttpExchange t) throws IOException{

                double miles=Double.parseDouble(t.getRequestURI().getQuery().split("=")[1]);
                sendResponse(t, "Km is:"+ (miles*1.60934));
            }
        });

        server.start();
        System.out.println("Server Started on port 8080");

    }

    static void sendResponse(HttpExchange t, String text) throws IOException
    {
        t.sendResponseHeaders(200,text.length());
        OutputStream os=t.getResponseBody();
        os.write(text.getBytes());
        os.close();
    }
}