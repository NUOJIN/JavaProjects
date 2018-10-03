//Author: cheng758
public class LinkedList<T extends Comparable<T>> implements List<T>{
	private boolean isSorted;
	private Node<T> start;
	private int num;

	public LinkedList(){
		start = null;
		isSorted = true;
		num = 0;
	}

	public boolean add(T element){
		if(element==null)
			return false;
		else{
			start = new Node<>(element,start);
			num++;
			isSorted = false;
			Node<T> temp = start;
			while(temp.getNext()!=null)
				temp = temp.getNext();
			return true;
		}
	}

	//The index in this method can not be 0
	//If want to add at 0, it should use add(T element) method
	public boolean add(int index,T element){	
		if(index<=0||index>num)
			return false;
		else{
			Node<T> trailer = start;
			Node<T> ptr = start.getNext();
			for(int i=0;i<index-1;i++){
				trailer = ptr;
				ptr = ptr.getNext();
			}
			trailer.setNext(new Node<>(element,ptr));
			num++;
			isSorted = false;
			Node<T> temp = start;
			while(temp.getNext()!=null)
				temp = temp.getNext();
			return true;
		}
	}

	public void clear(){
		num = 0;
		start = null;
		isSorted = true;
	}

	public boolean contains(T element){
		Node<T> temp = start;
		while(temp!=null){
			if(temp.getData()==element)
				return true;
			temp = temp.getNext();
		}
		return false;
	}

	public T get(int index){
		Node<T> temp = start;
		for(int i=0;i<index;i++)
			temp = temp.getNext();
		return temp.getData();
	}

	public int indexOf(T element){
		Node<T> temp = start;
		for(int i=0;i<num;i++){
			if(temp.getData()==element)
				return i;
			temp = temp.getNext();
		}
		return -1;
	}

	public boolean isEmpty(){
		return (num==0);
	}

	public int lastIndexOf(T element){
		Node<T> temp = start;
		int result = -1;
		for(int i=0;i<num;i++){
			if(temp.getData()==element)
				result = i;
			temp = temp.getNext();
		}
		return result;
	}

	public T set(int index,T element){
		if(index<0||index>=num)
			return null;
		else{
			Node<T> ptr = start;
			for(int i=0;i<index;i++)
				ptr = ptr.getNext();
			T temp = ptr.getData();
			ptr.setData(element);
			return temp;
		}
	}

	public int size(){
		return num;
	}

	public void sort(boolean order){
		if(order){
			for(int i=0;i<num;i++)
				for(int j=1;j<num-i;j++){
					if(get(j-1).compareTo(get(j))>0)
						set(j,set(j-1,get(j)));
				}
			isSorted = true;
		}
		else{
			for(int i=0;i<num;i++)
				for(int j=1;j<num-i;j++)
					if(get(j-1).compareTo(get(j))<0)
						set(j,set(j-1,get(j)));
		}
	}

	public boolean remove(T element){
		if(remove(indexOf(element))!=null)
			return true;
		else
			return false;
	}

	public T remove(int index){
		if (index<0||index>=num)
			return null;
		else if(index==0){
			T temp = start.getData();
			start = start.getNext();
			num--;
			return temp;
		}
		else{
			Node<T> traiter = start;
			Node<T> ptr = start.getNext();
			for(int i=0;i<index-1;i++){
				traiter = ptr;
				ptr = ptr.getNext();
			}
			traiter.setNext(ptr.getNext());
			num--;
			return ptr.getData();
		}
	}

	public String toString(){
		String result = "";
		Node<T> temp = start;
		while(temp!=null){
			result = result + temp.getData().toString() + "\n";
			temp = temp.getNext();
		}
		return result;
	}
}