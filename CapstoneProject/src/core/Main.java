package core;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;

import jay.jaysound.JayLayer;
import jay.jaysound.JayLayerListener;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author kevinvalencia and vihaanchinthakindi Runs the program
 */
public class Main implements JayLayerListener {

	public static Mixer mixer;
	public static Clip clip;
	public static JFrame window;
	private JayLayer sound;

	
	public Main() {
		sound = new JayLayer("audio/","audio/", false);
		sound.addPlayList();
		sound.addSong(0, "game1.mp3");
		sound.changePlayList(0);
		sound.addJayLayerListener(this);
		System.out.println(sound.isPlaying());
		
		
		
		
	}
	/**
	 * Creates a window for the program to run in
	 * 
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {

		DrawingSurface drawing = new DrawingSurface();
		
		PApplet.runSketch(new String[] { "" }, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();

		window.setSize(400, 300);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
		Main player = new Main();
		

	}
/**
 * Method plays music
 * @param shouldOpen variable to determine whether music plays or doesnt
 * @param fileData String representing location of the music file
 */
	public static void playMusic(boolean shouldOpen, String fileData) {
		if (shouldOpen) {
			Mixer.Info[] audioTypes = AudioSystem.getMixerInfo();

			mixer = AudioSystem.getMixer(audioTypes[0]);
			DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
			try {
				clip = (Clip) mixer.getLine(dataInfo);
			}

			catch (LineUnavailableException lol) {
				lol.printStackTrace();
			}

			try {
				URL info = Main.class.getResource(fileData);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(info);
				clip.open(audioStream);
			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException lol) {
				lol.printStackTrace();
			}
			clip.start();

			do {
				try {
					Thread.sleep(50);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			} while (window.isActive());

		} else {
			clip.close();
		}
	}
@Override
public void musicStarted() {
	// TODO Auto-generated method stub
	
}
@Override
public void musicStopped() {
	// TODO Auto-generated method stub
	
}
@Override
public void playlistEnded() {
	// TODO Auto-generated method stub
	
}
@Override
public void songEnded() {
	// TODO Auto-generated method stub
	
}

}
