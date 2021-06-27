# Projet de FSGBD
## Repartition des rôles au sein du projet
Brondino Thomas :
* Rapport
Camorani Alexis :
* Code 
* Rapport  :
   * Partie 1 : Identification du SGBD
   * Partie 1 : Gestion de la concurrence d’accès
   * Partie 3
Ferriol Julien :
* Rapport
Lefeuvre Maxence 
* Code 
* Rapport 
   * Partie 1 : Architecture fonctionnelles du SGBD 
   * Le dictionnaire de données
   * Organisation physique du SGBD
   * Gestion de la reprise sur panne
      * La journalisation, 
      * Les sauvegardes, 
   * Optimisation de requêtes
   * Partie 3

## Réalisations sur la partie du code
Attention à utiliser la bonne version du JDK Java :
![image](https://user-images.githubusercontent.com/28843048/123548642-ec3cac00-d765-11eb-92ae-9a59f55dd1d4.png)

* Nous avons réussis à exécuter le programme à partir d'un fichier csv
* Création de la méthode de recherche par l'index
* Création de la méthode de recherche séquentielle
* Réalisation des statistique (tps min, max et moyen) avec leur affichage à la fin des recherches
    * Nous pouvons d'ailleurs dire que les temps sont assez faibles ( 0ms ou 1ms ) car nous avons peu d'éléments dans notre liste et que le temps d'exécution dépend aussi de la machine qui exécute le programme ( Plus une machine est puissante et plus elle sera rapide ). De plus les données à rechercher ne sont pas aussi complexes que des données d'une vraie bdd à taille réelle ( Exemple Pôle Emploi --> To de données ) 
    * De ce fait, nous pouvons tout de même dire qu'à taille réelle, la recherche séquentielle serait une recherche à éviter car parcourir chaque élément serait beaucoup trop long, nous nous voyons mal parcourir des millions/milliard de données une à une. 
