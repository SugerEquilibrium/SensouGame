package Sys;

import Character.Character;
import Item.Item;
import Land.Land;
import Trap.Trap;

public class Map {
	private int Xsize;
	private int Ysize;
	private Character cl[][];		//キャラクターレイヤー
	private Item il[][][];			//アイテムレイヤー (最大100アイテムまで保管可能)
	private Trap tl[][];			//トラップレイヤー
	private Land ll[][];			//地形レイヤー

	public Character[][] getCharacterLayer(){
		return this.cl;
	}

	public Item[][][] getItemLayer(){
		return this.il;
	}

	public Trap[][] getTrapLayer(){
		return this.tl;
	}

	public Land[][] getLandLayer(){
		return this.ll;
	}

	public int getXsize() {
		return this.Xsize;
	}

	public int getYsize() {
		return this.Ysize;
	}

	public Map(int x, int y) {
		Xsize = x;
		Ysize = y;
		this.cl = new Character[x][y];
		this.il = new Item[x][y][100];
		this.tl = new Trap[x][y];
		this.ll = new Land[x][y];
		Character ce = new Character();
		Item ie = new Item();
		Trap te = new Trap();
		Land le = new Land();
		for(int x1 = 0; x1 < this.Xsize; x1++) {
			for(int y1 = 0; y1 < this.Ysize; y1++) {
				cl[x1][y1] = ce;
				tl[x1][y1] = te;
				ll[x1][y1] = le;
				for(int stack = 0; stack < this.il[x1][y1].length; stack++) {
					this.il[x1][y1][stack] = ie;
				}
			}
		}
	}

	//引数のIDに一致するキャラクターの座標を取得
	public int[] getPosition(String ID) {
		int position[] = new int[2];
		for(int x = 0; x < this.Xsize; x++) {
			for(int y = 0; y < this.Ysize; y++) {
				if(this.cl[x][y].getID().equals(ID)) {
					position[0] = x;
					position[1] = y;
				}
				if(this.tl[x][y].getID().equals(ID)) {
					position[0] = x;
					position[1] = y;
				}
			}
		}
		return position;		//見つからなかった場合はnull配列を返す（要修正
	}

	//引数の座標にキャラクターをセット
	public void setCharacter(int x, int y, Character c) {
		this.cl[x][y] = c;
	}

	//アイテムを一番上にセット
	public void setItem(int x, int y, Item i) {
		this.il[x][y][Util.countObjArr(this.il[x][y])] = i;
	}

	//引数の座標にトラップをセット
	public void setTrap(int x, int y, Trap t) {
		this.tl[x][y] = t;
	}

	//引数の座標に地形オブジェクトをセット
	public void setLand(int x, int y, Land l) {
		this.ll[x][y] = l;
	}

	//引数の座標のキャラクターを消去
	public void removeCharacter(int x, int y) {
		this.cl[x][y] = new Character();
	}

	//指定した座標のスタックアイテム配列から引数のアイテムIDを検索し、削除、上詰め
	public void removeItem(int x, int y, String ID) {
		Util.removeItemById(this.il[x][y], ID);
	}

	//引数の座標のトラップを削除
	public void removeTrap(int x, int y) {
		this.tl[x][y] = new Trap();
	}

	//引数の座標の地形オブジェクトを削除
	public void removeLand(int x, int y) {
		this.ll[x][y] = new Land();
	}

	//座標(x -> X, y -> Y)にキャラクターを移動
	public void moveCharacter(int x, int y, int X, int Y) {
		setCharacter(X, Y, this.cl[x][y]);
		removeCharacter(x, y);
	}

	//指定した座標のスタックアイテム配列から引数のアイテムIDを検索し移動、移動元は削除、上詰め
	//座標を(x -> X, y -> Y)に移動
	public void moveItem(int x, int y, String ID, int X, int Y) {
		setItem(X, Y, Util.findItemById(this.il[x][y], ID));
		removeItem(x, y, ID);
	}

	//座標(x -> X, y -> Y)にトラップを移動
	public void moveTrap(int x, int y, int X, int Y) {
		setTrap(X, Y, this.tl[x][y]);
		removeTrap(x, y);
	}

	//引数の座標が盤面の範囲外かどうかをboolean型で返します
	public boolean outSideError(int x, int y) {
		boolean out = false;
		if(x >= this.Xsize || y >= this.Ysize || x < 0 || y < 0) {
			out = true;
		}
		return out;
	}

