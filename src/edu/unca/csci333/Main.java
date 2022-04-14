package edu.unca.csci333;

import java.util.Random;

/*
 * CSCI 333
 * Homework 6
 * 3/17/2022
 * 
 *Tests Order Statistic Tree's functions
 */
public class Main {

	public static void main(String[] args) {
		BinarySearchTree a = new BinarySearchTree();
		Random rand = new Random();
		
		int[] in = {0,1,2,3,4,-10,-4,-7,9,7,5,6,8,-2,-5,-6,-8,-9,-3,-1}; // used to test insert and delete 
		
		// Test insert function
		for(int i = 0; i < in.length; i++) {
			BSTNode z = new BSTNode(in[i]);
			a.insert(z);
		}
		
		// Test tree traversals
		System.out.println("Pre-order Traversal : ");
		a.preOrderTraversal();
		
		System.out.println("\n\nIn-order Traversal : ");
		a.inOrderTraversal();
		
		System.out.println("\n\nPost-order Traversal : ");
		a.postOrderTraversal();
		
		// Check that tree size updated correctly
		System.out.println("\n\nTree Size: " + a.getSize());
		
		// Test search function with random numbers
		for(int i = 0; i < 10; i++) {
			int max = a.maximum().getKey();
			int min = a.minimum().getKey();
			int k = rand.nextInt((max+5)-min+1) + (min-5);
			System.out.print("\nSearching for " + k +": ");
			if(a.search(k) != null) {
				System.out.print((a.search(k).getKey()));
			}
			else System.out.print(a.search(k));
		}
		System.out.println();
		
		// Test delete function
		System.out.print("\nNodes Deleted: ");
		for(int i = 0; i < 8;i++) {
			BSTNode z = a.search(in[i]);
			a.delete(z);
			System.out.print(z.getKey() + " ");
		}
		System.out.println("\n\nTree Size: " + a.getSize());
		System.out.println("\nIn-order Traversal : ");
		a.inOrderTraversal();
		
		// Test select and rank functions
		int[] ranks = new int[5];
		System.out.println();
		for(int i = 8; i < 13;i++) {
			BSTNode z = a.search(in[i]);
			System.out.println("\nRank query on " +z.getKey() + ": Rank = " + a.rank(z));
			ranks[i-8] = a.rank(z);
		}
		
		for(int i = 0; i < ranks.length; i++) {
			System.out.println("\nSelect query: Node with rank "+ ranks[i] + " = " + a.select(ranks[i]).getKey());
		}
	}

}
