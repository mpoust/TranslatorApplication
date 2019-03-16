import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;

/*
 * Michael Poust
 * mbp3@pct.edu
 * 11/15/2016
 * 
 * This is the Binary Search Tree (BST) class. This implementation is based off 
 * of the BST class provided by the textbook's source. Only the necessary classes
 * for this application have been implemented, some with slight modifications
 * as needed.
 * 
 */

public class BST <Key extends Comparable<Key>, Value> {
	private Node root;
	
	/****** Nested Node Class ******/
	private class Node{
		private Key 	key;	// English word - used to sort 
		private Value 	value; 	// Spanish word - translated key
		private Node	left; 	// pointer to left subtree
		private Node 	right; 	// pointer to right subtree
		private int 	size; 	// number of nodes in the subtree ** maybe removing
		
		public Node(Key k, Value v, int s){
			key 	= k;
			value 	= v;
			size 	= s;
		}
	}
	
	/**
	 * Initializes an empty Binary Search Tree.
	 */
	public BST(){
		root = null;
	}
	
	/**
	 * Returns true if the BST is empty.
	 * @return true if empty, otherwise false
	 */
	public boolean isEmpty(){
		return size() == 0;
	}
	
	/**
	 * Returns the number of Nodes (key-value pair) in the BST.
	 * @return number of Nodes (key-value pair) in the BST
	 */
	public int size(){
		return size(root);
	}
	
	/**
	 * private helper method to size() to return size of node.
	 * @param n Node passed in to access size
	 * @return size of given node
	 */
	private int size(Node n){
		if(n == null)
			return 0;
		return n.size;
	}
	
	/**
	 * looks to see if the given key is in the BST.
	 * throws a NullPointerException if the passed key is null
	 * @param key Key passed in to look for in the BST
	 * @return true if key is in BST, otherwise false
	 */
	public boolean lookup(Key key){
		if(key == null)
			throw new NullPointerException("Argument to lookup() is null");
		return getVal(key) != null;
	}
	
	/**
	 * helper method to lookup() to return the value corresponding to the given key
	 * @param key Key passed in to get value of
	 * @return value of given key
	 */
	public Value getVal(Key key){
		return getVal(root, key);
	}
	
	/**
	 * private helper method to getVal(Key key) to return value corresponding to given key
	 * starting at a given node - root from getVal(Key key).
	 * @param node Node to start comparing key value
	 * @param key Key to compare at each node
	 * @return null if Node is null, else recursively return value of node
	 */
	private Value getVal(Node node, Key key){
		if(node == null)
			return null;
		if(key.compareTo(node.key) < 0)
			return getVal(node.left, key);
		else if(key.compareTo(node.key) > 0)
				return getVal(node.right, key);
		else
			return node.value;
	}
	
