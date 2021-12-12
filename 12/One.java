import java.util.*;

public class One {

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
		Set<Cave> visited = new HashSet<Cave>();
		visited.add(start);
		int paths = dfs(start, visited);

		System.out.println(paths);
	}

	static int dfs(Cave current, Set<Cave> visited) {
		if (current.name.equals("end")) {
			return 1;
		}
		int paths = 0;
		for (Cave next : current.connections) {
			if (next.isBig() || !visited.contains(next)) {
				Set<Cave> newVisited = copy(visited);
				newVisited.add(next);
				paths += dfs(next, newVisited);
			}
		}
		return paths;
	}

	static Set<Cave> copy(Set<Cave> visited) {
		Set<Cave> copy = new HashSet<Cave>();
		for (Cave cave : visited) {
			copy.add(cave);
		}
		return copy;
	}
}

class Cave {
	String name;
	ArrayList<Cave> connections;

	Cave(String name) {
		this.name = name;
		this.connections = new ArrayList<Cave>();
	}

	boolean isBig() {
		if (this.name.equals(this.name.toUpperCase())) return true;
		else return false;
	}
}
