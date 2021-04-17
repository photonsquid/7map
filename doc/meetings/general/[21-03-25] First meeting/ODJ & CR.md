# Ordre du jour et compte rendu de la première réunion du projet 7map

|    Réunion    |                                       1ère réunion du projet 7map                                       |
| :-----------: | :-----------------------------------------------------------------------------------------------------: |
|     Lieu      |                                                chez Seb                                                 |
| Date et durée |                                          25/03/2021 13h-15h30                                           |
|  Présent.e.s  | Philippe Negrel-Jerzy, Félix Parain, Selma Oujid, Hamid Oukhnini, Mohamed M'Hand Ouammi, Sébastien Pont |

Animation : Philippe

Prise de note : Sébastien

## Sommaire

- [Ordre du jour et compte rendu de la première réunion du projet 7map](#ordre-du-jour-et-compte-rendu-de-la-première-réunion-du-projet-7map)
  - [Sommaire](#sommaire)
  - [Ordre du jour](#ordre-du-jour)
  - [Compte rendu](#compte-rendu)
    - [Dates limites](#dates-limites)
    - [Tâches prioritaires et répartition](#tâches-prioritaires-et-répartition)
    - [Afficher la carte](#afficher-la-carte)
    - [Enregistrer les données](#enregistrer-les-données)
      - [Comment sont stockées les données ?](#comment-sont-stockées-les-données-)

## Ordre du jour

1. Définir un calendrier global avec jalons
2. Définir les tâches prioritaires et les répartir
3. Commencer à faire un diagramme de classe

## Compte rendu

### Dates limites

- Une réunion générale est fixée toutes les deux semaines :
  - 08 avril
  - 22 avril
  - 06 mai
  - 20 mai

### Tâches prioritaires et répartition

Les deux premières tâches à réaliser sont :

- Afficher la carte
- Enregistrer les données

Pour cela on crée deux équipes :

- @teamData
  - @selmaoujid
  - @FaislX
  - @seba1204
- @teamRendering
  - @kingussopp
  - @Mmzhk21
  - @l3alr0g

### Afficher la carte

Librairie : OpenGL

### Enregistrer les données

- Base de données :
  - MongoDB (non)
  - SQL (oui)

#### Comment sont stockées les données ?

 Principe de nœuds / vecteurs :
un "tableau" qui enregistre une liste de points / coordonnées
un tableau qui relie ces points en les caractérisant

- on part en fait sur le format GDF 5.0

  - il faut une personne qui s'occupe de lire ce format et le transformer en un objet défini et utilisable par tout le monde

ex :
P1 ; P2 : Route maritime +++
