/* Name: Ada-Marie Nita
 * Date: June 14th, 2019
 * Description: This Checkers game is supposed to be a fun interactive game between the user
 * and the computer. Each player will take turns moving their pieces until there are no more pieces
 * left on the checkers board. Points will be displayed at the end, whoever has the most points is dubbed the winner.
 * User has option to play again or exit the game.
 */
import java.util.Scanner;
public class Final_Project {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		welcomeSequence(); // This is what greets the user when they first open the game
		int points1 = 0; // variable that records player 1's points
		int points2 =0; // variable that records player 2' points		
		
		// This part allows player 1 to choose what they want to play as!
		System.out.println("Player 1: choose a character to be your playing piece (i.e. 'O', '@', '?'):");
		String user1Piece = input.nextLine();
		while (user1Piece.length()!=1) {
			if (user1Piece.length()>1) {
				System.out.println("Oops not an option! Type a valid playing piece (i.e. 'O', '@', '?'):");
				user1Piece = input.nextLine();
			}
		}	
		// This part allows player 2 to choose what they want to play as!
		System.out.println("Player 2: choose a character to be your playing piece (i.e. 'O', '@', '?'):");
		String user2Piece = input.nextLine();
		while (user2Piece.length()!=1) {
			if (user2Piece.length()>1) {
				System.out.println("Oops not an option! Type a valid playing piece (i.e. 'O', '@', '?'):");
				user2Piece = input.nextLine();
			}
		}	
		
		System.out.println("Great! Now let's begin the game! Player 1 will start at the bottom of the board and Player 2 will be at the top.\nRemember to have fun and be smart about where you move! CLICK ENTER to start the fun:");
		input.nextLine();
		
		int numValue = (int)'A'; // needed to print the alpha letters of the grid
		String letters = " "; // needed to print the alpha letter of the grid along the lefthand side
		String[][] array = new String[9][9]; // creates a 2D array a.k.a. the checkerboard
		int numbers = 1; // this is where the numbers start along the bottom of the grid
		String numbersRow = " "; // needed to print the numbers of the grid along the bottom
		
