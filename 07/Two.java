import java.util.*;

// 1 2 3  4  5  6  7  8  9
// 1 3 6 10 15 21 28 36 45

// There's definitely a math-y way to do this, but... it's a race!

public class Two {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Collect array
		String[] posStrings = sc.nextLine().split(",");
		int[] pos = new int[posStrings.length];
		int left = Integer.MAX_VALUE;
		int right = Integer.MIN_VALUE;
		for (int i=0;i<pos.length; i++) {
			pos[i] = Integer.parseInt(posStrings[i]);
			if (pos[i] > right) right = pos[i];
			if (pos[i] < left) left = pos[i];
		}

		int best = Integer.MAX_VALUE;
		for (int i=left; i<=right; i++) {
			int current = 0;
			for(int p=0;p<pos.length;p++) {
				if (pos[p] > i) {
					int diff = pos[p] - i;
					for (int j=1;j<=diff;j++) {
						current += j;
					}
				}
				if (pos[p] < i) {
					int diff = i - pos[p];
					for (int j=1;j<=diff;j++) {
						current += j;
					}
				}
			}
			if (current < best) best = current;
		}
		System.out.println(best);
	}
}
