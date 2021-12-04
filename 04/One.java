import java.util.*;

public class One {
	public static int boardWidth = 5;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] chosenNumString = sc.nextLine().split(",");
		int[] chosen = new int[chosenNumString.length];
		for (int i=0;i<chosen.length;i++) {
			chosen[i] = Integer.parseInt(chosenNumString[i]);
		}

		int bestBoardTime = Integer.MAX_VALUE;
		int bestBoardScore = 0;

		while (sc.hasNextInt()) { // while has next board
			Set<Integer> board = new HashSet<Integer>();
			List<Set<Integer>> lines = new ArrayList<Set<Integer>>();
			// pre-load column lines
			for (int i=0;i<boardWidth;i++) {
				lines.add(new HashSet<Integer>());
			}
			Set<Integer> currentRow = new HashSet<Integer>();
			for(int i=0;i<boardWidth*boardWidth;i++) { // for each cell of board
				int cell = sc.nextInt();
				// Add cell to the relevant sets
				board.add(cell);
				lines.get(i%boardWidth).add(cell);
				currentRow.add(cell);
				if (currentRow.size() >= boardWidth) {
					lines.add(currentRow);
					currentRow = new HashSet<Integer>();
				}
			}

			// remove from sets one by one until a set is empty
			int round = -1;
			while(!anyAreEmpty(lines)) {
				round++;
				// remove from all sets
				board.remove(chosen[round]);
				for(Set<Integer> line : lines) {
					line.remove(chosen[round]);
				}
			}

			// calculate score from remaining in board set
			int sum = 0;
			for(int cell : board) sum += cell;

			// compare to previous best
			if (round < bestBoardTime) {
				bestBoardTime = round;
				bestBoardScore = sum * chosen[round];
			}
		}

		System.out.println(bestBoardScore);
	}

	static boolean anyAreEmpty(List<Set<Integer>> sets) {
		for (Set<Integer> set : sets) {
			if (set.isEmpty()) return true;
		}
		return false;
	}

}
