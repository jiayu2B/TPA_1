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


public class Server_L {
	static HashMap map = new HashMap();
	static HashMap list = new HashMap();
	static int flag = 0;
	static int count = 0;
    public static void main(String[] args) throws IOException {
	    	//set connection with client
	    		try {
	                ServerSocket serverSocket1 = new ServerSocket(Integer.parseInt(args[1]));
	                Socket socket1 = null;
	                
	                System.out.println("***TCP Server is on, Waiting for data***");
	                
	                while(true){
	                    socket1 = serverSocket1.accept();
	                    Thread thread = new Thread(new ServerThreadTC(socket1));
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
	

	public String Reg(String port) {
		map.put("" + count, port);
		Set set = map.keySet();
    	for(Iterator iter = set.iterator(); iter.hasNext();)
    	  {
    	   String key = (String)iter.next();
    	   String value = (String)map.get(key);
    	   System.out.println("Node:" + key+"===="+value);
    	  }
		return "Register success"+" "+port;
	}


	public String put(String key, String value) {
		Set set = map.keySet();
    	for(Iterator iter = set.iterator(); iter.hasNext();)
    	  {
    	   String _count = (String)iter.next();
    	   String _port = (String)map.get(_count);
    	   System.out.println(_port);
    	   put.put(key,value,Integer.parseInt(_port));
    	  }
			
		return "try to put";
		
	}
	public String del(String key) {
		Set set = map.keySet();
    	for(Iterator iter = set.iterator(); iter.hasNext();)
    	  {
    	   String _count = (String)iter.next();
    	   String _port = (String)map.get(_count);
    	   System.out.println(_port);
    	   del.del(key,Integer.parseInt(_port));
    	  }
			
		return "try to put";
		
	}
	public String store() {
		Set set = map.keySet();
    	for(Iterator iter = set.iterator(); iter.hasNext();)
    	  {
    	   String _count = (String)iter.next();
    	   String _port = (String)map.get(_count);
    	   System.out.println(_port);
    	   store.store(Integer.parseInt(_port));
    	  }
			
		return "try to put";
		
	}
}
