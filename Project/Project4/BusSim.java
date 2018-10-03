import java.util.LinkedList;
public class BusSim{
	//Use agenda to take events
	public static PQ agenda = new PQ();
	//Use queue array to contain passengers from different stops
	public static IQ<Passenger>[][] lines = new IQ[10][2];

	public static double[] calculate(LinkedList<Double> a){
		double result = 0;
		double max = 0;
		int size = a.size();
		for(int i=0;i<size;i++){
			double temp = a.remove();
			result += temp;
			if(temp>max)
				max = temp;
		}
		double[] r = new double[2];
		r[0] = result/size;
		r[1] = max;
		return r;
	}

	public static void main(String[] args){
		//Initialize the passenger lines and prodece the passengers at different stops
		for(int i=0;i<10;i++){
			lines[i][0] = new Q();
			lines[i][1] = new Q();
			agenda.add(new PassengerEvent(i),0);
			agenda.add(new PassengerEvent(i),0);
			if(i>6&&i<10)
				agenda.add(new PassengerEvent(i),0);
		}
		//Allocate the buses according to the number of NUM_OF_BUS
		//The order to allocate bus is from first stop to the last one, from true (east direction) to false
		for(int j=0;j<NUMBER_OF_BUS;j++){
			if(j==0)
				agenda.add(new BusEvent(0,true),0);
			else if(j==17)
				agenda.add(new BusEvent(9,false),0);
			else if(j<9)
				agenda.add(new BusEvent(j,true),0);
			else
				agenda.add(new BusEvent(j-8,false),0);
		}
		while(agenda.getCurrentTime() <= 10000)
			agenda.remove().run();
		waitingAverage.add(Passenger.totalWait/(double)Passenger.count);
		waitingMaximun.add(Passenger.maxWait);
		System.out.println("Successfully add "+Passenger.maxWait+" and "+Passenger.totalWait);
		//double[] t = calculate(PassengerEvent.arrivalTimes);
		arrivalAverage.add(calculate(PassengerEvent.arrivalTimes)[0]);
		PassengerEvent.arrivalTimes = new LinkedList<>();
		//double[] y = calculate(Bus.numOfPass);
		pasNumMax.add(calculate(Bus.numOfPass)[1]);
		Bus.numOfPass = new LinkedList<>();
		fullTime.add((double)Bus.busyTime);
		fullPercentage.add(((double)Bus.busyTime/(double)Bus.totalTime)*100);
		totalCost.add((double)Bus.passengerTimeStop);
		averageCost.add((double)Bus.passengerTimeStop/NUMBER_OF_BUS);
		}

	public static void main(String[] args){
		for(int i=0;i<100;i++){
			run();
			Passenger.totalWait = 1;
			//Passenger.count = 0;
			Passenger.maxWait = 1;
		}
		System.out.println("Average waiting time is "+calculate(waitingAverage)[0]);
		System.out.println("Maximum waiting time is "+calculate(waitingMaximun)[0]);
		System.out.println("Average arrival time is "+calculate(arrivalAverage)[0]);
		System.out.println("Maximum bus passengers number is "+calculate(pasNumMax)[0]);
		System.out.println("The buses are full for "+calculate(fullTime)[0]+" times.");
		System.out.println("The full percentage of bus is "+ calculate(fullPercentage)[0]+"% .");
		//We use SP (stop multi passengers) to replace MPG here
		System.out.println("The total Stop Multi Passengers of all the bus (the cost of this plan) is "+calculate(totalCost)[0]);
		System.out.println("The average Stop Multi Passengers of all the bus (the cost of this plan) is "+calculate(averageCost)[0]);

	}

	//Use Load to control inter-arrival rate. If LOAD is true, the inter-arrival rate will always be 120 seconds per person
	public static boolean LOAD = false;
	public static int NUMBER_OF_BUS = 1;
	public static int[] BUS_SIZE = {30,10};

	public static LinkedList<Double> waitingAverage = new LinkedList<>();
	public static LinkedList<Double> waitingMaximun = new LinkedList<>();
	public static LinkedList<Double> arrivalAverage = new LinkedList<>();
	public static LinkedList<Double> pasNumMax = new LinkedList<>();
	public static LinkedList<Double> fullTime = new LinkedList<>();
	public static LinkedList<Double> fullPercentage = new LinkedList<>();
	public static LinkedList<Double> totalCost = new LinkedList<>();
	public static LinkedList<Double> averageCost = new LinkedList<>();
}