import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;



public class Main {
	
	static BufferedWriter bw;
	
	static class Queue{
		int[] data;
		int front;
		int back;
		
		Queue() {
			data = new int[2000000];
			front = 0;
			back = 0;
		}
		
		void push(int X) {
			data[back++] = X;
			if(back >= data.length) {
				back = 0;
			}
		}
		
		void pop() throws IOException {
			bw.write((front==back ? -1 : data[front++]) + "\n");
		}
		
		void size() throws IOException {
			bw.write((back-front >= 0 ? back-front : data.length+1+back-front) + "\n");
		}
		
		void empty() throws IOException {
			bw.write((front==back ? 1 : 0) + "\n");
		}
		
		void front() throws IOException {
			bw.write((front==back ? -1 : data[front]) + "\n");
		}
		
		void back() throws IOException {
			bw.write((front==back ? -1 : (back==0 ? data[data.length-1] : data[back-1])) + "\n");
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Queue q = new Queue();
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
				case "push":
					q.push(Integer.parseInt(st.nextToken()));
					break;
				case "pop":
					q.pop();
					break;
				case "size":
					q.size();
					break;
				case "empty":
					q.empty();
					break;
				case "front":
					q.front();
					break;
				case "back":
					q.back();
					break;
				default: break;
			}
		}
		bw.flush();
		br.close();
		bw.close();
	}
}