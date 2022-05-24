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
	public static int effectNumber;
	public static int jumpNumber;
	public static int doorNumber;
	
	ArrayList<String> soundEffects = new ArrayList<String>();
	//ArrayList<String> songs = new ArrayList<String>();

	/**
	 * constructor to initialize all Music elements, and start playing MainMenu song.
	 */
	public Main() {
		effectNumber = 0;
		jumpNumber = 500;
		doorNumber = 1000;
		
		//songs.add("game1.mp3");
		//soundEffects.add("door.mp3");
		//soundEffects.add("death.mp3");
		for (int i = 0; i <500; i++) {
			soundEffects.add("death.mp3");
		}
		for (int i = 0; i < 500;i++ ) {
			soundEffects.add("jumping.mp3");
		}
		
		for (int i = 0; i < 500;i++ ) {
			soundEffects.add("realDoor.mp3");
		}
		
		sound = new JayLayer("audio/","audio/", false);
		sound.addPlayList();
		sound.addSoundEffects(soundEffects);
		sound.addSong(0, "Vice.mp3");
		
		//playlist 0
		
		sound.addPlayList();
		sound.addSong(1, "synthwave1.mp3");
		
		
		sound.addPlayList();
		sound.addSong(2, "synthwave3.mp3");
		
		sound.addPlayList();
		sound.addSong(3, "realDoor.mp3");
		
		sound.addPlayList();
		sound.addSong(4, "death.mp3");
		
		
		
		
		sound.changePlayList(0);
		sound.addJayLayerListener(this);
	
		sound.nextSong();
	
		//System.out.println(sound.isPlaying());
		
		
		
		
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

		window.setSize(400,400);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
		Main player = new Main();
		

	}
/**
 * Stops current song, and changes the Song to the one in the list corresponding to parameter
 * @param i index of the song to be played
 */
public static void changeSong( int i) {
	if (i ==4) {
		
	sound.stopSong();
	sound.changePlayList(i);
	
	
	
	
	sound.nextSong();
	
	try {
		
		Thread.sleep(840);
		
		sound.stopSong();
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	
	
	
	else {
	sound.stopSong();
	sound.changePlayList(i);
	
	
	
	sound.nextSong();
	}
}
/**
 * plays specified soundeffect
 * @param i index of soundEffect to be played
 */
public static void playSoundEffect(int i) {
	sound.playSoundEffect(i);
	if (i < 500) {
	effectNumber++;
	}
	else if (i < 1000 && i >=500){
		jumpNumber++;
	}
	else {
		doorNumber++;
	}
	//sound.
	
}
/**
 * stops the current song
 */
public static void stopSong() {
	sound.stopSong();
}
@Override
/**
 * handles what to do when music stopped, nothing happens in this program
 */
public void musicStopped() {
	
	
}
@Override
/**
 * handles what to do when playlist ends, nothing happens in this program
 */
public void playlistEnded() {
	// TODO Auto-generated method stub
	
}
@Override
/**
 * handles what to do when Song ends, nothing happens in this program
 */
public void songEnded() {
	// TODO Auto-generated method stub
	
}
/**
 * handles what to do when music starts, nothing happens in this program
 */
public void musicStarted() {
	// TODO Auto-generated method stub
	
}

}
