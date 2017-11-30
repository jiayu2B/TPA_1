import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ServerThreadTC implements Runnable{

    Socket socket = null;
        
    public ServerThreadTC(Socket socket) {
    this.socket = socket;
}

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is,"GBK");
            br = new BufferedReader(isr);
            
            String data = null;
            String line = "unknow message";
            Server_L sv = new Server_L();
            while((data=br.readLine()) != null){
            		String [] b = data.split(" ");
            		
            		switch (b[0]) {
                    case "put":
                    	line = sv.put(b[1],b[2]);
                   		break;
                    case "del":
                    	line = sv.del(b[1]);
                    	break;
                    case "store":
                    	line = sv.store();
                    	break;
                    case "Register":
                    	line = sv.Reg(b[1]);
                    	break;
            		}
                System.out.println("Server_L get: "+data);
            }
            socket.shutdownInput();
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write(line);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        
    }

}