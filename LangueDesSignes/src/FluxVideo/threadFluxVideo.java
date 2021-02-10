package FluxVideo;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import Observer.*;

//le flux est observer par le panneau video

public class threadFluxVideo implements Runnable, Observable {
	
	
	FluxVideo flux = null;
	BufferedImage imageFlux = null;
	IplImage imageIpl = null;
	
	
	boolean continuer = true;
	boolean pause  = false;
	
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	
	public threadFluxVideo()
	{
		
		flux = new FluxVideo();
		
	}
	
	public void run (){
		
		while(continuer)
		{
			
			if(!pause)
			{
				try{
						 
						 this.imageIpl = flux.getImageFlux();
						 this.imageFlux = this.imageIpl.getBufferedImage();
						 
						 this.updateObservateur();//on previent au panneau video qu on a actualise le flux
						 
						 Thread.sleep(30);
	
				} catch ( InterruptedException | Exception e) {
				e.printStackTrace();
				}
			}
			else
			{
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		}	
	}
	
	public void setContinuer(boolean bool){ this.continuer = bool;}
	public void setPause(boolean bool){ this.pause = bool;}
	
	public BufferedImage getCapture (){
		
		if(this.imageIpl != null)
			return imageFlux;
		else
			return null;
		
		
	}
	
	public IplImage getCaptureIpl (){ 
		if(this.imageIpl != null)
			return this.imageIpl;
		else
			return null;
		}
	
	//methode Observable
	
	@Override
	public void addObservateur(Observateur obs) {
		this.listObservateur.add(obs);	
	}

	@Override
	 public void updateObservateur() {
	    for(Observateur obs : this.listObservateur )
	      obs.update();
	  }

	@Override
	public void delObservateur() {
		this.listObservateur = new ArrayList<Observateur>();
	}

}
