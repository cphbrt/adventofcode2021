import java.util.*;

public class One {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int count = 0;
		while(sc.hasNext()) {
			for(int i=0;i<10;i++) { // First 10 patterns
				sc.next();
			}
			sc.next(); // |
			for (int i=0;i<4;i++) { // 4 "output values" in notes
				int length = sc.next().length();
				if (length == 2 || length == 4 || length == 3 || length == 7) count++;
			}
		}
		
		System.out.println(count);
	}
}
