import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static class MinHeap {
		private int[] heap;
		private int size;
		private int capacity;
		
		MinHeap(int capacity) {
			size = 0;
			this.capacity = capacity;
			heap = new int[this.capacity + 1];
		}
		
		void swap(int idx1, int idx2) {
			int tmp = heap[idx1];
			heap[idx1] = heap[idx2];
			heap[idx2] = tmp;
		}
		
		void push(int x) {
			int idx = ++size;
			heap[idx] = x;
			
			while (idx > 1 && heap[idx / 2] > heap[idx]) {
				swap(idx, idx / 2);
				idx /= 2;
			}
		}
		
		int pop() {
			if (size <= 0) {
				return 0;
			} else {
				int answer = heap[1];
				heap[1] = heap[size--];
				int idx = 1;
				while (idx * 2 <= size) {
					int maxIdx = idx * 2;
					if (idx * 2 + 1 <= size) {
						if (heap[maxIdx] > heap[maxIdx + 1]) {
							maxIdx += 1;
						}
					}
					
					if (heap[idx] > heap[maxIdx]) {
						swap(idx, maxIdx);
					}
					idx = maxIdx;
				}
				return answer;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		MinHeap heap = new MinHeap(N);
		while (N-- > 0) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				bw.write(heap.pop() + "\n");
			} else {
				heap.push(x);
			}
		}
		
		bw.flush();
		br.close();
		bw.close();

	}
}