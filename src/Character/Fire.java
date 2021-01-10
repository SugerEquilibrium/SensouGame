package Character;

import Sys.Map;
import Sys.Util;
import Trap.Flame;

public class Fire extends Character {
	Flame[] flame;

	public Fire(Map m, char team) {
		super(Util.searchNewId(m, "cf"), "God of Fire", team);
	}

	public void burn(Map m, int direction) {
		int x = m.getPosition(this.getID())[0] + Util.directionVector(direction)[0];
		int y = m.getPosition(this.getID())[1] + Util.directionVector(direction)[1];
		m.getCharacterLayer()[x][y].damage(2);
	}

	public void putFlameTrap() {

	}
}
