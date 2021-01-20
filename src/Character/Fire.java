package Character;

import Sys.Map;
import Sys.Util;
import Trap.Flame;

public class Fire extends Character {
	Flame[] flame;

	public Fire(Map m, char team) {
		super(m, "cf", "God of Fire", team);
		flame = new Flame[3];
		for(int i = 0; i < flame.length; i++) {
			flame[i] = new Flame(m);
		}
	}

	//特殊攻撃 : 火炎放射
	//隣接するキャラクターに2ダメージ
	public void burn(Map m, int direction) {
		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		int X = y + Util.directionVector(direction)[0];
		int Y = y + Util.directionVector(direction)[1];
		m.getCharacterLayer()[x][y].damage(2);
	}

	public void putFlameTrap() {

	}
}
