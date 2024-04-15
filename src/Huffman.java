import java.util.PriorityQueue;

public class Huffman {
    /*
     *have the huffman code
     *
     */


    String [] huffmanCodes ;
    int[] byteFrequencies;
    Node rootHuffmanTree;

    public Huffman() { // in Gui
        huffmanCodes=new String[256];
    }

    public Huffman(int[] byteFrequencies) {
        huffmanCodes=new String[256];
        this.byteFrequencies = byteFrequencies;
    }

    void displayHuffmanTable() {
        GUI.textArea.appendText("Byte\t\thuffCode\t\tlength\t Frequency");
        GUI.textArea.appendText("\n");
        for (int i = 0; i < byteFrequencies.length; i++) {
            if (byteFrequencies[i] > 0) {

                GUI.textArea.appendText(i + "\t\t" + huffmanCodes[i]+ "\t\t" +huffmanCodes[i].length()+ "\t\t" +byteFrequencies[i]);
                GUI.textArea.appendText("\n");
            }
        }
    }
    void displayHeaderFile() {
        GUI.textArea.setText("Byte\t\thuffCode");
        GUI.textArea.appendText("\n");
        for (int i = 0; i < byteFrequencies.length; i++) {
            if (byteFrequencies[i] > 0) {
                GUI.textArea.appendText(i + "\t\t" + huffmanCodes[i]);
                GUI.textArea.appendText("\n");
            }
        }
    }

    void buildHuffmanTree() { // get root node
        PriorityQueue<Node> minHeap = new PriorityQueue<>(256);
        // Insert leaves in the minHeap
        for (int i = 0; i < byteFrequencies.length; i++) {
            if (byteFrequencies[i] > 0) {
                minHeap.offer(new Node((byte) i, byteFrequencies[i]));

            }
        }
        // Build the Huffman tree using a min-heap
        while (minHeap.size() > 1) {
            //del first two min sum two freq ,put it in new node ,with lef(Fmin)_right(Smin)
            Node x = minHeap.poll();
            Node y = minHeap.poll();

            Node z = new Node((byte) 0, x.frequency + y.frequency);
            z.left = x;
            z.right = y;

            minHeap.offer(z);
        }
        rootHuffmanTree = minHeap.poll();
    }


    void generateHuffmanCodes(Node node, String code, String[] huffmanCodes) {//  preorder
        if (node != null) {
            if (node.isLeaf()) {
                huffmanCodes[Byte.toUnsignedInt(node.symbol)] = code;
            } else {
                generateHuffmanCodes(node.left, code + "0", huffmanCodes);
                generateHuffmanCodes(node.right, code + "1", huffmanCodes);
            }
        }
    }

    String getHuffCode(int idx){
        return huffmanCodes[idx];
    }
    public String[] getHuffmanCodes() {
        return huffmanCodes;
    }

    public void setHuffmanCodes(String[] huffmanCodes) {
        this.huffmanCodes = huffmanCodes;
    }

    public int[] getByteFrequencies() {
        return byteFrequencies;
    }

    public void setByteFrequencies(int[] byteFrequencies) {
        this.byteFrequencies = byteFrequencies;
    }

    public Node getRootHuffmanTree() {
        return rootHuffmanTree;
    }

    public void setRootHuffmanTree(Node rootHuffmanTree) {
        this.rootHuffmanTree = rootHuffmanTree;
    }
}
