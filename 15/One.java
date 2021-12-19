import java.util.*;

public class One {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// READ CAVERN
		ArrayList<int[]> rowList = new ArrayList<int[]>();
		while(sc.hasNextLine()) {
			char[] charArray = sc.nextLine().toCharArray();
			int[] colArray = new int[charArray.length];
			for(int i=0;i<charArray.length;i++) {
				colArray[i] = Integer.parseInt(String.valueOf(charArray[i]));
			}
			rowList.add(colArray);
		}
		int[][] cavern = rowList.toArray(new int[rowList.size()][]);

		// SETUP
		cavern[0][0] = 0;
		int[][] weight = new int[cavern.length][cavern[0].length];
		for(int y=0;y<weight.length;y++) {
			for(int x=0;x<weight[y].length;x++) {
				weight[y][x] = Integer.MAX_VALUE;
			}
		}
		weight[0][0] = 0;
		// print(weight);

		int[] start = new int[] { 0, 0 };
		int[] dest = new int[] { cavern.length-1, cavern[0].length-1 };

		// LAZY APPROXIMATE OF DIJKSTRA
		int[] current = start;

		boolean changed = true;
		while (changed) {
			changed = false;
			for (int y=0;y<cavern.length;y++) {
				for (int x=0;x<cavern[0].length;x++) {
					changed = changed || weighNeighbors(new int[] { y, x }, cavern, weight);
				}
			}
		}
		// weighNeighbors(current, cavern, weight);
		// print(weight);
		System.out.println(weight[weight.length-1][weight[0].length-1]);
	}

	static boolean weighNeighbors(int[] current, int[][] cavern, int[][] weight) {
		boolean changed = false;
		if (current[0] != 0) { // above
			// if (current[1] != 0) { // left
			// 	if (weight[current[0]][current[1]] + cavern[current[0]-1][current[1]-1] < weight[current[0]-1][current[1]-1]) {
			// 		weight[current[0]-1][current[1]-1] = weight[current[0]][current[1]] + cavern[current[0]-1][current[1]-1];
			// 		changed = true;
			// 	}
			// }
			// horizontal same
			if (weight[current[0]][current[1]] + cavern[current[0]-1][current[1]] < weight[current[0]-1][current[1]]) {
				weight[current[0]-1][current[1]] = weight[current[0]][current[1]] + cavern[current[0]-1][current[1]];
				changed = true;
			}
			// if (current[1] != weight[0].length-1) { // right
			// 	if (weight[current[0]][current[1]] + cavern[current[0]-1][current[1]+1] < weight[current[0]-1][current[1]+1]) {
			// 		weight[current[0]-1][current[1]+1] = weight[current[0]][current[1]] + cavern[current[0]-1][current[1]+1];
			// 		changed = true;
			// 	}
			// }
		}
		// vertical same
		if (current[1] != 0) { // left
			if (weight[current[0]][current[1]] + cavern[current[0]][current[1]-1] < weight[current[0]][current[1]-1]) {
				weight[current[0]][current[1]-1] = weight[current[0]][current[1]] + cavern[current[0]][current[1]-1];
				changed = true;
			}
		}
		// horizontal same
		// if (weight[current[0]][current[1]] + cavern[current[0]][current[1]] < weight[current[0]][current[1]]) {
		// 	weight[current[0]][current[1]] = weight[current[0]][current[1]] + cavern[current[0]][current[1]];
		// 	changed = true;
		// }
		if (current[1] != weight[0].length-1) { // right
			if (weight[current[0]][current[1]] + cavern[current[0]][current[1]+1] < weight[current[0]][current[1]+1]) {
				weight[current[0]][current[1]+1] = weight[current[0]][current[1]] + cavern[current[0]][current[1]+1];
				changed = true;
			}
		}
		if (current[0] != weight.length-1) { // below
			if (current[1] != 0) { // left
				// if (weight[current[0]][current[1]] + cavern[current[0]+1][current[1]-1] < weight[current[0]+1][current[1]-1]) {
				// 	weight[current[0]+1][current[1]-1] = weight[current[0]][current[1]] + cavern[current[0]+1][current[1]-1];
				// 	changed = true;
				// }
			}
			// horizontal same
			if (weight[current[0]][current[1]] + cavern[current[0]+1][current[1]] < weight[current[0]+1][current[1]]) {
				weight[current[0]+1][current[1]] = weight[current[0]][current[1]] + cavern[current[0]+1][current[1]];
				changed = true;
			}
			if (current[1] != weight[0].length-1) { // right
				// if (weight[current[0]][current[1]] + cavern[current[0]+1][current[1]+1] < weight[current[0]+1][current[1]+1]) {
				// 	weight[current[0]+1][current[1]+1] = weight[current[0]][current[1]] + cavern[current[0]+1][current[1]+1];
				// 	changed = true;
				// }
			}
		}
		return changed;
	}

	static void print(int[][] grid) {
		for(int y=0;y<grid.length;y++) {
			for(int x=0;x<grid[y].length;x++) {
				System.out.print(grid[y][x]+" ");
			}
			System.out.println();
		}
	}
}
