import java.util.Scanner;
import java.util.ArrayList;

/*
This got messy. Definitely would approach differently if not in a race.
 */
public class Two {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<char[]> lines = new ArrayList<char[]>();

		while (sc.hasNextLine()) {
			lines.add(sc.nextLine().toCharArray());
		}

		// Yes, this is a shallow copy, but that's okay for us.
		ArrayList<char[]> remainingLines = (ArrayList<char[]>) lines.clone();
		int i = 0;
		while (remainingLines.size() > 1) {
			char[] mostFrequentOrEqual = mostFrequentOrEqual(oneFrequency(remainingLines), remainingLines.size());

			ArrayList<char[]> newRemainingLines = new ArrayList<char[]>();
			for (char[] line : remainingLines) {
				if (line[i] == mostFrequentOrEqual[i]) newRemainingLines.add(line);
				else if ((mostFrequentOrEqual[i] == '=' && line[i] == '1')) {
					newRemainingLines.add(line);
				}
			}
			remainingLines = newRemainingLines;
			i++;
		}
		
		int oxygen = Integer.parseInt(new String(remainingLines.get(0)), 2);

		i = 0;
		remainingLines = (ArrayList<char[]>) lines.clone();
		while (remainingLines.size() > 1) {
			char[] mostFrequentOrEqual = mostFrequentOrEqual(oneFrequency(remainingLines), remainingLines.size());

			ArrayList<char[]> newRemainingLines = new ArrayList<char[]>();
			for (char[] line : remainingLines) {
				if (line[i] != mostFrequentOrEqual[i] && mostFrequentOrEqual[i] != '=') newRemainingLines.add(line);
				else if ((mostFrequentOrEqual[i] == '=' && line[i] == '0')) {
					newRemainingLines.add(line);
				}
			}
			remainingLines = newRemainingLines;
			i++;
		}

		int co2 = Integer.parseInt(new String(remainingLines.get(0)), 2);
		System.out.println(oxygen * co2);

	}

	static int[] oneFrequency(ArrayList<char[]> lines) {
		int[] oneFrequency = new int[lines.get(0).length];
		for (int i=0 ; i < lines.size() ; i++) {
			char[] line = lines.get(i);
			for (int j=0; j<line.length ; j++) {
				if (line[j] == '1') oneFrequency[j]++;
			}
		}
		return oneFrequency;
	}

	static char[] mostFrequentOrEqual(int[] oneFrequency, int numLines) {
		char[] mostFrequent = new char[oneFrequency.length];
		for (int i=0;i<oneFrequency.length;i++) {
			if (oneFrequency[i] > (numLines - oneFrequency[i])) mostFrequent[i] = '1';
			else if (oneFrequency[i] < (numLines - oneFrequency[i])) mostFrequent[i] = '0';
			else mostFrequent[i] = '=';
		}
		return mostFrequent;
	}
}
