# tests réalisés avec l'aide de copilot

Feature: Vérifier le comportement des tours

  Scenario Outline: La tour <type> tire quand l'ennemi est à portée
    Given Une partie headless pour les tests
    And Une tour de type "<type>" est placée en <tx> <ty>
    And Un ennemi est placé en <ex> <ey>
    When La tour interagit avec deltaTime <dt>
    Then Un effet de type "<effect>" est créé

    Examples:
      | type      | effect    | tx  | ty  | ex  | ey  | dt  |
      | BlackHole | StarDust  | 100 | 100 | 150 | 100 | 1.0 |
      | Sun       | SunSpot   | 100 | 100 | 180 | 100 | 0.5 |
      | Missile   | Flamme    | 100 | 100 | 110 | 100 | 1.0 |

  Scenario Outline: La tour <type> ne tire pas quand l'ennemi est hors portée
    Given Une partie headless pour les tests
    And Une tour de type "<type>" est placée en <tx> <ty>
    And Un ennemi est placé en <ex_out> <ey_out>
    When La tour interagit avec deltaTime <dt>
    Then Aucun effet n'est créé

    Examples:
      | type      |tx  | ty  |  ex_out | ey_out | dt  |
      | BlackHole |100 | 100 |  300    | 100    | 1.0 |
      | Sun       |100 | 100 |  300    | 100    | 0.5 |
      | Missile   |100 | 100 |  200    | 100    | 1.0 |