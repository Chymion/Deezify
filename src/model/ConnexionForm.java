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

        /* On vérfie si l'utilisateur existe déjà dans la base */

        if ( this.existe( pseudo, motDePasse ) ) {

            Utilisateur utilisateur = new Utilisateur( pseudo, motDePasse );
            resultat = "Connexion réussie";
            return utilisateur;

        } else {

            resultat = "Échec de la connexion, cet utilisateur n'existe pas";
            return null;

        }
    }

    /**
     * Valide le mot de passe saisi si il est correct ou pas
     * 
     * @param motDePasse
     * @throws Exception
     */

    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                resultat += "Le mot de passe doit contenir au moins 3 caractères.";
            }
        } else {
            resultat += "Merci de saisir votre mot de passe.";
        }
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