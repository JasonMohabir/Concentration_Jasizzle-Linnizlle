//Team Jasizzle Linizzle - Richard Lin, Jason Mohabir
//APCS1 pd10
//hw36 -- Some Folks Call It a Memory
//2015-11-24


//This hw is updated from the version on 11-23-2015
import cs1.Keyboard;  //if comfortable with Scanner, you may comment this out

public class Concentration {
    
    //instance variables
    private Tile[][] _board = new Tile[4][4];     //storage for 4x4 grid of Tiles.
    private int _numberFaceUp;   //count of Tiles with faces visible
    private String[] _words = {"cat","dog","hello","whoo","meep","kings","rights","lefts"};     //list of unique Strings used for Tile vals
    private static int _numRows = 4;   //I didn't use this instance variable
    
    
    //insert constructor and methods here

    public Concentration() {
	populate(); //Calls populate method
	scramble(); //Calls scramble method
    }

    //This is the toString to show the board
    public String toString() {
	String retStr = "";

	//The code here is just a basic double foreach loop that prints the 2d array
	for (Tile[] i : _board) {
	    retStr += "\n";
	    for (Tile j : i) {
		retStr += j + "\t";
	    }
	}
	
	retStr += "\n";

	return retStr;
    }

    
    //This populates the array
    public void populate() {

	//This keeps track of what word we're on for the list of words
	int wordCtr = 0;

	//A double for loop to cover all the rows and columns
	for (int i = 0; i < _board.length; i++) {
	    for (int j = 0; j < _board[i].length; j++) {
		_board[i][j] = new Tile(_words[wordCtr % 8]); //This sets the value. Its mod 8 since we duplicate the words.
		wordCtr += 1; //This increments the wordCtr up one
	    }
	}
    }

    //Yay for old (Slots.java) code
    public void swap( int i, int j, int x, int y ) { 

	//This is just modified for 2d arrays
	Tile tempIJ = _board[i][j];
	Tile tempXY = _board[x][y];

	_board[i][j] = tempXY;
	_board[x][y] = tempIJ;
    }

    //The scramble is basically the Slots.java code as well, just modified quite a bit to fit the 2d array
    public void scramble() {

	//Double for loop to go inside the 2d array
	for (int i = 0; i< _board.length; i++) {
	    for (int j = 0; j < _board[i].length; j++) {
		//The swap here is just like the Slots.java, except a bit modified to fit 2d arrays.
		swap(i, j, (int)((Math.random()) * (_board[i].length)), (int)((Math.random()) * (_board[i].length))); 
	    }
	}
	
    }

    //This function is to see constantly check the board for the game winning state
    public int checkFlipped() {

	//The ctr is reset to 0 each run
	int ctr = 0;

	//The double for loop goes through each value in the 2d array, and sees if it is face up.
	for (int i = 0; i < _board.length; i++) {
	    for (int j = 0; j < _board[i].length; j++) {
		if (_board[i][j].isFaceUp()) {
		    ctr += 1;
		}
	    }
	}
	
	return ctr; //This just returns the ctr. In another method, it checks if ctr = 16, in which the game state would be a win.
    }

    //This is a glorified flip method from the Tile class.
    
    public void flipGame(Tile flipTile, Concentration display) {

	//It checks if the Tile is already flipped up, and if it is, it just wastes a turn.
	if (flipTile.isFaceUp()) {
	    System.out.println("The Tile is Already Up. You Wasted a Turn. ");
	    //It flips the tile again because at the end of the turn, if no matches happen, all tiles get flipped over.
	    //By flipping this flipped up tile downward, when the Tiles get flipped over, it becomes face up again. 
	    flipTile.flip(); 
	}
	// Otherwise, it flips the Tile normally.
	else{
	    flipTile.flip();
	}

	//It also prints the board after the flip
	System.out.println("\n Board Currently: \n************************\n" + display + "\n************************\n");
    }

    //This checks for matches
    public void match(Tile first, Tile second) {

	 //This case is for when people put the same coord twice
	if (second.isFaceUp()) {
	    first.flip();
	    second.flip();
	    System.out.println("Nope!\n.\n.\n.");
	}

	//This case is simple equality
	else if (first.equals(second)) {
	    System.out.println("Success!");
	}

	//This case is simple inequality
	else {
	    first.flip();
	    System.out.println("Nope!\n.\n.\n.");
	}
    }
	

    //The big ole play method
    public void play() {

	//This is to keep track of the score
	int flipCtr = 0;

	//Introducing the game
	System.out.println("Welcome to Concentration!");
	System.out.println("Here is your board");
	System.out.println(this);

	//This runs the turns. It goes until all are flipped
	while (checkFlipped() < 16) {

	    //These are the rows and columns of the two tiles
	    int row1 = 0;
	    int row2 = 0;
	    int col1 = 0;
	    int col2 = 0;

	    //The following section I tried to modify to be modular, since there is a lot of repeated code, but if its in a separate method, it doesn't modify the row/col variables.
	    
	    System.out.println("First Flip!");
	    
	    System.out.println("(Rows and Columns begin with 0):");

	    //Basically, the following code is the row/col input block, and the flip
	    //There is a try and catch block for each input to prevent errors
	    System.out.print("Row: ");
	    try {
		row1 = Integer.parseInt(Keyboard.readString());
	    }
	    catch (Exception e) {}
	    
	    System.out.print("Column: ");
	    try {
		col1 = Integer.parseInt(Keyboard.readString());
	    }
	    catch (Exception e) {}
	    
	    
	    Tile b1 = _board[row1][col1];
	    flipGame(b1, this);

	    System.out.println("Second Flip!");

	    System.out.println("(Rows and Columns begin with 0):");

	    System.out.print("Row: ");
	    try {
		row2 = Integer.parseInt(Keyboard.readString());
	    }
	    catch (Exception e) {}

	    System.out.print("Column: ");
	    try {
		col2 = Integer.parseInt(Keyboard.readString());
	    }
	    catch (Exception e) {}

	    Tile b2 = _board[row2][col2];
	    flipGame(b2, this);

	    //This ends the input/flip section
	    
	    System.out.println();

	    //This part is checking for matching and then shows the board
	    match(b1, b2);
	    System.out.println(this);
	    
	    flipCtr += 2;

	}

	System.out.println("You've Completed the Matching!");
	System.out.println("Here's how many flips you did: " + flipCtr);
	
    }
	    
    //DO NOT MODIFY main()
    public static void main(String[] args){
	Concentration game = new Concentration();
	game.play();
    }
    
}//end class Concentration
