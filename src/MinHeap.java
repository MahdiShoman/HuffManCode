import java.util.Arrays;

public class MinHeap {
    private int capacity = 10;
    int size = 0;
    private Node[] items = new Node[capacity];

    public MinHeap() {
    }

    public MinHeap(int capacity) {
        this.capacity = capacity;
    }

    public MinHeap(int size, Node[] items) {
        this.size = size;
        this.items = Arrays.copyOf(items, capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private Node parent(int index) {
        return items[getParentIndex(index)];
    }

    private Node leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private Node rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private void swap(int index1, int index2) {
        Node temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public Node getMinimum() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return items[0];
    }

    public Node deleteMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        Node item = items[0];
        items[0] = items[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    public void insert(Node item) {
        ensureCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index).symbol > items[index].symbol) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).symbol < leftChild(index).symbol) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index].symbol < items[smallerChildIndex].symbol) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(new Node((byte) 5));
        minHeap.insert(new Node((byte) 3));
        minHeap.insert(new Node((byte) 8));
        minHeap.insert(new Node((byte) 1));
        minHeap.insert(new Node((byte) 10));

        System.out.println("Min Heap: ");
        while (minHeap.size > 0) {
            System.out.print(minHeap.deleteMin().symbol + " ");
        }
        System.out.println(minHeap.size);
    }
}
