package Character;

import Sys.Map;
import Sys.Util;

public class Kinnniku extends Character {
	
	public Kinnniku(Map m, char team) {
		super(m, Util.searchNewId(m, "ck"), "Kinniku-man", team);
	}

}
