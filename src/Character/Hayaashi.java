package Character;

import Sys.Map;
import Sys.Util;

public class Hayaashi extends Character {

	public Hayaashi(Map m, char team) {
		super(m, Util.searchNewId(m, "ch"), "Hayaashi-kun", team);
	}
}
