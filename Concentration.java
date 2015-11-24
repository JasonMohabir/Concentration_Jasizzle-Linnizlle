import cs1.Keyboard;  //if comfortable with Scanner, you may comment this out


public class Concentration {
    
    //instance variables
    private Tile[][] _board = new Tile[4][4];     //storage for 4x4 grid of Tiles.
    private int _numberFaceUp;   //count of Tiles with faces visible
    private String[] _words = {"cat","dog","hello","whoo","meep","kings","rights","lefts"};
    //list of unique Strings used for Tile vals
    private static int _numRows = 4;
    
    
    //insert constructor and methods here

    public Concentration() {
	populate();
	scramble();
    }
    
    public String toString() {
	String retStr = "";
	
	for (Tile[] i : _board) {
	    retStr += "\n";
	    for (Tile j : i) {
		retStr += j + "\t";
	    }
	}
	
	retStr += "\n";

	return retStr;
    }


    public void populate() {
	int wordCtr = 0;
	for (int i = 0; i < _board.length; i++) {
	    for (int j = 0; j < _board[i].length; j++) {
		_board[i][j] = new Tile(_words[wordCtr % 8]);
		wordCtr += 1;
	    }
	}
    }

    public void swap( int i, int j, int x, int y ) { //Yay for old me
	Tile tempIJ = _board[i][j];
	Tile tempXY = _board[x][y];

	_board[i][j] = tempXY;
	_board[x][y] = tempIJ;
    }
    
    public void scramble() {

	for (int i = 0; i< _board.length; i++) {
	    for (int j = 0; j < _board[i].length; j++) {
		swap(i, j, (int)((Math.random()) * (_board[i].length)), (int)((Math.random()) * (_board[i].length)));
	    }
	}
	
    }

    public int checkFlipped() {
	int ctr = 0;
	for (int i = 0; i < _board.length; i++) {
	    for (int j = 0; j < _board[i].length; j++) {
		if (_board[i][j].isFaceUp()) {
		    ctr += 1;
		}
	    }
	}
	return ctr;
    }
    
    public void flipGame(Tile flipTile, Concentration display) {
	if (flipTile.isFaceUp()) {
	    System.out.println("The Tile is Already Up. You Wasted a Turn. ");
	    flipTile.flip();
	}
	else{
	    flipTile.flip();
	}
	System.out.println("\n Board Currently: \n************************\n" + display + "\n************************\n");
    }

    public void match(Tile first, Tile second) {
	
	if (second.isFaceUp()) { //This case is for when people put the same coord twice
	    first.flip();
	    second.flip();
	    System.out.println("Nope!\n.\n.\n.");
	}
	
	else if (first.equals(second)) {
	    System.out.println("Success!");
	}
	
	else {
	    first.flip();
	    System.out.println("Nope!\n.\n.\n.");
	}
    }
	
	
    public void play() {

	int flipCtr = 0;
	
	System.out.println("Welcome to Concentration!");
	System.out.println("Here is your board");
	System.out.println(this);

	while (checkFlipped() < 16) {

	    int row1 = 0;
	    int row2 = 0;
	    int col1 = 0;
	    int col2 = 0;

	    System.out.println("First Flip!");
	    
	    System.out.println("(Rows and Columns begin with 0):");

	    System.out.print("Row: ");
	    row1 = Integer.parseInt(Keyboard.readString());

	    System.out.print("Column: ");
	    col1 = Integer.parseInt(Keyboard.readString());
	    
	    Tile b1 = _board[row1][col1];
	    flipGame(b1, this);

	    System.out.println("Second Flip!");

	    System.out.println("(Rows and Columns begin with 0):");

	    System.out.print("Row: ");
	    row2 = Integer.parseInt(Keyboard.readString());
	    System.out.println();

	    System.out.print("Column: ");
	    col2 = Integer.parseInt(Keyboard.readString());
	    System.out.println();

	    Tile b2 = _board[row2][col2];
	    flipGame(b2, this);

	    match(b1, b2);
	    System.out.println(this);
	    
	    flipCtr += 2;

	}

	System.out.println("You've Completed the Matching!");
	
    }
	    
    
      
    
    //DO NOT MODIFY main()
    public static void main(String[] args){
	Concentration game = new Concentration();
	game.play();
    }
    
}//end class Concentration
