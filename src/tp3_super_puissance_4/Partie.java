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
    Grille LaGrille=new Grille();

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
            Jeton new_jeton=new Jeton(coul1);
            ListeJoueurs[0].ajouterJeton(new_jeton);
        }
        String coul2 = ListeJoueurs[1].Couleur;
        //pour le deuxieme joueur : 
        for (int m = 0; m < 21; m++) {
            Jeton new_jeton=new Jeton(coul2);
            ListeJoueurs[1].ajouterJeton(new_jeton);
        }

    }

    public void debuterPartie() {

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
            if (ListeJoueurs[0].Couleur == "Jaune") {
                System.out.println("Le joueur 1 commence");
            } else {
                System.out.println("Le joueur 2 commence");
            }

        } else {
            if (ListeJoueurs[1].Couleur == "Jaune") {
                System.out.println("Le joueur 2 commence");
            } else {
                System.out.println("Le joueur 1 commence");
            }
        }

        while (true) {
            System.out.println(("Voici la grille :"));
            LaGrille.afficherGrilleSurConsole();
            System.out.println("Que voulez-vous faire ? :"
                    + "\n1) Placer un jeton"
                    + "\n2) Recuperer un jeton"
                    +"\n3) Desintegrer un jeton adverse"
                    +"\n4) Quitter");
            int choix=saisieUtilisateur.nextInt();
            if(choix==4){
                break;
            }
            
            
            //Placer un jeton :
            if(choix==1){
                while (true){
                System.out.println("Choix de la colonne :");
                int j=(int)saisieUtilisateur.nextInt();
                if(LaGrille.colonneRemplie(j)==true){
                    System.out.println("La colonne est pleine");
                }else{
                    LaGrille.ajouterJetonDansColonne(ListeJoueurs[0].ListeJetons[ListeJoueurs[0].nombreJetons_restants], j);
                }
                    //test
                }
            }
        }
    }
}
