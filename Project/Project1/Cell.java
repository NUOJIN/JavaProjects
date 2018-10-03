//Author: cheng758
public class Cell {
    private int row;
    private int col;
    private char status; // ' ': Empty, 'B': Boat, 'H': Hit; 'M': Miss

    // TODO: To get the status of the cell
    public char get_status(){
        return status;
    }

    // TODO: To change the status of the cell to c
    public void set_status(char c){
        status = c;
    }

    // TODO: To initial the position and status of the cell by the constructor
    public Cell(int row, int col, char status){
        this.row = row;
        this.col = col;
        this.status = status;
    }
    
    //TODO: get row
    public int get_row(){
        return row;
    }
    
    //TODO: get col
    public int get_col(){
        return col;
    }

}
