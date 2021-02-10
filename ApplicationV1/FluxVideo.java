package ApplicationV1;

import java.awt.Image;
import java.awt.image.BufferedImage;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.*;

public class FluxVideo {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;

	private FrameGrabber FluxVideo;
	private IplImage ImageFlux = new IplImage();
	private Image Image;

	public void initFluxVideo() throws Exception {

		this.FluxVideo = FrameGrabber.createDefault(0);

		if (this.FluxVideo == null)
			throw new Exception("Probleme d'initialisation de la webcam... ");
		this.FluxVideo.start();
	}

	public IplImage getImageFlux() {
		return this.ImageFlux;
	}

	public Image getImage() {
		return this.Image;
	}

	public FrameGrabber getFluxVideo() {
		return this.FluxVideo;
	}

	public void setImageFlux(IplImage image) {

		this.ImageFlux = image;
	}

	// public void

	public void setImage(Image image) {
		this.Image = image;

	}

	public void setImage(BufferedImage buffer) {
		this.Image = buffer;
	}

	public void free() throws Exception {

		this.Image.flush();
	}

}
