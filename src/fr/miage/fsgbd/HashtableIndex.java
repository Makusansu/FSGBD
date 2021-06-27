package fr.miage.fsgbd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * Cette classe est la classe principale qui gère la hashtable de base
 */
public class HashtableIndex {
    private final Hashtable<Integer, HashtableElement> hashtable;

    public HashtableIndex(Integer size) {
        this.hashtable = new Hashtable<>(size);
    }

    public HashtableElement getElement(Integer key) {
        return this.hashtable.get(key);
    }

    // Fonction d'initialisation de la hashtable
    public void init(String dataFileName, String delimiter, String[] indexColumnList) {
        // Récupère les données du CSV
        List<List<String>> data = loadData(dataFileName, delimiter);

        // On note les index dans le tableau de données des colonnes qui nous interessent
        List<Integer> indexIndexes = new ArrayList<>();
        for (String index : indexColumnList) {
            for (int i = 0; i < data.get(0).size(); i++) {
                if (index.equalsIgnoreCase(data.get(0).get(i)))
                    indexIndexes.add(i);
            }
        }

        // Si le tableau d'index est vide, on arrête tout, on ne pourra pas indexer
        if (indexIndexes.isEmpty()) {
            System.out.println("Aucun index trouvé dans le fichier, abord mission !");
            System.exit(1);
        }

        // On retire la première ligne qui contient les colonnes car nous n'en avons plus besoin
        data.remove(0);

        // On parcours les données
        for (List<String> line : data) {
            // On crée un nouvel élément
            HashtableElement hashtableElement = new HashtableElement(line, indexIndexes);
            // Si la clef correspondant à l'élément n'existe pas déjà, on ajoute la valeur dans la hashtable
            if (!this.hashtable.containsKey(hashtableElement.hashCode()))
                this.hashtable.put(hashtableElement.hashCode(), hashtableElement);
            else { // Sinon, on parcours les éléments existant pour la clef trouvée et on va rajouter notre enregistrement en bout de file
                HashtableElement existing = this.hashtable.get(hashtableElement.hashCode());
                while (existing.getNextElement() != null) {
                    existing = existing.getNextElement();
                }
                existing.setNextElement(new HashtableElement(line, indexIndexes));
            }
        }

        // Finalement on affiche la hashtable pour voir le résultat
        hashtable.forEach(
                (k, v) -> System.out.println("Key : " + k + ", Value : " + v));
    }

    // Charge les données à partir d'un fichier CSV
    public List<List<String>> loadData(String dataFileName, String delimiter) {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                data.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Recherche l'élément qui a pour index la valeur de la clé [key]
     * Récupère directement l'élément à partir de la clé [key] sans parcourir toute la hashtable
     */
    public void recherche(Integer key) {
        // On cherche l'index dans le tableau à partir de la clé sans parcourir tous les éléments
        System.out.println("L'élement qui a pour index la clé  : " + key +" est l'élément suivant : " + getElement(key).value);

    }

    /**
     * Recherche l'élément qui a pour index la valeur de la clé [key]
     * Parcours toute la hashtable jusqu'à trouver le bon élément
     */
    public void rechercheSequentiel(Integer key) {
        // On part du premier et on parcourt tout la hashtable jusqu'à trouver le bon element
        hashtable.forEach((k,v) ->{
            if (k.equals(key)){
                System.out.println("L'élement qui a pour index la clé  : " + key +" est l'élément suivant : " + v.value);
            }
                }
        );
    }


}
