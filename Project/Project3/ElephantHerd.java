//author: cheng758
class ElephantHerd{
	private List<Elephant> list;//create a list

	public ElephantHerd(){
		list = new LinkedList<>();
	}

	public boolean add(Elephant ellie){
		return list.add(ellie);
	}

	public Elephant find(String name){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName()==name)
				return list.get(i);
		}
		return null;

	}

	public Elephant remove(int index){
		return list.remove(index);
	}

	public void sort(){//use insertion sort to sort
		for(int i=1;i<list.size();i++){
			for(int j=i-1;j>=0;j--){
				if(list.get(j).getHeight()<list.get(j+1).getHeight()){
					Elephant temp;
					temp = list.set(j+1,list.get(j));
					temp = list.set(j,temp);
				}
			}
		}
	}

	public Elephant[] getTopKLargesElephants(int k){
		if(k<=list.size()){
			Elephant[] result = new Elephant[k];
			this.sort();
			for(int i=0;i<k;i++){
				result[i] = list.get(i);
			}
			return result;
		}
		else
			return null;
	}

	public void clear(){
		list.clear();
	}

	public String toString(){
		return list.toString();
	}

	public static void main(String[] args){
		ElephantHerd a = new ElephantHerd();
		Elephant ellie = new Elephant("Ellie",1,11.0);
		Elephant noki = new Elephant("Noki",2,10.0);
		Elephant john = new Elephant("John",3,12.0);
		a.add(ellie);
		a.add(noki);
		a.add(john);
		//a.remove(2);
		Elephant[] top;
		top = a.getTopKLargesElephants(2);
		for(int i=0;i<top.length;i++)
			System.out.println(top[i]);
	}
}