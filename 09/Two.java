import java.util.*;

public class Two {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// read lines
		ArrayList<String> lines = new ArrayList<String>();
		while(sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}

		// convert lines to int matrix
		int[][] map = new int[lines.size()][];
		for(int i=0;i<map.length;i++) {
			int[] row = new int[lines.get(i).length()];
			for(int j=0;j<row.length;j++) {
				boolean isBasin = Integer.parseInt(lines.get(i).substring(j,j+1)) != 9;
				if (isBasin) row[j] = 0;
				else row[j] = -1;
			}
			map[i] = row;
		}

		// paint basins a specific "color" (int value)
		boolean changed = false;
		int basin = 1;
		do {
			changed = false;
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[i].length;j++) {
					if (map[i][j] == -1) continue;
					if (i != 0 && map[i-1][j] > map[i][j]) {
						map[i][j] = map[i-1][j];
						changed = true;
					}
					if (i != map.length-1 && map[i+1][j] > map[i][j]) {
						map[i][j] = map[i+1][j];
						changed = true;
					}
					if (j != 0 && map[i][j-1] > map[i][j]) {
						map[i][j] = map[i][j-1];
						changed = true;
					}
					if (j != map[i].length-1 && map[i][j+1] > map[i][j]) {
						map[i][j] = map[i][j+1];
						changed = true;
					}
					if (map[i][j] == 0) {
						map[i][j] = basin;
						basin++;
						changed = true;
					}
				}
			}
		} while(changed);

		// count number of cells painted each apparent basin color
		int[] basinSizes = new int[basin+1];
		for(int b=1;b<basin;b++) {
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[i].length;j++) {
					if (map[i][j] == b) basinSizes[b]++;
				}
			}
		}

		// identify largest basins by sorting basin sizes (ascending) and grabbing from bottom
		Arrays.sort(basinSizes);
		
		// then multiply those largest basin sizes
		System.out.println(basinSizes[basinSizes.length-1] * basinSizes[basinSizes.length-2] * basinSizes[basinSizes.length-3]);
	}
}
