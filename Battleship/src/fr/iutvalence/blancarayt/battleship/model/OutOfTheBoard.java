package fr.iutvalence.blancarayt.battleship.model;

/**
 * Battleship's placement boat exception.
 *
 * @author léasilé
 * @version 2.0
 */

public final class OutOfTheBoard extends Exception
{
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * OutOfTheBoard constructor.
	 * 
	 * @param message
	 */
	public OutOfTheBoard(String message)
	{
		super("Coodonate out of the board : " + message);
	}
}
