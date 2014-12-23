import java.io.File;


public class FileManager {

	File mapFile, sound;
	
	public FileManager(){
		mapFile = new File("map.txt");
		sound = new File("walkMiner.wav");

	}
	
	public File getFile (String fileName){
		if(fileName.equals("map.txt"))
			return mapFile;
		else if(fileName.equals("walkMiner.wav"))
			return sound;
		return null;
	}
}
