/*
 * TP3_Super_Puissance_4
 * COURBI Antoine BONNEAU Edwin
 * TP realise le 23/10/2020
 * 
 * Projet Super Puissance 4
 * 
 * Classe Partie
 */
package tp3_super_puissance_4;

import java.util.Random;//pour nos nombres aleatoires ... 
import java.util.Scanner;

/**
 *
 * @author antoi
 */
public class Partie {//on cree nos attributs

    Joueur[] ListeJoueurs = new Joueur[2];
    Jeton[] ListeJeton = new Jeton[21];
    Joueur joueurCourant;
    Grille LaGrille = new Grille();

    public void attribuerCouleursAuxJoueurs() {
        // génération d'un nombre >= 0 et < 2
        Random r = new Random();//un objet r de classe random
        int n = r.nextInt(2);//un nombre entier n aleatoire entre 0 et 1

        String[] Tableau_Couleurs = new String[2];//notre tableau de chaines de 
        //caracteres contenant deux couleurs
        Tableau_Couleurs[0] = "Jaune";
        Tableau_Couleurs[1] = "Rouge";

        if (n == 0) {//dans un cas ... 
            ListeJoueurs[0].affecterCouleur(Tableau_Couleurs[0]);
            ListeJoueurs[1].affecterCouleur(Tableau_Couleurs[1]);

        } else {//dans l'autre cas ... 
            ListeJoueurs[0].affecterCouleur(Tableau_Couleurs[1]);
            ListeJoueurs[1].affecterCouleur(Tableau_Couleurs[0]);
            //On remarque qu'on aurait pu utiliser un switch ... 

        }

    }

    public void initialiserPartie() {

        // 1 _ On cree notre Grille, a zero
        LaGrille = new Grille();//on initialise notre grille
        LaGrille.viderGrille();

        // 2 _ On cree nos Trous Noirs et Desintegrateurs 
        Random a = new Random();//un objet a de classe random

        //pour les deux premiers desintegrateurs on creera un Trou Noir
        int i = 0;//notre indice
        while (i < 2) {//on effectue une boucle d'autant d'element de trou
            //Noirs et Desintegrateurs : 
            int ligne1 = a.nextInt(6);
            int colonne1 = a.nextInt(7);

            LaGrille.Cellules[ligne1][colonne1].trouNoir = true;
            LaGrille.Cellules[ligne1][colonne1].desintegrateur = true;
            //pour les deux premiers trouNoir on a un desintegrateur
            i++;//on incremente i

            //en sortant de cette boucle il nous manque trois Trous Noirs et trois 
            //desintegrateurs, ils doivent etre places sur des cellules ne
            //contenant ni Trou Noir ni desintegrateur : 
        }

        // On fait notre boucle while pour les Trous Noirs et desintegrateurs :
        int j = 0;//notre indice
        while (j < 3) {//on effectue une boucle d'autant d'element de trou
            //Noirs :  
            int ligne2 = a.nextInt(6);//ligne2 et colonne2 sont pour nos trous noirs
            int colonne2 = a.nextInt(7);

            if (LaGrille.Cellules[ligne2][colonne2].presenceTrouNoir() == false && LaGrille.Cellules[ligne2][colonne2].presenceDesintegrateur() == false) {
                LaGrille.Cellules[ligne2][colonne2].trouNoir = true;
                j++;//on incremente j
            }
        }

//on fait pareil pour nos desintegrateurs :
        int k = 0;//notre indice
        while (k < 3) {//on effectue une boucle d'autant d'element de trou
            //Noirs :  
            int ligne3 = a.nextInt(6);//ligne3 et colonne3 sont pour nos trous noirs
            int colonne3 = a.nextInt(7);

            if (LaGrille.Cellules[ligne3][colonne3].presenceTrouNoir() == false && LaGrille.Cellules[ligne3][colonne3].presenceDesintegrateur() == false) {
                LaGrille.Cellules[ligne3][colonne3].desintegrateur = true;
                k++;//on incremente k
            }
        }

        // 3 _ On cree nos jetons et on les donne aux joueurs : 
        //Les jetons sont cree dans un tableau de jeton en meme temps que la
        //creation du joueur, on a plus qu'a leur donner une couleur :
        // ... 
        //pour le premier joueur :
        String coul1 = ListeJoueurs[0].Couleur;
        for (int l = 0; l < 21; l++) {
            Jeton new_jeton = new Jeton(coul1);
            ListeJoueurs[0].ajouterJeton(new_jeton);

        }
        String coul2 = ListeJoueurs[1].Couleur;
        //pour le deuxieme joueur : 
        for (int m = 0; m < 21; m++) {
            Jeton new_jeton = new Jeton(coul2);
            ListeJoueurs[1].ajouterJeton(new_jeton);

        }

    }

