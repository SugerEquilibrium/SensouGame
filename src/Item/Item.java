package Item;

import Sys.Map;
import Sys.Obj;

public class Item extends Obj{

	public Item(Map m) {
		super(m, "", "");
	}

	public Item(Map m, String type, String name){
		super(m, type, name);
	}

	public void getPosition(int x, int y) {

	}

	public void setPosition(int x, int y, int stack) {
		this.getPosition()[0] = x;
		this.getPosition()[1] = y;
		this.getPosition()[2] = stack;
	}

	public void move(int x, int y, String type) {
		this.getM().removeItem(this.getPosition()[0], this.getPosition()[1], type);
		this.getM().setItem(x, y, this);
	}
}
