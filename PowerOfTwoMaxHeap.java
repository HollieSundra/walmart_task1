import java.util.ArrayList;

public class PowerOfTwoMaxHeap {
    private ArrayList<Integer> heap;
    private final int childrenFactor;

    public PowerOfTwoMaxHeap(int power) {
        if (power < 0) {
            throw new IllegalArgumentException("Power must be non-negative.");
        }
        this.heap = new ArrayList<>();
        this.childrenFactor = (int) Math.pow(2, power);
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() -  1);
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap  is empty.");
        }

        int max = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            heapifyDown(0);
        }

        return max;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int  parentIndex = (index - 1) / childrenFactor;
            if (heap.get(index) > heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
            }else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int largest = index;

        while (true) {
            int startChildIndex = childrenFactor * index + 1;
            int endChildIndex = Math.min(startChildIndex + childrenFactor, heap.size());

            for (int i = startChildIndex; i < endChildIndex; i++) {
                if (i < heap.size() && heap.get(i) > heap.get(largest)) {
                    largest = i;
                }
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(1);

        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);
        heap.insert(15);

        heap.printHeap();

        System.out.println("Max:" + heap.popMax());
        heap.printHeap();

        System.out.println("Max: " + heap.popMax());
        heap.printHeap();
    }
}