package Service;

import java.awt.Desktop;
import java.awt.JobAttributes;
import java.io.File;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class MusicService {

	public static void startMusic(String location) {
		try {
			File file = new File(location);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			clip.start();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
	}

	public static void startMusic2(String location) {
		try {
			File file = new File(location);
			
			Desktop.getDesktop().open(file);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}
//	public static void startMusic3() {
//		Media media ;
//		MediaPlayer mediaPlayer;
//	}

}
