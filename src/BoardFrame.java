
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * This class creates the setupFrame and calls the gameFrame
 * @author Tran, Harry; Mansahia, Shahbaz Singh; Saengsavang, Monty;
 *
 */
public class BoardFrame extends JFrame implements ChangeListener{

	public Board board;
	private JButton pitsButtonsA[] = new JButton[7];
	private JButton pitsButtonsB[] = new JButton[7];
	private JButton mancalaA;
	private JButton mancalaB;
	private BoardStrategy strategy;
	private JLabel turnLabel;
	
	/**
	 * Constructor to intialize the board, call setupFrame first
	 *
	 */
	public BoardFrame() {
		JFrame setupFrame = new JFrame();
		setupFrame(setupFrame);
	}

	
	/**
	 * Frame to ask player the setup options, (eg. # of stones)
	 * @param sFrame is the setup frame created specifically for displaying set up options
	 */
	public void setupFrame(JFrame sFrame) {
		// set size of the setup frame with a title
		sFrame.setSize(400, 285);
		sFrame.setTitle("Mancala Setup");
		sFrame.setLayout(new BorderLayout());

		// create title label and set font and size
		JLabel setupTitle = new JLabel("Mancala Setup Settings", JLabel.CENTER);
		setupTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));

		// create panel that will display all prompts for game info
		JPanel gameInfoPanel = new JPanel();
		gameInfoPanel.setLayout(new BorderLayout());

		// create prompt for how many stones to begin with, and radio buttons
		// and set action commands to identify which radio was chosen
		JLabel stonePrompt = new JLabel(
				"How many stones would you like to start with?");
		JRadioButton threeStone = new JRadioButton("3");
		threeStone.setActionCommand("3");
		threeStone.setSelected(true);
		JRadioButton fourStone = new JRadioButton("4");
		fourStone.setActionCommand("4");
		fourStone.setSelected(false);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(threeStone);
		buttonGroup.add(fourStone);

		// create a stone panel, add prompt and stone radio buttons to stone
		// panel
		JPanel stonePanel = new JPanel();
		stonePanel.setLayout(new FlowLayout());
		stonePanel.add(stonePrompt);
		stonePanel.add(threeStone);
		stonePanel.add(fourStone);

		// add the stone panel to the main gameInfoPanel
		gameInfoPanel.add(stonePanel, BorderLayout.NORTH);

		// create panel for holding name prompts and text boxes
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());

		// create prompt and textbox for getting name of player A
		JLabel firstPlayerPrompt = new JLabel("Enter the name of Player A");
		JTextField firstPlayerName = new JTextField("Player A", 20);

		// add first player prompt and textbox to the namePanel
		namePanel.add(firstPlayerPrompt);
		namePanel.add(firstPlayerName);

		// create prompt and text box for getting name of player b
		JLabel secondPlayerPrompt = new JLabel("Enter the name of Player B");
		JTextField secondPlayerName = new JTextField("Player B", 20);

		// add second player prompt and text box to the namePanel
		namePanel.add(secondPlayerPrompt);
		namePanel.add(secondPlayerName);

		// add namePanel to the main gameInfoPanel
		gameInfoPanel.add(namePanel, BorderLayout.CENTER);

		// create a panel to hold radio buttons for the different themes
		JPanel themePanel = new JPanel();
		themePanel.setLayout(new FlowLayout());

		// create label for prompt and radio buttons for each theme and set
		// action commands to identify which radio was chosen
		JLabel themePrompt = new JLabel("Select a theme: ");
		JRadioButton rainbowTheme = new JRadioButton("Rainbow theme");
		rainbowTheme.setActionCommand("Rainbow");
		rainbowTheme.setSelected(true);
		JRadioButton christmasTheme = new JRadioButton("Christmas theme");
		christmasTheme.setActionCommand("Christmas");
		christmasTheme.setSelected(false);

		ButtonGroup themeGroup = new ButtonGroup();
		themeGroup.add(rainbowTheme);
		themeGroup.add(christmasTheme);

		// add prompt and buttons to the themePanel
		themePanel.add(themePrompt);
		themePanel.add(rainbowTheme);
		themePanel.add(christmasTheme);

		// add the themePanel to the main gameInfoPanel
		gameInfoPanel.add(themePanel, BorderLayout.SOUTH);

		// create a panel to hold the start button and the quit button
		JPanel startPanel = new JPanel();

		// create a start button with action to get information, dispose of this
		// setup screen, and call the gameFrame
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get number of stones input from setup menu
				int stones = Integer.parseInt(buttonGroup.getSelection()
						.getActionCommand());

				board = new Board(stones);

				// get names of players from JTextFields and set them in the
				// model
				board.setPlayerAsName(firstPlayerName.getText());
				board.setPlayerBsName(secondPlayerName.getText());

				// get the theme input from setup menu
				String theme = themeGroup.getSelection().getActionCommand();

				sFrame.dispose();

				// if theme selected is rainbow, send rainbow theme
				if (theme.equals("Rainbow")) {
					// get rainbow image
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File("rainbow.jpg"));

					} catch (IOException e1) {
						e1.printStackTrace();
					}

					// create a theme strategy, given rainbow theme and image
					// associated
					strategy = new RainbowTheme(img);

					// call game frame method to create frame using this theme
					gameFrame();
				} else // else send Christmas theme
				{
					// gameFrame();
					// Get Christmas image
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File("christmas.jpg"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					// create a theme strategy, given christmas theme and image
					// associated
					strategy = new ChristmasTheme(img);

					// call game frame method to create frame using this theme
					gameFrame();
				}
			}
		});
		startButton.setBackground(Color.WHITE);

		// create a quit button with action to exit when pressed
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setBackground(Color.RED);

		// add these buttons to the startPanel
		startPanel.add(startButton);
		startPanel.add(quitButton);

		// add title to the NORTH, gameInfo to the CENTER, and startPanel to the
		// SOUTH
		sFrame.add(setupTitle, BorderLayout.NORTH);
		sFrame.add(gameInfoPanel, BorderLayout.CENTER);
		sFrame.add(startPanel, BorderLayout.SOUTH);

		sFrame.setVisible(true);
		sFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	/**
	 * Automatically called by setupFrame, gameFrame creates the mancala board
	 * and initializes all pits and prompts for the players
	 *
	 */
	public void gameFrame() {
		setSize(1150, 640);
		setTitle("Mancala");
		setLayout(new BorderLayout());
		
		//attach model
		board.attach(this);

		// load background image from files and set to this frame
		Image img = strategy.getImage();
		getContentPane().add(new BackgroundPanel(img));

		// create undoPanel to hold the undo button
		JPanel undoPanel = new JPanel();
		undoPanel.setLayout(new FlowLayout());

		// create undo button and set its preferred size and add it to the
		// undoPanel
		JButton undoButton = new JButton("Undo");
		undoButton.setPreferredSize(new Dimension(100, 30));
		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				//change the model, stateChanged will be called automatically
				board.undo();
				
				board.display();
				System.out.println();
			}
		});
		undoPanel.add(undoButton, BorderLayout.NORTH);

		// add undoPanel to NORTH side of the backgroundImg label
		add(undoPanel, BorderLayout.NORTH);

		// create panel to hold all pits, 12 in total
		JPanel pitsPanel = new BackgroundPanel(img);
		pitsPanel.setLayout(new GridLayout(0, 6, 5, 30));

		// Fills in top row of pits panel to shape pits into squares
		for (int i = 0; i < 6; i++) {
			JLabel label = new JLabel();
			pitsPanel.add(label);
		}
		
		//create first row of pits B6-B1
		for (int i = 6; i >= 1; i--) {
			pitsButtonsB[i] = new JButton();
			strategy.changeButtonColor(pitsButtonsB[i]);
			pitsButtonsB[i].setPreferredSize(new Dimension(100,100));
			strategy.addToPit(pitsButtonsB[i], board.getNumOfStones());
			
			final int index = i;
			pitsButtonsB[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//change the model, stateChanged will be called automatically
					board.select("B" + index);
					
					board.display();
					System.out.println();
		
				}
			});
			
			pitsPanel.add(pitsButtonsB[i]);
		}
		
		//create second row of pits A1-A6
		for (int i = 1; i <= 6; i++) {
			pitsButtonsA[i] = new JButton();
			strategy.changeButtonColor(pitsButtonsA[i]);
			pitsButtonsA[i].setPreferredSize(new Dimension(100,100));
			strategy.addToPit(pitsButtonsA[i], board.getNumOfStones());
			
			final int index = i;
			pitsButtonsA[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//change the model, stateChanged will be called automatically
					board.select("A" + index);
					
					board.display();
					System.out.println();
					
				}
			});
			
			pitsPanel.add(pitsButtonsA[i]);
		}
		
		//fills in bottom row of pits panel to shape pits into squares
		for (int i = 0; i < 6; i++) {
			JLabel label = new JLabel();
			pitsPanel.add(label);
		}
		
		// add pitsPanel to CENTER of background JLabel
		add(pitsPanel, BorderLayout.CENTER);

		// create east side mancala pit
		BackgroundPanel eastMancala = new BackgroundPanel(img);
		mancalaA = new JButton();
		mancalaA.setEnabled(false);
		mancalaA.setPreferredSize(new Dimension(120, 235));
		mancalaA.setBackground(Color.LIGHT_GRAY);

		JLabel eastName = new JLabel(" " + board.getPlayerAsName() + " ");
		eastName.setForeground(Color.WHITE);
		eastName.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		// put east mancala in correct position
		eastMancala.setLayout(new GridBagLayout());
		eastMancala.add(mancalaA);
		eastMancala.add(eastName);
		
		// create west side mancala pit
		JPanel westMancala = new BackgroundPanel(img);
		mancalaB = new JButton();
		mancalaB.setEnabled(false);
		mancalaB.setPreferredSize(new Dimension(120, 235));
		mancalaB.setBackground(Color.LIGHT_GRAY);

		//create label for player name
		JLabel westName = new JLabel(" " + board.getPlayerBsName() + " ");
		westName.setForeground(Color.WHITE);
		westName.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		// put west mancala in correct position and add name of player
		westMancala.setLayout(new GridBagLayout());
		westMancala.add(westName);
		westMancala.add(mancalaB);
		
		add(westMancala, BorderLayout.WEST);
		add(eastMancala, BorderLayout.EAST);

		// create panel for prompting whos turn it is
		JPanel turnPanel = new JPanel();
		turnLabel = new JLabel();
		
		String playerName = board.getPlayerAsName();
	
		turnLabel.setText(playerName + "'s Turn");
		turnLabel.setForeground(Color.WHITE);
		turnLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		turnPanel.add(turnLabel);

		add(turnPanel, BorderLayout.SOUTH);
		
		strategy.changeBorderPanelColor(undoPanel, turnPanel);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * stateChanged is called when a change in the model happens, the view is updated and repainted
	 * @param ChangeEvent e is an event object
	 */
	public void stateChanged(ChangeEvent e) {
	
		//iterate 1 - 13
		for(int i = 1; i <= board.pits.size(); i++)
		{
			//all conditions that will copy the model into the view
			if(i == 7)
				strategy.addToPit(mancalaA, board.pits.get(6).getStones());
			
			else if(i == 14)
				strategy.addToPit(mancalaB, board.pits.get(13).getStones());
			
			else if(i <= 6)
				strategy.addToPit(pitsButtonsA[i], board.pits.get(i-1).getStones());
			
			else if(i >= 8)	
				strategy.addToPit(pitsButtonsB[i-7], board.pits.get(i-1).getStones());
		}
		
		//get players turn and update view
		String playerName = "";
		if(board.getTurn().equals("A"))
			playerName = board.getPlayerAsName();
		else
			playerName = board.getPlayerBsName();
		turnLabel.setText(playerName + "'s Turn");
		
		
		if(board.checkEnd())
		{
			//calculate the total in the pits
			board.calculateTotal();
			
			//update the view with data from model
			for(int i = 1; i <= board.pits.size(); i++)
			{
				//all conditions that will copy the model into the view
				if(i == 7)
					strategy.addToPit(mancalaA, board.pits.get(6).getStones());
				
				else if(i == 14)
					strategy.addToPit(mancalaB, board.pits.get(13).getStones());
				
				else if(i <= 6)
					strategy.addToPit(pitsButtonsA[i], board.pits.get(i-1).getStones());
				
				else if(i >= 8)	
					strategy.addToPit(pitsButtonsB[i-7], board.pits.get(i-1).getStones());
			}
			
			//get winner, A or B, change label to show winner
			String winner = board.getWinner();
			if(winner.equals("A"))
				turnLabel.setText(board.getPlayerAsName() + " Wins!");
			else if(winner.equals("B"))
				turnLabel.setText(board.getPlayerBsName() + " Wins!");
			else
				turnLabel.setText("Tie!");
		}
		
		repaint();
		
	}

}
