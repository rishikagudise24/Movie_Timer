// --== CS400 Fall 2023 File Header Information ==--
// Name: Rishika Gudise 
// Email: gudise@wisc.edu
// Group: C28
// TA: Manas Trivedi
// Lecturer: Florian 
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

   	 // Red-Black Tree specific methods and properties can be added here
	protected static class RBTNode<T> extends Node<T> {
    		public int blackHeight = 0;
    		public RBTNode(T data) { super(data); }
    		public RBTNode<T> getUp() { return (RBTNode<T>)this.up; }
    		public RBTNode<T> getDownLeft() { return (RBTNode<T>)this.down[0]; }
    		public RBTNode<T> getDownRight() { return (RBTNode<T>)this.down[1]; }
	}
	//check for and fixes any RBT violations such as when the parent is red. 
	protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> node) {

		RBTNode<T> parent = node.getUp();
		if(node.getUp() == null ){
			node.blackHeight = 1;
			return;
		}
		RBTNode<T> grandparent = parent.getUp();

		//case 1: the node's parent is BLACK, therefore NO VIOLATION
		if(parent.blackHeight ==  1|| node.blackHeight == 1){
			return;
		}

		//case 2: the node's parent is RED  --> either recolor if aunt is red or rotate and color swap if aunt is black
		RBTNode<T> aunt = (parent == grandparent.getDownLeft()) ? grandparent.getDownRight():grandparent.getDownLeft();
	 if(node.blackHeight == 0 && parent.blackHeight == 0){
		//aunt is red --> recoloring steps 
		if(aunt != null && aunt.blackHeight == 0) {
			parent.blackHeight = 1;
			aunt.blackHeight = 1;

			if(grandparent != root){
				grandparent.blackHeight = 0;
			}

			enforceRBTreePropertiesAfterInsert(grandparent);
			return;
		}
		//aunt is black --> rotate once or twice (line or triangle case) then color swap ! 
		if(aunt == null || aunt.blackHeight == 1) {
			if ((parent.isRightChild() && !node.isRightChild()) || (!parent.isRightChild() && node.isRightChild())){
				   rotate(node,parent);
				   node = parent;
				   parent = node.getUp();
				}
			rotate(parent, grandparent);
			}
		parent.blackHeight = 1;	
          	grandparent.blackHeight = 0;

	}
	enforceRBTreePropertiesAfterInsert(parent);
	((RBTNode<T>)root).blackHeight = 1;

}

	//Takes in a node and uses insertHelper to put the node in the tree, then calls enforceRBTreePropertiesAfterInsert to fix any 
	//RBT violations 
	@Override
	public boolean insert(T data) throws NullPointerException {
		if( data == null ){
			throw new NullPointerException("Cannot insert data value into the tree.");
		}
		RBTNode<T> newNode = new RBTNode<>(data);
		boolean inserted = insertHelper(newNode);
		
		if(inserted) {
			enforceRBTreePropertiesAfterInsert(newNode);
			((RBTNode<T>)root).blackHeight = 1;
		}


		return inserted;


	}


	//JUnit Tests --> 3 test methods 

	//Test 1: When aunt is red of a newly inserted red node, checks if recoloring was done properly to second level. 
	@Test
	public void testRecolorAuntRed() {
		//create/intitalized a new RBT 
		RedBlackTree<Integer> tree = new RedBlackTree<>();
		//defines root node and makes it black
		RBTNode<Integer> rootNode = new RBTNode<>(54);
		rootNode.blackHeight = 1;
		rootNode.down[0] = new RBTNode<>(14);
		rootNode.down[0].up = rootNode;
		rootNode.down[1] = new RBTNode<>(68);
		rootNode.down[1].up = rootNode;
		tree.root = rootNode;
		//insert a node into the tree
		tree.insert(24);
		//check if tree order is correct (no rotations on this tree) 
		Assertions.assertEquals("[ 54, 14, 68, 24 ]", tree.toLevelOrderString());
		Assertions.assertEquals(1,((RBTNode<Integer>)tree.root).getDownRight().blackHeight);
		Assertions.assertEquals(1,((RBTNode<Integer>)tree.root).blackHeight);
	}
	//Test 2: Check another recoloring situation when aunt of inserted node is red (similar to test 1)  
	@Test
	public void recolorTest2() {
	//initialize/create new RBT 
        RedBlackTree<Integer> tree = new RedBlackTree<>();
	//define root and its color to blakc 
	RBTNode<Integer> root = new RBTNode<>(45);
        root.blackHeight = 1;
        root.down[0] = new RBTNode<>(9);
        root.down[0].up = root;
        root.down[1] = new RBTNode<>(86);
        root.down[1].up = root;
        tree.root = root;
		//insert new node into tree
		tree.insert(65);
		//assert if the colors of the nodes are right after recoloring (aunt was red in this case) 
		Assertions.assertEquals(1, ((RBTNode<Integer>)tree.root).blackHeight);
		Assertions.assertEquals(1, ((RBTNode<Integer>)tree.root).getDownLeft().blackHeight);
		Assertions.assertEquals(1, ((RBTNode<Integer>)tree.root).getDownRight().blackHeight);
		Assertions.assertEquals(0, ((RBTNode<Integer>)tree.root).getDownRight().getDownLeft().blackHeight);
	}

	//Test 3: Testing when aunt of inserted node is black (specifically line case) if it will rotate and color swap accordingly.
	@Test
	public void blackAuntSingleLineTest() {
	//create/ initialize new tree 
	RedBlackTree<Integer> tree = new RedBlackTree<>();
	//define root and set color to black
	RBTNode<Integer> root = new RBTNode<>(30);
        root.blackHeight = 1;
        root.down[0] = new RBTNode<>(10);
        root.down[0].up = root;
	root.down[1] = new RBTNode<>(70);
	root.down[1].up = root;
	//make the aunt black for the sake of this test 
	((RBTNode<Integer>)root.down[1]).blackHeight = 1;
        tree.root = root;
		//insert new node into the tree
		tree.insert(5);
		//assert if the new order of tree is correct after single rotation (line case) 
		Assertions.assertEquals("[ 10, 5, 30, 70 ]", tree.toLevelOrderString());
		//assert if color swap took place correctly 
		Assertions.assertEquals(1, ((RBTNode<Integer>)tree.root).blackHeight);
		Assertions.assertEquals(0, ((RBTNode<Integer>)tree.root).getDownLeft().blackHeight);
		Assertions.assertEquals(0, ((RBTNode<Integer>)tree.root).getDownRight().blackHeight);
	}


}
