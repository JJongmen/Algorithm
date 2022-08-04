import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static Integer[] sortArr;
	static int posCnt;
	
	static int binSearch(int n) {
		int left = 0;
		int right = posCnt - 1;
		
		while (left < right && left >= 0) {
			int mid = (left + right) / 2;
			if (sortArr[mid] < n) {
				left = mid + 1;
			} else if (sortArr[mid] > n) {
				right = mid - 1;
			} else {
				left = mid - 1;
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] posArr = new int[N];
		Set<Integer> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int pos = Integer.parseInt(st.nextToken());;
			posArr[i] = pos;
			set.add(pos);
		}
		
		sortArr = set.toArray(new Integer[0]);
		posCnt = sortArr.length;
		Arrays.sort(sortArr);
		for (int i = 0; i < N; i++) {
			bw.write(binSearch(posArr[i]) + " ");
		}
		
		bw.flush();
		br.close();
		bw.close();
		
	}
}