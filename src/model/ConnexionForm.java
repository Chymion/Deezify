package model;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

/**
 * Permet de connecter un utilisateur
 * 
 * @author guill
 *
 */

public final class ConnexionForm {

    private static final String CHAMP_PASS   = "password";
    private static final String CHAMP_PSEUDO = "pseudo";

    private String              resultat;
    private DatabaseConnection  db;

    /**
     * Constructeur permettant de se connecter à la base
     * 
     * @throws Exception
     */

    public ConnexionForm() throws Exception {

        this.db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                "com.mysql.cj.jdbc.Driver" );

    }

    /**
     * Renvoi un booléen si l'utilisateur existe déjà ou non dans la base
     * 
     * @param pseudo
     * @param mdp
     * @return un booléen
     * @throws Exception
     */

    public boolean existe( String pseudo, String mdp ) throws Exception {

        ResultSet rs = null;

        // On récupère tous les utilisateurs
        rs = this.db.getData( "SELECT Pseudo, MotDePasse from utilisateur" );

        // On vérifie si l'utilisateur est présent
        while ( rs.next() )
            if ( rs.getString( "Pseudo" ).equals( pseudo.trim() ) && rs.getString( "MotDePasse" ).equals( mdp.trim() ) )
                return true;

        return false;

    }

    /**
     * Permet de faire connecter l'utilisateur si il est déjà présent dans la
     * base
     * 
     * @param request
     *            requête envoyé
     * @return un utilisateur
     * @throws Exception
     */

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) throws Exception {

        /* Récupération des champs du formulaire */
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );

        if ( verificationChamps( pseudo, motDePasse ) )
            if ( this.existe( pseudo, motDePasse ) ) {

                Utilisateur utilisateur = new Utilisateur( pseudo, motDePasse );
                resultat = "Connexion réussie\n";
                return utilisateur;
            } else {

                resultat = "Échec de la connexion, cet utilisateur n'existe pas\n";

            }

        return null;
    }

    /**
     * Permet de vérifier les données rentrées par l'utilisateur
     * 
     * @param pseudo
     * @param motDePasse
     * @return booléen : true si les données son saisies ( aucun champ vide ),
     *         false sinon
     */

    private boolean verificationChamps( String pseudo, String motDePasse ) {

        // Si ni le pseudo et motdepasse non étaient saisi
        if ( pseudo == null && motDePasse == null ) {
            resultat = "Veuillez rentrer votre mot de passe et votre pseudo\n";
            return false;
            // si le pseudo na pas été saisi
        } else if ( pseudo == null ) {
            resultat = "Veuillez rentrer votre pseudo\n";
            return false;
            // si le mot de pase n'a pas été saisi
        } else if ( motDePasse == null ) {
            resultat = "Veuillez rentrer un mot de passe\n";
            return false;
            // si tous a été saisi
        } else
            return true;

    }

    /**
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * 
     * @param request
     * @param nomChamp
     * @return retourne nomChamp en enlevant les espaces
     */

    private String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

    // GETTERS ET SETTERS
    // ------------------------------------------------------------------------------------------------------

    /**
     * 
     * @return les commentaires pour l'utilisateur
     */

    public String getResultat() {
        return resultat;
    }

}