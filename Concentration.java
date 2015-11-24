import cs1.Keyboard;  //if comfortable with Scanner, you may comment this out


public class Concentration {
    
    //instance variables
    private Tile[][] _board;     //storage for 4x4 grid of Tiles.
    private int _numberFaceUp;   //count of Tiles with faces visible
    private String[] _words = {"cat","dog","hello","whoo","meep","kings","rights","lefts"};
    //list of unique Strings used for Tile vals
    private static int _numRows = 4;
    
    
    //insert constructor and methods here
    
    
    public void populate(){
	
	for (int i = 0; i<_words.length; i+=2){

	    Tile t1 = new Tile(_words[i]);
	    Tile t2 = new Tile(_words[i+1]);
	    
	    for (int j = 0; j < 4; j++) {
		_board[j][0] = t1;
		_board[j][1] = t1;
		_board[j][2] = t2;
		_board[j][3] = t2;
	    }
	}
	    
    }     
    
      
    
    //DO NOT MODIFY main()
    public static void main(String[] args){
	Concentration game = new Concentration();
	game.play();
    }
    
}//end class Concentration
