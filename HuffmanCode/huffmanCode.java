package HuffmanCode;

import java.util.*;

public class huffmanCode {

	// 声明哈夫曼编码表，static修饰，在多个方法中共同使用
	public static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	// 编码完成后最后一个字节数组的长度
	public static int endLen;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = "abbcccddddeeeee";
		byte[] contentBytes = content.getBytes();
		byte[] huffmanCodeBytes = huffmanZip(contentBytes);
		byte[] source = decode(huffmanCodes, huffmanCodeBytes);
		System.out.println("原来的字符串=" + new String(source));
	}

	// 创建一个接口函数封装好实现的细节
	/**
	 * 
	 * @param bytes 原始字符串对应的字节数组
	 * @return 返回处理后的字节数组
	 */
	public static byte[] huffmanZip(byte[] bytes) {
		System.out.println("处理前的字节数组："+Arrays.toString(bytes)+" 长度="+bytes.length);
		List<Node> nodes = getNodes(bytes);
		// 创建哈夫曼树
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		// 对应的哈夫曼编码
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		// 根据生成的哈夫曼编码，得到压缩后的数组
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		System.out.println("处理后的字节数组："+Arrays.toString(huffmanCodeBytes)+" 长度="+huffmanCodeBytes.length);
		preOrder(huffmanTreeRoot);
		return huffmanCodeBytes;
	}

	private static List<Node> getNodes(byte[] bytes) {
		// 创建Arrlist
		ArrayList<Node> nodes = new ArrayList<Node>();
		// 遍历bytes，统计byte出现的次数->map
		Map<Byte, Integer> counts = new HashMap();
		for (byte b : bytes) {
			if (!counts.containsKey(b)) {
				counts.put(b, 1);
			} else {
				counts.put(b, counts.get(b) + 1);
			}
		}
		// 把每一个键对转成一个Node对象，并加入到nodes集合中
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}

	// 写一个前序遍历
	public static void preOrder(Node node) {
		if (node != null) {
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	// 可以通过list创建哈夫曼树
	/**
	 * @param nodes 要处理的nodes
	 * @return 返回root节点
	 */
	public static Node createHuffmanTree(List<Node> nodes) {
		// 传入的list中大于1个节点时候才构建二叉树
		while (nodes.size() > 1) {
			// 对集合里面的元素进行排序
			Collections.sort(nodes);
			// 取出权值最小的节点
			Node left = nodes.get(0);
			Node right = nodes.get(1);
			// 构建一个新的二叉树节点，并且初始化它的值为null
			Node parent = new Node(null, left.weight + right.weight);
			// 把两个节点挂在一个非子节点上
			parent.left = left;
			parent.right = right;
			// 移除左右两个节点
			nodes.remove(left);
			nodes.remove(right);
			// 把这个非叶子节点加入到存放到nodes中
			nodes.add(parent);
		}
		return nodes.get(0);
	}

	// 生成哈夫曼树对应的哈夫曼编码
	// 思路：
	// 将哈夫曼编码存放在map中比较合适 Map<Byte,String>
	// 32->01 97->100 100->11000

	// 为了调用方便，重载getCodes
	private static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		if (root.left == null && root.right == null) {
			huffmanCodes.put(root.data, "0");
		}
		// 在生成哈夫曼编码表时，需要去拼接路径，定义一个StringBuilder 存储叶子节点的路径
		StringBuilder builder = new StringBuilder();
		// 处理左子树
		getCodes(root.left, "0", builder);
		// 处理右子树
		getCodes(root.right, "1", builder);
		return huffmanCodes;
	}

	/**
	 * 功能：将传入的node节点的所有叶子节点的哈夫曼得到，并放入huffmanCodes中
	 * 
	 * @param node    //传入节点
	 * @param code    //路径：左子节点是0 右子节点1
	 * @param builder //用于拼接路径
	 */
	private static void getCodes(Node node, String code, StringBuilder builder) {
		// 重建StringBuilder是为了防治地址引用一值，保持生成字符不共用同一字符串
		StringBuilder builder2 = new StringBuilder(builder);
		// 将code加入到StringBuilder
		builder2.append(code);
		if (node != null) { // 如果为空不进行处理
			// 判断当前是叶子还是非叶子节点
			if (node.data == null) {
				// 递归处理
				// 左边递归
				getCodes(node.left, "0", builder2);
				// 右边递归
				getCodes(node.right, "1", builder2);
			} else {
				// 找到了叶子节点
				huffmanCodes.put(node.data, builder2.toString());
			}
		}
	}

	// 编写一个方法，将字符串对应的byte[]数组，通过生成哈夫曼编码表，返回一个哈夫曼编码处理后的byte[]数组
	/**
	 * @param bytes       原始数据要处理的byte数组
	 * @param huffmanCode //哈夫曼编码表
	 * @return //返回处理后的byte[]
	 */
	public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
		// 利用huffmanCodes将byte转成哈夫曼对应的字符串
		StringBuilder builder = new StringBuilder("");
		// 遍历数组
		for (byte b : bytes) {
			builder.append(huffmanCodes.get(b));
		}
		// 将"10101000..." 转成byte[]
		// 统计返回的哈夫曼编码有多长
		int len = (builder.length() % 8) == 0 ? builder.length() / 8 : builder.length() / 8 + 1;
		endLen = builder.length() % 8;
		// 创建 存储后的bytes压缩数组
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;// 记录是第几个byte
		for (int i = 0; i < builder.length(); i += 8) {// 因为是每8位对应一个byte，所以步长+8
			String strByte;
			// 两种情况i+8超过最后位置和不超过的分别赋值

			strByte = i + 8 > builder.length() ? builder.substring(i) : builder.substring(i, i + 8);
			// 后面一个参数2表示转换成二进制
			huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
		}
		return huffmanCodeBytes;
	}

	// 编写一个方法，完成对压缩数据对解码
	/**
	 * @param huffmanCodes 哈夫曼编码表
	 * @param huffmanBytes 哈夫曼编码得到对字节数组
	 * @return 就是原来对字符串对应对数组
	 */
	public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
		// 1.先得到 huffmanBytes 对应对二进制字符串，形式10101000...
		StringBuilder builder = new StringBuilder();
		// 2.将byte数组转成二进制的字符串
		for (int i = 0; i < huffmanBytes.length; i++) {
			byte b = huffmanBytes[i];
			// 判断是不是最后一个字节
			boolean flag = (i == huffmanBytes.length - 1);
			builder.append(byteToBitString(!flag, b));
		}
