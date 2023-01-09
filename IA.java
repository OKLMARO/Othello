import java.util.Random;

public class IA {
    /*
     * 
     * Classe IA permettante de Joueur à la place d'un joueur, elle comporte une instance Joueur et une instance de Jeux
     * 
     */
    private Joueur joueur;
    private Othello jeux;

    /*
     * 
     * Constructeur de la classe IA
     * 
     * @param Joueur
     * @param Othello
     * 
     */
    public IA(Joueur joueur, Othello jeux){
        this.joueur = joueur;
        this.jeux = jeux;
    }

    /*
     * 
     * Méthode IA_naif qui permet de jouer dans une position aléatoire du plateau.
     * 
     */
    public void IA_naif(){
        Random rand = new Random();
        int x = rand.nextInt(jeux.getPlateau().getTaille());
        int y = rand.nextInt(jeux.getPlateau().getTaille());
        while(jeux.jouer(joueur, x, y) != true){
            x = rand.nextInt(jeux.getPlateau().getTaille());
            y = rand.nextInt(jeux.getPlateau().getTaille());
        }
        jeux.jouer(joueur, x, y);
    }


    /*
     * 
     * Méthode getJeux permettant de renvoyer une instance de la classe Othello
     * 
     * @return Othello
     * 
     */
    public Othello getJeux(){
        return jeux;
    }
    

    /*
     * 
     * Méthode getJoueur permettant de renvoyer une instance de la classe Joueur
     * 
     * @return Joueur
     * 
     */
    public Joueur getJoueur(){
        return joueur;
    }

    /*
     * 
     * Méthode setJeux permettant de remplacer l'instance de Othello
     * 
     * @param Othello
     */
    public void setJeux(Othello jeux){
        this.jeux = jeux;
    }

    /*
     * 
     * Méthode setCouleurIA qui permet de sélectionner la couleur que l'IA va jouer
     * 
     * @param char
     * 
     */
    public void setCouleurIA(char couleur){
        this.joueur.setCouleur(couleur);
    }
}
