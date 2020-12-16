package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 无向图的创建 会造成一半内存的浪费
 * 
 * @author yu
 *
 */
public class Graph {

	private ArrayList<String> vertexList; // 存储顶点集合
	private int[][] edges; // 存储图对应的领结矩阵,是顶点和顶点连接关系的表达，根据传入两个顶点下标制造出关系，实质是边
	private int numOfEdges; // b表示边的数目
	private boolean[] isVisited;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 测试代码
		int n = 5;
		String vertex[] = { "A", "B", "C", "D", "E" };
		Graph graph = new Graph(n);
		// 循环添加顶点
		for (String value : vertex) {
			graph.insertVertex(value);
		}
		// 添加边
		graph.insertEdge(0, 1, 1); // A->B
		graph.insertEdge(0, 2, 1); // A->C
		graph.insertEdge(1, 2, 1); // B->C
		graph.insertEdge(1, 3, 1); // B->D
		graph.insertEdge(1, 4, 1); // B->E
		graph.showGraph();
		
//		//深度遍历
//		System.out.println("深度遍历");
//		graph.dfs();
//		//广度优先
		System.out.println("\n广度遍历");
		graph.bfs();
	}

	public Graph(int n) {
		// 初始化矩阵和vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		numOfEdges = 0;
		isVisited = new boolean[n];
	}

	// 得到第一个领结节点的下标
	/**
	 * @param index
	 * @return 返回对应下标，否则返回-1
	 */
	public int getFirstNeighbor(int index) {
		for (int i = 0; i < vertexList.size(); i++) {
			if (edges[index][i] > 0) {
				return i;
			}
		}
		return -1;
	}

	// 根据前一个领结节点的下标来获取下一个领结节点的下标
	public int getNextNeighbor(int v1, int v2) {				//对整个矩阵进行操作
		for (int i = v2 + 1; i < vertexList.size(); i++) {
			if(edges[v1][i]>0) {
				return i;
			}
		}
		return -1;
	}
	
	//深度优先遍历算法
	public void dfs(boolean[] isVisited,int i) {
		//首先我们先访问该节点
		System.out.print(getValueByIndex(i)+"->");
		//将该节点设置为已经访问过了
		isVisited[i]=true;
		//查找节点i的第一个领结节点
		int w=getFirstNeighbor(i);
		while(w!=-1) {// 说明有节点可以访问
			if(!isVisited[w]) {
				dfs(isVisited,w);
			}
			//如果被访问过了
			w=getNextNeighbor(i,w);      //重新选择可用的领结节点
		}
	}
	
	//非连通图进行dfs的重载
	 public void dfs() {
		 //遍历所有节点
		 for(int i=0;i<getNumOfVertex();i++) {
			 if(!isVisited[i]) {
				 dfs(isVisited,i);
			 }
		 }
	 }
	 
	 //对一个节点进行广度优先遍历的方法
	 public void bfs(boolean[] isVisited,int i) {
		 int u;    //表示队列的头节点对应下标
		 int w;		//领结节点w
		 //队列
		 LinkedList queue =new LinkedList();
		 //访问节点
		 System.out.print(getValueByIndex(i)+"=>");
		 //标为已访问
		 isVisited[i]=true;
		 //将节点加入队列
		 queue.addLast(i);;
		 while(!queue.isEmpty()) {
			 //取出队列头节点的下标
			 u=(Integer)queue.removeFirst(); 
			 //得到第一个领结点的下标
			 w=getFirstNeighbor(u);
			 while(w!=-1) {//找到了
				 //是否访问过
				 if(!isVisited[w]) {
					 System.out.print(getValueByIndex(w)+"=>");
					 //标记已经访问
					 isVisited[w]=true;
					 //入队列
					 queue.addLast(w);
				 }
				 //以u为前驱带点找w后面的下一个领结点
				 w=getNextNeighbor(u,w);	//体现出广度优先
			 }
		 }
	 }
	 
	 //遍历所有的节点，都进行广度优先搜索
	 public void bfs() {
		 for(int i=0;i<getNumOfVertex();i++) {
			 if(!isVisited[i]){
				 bfs(isVisited,i);
			 }
		 }
	 }

	// 图中常用的方法
	// 返回节点的个数
	public int getNumOfVertex() {
		return vertexList.size();
	}

	// 得到边的个数
	public int getNumOfEdges() {
		return numOfEdges;
	}

	// 返回节点i（下标）对应的数据
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}

	// 返回v1和v2的值
	public int getWeight(int i, int j) {
		return edges[i][j];
	}

	// 显示图对应的矩阵
	public void showGraph() {
		for (int[] link : edges) {
			System.out.println(Arrays.toString(link));
		}
	}

	// 插入节点
	/**
	 * @param vertex 顶点名称
	 */
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}

	// 添加边
	/**
	 * @param v1     v1表示第几个顶点的下标 "A"-"B" "A"->0 "B"->1
	 * @param v2     v2表示第二个顶点的下标 "B"-"A" "B"->1 "A"->0
	 * @param weight
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}

}
