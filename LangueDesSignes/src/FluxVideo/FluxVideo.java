package FluxVideo;

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

		this.fluxVideo = FrameGrabber.createDefault(1);

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

	
}