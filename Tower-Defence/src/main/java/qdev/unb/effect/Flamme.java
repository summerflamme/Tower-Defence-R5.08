package qdev.unb.effect;

import qdev.unb.utils.Coordinate;
import qdev.unb.utils.ImageLoader;

/**
 * qdev.unb.Game effect when the qdev.unb.tower.BlackHole.Missile attacks
 */
public class Flamme extends Effect {

    public Flamme(Coordinate pos, Coordinate target) {
        // Loads flamme image
        ImageLoader loader = ImageLoader.getLoader();
        this.picture = loader.getImage("flamme.png");

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
