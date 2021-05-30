# Manuel utilisateur de l'application 7map

## Sommaire

- [Manuel utilisateur de l'application 7map](#manuel-utilisateur-de-lapplication-7map)
  - [Sommaire](#sommaire)
  - [Graphique](#graphique)
    - [Lancer l'application](#lancer-lapplication)
    - [Déplacer la carte](#déplacer-la-carte)
  - [Ajouter une carte](#ajouter-une-carte)
  - [Option du CLI](#option-du-cli)
  - [Éditer les styles de la carte](#éditer-les-styles-de-la-carte)

## Graphique

### Lancer l'application

Prérequis :

- Avoir [java](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html) d'installé
- Avoir le fichier source [`7map_0.3_pre.jar`](https://github.com/7map/7map/releases/tag/0.3).

Dans le dossier contenant le fichier `7map_0.3_pre.jar`, dans un terminal exécuter :

```console
java -jar ./7map_0.3_pre.jar -B
```

### Déplacer la carte

Utiliser les touches fléchées `→`, `←`, `↑`, `↓` pour déplacer la carte.

Utiliser la touche `A` pour zoomer, `E` pour dézoomer.

## Ajouter une carte

Les données sont au format [XML OSM](https://wiki.openstreetmap.org/wiki/OSM_XML), et peuvent être téléchargées gratuitement depuis via une [API REST](https://wiki.openstreetmap.org/wiki/API_v0.6#Retrieving_map_data_by_bounding_box:_GET_.2Fapi.2F0.6.2Fmap).

Par exemple, le lien suivant télécharge les alentours de l'ENSEEIHT :
[https://api.openstreetmap.org/api/0.6/map?bbox=1.45338,43.60116,1.45760,43.60297](https://api.openstreetmap.org/api/0.6/map?bbox=1.45338,43.60116,1.45760,43.60297)

Vous pouvez préciser l'URL directement en exécutant le programme de la manière suivante :

```console
java -jar ./7map_0.3_pre.jar -U https://api.openstreetmap.org/api/0.6/map?bbox=1.45338,43.60116,1.45760,43.60297
```

Si vous avez déjà une carte OSM téléchargée, vous pouvez l'ouvrir via la CLI :

```console
java -jar ./7map_0.3_pre.jar -F ./newMap.osm
```

Ou via l'interface graphique en cliquant sur le bouton `Browse`, ou `File > Open`

## Option du CLI

 - `-B` ou `--build` Forcer le chargement d'une carte. Si aucun chemin n'est donné, via l'option `-F`, la carte par défaut sera affichée.
 - `-F` ou `--fileName` `[path/to/map.osm]`  Charge le fichier passé en paramètre contenant les données d'une carte OSM et le compile pour l'affichage.
 - `-U` ou `--url` `[url/of/a/osm/file]`  Télécharge le fichier *.osm et le compile pour l'affichage.

## Éditer les styles de la carte

Vous pouvez changer les styles de la carte (couleur des routes, bâtiments, ...) en modifiant le fichier `styles.json` se trouvant dans le dossier `HOME/.7map` (`C:\Users\<USERNAME>\.7map` pour Windows, `/users/USERNAME/.7map`)

Un objet doit avoir cette structure :

```json
{
  "k": "type",
  "v": "poly",
  "borderThickness": 12,
  "borderColor": "#7f8c8d",
  "color": "#7f8c8d"
}
```

avec les paramètres `k` et `v` correspondant aux `key` et `value` d'un [tag OSM](https://wiki.openstreetmap.org/wiki/Map_features)