import java.util.*;

/*
  0:      1:      2:      3:      4:
 aaaa    ....    aaaa    aaaa    ....
b    c  .    c  .    c  .    c  b    c
b    c  .    c  .    c  .    c  b    c
 ....    ....    dddd    dddd    dddd
e    f  .    f  e    .  .    f  .    f
e    f  .    f  e    .  .    f  .    f
 gggg    ....    gggg    gggg    ....

  5:      6:      7:      8:      9:
 aaaa    aaaa    aaaa    aaaa    aaaa
b    .  b    .  .    c  b    c  b    c
b    .  b    .  .    c  b    c  b    c
 dddd    dddd    ....    dddd    dddd
.    f  e    f  .    f  e    f  .    f
.    f  e    f  .    f  e    f  .    f
 gggg    gggg    ....    gggg    gggg

Unique numbers of segments: 1 4 7 8

Numbers of segments:
1 2    c  f
4 4   bcd f
7 3  a c  f
8 7  abcdefg

6 6  ab defg
9 6  abcd fg
0 6  abc efg
3 5  a cd fg

5 5  ab d fg
2 5  a cde g

*/
public class Two2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int sum = 0;
		while(sc.hasNext()) {
			char[][] patterns = new char[10][];
			for (int i=0;i<patterns.length;i++) patterns[i] = sc.next().toCharArray();
			for (char[] pattern : patterns) Arrays.sort(pattern);

			Map<String, Character> patternToDigit = new HashMap<String, Character>();
			Map<Integer, char[]> digitToPattern = new HashMap<Integer, char[]>();

			for (char[] pattern : patterns) {
				if(pattern.length == 2) {
					patternToDigit.put(String.valueOf(pattern), '1');
					digitToPattern.put(1, pattern);
				}
				else if(pattern.length == 4) {
					patternToDigit.put(String.valueOf(pattern), '4');
					digitToPattern.put(4, pattern);
				}
				else if(pattern.length == 3) {
					patternToDigit.put(String.valueOf(pattern), '7');
					digitToPattern.put(7, pattern);
				}
				else if(pattern.length == 7) {
					patternToDigit.put(String.valueOf(pattern), '8');
					digitToPattern.put(8, pattern);
				}
			}
			for (char[] pattern : patterns) {
				if(pattern.length == 6) {
					if(!allAinB(digitToPattern.get(1), pattern)) {
						patternToDigit.put(String.valueOf(pattern), '6');
						digitToPattern.put(6, pattern);
					}
					else if(!allAinB(digitToPattern.get(4), pattern)) {
						patternToDigit.put(String.valueOf(pattern), '0');
						digitToPattern.put(0, pattern);
					}
					else {
						patternToDigit.put(String.valueOf(pattern), '9');
						digitToPattern.put(9, pattern);
					}
				}
			}
			for (char[] pattern : patterns) {
				if (pattern.length == 5) {
					if(allAinB(digitToPattern.get(1), pattern)) {
						patternToDigit.put(String.valueOf(pattern), '3');
						digitToPattern.put(3, pattern);
					}
					else if(!allAinB(pattern, digitToPattern.get(9))) {
						patternToDigit.put(String.valueOf(pattern), '2');
						digitToPattern.put(2, pattern);
					}
					else {
						patternToDigit.put(String.valueOf(pattern), '5');
						digitToPattern.put(5, pattern);
					}
				}
			}

			sc.next(); // consume "|"

			char[] output = new char[4];
			for (int i=0;i<4;i++) {
				char[] digit = sc.next().toCharArray();
				Arrays.sort(digit);
				output[i] = patternToDigit.get(String.valueOf(digit));
			}
			sum += Integer.parseInt(String.valueOf(output));
		}
		System.out.println(sum);
	}
	static boolean allAinB(char[] a, char[] b) {
		for (char c : a) {
			boolean found = false;
			for (char d : b) {
				if (d == c) {
					found = true;
					break;
				}
			}
			if (!found) return false;
		}
		return true;
	}
}
