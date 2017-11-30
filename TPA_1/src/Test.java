import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class Test {
	static HashMap map = new HashMap();
		public static void main(String[] args) throws IOException {
		map.put("aaa","aaaaaa");
		Set set = map.keySet();
    	for(Iterator iter = set.iterator(); iter.hasNext();)
    	  {
    	   String key = (String)iter.next();
    	   String value = (String)map.get(key);
    	   System.out.println(key+"===="+value);
    	  }
		}
		}
