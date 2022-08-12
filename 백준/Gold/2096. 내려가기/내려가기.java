import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int getMax(int[][] table) {
		int size = table.length;
		int[][] maxTable = new int[size][3];
		
		for (int i = 0; i < 3; i++) {
			maxTable[0][i] = table[0][i];
		}
		
		for (int i = 1; i < size; i++) {
			int maxLeft = Math.max(maxTable[i - 1][0], maxTable[i - 1][1]);
			int maxRight = Math.max(maxTable[i - 1][1], maxTable[i - 1][2]);
			maxTable[i][0] = maxLeft + table[i][0];
			maxTable[i][1] = Math.max(maxLeft, maxRight) + table[i][1];
			maxTable[i][2] = maxRight + table[i][2];
		}
		
		return Math.max(maxTable[size - 1][0],
				Math.max(maxTable[size - 1][1], maxTable[size - 1][2]));
		
	}
	
	static int getMin(int[][] table) {
		int size = table.length;
		int[][] minTable = new int[size][3];
		
		for (int i = 0; i < 3; i++) {
			minTable[0][i] = table[0][i];
		}
		
		for (int i = 1; i < size; i++) {
			int minLeft = Math.min(minTable[i - 1][0], minTable[i - 1][1]);
			int minRight = Math.min(minTable[i - 1][1], minTable[i - 1][2]);
			minTable[i][0] = minLeft + table[i][0];
			minTable[i][1] = Math.min(minLeft, minRight) + table[i][1];
			minTable[i][2] = minRight + table[i][2];
		}
		
		return Math.min(minTable[size - 1][0],
				Math.min(minTable[size - 1][1], minTable[size - 1][2]));
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[][] table = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bw.write(getMax(table) + " ");
		bw.write(getMin(table) + "");
		
		bw.flush();
		br.close();
		bw.close();

	}
}