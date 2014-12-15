import java.awt.Graphics;


public class CollisionDetector {

	GameGUI game;
	
	public CollisionDetector(GameGUI game){
		this.game = game;
	}

	public boolean checkBonusDiamondCollision(Miner m, GameObject go) {
		
		if(go.getGameObjectState() != GameObject.State.eaten){
			if(go.getRectangle().intersects(m.rectangle)){
				return true;
			}
			else{
				return false;
			}
		}else{

			return false;
		}
	}
	
	public boolean checkMonsterCollision (Miner m, Monster monster){
		
		return false;
	}
}
