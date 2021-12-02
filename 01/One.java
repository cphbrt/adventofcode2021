import java.util.Scanner;

public class One {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int increases = 0;
		int previous = Integer.parseInt(sc.nextLine());
		while (sc.hasNextLine()) {
			int next = Integer.parseInt(sc.nextLine());
			if (next > previous) {
				increases++;
			}
			previous = next;
		}
		System.out.println(increases);
	}
}
