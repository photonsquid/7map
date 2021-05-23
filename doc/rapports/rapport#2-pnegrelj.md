# Rapport personnel - projet TOB - 7map

| Philippe Négrel-Jerzy |
|-----------------------|
| Groupe KL-05          |
| Projet 7map           |

## Travaux réalisés à ce jour
- gestion des réunions et des tâches au sein de la "team rendering"

- Amélioration des méthodes de calcul de projections entre référentiels

- Ajout d'une API permettant d'utiliser ImGui au sein du moteur de rendu

- Quelques améliorations d'optimisation majeures (performances détaillées ci-dessous)

  |                      | GTX 950M 4 Go dédiés | RTX 2060 6 Go dédiés |
  | -------------------- | -------------------- | -------------------- |
  | ancienne performance | 800 fps*             | 1500 fps*            |
  | nouvelle performance | 1700 fps*            | 3600 fps*            |

  *frames per second

  > Fonctionnalités gérées actuellement : 
  >
  > - affichage d'objets en 3D
  > - déplacement de caméra
  > - affichage de textures (avec transparence)
  > - shading basique en glsl
  > - gestion de menus, boutons, champs de texte et autres éléments d'interfaces utilisateur

- administration du [repository github](https://github.com/7map/7map)