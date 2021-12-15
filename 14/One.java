import java.util.*;

public class One {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[] template = sc.nextLine().toCharArray();

		sc.nextLine(); // \n

		Map<String, Character> rules = new HashMap<String, Character>();
		while(sc.hasNextLine()) {
			String[] ruleparts = sc.nextLine().split(" -> ");
			rules.put(ruleparts[0], ruleparts[1].charAt(0));
		}

		System.out.println(template);
		for (int i=0;i<10;i++) {
			ArrayList<Character> step = new ArrayList<Character>();

			for (int j=0;j<template.length-1;j++) {
				step.add(template[j]);
				step.add(rules.get(""+template[j]+template[j+1]));
			}
			step.add(template[template.length-1]);

			char[] newtemplate = new char[step.size()];
			for(int j=0;j<newtemplate.length;j++) {
				newtemplate[j] = step.get(j);
			}

			template = newtemplate;
		}

		Map<Character, Integer> countmap = new HashMap<Character, Integer>();

		for (Character c : template) {
			if (!countmap.containsKey(c)) {
				countmap.put(c, 1);
			} else {
				countmap.put(c, countmap.get(c)+1);
			}
		}

		List<Integer> countlist = new ArrayList<Integer>();
		countlist.addAll(countmap.values());
		Collections.sort(countlist);

		System.out.println(countlist);
		System.out.println(countlist.get(countlist.size()-1) - countlist.get(0));
	}


}
