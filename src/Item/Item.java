package Item;

import Sys.Map;
import Sys.Obj;

public class Item extends Obj{

	public Item(Map m) {
		super(m, "i0", "");
	}

	public Item(Map m, String ID, String name){
		super(m, ID, name);
	}
}
