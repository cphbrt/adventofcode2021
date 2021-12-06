import java.util.*;

public class Two {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Map<Long, Long> daysLeftToCount = new HashMap<Long, Long>();

		for(String initialFish : sc.nextLine().split(",")) {
			long fishval = Long.parseLong(initialFish);
			if (daysLeftToCount.containsKey(fishval)) {
				daysLeftToCount.put(fishval, daysLeftToCount.get(fishval)+1L);
			} else {
				daysLeftToCount.put(fishval, 1L);
			}
		}

		for (int d=0 ; d<256; d++) {
			HashMap<Long, Long> newMap = new HashMap<Long, Long>();

			if (daysLeftToCount.containsKey(0L)) {
				newMap.put(8L, daysLeftToCount.get(0L));
				newMap.put(6L, daysLeftToCount.get(0L));
			}

			for (long i=1L; i<=8L; i++) {
				if (daysLeftToCount.containsKey(i)) {
					if (newMap.containsKey(i-1L)) {
						newMap.put(i-1L, newMap.get(i-1L) + daysLeftToCount.get(i));
					} else {
						newMap.put(i-1L, daysLeftToCount.get(i));
					}
				}
			}

			daysLeftToCount = newMap;
		}

		long sum = 0L;
		Collection<Long> values = daysLeftToCount.values();
		for (long v : values) {
			sum += v;
		}
		System.out.println(sum);
	}
}
