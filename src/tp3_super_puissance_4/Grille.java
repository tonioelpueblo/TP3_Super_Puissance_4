/*
 * TP3_Super_Puissance_4
 * COURBI Antoine BONNEAU Edwin
 * TP realise le 23/10/2020
 * 
 * Projet Super Puissance 4
 * 
 * Classe Grille
 */
package tp3_super_puissance_4;

/**
 *
 * @author antoi
 */
public class Grille {//on cree nos attributs

    Cellule[][] Cellules = new Cellule[6][7];

    public int ajouterJetonDansColonne(Jeton Notre_Jeton, int indice) {
        int nombre_a_retourner = 0;
        for (int ligne=0;ligne<6;ligne++){
            if(Cellules[ligne][indice].presenceTrouNoir()==true){
                Cellules[ligne][indice].activerTrouNoir();
                if(Cellules[ligne][indice].presenceDesintegrateur()==true){
                    Cellules[ligne][indice].recupererDesintegrateur();
                    return 1;
                }
                return 0;
            }
            if(Cellules[ligne][indice].presenceDesintegrateur()==true){
                    Cellules[ligne][indice].recupererDesintegrateur();
                    nombre_a_retourner=1;
                }
            
            
        }
        if(nombre_a_retourner!=1){
                nombre_a_retourner=0;
        }
        if (celluleOccupee(0,indice)== true) {
            return 0;//si la colonne est pleine on renvoie false
        } else {//sinon ... 
            for(int i=5;i>=0;i=i-1) {
                if (celluleOccupee(i,indice)==false) {
                    Cellules[i][indice].jetonCourant = Notre_Jeton;
                    return nombre_a_retourner;
                }
            }
            return 0;//c'est inutile puisque si la colonne n'est pas pleine
            //et que je regarde chacun de ses elements je trouverai une cellule vide
            //et donc la methode renverrai un boolean mais la machine ne le sait pas
        }
    }

    public boolean etreRemplie() {
        for (int i = 0; i < 7; i++) {//je vais parcourir ma premiere ligne, si une colonne
            //n'est pas remplie alors on renvoie false puisque la grille n'est pas
            //pleine, mais si on sort de la boucle et que rien n'a ete renvoyé
            //alors la grille est remplie et on renvoie true
            if ((Cellules[0][i]) == null) {
                return false;
            }
        }
        return true;
    }

    public void viderGrille() {
        for (int i = 0; i < 6; i++) {//on parcourt notre grille par ligne et par
            //colonne et on la remplie de "null" pour dire que chaque
            //cellule est un objet cellule "null"
            for (int j = 0; j < 7; j++) {
                Cellules[i][j] = new Cellule();
            }
        }
    }

    public void afficherGrilleSurConsole() {
        //on va parcourir notre grille et si on a un trou noir on affiche un
        //'o' et si on a un jeton sans trou noir on affiche 'j' ou 'r' pour
        //rouge ou jaune ... 
        for (int i = 0; i < 6; i++) {//ligne
            String ligne = "";
            for (int j = 0; j < 7; j++) {//colonne, elements d'une ligne ... 
                String caractere = "";

                if (Cellules[i][j].presenceTrouNoir() == true) {
                    caractere = "[o]";//si on a un trou noir on affiche ca
                } else {//et si on a pas de trou noir
                    if(Cellules[i][j].jetonCourant!=null){
                    if (Cellules[i][j].lireCouleurDuJeton() == "Jaune") {//mais un jeton
                        caractere = "[j]";
                    }
                    if (Cellules[i][j].lireCouleurDuJeton() == "Rouge") {//mais un jeton
                        caractere = "[r]";
                    }
                    
                    }else{
                        caractere="[ ]";
                    }}
                ligne += caractere;//on ajoute a notre ligne un caractere et a la
                //fin de la ligne on renvoie la ligne entiere
                }
                
            System.out.println(ligne);//on affiche les lignes une par une
                }}    
    

    public boolean celluleOccupee(int i, int j) {
        if (Cellules[i][j].jetonCourant == null) {
            return false;
        } else {
            return true;
        }
    }

    public String lireCouleurDuJeton(int i, int j) {
        return Cellules[i][j].lireCouleurDuJeton();
    }

