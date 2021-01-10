package Trap;

import Sys.Map;
import Sys.Util;

public class Flame extends Trap {
	
	public Flame(Map m) {
		super(Util.searchNewId(m, "tf"), "FlameTrap");
		this.setType("tf");
		this.setName("FlameTrap");
	}

}
