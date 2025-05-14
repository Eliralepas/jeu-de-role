package affichage;

public abstract class Demande {

    public static int demandeEntier(int min, int max, String msgDemande) throws NumberFormatException{
        int entier = min - 1;
        String msgErreur = "/!\\ Vous devez entrer un nombre entier entre " + min + " et " + max + ". /!\\";
        while (!(min <= entier && entier <= max)) {
            System.out.println(msgDemande);
            String reponse = System.console().readLine();
            try {
                entier = Integer.parseInt(reponse);
                if(!(min <= entier && entier <= max)){
                    System.out.println(msgErreur);
                }
            } catch (NumberFormatException e) {
                System.out.println(msgErreur);
            }
        }
        return entier;
    }

    public static String demandeString(String msgDemande, int tailleMax){
        String chaine = "";
        while(chaine.isEmpty()){
            System.out.println(msgDemande);
            chaine = System.console().readLine();
            if (chaine.length() > tailleMax){
                chaine = "";
            }
        }
        return chaine;
    }
}
