import donjon.Donjon;
import personnages.Joueur;
import personnages.classes.Guerrier;
import personnages.races.Humain;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        Joueur Diego = new Joueur("Diego", new Humain(), new Guerrier());
        System.out.println(Diego.toString());
        Donjon d = new Donjon(15,15);
        d.remplir();
        System.out.println(d.m_plateau[0][0]);
    }
}