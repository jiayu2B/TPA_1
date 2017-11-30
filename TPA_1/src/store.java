import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class store {
	static String re_ = null;
	public static String store(int port) {
	
		try {
            Socket socket = new Socket("127.0.0.1", port);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("store");
            pw.flush();
            socket.shutdownOutput();
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            String data = null;
            
            while((data=br.readLine())!= null){
                System.out.println("Server_N£º"+data);
                if (data != null) {
                re_ = data;
                }
            }

	}catch (UnknownHostException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
		return re_;
	}
}
