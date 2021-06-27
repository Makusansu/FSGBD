package fr.miage.fsgbd;

import java.util.ArrayList;
import java.util.Collections;


public class Main {

    // La taille max de l'index
    public static final Integer HASHTABLE_SIZE = 50;

    // Le délimiteur à utiliser pour séparer les champs
    private static final String DELIMITER = ";";

    // Le nom du fichier, doit se trouver dans la racine du projet
    private static final String DATA_FILE_NAME = "hashtable-indexing/data.csv";

    // Personnaliser ici les noms des colonnes que vous souhaitez utiliser pour la base de l'index
    private static final String[] INDEX_COLUMN_LIST = {"civilite", "nom", "prenom"};

    public static void main(String[] args) {
        // On instancie une nouvelle hashtable
        HashtableIndex hashtableIndex = new HashtableIndex(HASHTABLE_SIZE);

        // On l'initialise avec les paramètres définis plus haut
        hashtableIndex.init(DATA_FILE_NAME, DELIMITER, INDEX_COLUMN_LIST);

        // Valo pour les stats
        ArrayList<Long> debut = new ArrayList<>();
        ArrayList<Long> fin = new ArrayList<>();
        ArrayList<Long> tempsRecherche = new ArrayList<>();
        long tps_moyen = 0;

        // On lance la méthode recherche(key)
        System.out.println("**** Recherche par l'index ****");
        for(int i = 0; i<50;i++){
            debut.add(System.currentTimeMillis());
            hashtableIndex.recherche(i);
            fin.add(System.currentTimeMillis());
        }

        // Traitement des stats
        System.out.println("**** Stat de la recherche ****");
        getTps_moyen(debut, fin, tempsRecherche, tps_moyen);


        // On vide les valo de stats
        debut.clear();
        fin.clear();
        tempsRecherche.clear();
        tps_moyen = 0;

        // Recherche Sequentiel
        System.out.println("\n**** Recherche Sequentiel ****");
        for(int i = 0; i<50;i++){
            debut.add(System.currentTimeMillis());
            hashtableIndex.rechercheSequentiel(i);
            fin.add(System.currentTimeMillis());
        }

        // Traitement des stats pour la recherche sequentiel
        System.out.println("**** Stat de la recherche séquentiel ****");
        getTps_moyen(debut, fin, tempsRecherche, tps_moyen);


    }

    /**
     * Calcul les statistiques
     * @param debut List des temps de début d'exécution
     * @param fin List des temps de fin d'exécution
     * @param tempsRecherche List du temps d'exécution global
     * @param tps_moyen temps moyen d'exécution
     */
    private static void getTps_moyen(ArrayList<Long> debut, ArrayList<Long> fin, ArrayList<Long> tempsRecherche, long tps_moyen) {
        for(int i = 0; i<debut.size();i++ ){
            tempsRecherche.add(fin.get(i)-debut.get(i));
        }

        for (Long aLong : tempsRecherche) {
            tps_moyen = tps_moyen + aLong;
        }
        tps_moyen = tps_moyen/tempsRecherche.size();

        Collections.sort(tempsRecherche);

        System.out.println("Le temps minimum est " + tempsRecherche.get(0) + "ms");
        System.out.println("Le temps maximum est " + tempsRecherche.get(tempsRecherche.size()-1)+"ms");
        System.out.println("Le temps moyen est " + tps_moyen+"ms");
    }
}
