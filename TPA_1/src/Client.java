import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(args[1], Integer.parseInt(args[2]));
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            switch(args[3]) {
            case "put":pw.write(args[3]+" "+args[4]+" "+args[5]);break;
            case "del":pw.write(args[3]+" "+args[4]);break;
            case "get":pw.write(args[3]+" "+args[4]);break;
            default:pw.write(args[3]);break;
            }
            pw.flush();
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            String data = null;
            while((data=br.readLine())!= null){
                System.out.println("Server_L£º"+data);
            }

            socket.close();
        
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}