package Character;

import Sys.Map;
import Sys.Util;

public class Mud extends Character {
	
	public Mud(Map m, char team) {
		super(m, Util.searchNewId(m, "cm"), "Mud-man", team);
	}

}
