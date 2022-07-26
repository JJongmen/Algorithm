import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] sequence = new int[N+1];		//입력으로 주어진 수열을 담을 배열
		//sequence의 i번째 인덱스의 수가 마지막에 있는 부분 수열들 중 최대 길이를 담는 배열
		int[] maxSubSeq = new int[N+1];		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		for(int i = 1; i <= N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
			int maxTmp = 0;
			for(int j = 0; j < i; j++) {
				if(sequence[j] < sequence[i]) {
					maxTmp = Math.max(maxTmp, maxSubSeq[j]);
				}
			}
			maxSubSeq[i] = maxTmp + 1;
			max = Math.max(max, maxSubSeq[i]);
		}
		System.out.println(max);
	}
}