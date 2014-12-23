import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public enum SoundEffect{bonus("bonus.wav"),coin("coin.wav"),monsterWalk("monster.wav"),minerWalk("dig.wav"),eatMiner("eatminer.wav"),gameplay("monster.wav");

private Clip clip;

public boolean isClicked;


SoundEffect(String soundFile){

	isClicked = true;

	try{
		URL url = this.getClass().getClassLoader().getResource(soundFile);
		AudioInputStream audio = AudioSystem.getAudioInputStream(url);
		clip = AudioSystem.getClip();
		clip.open(audio);
	}catch(Exception e){
		System.out.println("Exception in SoundManager: " + e);
	}
}

public void play(){

	if ( isClicked) {
		if (clip.isRunning())
			clip.stop();   // Stop the player if it is still running
		clip.setFramePosition(0); // rewind to the beginning
		clip.start();     // Start playing
	}
	else
		stop();
}

public void stop(){

	clip.stop();
}

static void init(){
	values();
}




}




