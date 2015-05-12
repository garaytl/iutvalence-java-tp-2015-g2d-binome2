package fr.iutvalence.blancarayt.bataillenavale;

import java.util.Scanner;

/**
 * Battleship's game.
 *
 * @author léasilé
 * @version 2.0
 */
public class Game
{
	/**
	 * Player 1.
	 */
	private Player player1;

	/**
	 * Player 2.
	 */
	private Player player2;

	public void start()
	{
		// TODO sysout de la grid
		Boat aircraftCarrier1 = new AircraftCarrier();
		askBoat(aircraftCarrier1, player1);
		Boat submarine1 = new Submarine();
		askBoat(submarine1, player1);
		Boat submarine2 = new Submarine();
		askBoat(submarine2, player1);
		Boat battleCruiser1 = new BattleCruiser();
		askBoat(battleCruiser1, player1);
		Boat destroyer1 = new Destroyer();
		askBoat(destroyer1, player1);
		
		Boat aircraftCarrier2 = new AircraftCarrier();
		askBoat(aircraftCarrier2, player2);
		Boat submarine3 = new Submarine();
		askBoat(submarine3, player2);
		Boat submarine4 = new Submarine();
		askBoat(submarine4, player2);
		Boat battleCruiser2 = new BattleCruiser();
		askBoat(battleCruiser2, player2);
		Boat destroyer3 = new Destroyer();
		askBoat(destroyer3, player2);
		attack(player1, player2);

	}

	private Direction stringToDirection(String dir) throws UnvalidDirection
	{
		Direction direction;
		String dirUp = dir.toUpperCase().trim();
		switch (dirUp)
		{
		case "LEFT":
			direction = Direction.LEFT;
			break;
		case "RIGHT":
			direction = Direction.RIGHT;
			break;
		case "UP":
			direction = Direction.UP;
			break;
		case "DOWN":
			direction = Direction.DOWN;
			break;
		default:
			throw new UnvalidDirection("The direction " + dir
					+ " is invalid please type LEFT, RIGHT, UP or DOWN");
		}
		return direction;
	}
	

	private String attack(Player attackPlayer, Player hittedPlayer){
		Scanner sc = new Scanner(System.in);
		System.out.println(attackPlayer+" ! Tip the coordonee of your attack ! (Letter and number)");
		String coor = sc.next();
		String coorUp = coor.toUpperCase().trim();
		String strx= coorUp.substring(0,1);
		String stry=null;
		boolean yValid=true;
		do{
			yValid=true;
			if (coorUp.length()==2){
				stry= coorUp.substring(1,2);
			}
			else if (coorUp.length()==3){
				stry= coorUp.substring(1,3);
			}
			else 
				yValid=false;
		}while(!yValid);
		int y = Integer.parseInt(stry);
		int x = strx.toCharArray()[0];
		x=x-1;
		y=y-1;
		String result;
		if (hittedPlayer.board.cases[x][y].boat!=null)
		{
			hittedPlayer.board.cases[x][y].boat.hitted();
			if (hittedPlayer.board.cases[x][y].boat.condition = false)
			{
				result = "Coulé !";
			}
			else
			{
				result = "Touché !";
			}
			
		}
		else
		{
			result = "A l'eau..";
		}
		hittedPlayer.board.cases[x][y].hit();
		return result;
	}

	
	private void askBoat(Boat boat, Player player)
	{
		Scanner sc = new Scanner(System.in);
		boolean correctPlace = true;
		do
		{
			correctPlace = true;
			System.out.println("tip the depart x of "+player.getPseudo()+"'s "+ boat + " :");
			int x = sc.nextInt()-1;
			System.out.println("tip the depart y of "+player.getPseudo()+"'s " + boat + " :");
			int y = sc.nextInt()-1;
			boolean correctDir = true;
			Direction direction = null;
			do
			{
				correctDir = true;
				System.out.println("tip the direction of "+player.getPseudo()+"'s " + boat + " :");
				String Dir = sc.next();
				try
				{
					direction = stringToDirection(Dir);
				}
				catch (UnvalidDirection e)
				{
					System.err.println(e.getMessage());
					correctDir = false;
				}
			} while (!correctDir);
			try
			{
				player.addBoat(x, y, direction, boat);
			} 
			catch (OutOfTheBoard | Occupated f)
			{
				System.err.println(f.getMessage());
				correctPlace = false;
			}
		} while (!correctPlace);
		System.out.println(player.board.toString());
	}

	public Game(String pseudo1, String pseudo2)
	{
		this.player1 = new Player(pseudo1);
		this.player2 = new Player(pseudo2);

	}
}
