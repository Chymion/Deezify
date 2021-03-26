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
	public int lancerMusique(String file) {
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
                    	lancerMusique(fileLocation);
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
	public void resume()
	{
		try {
			player.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public void manageEffects(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		
		if ( request.getParameter( "boutonPlay" ) != null ) {
			if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
                	System.out.println("synchonised");
                	synchronized (thread) {
						thread.suspend();
					}
                session.setAttribute( "count", true );
            } else {
                thread.resume();
                session.setAttribute( "count", false );
            }
		}
		
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
