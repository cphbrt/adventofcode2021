import java.util.*;

public class Two {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Map<String, Cave> caveMap = new HashMap<String, Cave>();
		while (sc.hasNextLine()) {
			String[] caveLink = sc.nextLine().split("-");
			Cave left = caveMap.get(caveLink[0]);
			if (left == null) {
				left = new Cave(caveLink[0]);
				caveMap.put(caveLink[0], left);
			}
			Cave right = caveMap.get(caveLink[1]);
			if (right == null) {
				right = new Cave(caveLink[1]);
				caveMap.put(caveLink[1], right);
			}
			left.connections.add(right);
			right.connections.add(left);
		}

		Cave start = caveMap.get("start");
		ArrayList<Cave> visited = new ArrayList<Cave>();
		visited.add(start);
		boolean doubleUsed = false;
		int paths = dfs(start, visited, doubleUsed);

		System.out.println(paths);
	}

	static int dfs(Cave current, ArrayList<Cave> visited, boolean doubleUsed) {
		if (current.name.equals("end")) {
			return 1;
		}
		int paths = 0;
		for (Cave next : current.connections) {
			if (
				!next.name.equals("start") && 
				(
					next.isBig() ||
					!visited.contains(next) ||
					!doubleUsed
				)
			) {
				boolean willBeDoubleUsed = doubleUsed || (!next.isBig() && visited.contains(next));
				ArrayList<Cave> newVisited = copy(visited);
				newVisited.add(next);
				paths += dfs(next, newVisited, willBeDoubleUsed);
			}
		}
		return paths;
	}

	static ArrayList<Cave> copy(ArrayList<Cave> visited) {
		ArrayList<Cave> copy = new ArrayList<Cave>();
		for (Cave cave : visited) {
			copy.add(cave);
		}
		return copy;
	}
}

class Cave {
	String name;
	Set<Cave> connections;

	Cave(String name) {
		this.name = name;
		this.connections = new HashSet<Cave>();
	}

	boolean isBig() {
		if (this.name.equals(this.name.toUpperCase())) return true;
		else return false;
	}

	public String toString() {
		return this.name;
	}
}
