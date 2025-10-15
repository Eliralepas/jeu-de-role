# Donjon&Dragon Project ![Statut](https://img.shields.io/badge/Statut-Terminé-success)

Pour notre projet en programmation, 2e année de BUT 1, nous devions développer un jeu, **Donjon et Dragon**, jouable sur le terminal.
Le jeu permet la création de donjons, de personnages et d'équipements, et se joue tour par tour.

## Affichage

```console
********************************************************************************
Donjon 2:
                                    Caelynn (Elfe magicienne)              

********************************************************************************
Tour 3:
   And   Andry (Naine Clerc, 15/16)
   X(    Demogorgon (25/45)
   Dar   Darvin (Humain Guerrier, 13/20)
-> Cae   Caelynn (Elfe Magicienne, 10/12)
   X^    Dragon bleu (35/50)
   Alt   Alton (Halfelin Roublard, 14/16)

      A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W
   *-----------------------------------------------------------------------*
1  |  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  |
2  |  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  |
3  |  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  |
4  |  .  .  .  .  X^ .  .  .  .  .  *  .  .  .  .  .  .  .  .  .  .  .  .  |
5  |  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  |
6  |  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  |
7  |  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  *  .  .  .  .  .  .  |
8  |  .  .  .  .  .  .  .  .  . [ ] .  .  .  .  .  .  .  .  .  .  .  .  .  |
9  |  .  .  .  .  .  .  .  .  . [ ][ ] .  .  .  .  .  .  .  .  .  .  .  .  |
10 |  .  .  .  .  .  .  .  .  .  . [ ] . Alt .  .  .  .  .  .  .  .  .  .  |
11 |  .  .  .  .  .  .  .  .  .  . [ ] .  .  .  .  .  .  .  .  .  .  .  .  |
12 |  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  |
13 |  .  .  .  .  .  .  .  .  .  .  . Cae .  .  .  .  .  .  .  .  .  .  .  |
14 |  .  .  .  .  .  .  .  .  .  .  . And .  .  .  X( .  .  .  *  .  .  .  |
15 |  .  .  .  .  *  .  .  .  .  *  .  .  .  .  .  .  .  .  .  .  .  .  .  |
16 |  .  .  .  .  .  .  .  .  .  .  .  .  . Dar .  .  .  .  .  .  .  .  .  |
17 |  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  |
18 |  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  |
   *-----------------------------------------------------------------------*
    * Equipement   |   [ ] Obstacle  |

Caelynn
  Vie : 10/12
  Armure: aucune
  Arme: fronde (degat: 1d4, portee: 6)
  Inventaire: [1] baton
  Force: 8
  Dextérité: 16
  Vitesse: 12

Caelynn il vous reste 2 actions que souhaitez vous faire ?
  - laisser le maître du jeu commenter l'action précédente 
  - commenter action précédente 
  - attaquer
  - se déplacer 
  - s'équiper 
```

```console
  $ att P14
  Lancer un dé de 20 (appuyer sur n'importe quelle touche)
  $
  Vous avez fait 13
  Votre attaque est de 13+16(Dextérité)=29.
  Votre attaque perce l'armure du Demogorgon (18).
  Lancer un dé de 4 pour infliger des dégâts (appuyer sur n'importe quelle touche)
  $
  Le Demogorgon subit 3 dégâts!
  Il lui reste 22 PV.
```
## Technologies utilisées

- **Langage** : Java
- **IDE** : IntelliJ IDEA

## Installation et execution 
1. **Cloner le dépôt :**
    ```bash
    git clone https://github.com/mon-utilisateur/doonjons-et-dragons.git
    cd doonjons-et-dragons/out/production/DOOnjons_et_Dragons
2. Installer une version de Java supérieure à 23:
    [Java Archive Downloads](https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html)

3. Lancer le jeu :
    ```bash
    java Main

## Fonctionnalités principales
- Génération d'un donjon et des joueurs
- Gestion des tours
- Déplacements, attaques, sorts, équipements
- Interventions du maître du jeu
- Choix de la langue (Anglais/Français)







