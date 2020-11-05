/*
 * TP3_Super_Puissance_4
 * COURBI Antoine BONNEAU Edwin
 * TP realise le 23/10/2020
 * 
 * Projet Super Puissance 4
 * 
 * Classe Cellule
 */
package tp3_super_puissance_4;

/**
 *
 * @author antoi
 */
public class Cellule {//on cree nos attributs
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;

public Cellule (){
    //on cree notre constructeur qui initialise tout a zero (null pour des
    //objets et false pour boolean ... )
    jetonCourant=null;
    trouNoir=false;
    desintegrateur=false;
}
public boolean affecterJeton (Jeton Nouveau_Jeton){
    //on ajoute notre jeton a notre cellule : 
    if (jetonCourant==null){
        jetonCourant=Nouveau_Jeton;
        return true;//tout s'est bien passé
    }
    else {
        return false;
    }
    
    
}
public Jeton recupererJeton(){
    //on renvoie la reference du jeton de la cellule si il existe
    return jetonCourant;
}
public boolean supprimerJeton(){
    //on renvoie vrai si on a bien supprimer, false sinon ... 
    if (jetonCourant!=null){
        jetonCourant=null;
        return true;
    }
    else {
        return false;
    }
}
public boolean placerTrouNoir(){
    if (trouNoir==false){//on place un trou noir si il n'y en a pas
        trouNoir=true;
        return true;
    }
    else{//sinon on renvoie false ... 
        return false;
    }
    
}
public boolean placerDesintegrateur(){
    if (desintegrateur==false){//on place un desintegrateur si il n'y en a pas
        desintegrateur=true;
        return true;
    }
    else{//sinon on renvoie false ... 
        return false;
    }
    
}
public boolean presenceTrouNoir(){
    return trouNoir;//si un trouNoir est present on renvoie vrai
}
public boolean presenceDesintegrateur(){
    return desintegrateur;//si un desintegrateur est present on renvoie vrai
}
public String lireCouleurDuJeton(){
    if(jetonCourant==null){
            return "null";
        }else{
        return jetonCourant.Couleur;//on renvoie la couleur du Jeton Courant ... 
        }
    
    
}
public boolean recupererDesintegrateur(){
    if (desintegrateur==true){//si on a un desintegrateur on l'enleve
        desintegrateur=false;
        
        return true;//et on renvoie vraie
    }
    else {
        return false;//sinon on renvoie faux
    }
}
public boolean activerTrouNoir(){
    if (trouNoir==true){//si j'ai un trouNoir
        trouNoir=false;//le Trou Noir disparait
        jetonCourant=null;//le Jeton Disparait avec le Trou Noir ... 
        return true;
    }
    else {
        //si je n'ai pas de Trou Noir on renvoie false ... 
        return false;
    }
}
}