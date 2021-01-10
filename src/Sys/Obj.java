package Sys;

public abstract class Obj {
	String ID;	//IDは変数名と同じにすること
	String name;

	public Obj(String ID, String name){
		this.ID = ID;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getID() {
		return this.ID;
	}

//	public abstract boolean isNext(Map m, Object o);
}