		// this creates the set-up checker board with the players' pieces and prints it out
		for (int i=0; i<9; i++) { // i represents the first coordinate of each position in the array
			for (int j=0; j<9; j++) { // j represents the second coordinate of each position in the array
				if (j==0 && i!=8) {
					letters = "" + (char)numValue + "";
					array[i][j] = letters;
					numValue++;
				} else if (i==0 && j%2==0 && j!=0) {
					array[i][j]="[" + user2Piece + "]";
				} else if (i==1 && j%2==1 && j!=0) {
					array[i][j] = "[" + user2Piece + "]";
				} else if (i==7 && j%2==1) {
					array[i][j] = "[" + user1Piece + "]";
				} else if (i==6 && j%2==0) {
					array[i][j] = "[" + user1Piece + "]";
				} else if (i==8 && j!=0){
					numbersRow = " " + numbers + " ";
					array[i][j] = numbersRow;
					numbers++;
				} else if (i==8 && j==0) {
					array[i][j] = " ";
				}else {
				 	array[i][j] = "[ ]";
				}
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
		
		// Player 1 starts the game by typing in the piece they want to move, checking if it's a valid response
		System.out.println("Player 1: Which piece do you want to move? (i.e. G2, E5, F7)");
		String startPlace = input.nextLine();
		startPlace = startPlace.toUpperCase();
			while (startPlace.length()>2 || startPlace.charAt(0)>72 || startPlace.charAt(0)<65 || startPlace.charAt(1)>56 || startPlace.charAt(1)<48) {
				System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4)");
				startPlace = input.nextLine();
				startPlace = startPlace.toUpperCase();
			}
			int countForPlayer1Piece = 2; // will be used later on to check if player 1 still has pieces left to play
			int countForPlayer2Piece = 2; // will be used later on to check if player 2 still has pieces left to play
			
		// the while loop will allow the players to continuously take their turns playing their pieces until one player no longer has any pieces left
		while (countForPlayer1Piece!=0 && countForPlayer2Piece!=0) {
			
		// PLAYER 1 START: this is where player 1 will begin choosing their playing piece, given
		// that the piece exists, you can move with that piece, and if the piece they choose is surrounded by the
		// opponent's piece
			
			int	i = startPlace.charAt(0)-65; // represents the first coordinate of the position the user chose
			int	j = startPlace.charAt(1)-48; // represents the second coordinate of the position the user chose
			
			// so long as none of the following conditions happen, the game can continue, otherwise, the user cannot move the piece they chose
		while ((!array[i][j].contains("[" + user1Piece + "]")) || (j<8 && j>1 && array[i-1][j-1].contains("[" + user1Piece + "]") && array[i-1][j+1].contains("[" + user1Piece + "]")) || 
				(j==8 && array[i-1][j-1].contains("[" + user1Piece + "]")) || (j==1 && array[i-1][j+1].contains("["+ user1Piece + "]")) ||
				(j<7 && j>2 && array[i-1][j-1].contains("[" + user2Piece + "]") && !array[i-2][j-2].contains("[ ]") && array[i-1][j+1].contains("[" + user2Piece + "]") && !array[i-2][j+2].contains("[ ]")) || 
				((j==2 && array[i-1][j+1].contains("[" + user2Piece + "]") && !array[i-2][j+2].contains("[ ]") && !array[i-1][j-1].contains("[ ]")) || (j==1 && i!=1 && array[i-1][j+1].contains("[" + user2Piece + "]") && !array[i-2][j+2].contains("[ ]"))) || 
				((j==7 && array[i-1][j-1].contains("[" + user2Piece + "]") && !array[i-2][j-2].contains("[ ]") && !array[i-1][j+1].contains("[ ]")) || (j==8 && array[i-1][j-1].contains("[" + user2Piece + "]") && !array[i-2][j-2].contains("[ ]"))) ||
				((j>1 && j<8 && array[i-1][j-1].contains("[" + user2Piece + "]") && array[i-1][j+1].contains("[" + user1Piece + "]") && !array[i-2][j-2].contains("[ ]"))) ||
				(j>1 && j<8 && array[i-1][j+1].contains("[" + user2Piece + "]") && array[i-1][j-1].contains("[" + user1Piece + "]") && !array[i-2][j+2].contains("[ ]")) ||
				((j==1) && (i==1) && !array[i-1][j+1].contains("[ ]"))) {
			
			if (!array[i][j].contains("[" + user1Piece + "]")) {
				System.out.println("Oops, that box doesn't have your piece! Type in a valid coordinate:");
				startPlace = input.nextLine();
				startPlace = startPlace.toUpperCase();
				i = startPlace.charAt(0)-65; 
				j = startPlace.charAt(1)-48; 
			} else {
				System.out.println("Uh oh! Looks like you have no where to go. Choose a DIFFERENT piece to move:");
				startPlace = input.nextLine();
				startPlace = startPlace.toUpperCase();
				i = startPlace.charAt(0)-65;
				j = startPlace.charAt(1)-48;
			}
			
		}	
		
		String deadPiece = " "; // the name of the piece player 1 may jump over if they have the chance
		int m = 0; // the first coordinate of the position of the 'deadpiece'
		int n = 0; // the second coordinate of the position of the 'deadpiece'
		int x = 0; // if no deadpiece, x is 1st coordinate of the position the user wants to move to or FINISH PLACE
		int y = 0; // if no deadpiece, y is the 2nd coordinate of the position the user wants to move to or FINISH PLACE
		
				// FIRST CONDITION: user can move freely around, anywhere
				if (((j>2 && j<7) && (array[i-1][j-1].contains("[ ]") && array[i-1][j+1].contains("[ ]"))) || (j==1 && array[i-1][j+1].contains("[ ]")) || (j==8 && array[i-1][j-1].contains("[ ]")) || ((j==2) && array[i-1][j-1].contains("["+user2Piece+"]") && array[i-1][j+1].contains("[ ]"))  || (j==7 && array[i-1][j-1].contains("[" + user1Piece + "]") && array[i-1][j+1].contains("[ ]"))|| ((j==7) && array[i-1][j+1].contains("[" +user2Piece + "]") && array[i-1][j-1].contains("[ ]")) || (j==2 && array[i-1][j-1].contains("[ ]") && array[i-1][j+1].contains("[ ]")) || (j==7 && array[i-1][j-1].contains("[ ]") && array[i-1][j+1].contains("[ ]")) || (j>0 && j<8 && array[i-1][j+1].contains("[ ]") && !array[i-1][j-1].contains("[" + user2Piece + "]")) || (j>1 && j<9 && array[i-1][j-1].contains("[ ]") && !array[i-1][j+1].contains("[" + user2Piece + "]"))) {
					array[i][j]="[ ]"; // makes the starting piece disappear

					System.out.println("Where would you like to move the piece to?");
					String finishPlace = input.nextLine();
					finishPlace = finishPlace.toUpperCase();
					
						x = finishPlace.charAt(0)-65;
						y = finishPlace.charAt(1)-48;
						
						// the following conditions are carried out if the user tries to move somewhere outside of the boundary of one block at a time
						while ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) ||(array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) || (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48)) {
							if (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48) {
								System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4)");
								finishPlace = input.nextLine();
								finishPlace = finishPlace.toUpperCase();
								x = finishPlace.charAt(0)-65;
								y = finishPlace.charAt(1)-48;
							}
							if (array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) {
								System.out.println("That space is not available! Type a different place:");
								finishPlace = input.nextLine();
								finishPlace = finishPlace.toUpperCase();
								x = finishPlace.charAt(0)-65;
								y = finishPlace.charAt(1)-48;
							}
							if ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1)))){
								System.out.println("Hmm, you can't move there! Type a different coordinate:");
								finishPlace = input.nextLine();
								finishPlace = finishPlace.toUpperCase();
								x = finishPlace.charAt(0)-65;
								y = finishPlace.charAt(1)-48;
							}
							
						}
						
						array[x][y]="[" + user1Piece + "]"; //makes the piece the user moved appear in the new location
					
							for (int h=0; h<9; h++) { // h and g are incremental variables used to print out grid with newly shifted pieces
								for (int g=0; g<9; g++) {
									System.out.print(array[h][g]);
								}
								System.out.println();
							}
				
				// SECOND CONDITION: If there is an option to jump over the other player's piece, the computer will automatically jump over it
				} else if ((j>2 && j<7) && array[i-1][j-1].contains("[" + user2Piece + "]") && (array[i-1][j+1].contains("[ ]") || array[i-1][j+1].contains("[" + user1Piece + "]"))) {
					if (array[i-2][j-2].contains("[ ]")) {
						System.out.println("Player 1, you get to jump over one of Player 2's piece!");
						points1++;
						x = i-2;
						y = j-2;
						array[i-1][j-1]= "[ ]";
						array[i][j]= "[ ]";
						array[x][y] = "[" + user1Piece + "]";
						for (int h=0; h<9; h++) { // h and g are incremental variables used to print out grid with newly shifted pieces
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					} else {
						array[i][j]="[ ]"; // makes the piece they moved disappear from old location

						System.out.println("Where would you like to move the piece to?");
						String finishPlace = input.nextLine();
						finishPlace = finishPlace.toUpperCase();
						
							x = finishPlace.charAt(0)-65;
							y = finishPlace.charAt(1)-48;
							
							// the following conditions are carried out if the user tries to move somewhere outside of the boundary of one block at a time	
							while ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) ||(array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) || (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48)) {
								if (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48) {
									System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4)");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								if (array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) {
									System.out.println("That space is not available! Type a different place:");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								if ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1)))){
									System.out.println("Hmm, you can't move there! Type a different coordinate:");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								
							}
							
							array[x][y]="[" + user1Piece + "]"; //makes the piece the user moved appear in the new location
	
								for (int h=0; h<9; h++) { // h and g are incremental variables used to print out grid with newly shifted pieces
									for (int g=0; g<9; g++) {
										System.out.print(array[h][g]);
									}
									System.out.println();
								}
					} 
				// THIRD CONDITION: If there is an opportunity to jump over other player's piece somewhere else
				} else if ((j>2 && j<7) && array[i-1][j+1].contains("[" + user2Piece + "]") && (array[i-1][j-1].contains("[ ]") || array[i-1][j-1].contains("[" + user1Piece + "]"))) {
					if (array[i-2][j+2].contains("[ ]")) {
						System.out.println("Player 1, you get to jump over one of Player 2's piece!");
						points1++;
						 x = i-2;
						y = j+2;
						array[x][y]="[" + user1Piece + "]";
						array[i-1][j+1]="[ ]";
						array[i][j]="[ ]";
						for (int h=0; h<9; h++) { // h and g are used to print out the grid with newly shifted places
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
								
					} else {	
						array[i][j]="[ ]"; // makes piece disappear from old location
						
						System.out.println("Where would you like to move the piece to?");
						String finishPlace = input.nextLine();
						finishPlace = finishPlace.toUpperCase();
						
							x = finishPlace.charAt(0)-65;
							y = finishPlace.charAt(1)-48;
							
							// the following conditions are carried out if the user tries to move somewhere outside of the boundary of one block at a time
								while ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) ||(array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) || (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48)) {
								if (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48) {
									System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4)");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								if (array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) {
									System.out.println("That space is not available! Type a different place:");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								if ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1)))){
									System.out.println("Hmm, you can't move there! Type a different coordinate:");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								
							}
							
							array[x][y]="[" + user1Piece + "]"; // makes piece appear in new location

								for (int h=0; h<9; h++) {// h and g are used to print out the grid with newly shifted places
									for (int g=0; g<9; g++) {
										System.out.print(array[h][g]);
									}
									System.out.println();
								}
					}
				// FOUR CONDITION: If the user has TWO options to jump over the other players pieces
				} else if ((j>2 && j<7) && array[i-1][j-1].contains("[" + user2Piece + "]") && array[i-1][j+1].contains("["+user2Piece+"]")) {
					
						if (array[i-2][j-2].contains("[ ]") && array[i-2][j+2].contains("[ ]")) { // if both spots are available, user can decide where they want to jump to
							System.out.println("Player 1: You can jump over two of Player 2's pieces. Which piece do you want to jump over?");
							deadPiece = input.nextLine();
							deadPiece = deadPiece.toUpperCase(); // the user types in the location of the piece they want to jump over
							m = deadPiece.charAt(0)-65;
							n = deadPiece.charAt(1)-48;
	
							if (m==(i-1) && n==(j-1)) {
								x = i-2;
								y = j-2;
								array[m][n] = "[ ]";
								array[i][j] = "[ ]";
								array[x][y]= "[" + user1Piece + "]";
								points1++;
								for (int h=0; h<9; h++) { // h and g used to print out grid
									for (int g=0; g<9; g++) {
										System.out.print(array[h][g]);
									}
									System.out.println();
								}
							} else if (m==(i-1) && n==(j+1)){
								x = i-2;
								y = j+2;
								array[m][n] = "[ ]";
								array[i][j] = "[ ]";
								array[x][y]="[" + user1Piece + "]";
								points1++;
								for (int h=0; h<9; h++) { // h and g used to print out grid
									for (int g=0; g<9; g++) {
										System.out.print(array[h][g]);
									}
									System.out.println();
								}
							}
						} else if (array[i-2][j-2].contains("[ ]") && !array[i-2][j+2].contains("[ ]")) { // if spot not available behind, then user has only one other option, and the jump becomes automatic
							System.out.println("Player 1, you can jump over one of Player 2's piece!");
							points1++;
							x = i-2;
							y = j-2;
							array[x][y]="[" + user1Piece + "]";
							array[i-1][j-1]="[ ]";
							array[i][j]="[ ]";
							for (int h=0; h<9; h++) { // printing grid using variables h and g
								for (int g=0; g<9; g++) {
									System.out.print(array[h][g]);
								}
								System.out.println();
							}
						} else if (array[i-2][j+2].contains("[ ]") && !array[i-2][j-2].contains("[ ]")) { // if one spot is not available, it will automatically jump to the other
							System.out.println("Player 1, you can jump over one of Player 2's piece!");
							points1++;
							 x = i-2;
							 y = j+2;
							array[x][y]="["+ user1Piece + "]";
							array[i-1][j+1]="[ ]";
							array[i][j]="[ ]";
							for (int h=0; h<9; h++) { // printing grid with h and g
								for (int g=0; g<9; g++) {
									System.out.print(array[h][g]);
								}
								System.out.println();
							}
						} 
						
				// FIFTH CONDITION: The piece is in the second column, they technically can only jump over one spot
				} else if ((j==2) && array[i-1][j+1].contains("[" + user2Piece + "]") && (array[i-1][j-1].contains("[ ]") || array[i-1][j-1].contains("[" + user1Piece + "]"))) {
					if (array[i-2][j+2].contains("[ ]")) {
						System.out.println("Player 1, you get to jump over one of Player 2's piece!");
						points1++;
						x = i-2;
						y = j+2;
						array[x][y]="[" + user1Piece + "]";
						array[i-1][j+1]="[ ]";
						array[i][j]="[ ]";
						for (int h=0; h<9; h++) { // h and g used to print grid
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					} else {
						array[i][j]="[ ]"; // makes piece from old location disappear
						
						System.out.println("Where would you like to move the piece to?");
						String finishPlace = input.nextLine();
						finishPlace = finishPlace.toUpperCase();
						
							x = finishPlace.charAt(0)-65;
							y = finishPlace.charAt(1)-48;
							
							
							while ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) ||(array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) || (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48)) {
								if (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48) {
									System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4)");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								if (array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) {
									System.out.println("That space is not available! Type a different place:");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								if ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1)))){
									System.out.println("Hmm, you can't move there! Type a different coordinate:");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								
							}
							
							array[x][y]="[" + user1Piece + "]"; // makes piece appear in new location
							
						// PRINTING GRID USING VARIABLES H AND G
								for (int h=0; h<9; h++) {
									for (int g=0; g<9; g++) {
										System.out.print(array[h][g]);
									}
									System.out.println();
								}
						}
				// SIXTH CONDITION: If piece is in column 2
				} else if ((j==2) && array[i-1][j-1].contains("[" + user2Piece + "]") && (array[i-1][j+1].contains("[" + user2Piece +"]"))) {
						if (array[i-2][j+2].contains("[ ]")) {
							System.out.println("Player 1, you get to jump over one of Player 2's piece!");
							points1++;
							x = i-2;
							y = j+2;
							array[x][y]="[" + user1Piece + "]";
							array[i-1][j+1]="[ ]";
							array[i][j]="[ ]";
							for (int h=0; h<9; h++) {
								for (int g=0; g<9; g++) {
									System.out.print(array[h][g]);
								}
								System.out.println();
							}
						}
					
				} else if ((j==1) && array[i-1][j+1].contains("[" + user2Piece + "]")) {
						if (array[i-2][j+2].contains("[ ]")) {
							System.out.println("Player 1, you get to jump over one of Player 2's piece!");
							points1++;
							x = i-2;
							y = j+2;
							array[x][y]="[" + user1Piece + "]";
							array[i-1][j+1]="[ ]";
							array[i][j]="[ ]";
							for (int h=0; h<9; h++) {
								for (int g=0; g<9; g++) {
									System.out.print(array[h][g]);
								}
								System.out.println();
							}
						}
				} else if ((j==8) && array[i-1][j-1].contains("[" + user2Piece + "]")) {
					if (array[i-2][j-2].contains("[ ]")) {
						System.out.println("Player 1, you get to jump over one of Player 2's piece!");
						points1++;
						x = i-2;
						y = j-2;
						array[x][y]="[" + user1Piece + "]";
						array[i-1][j-1]="[ ]";
						array[i][j]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					}
				} else if ((j==7) && array[i-1][j-1].contains("[" + user2Piece + "]") && (array[i-1][j+1].contains("[ ]") || array[i-1][j+1].contains("[" + user1Piece + "]"))) {
					if (array[i-2][j-2].contains("[ ]")) {
						System.out.println("Player 1, you get to jump over one of Player 2's piece!");
						points1++;
						x = i-2;
						y = j-2;
						array[x][y]="[" + user1Piece + "]";
						array[i-1][j-1]="[ ]";
						array[i][j]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					} else {
						array[i][j]="[ ]";
						
						System.out.println("Where would you like to move the piece to?");
						String finishPlace = input.nextLine();
						finishPlace = finishPlace.toUpperCase();
							x = finishPlace.charAt(0)-65;
							y = finishPlace.charAt(1)-48;
							while ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) ||(array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) || (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48)) {
								if (finishPlace.length()>2 || finishPlace.charAt(0)>72 || finishPlace.charAt(0)<65 || finishPlace.charAt(1)>56 || finishPlace.charAt(1)<48) {
									System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4)");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								if (array[x][y].contains("[" + user1Piece + "]") || array[x][y].contains("[" + user2Piece + "]")) {
									System.out.println("That space is not available! Type a different place:");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
								if ((x==(i+1)) || (x!=(i-1) && (y!=(j-1) && y!=(j+1))) || (x!=(i-1) && (y==(j-1) || y==(j+1))) || (x==(i-1) && (y!=(j-1) && y!=(j+1)))){
									System.out.println("Hmm, you can't move there! Type a different coordinate:");
									finishPlace = input.nextLine();
									finishPlace = finishPlace.toUpperCase();
									x = finishPlace.charAt(0)-65;
									y = finishPlace.charAt(1)-48;
								}
							}
							
							array[x][y]="[" + user1Piece + "]";
							
						// PRINT GRID
								for (int h=0; h<9; h++) {
									for (int g=0; g<9; g++) {
										System.out.print(array[h][g]);
									}
									System.out.println();
								}
					}
				} else if ((j==7) && array[i-1][j-1].contains("[" + user2Piece + "]") && array[i-1][j+1].contains("[" + user2Piece + "]")) {
					if (array[i-2][j-2].contains("[ ]")) {
						System.out.println("Player 1, you get to jump over one of Player 2's piece!");
						points1++;
						x = i-2;
						y = j-2;
						array[x][y]="[" + user1Piece + "]";
						array[i-1][j-1]="[ ]";
						array[i][j]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					}
				} 
				// IF PLAYER 1'S PIECE REACHES THE END OF THE BOARD IT DISAPPEARS AND THEY WIN A POINT

				for (int h=0; h<9; h++) {
					if (array[0][h].contains("[" + user1Piece + "]")) {
						array[0][h] = "[ ]";
						System.out.println("YAY! PLAYER 1! YOU REACHED THE END OF THE BOARD! YOU GET 2 POINTS! CLICK ENTER TO CONTINUE!");
						input.nextLine();
						points1+=2;
						for (int k=0; k<9; k++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[k][g]);
							}
							System.out.println();
							}	
						}
				}
				countForPlayer1Piece=0;
				countForPlayer2Piece=0;
				for (int h=0; h<9; h++) { //h increases the increment of the first coordinate of the 2D array by one each time
					for (int g=0; g<9; g++) { //g increases the increment of the second coordinate of the 2D array by one each time
						if (array[h][g].contains("[" + user1Piece + "]")) {
							countForPlayer1Piece++;
						} else if (array[h][g].contains("[" + user2Piece + "]")) {
							countForPlayer2Piece++;
						}
					}
				}
				
				if (countForPlayer1Piece==0 || countForPlayer2Piece==0) {
					System.out.print("GAME OVER! AWWW THE GAME FINISHED ALREADY?! I HOPE YOU HAD FUN! CLICK ENTER TO SEE THE RESULTS:");
					input.nextLine();
					if (countForPlayer1Piece==0) {
						displayResults(points1, points2);
						String results = displayResults(points1, points2);
						System.out.println(results);
						System.out.println("It was nice playing with you! Hope you come back soon to play again! BYE!!!!!!");
						
					} else if (countForPlayer2Piece==0) {
						displayResults(points1, points2);
						String results = displayResults(points1, points2);
						System.out.println(results);
						System.out.println("It was nice playing with you! Hope you come back soon to play again! BYE!!!!!!");
					}
				} else {
					countForPlayer1Piece = 0;
					countForPlayer2Piece =0;
					// PLAYER 2 START
						System.out.println("Player 2: Which piece would you like to move? (i.e. B3, B7, C2) ");
						String startPlace2 = input.nextLine();
						startPlace2 = startPlace2.toUpperCase();
						
						String deadPiece2 = " ";
						int u = 0, o = 0, t = 0, q = 0;
						
							while (startPlace2.length()>2 || startPlace2.charAt(0)>72 || startPlace2.charAt(0)<65|| startPlace2.charAt(1)>56 || startPlace2.charAt(1)<48) {
								System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4, capitals matter!)");
								startPlace2=input.nextLine();
								startPlace2 = startPlace2.toUpperCase();
							}
						
							int r = startPlace2.charAt(0)-65;
							int s = startPlace2.charAt(1)-48;
							
							while ((!array[r][s].contains("[" + user2Piece + "]")) || (s<8 && s>1 && array[r+1][s-1].contains("[" + user2Piece + "]") && array[r+1][s+1].contains("[" + user2Piece + "]")) ||
									(s==8 && array[r+1][s-1].contains("[" + user2Piece + "]")) || (s==1 && array[r+1][s+1].contains("["+ user2Piece + "]")) ||
									(s<7 && s>2 && array[r+1][s-1].contains("[" + user1Piece + "]") && !array[r+2][s-2].contains("[ ]") && array[r+1][s+1].contains("[" + user1Piece + "]") && !array[r+2][s+2].contains("[ ]")) ||
									((s==2 && array[r+1][s+1].contains("[" + user1Piece + "]") && !array[r+2][s+2].contains("[ ]") && !array[r+1][s-1].contains("[ ]")) || (s==1 && array[r+1][s+1].contains("[" + user1Piece + "]") && !array[r+2][s+2].contains("[ ]"))) || 
									((s==7 && array[r+1][s-1].contains("[" + user1Piece + "]") && !array[r+2][s-2].contains("[ ]") && !array[r+1][s+1].contains("[ ]")) || (j==8 && array[i-1][j-1].contains("[" + user2Piece + "]") && !array[i-2][j-2].contains("[ ]"))) ||
									((s>1 && s<8 && array[r+1][s-1].contains("[" + user1Piece + "]") && array[r+1][s+1].contains("[" + user2Piece + "]") && !array[r+2][s-2].contains("[ ]"))) ||
									(s>1 && s<8 && array[r+1][s+1].contains("[" + user1Piece + "]") && array[r+1][s-1].contains("[" + user2Piece + "]") && !array[r+2][s+2].contains("[ ]")) ||
									(array[6][8].contains("[" + user2Piece + "]") && !array[7][7].contains("[ ]")) ||
									((s==8) && (r==6) && !array[7][7].contains("[ ]"))) {	
										if (!array[r][s].contains("[" + user2Piece + "]")) {
											System.out.println("Oops, that box doesn't have your piece! Type in a valid coordinate:");
											startPlace2 = input.nextLine();
											startPlace2 = startPlace2.toUpperCase();
											r = startPlace2.charAt(0)-65;
											s = startPlace2.charAt(1)-48;
										} else {
											System.out.println("Uh oh! Looks like you have no where to go. Choose a different piece to start with:");
											startPlace2 = input.nextLine();
											startPlace2 = startPlace2.toUpperCase();
											r = startPlace2.charAt(0)-65;
											s = startPlace2.charAt(1)-48;
										}
							}
							
			
			
				if (((s>2 && s<7) && array[r+1][s-1].contains("[ ]") && array[r+1][s+1].contains("[ ]")) || (s==1 && array[r+1][s+1].contains("[ ]")) || (s==8 && array[r+1][s-1].contains("[ ]")) || ((s==2) && array[r+1][s-1].contains("["+user1Piece+"]") && array[r+1][s+1].contains("[ ]")) || ((s==7) && array[r+1][s+1].contains("[" +user1Piece + "]") && array[r+1][s-1].contains("[ ]")) || (s==2 && array[r+1][s-1].contains("[ ]") && array[r+1][s+1].contains("[ ]")) || (s==7 && array[r+1][s-1].contains("[ ]") && array[r+1][s+1].contains("[ ]")) || (s>0 && s<8 && array[r+1][s+1].contains("[ ]") && !array[r+1][s-1].contains("[" + user1Piece + "]")) || (s>1 && s<9 && array[r+1][s-1].contains("[ ]") && !array[r+1][s+1].contains("[" + user1Piece + "]"))){
					array[r][s] = "[ ]";
					
					System.out.println("To which spot do you want to move your piece?");
					String finishPlace2 = input.nextLine();
					finishPlace2 = finishPlace2.toUpperCase();
						
							t = finishPlace2.charAt(0)-65; 
							q = finishPlace2.charAt(1)-48; 
						
						while ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) && q!=(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) || (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48 )) {
							if (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48) {
								System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4, capitals matter!)");
								finishPlace2 = input.nextLine();
								finishPlace2 = finishPlace2.toUpperCase();
								t = finishPlace2.charAt(0)-65;
								q = finishPlace2.charAt(1)-48;
							}
							if (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) {
								System.out.println("That space is unavailable! Type a different place:");
								finishPlace2 = input.nextLine();
								finishPlace2 = finishPlace2.toUpperCase();
								t = finishPlace2.charAt(0)-65;
								q = finishPlace2.charAt(1)-48;
							}
							if ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) || q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1)))){
								System.out.println("Hmm, you can't move there! Type in a different coordinate:");
								finishPlace2 = input.nextLine();
								finishPlace2 = finishPlace2.toUpperCase();
								t = finishPlace2.charAt(0)-65;
								q = finishPlace2.charAt(1)-48;
							}
						}
						
						array[t][q] = "[" + user2Piece + "]";
						
					// PRINT GRID
						for (int w=0; w<9; w++) {
							for (int k=0; k<9; k++) {
								System.out.print(array[w][k]);
							}
							System.out.println();
						}
					
			
				} else if ((s>2 && s<7) && array[r+1][s-1].contains("[" + user1Piece + "]") && (array[r+1][s+1].contains("[ ]") || array[r+1][s+1].contains("[" + user2Piece + "]"))) {
					if (array[r+2][s-2].contains("[ ]")) {
						System.out.println("Player 2, you get to jump over one of Player 1's piece!");
						points2++;
						t = r+2;
						q = s-2;
						array[r+1][s-1]= "[ ]";
						array[r][s]= "[ ]";
						array[t][q] = "[" + user2Piece + "]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					} else {
						array[r][s] = "[ ]";
						
						System.out.println("To which spot do you want to move your piece?");
						String finishPlace2 = input.nextLine();
						finishPlace2 = finishPlace2.toUpperCase();
							
								t = finishPlace2.charAt(0)-65; 
								q = finishPlace2.charAt(1)-48; 
							
							while ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) && q!=(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) || (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48 )) {
								if (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48) {
									System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4, capitals matter!)");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
								if (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) {
									System.out.println("That space is unavailable! Type a different place:");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
								if ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) || q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1)))){
									System.out.println("Hmm, you can't move there! Type in a different coordinate:");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
							}
							
							array[t][q] = "[" + user2Piece + "]";
							
						// PRINT GRID
							for (int w=0; w<9; w++) {
								for (int k=0; k<9; k++) {
									System.out.print(array[w][k]);
								}
								System.out.println();
							}
					}
				} else if ((s>2 && s<7) && array[r+1][s+1].contains("[" + user1Piece + "]") && (array[r+1][s-1].contains("[ ]") || array[r+1][s-1].contains("[" + user2Piece + "]"))) {
					if (array[r+2][s+2].contains("[ ]")) {
						System.out.println("Player 2, you get to jump over one of Player 1's piece!");
						points2++;
						t=r+2;
						q=s+2;
						array[t][q]="[" + user2Piece + "]";
						array[r+1][s+1]="[ ]";
						array[r][s]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					} else {
						array[r][s] = "[ ]";
						
						System.out.println("To which spot do you want to move your piece?");
						String finishPlace2 = input.nextLine();
						finishPlace2 = finishPlace2.toUpperCase();
							
							t = finishPlace2.charAt(0)-65; 
							q = finishPlace2.charAt(1)-48; 
							
							while ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) && q!=(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) || (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48 )) {
								if (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48) {
									System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4, capitals matter!)");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
								if (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) {
									System.out.println("That space is unavailable! Type a different place:");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
								if ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) || q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1)))){
									System.out.println("Hmm, you can't move there! Type in a different coordinate:");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
							}
							
							array[t][q] = "[" + user2Piece + "]";
							
						// PRINT GRID
							for (int w=0; w<9; w++) {
								for (int k=0; k<9; k++) {
									System.out.print(array[w][k]);
								}
								System.out.println();
							}
					}
				} else if ((s>2 && s<7) && array[r+1][s-1].contains("[" + user1Piece + "]") && array[r+1][s+1].contains("["+user1Piece+"]")) {
					if (array[r+2][s-2].contains("[ ]") && array[r+2][s+2].contains("[ ]")) {
						System.out.println("Player 2: You can jump over two of Player 1's pieces. Which piece do you want to jump over?");
						deadPiece2 = input.nextLine();
						deadPiece2 = deadPiece2.toUpperCase();
						u = deadPiece2.charAt(0)-65;
						o = deadPiece2.charAt(1)-48;

						if (u==(r+1) && o==(s-1)) {
							t=r+2;
							q=s-2;
							array[u][o] = "[ ]";
							array[r][s] = "[ ]";
							array[t][q]= "[" + user2Piece + "]";
							points2++;
							for (int h=0; h<9; h++) {
								for (int g=0; g<9; g++) {
									System.out.print(array[h][g]);
								}
								System.out.println();
							}
						} else if (u==(r+1) && o==(s+1)){
							t=r+2;
							q=s+2;
							array[u][o] = "[ ]";
							array[r][s] = "[ ]";
							array[t][q]="[" + user2Piece + "]";
							points2++;
							for (int h=0; h<9; h++) {
								for (int g=0; g<9; g++) {
									System.out.print(array[h][g]);
								}
								System.out.println();
							}
						}
					} else if (array[r+2][s-2].contains("[ ]") && !array[r+2][s+2].contains("[ ]")) {
						System.out.println("Player 2, you can jump over one of Player 1's pieces!");
						points2++;
						t = r+2;
						q = s-2;
						array[t][q]="[" + user2Piece + "]";
						array[r+1][s-1]="[ ]";
						array[r][s]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					} else if (array[r+2][s+2].contains("[ ]") && !array[r+2][s-2].contains("[ ]")) {
						System.out.println("Player 2, you can jump over one of Player 1's piece!");
						points2++;
						t = r+2;
						q= s+2;
						array[t][q]="["+ user2Piece + "]";
						array[r+1][s+1]="[ ]";
						array[r][s]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					} 
				} else if ((s==2) && array[r+1][s+1].contains("[" + user1Piece + "]") && (array[r+1][s-1].contains("[ ]") || array[r+1][s-1].contains("[" + user2Piece + "]"))) {
					if (array[r+2][s+2].contains("[ ]")) {
						System.out.println("Player 2, you get to jump over one of Player 1's pieces!");
						points2++;
						t = r+2;
						q= s+2;
						array[t][q]="[" + user2Piece + "]";
						array[r+1][s+1]="[ ]";
						array[r][s]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					} else {
						array[r][s] = "[ ]";
						
						System.out.println("To which spot do you want to move your piece?");
						String finishPlace2 = input.nextLine();
						finishPlace2 = finishPlace2.toUpperCase();
							
								t = finishPlace2.charAt(0)-65; 
								q = finishPlace2.charAt(1)-48; 
							
							while ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) && q!=(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) || (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48 )) {
								if (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48) {
									System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4, capitals matter!)");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
								if (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) {
									System.out.println("That space is unavailable! Type a different place:");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
								if ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) || q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1)))){
									System.out.println("Hmm, you can't move there! Type in a different coordinate:");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
							}
							
							array[t][q] = "[" + user2Piece + "]";
							
						// PRINT GRID
							for (int w=0; w<9; w++) {
								for (int k=0; k<9; k++) {
									System.out.print(array[w][k]);
								}
								System.out.println();
							}
					}
				} else if ((s==2) && array[r+1][s-1].contains("[" + user1Piece + "]") && array[r+1][s+1].contains("[" + user1Piece +"]")) {
					if (array[r+2][s+2].contains("[ ]")) {
						System.out.println("Player 2, you get to jump over one of Player 1's piece!");
						points2++;
						t = r+2;
						q = s+2;
						array[t][q]="[" + user2Piece + "]";
						array[r+1][s+1]="[ ]";
						array[r][s]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					}
				} else if ((s==1) && array[r+1][s+1].contains("[" + user1Piece + "]")) {
					if (array[r+2][s+2].contains("[ ]")) {
						System.out.println("Player 2, you get to jump over one of Player 1's pieces!");
						points2++;
						t = r+2;
						q = s+2;
						array[t][q]="[" + user2Piece + "]";
						array[r+1][s+1]="[ ]";
						array[r][s]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					}
				} else if ((s==8) && array[r+1][s-1].contains("[" + user1Piece + "]")) {
					if (array[r+2][s-2].contains("[ ]")) {
						System.out.println("Player 2, you get to jump over one of Player 1's piece!");
						points2++;
						t = r+2;
						q = s-2;
						array[t][q]="[" + user2Piece + "]";
						array[r+1][s-1]="[ ]";
						array[r][s]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					}
				} else if ((s==7) && array[r+1][s-1].contains("[" + user1Piece + "]") && (array[r+1][s+1].contains("[ ]") || array[r+1][s+1].contains("[" + user2Piece +  "]"))) {
					if (array[r+2][s-2].contains("[ ]")) {
						System.out.println("Player 2, you get to jump over one of Player 1's piece!");
						points2++;
						t = r+2;
						q = s-2;
						array[t][q]="[" + user2Piece + "]";
						array[r+1][s-1]="[ ]";
						array[r][s]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					} else {
						array[r][s] = "[ ]";
						
						System.out.println("To which spot do you want to move your piece?");
						String finishPlace2 = input.nextLine();
						finishPlace2 = finishPlace2.toUpperCase();
							
								t = finishPlace2.charAt(0)-65; 
								q = finishPlace2.charAt(1)-48; 
							
							while ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) && q!=(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) || (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48 )) {
								if (finishPlace2.length()>2 || finishPlace2.charAt(0)>72 || finishPlace2.charAt(0)<65|| finishPlace2.charAt(1)>56 || finishPlace2.charAt(1)<48) {
									System.out.println("Oops! Please type in a valid coordinate (i.e. G2, G4, capitals matter!)");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
								if (array[t][q].contains("[" + user1Piece + "]") || array[t][q].contains("[" + user2Piece + "]")) {
									System.out.println("That space is unavailable! Type a different place:");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
								if ((t==(r-1)) || (t!=(r+1) && (q!=(s-1) || q!=(s+1))) || (t!=(r+1) && (q==(s-1) || q==(s+1))) || (t==(r+1) && (q!=(s-1) && q!=(s+1)))){
									System.out.println("Hmm, you can't move there! Type in a different coordinate:");
									finishPlace2 = input.nextLine();
									finishPlace2 = finishPlace2.toUpperCase();
									t = finishPlace2.charAt(0)-65;
									q = finishPlace2.charAt(1)-48;
								}
							}
							
							array[t][q] = "[" + user2Piece + "]";
							
						// PRINT GRID
							for (int w=0; w<9; w++) {
								for (int k=0; k<9; k++) {
									System.out.print(array[w][k]);
								}
								System.out.println();
							}
					}
				} else if ((s==7) && array[r+1][s-1].contains("[" + user1Piece + "]") && array[r+1][s+1].contains("[" + user1Piece + "]")) {
					if (array[r+2][s-2].contains("[ ]")) {
						System.out.println("Player 2, you get to jump over one of Player 1's piece!");
						points2++;
						t = r+2;
						q = s-2;
						array[t][q]="[" + user2Piece + "]";
						array[r+1][s-1]="[ ]";
						array[r][s]="[ ]";
						for (int h=0; h<9; h++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[h][g]);
							}
							System.out.println();
						}
					}					
				}
				
				for (int h=0; h<9; h++) {
					if (array[7][h].contains("[" + user2Piece + "]")) {
						array[7][h] = "[ ]";
						System.out.println("YAY! PLAYER 2! YOU REACHED THE END OF THE BOARD! YOU GET 2 POINTS! CLICK ENTER TO CONTINUE!");
						input.nextLine();
						points2+=2;
						
						for (int k=0; k<9; k++) {
							for (int g=0; g<9; g++) {
								System.out.print(array[k][g]);
							}
							System.out.println();
						}
					}
				}
		}	
				// This for-loop will check the checkerboard at the end of each round
				// if a player has run out of pieces. The game is over once a player
				// has no more pieces to play. Scores will be displayed afterwards
			
				for (int h=0; h<9; h++) {
					for (int g=0; g<9; g++) {
						if (array[h][g].contains("[" + user1Piece + "]")) {
							countForPlayer1Piece++;
						} else if (array[h][g].contains("[" + user2Piece + "]")) {
							countForPlayer2Piece++;
						}
					}
				}
				
				if (countForPlayer1Piece==0 || countForPlayer2Piece==0) {
					System.out.print("GAME OVER! AWWW THE GAME FINISHED ALREADY?! I HOPE YOU HAD FUN! CLICK ENTER TO SEE THE RESULTS:");
					input.nextLine();
					if (countForPlayer1Piece==0) {
						displayResults(points1, points2);
						String results = displayResults(points1, points2);
						System.out.println(results);
						System.out.println("It was nice playing with you! Hope you come back soon to play again! BYE!!!!!!");
						
					} else if (countForPlayer2Piece==0) {
						displayResults(points1, points2);
						String results = displayResults(points1, points2);
						System.out.println(results);
						System.out.println("It was nice playing with you! Hope you come back soon to play again! BYE!!!!!!");
						
					}
				} else {
					System.out.println("Player 1: Which piece do you want to move? (i.e. G8, F7, etc)");
					startPlace = input.nextLine();
					startPlace = startPlace.toUpperCase();
				}	
			}
					
		}
	
	
	public static String displayResults(int player1Points, int player2Points) {
		String results = "";
		if (player1Points>player2Points) {
			results = "Player 1: " + player1Points + "\nPlayer 2: " + player2Points + "\nCONGRATULATIONS, PLAYER 1! YOU WON THE GAME! HURRAH! YIPPEE!\nSORRY PLAYER 2, YOU KIND OF SUCK, MAYBE NEXT TIME OR MAYBE CHECKERS JUST ISN'T YOUR GAME...";
		} else if (player2Points>player1Points) {
			results = "Player 1: " + player1Points + "\nPlayer 2: " + player2Points + "\nCONGRATULATIONS, PLAYER 2! YOU WON THE GAME! HURRAH! YIPPEE!\nSORRY PLAYER 1, YOU WERE KIND OF BAD AT THIS GAME, MAYBE YOU'LL WIN NEXT TIME? OR MAYBE CHECKERS JUST ISN'T YOUR GAME...";
		} else {
			results = "Player 1: " + player1Points + "\nPlayer 2: " + player2Points + "\nHmmm, that's odd... IT'S A TIE! WOWZHA! YOU GUYS WERE EQUALLY GOOD AND BAD AT THIS GAME!";
		}
		return results;
		
	}
	
	public static void welcomeSequence() {
		Scanner input = new Scanner (System.in);
		System.out.print("Welcome to Checkers! Let's have some fun! [CLICK ENTER]");
		input.nextLine();
		System.out.print("Have you ever played checkers before? (Y/N)");
		String yesOrNo = input.nextLine();
			if (yesOrNo.equalsIgnoreCase("Y") || yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("yeah")) {
				System.out.print("Oh great! I still want to show you the rules first! This is a game of checkers unlike any other [CLICK ENTER]");
				input.nextLine();
				rulesOfTheGame();
			} else if (yesOrNo.equalsIgnoreCase("N") || yesOrNo.equalsIgnoreCase("no")) {
				System.out.print("That's okay! Let me teach you! [CLICK ENTER]");
				input.nextLine();
				rulesOfTheGame();
			} else {
				System.out.print("Let's get to the rules of the game! [CLICK ENTER]");
				input.nextLine();
				rulesOfTheGame();
			}
	}
	
	public static void rulesOfTheGame() {
		Scanner input = new Scanner (System.in);
		System.out.print("1.) This is a game between two players, so you will need to have two users before starting [CLICK ENTER]");
		input.nextLine();
		System.out.print("2.) Choose a playing piece to play with. You can be anything, so long as it's ONE character (i.e. 'D', '?', '+') [CLICK ENTER]");
		input.nextLine();
		System.out.print("3.) Take turns moving your pieces around on the board. You can ONLY MOVE ONE BOX DIAGONALLY AT A TIME. [CLICK ENTER]");
		input.nextLine();
		System.out.print("4.) The goal of the game is to get the most points by jumping over as many pieces as possible of the other player! [CLICK ENTER]");
		input.nextLine();
		System.out.print("5.) The computer will automatically jump over a player's piece if it sees the opportunity, don't try stopping it! [CLICK ENTER]");
		input.nextLine();
		System.out.print("6.) You get ONE POINT for every jump. You get TWO POINTS for reaching the end of the board! [CLICK ENTER]");
		input.nextLine();
		System.out.print("7.) The game is over once a player's pieces disappear completely. The points are displayed at the end and the winner is declared! [CLICK ENTER]");
		input.nextLine(); 
		System.out.print("Okay, I think that's enough for now. Let's start the game! Please CLICK ENTER to continue:");
		input.nextLine();
	}
	
	
}
