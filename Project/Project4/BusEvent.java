class BusEvent implements IEvent{
	BusEvent(int stop,boolean direction){
		curStop = stop;
		bus = new Bus(direction);
	}

	BusEvent(int stop,Bus b){
		curStop = stop;
		bus = b;
	}

	public void run(){
		//System.out.println("Now the bus is at "+curStop+" Stop at time "+BusSim.agenda.getCurrentTime());
		IQ<Passenger> pasToGo = bus.removePassenger(curStop);
		if(pasToGo.length()>0){
			getoffTime = 0;
			getoffTime += 2*pasToGo.length();
			BusSim.agenda.add(new BusEvent(curStop,bus),getoffTime);
			return;
		}
		if((!bus.isFull()&&(BusSim.lines[curStop][0].length()>0)&&bus.getDirection())||((!bus.isFull())&&(BusSim.lines[curStop][1].length()>0)&&(!bus.getDirection()))){
			getonTime = 0;
			if(bus.getDirection()){
				while(!bus.isFull()&&(BusSim.lines[curStop][0].length()>0)){
					bus.addPassenger(BusSim.lines[curStop][0].remove());
					getonTime += 3;
				}
		}
			else{
				while(!bus.isFull()&&(BusSim.lines[curStop][1].length()>0)){
					bus.addPassenger(BusSim.lines[curStop][1].remove());
					getonTime += 3;
				}
			}
			BusSim.agenda.add(new BusEvent(curStop,bus),getonTime);
			return;
		}
		if(bus.getDirection()){
			if((getoffTime+getonTime)>15)
				BusSim.agenda.add(new BusEvent((curStop+1)%10,bus),180);
			else
				BusSim.agenda.add(new BusEvent((curStop+1)%10,bus),195-getonTime-getoffTime);
		}
		else{
			if((getoffTime+getonTime)>15)
				BusSim.agenda.add(new BusEvent(((curStop-1)%10+10)%10,bus),180);
			else
				BusSim.agenda.add(new BusEvent(((curStop-1)%10+10)%10,bus),195-getonTime-getoffTime);
		}
	}


	private static int getonTime = 0;
	private static int getoffTime = 0;
	private int curStop;
	private Bus bus;
}