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
     * Constructeur permettant de se connecter � la base
     * 
     * @throws Exception
     */

    public ConnexionForm() throws Exception {
        this.db = DatabaseConnection.getInstance();
    }

    /**
     * Renvoi un bool�en si l'utilisateur existe d�j� ou non dans la base
     * 
     * @param pseudo
     * @param mdp
     * @return un bool�en
     * @throws Exception
     */

    public boolean existe( String pseudo, String mdp ) throws Exception {

        ResultSet rs = null;

        // On r�cup�re tous les utilisateurs
        rs = this.db.getData( "SELECT Pseudo, MotDePasse from utilisateur" );

        // On v�rifie si l'utilisateur est pr�sent
        while ( rs.next() )
            if ( rs.getString( "Pseudo" ).equals( pseudo.trim() ) && rs.getString( "MotDePasse" ).equals( mdp.trim() ) )
                return true;

        return false;

    }

    /**
     * Permet de faire connecter l'utilisateur si il est d�j� pr�sent dans la
     * base
     * 
     * @param request
     *            requ�te envoy�
     * @return un utilisateur
     * @throws Exception
     */

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) throws Exception {

        /* R�cup�ration des champs du formulaire */
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );

        if ( verificationChamps( pseudo, motDePasse ) )
            if ( this.existe( pseudo, motDePasse ) ) {

                Utilisateur utilisateur = new Utilisateur( pseudo, motDePasse );
                resultat = "Connexion réussie\n";
                return utilisateur;
            } else {

                resultat = "Echec de la connexion, cet utilisateur n'existe pas\n";

            }

        return null;
    }

    /**
     * Permet de v�rifier les donn�es rentr�es par l'utilisateur
     * 
     * @param pseudo
     * @param motDePasse
     * @return bool�en : true si les donn�es son saisies ( aucun champ vide ),
     *         false sinon
     */

    private boolean verificationChamps( String pseudo, String motDePasse ) {

        // Si ni le pseudo et motdepasse non �taient saisi
        if ( pseudo == null && motDePasse == null ) {
            resultat = "Veuillez rentrer votre mot de passe et votre pseudo\n";
            return false;
            // si le pseudo na pas �t� saisi
        } else if ( pseudo == null ) {
            resultat = "Veuillez rentrer votre pseudo\n";
            return false;
            // si le mot de pase n'a pas �t� saisi
        } else if ( motDePasse == null ) {
            resultat = "Veuillez rentrer un mot de passe\n";
            return false;
            // si tous a �t� saisi
        } else
            return true;

    }

    /**
     * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
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