    public void debuterPartie() {
        
        
        //Pour le choix de desintegrer ou de recuperer un jeton on initialise
        //un compteur de tour a zero ... 
        //Si le jour a moins de 21 jetons on va afficher ou non dans le menu 
        //la possibilité
        //de recuperer un jeton ... En effet il faut attendre que les deux
        //joueurs ai pose un jeton pour pouvoir en recuperer un ...
        //Pour le desintegrateur on affiche dans le menu la possibilité de 
        //desintegrer que si il y a un nombre de desintegrateur(s) suffisant ... 
           
        
        //Nom des joueurs :
        Scanner saisieUtilisateur = new Scanner(System.in);
        System.out.println("Nom du premier joueur ? :");
        String nom1 = saisieUtilisateur.next();
        ListeJoueurs[0] = new Joueur(nom1);

        System.out.println("Nom du deuxieme joueur ? :");
        String nom2 = saisieUtilisateur.next();
        ListeJoueurs[1] = new Joueur(nom2);

        attribuerCouleursAuxJoueurs();
        initialiserPartie();

        //Premier joueur :
        Random r = new Random();
        int n = r.nextInt(2);
        if (n == 0) {//dans un cas ... 

            joueurCourant = ListeJoueurs[0];
            System.out.println("Le joueur 1 commence et a les " + joueurCourant.Couleur);
        } else {

            joueurCourant = ListeJoueurs[1];
            System.out.println("Le joueur 2 commence et a les " + joueurCourant.Couleur);
        }

        //Menu ... 
        while (true) {
            System.out.println(("Joueur :" + joueurCourant.Nom));
            System.out.println(("Nombre de desintegrateur(s) du joueur :" + joueurCourant.nombreDesintegrateurs));
            System.out.println("Nombre de jeton(s) du joueur :"+joueurCourant.nombreJetons_restants);
            System.out.println(("Voici la grille :"));
            LaGrille.afficherGrilleSurConsole();
            System.out.println("Que voulez-vous faire ? :");
            System.out.println("\n1) Placer un jeton");
            if(joueurCourant.nombreJetons_restants<21){
                System.out.println("\n2) Recuperer un jeton (le votre)");
            }
            if(joueurCourant.nombreDesintegrateurs>0){
            System.out.println("\n3) Desintegrer un jeton adverse");
            }
            System.out.println("\n4) Quitter");
                    
            int choix = saisieUtilisateur.nextInt();
            if (choix == 4) {
                break;
            }

            //Placer un jeton :
            if (choix == 1) {
                while (true) {
                    System.out.println("Choix de la colonne (entre 0 et 6) :");
                    int j = (int) saisieUtilisateur.nextInt();
                    if ((j < 0) || (j > 6)) {

                    } else {
                        if (LaGrille.colonneRemplie(j) == true) {
                            System.out.println("La colonne est pleine");

                        } else {
                            joueurCourant.nombreDesintegrateurs += LaGrille.ajouterJetonDansColonne(joueurCourant.ListeJetons[(joueurCourant.nombreJetons_restants) - 1], j,joueurCourant);
                            joueurCourant.nombreJetons_restants=(joueurCourant.nombreJetons_restants-1);
//on incremente a notre nombre de desintegrateur
                            //de notre joueur soit 0 soit 1 en fonction de si on
                            //a croise un desintegrateur ou non ... 

                            //on change le joueur courant = 
                            break;
                        }
                    }
                }
            }
            //Recuperer un jeton : 
            if (choix == 2) {
            
            //Pour recuperer un jeton le jeton doit exister sur la case et doit
            //etre de la meme couleur que le joueurCourant ... 
            //Pour le recuperer on ajoute un au nombre de jeton du joueur
            //Puis on tasse la colonne sur le jeton qui est supprimé ... 
            
            while(true){
                                       
            System.out.println("Quel jeton voulez-vous recuperer ? : (1<=i<=6,1<=j<=7)");
            int ligne=saisieUtilisateur.nextInt();
            int colonne=saisieUtilisateur.nextInt();
            if((ligne>7)||(ligne<0)||(colonne>8)||(colonne<0)){
                
            }else{
            //On verifie que le jeton exsite : ... 
            if(LaGrille.celluleOccupee(ligne-1, colonne-1)==true){
                //On verifie que la couleur correspond : ... 
                if(LaGrille.lireCouleurDuJeton(ligne-1, colonne-1)==joueurCourant.Couleur){
                    //Tout correspond c'est ok : ... 
                    joueurCourant.nombreJetons_restants+=1;
                    LaGrille.tasserGrille(ligne,colonne);
                    break;
                }//Si la couleur ne correspond pas on le dit : 
                else{
                    System.out.println("La couleur ne correspond pas, choississez un autre jeton ... ");
                }
            }
            else{
                System.out.println("Il n'y a pas de jeton ici, choississez un jeton ... ");
            }
            }
            }
            }
            //Desintegrer un jeton : 
            if (choix == 3) {
                
                
        //Pour desintegrer un jeton le jeton doit exister sur la case et doit
            //etre de la couleur oppose que celle de joueurCourant ... 
            //De plus le joueur courant doit avoir suffisament de desintegrateur
            //Pour le desintegrer on enleve un au nombre de desintegrateur du
            //joueur
            //Puis on tasse la colonne sur le jeton qui est supprimé ... 
            while(true){
                                       
            System.out.println("Quel jeton voulez-vous Desintegrer ? : (1<=i<=6,1<=j<=7)");
            int ligne=saisieUtilisateur.nextInt();
            int colonne=saisieUtilisateur.nextInt();
            if((ligne>7)||(ligne<0)||(colonne>8)||(colonne<0)){
                
            }else{
            //On verifie que le jeton exsite : ... 
            if(LaGrille.celluleOccupee(ligne-1, colonne-1)==true){
                //On verifie que la couleur correspond : ... 
                String couleurCorect;
                if (joueurCourant == ListeJoueurs[0]) {
                couleurCorect = ListeJoueurs[1].Couleur;
            } else {
                couleurCorect = ListeJoueurs[0].Couleur;
            }
                if(LaGrille.lireCouleurDuJeton(ligne-1, colonne-1)==couleurCorect){
                    //Tout correspond c'est ok : ... 
                    joueurCourant.nombreDesintegrateurs=(joueurCourant.nombreDesintegrateurs-1);
                    LaGrille.tasserGrille(ligne,colonne);
                    break;
                }//Si la couleur ne correspond pas on le dit : 
                else{
                    System.out.println("La couleur ne correspond pas, choississez un autre jeton ... ");
                }
            }
            else{
                System.out.println("Il n'y a pas de jeton ici, choississez un jeton ... ");
            }
            }
            }
            }
            
            
            
            
            
            if ((LaGrille.etreGagnantePourJoueur(ListeJoueurs[0]) == true)&&(LaGrille.etreGagnantePourJoueur(ListeJoueurs[1])==true)) {
                System.out.println("Les deux joueurs ont gagné, egalité, FELICITATIONS !!");
                break;          
                }
            else if((LaGrille.etreGagnantePourJoueur(ListeJoueurs[0]) == true)){
                System.out.println(ListeJoueurs[0].Nom + " a gagné, FELICITATIONS !!");
                break; 
            }
            else if((LaGrille.etreGagnantePourJoueur(ListeJoueurs[1])==true)){
                System.out.println(ListeJoueurs[1].Nom + " a gagné, FELICITATIONS !!");
                break; 
            }
            
            //on echange notre joueur Courant : 
            if (joueurCourant == ListeJoueurs[0]) {
                joueurCourant = ListeJoueurs[1];
            } else {
                joueurCourant = ListeJoueurs[0];
            }
            
        
        }
    }
}
