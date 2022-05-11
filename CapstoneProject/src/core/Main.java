package core;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;

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
public class Main {

	public static Mixer mixer;
	public static Clip clip;
	public static JFrame window;

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
		playMusic(true,"/Music/africa-toto.wav");
		window.setSize(400, 300);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
		
		
		
		
		
		
	}
	
	public static void playMusic(boolean shouldOpen, String fileData) {
Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();

		
		mixer = AudioSystem.getMixer(mixInfos[0]);
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try {
			clip = (Clip) mixer.getLine(dataInfo);
		}

		catch (LineUnavailableException lol) {
			lol.printStackTrace();
		}

		
		try {
			URL soundURL = Main.class.getResource(fileData);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
			clip.open(audioStream);
		}
		catch (LineUnavailableException lol) {
			lol.printStackTrace();
		}
		catch (UnsupportedAudioFileException lol1) {
			lol1.printStackTrace();
		}
		catch (IOException lol3) {
			lol3.printStackTrace();
		}
		clip.start();		
		
		do {
			try { Thread.sleep(50);
			}
			catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		} while (window.isActive());

	}

}
