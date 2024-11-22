package com.harshadeep;


import java.util.Random;
import java.util.Scanner;

class TicTacToe
 {
 	static char[][] board;
 	char[][] demo=new char[3][3];
 	
 	
 	 //constructor for initializing an array
 	TicTacToe()
 	{
 		board=new char[3][3];
 		initBoard();
 	}
 	
 	void initBoard()
 	{
 		for(int i=0;i<board.length;i++)
 		{
 			for(int j=0;j<board[i].length;j++)
 			{
 				board[i][j]=' '; // initializing with space character
 			}
 		}
 	}
 	
 	 // printing the board
 	static void printBoard()
 	{
 		System.out.println("   0   1   2   ");  //printing col numbers
 		System.out.println(" -------------");
 		for (int i = 0; i < board.length; i++) {
 			System.out.print(i+"| ");  //printing row numbers
 			for (int j = 0; j < board[i].length; j++) {
 				System.out.print(board[i][j]+" | ");
 			}
 			System.out.println();
 			System.out.println(" -------------");
 		}
 	}
 	
 	 //placing a X or O mark
 	static void placeMark(int row,int col,char mark)
 	{
 		if(row>=0 && row<3 && col>=0 && col<3)
 		{
 			board[row][col]=mark;
 		}
 		else
 			System.out.println("invalid position");
 	}
 	
 	// checking for the column win condition
 	static boolean checkColWin()
 	{
 		for (int j = 0; j < 3; j++) {
 			if(board[0][j]!=' ' &&board[0][j]==board[1][j]&&board[1][j]==board[2][j])
 			{
 				return true;
 			}
 			
 		}
 		return false;
 	}
 	
 	//checking for the row win condition
 	static boolean checkRowWin()
 	{
 		for(int i=0;i<3;i++)
 		{
 			if(board[i][0]!=' '&&board[i][0]==board[i][1] && board[i][1]==board[i][2])
 			{
 				return true;
 			}
 		}
 		return false;
 	}
 	
 	//checking for the diagonal win condition
 	static boolean checkDiagonalWin()
 	{
 		if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] ||
 				board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0])
 		{
 			return true;
 		}
 		return false;
 	}
 	
 	//checking for the draw condition
 	static boolean checkDraw()
 	{
 		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j]==' ')
				{
					return false;
				}
			}
		}
 		return true;
 	}
 	
 	//design purpose in the console
 	void initDemo()
 	{
 		demo[0][0]='T';
 		demo[0][1]='I';
 		demo[0][2]='C';
 		demo[1][0]='T';
 		demo[1][1]='A';
 		demo[1][2]='C';
 		demo[2][0]='T';
 		demo[2][1]='O';
 		demo[2][2]='E';
 		
 	}
 	
 	//design purpose in the console
 	void printDemo()
 	 	{
 	 		System.out.println("-------------");
 	 		for (int i = 0; i < board.length; i++) {
 	 			System.out.print("| ");  //printing row numbers
 	 			for (int j = 0; j < board[i].length; j++) {
 	 				System.out.print(demo[i][j]+" | ");
 	 			}
 	 			System.out.println();
 	 			System.out.println("-------------");
 	 		}
 	 	}
 	
 }

//player class which is a parent of humanplayer class and ai player class
abstract class Player
{
	 String name;
	 char mark;
	 
	 abstract void makeMove();
	 
	 //checking for the valid move or not
	 boolean isValidMove(int row,int col)
	 {
		 if(row>=0 && row <3 && col>=0 && col<3)
		 {
			 if(TicTacToe.board[row][col]==' ')
			 {
				 return true;
			 }
		 }
		 return false;
	 }
}



