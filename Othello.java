public class Othello {
    /*
     * 
     * La classe Othello nous permet de "manager" une partie de Othello, elle comporte les différents joueur, le plateau, le compteur, ainsi que le tour des joueurs
     * 
     */
    private Joueur joueur_a;
    private Joueur joueur_b;
    private Plateau plateau;
    private int compteur;
    private boolean tour_joueur1;

    /*
     * 
     * Constructeur de la classe Othello
     * 
     * @param Plateau
     * @param Joueur
     * @param Joueur
     * 
     */
    public Othello(Plateau plateau, Joueur joueur_a, Joueur joueur_b){
        this.plateau = plateau;
        this.joueur_a = joueur_a;
        this.joueur_b = joueur_b;
        compteur = plateau.getTaille() * plateau.getTaille() - 4;
        this.tour_joueur1 = true;
    }

    /*
     * 
     * Méthode jouer nous permettant de joueur un coup dans le jeu et nous retourne true si le joueur peut jouer et false si non
     * 
     * @param Joueur
     * @param int
     * @param int
     * 
     * @return boolean
     * 
     */
    public boolean jouer(Joueur joueur, int x, int y){
        if(this.plateau.Coup(joueur, x, y) == false){
            return false;
        }
        this.plateau.Coup(joueur, x, y);
        if(tour_joueur1 == true){
            tour_joueur1 = false;
            return true;
        }
        tour_joueur1 = true;
        return true;
    }

    /*
     * 
     * Méthode getTourJoueur1 nous permettant de renvoyer la varible tour_joueur1 d'une instance de Othello
     * 
     * @return boolean
     * 
     */
    public boolean getTourJoueur1(){
        return tour_joueur1;
    }

    /*
     * 
     * Méthode setTourJoueur1 qui nous permet de changer la variable tour_joueur1 d'une instance de Othello
     * 
     * @param boolean
     * 
     */
    public void setTourJoueur1(boolean tour_joueur1){
        this.tour_joueur1 = tour_joueur1;
    }

    /*
     * 
     * Méthode setCompteur qui nous permet de changer la variable compteur d'une instance de Othello
     * 
     * @param int
     * 
     */
    public void setCompteur(int compteur){
        this.compteur = compteur;
    }

    /*
     * 
     * Méthode qui renvoie le plateau d'une instance de Othello
     * 
     * @return Plateau
     * 
     */
    public Plateau getPlateau(){
        return this.plateau;
    }

    /*
     * 
     * Méthode permettant de renvoyer le compteur d'une instance de Othello
     * 
     * @return int
     * 
     */
    public int getCompteur(){
        return this.compteur;
    }

    /*
     * 
     * Méthode permettant de renvoyer le joueur_a d'une instance de Othello
     * 
     * @retrun Joueur
     * 
     */
    public Joueur getJoueurA(){
        return joueur_a;
    }

    /*
     * 
     * Méthode permettant de renvoyer le joueur_b d'une instance de Othello
     * 
     * @retrun Joueur
     * 
     */
    public Joueur getJoueurB(){
        return joueur_b;
    }
}
