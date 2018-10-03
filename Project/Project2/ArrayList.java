//author: cheng758
class ArrayList<T extends Comparable<T>> implements List<T>{
	private T[] a;
	private boolean isSorted;
	private int arrayLen;//arrayLen means space the array takes
	private int numLen;//numLen means the size of the list

	@SuppressWarnings("unchecked")
	public ArrayList(){
		isSorted = true;
		arrayLen = 2;
		numLen = 0;//the size of the list is 0 at first 
		this.a = (T[]) new Comparable[2];
	}

	public boolean add(T element){
		if(element!=null){
			a[numLen] = element;
			numLen++;
			if(numLen==arrayLen)//if the array is full, double the space of it
				double_array_len();//I use a function to double it
			isSorted = false;
			return true;
		}
		else
			return false;
	}

	public boolean add(int index, T element){
		if(index<0||index>=numLen||element==null)
		//if the index if out of bound, it will return false
			return false;
		else{
			for(int i=numLen;i>index;i--)
				a[i] = a[i-1];
			a[index] = element;//cover the value of a[index] by element
			numLen++;
			if(numLen==arrayLen)
				double_array_len();
			isSorted = false;
			return true;
		}
	}

	public void clear(){
		for(int i=numLen-1;i>=0;i--)//use remove one by one
			remove(i);
	}

	public boolean contains(T element){
			for(int i=0;i<numLen;i++){
				if(a[i]==element)
					return true;//return it directly
			}
			return false;
	}

	public T get(int index){
		return a[index];
	}

	public int indexOf(T element){//use while loop to find the element
		boolean flag = false;
		int index = -1;
		while(!flag){
			index++;
			if(a[index]==element)
				flag = true;
			if(index>=numLen)
				break;
		}
		if(flag)
			return index;
		else
			return -1;

	}

	public boolean isEmpty(){//to check whether numLen is zero or not
		if(numLen==0)
			return true;
		else
			return false;
	}

	public int lastIndexOf(T element){//use for loop from end to start
		boolean flag = false;
		int index = -1;
		for(int i=0;i<numLen;i++){
			if(a[i]==element){
				index = i;
				flag = true;
			}
		}
		if(flag)
			return index;
		else
			return -1;
	}

	public T set(int index,T element){
		T value;
		if(index>=numLen||index<0)
			return null;
		else{
			value = a[index];
			a[index] = element;
			return value;
		}
	}

	public int size(){//to return the numLen
		return numLen;
	}

	public void sort(boolean order){//use insertion sort to sort
		if(!isSorted&&order){
			for(int i=1;i<numLen;i++)
				for(int j=i-1;j>=0;j--){
					if(a[j].compareTo(a[j+1])>0){
						T temp = a[j];
						a[j] = a[j+1];
						a[j+1] = temp;
					}
				}
		}
		else if(!order){
			for(int i=1;i<numLen;i++)
				for(int j=i-1;j>=0;j--){
					if(a[j].compareTo(a[j+1])<0){
						T temp = a[j];
						a[j] = a[j+1];
						a[j+1] = temp;
					}
				}
		}
	}

	///*public int compareTo(T o){
	//	return toSring().compareTo(o.toSring());
	//}*/

	public boolean remove(T element){//use for loop to remove
		int index = indexOf(element);
		System.out.println(index);
		if(index==-1)
			return false;
		else{
			for(int i=index;i<numLen;i++)
				a[i] = a[i+1];
			a[--numLen] = null;
			if(numLen==arrayLen/2&&numLen!=1)
				half_array_len();
			return true;
		}

	}

	public T remove(int index){
		T result;
		if(index<0||index>=numLen)
			return null;
		else{
			result = a[index];
			for(int i=index;i<numLen;i++)
				a[i] = a[i+1];
			a[--numLen] = null;
			if(numLen<arrayLen/2&&numLen!=1)
				half_array_len();
			return result;
		}
	}

	public String toString(){
		String result = "";
		for(int i=0;i<numLen;i++)
			result = result + a[i].toString() + "\n";
		return result;
	}


	@SuppressWarnings("unchecked")
	public void double_array_len(){//to double the size of array
		T[] b = (T[]) new Comparable[arrayLen*2];
		for(int i=0;i<numLen;i++)
			b[i] = a[i];
		arrayLen = arrayLen * 2;
		a = b;
	}

	@SuppressWarnings("unchecked")
	public void half_array_len(){//to cut the size of array to half
		T[] b = (T[]) new Comparable[arrayLen/2];
		for(int i=0;i<numLen;i++)
			b[i] = a[i];
		arrayLen = arrayLen / 2;
		a = b;
	}

	public static void main(String[] args){
		List<Elephant> b = new ArrayList<>();
		b.add(new Elephant("Andrew", 21, 10));
		b.add(new Elephant("John", 20, 9));
		b.add(new Elephant("Zuy", 20, 9));
		b.sort(false);
		System.out.println(b.toString());
	}
}
