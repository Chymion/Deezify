package adaptateur;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import model.AudioMaster;

public class Mp3 implements Mp3Interface{

	public Player player;
	FileInputStream FIS;
	BufferedInputStream BIS;
	public long pauseLocation;
	public long songTotalLength;
	public String fileLocation;
	public Thread thread;
	
	@Override
	public int launchMusic(String file) {
		// TODO Auto-generated method stub
		try {
            FIS = new FileInputStream(file);
            BIS = new BufferedInputStream(FIS);
            
            try {
				player = new Player(BIS);
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            songTotalLength = FIS.available();
            fileLocation = file + "";
            
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
           
        } 
		
		thread = new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                    
                    if(player.isComplete() ){
                    	launchMusic(fileLocation);
                    }
                    if(player.isComplete()){
                        
                    }
                } catch (JavaLayerException ex) {
                    
                }
            }
        };
		
		thread.start();
        
        
		return 0;
	}
	
	@Override
	public void pause()
	{
		synchronized (thread) {
			thread.suspend();
		}
	}
	
	@Override
	public void resume()
	{
		thread.resume();
	}
	
	@Override
	public void Destruction() {
		// TODO Auto-generated method stub
		if(player != null) {
			player.close();
			thread.stop();
		}
	}

}
