package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

/***
 * Initialisation du contexte OpenAL. Permet de jouer un fichier audio au format
 * wav
 * 
 * @author Orane ADJALI
 *
 */

public class AudioMaster {

    // Buffer contenant le flux de donnees de la musique. Et un entier
    // contenant la source
    private static ArrayList<Integer> buffers = new ArrayList<Integer>();
    private static int                sourceID;
    private static String             songName;
    private static float 			  volume = 1.0f;
    private static float 			  pitch = 1.0f;
    private  static boolean 		  count = false;
    private static boolean 			  firstClick = false;
    /***
     * Initialisation du contexte OpenAL
     */
    public static void init() {
        try {
            AL.create();
        } catch ( LWJGLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /***
     * Joue le buffer d'entier passé en paramètre
     * 
     * @param buffer
     */
    public static void play( int buffer ) {
        AL10.alSourcei( sourceID, AL10.AL_BUFFER, buffer );
        AL10.alSourcePlay( sourceID );
    }

    public void pause() {
        AL10.alSourcePause( sourceID );
    }

    public static void setVolume( float volume ) {
        AL10.alSourcef( sourceID, AL10.AL_GAIN, volume );
    }

    public static void modifierPitch( float pitch ) {
        AL10.alSourcef( sourceID, AL10.AL_PITCH, pitch );
    }

    public void continuer() {
        AL10.alSourcePlay( sourceID );
    }

    public static void setSongName( String name ) {
       songName = name;
    }

    public static String getSongName() {
        return songName;
    }

    /***
     * Charge la musique passée en paramètre (String) dans un buffer
     * Specification des proprietes elementaires de la source: Gain, Pitch,
     * Position
     * 
     * @param file
     * @return buffer
     */
    public static int chargerMusique( String file ) {
        // Chargement des bits composant le fichier audio dans le buffer.
        int buffer = AL10.alGenBuffers();
        buffers.add( buffer );
        WaveData waveFile = WaveData.create( file );
        AL10.alBufferData( buffer, waveFile.format,
                waveFile.data, waveFile.samplerate );
        waveFile.dispose();
        // Parametrage des proprietes de la source
        sourceID = AL10.alGenSources();
        AL10.alSourcef( sourceID, AL10.AL_GAIN, 1 );
        AL10.alSourcef( sourceID, AL10.AL_PITCH, 1 );
        AL10.alSource3f( sourceID, AL10.AL_POSITION, 0, 0, 0 );
        return buffer;
    }

    public static void startSong(HttpServletRequest request, HttpSession session)
    {
    	if ( request.getParameter( "music" ) != null ) {

            if ( session.getAttribute( "count" ) == null )
                session.setAttribute( "count", count );

            // Pitch remis par d�faut lors de la s�lection d'une nouvelle
            // musique
            session.setAttribute( "pitch", 1.0f );
            session.setAttribute("vol", volume);

            firstClick = (boolean) session.getAttribute( "click" );
            if ( !firstClick ) {
                firstClick = true;
                session.setAttribute( "click", firstClick );
                init();
                setSongName( request.getParameter( "music" ) );
                demarrer();
            } else {

                count = false;
                Destruction();
                init();
                setSongName( request.getParameter( "music" ) );
                demarrer();
            }
            session.setAttribute( "count", count );
        }
    }
    
    public static void gestionEvenements(HttpServletRequest request, HttpSession session)
    {
    	session.setAttribute( "vol", volume );
        session.setAttribute( "pitch", pitch );
    	 if ( request.getParameter( "boutonPlay" ) != null ) {
             if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
                 ( (AudioMaster) session.getAttribute( "audio" ) ).pause();
                 session.setAttribute( "count", true );
             } else {
                 ( (AudioMaster) session.getAttribute( "audio" ) ).continuer();
                 session.setAttribute( "count", false );
             }
         }

         if ( request.getParameter( "boutonLow" ) != null ) {
             volume = (float) session.getAttribute( "vol" );
             session.setAttribute( "vol", volume /= 2.3f );
             AudioMaster.setVolume( (float) session.getAttribute( "vol" ) );
         }

         if ( request.getParameter( "boutonUp" ) != null ) {
             volume = (float) session.getAttribute( "vol" );
             session.setAttribute( "vol", volume *= 2.3f );
             AudioMaster.setVolume( (float) session.getAttribute( "vol" ) );

         }

         if ( request.getParameter( "boutonFaster" ) != null ) {
             pitch = (float) session.getAttribute( "pitch" );
             session.setAttribute( "pitch", pitch += 0.1f );
             AudioMaster.modifierPitch( (float) session.getAttribute( "pitch" ) );
         }

         if ( request.getParameter( "boutonSlower" ) != null ) {
             pitch = (float) session.getAttribute( "pitch" );
             session.setAttribute( "pitch", pitch -= 0.1f );
             AudioMaster.modifierPitch( (float) session.getAttribute( "pitch" ) );
         }
    }
    
    // Liberation des ressources
    public static void Destruction() {
        AL.destroy();
    }

    /***
     * Creation d'un objet musique, recuperation du chemin vers la musique et
     * lancement de la musique
     */
    public static void demarrer() {
        Musique mod = null;
        try {
            mod = new Musique( getSongName() );
            System.out.println( "Musique en cours: " + mod.getChemin() );
            int buffer = chargerMusique( "controller/" + mod.getChemin() );
            play( buffer );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}