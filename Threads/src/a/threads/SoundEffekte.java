package a.threads;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundEffekte {

	public static File sound;

	public static float value;

	Clip clip;
	
	public SoundEffekte() {
		value = -20;
	}
	
	public void load() {
		sound = new File("src/a/image/laser.wav");
	}

	public void play(File sound) {
		
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			
			FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			fc.setValue(value);
			
			clip.start();
		}catch (Exception e) {
			
		}
	}

	


}
