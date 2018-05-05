//comment
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BoardFrame extends JFrame {

	public Board board;
	//constructor
	public BoardFrame()
	{
		JFrame setupFrame = new JFrame();
		setupFrame(setupFrame);
	}
	
	//frame for setting up the mancala game, how many stones?
	public void setupFrame(JFrame sFrame)
	{
		//set size of the setup frame with a title
		sFrame.setSize(400, 285);
		sFrame.setTitle("Mancala Setup");
		sFrame.setLayout(new BorderLayout());
		
		//create title label and set font and size
		JLabel setupTitle = new JLabel("Mancala Setup Settings", JLabel.CENTER);
		setupTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		//create panel that will display all prompts for game info
		JPanel gameInfoPanel = new JPanel();
		gameInfoPanel.setLayout(new BorderLayout());
		
		//create prompt for how many stones to begin with, and radio buttons and set action commands to identify which radio was chosen
		JLabel stonePrompt = new JLabel("How many stones would you like to start with?");
		JRadioButton threeStone = new JRadioButton("3");
		threeStone.setActionCommand("3");
		threeStone.setSelected(true);
		JRadioButton fourStone = new JRadioButton("4");
		fourStone.setActionCommand("4");
		fourStone.setSelected(false);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(threeStone);
		buttonGroup.add(fourStone);	

		//create a stone panel, add prompt and stone radio buttons to stone panel
		JPanel stonePanel = new JPanel();
		stonePanel.setLayout(new FlowLayout());
		stonePanel.add(stonePrompt);
		stonePanel.add(threeStone);
		stonePanel.add(fourStone);
		
		//add the stone panel to the main gameInfoPanel
		gameInfoPanel.add(stonePanel, BorderLayout.NORTH);
		
		//create panel for holding name prompts and text boxes
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());	
		
		//create prompt and textbox for getting name of player A
		JLabel firstPlayerPrompt = new JLabel("Enter the name of Player A");
		JTextField firstPlayerName = new JTextField("Player A", 20);
		
		//add first player prompt and textbox to the namePanel
		namePanel.add(firstPlayerPrompt);
		namePanel.add(firstPlayerName);
	
		//create prompt and text box for getting name of player b
		JLabel secondPlayerPrompt = new JLabel("Enter the name of Player B");
		JTextField secondPlayerName = new JTextField("Player B", 20);
		
		//add second player prompt and text box to the namePanel
		namePanel.add(secondPlayerPrompt);
		namePanel.add(secondPlayerName);
		
		//add namePanel to the main gameInfoPanel
		gameInfoPanel.add(namePanel, BorderLayout.CENTER);
		
		//create a panel to hold radio buttons for the different themes
		JPanel themePanel = new JPanel();
		themePanel.setLayout(new FlowLayout());
		
		//create label for prompt and radio buttons for each theme and set action commands to identify which radio was chosen
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
		
		//add prompt and buttons to the themePanel
		themePanel.add(themePrompt);
		themePanel.add(rainbowTheme);
		themePanel.add(christmasTheme);
		
		//add the themePanel to the main gameInfoPanel
		gameInfoPanel.add(themePanel, BorderLayout.SOUTH);
		
	
		//create a panel to hold the start button and the quit button
		JPanel startPanel = new JPanel();
		
		//create a start button with action to get information, dispose of this setup screen, and call the gameFrame
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//get number of stones input from setup menu
				int stones = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
			
				board = new Board(stones);
				
				//get names of players from JTextFields and set them in the model
				board.setPlayerAsName(firstPlayerName.getText());
				board.setPlayerBsName(secondPlayerName.getText());
				
				//get the theme input from setup menu
				String theme = themeGroup.getSelection().getActionCommand();
				
				sFrame.dispose();
				
				//if theme selected is rainbow, send rainbow theme
				if(theme.equals("Rainbow"))
				{
					//get rainbow image
					BufferedImage img = null;
					try{
						img = ImageIO.read(new File("rainbow.jpg"));
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					//create a theme strategy, given rainbow theme and image associated
					BoardStrategy strategy = new RainbowTheme(img);
					
					//call game frame method to create frame using this theme 
					gameFrame(strategy);
				}
				else //else send christmas theme
				{
					//gameFrame();
				}
			}
		});
		startButton.setBackground(Color.WHITE);
		
		//create a quit button with action to exit when pressed
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		quitButton.setBackground(Color.RED);
		
		//add these buttons to the startPanel 
		startPanel.add(startButton);
		startPanel.add(quitButton);
		
		//add title to the NORTH, gameInfo to the CENTER, and startPanel to the SOUTH
		sFrame.add(setupTitle, BorderLayout.NORTH);
		sFrame.add(gameInfoPanel, BorderLayout.CENTER);
		sFrame.add(startPanel, BorderLayout.SOUTH);
		
		sFrame.setVisible(true);
		sFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//function for drawing out the game frame, automatically called by setupFrame
	public void gameFrame(BoardStrategy strategy)
	{
		setSize(1000,700);
		setTitle("Mancala");
		setLayout(new BorderLayout());
	
		//load background image from files and set to this frame
		Image img = strategy.getBackgroundImage();
		getContentPane().add(new BackgroundPanel(img));
		
		//create undoPanel to hold the undo button
		JPanel undoPanel = new JPanel();
		undoPanel.setLayout(new FlowLayout());
				
		//create undo button and set its preferred size and add it to the undoPanel
		JButton undoButton = new JButton("Undo");
		undoButton.setPreferredSize(new Dimension(80,20));
		undoPanel.add(undoButton, BorderLayout.NORTH);
		
		//add undoPanel to NORTH side of the backgroundImg label
		add(undoPanel, BorderLayout.NORTH);
		
		//create panel to hold all pits, 12 in total
		JPanel pitsPanel = new JPanel();
		pitsPanel.setLayout(new GridLayout(2, 6));
	
		//iterate 12 times to create all pits as JButtons, adding all pits to pitsPanel
		for(int i = 0; i < 12; i++)
		{
			JButton pit = new JButton();
			pit.setPreferredSize(new Dimension(50,50));
			strategy.setBackgroundColor(pit);
			strategy.addStone(pit, board.getNumOfStones());
			pitsPanel.add(pit);
		}
		
		//add pitsPanel to CENTER of background JLabel
		add(pitsPanel, BorderLayout.CENTER);
		
		//create east side mancala pit
		JPanel eastMancala = new BackgroundPanel(img);
		JButton mancalaA = new JButton();
		mancalaA.setEnabled(false);
		mancalaA.setPreferredSize(new Dimension(200, 300));
		mancalaA.setBackground(Color.LIGHT_GRAY);
		eastMancala.add(mancalaA);
		
		//create west side mancala pit
		JPanel westMancala = new BackgroundPanel(img);
		JButton mancalaB = new JButton();
		mancalaB.setEnabled(false);
		mancalaB.setPreferredSize(new Dimension(200, 300));
		mancalaB.setBackground(Color.LIGHT_GRAY);
		westMancala.add(mancalaB);
		
		add(westMancala, BorderLayout.WEST);
		add(eastMancala, BorderLayout.EAST);
//---------------------------------------------------------------------------		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
