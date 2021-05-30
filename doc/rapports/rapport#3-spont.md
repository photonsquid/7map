# Notes à moi même (@seba1204)

Notes temporaires pour mon rapport personnel.

## `extracter.java` : un problème de type générique

### Un code propre

Dans le parser XML : `extracter.java`, la méthode `setData` par exemple s'utilise de la manière suivante :

```java
// Déclaration d'un l'objet
Node nd = new Node();

// Affecter les données parsées de `root` à l'objet
setData(root, nd);
```

Il aurait cependant était plus "propre" selon moi d'avoir l'utilisation suivante :

```java
// Affecter les données parsées de `root` à un nouvel objet
Node nd = getData(root);
```

En effet, la méthode `setData` ne "touche" pas l'objet qu'elle reçoit en second paramètre (`nd` par exemple), elle analyse uniquement son contenu de manière "réflective".

J'ai cependant besoin du type (et non pas sa classe) de cet objet pour pouvoir créer un objet par exemple :

```java
T obj = createObjectFrom(T.getClass());
```

En effet, depuis un type générique `T`, il est compliqué de récupérer sa classe (`T.getClass()` n'est pas possible). De plus, je n'ai pas réussi à récupérer le type d'une classe dans la mesure où il n'existe pas de type `Type`, de la même manière qu'il existe un type `Class`.

*Note :*  `createObjectFrom` est une méthode privée qui crée un nouvel objet à partir d'une classe donnée en paramètre.

### "Unchecked cast warning"

Dans la logique présentée au point précédent, je crée des fonctions qui retourne des objets de type générique `T`.
Pour cela il est possible que je crée d'abord l'objet en tant que `Object` puis que je le cast en `T`. Il serait bien de vérifier que le cast est possible avant de le faire à l'aide d'un `instanceof`, mais cela n'est pas possible car les types génériques (`T`) sont supprimés au moment de l'exécution.
