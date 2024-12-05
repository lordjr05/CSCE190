/*
 * Written by Jeffrey Lord
 */
import java.util.Scanner;
import java.util.Random;

public class CSCEHW 
{
	public static final int BOARDSIZE = 10;		//Fixes the Board to the size of 10x10
	
	public static final char Car = 'X';			//Introduces Variables and Which each symbol represents
	public static final char Space = '_';
	public static final char Home = '^';
	public static final char Path = '#';
	public static final char Pothole = '_';
	
	
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		Random RNG = new Random();
	
		boolean playAgain = true;
		
		while(playAgain)							//Lets the Player Replay the game once they win or lose
		{
			Gameplay(keyboard, RNG);
			
			System.out.println("Do You Want to Play Again? (yes\no)");
			String PlayAgainAnswer = keyboard.next();
			
			playAgain = PlayAgainAnswer.equalsIgnoreCase("yes");
			
		}
		
		System.out.println("Thanks for Playing");			//Gives a goodbye text for whenever the player ends
		keyboard.close();
		
	}	
	public static void Gameplay(Scanner keyboard, Random RNG)
	{
		int pX = 0;			//Fixes the Player at the starting position of (0,0)
		int pY = 0;	
		
		int hX = 9;			//Fixes the Home at the point of (9,9)
		int hY = 9;
		
		char[][] board = new char[BOARDSIZE][BOARDSIZE];
		
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j] = Space;
			}
		}										//Sets each point with its variable 
		
		board[pY][pX] = Car;
		board[hY][hX] = Home;
		
		for (int i = 0; i < 5; i++)
		{
			int potholeX = RNG.nextInt(BOARDSIZE);
			int potholeY = RNG.nextInt(BOARDSIZE);
			board[potholeY][potholeX] = Pothole;
		}
		
		
		
		
		System.out.println("Welcome to Pothole Mania!");
		boolean gameOver= false;
		while(!gameOver)
		{
			for(int i=0;i<board.length;i++)			//Introduces the player to the game and tells the options to pick from
			{
				for(int j=0;j<board[i].length;j++)
				{
					System.out.print(board[i][j]);
					
				}
				System.out.println();
		}
		System.out.println("Enter either -1,0, or 1 to move in the x");
		int dX = keyboard.nextInt();
		System.out.println("Enter either -1,0, or 1 to move in the y");
		int dY = keyboard.nextInt();

		if(dX < -1 || dX > 1)
		{
			System.out.println("Invalid Movement");		//Informs the player when they move in a invalid way
			dX = 0;
		}
		if(dY < -1 || dY > 1)
		{
			System.out.println("Invalid Movement");
			dY = 0;
		}
		board[pY][pX] = Path;
		pX += dX;
		pY += dY;
		
		if(pX < 0)
		{
			pX = 0;
		}
		else if(pX > BOARDSIZE-1)			//Sets the player back in a valid position once the go off the board
		{
			pX = BOARDSIZE-1;
		}
		if(pY < 0)
		{
			pY = 0;
		}
		else if(pY > BOARDSIZE-1)
		{
			pY = BOARDSIZE-1;
		}
		board[pY][pX] = Car;
		if (pX == hX && pY == hY)
		{
			System.out.println("You made it home!!!");		//Awards the player once they reach the home position
			gameOver = true;
		}
		if (board[pY][pX] == Pothole)
		{
			System.out.println("You ran into a Pothole! You Lose!");		//Awards the player a loss if they hit a random pothole
			gameOver = true;
		}
			
		}
	}
}
