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

public class ServerThreadTC_N implements Runnable{

    Socket socket = null;
        
    public ServerThreadTC_N(Socket socket) {
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
            Server_N sv = new Server_N();
            while((data=br.readLine()) != null){
            		String [] b = data.split(" ");
            		
            		switch (b[0]) {
	                    case "dput1":
	                    	line = sv.dput1();
	                    	break;
	                    case "dput2":
	                    	line = sv.dput2(b[1],b[2]);
	                    	break;
	                    case "ddel1":
	                    	line = sv.ddel1();
	                    	break;
	                    case "ddel2":
	                    	line = sv.ddel2(b[1]);
	                    	break;
	                    case "dabort":
	                    	line = sv.dabort();
	                    	break;
	                    case "store":
	                    	line = sv.store();
	                    	break;
            		}
                System.out.println("Server_N get: "+data);
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