//		System.out.println("哈夫曼字节数组对应的二进制字符串="+builder.toString());
		// 把字符串按照指定的哈夫曼进行解码
		// 把哈夫曼编码表进行调换，因为要进行反向查询
		Map<String, Byte> map = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		// 创建集合，存放byte
		List<Byte> list = new ArrayList<>();
		for (int i = 0; i < builder.length();) {
			int count = 1; // 小的计数器
			boolean flag = true;
			Byte b = null;
			while (flag) {
				// 取出一个bit '1'或者'0'
				String key = builder.substring(i, i + count); // i 不动 让count移动，直到匹配到一个字符
				b = map.get(key);
				if (b == null) {// 没有匹配到
					count++;
				} else {
					flag = false;
				}
			}
			list.add(b);
			i = i + count;
		}
		// 当for循环结束以后，list中存放了所有当字符
		// 把list中的数据放入到byte[] 并返回
		byte b[] = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}

	// 完成数据转成对应的二进制字符串'10101000...'
	/**
	 * 将一个byte 转成一个二进制的字符串
	 * 
	 * @param b    传入的是一个字节b
	 * @param flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false表示不会补高位
	 * @return 是该b对应对二进制对字符串，（注意是按照补码返回）
	 */
	public static String byteToBitString(boolean flag, byte b) {
		// 使用一个变量保持b
		int temp = b; // 将b转成int
		temp |= 256;
		String str = Integer.toBinaryString(temp);// 返回的是temp对应的二进制补码
		if (flag || (flag == false && endLen == 0)) {
			return str.substring(str.length() - 8);
		} else {
			return str.substring(str.length() - endLen);
		}
	}

}
