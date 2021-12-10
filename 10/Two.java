import java.util.*;

public class Two {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Map<Character, Long> errorScore = Map.of(
			')', 1L,
			']', 2L,
			'}', 3L,
			'>', 4L
		);

		ArrayList<Long> scores = new ArrayList<Long>();
		line: while(sc.hasNextLine()) {
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
						continue line;
					}
				}
			}

			long score = 0L;
			while (stack.size() > 0) {
				score = score * 5L + errorScore.get(stack.pop());
			}
			scores.add(score);
		}

		Collections.sort(scores);

		System.out.println(scores.get(scores.size()/2));
	}
}
