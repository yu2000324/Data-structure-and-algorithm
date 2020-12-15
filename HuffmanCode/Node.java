package HuffmanCode;

public class Node implements Comparable<Node>{
	Byte data;    //存放数据本身
	int weight;  //权值
	Node left;
	Node right;
	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.weight-o.weight;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
	
}
