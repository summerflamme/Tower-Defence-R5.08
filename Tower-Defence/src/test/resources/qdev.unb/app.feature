Feature:
  Scenario: Vérifier que l'alien hérite bien de la classe Enemy
    Given Un alien est créer
    When  Je vérifie son type
    Then  l'alien est une instance de Enemy

  Scenario: Vérifier la position de spawn d'un Alien
    Given Un alien est créer
    When  Je vérifie la position de départ de l'alien
    Then  L'alien est bien à la position 2 302

  Scenario: l'alien arrive au soleil, le joueur perd 1 vie
    Given Un alien est créer
    And   La partie est lancée en mode "Normal"
    When  L'alien arrive en 595 438
    Then  Le joueur à 9 vies restantes

  Scenario: Vérifier que la comet hérite bien de la classe Enemy
    Given Une comète est créer
    When  Je vérifie le type de la comet
    Then  la comet hérite bien de la classe Enemy

  Scenario: Vérifier la position de spawn d'une comète
    Given Une comète est créer
    When  Je vérifie la position de départ de la comet
    Then  La comète est bien à la position 2 302:

  Scenario: Partie lancée en Easy
    Given La partie est lancée en mode "Easy"
    Then Le joueur a 20 vies
    And Le joueur a 400 de monnaie
    And Il faut stopper 250 ennemis pour gagner

  Scenario: Partie lancée en Normal
    Given La partie est lancée en mode "Normal"
    Then Le joueur a 10 vies
    And Le joueur a 200 de monnaie
    And Il faut stopper 500 ennemis pour gagner


  Scenario: Partie lancée en Hard
    Given La partie est lancée en mode "Hard"
    Then Le joueur a 5 vies
    And Le joueur a 100 de monnaie
