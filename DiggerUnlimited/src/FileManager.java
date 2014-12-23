import java.io.File;


public class FileManager {

	public File[] files;
	File mapFile, sound;
	
	public FileManager(){
		mapFile = new File("map.txt");
		sound = new File("walkMiner.wav");
		files[0] = mapFile;
		files[1] = sound;
	}
	
	public File getFile (String fileName){
		if(fileName.equals("map.txt"))
			return mapFile;
		else if(fileName.equals("walkMiner.wav"))
			return sound;
		return null;
	}
}
