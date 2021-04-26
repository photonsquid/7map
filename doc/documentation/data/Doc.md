# Documentation de la team Data

## Sommaire

- [Documentation de la team Data](#documentation-de-la-team-data)
  - [Sommaire](#sommaire)
  - [Différents formats](#différents-formats)
  - [Compte rendu de la réunion entre Sébastien et Sébastien](#compte-rendu-de-la-réunion-entre-sébastien-et-sébastien)

## Différents formats

Il existe de nombreux standard d'enregistrement de données cartographiques.
Notre application a initialement choisi de traiter des fichiers au format [GeoJson](https://fr.wikipedia.org/wiki/GeoJSON) car il est très simple, facile d'utilisation et permet une grande personnalisation (via la propriété `properties`).

Cependant le [format](https://wiki.openstreetmap.org/wiki/OSM_XML) utilisé par [OpenStreetMap](https://wiki.openstreetmap.org/wiki/Main_Page) (OSM) semble être le plus répandu, et le plus utilisé. De plus il existe l'API d'OSM pour télécharger très simplement des bouts de cartes sous ce format via une simple requête HTTP.

C'est pourquoi nous voulons pouvoir aussi être capable de lire ce format.

## Compte rendu de la réunion entre Sébastien et Sébastien

On garde le json comme format d'enregistrement mais on récupère les donnée depuis des fichiers de format OSM. 
On peut ausii utiliser l'API de OSM pour récupérer les données dynamiquement (?)
Le but est d'avoir une base de donnée en local en json qui s'occupe de fournir les données nécessaires à l'application pendant l'utilisation.
Ilpeut aussi y aoir une base de donnée distnte qui permet d'enregistrer les données "sur internet"
Il pourrait y avoir deux serveurs de données : un chez moi et un chez Philippe avec un load balancing pour répartir les requêtes

Il faut donc un parser (en java) qui prend en entrée des fichiers / streams au format OSM et qui les transforme en objet GEOJson personnel que l'on se sera créé pour afficher le plus rapidement les données qui vont être exportés dans la base de donnée. Ensuite lors de l'utilisation, l'application fera des requêtes à cette base de donnée mongodb en récupérant ces données quasi prêtes pour l'affichage.
L'avantage de la base de donnée est qu'elle reverra relativement rapidement une liste d'objets compris entre deux atitudes et longitudes.
