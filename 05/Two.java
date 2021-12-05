import java.util.*;

public class Two {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Map<String, Integer> map = new HashMap<String, Integer>();
		
		while(sc.hasNextLine()) {
			Line line = new Line(sc.nextLine());
			
			// if (line.isHorizontalOrVertical()) {
				List<Point> points = line.points();
				for (Point point : points) {
					String p = point.toString();
					if (map.containsKey(p)) {
						map.put(p, map.get(p)+1);
					} else {
						map.put(p, 1);
					}
				}
			// } else {
				// probably Part 2
			// }
		}
		Collection<Integer> values = map.values();
		int numOver2 = 0;
		for (int value : values) {
			if (value >= 2) numOver2++;
		}
		System.out.println(numOver2);
	}
}

class Point {
	int x;
	int y;
	Point(String coordinates) {
		String[] xy = coordinates.split(",");
		this.x = Integer.parseInt(xy[0]);
		this.y = Integer.parseInt(xy[1]);
	}
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return ""+x+","+y;
	}
}

class Line {
	Point start;
	Point end;
	Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	Line(String vector) {
		String[] startEnd = vector.split(" -> ");
		this.start = new Point(startEnd[0]);
		this.end = new Point(startEnd[1]);
	}

	boolean isHorizontalOrVertical() {
		return this.start.x == this.end.x || this.start.y == this.end.y;
	}

	List<Point> points() {
		
		List<Point> points = new ArrayList<Point>();

		int x = this.start.x;
		int y = this.start.y;
		while(!(x == this.end.x && y == this.end.y)) {
			points.add(new Point(x,y));

			// I think this'll also handle diagonal
			if (x < this.end.x) x++;
			if (x > this.end.x) x--;
			if (y < this.end.y) y++;
			if (y > this.end.y) y--;
		}
		points.add(this.end);
		return points;
	}
}
