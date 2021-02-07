package Character;

import Sys.Map;
import Sys.Util;

public class Ice extends Character {

	public Ice(Map m, char team) {
		super(m, "ci", "God of Ice", team);
	}

	public void freeze(Map m, int direction) {
		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		int X = y + Util.directionVector(direction)[0];
		int Y = y + Util.directionVector(direction)[1];
		m.getCharacterLayer()[x][y].damage(2);
	}

}
