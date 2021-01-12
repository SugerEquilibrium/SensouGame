package Character;

import Sys.Map;
import Sys.Util;

public class Alcohol extends Character {
	
	public Alcohol(Map m, char team) {
		super(m, Util.searchNewId(m, "ca"), "Alcoholic", team);
	}

}
