import java.util.*;

public class One {

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
				row[j] = Integer.parseInt(lines.get(i).substring(j,j+1));
			}
			map[i] = row;
		}

		int sum = 0;
		// find low points in int matrix
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				if (
					(i == 0 || map[i-1][j] > map[i][j])
					&&
					(i == map.length-1 || map[i+1][j] > map[i][j])
					&&
					(j == 0 || map[i][j-1] > map[i][j])
					&&
					(j == map[i].length-1 || map[i][j+1] > map[i][j])
				) {
					sum += map[i][j]+1;
				}
			}
		}
		System.out.println(sum);
	}
}