	//引数の座標にオブジェクトが入れるかどうかをboolean型で返します
	public boolean collision(int x, int y, char c) {
		boolean collision = false;
		if(outSideError(x, y)) {
			collision = true;
			return collision;
		}
		switch (c){
		case 'c':
			if(!cl[x][y].getID().equals("c0")) {
				collision = true;
			}
			break;
		case 'i':
			if(!cl[x][y].getID().equals("i0")) {
				collision = true;
			}
			break;
		case 't':
			if(!tl[x][y].getID().equals("t0")) {
				collision = true;
			}
			break;
		}
		return collision;
	}

	public void printCharacterLayer() {
		//列の横幅の最大を決定
		int widthMax[] = new int[Xsize];
		for(int x = 0; x < Xsize; x++) {
			widthMax[x] = 0;
			for(int y = 0; y < Ysize; y++) {
				if(widthMax[x] < this.cl[x][y].getName().length()) {
					widthMax[x] = this.cl[x][y].getName().length();
				}
			}
		}

		//一番上の線を描画
		System.out.print(" ");
		for(int i = 0; i < Xsize; i++) {
			System.out.print("+");
			for(int j = 0; j < widthMax[i] + 2; j++) {
				System.out.print("-");
			}
		}
		System.out.print("+\n");

		//マップ本体描画
		for(int y = 0; y < Ysize; y++) {
			for(int x = 0; x < Xsize; x++) {
				System.out.print(" | ");
				System.out.print(this.cl[x][y].getName());
				for(int i = 0; i < widthMax[x] - this.cl[x][y].getName().length(); i++) {
					System.out.print(" ");
				}
			}
			System.out.println(" |");

			//横棒描画
			System.out.print(" ");
			for(int i = 0; i < Xsize; i++) {
				System.out.print("+");
				for(int j = 0; j < widthMax[i] + 2; j++) {
					System.out.print("-");
				}
			}
			System.out.print("+\n");
		}
	}

	public void printItemLayer() {
		//列の横幅の最大を決定
		int widthMax[] = new int[Xsize];
		for(int x = 0; x < Xsize; x++) {
			widthMax[x] = 0;
			for(int y = 0; y < Ysize; y++) {
				for(int stack = 0; stack < Util.countObjArr(this.il[x][y]) + 1; stack++) {
					if(widthMax[x] < this.il[x][y][stack].getName().length()) {
						widthMax[x] = this.il[x][y][stack].getName().length();
					}
				}
			}
		}

		//行の高さの最大を決定
		int heightMax[] = new int[Ysize];
		for(int y = 0; y < Ysize; y++) {
			heightMax[y] = 1;
			for(int x = 0; x < this.Xsize; x++) {
				if(heightMax[y] < Util.countObjArr(this.il[x][y])) {
					heightMax[y] = Util.countObjArr(this.il[x][y]);
				}
			}
		}

		//一番上の線を描画
		System.out.print(" ");
		for(int i = 0; i < Xsize; i++) {
			System.out.print("+");
			for(int j = 0; j < widthMax[i] + 2; j++) {
				System.out.print("-");
			}
		}
		System.out.print("+\n");

		//マップ本体描画
		for(int y = 0; y < Ysize; y++) {
			for(int stack = heightMax[y]; stack > 0; stack--) {
				for(int x = 0; x < Xsize; x++) {
					System.out.print(" | ");
					System.out.print(this.il[x][y][stack -1].getName());
					for(int i = 0; i < widthMax[x] - this.il[x][y][stack -1].getName().length(); i++) {
						System.out.print(" ");
					}
				}
				System.out.println(" |");
			}

			//横棒描画
			System.out.print(" ");
			for(int i = 0; i < Xsize; i++) {
				System.out.print("+");
				for(int j = 0; j < widthMax[i] + 2; j++) {
					System.out.print("-");
				}
			}
			System.out.print("+\n");
		}
	}

