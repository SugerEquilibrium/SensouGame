package Obj;

import Sys.ItemStackCtrl;
import Sys.Map;

public class Character extends Object {
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
		int x = m.getPosition(this.ID)[0];
		int y = m.getPosition(this.ID)[1];
		int X = x;
		int Y = y;
		switch(direction) {
		case 1:
			Y -= 1;
			break;
		case 4:
			X += 1;
			break;
		case 6:
			Y += 1;
			break;
		case 3:
			X -= 1;
			break;
		}
		m.moveCharacter(x, y, X, Y);
	}

	//同じ座標のアイテム取得
	//取得するアイテム数を制限したい時は、引数のIDをi0とし、無を取得させる
	//指定したIDがキャラクターと同じ座標にない場合は無を取得
	public void takeItem(Map m, String ID1, String ID2, String ID3) {
		int x = m.getPosition(this.ID)[0];
		int y = m.getPosition(this.ID)[1];
		//所有アイテム配列の最後に指定したIDのアイテムを配置
		//所有アイテムがいっぱいの時、又は、指定したIDが存在しない時は無視
		if(ItemStackCtrl.countItem(this.item) < 3) {
			this.item[ItemStackCtrl.countItem(this.item)] = ItemStackCtrl.findItemById(m.getItemLayer()[x][y], ID1);
			ItemStackCtrl.removeItemById(m.getItemLayer()[x][y], ID1);		//なぜかキャラクターがアイテムをとってくれない問題
		}
		if(ItemStackCtrl.countItem(this.item) < 3) {
			this.item[ItemStackCtrl.countItem(this.item)] = ItemStackCtrl.findItemById(m.getItemLayer()[x][y], ID2);
			ItemStackCtrl.removeItemById(m.getItemLayer()[x][y], ID2);
		}
		if(ItemStackCtrl.countItem(this.item) < 3) {
			this.item[ItemStackCtrl.countItem(this.item)] = ItemStackCtrl.findItemById(m.getItemLayer()[x][y], ID3);//ここで所持アイテム配列が全て初期化されてしまう持アイテム配列を4に増やすと解決するが、、
			ItemStackCtrl.removeItemById(m.getItemLayer()[x][y], ID3);
		}
	}

	//8方向に隣接するキャラクターを長さ8のキャラクター型配列に格納します
	public Character[] getNextCharcter(Map m) {
		Character[] nextCharacter = new Character[8];
		int counter = 0;
		int x = m.getPosition(this.ID)[0];
		int y = m.getPosition(this.ID)[1];
		for(int Y = -1; Y < 2; Y++) {
			for(int X = -1; X < 2; X++) {
				if(X != 0 || Y != 0) {
					if((x + X) < m.getXsize() && (y + Y)< m.getYsize()) {
						nextCharacter[counter] = m.getCharacterLayer()[x + X][y + Y];
					}else {
						nextCharacter[counter] = new Character();
					}
					counter++;
				}
			}
		}
		return nextCharacter;
	}

	public void damage() {
		this.HP -= 1;
		if(this.HP == 0) {
			this.isDead = true;
		}
	}

	public void attack(Map m, int direction) {
		getNextCharcter(m)[direction].damage();
	}
}
