package chen;



public class Tree {
	Node root;
	
	public void addNode(int key, String name){
		Node newNode = new Node(key,name);
		if (root == null){
			root = newNode;
		} else {
			Node focusNode = root;
			Node parent;
			while (true) {
				parent = focusNode;
				if (key < focusNode.key){
					focusNode = focusNode.leftchild;
				}
				if (focusNode == null){
					parent.leftchild = newNode;
					return;
				}
			} else {
				focusNode = focusNode.rightchild;
				if (focusNode == null){
					parent.rightchild = newNode;
					return;
				}
			}
		}
	}
	
	
	
	





	public static void main(String [] args){
		
	}
	
	
	
}



