package Obj;

import Sys.Map;

public class Character extends Object {
	int HP;
	private int HPMAX = 5;
	Item item[];
	int walkCount;
	int walkCountMax;	//1~6
	char team;

	public Character(String ID, String name, char team) {
		super(ID, name + "(" + team + ")");
		item = new Item[3];
	}

	//空欄のますにはこれを使用して埋める
	public Character() {
		super("c0", "");
	}

	public void setHP(int HP) {
		this.HP = HP;
	}

	public int getHP() {
		return this.HP;
	}

	public void setItem(Item i[]) {
		this.item = i;
	}

	public Item[] getItem() {
		return this.item;
	}

	public void setWalkCount(int walk) {
		this.walkCount = walk;
	}

	public int getWalkCount() {
		return this.walkCount;
	}

	public void setWalkCountMax(int walkmax) {
		this.walkCountMax = walkmax;
	}

	public int getWalkCountMax() {
		return this.walkCountMax;
	}

	public void setTeam(char team) {
		this.team = team;
	}

	public int getTeam() {
		return this.team;
	}
	
	public void move(Map m, int direction) {
		int x = m.getPosition(this.ID)[0];
		int y = m.getPosition(this.ID)[1];
		int X = x;
		int Y = y;
		switch(direction) {
		case 0:
			Y -= 1;
			break;
		case 2:
			X += 1;
			break;
		case 4:
			Y += 1;
			break;
		case 6:
			X -= 1;
			break;
		}
		m.moveCharacter(x, y, X, Y);
	}

}
