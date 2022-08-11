import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] tree;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[] visit;
	
	static void preorder(int node) throws IOException {
		int left = tree[node][0];
		int right = tree[node][1];
		
		visit[node] = true;
		bw.write(node - 1 + 'A');	
		
		if (left != 0 && !visit[left]) {
			preorder(left);
		}
		
		if (right != 0 && !visit[right]) {
			preorder(right);
		}
	}
	
	static void inorder(int node) throws IOException {
		int left = tree[node][0];
		int right = tree[node][1];
		visit[node] = true;
		
		if (left != 0 && !visit[left]) {
			inorder(left);
		}
		
		
		bw.write(node - 1 + 'A');
		
		if (right != 0 && !visit[right]) {
			inorder(right);
		}
	}

	static void postorder(int node) throws IOException {
		int left = tree[node][0];
		int right = tree[node][1];
		visit[node] = true;
		
		if (left != 0 && !visit[left]) {
			postorder(left);
		}
		
		if (right != 0 && !visit[right]) {
			postorder(right);
		}
		
		
		bw.write(node - 1 + 'A');
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new int[N + 1][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node = st.nextToken().charAt(0) - 'A' + 1;
			int lNode = st.nextToken().charAt(0) - 'A' + 1;
			int rNode = st.nextToken().charAt(0) - 'A' + 1;
			if (lNode != '.' - 'A' + 1) {
				tree[node][0] = lNode;
			}
			if (rNode != '.' - 'A' + 1) {
				tree[node][1] = rNode;
			}
		}
		
		visit = new boolean[N + 1];
		preorder(1);
		bw.write("\n");
		
		visit = new boolean[N + 1];
		inorder(1);
		bw.write("\n");
		
		visit = new boolean[N + 1];
		postorder(1);
		
		bw.flush();
		br.close();
		bw.close();
	}
}