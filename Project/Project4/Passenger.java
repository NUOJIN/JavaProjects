//Author: cheng758
import java.util.Random;
import java.util.LinkedList;
//Use Random to generate drop stop and direction of passengers
class Passenger{

	Passenger(int pickupStation){
		this.pickupStop = pickupStation;
		this.direction = directionGenerator(pickupStop);
		this.dropoffStop = dropoffStopGenerator(pickupStop);
		this.arriveTime = BusSim.agenda.getCurrentTime();
	}

	public int getPickupStop(){return pickupStop;}

	public int getDropoffStop(){return dropoffStop;}

	public boolean getDirection(){return direction;}

	//To get how much time he is waiting. If the passenger has been picked up yet, the result will be negative.
	public double getWaitingTime(){
		return pickupTime - arriveTime;
	}

	//Update the pickup time for the passenger
	public void getPickup(){pickupTime = BusSim.agenda.getCurrentTime();}

	//To generate the stop of the passenger
	//If the random number is same with pickStop, use recersion to generate again
	public int dropoffStopGenerator(int pickupStop){
		Random rand = new Random();
		int temp = rand.nextInt(13);
		if(temp==pickupStop)
			return dropoffStopGenerator(pickupStop);
		else if(temp==7||temp==8)
			return 7;
		else if(temp==9||temp==10)
			return 8;
		else if(temp==11||temp==12)
			return 9;
		else
			return temp;
	}

	public boolean directionGenerator(int pickupStop){
		Random rand = new Random();
		if(pickupStop==0)
			return true;
		else if(pickupStop==9)
			return false;
		else
			return (rand.nextInt(2)==1);
	}

	public void updateTime(){
		pickupTime = BusSim.agenda.getCurrentTime();
		double result = getWaitingTime();
		if (result>maxWait)
			maxWait = result;
		totalWait += result;
		count++;
	}

	//I use LinkedList to contain all the passengers' waiting time
	public static double maxWait;
	public static double totalWait;
	public static int count;
	private int pickupStop;
	private int dropoffStop;
	private double arriveTime;
	private double pickupTime;
	private boolean direction;
}