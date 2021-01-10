package Character;

import Item.Item;
import Sys.Map;
import Sys.Obj;
import Sys.Util;

public class Character extends Obj {
	private int HP;
	private int HPMAX = 5;
	private Item item[];
	private int walkCount;
	private int walkCountMax;	//1~6
	private char team;
	private boolean isDead;

	public Character(String ID, String name, char team) {
		super(ID, name + "(" + team + ")");
		this.HP = this.HPMAX;
		this.isDead = false;
		this.item = new Item[3];
		for(int i = 0; i < this.item.length; i++) {
			this.item[i] = new Item();
		}
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
		int x = m.getPosition(this.getID())[0];
		int y = m.getPosition(this.getID())[1];
		int X = x + Util.directionVector(direction)[0];
		int Y = y + Util.directionVector(direction)[1];
		m.moveCharacter(x, y, X, Y);
	}

	//同じ座標のアイテム取得
	//取得するアイテム数を制限したい時は、引数のIDをi0とし、無を取得させる
	//指定したIDがキャラクターと同じ座標にない場合は無を取得
	public void takeItem(Map m, String ID1, String ID2, String ID3) {
		int x = m.getPosition(this.getID())[0];
		int y = m.getPosition(this.getID())[1];
		//所有アイテム配列の最後に指定したIDのアイテムを配置
		//所有アイテムがいっぱいの時、又は、指定したIDが存在しない時は無視
		if(Util.countItemArr(this.item) < 3) {
			this.item[Util.countItemArr(this.item)] = Util.findItemById(m.getItemLayer()[x][y], ID1);
			Util.removeItemById(m.getItemLayer()[x][y], ID1);		//なぜかキャラクターがアイテムをとってくれない問題
		}
		if(Util.countItemArr(this.item) < 3) {
			this.item[Util.countItemArr(this.item)] = Util.findItemById(m.getItemLayer()[x][y], ID2);
			Util.removeItemById(m.getItemLayer()[x][y], ID2);
		}
		if(Util.countItemArr(this.item) < 3) {
			this.item[Util.countItemArr(this.item)] = Util.findItemById(m.getItemLayer()[x][y], ID3);//ここで所持アイテム配列が全て初期化されてしまう持アイテム配列を4に増やすと解決するが、、
			Util.removeItemById(m.getItemLayer()[x][y], ID3);
		}
	}

	//8方向に隣接するキャラクターを長さ8のキャラクター型配列に格納します
	//配列外に隣接している場合は、からキャラクターを格納します
	public Character[] getNextCharcter(Map m) {
		Character[] nextCharacter = new Character[8];
		int x = m.getPosition(this.getID())[0];
		int y = m.getPosition(this.getID())[1];
		int X;
		int Y;
		for(int i = 0; i < nextCharacter.length; i++) {
			X = x + Util.directionVector(i)[0];
			Y = y + Util.directionVector(i)[1];
			if(m.outSideError(X, Y)) {
				nextCharacter[i] = new Character();
			}else {				
				nextCharacter[i] = m.getCharacterLayer()[X][Y];
			}
		}
		return nextCharacter;
	}

	public void damage() {
		this.HP -= 1;
		if(this.HP < 0) {
			this.isDead = true;
		}
	}

	public void attack(Map m, int direction) {
		getNextCharcter(m)[direction].damage();
	}
}
