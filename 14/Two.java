import java.util.*;

/*
A complete mess.
*/
public class Two {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String template = sc.nextLine();

		// READ INITIAL STATE

		Map<CharSequence, Long> paircount = new HashMap<CharSequence, Long>();
		for (int i=0;i<template.length()-1;i++) {
			CharSequence subSequence = template.subSequence(i,i+2);
			if (!paircount.containsKey(subSequence)) {
				paircount.put(subSequence, 1L);
			} else {
				paircount.put(subSequence, paircount.get(subSequence)+1);
			}
		}

		sc.nextLine(); // \n

		// READ RULES

		Map<CharSequence, List<CharSequence>> rules = new HashMap<CharSequence, List<CharSequence>>();
		while(sc.hasNextLine()) {
			String[] ruleparts = sc.nextLine().split(" -> ");
			List<CharSequence> x = new ArrayList<CharSequence>();
			x.add("" + ruleparts[0].charAt(0) + ruleparts[1]);
			x.add("" + ruleparts[1] + ruleparts[0].charAt(1));
			rules.put(ruleparts[0], x);
		}

		// DONE READING INPUT

		// DO STEPS

		for (int i=0;i<40;i++) {
			Map<CharSequence, Long> newpaircount = new HashMap<CharSequence, Long>();

			Set<CharSequence> keys = paircount.keySet();
			for (CharSequence key : keys) {
				if (newpaircount.containsKey(rules.get(key).get(0))) {
					newpaircount.put(rules.get(key).get(0), newpaircount.get(rules.get(key).get(0))+paircount.get(key));
				} else {
					newpaircount.put(rules.get(key).get(0), paircount.get(key));
				}
				
				if (newpaircount.containsKey(rules.get(key).get(1))) {
					newpaircount.put(rules.get(key).get(1), newpaircount.get(rules.get(key).get(1))+paircount.get(key));
				} else {
					newpaircount.put(rules.get(key).get(1), paircount.get(key));
				}
			}
			paircount = newpaircount;
		}

		Map<Character, Long> letters = new HashMap<Character, Long>();
		for (Map.Entry<CharSequence, Long> entry : paircount.entrySet()) {
			Character c = entry.getKey().toString().toCharArray()[0];
			if (letters.containsKey(c)) {
				letters.put(c, letters.get(c)+entry.getValue());
			} else {
				letters.put(c, entry.getValue());
			}
		}
		letters.put(template.charAt(template.length()-1), letters.get(template.charAt(template.length()-1))+1);
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		for (Long v : letters.values()) {
			if (min > v) min = v;
			if (max < v) max = v;
		}
		System.out.println(max - min);
	}


}
