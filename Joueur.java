public class Joueur {
    /*
     * 
     * Classe Joueur permettant de simuler un Joueur dans le jeu othello elle comporte le nom de nom du joueur, sa couleur et un boolean pour savoir si c'est une IA.
     * 
     */
    private char couleur;
    private String nom;
    private boolean is_IA; 

    /*
     * 
     * Constructeur de la classe Joueur
     * 
     * @param char
     * @param String
     * 
     */
    public Joueur(char couleur, String nom){
        this.couleur = couleur;
        this.nom = nom;
        this.is_IA = false;
    }

    /*
     * 
     * Méthode getCouleur qui nous renvoie la couleur du joueur
     * 
     * @return char
     * 
     */
    public char getCouleur(){
        return this.couleur;
    }

    /*
     * 
     * Méthode getNom qui nous renvoie le nom du joueur
     * 
     * @return String
     * 
     */
    public String getNom(){
        return this.nom;
    }

    /*
     * 
     * Méthode setCouleur qui nous permet de changer la couleur d'une instance de la classe Joueur
     * 
     * @param char
     * 
     */
    public void setCouleur(char couleur){
        this.couleur = couleur;
    }

    /*
     * 
     * Méthode setNom qui nous permet de changer le nom d'une instance de la classe Joueur
     * 
     * @param String
     * 
     */
    public void setNom(String nom){
        this.nom = nom;
    }

    /*
     * 
     * Méthode setIs_IA qui nous permet de changer la varibale is_IA d'une instance de la classe Joueur
     * 
     * @param boolean
     * 
     */
    public void setIs_IA(boolean is_IA){
        this.is_IA = is_IA;
    }

    /*
     * 
     * Méthode getIs_IA permettant de renvoyer la varible is_IA d'une instance de la classe Joueur
     * 
     * @return boolean
     * 
     */
    public boolean getIs_IA(){
        return this.is_IA;
    }
}
