import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue sur le jeu Othello !");
        System.out.println("0 pour commencer une nouvelle partie | 1 pour charger une ancienne partie :");
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int choix_partie = sc.nextInt();
        if(choix_partie == 1){
            System.out.println("Veuillez le nom de votre partie :");
            Scanner nom_jeux = new Scanner(System.in);
            String partie = nom_jeux.nextLine();
            Othello jeux = loadGame(partie);
            Joueur joueur1 = jeux.getJoueurA();
            Joueur joueur2 = jeux.getJoueurB();
            Plateau plateau = jeux.getPlateau();
            if(joueur2.getIs_IA() == false){
                while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                    if(jeux.getTourJoueur1() == true){
                        plateau.afficher();
                        System.out.println("C'est au tour de " + joueur1.getNom());
                        System.out.println("Entrez les coordonnées y et x :");
                        int x = sc.nextInt() - 1;
                        int y = sc.nextInt() - 1;
                        if(x == -3 && y == -3){
                            saveGame(jeux, partie);
                            nom_jeux.close();
                            return;
                        }
                        while(jeux.jouer(joueur1, x, y) != true){
                            System.out.println("Coup impossible !");
                            x = sc.nextInt() - 1;
                            y = sc.nextInt() - 1;
                        }
                        jeux.jouer(joueur1, x, y);
                    }
                    else{
                        plateau.afficher();
                        System.out.println("C'est au tour de " + joueur2.getNom());
                        System.out.println("Entrez les coordonnées y et x :");
                        int x = sc.nextInt() - 1;
                        int y = sc.nextInt() - 1;
                        while(jeux.jouer(joueur2, x, y) != true){
                            x = sc.nextInt();
                            y = sc.nextInt();
                        }
                        jeux.jouer(joueur2, x, y);
                    }
                }
            }
            else{
                IA IA = new IA(joueur2, jeux);
                while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                    if(jeux.getTourJoueur1() == true){
                        plateau.afficher();
                        System.out.println("C'est au tour de " + joueur1.getNom());
                        System.out.println("Entrez les coordonnées y et x :");
                        int x = sc.nextInt() - 1;
                        int y = sc.nextInt() - 1;
                        while(jeux.jouer(joueur1, x, y) != true){
                            System.out.println("Coup impossible !");
                            x = sc.nextInt() - 1;
                            y = sc.nextInt() - 1;
                        }
                        jeux.jouer(joueur1, x, y);
                    }
                    else{
                        IA.IA_naif();
                    }
                }
                nom_jeux.close();
            }
        }
        else if(choix_partie == 0){
            Scanner joueur = new Scanner(System.in);
            System.out.println("Veuillez saisir le nom de votre partie :");
            String nom_partie = joueur.nextLine();
            System.out.println("Vous pourrez sauvgarder la partie à tout moment en entrant les coordonnées : -2 et -2");
            System.out.println("0 pour Joueur vs Joueur | 1 pour Joueur vs IA :");
            int choix_joueur = joueur.nextInt();
            if(choix_joueur == 0){
                Scanner nom_joueur = new Scanner(System.in);
                System.out.println("Veuillez entrer le nom des deux joueurs :");
                String nom_joueur1 = nom_joueur.nextLine();
                String nom_joueur2 = nom_joueur.nextLine();
                if(rand.nextInt(2) == 1){
                    Joueur joueur1 = new Joueur('N', nom_joueur1);
                    Joueur joueur2 = new Joueur('B', nom_joueur2);
                    System.out.println("Les pions noirs ont été attribué à " + nom_joueur1);
                    System.out.println("Veuillez entrez la taille du tableau souhaitez (0 pour taille par défaut) :");
                    Scanner taille_plateau = new Scanner(System.in);
                    int choix_taille = taille_plateau.nextInt();
                    if(choix_taille == 0){
                        Plateau plateau = new Plateau();
                        Othello jeux = new Othello(plateau, joueur1, joueur2);
                        jeux.setTourJoueur1(true);
                        while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                            if(jeux.getTourJoueur1() == true){
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur1.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur1, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur1, x, y);
                            }
                            else{
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur2.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur2, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur2, x, y);
                            }
                        }
                        System.out.println(plateau.vainqueur());
                    }
                    else{
                        Plateau plateau = new Plateau(choix_taille);
                        Othello jeux = new Othello(plateau, joueur1, joueur2);
                        jeux.setTourJoueur1(true);
                        while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                            if(jeux.getTourJoueur1() == true){
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur1.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur1, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur1, x, y);
                            }
                            else{
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur2.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur2, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur2, x, y);
                            }
                        }
                        System.out.println(plateau.vainqueur());
                    }
                    taille_plateau.close();
                }
                else{
                    Joueur joueur1 = new Joueur('B', nom_joueur1);
                    Joueur joueur2 = new Joueur('N', nom_joueur2);
                    System.out.println("Les pions noirs ont été attribué à " + nom_joueur2);
                    System.out.println("Veuillez entrez la taille du tableau souhaitez (0 pour taille par défaut) :");
                    Scanner taille_plateau = new Scanner(System.in);
                    int choix_taille = taille_plateau.nextInt();
                    if(choix_taille == 0){
                        Plateau plateau = new Plateau();
                        Othello jeux = new Othello(plateau, joueur1, joueur2);
                        jeux.setTourJoueur1(false);
                        while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                            if(jeux.getTourJoueur1() == true){
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur1.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur1, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur1, x, y);
                            }
                            else{
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur2.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur2, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur2, x, y);
                            }
                        }
                        System.out.println(plateau.vainqueur());
                    }
                    else{
                        Plateau plateau = new Plateau(choix_taille);
                        Othello jeux = new Othello(plateau, joueur1, joueur2);
                        jeux.setTourJoueur1(false);
                        while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                            if(jeux.getTourJoueur1() == true){
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur1.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur1, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur1, x, y);
                            }
                            else{
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur2.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur2, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur2, x, y);
                            }
                        }
                        System.out.println(plateau.vainqueur());
                    }
                    taille_plateau.close();
                }
                nom_joueur.close();
            }
            else if(choix_joueur == 1){
                System.out.println("Veuillez entrer le nom du joueur :");
                Scanner nom_joueur = new Scanner(System.in);
                String nom_joueur1 = nom_joueur.nextLine();
                if(rand.nextInt(2) == 1){
                    Joueur joueur1 = new Joueur('N', nom_joueur1);
                    Joueur joueur2 = new Joueur('B', "IA");
                    System.out.println("Veuillez entrez la taille du tableau souhaitez (0 pour taille par défaut) :");
                    Scanner taille_plateau = new Scanner(System.in);
                    int choix_taille = taille_plateau.nextInt();
                    if(choix_taille == 0){
                        Plateau plateau = new Plateau();
                        Othello jeux = new Othello(plateau, joueur1, joueur2);
                        IA IA = new IA(joueur2, jeux);
                        jeux.setTourJoueur1(true);
                        while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                            if(jeux.getTourJoueur1() == true){
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur1.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur1, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur1, x, y);
                            }
                            else{
                                IA.IA_naif();
                            }
                        }
                        System.out.println(plateau.vainqueur());
                    }
                    else{
                        Plateau plateau = new Plateau(choix_taille);
                        Othello jeux = new Othello(plateau, joueur1, joueur2);
                        IA IA = new IA(joueur2, jeux);
                        jeux.setTourJoueur1(true);
                        while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                            if(jeux.getTourJoueur1() == true){
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur1.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur1, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur1, x, y);
                            }
                            else{
                                IA.IA_naif();
                            }
                        }
                        System.out.println(plateau.vainqueur());
                    }
                    taille_plateau.close();
                }
                else{
                    Joueur joueur1 = new Joueur('B', nom_joueur1);
                    Joueur joueur2 = new Joueur('N', "IA");
                    System.out.println("Veuillez entrez la taille du tableau souhaitez (0 pour taille par défaut) :");
                    Scanner taille_plateau = new Scanner(System.in);
                    int choix_taille = taille_plateau.nextInt();
                    if(choix_taille == 0){
                        Plateau plateau = new Plateau();
                        Othello jeux = new Othello(plateau, joueur1, joueur2);
                        IA IA = new IA(joueur2, jeux);
                        jeux.setTourJoueur1(false);
                        while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                            if(jeux.getTourJoueur1() == true){
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur1.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur1, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur1, x, y);
                            }
                            else{
                                IA.IA_naif();
                            }
                        }
                        System.out.println(plateau.vainqueur());
                    }
                    else{
                        Plateau plateau = new Plateau(choix_taille);
                        Othello jeux = new Othello(plateau, joueur1, joueur2);
                        IA IA = new IA(joueur2, jeux);
                        jeux.setTourJoueur1(true);
                        while(jeux.getCompteur() > 0 || (plateau.fin_partie(joueur1) != true && plateau.fin_partie(joueur2) != true)){
                            if(jeux.getTourJoueur1() == true){
                                plateau.afficher();
                                System.out.println("C'est au tour de " + joueur1.getNom());
                                System.out.println("Entrez les coordonnées y et x :");
                                int x = sc.nextInt() - 1;
                                int y = sc.nextInt() - 1;
                                if(x == -3 && y == -3){
                                    saveGame(jeux, nom_partie);
                                    taille_plateau.close();
                                    return;
                                }
                                while(jeux.jouer(joueur1, x, y) != true){
                                    System.out.println("Coup impossible !");
                                    x = sc.nextInt() - 1;
                                    y = sc.nextInt() - 1;
                                }
                                jeux.jouer(joueur1, x, y);
                            }
                            else{
                                IA.IA_naif();
                            }
                        }
                        System.out.println(plateau.vainqueur());
                    }
                    taille_plateau.close();
                }
                nom_joueur.close();
            }
            joueur.close();
        }
        sc.close();
    }

    public static void saveGame(Othello othello, String nom_sauvegarde){
        try{
            FileWriter writer = new FileWriter(nom_sauvegarde + ".txt");
            writer.write(othello.getJoueurA().getNom() + "," + othello.getJoueurA().getCouleur() + "," + othello.getJoueurB().getNom() + "," + othello.getJoueurB().getCouleur() + "," + othello.getTourJoueur1() + "," + othello.getPlateau().getTaille() + "," + othello.getCompteur() + "," + othello.getJoueurA().getIs_IA() + "," + othello.getJoueurB().getIs_IA());
            for(int i = 0; i < othello.getPlateau().getTaille(); i = i + 1){
                for(int j = 0; j < othello.getPlateau().getTaille(); j = j + 1){
                    writer.write("," + othello.getPlateau().getElement(i, j));
                }
            }
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Othello loadGame(String nom_sauvegarde){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(nom_sauvegarde + ".txt"));
            String line = reader.readLine();
            String[] parts = line.split(",");
            Joueur joueur1 = new Joueur(parts[1].charAt(0), parts[0]);
            Joueur joueur2 = new Joueur(parts[3].charAt(0), parts[2]);
            Plateau plateau = new Plateau(Integer.parseInt(parts[5]));
            int coup = 0;
            int placement = 9;
            for(int i = 0; i < plateau.getTaille(); i = i + 1){
                for(int j = 0; j < plateau.getTaille(); j = j + 1){
                    if(parts[placement].charAt(0) == ' '){
                        placement = placement + 1;
                    }
                    else{
                        plateau.setElement(i, j, parts[placement].charAt(0));
                        coup = coup + 1;
                        placement = placement + 1;
                    }
                }
            }
            Othello jeux = new Othello(plateau, joueur1, joueur2);
            jeux.setCompteur(jeux.getCompteur() - coup);
            jeux.setTourJoueur1(Boolean.parseBoolean(parts[4]));
            joueur1.setIs_IA(Boolean.parseBoolean(parts[7]));
            joueur2.setIs_IA(Boolean.parseBoolean(parts[8]));
            reader.close();
            return jeux;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
