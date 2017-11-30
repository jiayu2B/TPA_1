public class put {

	public static String put(String key, String value,int port ) {
		String re_ = dput1.dput1(port);
		if (re_.equals("go ahead")) {
			System.out.println("dput2 ");
			dput2.dput2(port, key, value);
		}
		else {
			for(int count = 0; count < 10; count ++) {
				re_ = dput1.dput1(port);
				if (re_.equals("go ahead")) {
					System.out.println("dput2 ");
					dput2.dput2(port, key, value);
					count += 100;
				}
				if (count == 9) {
					dabort.dabort(port);
					break;
				}
			}
		}
		return "get put";
	}
}
