import java.util.Random;
import java.util.LinkedList;
class PassengerEvent implements IEvent{
	public PassengerEvent(int stop){
		this.stop = stop;
	}

	public int getStop(){return stop;}

	public double returnDouble(){
		//double[] interval = new double[20];
		double[] interval = {0.75,0.75,0.5,0.5,0.5,0.2,0.2,0.2,0.2,0,-0.75,-0.75,-0.5,-0.5,-0.5,-0.2,-0.2,-0.2,-0.2,0};
		Random rand = new Random();
		int index = rand.nextInt(20);
		return interval[index];
	}

	public void run(){
		Passenger a = new Passenger(stop);
		if(a.getDirection())
			BusSim.lines[stop][0].add(a);
		else
			BusSim.lines[stop][1].add(a);
		if(BusSim.LOAD){
			double temp = 120;
			arrivalTimes.add(temp);
			BusSim.agenda.add(new PassengerEvent(stop),temp);
		}
		else{
			double temp = 120+returnDouble()*120;
			arrivalTimes.add(temp);
			BusSim.agenda.add(new PassengerEvent(stop),temp);
		}
		//System.out.println("Passenger Event Stop: "+stop+", Destination is "+a.getDropoffStop()+", The direction is "+a.getDirection()+", Current Time is: "+BusSim.agenda.getCurrentTime());
	}

	private int stop;
	public static LinkedList<Double> arrivalTimes = new LinkedList();
}