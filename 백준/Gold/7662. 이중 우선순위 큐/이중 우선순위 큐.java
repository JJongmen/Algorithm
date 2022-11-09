import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class DualPriorityQueue {
		private int[] maxHeap;
		private int[] minHeap;
		private int size = 0;
		
		DualPriorityQueue(int capacity) {
			maxHeap = new int[capacity + 1];
			minHeap = new int[capacity + 1];
		}
		
		void swap(int[] heap, int a, int b) {
			int tmp = heap[a];
			heap[a] = heap[b];
			heap[b] = tmp;
		}
		
		void insert(int n) {
			size++;
			
			// maxHeap
			maxHeap[size] = n;
			int idx = size;
			while (idx > 1) {
				// 자식이 부모보다 크면 위치를 바꾼다
				if (maxHeap[idx] > maxHeap[idx / 2]) {
					swap(maxHeap, idx, idx / 2);
					idx /= 2;
				} else {
					break;
				}
			}
			
			// minHeap
			minHeap[size] = n;
			idx = size;			
			while (idx > 1) {
				// 자식이 부모보다 작으면 위치를 바꾼다
				if (minHeap[idx] < minHeap[idx / 2]) {
					swap(minHeap, idx, idx / 2);
					idx /= 2;
				} else {
					break;
				}
			}
		}
		
		void deleteMax() {
			if (this.isEmpty()) {
				return;
			} else {
				// maxHeap
				int max = this.findMax();
				maxHeap[1] = maxHeap[size--];
				int idx = 1;
				while (idx * 2 <= size) {
					// 왼쪽 자식의 위치를 저장한다.
					int maxChildIdx = idx * 2;
					// 오른쪽 자식이 있다면 더 큰 자식을 저장한다.
					if (maxChildIdx + 1 <= size && 
							maxHeap[maxChildIdx] < maxHeap[maxChildIdx + 1]) {
						maxChildIdx++;
					}
					// 부모보다 자식이 더 크면 위치를 바꾼다.
					if (maxHeap[idx] < maxHeap[maxChildIdx]) {
						swap(maxHeap, idx, maxChildIdx);
						idx = maxChildIdx;
					} else {
						break;
					}
				}
				
				//minHeap
				size++;	//maxHeap에서 size를 줄였어서 다시 올려줌
				for (int i = size; i >= 1; i--) {
					if (minHeap[i] == max) {
						idx = i;
						break;
					}
				}
				minHeap[idx] = minHeap[size--];
				while (idx > 1) {
					// 자식이 부모보다 작으면 위치를 바꾼다
					if (minHeap[idx] < minHeap[idx / 2]) {
						swap(minHeap, idx, idx / 2);
						idx /= 2;
					} else {
						break;
					}
				}
			}
		}
		
		void deleteMin() {
			if (this.isEmpty()) {
				return;
			} else {
				// minHeap
				int min = this.findMin();
				minHeap[1] = minHeap[size--];
				int idx = 1;
				while (idx * 2 <= size) {
					// 왼쪽 자식의 위치를 저장한다.
					int minChildIdx = idx * 2;
					// 오른쪽 자식이 있다면 더 작은 자식을 저장한다.
					if (minChildIdx + 1 <= size && 
							minHeap[minChildIdx] > minHeap[minChildIdx + 1]) {
						minChildIdx++;
					}
					// 부모보다 자식이 더 작으면 위치를 바꾼다.
					if (minHeap[idx] > minHeap[minChildIdx]) {
						swap(minHeap, idx, minChildIdx);
						idx = minChildIdx;
					} else {
						break;
					}
				}
				
				//maxHeap
				size++;	//minHeap에서 size를 줄였어서 다시 올려줌
				for (int i = size; i >= 1; i--) {
					if (maxHeap[i] == min) {
						idx = i;
						break;
					}
				}
				maxHeap[idx] = maxHeap[size--];
				while (idx > 1) {
					// 자식이 부모보다 크면 위치를 바꾼다
					if (maxHeap[idx] > maxHeap[idx / 2]) {
						swap(maxHeap, idx, idx / 2);
						idx /= 2;
					} else {
						break;
					}
				}
			}
		}
		
		int findMax() {
			return maxHeap[1];
		}
		
		int findMin() {
			return minHeap[1];
		}
		
		boolean isEmpty() {
			if (size == 0) {
				return true; 
			} else {
				return false;
			}
		}
		
		int size() {
			return size;
		}
		
		int[] getMaxHeap() {
			return maxHeap;
		}
		
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int k = Integer.parseInt(br.readLine());
			DualPriorityQueue que = new DualPriorityQueue(k);
			while (k-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String expr = st.nextToken();
				int n = Integer.parseInt(st.nextToken());
				if (expr.equals("I")) {
					que.insert(n);
				} else if (expr.equals("D")) {
					if (n == 1) {
						que.deleteMax();
					} else if (n == -1) {
						que.deleteMin();
					}
				}
			}
			if (que.isEmpty()) {
				bw.write("EMPTY\n");
			} else {
				bw.write(que.findMax() + " " + que.findMin() + "\n");
			}
		}
		
		bw.flush();
		br.close();
		bw.close();
		
	}
}