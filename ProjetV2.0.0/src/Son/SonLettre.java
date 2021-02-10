package Son;
import java.io.File;
import java.io.IOException;

import javax.sound.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;

public class SonLettre {
	private Clip clip = null;
    private AudioInputStream audioStream = null;
	
    
    
    public static void jouerSon(char lalettre) throws UnsupportedAudioFileException, IOException{
    	File file = null;
    	file = new File(lalettre + ".mp3");
    	
    	AudioInputStream in= AudioSystem.getAudioInputStream(file);
    	AudioInputStream din = null;
    	AudioFormat baseFormat = in.getFormat();
    	AudioFormat decodedFormat =
    	    new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
    	                    baseFormat.getSampleRate(),
    	                    16,
    	                    baseFormat.getChannels(),
    	                    baseFormat.getChannels() * 2,
    	                    baseFormat.getSampleRate(),
    	                    false);
    	din = AudioSystem.getAudioInputStream(decodedFormat, in);
    	// Play now.
    	try {
			rawplay(decodedFormat, din);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    
    
    //-----méthode
    private static void rawplay(AudioFormat targetFormat,  AudioInputStream din) 
    		throws IOException, LineUnavailableException {
    	
		byte[] data = new byte[4096];
		
		SourceDataLine line = getLine(targetFormat);
		
		if (line != null){
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			
			while (nBytesRead != -1){
				nBytesRead = din.read(data, 0, data.length);
				
				if (nBytesRead != -1)
				   nBytesWritten = line.write(data, 0, nBytesRead);
			}
			
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		}
	}

private static SourceDataLine getLine(AudioFormat audioFormat)
throws LineUnavailableException
{
SourceDataLine res = null;
DataLine.Info info =
new DataLine.Info(SourceDataLine.class, audioFormat);
res = (SourceDataLine) AudioSystem.getLine(info);
res.open(audioFormat);
return res;
}
}
