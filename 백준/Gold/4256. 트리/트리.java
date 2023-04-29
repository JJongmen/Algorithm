import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] preOrder = new int[1001];
    static int[] inOrder = new int[1001];
    static StringBuilder sb;

    static class Node {
        int num;
        Node lc, rc;

        public Node(int num) {
            this.num = num;
        }

        public Node(int num, Node lc, Node rc) {
            this.num = num;
            this.lc = lc;
            this.rc = rc;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                preOrder[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                inOrder[i] = Integer.parseInt(st.nextToken());
            }

            Node root = makeTree(1, 1, N);
            sb = new StringBuilder();
            printPostOrder(root);
            System.out.println(sb);
        }
    }

    private static void printPostOrder(Node node) {
        if (node.lc != null) printPostOrder(node.lc);
        if (node.rc != null) printPostOrder(node.rc);
        sb.append(node.num).append(' ');
    }

    private static Node makeTree(int preIdx, int inL, int inR) {
        if (inL > inR) return null;
        int inIdx = inL;
        for (; inIdx <= inR; inIdx++) {
            if (inOrder[inIdx] == preOrder[preIdx]) break;
        }
        return new Node(preOrder[preIdx], makeTree(preIdx + 1, inL, inIdx - 1), makeTree(preIdx + inIdx - inL + 1, inIdx + 1, inR));
    }

}