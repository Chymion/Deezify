package model;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class AudioMaster {

    // Buffer contenant le flux de données de la musique. Et un entier
    // contenant la source
    private static ArrayList<Integer> buffers = new ArrayList<Integer>();
    private static int                sourceID;
    private String                    songName;

    // Initialisation de OpenAL
    public void init() {
        try {
            AL.create();
        } catch ( LWJGLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void playSong( int buf ) throws IOException {
        // Si on entre la lettre 'p', la musique se lance.
        // Si on entre la lettre 'q', la musique se quitte
        char c = ' ';
        while ( c != 'q' ) {
            c = (char) System.in.read();

            if ( c == 'p' ) {
                play( buf );
            }
        }
    }

    // Jouer la source
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

    public void continuer() {
        AL10.alSourcePlay( sourceID );
    }

    public void setSongName( String name ) {
        this.songName = name;
    }

    public String getSongName() {
        return this.songName;
    }

    public static int chargerMusique( String file ) {
        // Chargement des bits composant le fichier audio dans le buffer.
        int buffer = AL10.alGenBuffers();
        buffers.add( buffer );
        WaveData waveFile = WaveData.create( file );
        AL10.alBufferData( buffer, waveFile.format,
                waveFile.data, waveFile.samplerate );
        waveFile.dispose();
        sourceID = AL10.alGenSources();
        AL10.alSourcef( sourceID, AL10.AL_GAIN, 1 );
        AL10.alSourcef( sourceID, AL10.AL_PITCH, 1 );
        AL10.alSource3f( sourceID, AL10.AL_POSITION, 0, 0, 0 );
        return buffer;
    }

    // Lib�ration des ressources
    public void Destruction() {
        AL.destroy();
    }

    public void demarrer() {
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