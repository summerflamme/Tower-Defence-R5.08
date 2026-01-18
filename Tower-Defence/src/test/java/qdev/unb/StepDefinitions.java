package qdev.unb;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qdev.unb.effect.Flamme;
import qdev.unb.effect.StarDust;
import qdev.unb.effect.SunSpot;
import qdev.unb.tower.BlackHole;
import qdev.unb.tower.Sun;
import qdev.unb.tower.Tower;
import qdev.unb.enemy.*;
import qdev.unb.utils.Coordinate;
import qdev.unb.utils.PathPoints;
import qdev.unb.utils.PathPosition;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    Game game;
    Alien alien;
    Comet comet;
    PathPoints line;
    Coordinate coord;
    Tower tower;


    ClassLoader myLoader = this.getClass().getClassLoader();
    InputStream pointStream = myLoader.getResourceAsStream("path_1.txt");
    Scanner s = new Scanner (pointStream);

    /*
    ======================================================
    Vérifier que l'alien hérite bien de la classe qdev.unb.entities.Enemy
    ======================================================
     */
    @Given("Un alien est créer")
    public void unAlienEstCreer() {
        // Write code here that turns the phrase above into concrete actions
        line = new PathPoints(s);
        alien = new Alien(line.getStart());
    }

    @When("Je vérifie son type")
    public void jeVerifieSonType() {
        // Write code here that turns the phrase above into concrete actions
        assertThat(alien).isInstanceOf(Alien.class);
    }

    @Then("l'alien est une instance de Enemy")
    public void lAlienEstUneInstanceDeEnemy() {
        // Write code here that turns the phrase above into concrete actions
        assertThat(alien).isInstanceOf(Enemy.class);
    }


    /*
   ======================================================
   Vérifier la position de spawn d'un Alien
   ======================================================
    */
    @When("Je vérifie la position de départ de l'alien")
    public void jeVérifieLaPositionDeDépartDeLAlien() {
        // Write code here that turns the phrase above into concrete actions
        coord =alien.getPosition().getCoordinate();
    }

    @Then("L'alien est bien à la position {int} {int}")
    public void lEnemyEstBienALaPosition(int px, int py) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(coord.x).isEqualTo(px);
        assertThat(coord.y).isEqualTo(py);
    }

    /*

    /*
   =====================================================
    l'alien arrive au soleil, le joueur perd 1 vie
   =====================================================
    */

    @When("L'alien arrive en {int} {int}")
    public void lAlienArriveEn(int px, int py) {
        coord = alien.setCoordinate(px, py);
        game.livesCounter --;
    }


    @Then("Le joueur à {int} vies restantes")
    public void leJoueurÀViesRestantes(int life) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(game.livesCounter).isEqualTo(life);
    }

    /*
   =====================================================
    Vérifier que la comet hérite bien de la classe qdev.unb.entities.Enemy
   =====================================================
    */

    @Given("Une comète est créer")
    public void uneComèteEstCréer() {
        // Write code here that turns the phrase above into concrete actions
        line = new PathPoints(s);
        comet = new Comet(line.getStart());
    }

    @When("Je vérifie la position de départ de la comet")
    public void jeVérifieLaPositionDeDépartDeLaComet() {
        // Write code here that turns the phrase above into concrete actions
        coord =comet.getPosition().getCoordinate();
    }
    @Then("La comète est bien à la position {int} {int}:")
    public void laComèteEstBienÀLaPosition(int px, int py) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(coord.x).isEqualTo(px);
        assertThat(coord.y).isEqualTo(py);
    }

    /*
   =====================================================
    Vérifier la position de spawn d'une comète
   =====================================================
    */

    @When("Je vérifie le type de la comet")
    public void jeVérifieLeTypeDeLaComet() {
        // Write code here that turns the phrase above into concrete actions
        assertThat(comet).isInstanceOf(Comet.class);
    }

    @Then("la comet hérite bien de la classe Enemy")
    public void laCometHériteBienDeLaClasseEnemy() {
        // Write code here that turns the phrase above into concrete actions
        assertThat(comet).isInstanceOf(Comet.class);
    }


    /*
       =====================================================
        Easy mode
       =====================================================
        */
    @Given("La partie est lancée en mode {string}")
    public void laPartieEstLancéeEnMode(String mode) {
        game = new Game(false);
        game.initializeDifficulty(mode);
    }

    @Then("Le joueur a {int} vies")
    public void leJoueurAVies(int expectedLives) {
        assertThat(game.livesCounter).isEqualTo(expectedLives);
    }

    @And("Le joueur a {int} de monnaie")
    public void leJoueurADeMonnaie(int expectedMoney) {
        assertThat(game.scoreCounter).isEqualTo(expectedMoney);
    }

    @And("Il faut stopper {int} ennemis pour gagner")
    public void ilFautStopperEnnemisPourGagner(int expectedKillsToReach) {
        assertThat(game.killsToReach).isEqualTo(expectedKillsToReach);
    }


