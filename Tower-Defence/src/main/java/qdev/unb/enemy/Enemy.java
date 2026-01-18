package qdev.unb.enemy;

import qdev.unb.utils.Coordinate;
import qdev.unb.utils.PathPosition;

import java.awt.Graphics;
import java.awt.Image;

/**
 * This is an abstract superclass for an enemy in the game
 */
abstract public class Enemy
{
	/* instance variables */
	protected PathPosition position;	// holds current position of enemy
	protected Image enemy;              // holds image of enemy
	protected String imageName;
	protected int anchorX;				// shifts position on x axis
	protected int anchorY;				// shifts position on y axis
	protected double velocity; 			// increases or decreases advance speed

	/**
	 * Advances the position of the enemy
	 *
	 */
	public void advance()
	{
		position.advance(10 + velocity);	// advances position 10 units plus velocity
	}

	/**
	 * Draws the enemy to the screen
	 *
	 * @param g
	 */
	public void draw(Graphics g)
	{
		// Draws qdev.unb.entities.Enemy object
		Coordinate c = position.getCoordinate();
		g.drawImage(enemy, c.x + anchorX, c.y + anchorY, null);

		// Draws dot on qdev.unb.entities.Enemy's (x, y) coordinates
		//g.setColor(Color.WHITE);
		//g.fillOval(c.x, c.y, 5, 5);
	}

	public Coordinate setCoordinate(int px, int py) {
		return position.getCoordinate();
	}

	/**
	 *
	 * @return
	 */
	public PathPosition getPosition()
	{
		return position;
	}

	public  String getImageName()
	{
		return this.imageName;
	}
}