	/**
	 * method to insert Nodes into the BST, uses helper insert methods 
	 * and gets the data from supplied file.
	 */
	@SuppressWarnings("unchecked")
	public void insert(){
		String path = new File("").getAbsolutePath(); // set path to file location
		try{
			// creating BufferedReader based off of determined path and file name, using UTF-8
			BufferedReader	  br 	= new BufferedReader(new InputStreamReader(
									  new FileInputStream(path+"/SpanishEnglishData.txt"), "UTF-8"));
			ArrayList<String> words = new ArrayList<>();
			String			  lineFetched = null;
			String[] 		  wordsArray;
			Key 			  key;
			Value 			  value;
			
			/*
			 * adds all key-value pairs from supplied file
			 * based off of tab-delimited values, ignores lines without a tab
			 */
			while(true){
				lineFetched = br.readLine();
				if(lineFetched == null)
					break;
				else
					if(lineFetched.contains("\t")){				// SKIPS LINES WITH HEADINGS
						wordsArray = lineFetched.split("\t");
						for(String each : wordsArray)
							if(!"".equals(each))
								words.add(each);
					}
			}
			/*
			 * takes key-value pairs grabbed from file and
			 * calls insert(key, value) to populate BST
			 */
			for(int i = 0; i < words.size(); i=i+2){
				key 	= (Key) words.get(i);		
				value 	= (Value) words.get(i+1);	
				insert(key, value);						// may throw NullPointerException
			}
			br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * private helper method to insert() that checks for null values 
	 * and calls further helper method if values are not null.
	 * @param key Key value to be inserted
	 * @param value Value value to be inserted
	 */
	private void insert(Key key, Value value){
		if(key == null)
			throw new NullPointerException("FIRST argument to put() is null");
		if(value == null)
			throw new NullPointerException("SECOND argument to put() is null");
		root = insert(root, key, value);
	}
	
	/**
	 * final helper method to insert() to make decisions based off of key values
	 * where to insert the new Node in the BST.
	 * @param node Node to make decision where to insert
	 * @param key Key to be inserted in new Node
	 * @param value Value to be inserted in new Node
	 * @return node with key-value pair and size assigned
	 */
	private Node insert(Node node, Key key, Value value){
		if(node == null)
			node = new Node(key, value, 1);
		else{
			if(key.compareTo(node.key) < 0)
				node.left = insert(node.left, key, value);
			else if(key.compareTo(node.key) > 0)
				node.right = insert(node.right, key, value);
			else
				node.value = value;
		}
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}

	/**
	 * returns the minimum Node based off of key in the BST.
	 * @return minimum Node in BST
	 */
	public Key min(){
		return min(root).key;
	}
	
	/**
	 * helper method to min() to return minimum Node in BST based off of key.
	 * @param node Node passed to determine minimum from
	 * @return minimum node in BST
	 */
	private Node min(Node node){
		if(node.left == null)
			return node;
		else
			return min(node.left);
	}
	
	/**
	 * returns the maximum Node based off of key in the BST.
	 * @return maximum Node in BST
	 */
	public Key max(){
		return max(root).key;
	}
	
	/**
	 * helper method to max() to return maximum Node in BST based off of key.
	 * @param node Node passed to determine maximum from
	 * @return maximum Node in BST
	 */
	private Node max(Node node){
		if(node.right == null)
			return node;
		else
			return max(node.right);
	}

	/**
	 * method to return an Iterable list of all the keys in the BST.
	 * @return Iterable list of keys
	 */
	public Iterable<Key> getKeys(){
		return getKeys(min(), max());
	}
	
	/**
	 * private helper method to getKeys() to return Iterable list of all the keys
	 * @param min Key returned by min()
	 * @param max Key returned by max()
	 * @return queue of keys
	 */
	private Iterable<Key> getKeys(Key min, Key max){
		if(min == null)
			throw new NullPointerException("First argument to keys() is null");
		if(max == null)
			throw new NullPointerException("Second argument to keys() is null");
		
		Queue<Key> queue = new Queue<Key>();
		getKeys(root, queue, min, max);
		return queue;
	}
	
	/**
	 * final helper method to getKeys(). recursively gets the keys from the BST
	 * and places them into a queue that is then returned from the previous
	 * helper method
	 * @param node Node to start placing into the queue
	 * @param queue Queue to hold the keys from the BST
	 * @param min Minimum key from the tree
	 * @param max Maximum key from the tree
	 */
	private void getKeys(Node node, Queue<Key> queue, Key min, Key max){
		if(node == null)
			return;
		int minCompare = min.compareTo(node.key);
		int maxCompare = max.compareTo(node.key);
		if(minCompare < 0)
			getKeys(node.left, queue, min, max);
		if(minCompare <= 0 && maxCompare >= 0)
			queue.enqueue(node.key);
		if(maxCompare > 0)
			getKeys(node.right, queue, min, max);
	}
	
	/**
	 * method to return an Iterable list of all the keys from the BST in order
	 * @return Iterable ArrayList of keys
	 */
	public Iterable<Key> inOrder(){
		ArrayList<Key> keys = new ArrayList<Key>();
		inOrder(keys, root);
		return keys;
	}
	
	/**
	 * private helper method to inOrder(). recursively adds the keys to the ArrayList
	 * @param keys ArrayList to hold the keys
	 * @param node Node to start placing keys into the list
	 */
	private void inOrder(ArrayList<Key> keys, Node node){
		if(node == null)
			return;
		inOrder(keys, node.left);
		keys.add(node.key);
		inOrder(keys, node.right);
	}
}