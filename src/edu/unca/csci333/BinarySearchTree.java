package edu.unca.csci333;

/*
 * CSCI 333
 * Homework 6
 * 3/17/2022
 * 
 *Binary search tree augmented to include order statistic queries
 */
public class BinarySearchTree {
	private BSTNode root;
	private int size;
	
	BinarySearchTree(){
		this.root = null;
		this.size = 0;
	}
	
	/**
	 * @return Size of tree (total number of nodes in tree)
	 */
	public int getSize() {
		return size;
	}

	private void transplant(BSTNode x, BSTNode y) {
		if(x.getP() == null) {
			this.root = y;
		}
		else if(x == x.getP().getLeft()) {
			x.getP().setLeft(y);
		}
		else {
			x.getP().setRight(y);
		}
		
		if(y != null) {
			y.setP(x.getP());
		}
	}
	
	/**
	 * Inserts a given node into tree in proper position and updates data fields accordingly
	 * @param z node to be inserted
	 */
	public void insert(BSTNode z) {
		insert_sub(z);
	}
	private void insert_sub(BSTNode z) {
		BSTNode y = null; // trailing pointer
		BSTNode x = this.root;
		
		while(x != null) {
			y = x;
			if(z.getKey() < x.getKey()) {
				x = x.getLeft();	// go left
			}
			else {
				x = x.getRight();
			}
		}
		z.setP(y);
		if(y == null) {
			this.root = z;
		}
		else if(z.getKey() < y.getKey()) {
			y.setLeft(z);
		}
		else {
			y.setRight(z);
		}
		size++;
		x = z;
		while (x != null) {
			x.updateSize();
			x = x.getP();
		}
	}
	
	/**
	 * Deletes a given node from tree and rearranges the tree accordingly
	 * @param z node to be deleted
	 */
	public void delete(BSTNode z) {
		delete_sub(z);
	}
	private void delete_sub(BSTNode z) {
		BSTNode y = null;
		BSTNode lowest = null;
		if(z == null) return;
		if(z.getLeft() == null) {
			lowest = z.getP();
			this.transplant(z, z.getRight());
		}
		else if(z.getRight() == null) {
			lowest = z.getP();
			this.transplant(z, z.getLeft());
		}
		else {
			y = min_helper(z.getRight());
			if(y.getP() != z) {
				lowest = y.getP();
				transplant(y,y.getRight());
				y.setRight(z.getRight());
				y.getRight().setP(y);
			}
			if(lowest == null) lowest =y;
			transplant(z,y);
			y.setLeft(z.getLeft());
			y.getLeft().setP(y);
		}
		this.size--;
		while (lowest != null) {
			lowest.updateSize();
			lowest = lowest.getP();
		}
	}
	
	/**
	 * @return minimum node in tree (node with lowest key value), null if tree is empty
	 */
	public BSTNode minimum() {
		return min_helper(this.root);
	}
	private BSTNode min_helper(BSTNode z) {
		BSTNode x = z;
		if (z != null) {
			if (z.getLeft() == null) {
				return z;
			} else {
				x = this.min_helper(z.getLeft());
			}
		}
		return x;
	}
	
	/**
	 * @return node with highest key value in tree, null if tree is empty
	 */
	public BSTNode maximum() {
		return max_helper(this.root);
	}
	private BSTNode max_helper(BSTNode z) {
		BSTNode x = z;
		if (z != null) {
			if (z.getRight() == null) {
				return z;
			} else {
				x = this.min_helper(z.getRight());
			}
		}
		return x;
	}
	
	/**
	 * Given a node it returns the node that is immediately after it in order
	 * @param x given node
	 * @return node immediately after given node; the same node if given node is the maximum
	 */
	public BSTNode successor(BSTNode x) {
		return successor_sub(x);
	}
	private BSTNode successor_sub(BSTNode x) {
		if(x.getRight() != null) {
			return this.min_helper(x.getRight());
		}
		while(x.getP() != null && x == x.getP().getRight()) {
			x = x.getP();
		}
		
		return x.getP();
	}
	
