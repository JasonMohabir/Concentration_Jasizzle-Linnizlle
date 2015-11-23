import cs1.Keyboard;  //if comfortable with Scanner, you may comment this out


public class Concentration {
    
    //instance variables
    private Tile[][] _board;     //storage for 4x4 grid of Tiles.
    private int _numberFaceUp;   //count of Tiles with faces visible
    private String[] _words = {"cat","dog","hello","whoo","meep","kings","rights","lefts"}
    //list of unique Strings used for Tile vals
    private static int _numRows = 4;
    
    
    //insert constructor and methods here
    
    /* 
    public static int[] populate(int[] a){
	for (int i = 0; i<a.length; i++){
	    String randWord =  _words[(int)(Math.random() * _words.length)];
	    if (freq(a,randWord) < 2){a[i] = randWord;}
	}
	return a;
    } 
    */

    /*
    public static int[] populate(int[] a){
	for (int i = 0; i < _words.length;i++){
	    if freq(a,_words[i]) < 2){

	}

    }

    */
    
    public static int freq( Object[] a, Object target ){
	int ctrFreq = 0;
	for (Object n: a){if (n.equals(target)) {ctrFreq +=0;}}
	return ctrFreq;
    }
      
    
    //DO NOT MODIFY main()
    public static void main(String[] args){
	Concentration game = new Concentration();
	game.play();
    }
    
}//end class Concentration
