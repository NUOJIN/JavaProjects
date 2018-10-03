//cheng758
//luo00034
import java.awt.*;
import java.util.*;

public class Node {

	private Node left; // null if leaf
	private Node right; // null if leaf
	private Shape shape; // non-null if leaf
	private Bounds bounds; // always set in constructor

	// Getters and setters
	// Use for unit tests!
	public Node getLeft(){ return left; }
	public Node getRight(){ return right; }
	public Shape getShape(){ return shape; }
	public Bounds getBounds(){ return bounds; }
	public void setLeft( Node l ){ left = l; }
	public void setRight( Node r ){ right = r; }
	public void setShape( Shape s ){ shape = s; }
	public void setBounds( Bounds b ){ bounds = b; }

	// TODO
	// 2) The constructor takes a stack of shapes and an (initial) splitting plane axis.
	// If there is one shape, then it stores the reference and becomes a leaf node.
	// If there are two or more shapes, it will partition the stack into seperate stacks,
	// create children, and pass the stacks to the children.
	// This is what we call top-down tree construction.
	public Node(Stack<Shape> stack, int axis){

		// Set our objects to null, so we know not to use
		// them if they are never set.
		left = null;
		right = null;
		shape = null;
		bounds = new Bounds();

		// We should never have an empty stack!
		if( stack.size() == 0 ){
		        throw new RuntimeException("**Node Error: Empty stack!");
		}

		// 2a) If our stack only has one shape, we are a leaf node!

		if(stack.size() == 1){
			Shape s = stack.pop();
			setShape(s);
			setBounds(s.getBounds());
		}
		else{
			// 2b) Extend our bounding box to contain everything in the stack

			Stack<Shape> temp = new Stack<>();
			while(stack.size()!=0){
				Shape s = stack.pop();
				bounds.extend(s.getBounds());
				temp.push(s);
			}
			//stack = temp;

			// 3) Now, split the stack!

			Stack<Shape> left = new Stack<>();
			Stack<Shape> right = new Stack<>();
			splitStack(temp,axis,left,right);
			setLeft(new Node(left,axis));
			setRight(new Node(right,axis));
		}
		// Do a recursive construction by making new Node (children).
		//
		// Here, we switch the axis between 1 and 2, which is called a
		// "round robin splitting plane". So if our current node was split on the
		// x axis, the children will be split along the y axis. There are significantly
		// better ways to determining where (and how) to split, and these are the
		// kinds of problems we deal with in Computer Graphics research.

	} // end constructor

	// TODO
	// 3) To decide which shape goes on which stack, we'll compute
	// the center of all objects currently in the stack. Objects that are less than the median
	// go on the left stack, greater than or equal to on the right.
	public void splitStack(Stack<Shape> stack, int axis, Stack<Shape> leftStack, Stack<Shape> rightStack){

		// We should never call split stack with an empty stack.
		if( stack.size() == 0 ){
		        throw new RuntimeException("**Node Error: Empty stack!");
		}

		// 3a) First, compute the centroid. This is the average of all vertices.
		// We'll use an iterator so we don't change the stack (yet).
		Stack<Shape> temp = new Stack<>();
		double x0 = 0;
		double y0 = 0;
		while(stack.size()!=0){
			Shape s = stack.pop();
			x0 += s.getCenter().x;
			y0 += s.getCenter().y;
			temp.push(s);
		}
		x0 = x0/temp.size();
		y0 = y0/temp.size();
		// 3b) Now that we know the center, we can partition the stack
		// into two seperate ones!
		while(temp.size()!=0){
			Shape s = temp.pop();
			if(axis==0)
				if(s.getCenter().x<=x0)
					leftStack.push(s);
				else
					rightStack.push(s);
			else
				if(s.getCenter().y<=y0)
					leftStack.push(s);
				else
					rightStack.push(s);
		}
		// Make sure both stacks have at least one element.
		// There are two ways this error would trigger:
		// -You made a mistake in your stack splitting
		// -Two elements have the same center along a specific axis (possible, but unlikely).
		if( leftStack.empty() || rightStack.empty() ){
			throw new RuntimeException("**splitStack Error: Empty child stack after split!");
		}

	} // end split stack

	// TODO
	// 4) Traverse the tree and find the selected shape.
	// If we're a leaf, test against the shape.
	// If we're a node, test children.
	// Only one shape should be selected at a time.
	public boolean select(double x, double y, int[] counter){
		counter[0]++; // Don't remove this

		// 4a) If we're outside the bounds of the node, we
		// don't need to check children!
		if(bounds.isOutside(x,y))
			return false;

		// 4b) If we are a leaf, check the shape
		if(left==null&&right==null)
			if(shape.select(x,y))
				return true;
			else
				return false;

		// If we aren't a leaf, we should have both
		// a left and right child.
		else
			return left.select(x,y,counter)||right.select(x,y,counter);
		// 4c) Otherwise, traverse children!
		// Since we assume no overlapping shapes, return true
		// if one was found.
	} // end select

	// Returns true if it finds a closer shape, in which is sets shapeRef and currentMin.
	// currentMin is ONLY updated when a closer shape is found.
	public boolean nearest(double x, double y, double[] currentMin, Shape[] shapeRef, int[] counter){
		counter[0]++; // Don't remove this

		// 5a) Check exterior distance between point and AABB.
		// If it's larger than the current min, return false
		// (since we know we're farther away than the current min).

		// 5b) If we are a leaf, check exterior distance
		// between the point and shape. If that exterior distance
		// is less than the current min, update the shapeRef
		// and currentMin, then return true.

		// If we aren't a leaf, we should have both
		// a left and right child.
		if( right == null || left == null ){
			throw new NullPointerException();
		}

		// 5c) Otherwise, traverse children!
		// As in select, we'll try to minimize tree traversal.
		// See which node is closer and traverse that branch first.

		return false;

	} // end nearest

	// Draw the boundaries of the node and children
	public void paint(Graphics2D g){

		// Our bounds should visibly enclose everything below it on the tree.
		bounds.paint(g);

		// If we're a leaf node, draw the shape contained by this node:
		if( shape != null ){
			return;
		}

		// If we aren't a leaf, we should have both
		// a left and right child.
		if( right == null || left == null ){
			throw new NullPointerException();
		}

		left.paint(g);
		right.paint(g);

	} // end paint


} // end class Node
