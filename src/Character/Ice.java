package Character;

import Sys.Map;
import Sys.Util;

public class Ice extends Character {
	
	public Ice(Map m, char team) {
		super(m, Util.searchNewId(m, "ci"), "God of Ice", team);
	}

}
