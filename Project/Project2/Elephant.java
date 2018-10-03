//author: cheng758
public class Elephant implements Comparable<Elephant>{
	String name;
	int age;
	double height;

	public Elephant(String initName, int initAge,double initHeight){
		this.name = initName;
		this.age = initAge;
		this.height = initHeight;
	}
	public void setName(String newName){
		this.name=newName;
	}
	public void setAge(int newAge){
		this.age=newAge;
	}
	public void setHeight(double newHeight){
		this.height=newHeight;
	}
	public String getName(){
		return this.name;
	}
	public int getAge(){
		return this.age;
	}
	public double getHeight(){
		return this.height;
	}
	public String toString(){
		return name + " "+ age + " " + height;
	}
	public int compareTo(Elephant other){
		return this.name.compareTo(other.name);
	}
}