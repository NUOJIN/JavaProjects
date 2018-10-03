//Author: cheng758
import java.util.LinkedList;
import java.util.Random;
public class Bus{
	public Bus(boolean direction){
		this.direction = direction;
		Random rand = new Random();
		int r = rand.nextInt(2);
		//Initialize the size of bus randomly
		size = BusSim.BUS_SIZE[r];
		pArray = new Passenger[size];
	}

	public boolean addPassenger(Passenger p){
		if(num<size&&direction==p.getDirection()){
			p.getPickup();
			pArray[num] = new Passenger(0);
			pArray[num++] = p;
			//System.out.println("Successfully add a passenger at "+BusSim.agenda.getCurrentTime());
			p.updateTime();
			numOfPass.add((double)num);
			totalTime++;
			if(num==size)
				busyTime++;
			return true;
		}
		else{
			//System.out.println("The bus is full.");
			return false;
		}
	}

	public IQ removePassenger(int stop){
		IQ<Passenger> result = new Q();
		for(int i=0;i<num;i++)
			if(pArray[i].getDropoffStop()==stop){
				result.add(pArray[i]);
				remove(i);
			}
			return result;
	}

	public void remove(int m){
		passengerTimeStop += num;
		for(int i=m;i<num-1;i++)
			pArray[i] = pArray[i+1];
		//System.out.println("Successfull drop a passenger.");
		num--;
	}

	public boolean isFull(){return (num==size);}

	public int getNum(){return num;}

	public boolean getDirection(){return direction;}

	public static LinkedList<Double> numOfPass = new LinkedList();
	//How many times the bus is fulled
	public static int busyTime = 0;
	//How many times the bus arrive the stop and start add passenger
	public static int totalTime = 0;
	//Represents the number of passenger times the number of stop all the buses pass
	public static int passengerTimeStop = 0;
	private int size;
	private Passenger[] pArray;
	private int num;
	private boolean direction;
}