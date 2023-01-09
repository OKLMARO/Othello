public class Plateau {
    /*
     * Classe Plateau nous permettant de reproduire le plateau du jeu Othello avec une tableau de charactère à deux dimensiosn et une varible taille qui nous comporte la taille du tableau
     * 
     */
    public static final String RESET = "\u001B[0m";
    public static final String NOIR = "\u001B[30m";
    public static final String BLANC = "\u001B[37m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";

    private char[][] plateau;
    private int taille;

    /*
     * 
     * Construceur de la classe Plateau
     * 
     * @param int
     * 
     */
    public Plateau(int taille){
        this.taille = taille;
        plateau = new char[taille][taille];
        for(int i = 0; i < taille; i = i + 1){
            for(int j = 0; j < taille; j = j + 1){
                plateau[i][j] = ' ';
            }
        }
        plateau[(taille / 2) - 1][(taille / 2)] = 'N';
        plateau[(taille / 2)][(taille / 2) - 1] = 'N';
        plateau[(taille / 2)][(taille / 2)] = 'B';
        plateau[(taille / 2) - 1][(taille / 2) - 1] = 'B';
    }


    /*
     * 
     * Construceur de la classe Plateau
     * 
     */
    public Plateau() {
        this.taille = 8;
        plateau = new char[8][8];
        for (int i = 0; i < this.taille; i = i + 1) {
            for (int j = 0; j < this.taille; j = j + 1) {
                plateau[i][j] = ' ';
            }
        }
        plateau[3][4] = 'N';
        plateau[4][3] = 'N';
        plateau[4][4] = 'B';
        plateau[3][3] = 'B';
    }


    /*
     * 
     * Méthode afficher qui nous permet d'afficher le tableau.
     * 
     */
    public void afficher() {
        for(int i = 0; i <= this.taille; i = i + 1){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i = 0; i < this.taille; i = i + 1) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < this.taille; j = j + 1) {
                if(plateau[i][j] == 'N'){
                    System.out.print(NOIR + plateau[i][j] + RESET + "|");
                }
                else if(plateau[i][j] == 'B'){
                    System.out.print(BLANC + plateau[i][j] + RESET + "|");
                }
                else{
                    System.out.print(GREEN_BACKGROUND + plateau[i][j] + RESET + "|");
                }
            }
            System.out.println();
        }
    }

    /*
     * 
     * Méthode Coup qui nous permet de placer des pions dans le plateau, et nous renvoie true si un changement est effecter et false si non
     * 
     * @param Joueur
     * @param int
     * @param int
     * @return boolean
     * 
     */
    public boolean Coup(Joueur joueur, int x, int y){
        boolean changement = false;
        if(this.plateau[x][y] != ' '){
            return changement;
        }
        int i = x;
        int j = y;

        while(j > 0 && this.plateau[i][j - 1] != joueur.getCouleur() && this.plateau[i][j - 1] != ' ' && j == y){
            j = j - 1;
        }
        if(j > 0 && this.plateau[i][j - 1] == joueur.getCouleur()){
            if(changement == true){
                j = y - 1;
            }
            else{
                j = y;
            }
            while(this.plateau[i][j] != joueur.getCouleur()){
                this.plateau[i][j] = joueur.getCouleur();
                j = j - 1;
            }
            return true;
        }

        j = y;
        while(j < this.taille - 1 && this.plateau[i][j + 1] != joueur.getCouleur() && this.plateau[i][j + 1] != ' ' && j == y){
            j = j + 1;
        }
        if(j < this.taille - 1 && this.plateau[i][j + 1] == joueur.getCouleur()){
            if(changement == true){
                j = y + 1;
            }
            else{
                j = y;
            }
            while(this.plateau[i][j] != joueur.getCouleur()){
                this.plateau[i][j] = joueur.getCouleur();
                j = j + 1;
            }
            return true;
        }

        j = y;
        while(i > 0 && this.plateau[i - 1][j] != joueur.getCouleur() && this.plateau[i - 1][j] != ' ' && i == x){
            i = i - 1;
        }
        if(i > 0 && this.plateau[i - 1][j] == joueur.getCouleur()){
            if(changement == true){
                i = x - 1;
            }
            else{
                i = x;
            }
            while(this.plateau[i][j] != joueur.getCouleur()){
                this.plateau[i][j] = joueur.getCouleur();
                i = i - 1;
            }
            return true;
        }

        i = x;
        while(i < this.taille - 1 && this.plateau[i + 1][j] != joueur.getCouleur() && this.plateau[i + 1][j] != ' ' && i == x){
            i = i + 1;
        }
        if(i < this.taille - 1 && this.plateau[i + 1][j] == joueur.getCouleur()){
            if(changement == true){
                i = x + 1;
            }
            else{
                i = x;
            }
            while(this.plateau[i][j] != joueur.getCouleur()){
                this.plateau[i][j] = joueur.getCouleur();
                i = i + 1;
            }
            return true;
        }

        i = x;
        while(i > 0 && j > 0 && this.plateau[i - 1][j - 1] != joueur.getCouleur() && this.plateau[i - 1][j - 1] != ' ' && j == y && i == x){
            i = i - 1;
            j = j - 1;
        }
        if(j > 0 && i > 0 && this.plateau[i - 1][j - 1] == joueur.getCouleur()){
            if(changement == true){
                i = x - 1;
                j = y - 1;
            }
            else{
                i = x;
                j = y;
            }
            while(this.plateau[i][j] != joueur.getCouleur()){
                this.plateau[i][j] = joueur.getCouleur();
                i = i - 1;
                j = j - 1;
            }
            return true;
        }

        i = x;
        j = y;
        while(i < this.taille - 1 && j < this.taille - 1 && this.plateau[i + 1][j + 1] != joueur.getCouleur() && this.plateau[i + 1][j + 1] != ' ' && j == y && i == x){
            i = i + 1;
            j = j + 1;
        }
        if(i < this.taille - 1 && j < this.taille - 1  && this.plateau[i + 1][j + 1] == joueur.getCouleur()){
            if(changement == true){
                i = x + 1;
                j = y + 1;
            }
            else{
                i = x;
                j = y;
            }
            while(this.plateau[i][j] != joueur.getCouleur()){
                this.plateau[i][j] = joueur.getCouleur();
                i = i + 1;
                j = j + 1;
            }
            return true;
        }

        i = x;
        j = y;
        while(i > 0 && j < this.taille - 1 && this.plateau[i - 1][j + 1] != joueur.getCouleur() && this.plateau[i - 1][j + 1] != ' ' && j == y && i == x){
            i = i - 1;
            j = j + 1;
        }
        if(i > 0 && j < this.taille - 1 && this.plateau[i - 1][j + 1] == joueur.getCouleur()){
            if(changement == true){
                i = x - 1;
                j = y + 1;
            }
            else{
                i = x;
                j = y;
            }
            while(this.plateau[i][j] != joueur.getCouleur()){
                this.plateau[i][j] = joueur.getCouleur();
                i = i - 1;
                j = j + 1;
            }
            return true;
        }

        i = x;
        j = y;
        while(i < this.taille - 1 && j > 0 && this.plateau[i + 1][j - 1] != joueur.getCouleur() && this.plateau[i + 1][j - 1] != ' ' && j == y && i == x){
            i = i + 1;
            j = j - 1;
        }
        if(i < this.taille - 1 && j > 0 && this.plateau[i + 1][j - 1] == joueur.getCouleur()){
            if(changement == true){
                i = x + 1;
                j = y - 1;
            }
            else{
                i = x;
                j = y;
            }
            while(this.plateau[i][j] != joueur.getCouleur()){
                this.plateau[i][j] = joueur.getCouleur();
                i = i + 1;
                j = j - 1;
            }
            return true;
        }
        return changement;
    }


    /*
     * 
     * Méthode fin_partie_aux qui nous permet de savoir si un joueur peut placer un coup a c'est coordonnées.
     * 
     * @param Joueur
     * @param int 
     * @param int
     * 
     * @return boolean
     * 
     */
    private boolean fin_partie_aux(Joueur joueur, int x, int y){
        int i = x;
        int j = y;

        while(j > 0 && this.plateau[i][j - 1] != joueur.getCouleur() && this.plateau[i][j - 1] != ' ' && j == y){
            j = j - 1;
        }
        if(j > 0 && this.plateau[i][j - 1] == joueur.getCouleur()){
            return true;
        }

        j = y;
        while(j < this.taille - 1 && this.plateau[i][j + 1] != joueur.getCouleur() && this.plateau[i][j + 1] != ' ' && j == y){
            j = j + 1;
        }
        if(j < this.taille - 1 && this.plateau[i][j + 1] == joueur.getCouleur()){
            return true;
        }

        j = y;
        while(i > 0 && this.plateau[i - 1][j] != joueur.getCouleur() && this.plateau[i - 1][j] != ' ' && i == x){
            i = i - 1;
        }
        if(i > 0 && this.plateau[i - 1][j] == joueur.getCouleur()){
            return true;
        }

        i = x;
        while(i < this.taille - 1 && this.plateau[i + 1][j] != joueur.getCouleur() && this.plateau[i + 1][j] != ' ' && i == x){
            i = i + 1;
        }
        if(i < this.taille - 1 && this.plateau[i + 1][j] == joueur.getCouleur()){
            return true;
        }

        i = x;
        while(i > 0 && j > 0 && this.plateau[i - 1][j - 1] != joueur.getCouleur() && this.plateau[i - 1][j - 1] != ' ' && j == y && i == x){
            i = i - 1;
            j = j - 1;
        }
        if(j > 0 && i > 0 && this.plateau[i - 1][j - 1] == joueur.getCouleur()){
            return true;
        }

        i = x;
        j = y;
        while(i < this.taille - 1 && j < this.taille - 1 && this.plateau[i + 1][j + 1] != joueur.getCouleur() && this.plateau[i + 1][j + 1] != ' ' && j == y && i == x){
            i = i + 1;
            j = j + 1;
        }
        if(i < this.taille - 1 && j < this.taille - 1  && this.plateau[i + 1][j + 1] == joueur.getCouleur()){
            return true;
        }

        i = x;
        j = y;
        while(i > 0 && j < this.taille - 1 && this.plateau[i - 1][j + 1] != joueur.getCouleur() && this.plateau[i - 1][j + 1] != ' ' && j == y && i == x){
            i = i - 1;
            j = j + 1;
        }
        if(i > 0 && j < this.taille - 1 && this.plateau[i - 1][j + 1] == joueur.getCouleur()){
            return true;
        }

        i = x;
        j = y;
        while(i < this.taille - 1 && j > 0 && this.plateau[i + 1][j - 1] != joueur.getCouleur() && this.plateau[i + 1][j - 1] != ' ' && j == y && i == x){
            i = i + 1;
            j = j - 1;
        }
        if(i < this.taille - 1 && j > 0 && this.plateau[i + 1][j - 1] == joueur.getCouleur()){
            return true;
        }
        return false;
    }

    /*
     * 
     * Méthode fin_partie qui test toutes les positions, si aucun pion ne peut etre placer dans le Plateau alors la méthode renvoie true si non false
     * 
     * @param Joueur
     * @return boolean
     * 
     */
    public boolean fin_partie(Joueur joueur){
        for(int i = 0; i < this.taille; i = i + 1) {
            for(int j = 0; j < this.taille; j = j + 1) {
                if(fin_partie_aux(joueur, i, j) == true){
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * 
     * Méthode vainqueur qui nous renvoie une chaine de charatère qui annonce le vainqueur
     * 
     * @return String
     * 
     */
    public String vainqueur(){
        int N = 0;
        int B = 0;
        for(int i = 0; i < this.taille; i = i + 1) {
            for(int j = 0; j < this.taille; j = j + 1) {
                if(plateau[i][j] == 'N'){
                    N = N + 1;
                }
                else if(plateau[i][j] == B){
                    B = B + 1;
                }
            }
        }
        if(N == B){
            return "Il y a une égalité de " + N + " points entre les deux couleurs !";
        }
        else if(N > B){
            return "La joueur noir à gagné de " + N + " points contre " + B + " points pour je joueur blanc !";
        }
        return "La joueur blanc à gagné de " + B + " points contre " + N + " points pour le joueur noir !";
    }

    /*
     * 
     * Méthode setElement qui nous permet de placement un element dans plateau
     * 
     * @param int
     * @param int
     * @param char
     * 
     */
    public void setElement(int x, int y, char element){
        this.plateau[x][y] = element;
    }

    /*
     * 
     * Méthode getElement qui nous renvoie quel charactère est placer dans les coordonnées
     * 
     * @param int 
     * @param int
     * 
     * @return char
     * 
     */
    public char getElement(int x, int y){
        return this.plateau[x][y];
    }

    /*
     * 
     * Méthode nous permettant de renvoyer la copie du tableau.
     * 
     * @return char[][]
     * 
     */
    public char[][] getCopie(){
        char[][] copie = new char[taille][taille];
        for (int i = 0; i < this.taille; i = i + 1) {
            for (int j = 0; j < this.taille; j = j + 1) {
                copie[i][j] = plateau[i][j];
            }
        }
        return copie;
    }

    /*
     * 
     * Méthode getTaille permettant de renvoyer la taille du plateau
     * 
     * @return int
     * 
     */
    public int getTaille(){
        return this.taille;
    }

    /*
     * 
     * Méthode getPlateau qui nous renvoie le plateau
     * 
     * @retrun char[][]
     * 
     */
    public char[][] getPlateau(){
        return plateau;
    }
}
