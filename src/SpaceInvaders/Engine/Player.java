package SpaceInvaders.Engine;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player extends Thread{

	private AudioInputStream audioStream;
	private String location;

	public Player(String location){
		this.location = location;
		
	}
	public void run(){
		InputStream in = getClass().getResourceAsStream(location);
		try {
			this.audioStream = new AudioInputStream(in, AudioSystem.getAudioFileFormat(in).getFormat(), 100000);
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