/*
       =====================================================
        Les 2 tests ont été réalisés avec l'aide de copilot
       =====================================================
 */

/*
       =====================================================
         La tour <type> tire quand l'ennemi est à portée
       =====================================================
        */

    @Given("Une partie headless pour les tests")
    public void unePartieHeadlessPourLesTests() {
        // Write code here that turns the phrase above into concrete actions
        game = new Game(false);
        game.enemies = new LinkedList<>();
        game.effects = new LinkedList<>();
    }


    @And("Une tour de type {string} est placée en {int} {int}")
    public void uneTourDeTypeEstPlaceeEn(String type, int tx, int ty) {
        Coordinate pos = new Coordinate(tx, ty);
        switch (type) {
            case "BlackHole" -> tower = new BlackHole(pos);
            case "Sun" -> tower = new Sun(pos);
            case "Missile" -> tower = new BlackHole.Missile(pos);
            default -> throw new IllegalArgumentException("Type de tour inconnu: " + type);
        }
        tower.setTimeSinceLastFire(1000.0);
    }

    @And("Un ennemi est placé en {int} {int}")
    public void unEnnemiEstPlaceEn(int ex, int ey) {
        // crée une liste contenant la coordonnée demandée (PathPosition attend une List<Coordinate>)
        List<Coordinate> coords = java.util.Collections.singletonList(new Coordinate(ex, ey));
        PathPosition start = new PathPosition(coords);
        Alien a = new Alien(start);

        if (game == null) {
            game = new Game(false);
            game.enemies = new java.util.LinkedList<>();
            game.effects = new java.util.LinkedList<>();
        } else {
            if (game.enemies == null) game.enemies = new java.util.LinkedList<>();
            if (game.effects == null) game.effects = new java.util.LinkedList<>();
        }

        game.enemies.add(a);
    }
    @When("La tour interagit avec deltaTime {double}")
    public void laTourInteragitAvecDeltaTime(double dt) {
        if (tower == null) {
            throw new IllegalStateException("La tour est null dans le step avant l'interaction");
        }
        if (game == null) {
            throw new IllegalStateException("L'objet game est null dans le step avant l'interaction");
        }
        if (game.enemies == null || game.enemies.isEmpty()) {
            throw new IllegalStateException("Pas d'ennemi présent dans game.enemies au moment de l'interaction");
        }

        tower.setTimeSinceLastFire(1000.0);

        tower.interact(game, dt);
    }

    @Then("Un effet de type {string} est créé")
    public void unEffetDeTypeEstCree(String effectName) {
        assertThat(game.effects).hasSize(1);
        Object eff = game.effects.get(0);
        switch (effectName) {
            case "StarDust" -> assertThat(eff).isInstanceOf(StarDust.class);
            case "SunSpot" -> assertThat(eff).isInstanceOf(SunSpot.class);
            case "Flamme" -> assertThat(eff).isInstanceOf(Flamme.class);
            default -> throw new IllegalArgumentException("Type d'effet inconnu: " + effectName);
        }
    }
    /*
       =====================================================
         La tour <type> ne tire pas quand l'ennemi est hors portée
       =====================================================
        */


    @Then("Aucun effet n'est créé")
    public void aucunEffetNEstCréé() {
            assertThat(game.effects).isEmpty();
    }
}



