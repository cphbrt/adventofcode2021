import java.util.*;

public class One {

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
				if (pos[p] > i) current += pos[p] - i;
				if (pos[p] < i) current += i - pos[p];
			}
			if (current < best) best = current;
		}
		System.out.println(best);
	}
}
