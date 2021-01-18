package Land;

import Sys.Map;
import Sys.Obj;

public class Land extends Obj{

	public Land(Map m) {
		super(m, "l0", "");
	}

	public Land(Map m, String ID, String name){
		super(m, ID, name);
	}
}
