# Système de stockage de données cartographiques avec pathfinding

Ce projet consiste à réaliser une application capable de stocker et d'afficher des données cartographiques de tout type (relief, axes de communication, cours d'eau, océans, bâtiments, etc.)

L'interface utilisateur sera assez intuitive, idéalement en 3d, mais une représentation 2D est déjà amplement suffisante, avec la carte représentée sous forme d'un plan que l'on peut déplacer à l'aide de la souris. Il sera possible de zoomer/dézoomer afin d'avoir une vision plus globale. Une barre de recherche permettant de localiser un élément géographique à partir de son nom ou de ses coordonnées sera également disponible.

Il sera également possible de rechercher le chemin le plus court entre deux points de la carte, en empruntant des moyens de transports au préalables sélectionnés par l'utilisateur.

L'enregistrement des données en RAM se fera selon un système communément utilisé en 3d : l'ensemble des points - ou nœuds - affichés sur la carte sera stocké individuellement dans des tableaux prévus à cet effet, avec leur rôle respectif. Séparément on stockera les liens entre les points (les "lignes" qui les relient), ainsi que le type de lien (courbe de niveau, axe routier, berge, sentier, etc.). Chaque point permettra de connaitre les liens qui l'utilisent, et chaque lien fera également référence aux points qu'il lie. Ainsi, la recherche d'un trajet d'un point A à un point B se fera en 3 étapes :

- recherche du point d'un axe routier le plus proche de A et création d'un nœud temporaire "A" à ces coordonnées

- recherche du point d'un axe routier le plus proche de B et création d'un nœud temporaire "B" à ces coordonnées

- calcul du chemin le plus court de nœud en nœud en appliquant l'algorithme de pathfinding A* ou Dijkstra (on fera des tests d'efficacité) entre les nœuds "A" et "B"

Il est important de noter que chaque lien caractérisant un axe de communication se verra associer des données telles que le trafic en temps réel, le taux de fréquentation (sentier - autoroute), les véhicules autorisés, ainsi que les éventuelles restrictions sur la vitesse, le sens, etc.

Ces données permettrons d'optimiser la vitesse des trajets de façon plus intelligente, afin de proposer des résultats toujours plus pertinent à l'utilisateur.

Enfin, il sera possible sur le long terme que l'application établisse un profil de valeurs (telles que la vitesse moyenne, le type de véhicule préféré, etc.) propre à chaque utilisateur qui ouvrira la porte à une prédiction des temps de déplacement plus personnalisée.
