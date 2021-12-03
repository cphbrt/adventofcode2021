import java.util.Scanner;

public class One {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Need first line for line length hint
		char[] line = sc.nextLine().toCharArray();
		int[] oneFrequency = new int[line.length];
		int linesEncountered = 0;
		
		// Process first line
		for (int i=0; i<line.length ; i++) {
			if (line[i] == '1') oneFrequency[i]++;
		}
		linesEncountered++;
		
		// Process remaining lines
		while (sc.hasNextLine()) {
			line = sc.nextLine().toCharArray();
			for (int i=0; i<line.length ; i++) {
				if (line[i] == '1') oneFrequency[i]++;
			}
			linesEncountered++;
		}

		// Calculate gamma and epsilon
		char[] gammaChars = new char[line.length];
		char[] epsilonChars = new char[line.length];
		for (int i=0 ; i < line.length ; i++) {
			if (oneFrequency[i] > linesEncountered/2) {
				gammaChars[i] = '1';
				epsilonChars[i] = '0';
			}
			else {
				gammaChars[i] = '0';
				epsilonChars[i] = '1';
			}
		}

		int gamma = Integer.parseInt(new String(gammaChars), 2);
		int epsilon = Integer.parseInt(new String(epsilonChars), 2);
		
		System.out.println(gamma * epsilon);
	}
}
