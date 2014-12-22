
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

		if(m.getCurrentTile().x == monster.currentTile.x  && m.getCurrentTile().y == monster.currentTile.y ){
			return true;
		}else if (m.getCurrentTile().x == monster.currentTile.x  && m.getCurrentTile().y == monster.currentTile.y+1){
			return true;
		}else if (m.getCurrentTile().x == monster.currentTile.x-1  && m.getCurrentTile().y == monster.currentTile.y){
			return true;
		}else if (m.getCurrentTile().x == monster.currentTile.x+1  && m.getCurrentTile().y == monster.currentTile.y){
			return true;
		}else if (m.getCurrentTile().x == monster.currentTile.x  && m.getCurrentTile().y == monster.currentTile.y-1){
			return true;
		}
		return false;
	}
}
