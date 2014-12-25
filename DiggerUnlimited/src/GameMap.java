import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class GameMap {

	//FileManager mapFileManager;
	//File mapFile
	private static GameMap gm = null;
	private GameMap(){

		//mapFileManager = new FileManager();
		//mapFile = new File("map.txt"); // map text file. 
		
	}
	
	public static GameMap getInstance(){
		if(gm == null)
			gm = new GameMap();
		return gm;
	}

	public char[][] generateMapFromFile(File mapFile){
		char[][] map;
		String[] mapStr;
		StringBuffer stringBuffer =null;
		try {
			FileReader fileReader = new FileReader(mapFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		mapStr = stringBuffer.toString().split("\\r?\\n");
		map = new char[18][30];
		
		for(int i=0; i < 18; i++){
			for(int j = 0; j < 30; j++){
				map[i][j] = mapStr[i].charAt(j);
			}
		}
		
		// Prints out the map. For debug purposes. 
		/*for (int i = 0; i <map.length; i++){
			for (int j = 0; j < map[i].length;j++){
				System.out.print(map[i][j]);
			}
			System.out.println();
		}*/
		return map;
	}
}
