package fr.miage.fsgbd;

import java.util.List;


/**
 * Cette classe est la classe qui gère les éléments de la hashtable
 */
public class HashtableElement {
     Integer key;
     List<String> value;
     HashtableElement nextElement;
     final List<Integer> indexColumnList;

    public HashtableElement(List<String> value, List<Integer> indexColumnList) {
        this.value = value;
        this.indexColumnList = indexColumnList;
        this.key = generateKey();
    }

    public Integer getKey() {
        return key;
    }

    public List<String> getValue() {
        return value;
    }

    public HashtableElement getNextElement() {
        return nextElement;
    }

    public void setNextElement(HashtableElement nextElement) {
        this.nextElement = nextElement;
    }

    /**
    * Fonction servant à générer une clef en se basant sur les index des colonnes qui ont été identifiés plus tôt
    * Pour créer une clef, on va concaténer toutes les String concernées, dans l'ordre, puis les convertir en int et les ajouter pour obtenir la somme totale
    */
    public Integer generateKey()
    {
        int keyValue = 0;
        StringBuilder key = new StringBuilder();
        for (Integer index : indexColumnList)
        {
            key.append(this.value.get(index));
        }
        for(int i=0; i < key.length(); i++)
        {
            int asciiVal = key.charAt(i);
            keyValue += asciiVal;
        }
        return keyValue;
    }


    @Override
    // Cette méthode est obligatoire pour les éléments d'une hashtable afin que les éléments puissent être comparés
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashtableElement)) return false;
        HashtableElement that = (HashtableElement) o;
        return getKey().equals(that.getKey()) && getValue().equals(that.getValue());
    }

    @Override
    // Même histoire, ici, cette méthode sert à déterminer comment convertir une donnée en son "hash"
    public int hashCode() {
        return getKey() % Main.HASHTABLE_SIZE;
    }

    @Override
    public String toString() {
        return  "[ key=" + key +
                ", value=" + value +
                "]\n"+
                "nextElement=" + nextElement;
    }
}
