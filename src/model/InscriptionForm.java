package model;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

/**
 * Cette classe permet d'ins�rer un nouvelle utilisateur dans la base de donn�es
 * 
 * @author guill
 */

public class InscriptionForm {

    private static final String CHAMP_PASS   = "motDePasse";
    private static final String CHAMP_PSEUDO = "pseudo";
    private static final String CHAMP_NOM    = "nom";
    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_EMAIL  = "email";

    private String              resultat     = "";
    private DatabaseConnection  db;

    /**
     * Constructeur permettant de se connecter � la base
     * 
     * @throws Exception
     */

    public InscriptionForm() throws Exception {

        this.db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                "com.mysql.cj.jdbc.Driver" );

    }

    /**
     * Ins�re un nouvelle utilisateur si les conditions sont remplies
     * 
     * @param request
     * @throws Exception
     */

    public void creerUtilisateur( HttpServletRequest request ) throws Exception {

        if ( stringToHtmlString( request.getParameter( "prenom" ) )
                || stringToHtmlString( request.getParameter( "nom" ) )
                || stringToHtmlString( request.getParameter( "pseudo" ) )
                || stringToHtmlString( request.getParameter( "motDePasse" ) ) ) {
            this.resultat += "\nVous ne pouvez pas mettre les caract�res suivants &, <, >,\"";
        }

        else if ( request.getParameter( "prenom" ).trim().equals( "" )
                || request.getParameter( "nom" ).trim().equals( "" )
                ||
                request.getParameter( "pseudo" ).trim().equals( "" )
                || request.getParameter( "email" ).trim().equals( "" ) ||
                request.getParameter( "motDePasse" ).trim().equals( "" ) ) {

            this.resultat += "\nVous avez oubli� de remplir des informations";

        } else {

            String motDePasse = ( this.getValeurChamp( request, CHAMP_PASS ) );
            String pseudo = this.getValeurChamp( request, CHAMP_PSEUDO );
            String nom = this.getValeurChamp( request, CHAMP_NOM );
            String prenom = this.getValeurChamp( request, CHAMP_PRENOM );
            String email = this.getValeurChamp( request, CHAMP_EMAIL );

            if ( !this.estDansBase( pseudo, email ) && ( this.validationEmail( email ) )
                    && this.validationMotDePasse( motDePasse ) ) {
                this.resultat = "Votre compte a bien �tait cr�� !! Connectez vous pour y acc�der";
                new Utilisateur( prenom, nom, pseudo, email, motDePasse );
            }

        }

    }

    /**
     * Permet de savoir si les identifiants souhait�s ne sont pas d�j� utilis�s
     * par un autre utilisateur
     * 
     * @param pseudo
     * @param email
     * @return un bool�en nous informant si les donn�es envoy�es sont d�j�
     *         utilis�es par un autre utilisateur
     * @throws Exception
     */

    private boolean estDansBase( String pseudo, String email ) throws Exception {

        // On r�cup�re tous les utilisateurs
        ResultSet rs = null;
        rs = this.db.getData( "SELECT Pseudo, Email from utilisateur" );

        while ( rs.next() ) {

            if ( rs.getString( "Pseudo" ).equals( pseudo.trim() ) ) {
                resultat = "\nUn utilisateur a d�j� le m�me pseudo que vous !!";
                return true;
            }

            if ( rs.getString( "Email" ).equals( email.trim() ) ) {
                resultat = "\nUn utilisateur a d�j� la m�me adresse que vous !!";
                return true;
            }

        }

        return false;

    }

    /**
     * Permet de savoir si le mot de passe souhait� respecte les conditions
     * 
     * @param motDePasse
     * @return un bool�en : true si le mot est sup�rieur � 3, false sinon
     * @throws Exception
     */

    private boolean validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                resultat += "\nLe mot de passe doit contenir au moins 3 caract�res.";
                return false;
            }
        } else {
            resultat += "\nMerci de saisir votre mot de passe.";
            return false;
        }
        return true;
    }

    /**
     * Permet de savoir si l'�mail souhait� respecte les conditions
     * 
     * @param email
     * @return un bool�en : true si la chaine ressemble � une adresse email,
     *         false sinon
     * @throws Exception
     */

    private boolean validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            this.resultat += "\nMerci de saisir une adresse mail valide.";
            return false;
        }
        return true;
    }

    /**
     * 
     * @param request
     * @param nomChamp
     * @return String : nomChamp sans les espaces en trop
     */

    private String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

    /**
     * 
     * @param s
     * @return la donn�e entr�e par l'utilisateur sans les failles XSS
     */

    private boolean stringToHtmlString( String s ) {
        boolean b = false;
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for ( int i = 0; i < n; i++ ) {
            char c = s.charAt( i );
            switch ( c ) {
            case '<':
                b = true;
                break;
            case '>':
                b = true;
                break;
            case '&':
                b = true;
                break;
            case '"':
                b = true;
                break;
            default:
                b = false;
                break;
            }
        }
        return b;
    }

    // GETTERS ET SETTERS
    // ------------------------------------------------------------------------------------------------------

    /**
     * 
     * @return resultat
     */

    public String getResultat() {
        return resultat;
    }

}
