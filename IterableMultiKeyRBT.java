// --== CS400 Fall 2023 File Header Information ==--
// Name: Rishika Gudise
// Email: gudise@wisc.edu
// Group: C28
// TA: Manas Trivedi
// Lecturer: Florian H.
// Notes to Grader: <optional extra notes>

import java.util.Iterator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Stack;
import java.util.NoSuchElementException;


public class IterableMultiKeyRBT<T extends Comparable<T>> extends RedBlackTree<KeyListInterface<T>> implements IterableMultiKeySortedCollectionInterface<T> {

	private Comparable<T> iteratorStartPoint;
	private int numKeys;

    /**
     * Inserts value into tree that can store multiple objects per key by keeping
     * lists of objects in each node of the tree.
     * @param key object to insert
     * @return true if a new node was inserted, false if the key was added into an existing node
     */
    public boolean insertSingleKey(T key){
		KeyList<T> keyList = new KeyList<>(key);

		RBTNode<KeyListInterface<T>> current = (RBTNode<KeyListInterface<T>>)root;
		while(current != null){
			int compare = keyList.compareTo(current.data);

			if (compare < 0){
				if(current.getDownLeft() == null) {
					insert(keyList);
					//insert(new RBTNode<KeyListInterface<T>>(keyList));
					numKeys++;
					return true;
				}
				current = current.getDownLeft();
			}else if(compare > 0){
				if(current.getDownRight() == null){
					insert(keyList);
					//insert(new RBTNode<>(keyList));
					numKeys ++;
					return true;
				}
				current = current.getDownRight();
			}else{
				current.data.addKey(key);
				return false;
			}
		}

		insert(keyList);
		numKeys++;
		return true;
	}

    /**
     * @return the number of values in the tree.
     */
    public int numKeys(){
	return numKeys;
	}

    /**
     * Returns an iterator that does an in-order iteration over the tree.
     */
    public Iterator<T> iterator(){
                return new Iterator<T>() {
			private Stack<Node<KeyListInterface<T>>> stack = getStartStack();
			private Iterator<T> iter = null;

		@Override
		public boolean hasNext() {
			if(iter != null && iter.hasNext()) {
				return true;
			}
			while (!stack.isEmpty()){
				Node<KeyListInterface<T>> node = stack.pop();
				iter = node.data.iterator();
				if(iter.hasNext()){
					return true; 
				} 
			}
			return false;
		}

		@Override
		public T next(){
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			return iter.next();

		}

		};
	}


    protected Stack<Node<KeyListInterface<T>>> getStartStack(){
	Stack<Node<KeyListInterface<T>>> stack = new Stack<>();
	Node<KeyListInterface<T>> current = root;

	if(iteratorStartPoint == null){
		while(current != null){
			stack.push(current);
			current = current.down[0]; 
		}
	   }
	else{

            while (current != null) {
                if (current.data.compareTo(new KeyList<>((T)this.iteratorStartPoint)) >= 0) {
                    stack.push(current);
                    current = current.down[0];
                }else{
                        current = current.down[1];
        }

        }
        }
	return stack;

}

    /**
     * Sets the starting point for iterations. Future iterations will start at the
     * starting point or the key closest to it in the tree. This setting is remembered
     * until it is reset. Passing in null disables the starting point.
     * @param startPoint the start point to set for iterations
     */
    public void setIterationStartPoint(Comparable<T> startPoint){
		iteratorStartPoint = startPoint;
	}

   @Override
   public void clear(){
		super.clear();
		numKeys = 0;
	}

   //Tests if the insertSingleKey method is properly inserting a key into the tree
   @Test
   public void testInsertSingleKey(){
	//create instance of IterableMultiKeyRBT
	IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
	//insert a single key
	boolean result = tree.insertSingleKey(10);
	//assert to see if the new node was successfully inserted
	Assertions.assertTrue(result);
	}

   //checks if the numKeys method can count the number of keys in a tree correctly
   @Test
   public void testNumKeys() {
	//create instance of IterableMultiKeyRBT
	IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
	//Insert some keys
	tree.insertSingleKey(10);
	tree.insertSingleKey(15);
	//get number of keys in the tree
	int numKeys = tree.numKeys();
	//assert to see if method gets the correct number of keys in the tree
	Assertions.assertEquals(2, numKeys);
	}

   //checks to see if the iterator iterates through the tree correctly in the right order
   @Test
   public void testIterator() {
	//create instance of IterableMultiKeyRBT
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
	//insert some keys
	tree.insertSingleKey(10);
	tree.insertSingleKey(20);
	tree.insertSingleKey(5);

	//create an iterator
	Iterator<Integer> iterator  = tree.iterator();
	//System.out.println( iterator.next());
	//System.out.println(iterator.next());
	//System.out.println(iterator.next());
	//iterate and assert the right order;
	Assertions.assertEquals(5, iterator.next());
	Assertions.assertEquals(10, iterator.next());
	}


}
