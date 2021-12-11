import java.util.*;

public class Two {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<int[]> rowList = new ArrayList<int[]>();

		while(sc.hasNextLine()) {
			char[] line = sc.nextLine().toCharArray();
			int[] row = new int[line.length];
			for (int i=0;i<line.length;i++) {
				row[i] = Integer.parseInt(String.valueOf(line[i]));
			}
			rowList.add(row);
		}

		int[][] grid = new int[rowList.size()][];
		for (int i=0;i<rowList.size();i++) {
			grid[i] = rowList.get(i);
		}

		int step = 0;
		boolean sync = false;
		while (!sync) {
			int flashes = 0;
			for(int y=0;y<grid.length;y++) {
				for(int x=0;x<grid[y].length;x++) {
					grid[y][x]++;
				}
			}

			boolean changed = false;
			do {
				changed = false;
				for(int y=0;y<grid.length;y++) {
					for(int x=0;x<grid[y].length;x++) {
						if (grid[y][x] == 0) continue;
						if (grid[y][x] > 9) {
							flash(grid, y, x);
							flashes++;
							changed = true;
						}
					}
				}
			} while (changed);
			if (flashes == grid.length * grid[0].length) sync = true;
			step++;
		}

		System.out.println(step);
	}

	static void flash(int[][] grid, int y, int x) {
		grid[y][x] = 0;

		if (y != 0) {
			if (x != 0) {
				if (grid[y-1][x-1] != 0) grid[y-1][x-1]++;
			}
			if (grid[y-1][x] != 0) grid[y-1][x]++;
			if (x != grid[0].length-1) {
				if (grid[y-1][x+1] != 0) grid[y-1][x+1]++;
			}
		}
		if (x != 0) {
			if (grid[y][x-1] != 0) grid[y][x-1]++;
		}
		if (x != grid[0].length-1) {
			if (grid[y][x+1] != 0) grid[y][x+1]++;
		}
		if (y != grid.length-1) {
			if (x != 0) {
				if (grid[y+1][x-1] != 0) grid[y+1][x-1]++;
			}
			if (grid[y+1][x] != 0) grid[y+1][x]++;
			if (x != grid[0].length-1) {
				if (grid[y+1][x+1] != 0) grid[y+1][x+1]++;
			}
		}
	}
}
