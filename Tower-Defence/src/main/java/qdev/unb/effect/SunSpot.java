package qdev.unb.effect;

import qdev.unb.utils.Coordinate;
import qdev.unb.utils.ImageLoader;

/**
 * qdev.unb.Game effect when the Black hole attacks
 */
public class SunSpot extends Effect
{
	public SunSpot(Coordinate pos, Coordinate target)
	{
		// Loads star dust image
		ImageLoader loader = ImageLoader.getLoader();
		this.picture = loader.getImage("sun_spot.png");
		
		// X and Y position of qdev.unb.effect.Effect
		this.posX = pos.x;
		this.posY = pos.y;		
		
		// X and Y position of target enemy
		this.velocityX = target.x - this.posX;
		this.velocityY = target.y - this.posY;
		
		// Sets time to 0
		this.ageInSeconds = 0;
	}	
}