//human player class
 class HumanPlayer extends Player
 {
	 
	 HumanPlayer(String name,char mark)
	 {
		 this.name=name;
		 this.mark=mark;
	 }
	 
	 @Override
	 void makeMove()
	 {
		 int row,col;
		 Scanner sc=new Scanner(System.in);
		 do
		 {
			 System.out.println("Enter the row and column");
			 row=sc.nextInt();
			 col=sc.nextInt();
			 
		 }while(!isValidMove(row,col));
		TicTacToe.placeMark(row, col, mark);
	 }
	 
	 
 }
 
 //Ai player class
 class AiPlayer extends Player
 {
	 
	 AiPlayer(String name,char mark)
	 {
		 this.name=name;
		 this.mark=mark;
	 }
	 
	 @Override
	 void makeMove()
	 {
		 Random r=new Random(); //random class will generate the random numbers
		 int row,col;
		 do {
			 row=r.nextInt(3); //It will generate random numbers between 0 to 2, 3 is Excluded
			 col=r.nextInt(3);
		 }while(!isValidMove(row,col));
		 
		 TicTacToe.placeMark(row, col, mark);
	 }
	 
	 
 }
 
public class LaunchGame {

	public static void main(String[] args) {

		TicTacToe t=new TicTacToe();
		Scanner sc=new Scanner(System.in);
		
		//main dashboard
		System.out.println("<---WELCOME TO TIC-TOC-TOE--->");
		t.initDemo();
		t.printDemo();
		System.out.println("<----Lets Play---->");
		System.out.println("Choose the Game Mode to Play");
		System.out.println("1-> Multi-Player mode");
		System.out.println("2-> Play with AI");
		System.out.println("3-> Exit");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
		{
			humanPlay(); //calling the human play method
			break;
		}
		case 2:
		{
			AiPlay(); //calling the ai play method
			break;
		}
		case 3:
		{
			System.exit(0); //terminating the program
		}
		default:
		{
			System.out.println("Entered wrong input.Try Again");
		}
		}
	}
	static void humanPlay()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("<---Welcome to Multi-Player Mode----->");
		System.out.println("Enter the player-1 name");
		String name1=sc.nextLine();
		System.out.println("Enter the player-2 name");
		String name2=sc.nextLine();
		HumanPlayer p1=new HumanPlayer(name1,'X');
		HumanPlayer p2=new HumanPlayer(name2,'O');
		Player cp;
		System.out.println(p1.name+" --> "+p1.mark);
		System.out.println(p2.name+" --> "+p2.mark);
		System.out.println("Let's TacToeTic!!!");
		TicTacToe.printBoard();
		cp=p1;
		
		
		while(true)
		{
			System.out.println(cp.name+" your turn");
			cp.makeMove();
			
			TicTacToe.printBoard();
			if(TicTacToe.checkRowWin()||TicTacToe.checkColWin()||TicTacToe.checkDiagonalWin())
			{
				System.out.println(cp.name+" has won");
				break;
			}
			else if(TicTacToe.checkDraw())
			{
				System.out.println("Game Drawn");
				break;
			}
			else
			{
				//switching the players after their turn
				if(cp==p1)
				{
					cp=p2;
				}
				else
				{
					cp=p1;
				}
			}

		}

	}
	
	static void AiPlay()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("<---Welcome to AI-Player Mode----->");
		System.out.println("Enter the player name");
		String name=sc.nextLine();
		HumanPlayer p1=new HumanPlayer(name,'X');
		AiPlayer a1=new AiPlayer("HAI",'O');
		System.out.println(p1.name+" --> "+p1.mark);
		System.out.println(a1.name+" --> "+a1.mark);
		System.out.println("Lets HAIIII....");
		TicTacToe.printBoard();
		Player cp; //this reference is used for switching the players
		cp=p1;
		
		while(true)
		{
			System.out.println(cp.name+" your turn ");
			cp.makeMove();
			TicTacToe.printBoard();
			if(TicTacToe.checkRowWin()||TicTacToe.checkColWin()|| TicTacToe.checkDiagonalWin())
			{
				System.out.println(cp.name+" has won! ");
				break;
			}
			//checking the draw condition
			else if(TicTacToe.checkDraw())
			{
				System.out.println("Game has been drawn ");
				break;
			}
			else
			{
				//switching the turn of player to AI and vice versa
				if(cp==p1)
				{
					cp=a1;
				}
				else
					cp=p1;
			}
		}
	}
}

