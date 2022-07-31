import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static class AbsMinHeap {
		private int[] heap;
		private int size;
		private int capacity;
		
		AbsMinHeap(int capacity) {
			size = 0;
			this.capacity = capacity;
			heap = new int[this.capacity + 1];
		}
		
		void swap(int idx1, int idx2) {
			int tmp = heap[idx1];
			heap[idx1] = heap[idx2];
			heap[idx2] = tmp;
		}
		
		boolean checkAbsMin(int idx1, int idx2) {
			if (Math.abs(heap[idx1]) < Math.abs(heap[idx2])) {
				return true;
			} else if (Math.abs(heap[idx1]) > Math.abs(heap[idx2])) {
				return false;
			} else {
				if (heap[idx1] < heap[idx2]) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		void push(int x) {
			int idx = ++size;
			heap[idx] = x;
			
			while (idx > 1 && checkAbsMin(idx, idx / 2)) {
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
					int maxIdx = idx * 2;	// 왼쪽 자식
					if (maxIdx + 1 <= size) {	// 오른쪽 자식이 있다면
						if (checkAbsMin(maxIdx + 1, maxIdx)) {
							maxIdx += 1;
						}
					}
					
					if (checkAbsMin(maxIdx, idx)) {
						swap(maxIdx, idx);
						idx = maxIdx;
					} else {
						break;
					}
				}
				return answer;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		AbsMinHeap heap = new AbsMinHeap(N);
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