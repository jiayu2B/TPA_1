import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Server_N {
	static HashMap map = new HashMap();
	static int flag = 0;
	static int lock = 0;
    public static void main(String[] args) throws IOException {
    	//tell server IP address    
    	try {
	            Socket socket = new Socket(args[1], Integer.parseInt(args[2]));
	            OutputStream os = socket.getOutputStream();
	            PrintWriter pw = new PrintWriter(os);
	            pw.write("Register"+" "+ args[3]);
	            pw.flush();
	            socket.shutdownOutput();
	            InputStream is = socket.getInputStream();
	            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
	            String data = null;
	            while((data=br.readLine())!= null){
	                System.out.println("CLIENT£º"+data);
	            }
    	
	            //socket.close();
    	}catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
	    	//set connection with client
	    		try {
	                ServerSocket serverSocket1 = new ServerSocket(Integer.parseInt(args[3]));
	                Socket socket1 = null;
	                int count = 0;
	                System.out.println("***TCP Node is on, Waiting for data***");
	                
	                while(true){
	                    socket1 = serverSocket1.accept();
	                    Thread thread = new Thread(new ServerThreadTC_N(socket1));
	                    thread.start();
	                    count++;
	                    System.out.println("times:"+count);
	                    InetAddress address = socket1.getInetAddress();
	                    System.out.println("connected "+address.getHostAddress());
	                    if(flag == 1) {
	                    	serverSocket1.close();
	                    }
	                    
	                }            
	            } catch (IOException e) {
	                e.printStackTrace();
	            }   	
    
    }
    
    //function
    
	public String dput1() {
		if (lock == 0) {
			System.out.println("sucessful locked map¡£");
			lock = 1;
			return "go ahead";
		}
		else {
			System.out.println("map is already locked ");
			return "stop";
		}
	}
	public String dput2(String key, String value) {
		map.put(key, value);
		lock = 0;
		return "key-value put";
		}
	public String ddel1() {
		if (lock == 0) {
			System.out.println("sucessful locked map¡£");
			lock = 1;
			return "go ahead";
		}
		else {
			System.out.println("map is already locked ");
			return "stop";
		}
	}
	public String ddel2(String key) {
		map.remove(key);
		lock = 0;
		return "key-value put";
		}
	public String dabort() {
		lock = 0;
		return "the move aborted";
	}
	
	public String store() {
		Set set = map.keySet();
        	for(Iterator iter = set.iterator(); iter.hasNext();)
        	  {
        	   String key = (String)iter.next();
        	   String value = (String)map.get(key);
        	   System.out.println(key+"===="+value);
        	  }
		return "the map is printed";
	}
	}

