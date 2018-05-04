

public class Pit {

	private String ID; // Identifies the pit, i.e A1, B5, AP, etc...
	private int stones; // Amount of stones in the pit
	private Pit adjacent; // Pit that is adjacent to current pit
	
	/**
	 * Ctor for pit
	 * @param ID is the ID
	 * @param stones is the initial amount of stones
	 */
	public Pit(String ID, int stones) {
		this.ID = ID;
		this.stones = stones;
	}
	
	// getter for ID
	public String getID() {return ID;}
	// getter for stones
	public int getStones() {return stones;}
	// adds a specific amount of stones
	public void addAmount(int amount) {stones += amount;}
	// returns true if pit is empty, false if pit is not empty
	public boolean isEmpty() {return stones == 0;}
	// emptys out the pit (sets stone to 0)
	public void empty() { stones = 0; }
	/**
	 * takes the stones in both params and adds to own while setting the stones in both param to 0
	 * This is used for the case where the last stone lands on an empty space
	 * @param pit 1st pit
	 * @param adjPit pit adjacent to 1st pit
	 */
	public void stealStones(Pit pit, Pit adjPit) {
		stones += pit.getStones();
		pit.empty();
		stones += adjPit.getStones();
		adjPit.empty();
	}
	// adds 1 stone
 	public void add() {stones++;}
 	// removes 1 stone
	public void sub() {stones--;}
	// sets the adjacent pit
	public void setAdjacent(Pit pit) {this.adjacent = pit;}
	// getter for the adjacent pit
	public Pit getAdjacent() {return adjacent;}
}
