import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static class Deque {
		private int[] deque;
		private int front;
		private int back;
		private int num;
		private int capacity;
		
		Deque(int maxlen) {
			capacity = maxlen;
			front = back = num = 0;
			deque = new int[capacity];
		}
		
		void push_front(int X) {
			if (front == 0 && num < capacity) {
				front = capacity;
			} 
			deque[--front] = X;
			num++;
		}
		
		void push_back(int X) {
			deque[back++] = X;
			num++;
			if (back == capacity) {
				back = 0;
			}
		}
		
		void pop_front() throws IOException {
			if (num == 0) {
				bw.write(-1 + "\n");
			} else {
				bw.write(deque[front++] + "\n");
				if(front == capacity) {
					front = 0;
				}
				num--;
			}
		}
		
		void pop_back() throws IOException {
			if (num == 0) {
				bw.write(-1 + "\n");
			} else {
				if(back == 0) {
					back = capacity;
				}
				bw.write(deque[--back] + "\n");
				num--;
			}
		}
		
		void size() throws IOException {
			bw.write(num + "\n");
		}
		
		void empty() throws IOException {
			if (num == 0) {
				bw.write(1 + "\n");
			} else {
				bw.write(0 + "\n");
			}
		}
		
		void front() throws IOException {
			if (num == 0) {
				bw.write(-1 + "\n");
			} else {
				bw.write(deque[front] + "\n");
			}
		}
		
		void back() throws IOException {
			if (num == 0) {
				bw.write(-1 + "\n");
			} else {
				if (back == 0) {
					bw.write(deque[capacity - 1] + "\n");
				} else {
					bw.write(deque[back - 1] + "\n");
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque deque = new Deque(N);
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String expr = st.nextToken();
			switch (expr) {
				case ("push_front") :
					deque.push_front(Integer.parseInt(st.nextToken()));
					break;
				case ("push_back") :
					deque.push_back(Integer.parseInt(st.nextToken()));
					break;
				case ("pop_front") :
					deque.pop_front();
					break;
				case ("pop_back") :
					deque.pop_back();
					break;
				case ("size") :
					deque.size();
					break;
				case ("empty") :
					deque.empty();
					break;
				case ("front") :
					deque.front();
					break;
				case ("back") :
					deque.back();
					break;
			}
		}
		bw.flush();
		br.close();
		bw.close();
	}
}