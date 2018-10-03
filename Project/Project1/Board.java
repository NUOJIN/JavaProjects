//Author: cheng758
public class Board {
    private int num_rows;
    private int num_columns;
    private int num_boats;
    private Battleboat[] boats;
    private Cell[][] board;
    private boolean debugMode;
    private int guess_time;
    private int unsunk_boats;
    
 
    // TODO: Assign appropriate number of boats to num_boats variable
    // TODO: Initialize the board as a 2-D Cell array
    // TODO: Initialize boats as a Battleboat array
    // TODO: Place Battleboats appropriately on board and add them to the board's boats

    public Board(int m , int n, boolean debugMode){
        guess_time = 0;
        num_rows = m;
        num_columns = n;
        num_boats = number_of_boats(m,n);
        unsunk_boats = num_boats;
        this.debugMode = debugMode;
        board = new Cell[num_rows][num_columns];
        boats = new Battleboat[num_boats];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++)
                board[j][i] = new Cell(j,i,' ');
        }
        
        for(int i=0; i<num_boats; i++){
            boats[i] = battleboat_generator();
        }
    }

    //Obscures a character if the game is not being played in debug mode
    private char debug(boolean debugMode, char c){
        if(debugMode){
            return c;
        }
        else{
            switch(c){
                case 'H':
                    c = 'H';
                    break;
                case 'M':
                    c = 'M';
                    break;
                default:
                    c = ' ';
                    break;
            }
            return c;
        }
    }

    //Prints a Board object in a way that makes sense to the player
    public String toString(){

        String boardString = "\t";
        for (int j = 0; j < num_columns-1; j++){
            boardString += j + " |" + "\t";
        }

        boardString += num_columns-1;

        for(int i = 0; i < num_rows; i++){
            boardString+= "\n" + i + "\t";
            for (int j = 0; j < num_columns; j++){
                boardString += debug(debugMode, board[i][j].get_status()) + "\t";
            }
        }

        boardString += "\n";
        return boardString;
    }

    // TODO: Return a int based on the guess for the cell/its status
    // TODO: Change the statuses of the cell if applicable
    public int guess(int r, int c){
        guess_time++;
        if (r>=num_rows||c>=num_columns||r<0||c<0){
            return 0;
            //"Penalty: Out of Bounds";
        }
        else if (board[r][c].get_status()==' ') {
            board[r][c].set_status('M');
            return 1;
            //"Miss";
        }
        else if(board[r][c].get_status()=='B'){
            board[r][c].set_status('H');
            for(int i=0; i<num_boats; i++){
                if (boats[i].contain(r,c))
                    boats[i].hitted();
                }
            unsunk_boats--;
            return 2;
            //"Hit";
        }
        else {
            return 3;
            //"Penalty: Redundant Guess";
        }
    }

    //TODO: write a function that calculates the number of unsunk boats
    public int unsunkBoats(){
        return unsunk_boats;
    }
    
    //TODO: decide the numbor of boats
    public int number_of_boats(int m, int n){
        int min;
        if(m<n)
            min = m;
        else
            min = n;
        int result;
        if (min==3)
            result = 1;
        else if (min <= 5)
            result = 2;
        else if (min <= 7)
            result = 3;
        else if (min <= 9)
            result = 4;
        else if (min <= 12)
            result = 6;
        else
            result = 0;
        return result;
    }
    
    //TODO: generate the battleboat's orientation from the seed
    public boolean orientation_generator(int seed){
        if (seed%2==0)
            return false;
        else
            return true;
    }
    
    //TODO: generate single battleboat
    public Battleboat battleboat_generator(){
        boolean orientation = orientation_generator(get_random_int());
        int size = 3;
        Cell[] boat = new Cell[3];
        boolean all_cell_empty = false;
        int row;
        int col;
        Battleboat result;
        while(!all_cell_empty){
            if(orientation==true){ //The ship is vertical when orientation is true
                row = get_random_int()%(num_rows-size+1);//generate a random int between [0,num_rows-size]
                col = get_random_int()%(num_rows);
                boat[0] = board[row][col];
                boat[1] = board[row+1][col];
                boat[2] = board[row+2][col];
            }
            else{//The ship is horizontal when orientation is false
                row = get_random_int()%(num_rows);
                col = get_random_int()%(num_columns+1-size);
                boat[0] = board[row][col];
                boat[1] = board[row][col+1];
                boat[2] = board[row][col+2];
            }
            all_cell_empty = (boat[0].get_status()==' '&&boat[1].get_status()==' '&&boat[2].get_status()==' ');
        }
        for(int i=0; i<3; i++){
            boat[i].set_status('B');
        }
        Battleboat a = new Battleboat(3,orientation,boat);
        return a;
    }
    
    //TODO: get a random int
    public int get_random_int(){
        return (int)(Math.random()*1000)+1;
    }
    
    //TODO: get the time of guess
    public int get_guess_time(){
        return guess_time;
    }
    
    public static void main(String[] args){
        Board a = new Board(10,10,true);
        System.out.println(a.toString());
        a.guess(4,4);
        System.out.println(a.toString());
        System.out.println(a.get_guess_time());
        System.out.println(a.unsunkBoats());
    }
}