	public void printMap() {

		//表示名を決定
		//displayNameの文字列を変更することで簡単に表示名のフォーマットを変更できる
		String displayNameC[][] = new String[this.Xsize][this.Ysize];
		String displayNameI[][][] = new String[this.Xsize][this.Ysize][100];
		String displayNameT[][] = new String[this.Xsize][this.Ysize];
		String displayNameL[][] = new String[this.Xsize][this.Ysize];
		for(int x = 0; x < Xsize; x++) {
			for(int y = 0; y < Ysize; y++) {
				if(this.cl[x][y].getTeam() == 0) {
					displayNameC[x][y] = "[" + this.cl[x][y].getID() + "] " + this.cl[x][y].getName();
				}else {
					displayNameC[x][y] =  "[" + this.cl[x][y].getID() + "] " + this.cl[x][y].getName() + " (" + this.cl[x][y].getTeam() + ")";
				}
				displayNameT[x][y] = "[" + this.tl[x][y].getID() + "] " + this.tl[x][y].getName();
				displayNameL[x][y] = "[" + this.ll[x][y].getID() + "] " + this.ll[x][y].getName();
				for(int stack = 0; stack < 100; stack++) {
					displayNameI[x][y][stack] = "[" + this.il[x][y][stack].getID() + "] " + this.il[x][y][stack].getName();
				}
			}
		}

		//列の横幅の最大を決定
		int widthMax[] = new int[Xsize];
		for(int x = 0; x < Xsize; x++) {
			widthMax[x] = 0;
			for(int y = 0; y < Ysize; y++) {
				if(widthMax[x] < displayNameC[x][y].length()) {
					widthMax[x] = displayNameC[x][y].length();
				}
				if(widthMax[x] < displayNameT[x][y].length()) {
					widthMax[x] = displayNameT[x][y].length();
				}
				if(widthMax[x] < displayNameL[x][y].length()) {
					widthMax[x] = displayNameL[x][y].length();
				}
				for(int stack = 0; stack < Util.countObjArr(this.il[x][y]) + 1; stack++) {
					if(widthMax[x] < displayNameI[x][y][stack].length()) {
						widthMax[x] = displayNameI[x][y][stack].length();
					}
				}
			}
		}

		//行の高さの最大を決定
		int heightMax[] = new int[Ysize];
		for(int y = 0; y < Ysize; y++) {
			heightMax[y] = 1;
			for(int x = 0; x < this.Xsize; x++) {
				if(heightMax[y] < Util.countObjArr(this.il[x][y])) {
					heightMax[y] = Util.countObjArr(this.il[x][y]);
				}
			}
		}

		//一番上の線を描画
		System.out.print("    ");
		for(int i = 0; i < Xsize; i++) {
			System.out.print("+");
			for(int j = 0; j < widthMax[i] + 8; j++) {
				System.out.print("-");
			}
		}
		System.out.print("+\n");

		//マップ内容描画
		for(int y = 0; y < Ysize; y++) {
			//キャラクターレイヤー描画
			for(int x = 0; x < Xsize; x++) {
				System.out.print("    |    ");
				System.out.print(displayNameC[x][y]);
				for(int i = 0; i < widthMax[x] - displayNameC[x][y].length(); i++) {
					System.out.print(" ");
				}
			}
			System.out.println("    |");
			//アイテムレイヤー描画
			for(int stack = heightMax[y]; stack > 0; stack--) {
				for(int x = 0; x < Xsize; x++) {
					System.out.print("    |    ");
					System.out.print(displayNameI[x][y][stack -1]);
					for(int i = 0; i < widthMax[x] - displayNameI[x][y][stack -1].length(); i++) {
						System.out.print(" ");
					}
				}
				System.out.println("    |");
			}
			//トラップレイヤー描画
			for(int x = 0; x < Xsize; x++) {
				System.out.print("    |    ");
				System.out.print(displayNameT[x][y]);
				for(int i = 0; i < widthMax[x] - displayNameT[x][y].length(); i++) {
					System.out.print(" ");
				}
			}
			System.out.println("    |");
			//地形レイヤー描画
			for(int x = 0; x < Xsize; x++) {
				System.out.print("    |    ");
				System.out.print(displayNameL[x][y]);
				for(int i = 0; i < widthMax[x] - displayNameL[x][y].length(); i++) {
					System.out.print(" ");
				}
			}
			System.out.println("    |");

			//横棒描画
			System.out.print("    ");
			for(int i = 0; i < Xsize; i++) {
				System.out.print("+");
				for(int j = 0; j < widthMax[i] + 8; j++) {
					System.out.print("-");
				}
			}
			System.out.print("+\n");
		}
	}
}
