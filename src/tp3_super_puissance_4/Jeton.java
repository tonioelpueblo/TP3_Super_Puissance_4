/*
 * TP3_Super_Puissance_4
 * COURBI Antoine BONNEAU Edwin
 * TP realise le 23/10/2020
 * 
 * Projet Super Puissance 4
 * 
 * Classe Jeton
 */
package tp3_super_puissance_4;

/**
 *
 * @author antoi
 */
public class Jeton {//on cree nos attributs
    String Couleur;
    

public Jeton (String Couleur_jeton){
//on cree notre constructeur :
Couleur = Couleur_jeton;

}
public String lireCouleur(){
    //notre methode qui renvoie la couleur du jeton :
    return Couleur;
    
}
}