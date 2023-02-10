import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static class BinarySearchTree {
        Node root;

        public BinarySearchTree(int data) {
            root = new Node(data);
        }

        public void insert(int data) {
            root.insert(data);
        }

        public void postOrderSearch() throws IOException{
            root.postOrderSearch();
            bw.flush();
        }
    }

    static class Node {
        int data;
        Node lc;
        Node rc;

        public Node(int data) {
            this.data = data;
        }

        public void insert(int data) {
            if (data < this.data) {
                if (lc == null) {
                    lc = new Node(data);
                } else {
                    lc.insert(data);
                }
            } else {
                if (rc == null) {
                    rc = new Node(data);
                } else {
                    rc.insert(data);
                }
            }
        }

        public void postOrderSearch() throws IOException {
            if (lc != null) {
                lc.postOrderSearch();
            }
            if (rc != null) {
                rc.postOrderSearch();
            }
            bw.write(data + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int root = Integer.parseInt(br.readLine());
        BinarySearchTree tree = new BinarySearchTree(root);
        while (br.ready()) {
            tree.insert(Integer.parseInt(br.readLine()));
        }
        tree.postOrderSearch();
        bw.close();
    }
}