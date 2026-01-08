
/**
 * Game effect when the Missile attacks
 */
public class Flamme extends Effect {

    public Flamme(Coordinate pos, Coordinate target) {
        // Loads flamme image
        ImageLoader loader = ImageLoader.getLoader();
        this.picture = loader.getImage("flamme.png");

        // X and Y position of Effect
        this.posX = pos.x;
        this.posY = pos.y;

        // X and Y position of target enemy
        this.velocityX = target.x - this.posX;
        this.velocityY = target.y - this.posY;

        // Sets time to 0
        this.ageInSeconds = 0;
    }
}
