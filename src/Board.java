

import java.util.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Board {

	public ArrayList<Pit> pits = new ArrayList<Pit>(); // list of pits on board
	public ArrayList<Pit> state;
	public String turnState;
	public String turn; // determines who's turn it currently is
	private String playerAsName;
	private String playerBsName;
	private int numOfStones;
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
	
	/**
	 * Ctor for the board, will also create a save state
	 * A1-A6, B1-B6 are interactive pits
	 * AP, BP are the main pits that belong to the player and keeps their score
	 * @param stones is the initial amount of stones
	 */
	public Board(int stones) {
		this.numOfStones = stones;
		
		// adds pits into list
		pits.add(new Pit("A1", stones));
		pits.add(new Pit("A2", stones));
		pits.add(new Pit("A3", stones));
		pits.add(new Pit("A4", stones));
		pits.add(new Pit("A5", stones));
		pits.add(new Pit("A6", stones));
		pits.add(new Pit("AP", 0));
		pits.add(new Pit("B1", stones));
		pits.add(new Pit("B2", stones));
		pits.add(new Pit("B3", stones));
		pits.add(new Pit("B4", stones));
		pits.add(new Pit("B5", stones));
		pits.add(new Pit("B6", stones));
		pits.add(new Pit("BP", 0));
		// sets adjacent pits
		setAdj(pits);
		
		// Current turn
		this.turn = "A";
		
		saveState();
	}
	
	/**
	 * Creates a saved state for the board
	 */
	public void saveState() {
		state = new ArrayList<Pit>();
		for(Pit p: pits)
			state.add(new Pit(p.getID(), p.getStones()));
		setAdj(state);		
		turnState = turn;
	}
	
	/**
	 * returns the Board to a saved state
	 */
	public void undo() {
		pits = state;
		turn = turnState;
		
		//notify listener
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener l:listeners)
			l.stateChanged(e);
	}
	
	/**
	 * sets the adjacent pits to one another
	 * @param list either the actual board or the save state
	 */
	public void setAdj(ArrayList<Pit> list) {
		// sets the adjacent pits
		// A1 - B6
		list.get(0).setAdjacent(list.get(12));
		list.get(12).setAdjacent(list.get(0));
		// A2 - B5
		list.get(1).setAdjacent(list.get(11));
		list.get(11).setAdjacent(list.get(1));
		// A3 - B4
		list.get(2).setAdjacent(list.get(10));
		list.get(10).setAdjacent(list.get(2));
		// A4 - B3
		list.get(3).setAdjacent(list.get(9));
		list.get(9).setAdjacent(list.get(3));
		// A5 - B2
		list.get(4).setAdjacent(list.get(8));
		list.get(8).setAdjacent(list.get(4));
		// A6 - B1
		list.get(5).setAdjacent(list.get(7));
		list.get(7).setAdjacent(list.get(5));
	}
	
	/**
	 * Check if the game is finished
	 * Step 1 to finding the winner
	 * @return true if one side has all empty pits, false if not the case
	 */
	public boolean checkEnd() {
		boolean status = true;
		for(int i = 0; i < 6; i++) 
			if(!pits.get(i).isEmpty()) 
				status = false;
		if(status)
			return status;
		status = true;
		for(int i = 7; i < 13; i++) 
			if(!pits.get(i).isEmpty()) 
				status = false;
		return status;
	}
	
	/**
	 * This is used after checkEnd() returns true, adds all the stones in the interactive pits of each player to their main pit and empties 
	 * out all of the interactive pits
	 * Step 2 to finding the winner
	 */
	public void calculateTotal() {
		int totalA = 0;
		for(int i = 0; i<= 5; i++) {
			totalA += pits.get(i).getStones();
			pits.get(i).empty();
		}
		pits.get(6).addAmount(totalA);
		int totalB = 0;
		for(int i = 12; i>= 7; i--) {
			totalB += pits.get(i).getStones();
			pits.get(i).empty();
		}
		pits.get(13).addAmount(totalB);
	}
	
	/**
	 * Compares the main pits to determine the winner
	 * final step in calculating the winner
	 * @return the winner
	 */
	public String getWinner(){
		if(pits.get(6).getStones() > pits.get(13).getStones())
			return "A";
		else if(pits.get(6).getStones() < pits.get(13).getStones())
			return "B";
		else
			return "Tie";
	}
	
	/**
	 * displays the board on the terminal
	 */
	public void display() {
		System.out.print("  ");
		for(int i = 12; i>= 7; i--)
			System.out.print(pits.get(i).getStones() + " ");
		System.out.println();
		System.out.println(pits.get(13).getStones() + "             " + pits.get(6).getStones());
		System.out.print("  ");
		for(int i = 0; i<= 5; i++)
			System.out.print(pits.get(i).getStones() + " ");
		System.out.println();
	}
	
	/**
	 * This is the main operation in the game. 
	 * Players will chose a pit on their side and that pit will be emptied and of of stones will be 
	 * added to the pits next to it.
	 * This method also applies all of the rules and cases that are available during the game, like for example:
	 * extra turns, stealing the stones in the opponent's pit
	 * @param ID The pit that the player selected
	 */
	public void select(String ID) {
		//checks if selected pit is on player's side
		if(ID.substring(0,1).equals(turn)) { 
			saveState(); // save the board's state before it changes
			Pit selected = new Pit("", 0);
			Pit next = selected;
			// finds selected pit
			for(Pit p: pits)
				if(p.getID().equals(ID))
					selected = p;
			boolean bonusTurn = true; // initialize bonus turn
			// picks up the stones in selected pit and spreads them to the others
			while(!selected.isEmpty()) {
				int indexNext = pits.indexOf(selected) + 1; // index of next pit
				while(!selected.isEmpty()) {
					indexNext = indexNext % 14; // keep the index in the bounds of the arraylist
					// skip the opposing player's main pit
					if(pits.get(indexNext).getID().substring(1).equals("P") && !pits.get(indexNext).getID().substring(0,1).equals(selected.getID().substring(0, 1)) )
						indexNext++;
					next = pits.get(indexNext % 14); // next pit
					next.add(); // adds one stone to next pit
					selected.sub(); // subtracts one stone from selected pit
					indexNext++; // go on to the next pit
				}
				//if the last stone ends up on an interactive pit
				if(!next.getID().equals(turn + "P")) {
					bonusTurn = false; // no bonus turn
					// if the last pit belongs to the current player and was an empty pit, steal all stones in the adjacent and the last pit and 
					// add the total to the current player's main pit
					if(next.getID().substring(0, 1).equals(turn) && next.getStones() == 1 && !next.getAdjacent().isEmpty())
						if(turn.equals("A"))
							pits.get(6).stealStones(next, next.getAdjacent());
						else if(turn.equals("B"))
							pits.get(13).stealStones(next, next.getAdjacent());
				}			
			}
			if(!bonusTurn)
				nextTurn(); // changes turn if there's no bonus turn
		}
		
		//notify listener
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener l:listeners)
			l.stateChanged(e);
	}
	
	// changes the turn, A = player 1, B = player 2
	public void nextTurn() {
		if (turn.equals("A"))
			turn = "B";
		else
			turn = "A";
	}
	
	//attach method for listeners
	public void attach(ChangeListener l) {listeners.add(l);}
	
	//getter for the turn
	public String getTurn() {return turn;}
	
	//getter for player A's name
	public String getPlayerAsName() {return this.playerAsName;}
	
	//getter for player B's name
	public String getPlayerBsName() {return this.playerBsName;}
	
	//setter for player A's name
	public void setPlayerAsName(String playerAsName) {this.playerAsName = playerAsName;}
	
	//setter for player B's name
	public void setPlayerBsName(String playerBsName) {this.playerBsName = playerBsName;}

	//getter for numOfStones
	public int getNumOfStones() {return numOfStones;}
}
