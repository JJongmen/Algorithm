import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] video;
	static BufferedWriter bw;
	
	static boolean isSame(int size, int x_idx, int y_idx) {
		for(int i = x_idx; i < x_idx + size; i++) {
			for(int j = y_idx; j < y_idx + size; j++) {
				if(video[i][j] != video[x_idx][y_idx])
					return false;
			}
		}
		return true;
	}
	
	static void compress(int size, int x_idx, int y_idx) throws IOException {
		if(isSame(size, x_idx, y_idx) || size == 1) {
			if(video[x_idx][y_idx] == 1)
				bw.write('1');
			else
				bw.write('0');
			return;
		}
		bw.write('(');
		compress(size/2, x_idx, y_idx);
		compress(size/2, x_idx, y_idx + size/2);
		compress(size/2, x_idx + size/2, y_idx);
		compress(size/2, x_idx + size/2, y_idx + size/2);
		bw.write(')');
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		video = new int[N][N];
		for(int i = 0 ; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < N; j++) 
				video[i][j] = tmp.charAt(j) - '0';
		}
		compress(N, 0, 0);
		bw.flush();
		br.close();
		bw.close();
		
	}
}