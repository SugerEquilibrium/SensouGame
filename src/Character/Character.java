package Character;

import Exc.OutsideExc;
import Item.Item;
import Sys.Map;
import Sys.Obj;
import Sys.Util;

public class Character extends Obj {
	private int HP;
	private int HPMAX = 5;
	private Item item[];
	private char team;
	private boolean isDead;
	private int deathCount;

	public Character(Map m, String type, String name, char team) {
		super(m, type, name);
		this.HP = this.HPMAX;
		this.isDead = false;
		this.item = new Item[3];
		this.team = team;
		for(int i = 0; i < this.item.length; i++) {
			this.item[i] = new Item(m);
		}
	}
	//空欄のますにはこれを使用して埋める
	public Character(Map m) {
		super(m, "", "");
		this.HP = 0;
		this.team = 0;
		this.isDead = true;
		this.item = new Item[3];
		for(int i = 0; i < this.item.length; i++) {
			this.item[i] = new Item(m);
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

	public void setTeam(char team) {
		this.team = team;
	}

	public char getTeam() {
		return this.team;
	}

	public boolean isDead() {
		return isDead;
	}

	//座標(x -> X, y -> Y)にキャラクターを移動
	public void move(int x, int y) {
		this.getM().removeCharacter(this.getPosition()[0], this.getPosition()[1]);
		this.getM().setCharacter(x, y, this);
	}

	//引数の方向番号の方向にキャラクターを１移動させる
	//移動できなかった場合はfalseを戻す
	public boolean move(int direction) {
		boolean g = true;
		try {
			int x = this.getPosition()[0];
			int y = this.getPosition()[1];
			int X = x + Util.directionVector(direction)[0];
			int Y = y + Util.directionVector(direction)[1];
			//マップ外指定例外処理
			if(this.getM().isOutSide(X, Y)) {
				throw new OutsideExc("マップの範囲外を指定しています");
			}
			if(!this.getM().getCharacterLayer()[X][Y].getType().equals("")) {
				g = false;
			}else {
				this.move(X, Y);
			}
		}catch(OutsideExc e){
			g = false;
		}
		return g;
	}

	//同じ座標のアイテム取得
	//取得するアイテム数を制限したい時は、引数のIDをi0とし、無を取得させる
	//指定したIDがキャラクターと同じ座標にない場合は無を取得
	public void takeItem(String ID1, String ID2, String ID3) {
		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		//所有アイテム配列の最後に指定したIDのアイテムを配置
		//所有アイテムがいっぱいの時、又は、指定したIDが存在しない時は無視
		if(Util.countObjArr(this.item) < 3) {
			this.setItem(Util.findItemById(this.getM(), this.getM().getItemLayer()[x][y], ID1));
			this.getM().removeItem(x, y, ID1);
		}
		if(Util.countObjArr(this.item) < 3) {
			this.setItem(Util.findItemById(this.getM(), this.getM().getItemLayer()[x][y], ID2));
			this.getM().removeItem(x, y, ID2);
		}
		if(Util.countObjArr(this.item) < 3) {
			this.setItem(Util.findItemById(this.getM(), this.getM().getItemLayer()[x][y], ID3));//ここで所持アイテム配列が全て初期化されてしまう持アイテム配列を4に増やすと解決するが、、
			this.getM().removeItem(x, y, ID3);
		}
	}

	//自分に隣接するキャラクターの数を整数で返します
	public int countNextCharacter() {
		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		int X;
		int Y;
		int nextChar = 0;
		for(int i = 0; i < 8; i++) {
			X = x + Util.directionVector(i)[0];
			Y = y + Util.directionVector(i)[1];
			if(this.getM().isOutSide(X, Y)) {
				;
			}else if(!this.getM().getCharacterLayer()[X][Y].getType().equals("")) {
				nextChar++;
			}
		}
		return nextChar;
	}

	//自分に隣接するキャラクターをキャラクター型配列に格納します
	public Character[] getNextCharacter() {
		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		int X;
		int Y;
		int counter = 0;
		Character[] nextCharacter = new Character[countNextCharacter()];
		for(int i = 0; i < 8; i++) {
			X = x + Util.directionVector(i)[0];
			Y = y + Util.directionVector(i)[1];
			if(this.getM().isOutSide(X, Y)) {
				;
			}else if(this.getM().getCharacterLayer()[X][Y].getType().equals("")){
				;
			}else {
				nextCharacter[counter] = this.getM().getCharacterLayer()[X][Y];
				counter++;
			}
		}
		return nextCharacter;
	}

	//引数の値だけHPを減らす
	//HPが0以下になった時死亡処理が走る
	public void damage(int damage) {
		int HP = this.HP;
		HP -= damage;
		if(HP < 0) {
			HP = 0;
			die();
		}
		this.HP = HP;
	}

	//引数の方向に隣接するキャラクターに1ダメージ
	public void attack(int direction) {
		try {
			int x = this.getPosition()[0];
			int y = this.getPosition()[1];
			int X = x + Util.directionVector(direction)[0];
			int Y = y + Util.directionVector(direction)[1];
			if(this.getM().isOutSide(X, Y)) {
				throw new OutsideExc("マップの範囲外を指定しています");
			}
			this.getM().getCharacterLayer()[X][Y].damage(133);
		}catch(OutsideExc e) {
			;
		}
	}

	//死亡処理
	//空キャラクターの場合は処理をスキップ
	//死んだプレイヤーの座標フィールドを(-1, -1)にする
	public void die() {
		if(this.getType().equals("")) {
			;
		}else {
			int x = this.getPosition()[0];
			int y = this.getPosition()[1];
			this.isDead = true;
			for(int i = 0; i < Util.countObjArr(this.item); i++) {
				this.getM().setItem(x, y, this.item[i]);
				this.item[i] = new Item(this.getM());
			}
			this.getM().getCharacterLayer()[x][y].setPosition(-1, -1);
			this.getM().getCharacterLayer()[x][y] = new Character(this.getM());
		}
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
