package Sys;

public abstract class Obj {
	private String ID;	//IDは変数名と同じにすること
	private String name;
	private String type;
	private Map m;

	public Obj(Map m, String ID, String name){
		this.ID = ID;
		this.name = name;
		this.m = m;
		this.type = ID.replaceAll("[0-9]", "");	//IDの番号部分だけ削除しオブジェクトタイプを代入する
	}
	public Obj(String ID, String name) {
		this.ID = ID;
		this.name = name;
		this.type = ID.replaceAll("[0-9]", "");
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return this.ID;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map getM() {
		return m;
	}

//	public abstract boolean isNext(Map m, Object o);
}
