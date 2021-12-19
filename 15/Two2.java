import java.util.*;

public class Two2 {

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
		int[][] miniCavern = rowList.toArray(new int[rowList.size()][]);

		int[][] cavern = new int[miniCavern.length*5][miniCavern[0].length*5];

		// print(cavern);
		for(int y=0;y<cavern.length;y++) {
			for(int x=0;x<cavern[0].length;x++) {
				int v = (miniCavern[ y % miniCavern.length ][ x % miniCavern[0].length ] + (y/miniCavern.length + x/miniCavern.length));
				while (v > 9) {
					v -= 9;
				}
				cavern[y][x] = v;
			}
		}
		print(cavern);

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

		Set<Cell> unvisited = new HashSet<Cell>();
		for (int y=0;y<cavern.length;y++) {
			for (int x=0;x<cavern[0].length;x++) {
				unvisited.add(new Cell(y, x));
			}
		}

		int[] current = start;
		while(unvisited.size() > 0 && unvisited.contains(new Cell(cavern.length-1, cavern[0].length-1))) {
			int[] lightestUnvisitedNeighbor = weighNeighbors(current, cavern, weight, unvisited);
			unvisited.remove(new Cell(current[0], current[1]));
			// current = lightestUnvisitedNeighbor(current, weight, unvisited);
			current = lightestUnvisitedNeighbor;
		}

		// boolean changed = true;
		// while (changed) {
		// 	changed = false;
		// 	for (int y=0;y<cavern.length;y++) {
		// 		for (int x=0;x<cavern[0].length;x++) {
		// 			changed = changed || weighNeighbors(new int[] { y, x }, cavern, weight);
		// 		}
		// 	}
		// }
		// weighNeighbors(current, cavern, weight);
		// print(weight);
		System.out.println(weight[weight.length-1][weight[0].length-1]);
	}

	// static int[] lightestUnvisitedNeighbor(int[] current, int[][] weight, Set<cell> unvisited) {
	// 	Cell lightestCell;
	// 	int lightestWeight = Integer.MAX_VALUE;
	// 	if (weight[current[0]-1][current[1]] < lightestWeight && !unvisited.contains(new Cell(current[1], current[0]-1))) {
	// 		lightestWeight = weight[current[0]][current[1]];
	// 		lightestCell = new Cell(current[1], current[0]);
	// 	}
	// 	if (weight[current[0]][current[1]] < lightestWeight && !unvisited.contains(new Cell(current[1], current[0]))) {
	// 		lightestWeight = weight[current[0]][current[1]];
	// 		lightestCell = new Cell(current[1], current[0]);
	// 	}
	// 	if (weight[current[0]][current[1]] < lightestWeight && !unvisited.contains(new Cell(current[1], current[0]))) {
	// 		lightestWeight = weight[current[0]][current[1]];
	// 		lightestCell = new Cell(current[1], current[0]);
	// 	}
	// 	if (weight[current[0]][current[1]] < lightestWeight && !unvisited.contains(new Cell(current[1], current[0]))) {
	// 		lightestWeight = weight[current[0]][current[1]];
	// 		lightestCell = new Cell(current[1], current[0]);
	// 	}

	// 	return new int[] { lightestCell.y, lightestCell.x };
	// }

	static int[] weighNeighbors(int[] current, int[][] cavern, int[][] weight, Set<Cell> unvisited) {
		int[] lightestCell = new int[2];
		int lightestWeight = Integer.MAX_VALUE;
		// boolean changed = false;
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
				if (weight[current[0]-1][current[1]] < lightestWeight && unvisited.contains(new Cell(current[0]-1, current[1]))) {
					lightestWeight = weight[current[0]-1][current[1]];
					lightestCell = new int[] { current[0]-1, current[1] };
				}
				// changed = true;
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
				if (weight[current[0]][current[1]-1] < lightestWeight && unvisited.contains(new Cell(current[0], current[1]-1))) {
					lightestWeight = weight[current[0]][current[1]-1];
					lightestCell = new int[] { current[0], current[1]-1 };
				}
				// changed = true;
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
				if (weight[current[0]][current[1]+1] < lightestWeight && unvisited.contains(new Cell(current[0], current[1]+1))) {
					lightestWeight = weight[current[0]][current[1]+1];
					lightestCell = new int[] { current[0], current[1]+1 };
				}
				// changed = true;
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
				if (weight[current[0]+1][current[1]] < lightestWeight && unvisited.contains(new Cell(current[0]+1, current[1]))) {
					lightestWeight = weight[current[0]+1][current[1]];
					lightestCell = new int[] { current[0]+1, current[1] };
				}
				// changed = true;
			}
			if (current[1] != weight[0].length-1) { // right
				// if (weight[current[0]][current[1]] + cavern[current[0]+1][current[1]+1] < weight[current[0]+1][current[1]+1]) {
				// 	weight[current[0]+1][current[1]+1] = weight[current[0]][current[1]] + cavern[current[0]+1][current[1]+1];
				// 	changed = true;
				// }
			}
		}
		return lightestCell;
		// return changed;
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

class Cell {
	int x;
	int y;

	Cell(int y, int x) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object other) {
		return this.x == ((Cell)other).x && this.y == ((Cell)other).y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y);
	}
}
