import java.util.Scanner;

public class Two {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int increases = 0;

		int a = 0;
		int b = Integer.parseInt(sc.nextLine());
		int c = Integer.parseInt(sc.nextLine());
		int d = Integer.parseInt(sc.nextLine());
		do {
			a = b;
			b = c;
			c = d;
			d = Integer.parseInt(sc.nextLine());

			if (a+b+c < b+c+d) {
				increases++;
			}
		} while (sc.hasNextLine());
		System.out.println(increases);
	}
}
