import java.util.Scanner;

public class One {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int horizontal = 0;
		int depth = 0;
		while (sc.hasNext()) {
			String direction = sc.next();
			int magnitude = Integer.parseInt(sc.next());

			if (direction.equals("forward")) horizontal += magnitude;
			if (direction.equals("down")) depth += magnitude;
			if (direction.equals("up")) depth -= magnitude;
		}
		System.out.println(horizontal * depth);
	}
}
