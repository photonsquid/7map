# Script présentation partie rendering

## Moteur Spinel

- Composé d'une classe principale Engine, à travers laquelle l'application communique avec le moteur
- expliciter le rôle des différents paquets : 
  - éléments = objets 3D de tous types
  - gfx = classes en lien avec le rendu des objets
  - utils = classes utilitaires (chargement de fichiers, etc...)
  - scheduling = évènements et exécution différée
  - math = classes mathématiques pour les calculs graphiques
- Window et Input gèrent respectivement les fenetres ouvertes et les entrès clavier/souris

## Nœuds et hiérarchie des objets

- Le moteur utilise un système d'arborescence pour afficher des objets en 3D
- Il existe une unique racine de la scène 3D, le SceneRenderer, qui est une Noeud racine
- Les Nodes sont des noeuds qui en plus d'avoir des enfants comme RootNode, on un parent
- Les GeomNodes héritent des attributs et méthodes des Nodes, mais possèdent en outre des coordonnées dans l'espace et une orientation
- Enfin les items possèdent un maillage associé et une échelle, ce qui en fait l'unique objet de cette structure réellement visible dans la scène
- on reviendra sur le paquet gui par la suite

## Evènements

- Explication sur le threading : le moteur ne peut pas s'exécuter sur le même thread que l'application pour des raisons évidentes d'indépendance entre calculs et affichage

- Toute opération réalisée en parallèle de l'affichage doit être programmée à l'aide évènements - cas d'utilisation à donner (pour une tache qui s'exécute en continu, on l'associe à un évènement toujours actif, l'EmptyEvent, pour une tâche qui s'exécute lorsque l'on appuie sur la touche espace, on lui associe un ButtonEvent, etc.)

- L'architecture est détaillée ici : *présenter le diagramme de classes simplifié*

  ​	Note : le TaskMgr (gestionnaire de tâches) utilise des objets de type Task pour "ranger" les taches

- Les Events peuvent être levés au sein du moteur comme des exception en java. *Explication du diagramme de séquence* 

## Techniques de rendu

### diapo 1

- Pour rentrer plus en détail dans les objets 3D utilisés ici : *détailler à quoi correspondent un vector3f, un vertex, une primitive, un mesh et un Item*

### diapo 2

- Voici un schéma de la pipeline, ou chaine de rendu qui permet d'afficher un objet à l'écran :
  - on part de données de position des vertex et de données sur les primitives, puis on en déduit la première figure visible sur le diapo
  - on applique un **vertex shader**, (*expliquer ce qu'est un shader, cf vidéo*) qui permet altérer les coordonnées des vertex, on obtient alors la figure centrale, déformée au besoin
  - on ajoute la texture a travers un **fragment shader**, assurant la coloration des pixels de chaque face selon une texture ou une couleur choisie, puis on affiche le tout à l'écran

## Interface utilisateur

- Enfin, aucun moteur 3D n'est complet sans une api d'affichage de menus et de texte, c'est pourquoi on a choisi d'intégrer dear ImGui (prononcer "dier aiemgyui") pour pouvoir facilement intéragir avec l'utilisateur
- expliciter le schéma d'arborescence
