package model;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.DatabaseConnection;
import model.Utilisateur;

public final class ConnexionForm {

    private static final String CHAMP_PASS   = "password";
    private static final String CHAMP_PSEUDO = "pseudo";

    private String              resultat;

    private Map<String, String> erreurs      = new HashMap<String, String>();
    private DatabaseConnection  db;

    public ConnexionForm() throws Exception {

        this.db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                "com.mysql.cj.jdbc.Driver" );

    }

    /**
     * Renvoi un booléen si l'utilisateur existe déjà ou non dans la base
     * 
     * @param pseudo
     * @param mdp
     * @return
     * @throws Exception
     */

    public boolean existe( String pseudo, String mdp ) throws Exception {

        ResultSet rs = null;

        // On récupère tous les utilisateurs
        rs = this.db.getData( "SELECT Pseudo, MotDePasse from utilisateur" );

        // On vérifie si l'utilisateur est présent
        while ( rs.next() )
            if ( rs.getString( "Pseudo" ).equals( pseudo ) && rs.getString( "MotDePasse" ).equals( mdp ) )
                return true;

        return false;

    }

    /**
     * Permet de faire connecter l'utilisateur si il est déjà présent dans la
     * base
     * 
     * @param request
     * @return
     * @throws Exception
     */

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) throws Exception {

        /* Récupération des champs du formulaire */
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );

        /* On vérfie si l'utilisateur existe déjà dans la base */

        if ( this.existe( pseudo, motDePasse ) ) {

            Utilisateur utilisateur = new Utilisateur( pseudo, motDePasse );

            /* Validation du champ mot de passe. */
            try {
                validationMotDePasse( motDePasse );
            } catch ( Exception e ) {
                setErreur( CHAMP_PASS, e.getMessage() );
            }
            utilisateur.setPassword( motDePasse );

            /* Initialisation du résultat global de la validation. */
            if ( erreurs.isEmpty() ) {
                resultat = "Succès de la connexion.";
            } else {
                resultat = "Échec de la connexion.";
            }

            return utilisateur;
        } else {
            setErreur( CHAMP_PASS, "Cet utilisateur n'existe pas" );
            return null;
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

    // GETTERS ET SETTERS
    // ------------------------------------------------------------------------------------------------------

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

}