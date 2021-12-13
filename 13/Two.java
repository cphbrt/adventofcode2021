import java.util.*;

public class Two {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Set<Dot> dots = new HashSet<Dot>();
		String line = sc.nextLine();
		while (!line.equals("")) {
			String[] lineParts = line.split(",");
			dots.add(
				new Dot(
					Integer.parseInt(lineParts[0]),
					Integer.parseInt(lineParts[1])
				)
			);
			line = sc.nextLine();
		}

		while (sc.hasNextLine()) {
			String[] foldParts = sc.nextLine().substring(11).split("=");
			String axis = foldParts[0];
			int fold = Integer.parseInt(foldParts[1]);

			Set<Dot> newDots = new HashSet<Dot>();
			for (Dot dot : dots) {
				Dot newDot = dot.fold(axis, fold);
				if (!newDots.contains(newDot)) {
					newDots.add(newDot);
				}
			}
			dots = newDots;
		}


		int maxX = 0;
		int maxY = 0;
		for (Dot dot : dots) {
			if (dot.x > maxX) maxX = dot.x;
			if (dot.y > maxY) maxY = dot.y;
		}

		boolean[][] grid = new boolean[maxY+1][maxX+1];
		for (Dot dot : dots) {
			grid[dot.y][dot.x] = true;
		}
		for(int y=0;y<grid.length;y++) {
			for (int x=0;x<grid[y].length;x++) {
				if (grid[y][x]) System.out.print("X");
				else System.out.print(" ");
			}
			System.out.println();
		}
	}
}

class Dot {
	int x;
	int y;
	
	Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Dot fold(String axis, int line) {
		Dot newDot;
		if (axis.equals("x")) {
			if (x < line) {
				newDot = new Dot(x, y);
			} else {
				newDot = new Dot(x-(x-line)*2, y);
			}
		} else {
			if (y < line) {
				newDot = new Dot(x, y);
			} else {
				newDot = new Dot(x, y-(y-line)-(y-line));
			}
		}
		return newDot;
	}

	@Override
	public boolean equals(Object obj) {
		return this.x == ((Dot)obj).x && this.y == ((Dot)obj).y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x,y);
	}

	@Override
	public String toString() {
		return "x="+this.x+",y="+this.y;
	}

}
