import java.util.*;

public class One {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Map<Character, Integer> errorScore = Map.of(
			')', 3,
			']', 57,
			'}', 1197,
			'>', 25137
		);

		int score = 0;
		while(sc.hasNextLine()) {
			char[] line = sc.nextLine().toCharArray();

			ArrayDeque<Character> stack = new ArrayDeque<Character>();

			for (char c : line) {
				if (c == '(') stack.push(')');
				else if (c == '[') stack.push(']');
				else if (c == '{') stack.push('}');
				else if (c == '<') stack.push('>');
				else {
					char pop = stack.pop();
					if (pop != c) {
						score += errorScore.get(c);
					}
				}
			}
		}
		System.out.println(score);
	}
}
