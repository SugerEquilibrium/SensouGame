package Sys;

public abstract class Obj {
	private String name;
	private String type;
	private Map m;
	private int position[];	//座標をここに格納

	public Obj(Map m, String type, String name){
		this.name = name;
		this.m = m;
		this.type = type;
		position = new int[3];
		position[0] = -1;	//オブジェクトのx座標	マップ上に存在しない場合は-1を代入
		position[1] = -1;	//オブジェクトのy座標
		position[2] = -1;	//アイテムスタック番号
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setPosition(int x, int y) {
		this.position[0] = x;
		this.position[1] = y;
	}

	public int[] getPosition() {
		return this.position;
	}

	public Map getM() {
		return m;
	}


//	public abstract boolean isNext(Map m, Object o);
}
