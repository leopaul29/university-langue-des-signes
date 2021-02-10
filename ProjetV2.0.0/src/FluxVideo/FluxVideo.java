package FluxVideo;

import static com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.*;

public class FluxVideo {


	private FrameGrabber fluxVideo = null;
	
	public FluxVideo() {
		
		try {
			initFluxVideo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initFluxVideo() throws Exception {

		this.fluxVideo = FrameGrabber.createDefault(0);

		if (this.fluxVideo == null)
			throw new Exception("Probleme d'initialisation de la webcam... ");
		this.fluxVideo.start();
	}

	public IplImage getImageFlux() throws Exception {
		return fluxVideo.grab();
	}


	public FrameGrabber getFluxVideo() {
		return this.fluxVideo;
	}

	
	
	public static void main(String[] args) 
	{
	    FrameGrabber grabber = null;
		try {
			grabber = FrameGrabber.createDefault(0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    if (grabber == null)
	    {
	        System.out.println("!!! Failed OpenCVFrameGrabber");
	        return;
	    }
	    

	    cvNamedWindow("video_demo");

	    try            
	    {
	        grabber.start();
	        IplImage frame = null;  

	        while (true)
	        {
	            frame = grabber.grab();               
	           
	            if (frame == null)
	            {
	                System.out.println("!!! Failed grab");
	                break;
	            }


	            cvShowImage("video_demo", frame);
	            cvWaitKey(1);
	        }

	    }
	    catch (Exception e) 
	    {    
	        System.out.println("!!! Exception");
	    }
	}

}
