import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] makeSumTable(int[][] table) {
		int size = table.length;
		int[][] sumTable = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			sumTable[i][0] = table[i][0];
			for (int j = 1; j < size; j++) {
				sumTable[i][j] = sumTable[i][j - 1] + table[i][j];
			}
		}
		
		for (int i = 1; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sumTable[i][j] += sumTable[i-1][j];
			}
		}
		
		return sumTable;
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] table = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] sumTable = makeSumTable(table);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int sum = sumTable[x2 - 1][y2 - 1];
			if (x1 >= 2) {
				sum -= sumTable[x1 - 2][y2 - 1];
			} 
			if (y1 >= 2) {
				sum -= sumTable[x2 - 1][y1 - 2];
			} 
			if (x1 >= 2 && y1 >= 2) {
				sum += sumTable[x1 - 2][y1 - 2];
			}
			bw.write(sum + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();

	}
}