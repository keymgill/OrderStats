package edu.unca.csci333;

/*
 * CSCI 333
 * Homework 6
 * 3/17/2022
 * 
 *Represents a node of a binary search tree with an order statistic augmentation
 */
public class BSTNode {
	private int key;
	private BSTNode p;
	private BSTNode left;
	private BSTNode right;
	private int size;
	
	public BSTNode(int key) {
		super();
		this.key = key;
		this.size = 0;
		this.p = null;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * 
	 * @return size of node
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Recalculates size of node based on the size of its children
	 */
	public void updateSize() {
		int leftSize = 0;
		int rightSize = 0;
		if(this.left != null) {
			leftSize = this.left.getSize();
		}
		if(this.right != null) {
			rightSize = this.right.getSize();
		}
		this.size = leftSize + rightSize + 1;
	}
	
	/**
	 * 
	 * @return node's data key
	 */
	public int getKey() {
		return key;
	}
	/**
	 * Sets key data field to given value
	 * @param key
	 */
	public void setKey(int key) {
		this.key = key;
	}
	
	/**
	 * Returns the node's parent node
	 * @return node's parent node
	 */
	public BSTNode getP() {
		return p;
	}
	/**
	 * Set node's parent node to given node
	 * @param p
	 */
	public void setP(BSTNode p) {
		this.p = p;
	}
	
	/**
	 * Returns the node's left child node
	 * @return node's left child
	 */
	public BSTNode getLeft() {
		return left;
	}
	
	/**
	 * Sets node's left child to given node
	 * @param left
	 */
	public void setLeft(BSTNode left) {
		this.left = left;
	}
	
	/**
	 * Returns node's right child node
	 * @return node's right child
	 */
	public BSTNode getRight() {
		return right;
	}
	public void setRight(BSTNode right) {
		this.right = right;
	}
	
}
