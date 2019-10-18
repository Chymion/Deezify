package view;


import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class AudioMaster implements Runnable {

	//Buffer contenant le flux de donnés de la musique. Et un entier contenant la source
	private static ArrayList<Integer> buffers = new ArrayList<Integer>();
	private static int sourceID;
	
	//Initialisation de OpenAL	
	public static void init() {
		try {
			AL.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void playSong(int buf) throws IOException
	{
		//Si on entre la lettre 'p', la musique se lance. 
		//Si on entre la lettre 'q', la musique se quitte
		char c = ' ';
		while (c != 'q')
		{
			c = (char)System.in.read();
			
			if (c == 'p')
			{
				play(buf);
			}
		}
	}

	
	// Jouer la source
	public static void play(int buffer)
	{
		AL10.alSourcei(sourceID, AL10.AL_BUFFER, buffer);
		AL10.alSourcePlay(sourceID);
	}
	
	public static void pause()
	{
		AL10.alSourcePause(sourceID);
	}
	public static void continuer()
	{
		AL10.alSourcePlay(sourceID);
	}
	
	
	public static int chargerMusique(String file)
	{
		// Chargement des bits composant le fichier audio dans le buffer.
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveData waveFile = WaveData.create(file);
		AL10.alBufferData(buffer, waveFile.format,
				waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		sourceID = AL10.alGenSources();
		AL10.alSourcef(sourceID, AL10.AL_GAIN, 1);
		AL10.alSourcef(sourceID, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceID, AL10.AL_POSITION, 0, 0,0);
		return buffer;
	}
	
	// Libération des ressources
	public static void Destruction()
	{
		for (int buffer : buffers)
		{
			AL10.alDeleteBuffers(buffer);
		}
		
		AL.destroy();
		AL10.alDeleteSources(sourceID);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread started");
		int buffer = chargerMusique("view/Aliez.wav");	
		char c = ' ';
		
		play(buffer);
		
	}
}
