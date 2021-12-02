import java.util.Scanner;

public class Two {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int aim = 0;
		int horizontal = 0;
		int depth = 0;
		while (sc.hasNext()) {
			String direction = sc.next();
			int magnitude = Integer.parseInt(sc.next());

			if (direction.equals("forward")) {
				horizontal += magnitude;
				depth += aim * magnitude;
			}
			if (direction.equals("down")) aim += magnitude;
			if (direction.equals("up")) aim -= magnitude;
		}
		System.out.println(horizontal * depth);
	}
}
