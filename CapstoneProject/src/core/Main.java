package core;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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


	public static JFrame window;
	private static JayLayer sound;
	public static int currentPlaylist;
	ArrayList<String> soundEffects = new ArrayList<String>();
	//ArrayList<String> songs = new ArrayList<String>();

	
	public Main() {
		
		//songs.add("game1.mp3");
		soundEffects.add("door.mp3");
		soundEffects.add("death.mp3");
		
		
		sound = new JayLayer("audio/","audio/", false);
		sound.addPlayList();
		sound.addSong(0, "Vice.mp3");
		sound.addSoundEffects(soundEffects);
		//playlist 0
		
		sound.addPlayList();
		sound.addSong(1, "synthwave1.mp3");
		
		
		sound.addPlayList();
		sound.addSong(2, "synthwave3.mp3");
		
		
		
		sound.changePlayList(0);
		sound.addJayLayerListener(this);
		currentPlaylist = 0;
		sound.nextSong();
	
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

	
public static void changeSong( int i) {
	sound.stopSong();
	sound.changePlayList(i);
	currentPlaylist = i;
	
	
	sound.nextSong();
	
}
public static void playSoundEffect(int i) {
	sound.playSoundEffect(i);
	//sound.
	
}

public static void stopSong() {
	sound.stopSong();
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
@Override
public void musicStarted() {
	// TODO Auto-generated method stub
	
}

}