    public boolean etreGagnantePourJoueur(Joueur joueur) {
        //La couleur du joueur :
        String Couleur=joueur.Couleur;
        
        // Vérifie les horizontales ( - )
        for (int i = 0; i < 6; i++) {
            if (cherche4alignes(i, 0, 0, 1,Cellules,Couleur)) {
                return true;
            }
        }

        // Vérifie les verticales ( ¦ )
        for (int j = 0; j < 7; j++) {
            if (cherche4alignes(0, j, 1, 0,Cellules,Couleur)) {
                return true;
            }
        }
        //Pour les diagonales :
        //  [\][\][\][\/][/][/][/]
        //  [\][ ][ ][ ][ ][ ][/]
        //  [\][ ][ ][ ][ ][ ][/]
        //  [ ][ ][ ][ ][ ][ ][ ]
        //  [ ][ ][ ][ ][ ][ ][ ]
        //  [ ][ ][ ][ ][ ][ ][ ]
        //On recherche des diagonales droite depuis le premiere ligne sur la
        //moitié des colonnes puis la premiere ligne sur l'autre moitié des
        //colonnes vers la gauche puis sur les deux colonnes des extremités
        //aux indices (lignes) 1 et 2 (2 et ) vers les droite et vers la gauche
        
        // Diagonales (cherche depuis la ligne du haut)
        for (int j = 0; j < 7; j++) {
            // Première diagonale ( / )
            if (cherche4alignes(0, j, 1, -1,Cellules,Couleur)) {
                return true;
            }
            // Deuxième diagonale ( \ )
            if (cherche4alignes(0, j, 1, 1,Cellules,Couleur)) {
                return true;
            }
        }

        // Diagonales (cherche depuis les colonnes gauches et droites)
        for (int i = 0; i < 6; i++) {
            // Première diagonale ( / )
            //Colonne gauche
            if (cherche4alignes(i, 0, 1, 1,Cellules,Couleur)) {
                return true;
            }
            // Deuxième diagonale ( \ )
            //Colonne droite
            if (cherche4alignes(i, 6, 1, -1,Cellules,Couleur)) {
                return true;
            }
        }

        //
        return false;
    }
    public boolean cherche4alignes(int i, int j, int di, int dj,Cellule[][] cellule,String coul) {
     /**
     * Cette méthode cherche 4 pions alignés sur une ligne. Cette ligne est
     * définie par le point de départ (i,j),
     * et par le déplacement (deplacement en j,deplacement en i). 
     * En utilisant des valeurs
     * appropriées pour dCol et dLigne on peut vérifier toutes les directions: 
     * horizontale: (0,1)
     * vérticale: (1,0)
     * diagonale / : (1,1)
     * diagonale \: (1,-1)
     */
        int compteur = 0;
        String couleur1=null;//notre premiere couleur
        while ((j >= 0) && (j < 7) && (i >= 0) && (i < 6)) {
            String couleur2=cellule[i][j].lireCouleurDuJeton();
            if ((couleur2 ==couleur1)&&(couleur2==coul)) {
                // Si la couleur correspond ... 
                couleur1 = cellule[i][j].lireCouleurDuJeton();
                compteur++;
            } else {
                // Sinon on reinitialise
                couleur1 = cellule[i][j].lireCouleurDuJeton();
                compteur=1;//le compteur par default vaut 1 puisque une case
                //correspond avec elle-meme ... 
            }

            // On sort lorsque le compteur atteint 4
            if (compteur >= 4) {
                return true;
            }

            // On passe à l'itération suivante
            i+=di;
            j+=dj;
            
        }

        // Aucun alignement n'a été trouvé
        return false;
    }

    public void tasserGrille(int i, int j) {
        j=j-1;
        Cellule a = Cellules[0][j];//contient la cellule 1 (0) de notre colonne
        Cellules[0][j] = null;//on vide notre premiere cellule
        for (int k = 1; k < i; k++) {//pour k va de la ligne zero a la ligne i
            //si l'indice était 0 alors on entre pas dans la boucle
            //puisque 1!<0, pour le reste on effectue :

            Cellule b = Cellules[k][j];//contient la cellule actuelle
            Cellules[k][j] = a;
            a = b;
            //a la fin l'objet a contient la cellule ciblee au depart

        }
    }

    public boolean colonneRemplie(int j) {
        for (int i = 0; i < 6; i++) {
            if (Cellules[i][j].jetonCourant==null) {
                return false;
            } else {
                return true;
            }
        }
        return false;//sinon on avait l'erreur 'missing return statement' ... 
    }

    public boolean trouNoir(int l, int m) {
        if (Cellules[l][m].trouNoir == true) {//si on a deja un trou noir
            return false;
        } else {
            Cellules[l][m].trouNoir = true;
            return true;
        }
    }

    public boolean placerDesintegrateur(int l, int m) {
        if (Cellules[l][m].desintegrateur == true) {//si on a deja un trou noir
            return false;
        } else {
            Cellules[l][m].desintegrateur = true;
            return true;
        }
    }

    public boolean supprimerJeton(int i, int j) {
        if (celluleOccupee(i, j) == true) {
            return false;
        } else {
            tasserGrille(i+1, j+1);
            return true;
        }
    }

    public Jeton recupererJeton(int i, int j) {
        Jeton a = Cellules[i][j].jetonCourant;
        tasserGrille(i, j);
        return a;
    }
}
