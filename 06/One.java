import java.util.*;

public class One {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Map<Integer, Integer> daysLeftToCount = new HashMap<Integer, Integer>();

		for(String initialFish : sc.nextLine().split(",")) {
			int fishval = Integer.parseInt(initialFish);
			if (daysLeftToCount.containsKey(fishval)) {
				daysLeftToCount.put(fishval, daysLeftToCount.get(fishval)+1);
			} else {
				daysLeftToCount.put(fishval, 1);
			}
		}

		for (int d=0 ; d<80; d++) {
			System.out.println(daysLeftToCount);
			HashMap<Integer, Integer> newMap = new HashMap<Integer, Integer>();

			if (daysLeftToCount.containsKey(0)) {
				newMap.put(8, daysLeftToCount.get(0));
				newMap.put(6, daysLeftToCount.get(0));
			}

			for (int i=1 ; i<=8; i++) {
				if (daysLeftToCount.containsKey(i)) {
					if (newMap.containsKey(i-1)) {
						newMap.put(i-1, newMap.get(i-1) + daysLeftToCount.get(i));
					} else {
						newMap.put(i-1, daysLeftToCount.get(i));
					}
				}
			}

			System.out.println(newMap);

			daysLeftToCount = newMap;
		}

		int sum = 0;
		Collection<Integer> values = daysLeftToCount.values();
		for (int v : values) {
			sum += v;
		}
		System.out.println(sum);
	}
}
