import personnages.Personnage;
import personnages.classes.Guerrier;
import personnages.races.Humain;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        Personnage Diego = new Personnage("Diego", new Humain(), new Guerrier());
        System.out.println(Diego.toString());
    }
}