	/**
	 * Given a node it returns the node that is immediately before it in order
	 * @param x given node
	 * @return node immediately before given node; the same node if given node is the minimum
	 */
	public BSTNode predeccessor(BSTNode x) {
		return predeccessor_sub(x);
	}
	private BSTNode predeccessor_sub(BSTNode x) {
		if(x.getLeft() != null) {
			return this.max_helper(x.getLeft());
		}
		while(x.getP() != null && x == x.getP().getLeft()) {
			x = x.getP();
		}
		
		return x.getP();
	}
	
	/**
	 * Searches tree for a node with the given key if it exists in the tree
	 * @param k given key
	 * @return node that matches the given key if it exists in the tree, null if it does not exist
	 */
	public BSTNode search(int k) {
		return this.search_sub(this.root,k);
	}
	private BSTNode search_sub(BSTNode x, int k) {
		if(x == null) {
			return null;
		}
		if(k == x.getKey()) {
			return x;
		}
		if(k < x.getKey()) {
			return this.search_sub(x.getLeft(), k);
		}
		else {
			return this.search_sub(x.getRight(), k);
		}
	}
	
	/**
	 * Given a rank it returns the node with that rank in the tree
	 * @param i given rank
	 * @return node with matching rank; null if that node does not exist
	 */
	public BSTNode select(int i) {
		return this.select_sub(this.root,i);
	}
	private BSTNode select_sub(BSTNode x, int i) {
		int leftSize = 0;
		if(x == null) return null;
		if (x.getLeft() != null) {
			leftSize = x.getLeft().getSize();
		}
		int r =  leftSize + 1; // rank
		if(i == r) {
			return x;
		}
		else if (i < r ) {
			return select_sub(x.getLeft(),i);
		}
		else {
			return select_sub(x.getRight(),i-r);
		}
	}
	
	/**
	 * Returns the rank of a given node
	 * @param x given node
	 * @return rank of given node; 0 if node does not exist
	 */
	public int rank(BSTNode x) {
		return this.rank_sub(x);
	}
	private int rank_sub(BSTNode x) {
		int r = 0; 
		if(x == null) return 0;
		if(x.getLeft() != null) {
			r = x.getLeft().getSize() + 1;
		} else r = 1;
		BSTNode y = x;
		while(y != root) {
			int leftSize = 0;
			if(y == y.getP().getRight()) {
				if(y.getP().getLeft() != null) leftSize = y.getP().getLeft().getSize();
				r = r + leftSize +1;
			}
			y = y.getP();
		}
		return r;
	}
	
	/**
	 * Prints in-order traversal of list on console
	 */
	public void inOrderTraversal() {
		inOrderTraversal_sub(this.root);
	}
	private void inOrderTraversal_sub(BSTNode x) {
		if(x != null) {
			this.inOrderTraversal_sub(x.getLeft());
			System.out.print(x.getKey() + " ");
			this.inOrderTraversal_sub(x.getRight());
		}
	}
	
	/**
	 * Prints pre-order traversal of list on console
	 */
	public void preOrderTraversal() {
		preOrderTraversal_sub(this.root);
	}
	private void preOrderTraversal_sub(BSTNode x) {
		if(x != null) {
			System.out.print(x.getKey() + " ");
			this.preOrderTraversal_sub(x.getLeft());
			this.preOrderTraversal_sub(x.getRight());
		}
	}
	
	/**
	 * Prints post-order traversal of list on console
	 */
	public void postOrderTraversal() {
		postOrderTraversal_sub(this.root);
	}
	private void postOrderTraversal_sub(BSTNode x) {
		if(x != null) {
			this.postOrderTraversal_sub(x.getLeft());
			this.postOrderTraversal_sub(x.getRight());
			System.out.print(x.getKey() + " ");
		}
	}
}
