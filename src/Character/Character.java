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

	public Character(Map m, String ID, String name, char team) {
		super(m, ID, name);
		this.HP = this.HPMAX;
		this.isDead = false;
		this.item = new Item[3];
		this.team = team;
		for(int i = 0; i < this.item.length; i++) {
			this.item[i] = new Item();
		}
	}

	//空欄のますにはこれを使用して埋める
	public Character() {
		super("c0", "");
		this.HP = 0;
		this.team = 0;
		this.isDead = false;
		this.item = new Item[3];
		for(int i = 0; i < this.item.length; i++) {
			this.item[i] = new Item();
		}
	}

	public void setHP(int HP) {
		this.HP = HP;
	}

	public int getHP() {
		return this.HP;
	}

	//所有アイテム配列の最後に引数のアイテムを配置
	public void setItem(Item i) {
		this.item[Util.countObjArr(this.item)] = i;
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

	public char getTeam() {
		return this.team;
	}

	public boolean isDead() {
		return isDead;
	}

	public void move(int direction) {
		int x = this.getM().getPosition(this.getID())[0];
		int y = this.getM().getPosition(this.getID())[1];
		int X = x + Util.directionVector(direction)[0];
		int Y = y + Util.directionVector(direction)[1];
		this.getM().moveCharacter(x, y, X, Y);
	}
	
	//引数のキャラクターをwalk回ユーザの方向を読み取り移動させます
	public void walk(int walk) {
		int x;
		int y;
		int X;
		int Y;
		int direction;
		for(int i = 0; i < walk; i++) {
			do {
				System.out.println("歩く方向を指定してください");
				x = this.getM().getPosition(this.getID())[0];
				y = this.getM().getPosition(this.getID())[1];
				//方向番号をキーボードから読み取ります
				direction = new java.util.Scanner(System.in).nextInt();
				X = x + Util.directionVector(direction)[0];
				Y = y + Util.directionVector(direction)[1];
				if(this.getM().collision(X, Y, 'c')) {
					System.out.println("その方向には進めません");
				}
			}while(this.getM().collision(X, Y, 'c'));
			this.move(direction);
			this.getM().updateMapWindow();
			this.setWalkCount(this.getWalkCount() - 1);
		}
	}
	
	//同じ座標のアイテム取得
	//取得するアイテム数を制限したい時は、引数のIDをi0とし、無を取得させる
	//指定したIDがキャラクターと同じ座標にない場合は無を取得
	public void takeItem(String ID1, String ID2, String ID3) {
		int x = this.getM().getPosition(this.getID())[0];
		int y = this.getM().getPosition(this.getID())[1];
		//所有アイテム配列の最後に指定したIDのアイテムを配置
		//所有アイテムがいっぱいの時、又は、指定したIDが存在しない時は無視
		if(Util.countObjArr(this.item) < 3) {
			this.setItem(Util.findItemById(this.getM().getItemLayer()[x][y], ID1));
			Util.removeItemById(this.getM().getItemLayer()[x][y], ID1);		//なぜかキャラクターがアイテムをとってくれない問題
		}
		if(Util.countObjArr(this.item) < 3) {
			this.setItem(Util.findItemById(this.getM().getItemLayer()[x][y], ID2));
			Util.removeItemById(this.getM().getItemLayer()[x][y], ID2);
		}
		if(Util.countObjArr(this.item) < 3) {
			this.setItem(Util.findItemById(this.getM().getItemLayer()[x][y], ID3));//ここで所持アイテム配列が全て初期化されてしまう持アイテム配列を4に増やすと解決するが、、
			Util.removeItemById(this.getM().getItemLayer()[x][y], ID3);
		}
	}

	//8方向に隣接するキャラクターを長さ8のキャラクター型配列に格納します
	//配列外に隣接している場合は、からキャラクターを格納します
	//未使用
	public Character[] getNextCharcter() {
		Character[] nextCharacter = new Character[8];
		int x = this.getM().getPosition(this.getID())[0];
		int y = this.getM().getPosition(this.getID())[1];
		int X;
		int Y;
		for(int i = 0; i < nextCharacter.length; i++) {
			X = x + Util.directionVector(i)[0];
			Y = y + Util.directionVector(i)[1];
			if(this.getM().outSideError(X, Y)) {
				nextCharacter[i] = new Character();
			}else {
				nextCharacter[i] = this.getM().getCharacterLayer()[X][Y];
			}
		}
		return nextCharacter;
	}

	//引数の値だけHPを減らす
	//HPが0以下になった時死亡処理が走る
	public void damage(int damage) {
		this.HP -= damage;
		if(this.HP < 0) {
			die();
		}
	}

	//引数の方向に隣接するキャラクターに1ダメージ
	public void attack(int direction) {
		int x = this.getM().getPosition(this.getID())[0] + Util.directionVector(direction)[0];
		int y = this.getM().getPosition(this.getID())[1] + Util.directionVector(direction)[1];
		this.getM().getCharacterLayer()[x][y].damage(1);
	}

	//死亡処理
	public void die() {
		int x = this.getM().getPosition(this.getID())[0];
		int y = this.getM().getPosition(this.getID())[1];
		this.isDead = true;
		for(int i = 0; i < this.item.length; i++) {
			this.getM().setItem(x, y, this.item[i]);
			this.item[i] = new Item();
		}
		this.getM().getCharacterLayer()[x][y] = new Character();
	}
	
	//ユーザの選択を引数で受け取り、それに対応した行動を実行する
	//キャラクターごとにオーバーライド
	public void action(int userSelect, int direction) {
		switch(userSelect) {
		case 0:
			break;
		case 1:
			attack(direction);
			break;
		}
	}
	
	//自分の全てのアクションをString型配列で返します
	//キャラクターごとにオーバーライド
	//String型配列の順番はactionメソッドと同じにするように
	public String[] listAction() {
		String[] action = new String[2];
		action[0] = "なにもしない";
		action[1] = "attack";
		return action;
	}
}
