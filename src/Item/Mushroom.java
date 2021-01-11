package Item;

import Sys.Map;
import Sys.Util;

public class Mushroom extends Item {
	
	public Mushroom(Map m) {
		super(m, Util.searchNewId(m, "im"), "PowerMushroom");
	}

}
