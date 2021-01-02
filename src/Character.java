
public class Character extends Object {
	int HP;
	private int HPMAX = 5;
	Item item[];
	int walkCount;
	int walkCountMax;	//1~6
	char team;

	Character(String ID, String name, char team) {
		super(ID, name + "(" + team + ")");
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


}
