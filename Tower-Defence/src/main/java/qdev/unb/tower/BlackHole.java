package qdev.unb.tower;

import qdev.unb.*;
import qdev.unb.effect.Flamme;
import qdev.unb.effect.StarDust;
import qdev.unb.enemy.Enemy;
import qdev.unb.utils.Coordinate;
import qdev.unb.utils.ImageLoader;

import java.util.List;

/**
 * This class creates a single blackhole tower
 */
public class BlackHole extends Tower
{
	/**
	 * Constructor
	 */
	public BlackHole(Coordinate pos)
	{
		ImageLoader loader = ImageLoader.getLoader();
		this.tower = loader.getImage("blackhole.png");
		this.position = pos;
		this.anchorX = -40;
		this.anchorY = -40;
	}
	
	/**
	 * 
	 */
	public void interact(Game game, double deltaTime)
	{	// tracks time that effect has existed
		timeSinceLastFire += deltaTime;
		
		// if time less than 1.5 seconds, don't interact
		if(timeSinceLastFire < 1)
			return;
		
		List<Enemy> enemies = game.enemies; // new list of enemies
		
		// Gives position of an enemy in enemy list
		for(Enemy e: enemies)
		{	
			
			// holds position of enemy
			Coordinate enemyPos = e.getPosition().getCoordinate();

			// Compute distance of enemy to tower
			double dx, dy, dist;	// change in x, y, and total distance
			
			// calculates change in x and y position 
			dx = enemyPos.x - position.x; // x position of enemy - tower
			dy = enemyPos.y - position.y; // y position of enemy - tower
		
			// use Pythagorean theorem to calculate distance
			dist = Math.sqrt((dx*dx) + (dy*dy));
			
			// holds position of effect
			Coordinate pos = new Coordinate(position.x, position.y);	
			
			// if enemy is in range, fire salt
			if(dist < 80)
			{	StarDust stardust = new StarDust(pos, enemyPos);
				game.effects.add(stardust);
				timeSinceLastFire = 0;
				return;
			}	
		} 
	}

	/**
	 * This class creates a single missile tower
	 */
	public static class Missile extends Tower {

		/**
		 * Constructor
		 */
		public Missile(Coordinate pos) {
			ImageLoader loader = ImageLoader.getLoader();
			this.tower = loader.getImage("missile.png");
			this.position = pos;
			this.anchorX = -40;
			this.anchorY = -40;
		}

		/**
		 *
		 */
		public void interact(Game game, double deltaTime) {	// tracks time that effect has existed
			timeSinceLastFire += deltaTime;

			// if time less than 1.5 seconds, don't interact
			if (timeSinceLastFire < 1) {
				return;
			}

			List<Enemy> enemies = game.enemies; // new list of enemies

			// Gives position of an enemy in enemy list
			for (Enemy e : enemies) {

				// holds position of enemy
				Coordinate enemyPos = e.getPosition().getCoordinate();

				// Compute distance of enemy to tower
				double dx, dy, dist;	// change in x, y, and total distance

				// calculates change in x and y position
				dx = enemyPos.x - position.x; // x position of enemy - tower
				dy = enemyPos.y - position.y; // y position of enemy - tower

				// use Pythagorean theorem to calculate distance
				dist = Math.sqrt((dx * dx) + (dy * dy));

				// holds position of effect
				Coordinate pos = new Coordinate(position.x, position.y);

				// if enemy is in range, fire flamme
				if (dist < 20) {
					Flamme flamme = new Flamme(pos, enemyPos);
					game.effects.add(flamme);
					timeSinceLastFire = 0;
					return;
				}
			}
		}
	}
}
