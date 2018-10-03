#Bus Simulation 
###Author Information 
Author: <bold>cheng758</bold>  
Course: CSCI 1933 Section 11
###How to start with new machine 
The main simulation file is BusSim.java. If you want to run the whole program, start with BusSim.  
###Overview  
   The main structure of project4 is similar to Lab10. I use agenda(PQ) in the BusSim file to order the events. Lines is a 2-dimensional array of Passenger Queue. For example, lines[0][0] means the passenger line at the first stop with the direction to east. And lines[0][1] must be a null empty queue because no passenger would like to go west at the first stop.(Because the first stop is the western one) For lines[7], lines[8] and lines[9], they are downtown stops, which have 1.5 times of common passengers arrival frequency and 2 times of common passengers take off frequency.   

In Bus.java and Passenger.java, I use a boolean variable "direction" to represent the direction of bus or passenger wanna to go. True means go east while false means go west. What's more, in Bus and Passenger file, I use LinkedList to record the waitingtime of different passengers and how many passengers are in the bus.   

There are there static variables in BusSim file. The first one is LOAD. If LOAD is true, the arrival rate of passenger  will be 120 seconds per person, otherwise, the rate will be in normal distribution with randomness just like what Project4.pdf shows. The second one is NUMBER_OF_BUS. It represents the total number of buses. The third one is BUS_SIZE, which means how many guys one bus can take at most.  
###Bugs and Issues  
Up to now, I have not found any exceptions yet.
