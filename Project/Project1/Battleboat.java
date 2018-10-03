//Author: cheng758
public class Battleboat {

    private int size;
    private boolean orientation; // false <-> horizontal, true <-> vertical
    private Cell[] spaces;

    // TODO: randomly set the orientation of the boat
    // TODO: set size of the boat (default to 3-cells long)
    // TODO: declare the Cell objects associated with each boat

    public Battleboat(int size, boolean orentation, Cell[] space){
        spaces = new Cell[3];
        this.size = size;
        this.orientation = orientation;
        for(int i=0; i<space.length; i++)
            this.spaces[i] = space[i];
    }

    // TODO: To get the orientation of the boat
    public boolean get_orientation(){
        return orientation;
    }

    // TODO: To get the size of the boat
    public int get_size(){
        return size;
    }

    // TODO: The get the Cell associated with the Battleboat
    public Cell[] get_spaces(){
        return spaces;
    }
    
    //To generate a random boolean with an integer
    public boolean get_random_orientation(int a){
        int seed = a%2;
        if (seed==0)
            return false;
        else
            return true;
    }
    
    //TODO: find whether the boat contains the cell or not
    public boolean contain(int r, int c){
        for(int i=0; i<size; i++){
            if(spaces[i].get_row()==r&&spaces[i].get_col()==c)
                return true;
        }
        return false;
    }
    
    //TODO: the boat is hitted
    public void hitted(){
        for(int i=0; i<size; i++){
            spaces[i].set_status('H');
        }
    }
}
