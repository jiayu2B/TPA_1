public class del {

	public static String del(String key,int port) {
		String re_ = ddel1.ddel1(port);
		if (re_.equals("go ahead")) {
			System.out.println("ddel2 ");
			ddel2.ddel2(port, key);
		}
		else {
			for(int count = 0; count < 10; count ++) {
				re_ = ddel1.ddel1(port);
				if (re_.equals("go ahead")) {
					System.out.println("dput2 ");
					ddel2.ddel2(port, key);
					count += 100;
				}
				if (count == 9) {
					dabort.dabort(port);
					break;
				}
			}
		}
		return "get del";
	}
}
