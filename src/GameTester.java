import java.util.Scanner;

public class GameTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	    BoardFrame gameFrame = new BoardFrame();
		
		Scanner in = new Scanner(System.in);
		System.out.println("How many initial stones?");
		int initial = in.nextInt();
		Board b = new Board(initial);
		System.out.println();
		System.out.println("Turn " + b.getTurn());
		b.display();
		boolean end = false;
		in = new Scanner(System.in);
		while (!end) {
			String inp = in.nextLine();
			if (inp.equals("undo"))
				b.undo();
			else
				b.select(inp.toUpperCase());
			System.out.println("Turn " + b.getTurn());
			b.display();
			end = b.checkEnd();
		}
		b.calculateTotal();
		System.out.println("--------------------------------------------------");
		b.display();
		System.out.println(b.getWinner());
		in.close();

	}

}
