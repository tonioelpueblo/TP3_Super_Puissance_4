/*
 * TP3_Super_Puissance_4
 * COURBI Antoine BONNEAU Edwin
 * TP realise le 23/10/2020
 * 
 * Projet Super Puissance 4
 * 
 * Classe Joueur
 */
package tp3_super_puissance_4;

/**
 *
 * @author antoi
 */
public class Joueur {//on cree nos attributs
    String Nom;
    String Couleur;
    Jeton[] ListeJetons=new Jeton[21];
    int nombreDesintegrateurs;
    int nombreJetons_restants;
    
public Joueur (String Nom_Joueur){
    //notre constructeur
    Nom=Nom_Joueur;
    
}   
public void affecterCouleur(String Couleur_Joueur){
    //affecte la couleur en parametre au joueur
    Couleur=Couleur_Joueur;
    
    
}
public void ajouterJeton (Jeton New_Jeton){
    //on ajoute a notre tableau un jeton a l'indice du nombre de jeton restants
    ListeJetons[nombreJetons_restants]=New_Jeton;
    nombreJetons_restants++;//on a un jeton en plus ... 
}
public void obtenirDesintegrateur(){
    //on incremente le nombre de desintegrateurs :
    nombreDesintegrateurs++;//on a un desintegrateur en plus ... 
}
public boolean utiliserDesintegrateur(){
    
    if (nombreDesintegrateurs!=0){//si il me reste des desintegrateurs (au
        //moins un ... 
        nombreDesintegrateurs=nombreDesintegrateurs-1;//on enleve 1
        return true;//tout est bon
    }
    else{
        return false;//on a pas assez de desintegrateur, on renvoie false
    }
}
}
