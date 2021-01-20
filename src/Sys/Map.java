package Sys;

import Character.Character;
import GUI.GuiMap;
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
	private GuiMap window;
	private int startPoint[][];		//スタート地点

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

	public GuiMap getWindow() {
		return this.window;
	}

	public int[][] getStartPoint(){
		return this.startPoint;
	}

	public Map(int x, int y) {
		this.Xsize = x;
		this.Ysize = y;
		this.cl = new Character[x][y];
		this.il = new Item[x][y][100];
		this.tl = new Trap[x][y];
		this.ll = new Land[x][y];
		this.startPoint = new int[2][2];
		//Aチームのスタート地点
		this.startPoint[0][0] = 0;
		this.startPoint[0][1] = 0;
		//Bチームのスタート地点
		this.startPoint[1][0] = this.Xsize - 1;
		this.startPoint[1][1] = this.Ysize - 1;
		Character ce = new Character(this);
		Item ie = new Item(this);
		Trap te = new Trap(this);
		Land le = new Land(this);
		for(int x1 = 0; x1 < this.Xsize; x1++) {
			for(int y1 = 0; y1 < this.Ysize; y1++) {
				this.setCharacter(x1, y1);
				this.setTrap(x1, y1);
				this.setLand(x1, y1);
				for(int stack = 0; stack < this.il[x1][y1].length; stack++) {
					this.setItem(x1, y1, stack);
				}
			}
		}
	}

	//引数のIDに一致するキャラクターの座標を取得
//	public int[] getPosition(String ID) {
//		int position[] = new int[2];
//		for(int x = 0; x < this.Xsize; x++) {
//			for(int y = 0; y < this.Ysize; y++) {
//				if(this.cl[x][y].getID().equals(ID)) {
//					position[0] = x;
//					position[1] = y;
//				}
//				if(this.tl[x][y].getID().equals(ID)) {
//					position[0] = x;
//					position[1] = y;
//				}
//			}
//		}
//		return position;		//見つからなかった場合はnull配列を返す（要修正
//	}

	//引数の座標にキャラクターをセット
	public void setCharacter(int x, int y, Character c) {
		this.cl[x][y] = c;
		c.setPosition(x, y);
	}
	//引数の座標に空キャラクターをセット
	public void setCharacter(int x, int y) {
		Character c = new Character(this);
		this.cl[x][y] = c;
		c.setPosition(x, y);
	}

	//アイテムを一番上にセット
	public void setItem(int x, int y, Item i) {
		this.il[x][y][Util.countObjArr(this.il[x][y])] = i;
		i.setPosition(x, y, Util.countObjArr(this.il[x][y]));
	}
	//引数の座標、スタック番号に空アイテムをセット
	public void setItem(int x, int y, int stack) {
		Item i = new Item(this);
		this.il[x][y][stack] = i;
		i.setPosition(x, y, stack);
	}

	//引数の座標にトラップをセット
	public void setTrap(int x, int y, Trap t) {
		this.tl[x][y] = t;
		t.setPosition(x, y);
	}
	//引数の座標に空トラップをセット
	public void setTrap(int x, int y) {
		Trap t = new Trap(this);
		this.tl[x][y] = t;
		t.setPosition(x, y);
	}

	//引数の座標に地形オブジェクトをセット
	public void setLand(int x, int y, Land l) {
		this.ll[x][y] = l;
		l.setPosition(x, y);
	}
	//引数の座標に空地形オブジェクトをセット
	public void setLand(int x, int y) {
		Land l = new Land(this);
		this.ll[x][y] = l;
		l.setPosition(x, y);
	}

	//引数の座標のキャラクターを消去
	public void removeCharacter(int x, int y) {
		this.cl[x][y].setPosition(-1, -1);	//マップ上に存在しないオブジェクトの座標は(-1, -1)とする
		this.setCharacter(x, y);
	}

	//指定した座標のスタックアイテム配列から引数のアイテムタイプ識別子を検索し、一つだけ削除、上詰め
	public void removeItem(int x, int y, String type) {
		Item i = Util.findItemById(this, this.il[x][y], type);
		i.setPosition(-1, -1, -1);
		for(int stack = i.getPosition()[2]; stack < Util.countObjArr(this.il[x][y]); stack++) {
			this.il[x][y][stack] = this.il[x][y][stack + 1];
		}
	}

	//引数の座標のトラップを削除
	public void removeTrap(int x, int y) {
		this.tl[x][y].setPosition(-1, -1);
		this.setTrap(x, y);
	}

	//引数の座標の地形オブジェクトを削除
	public void removeLand(int x, int y) {
		this.ll[x][y].setPosition(-1, -1);
		this.setLand(x, y);
	}

	//指定した座標のスタックアイテム配列から引数のアイテムタイプ識別子を検索し移動、移動元は削除、上詰め
	//座標を(x -> X, y -> Y)に移動
	public void moveItem(int x, int y, String ID, int X, int Y) {
		setItem(X, Y, Util.findItemById(this, this.il[x][y], ID));
		removeItem(x, y, ID);
	}

	//座標(x -> X, y -> Y)にトラップを移動
	public void moveTrap(int x, int y, int X, int Y) {
		setTrap(X, Y, this.tl[x][y]);
		removeTrap(x, y);
	}

	//引数の座標が盤面の範囲外かどうかをboolean型で返します
	public boolean isOutSide(int x, int y) {
		boolean out = false;
		if(x >= this.Xsize || y >= this.Ysize || x < 0 || y < 0) {
			out = true;
//			throw new OutsideExc("マップの範囲外を指定しています");
		}
		return out;
	}

	//引数の座標にオブジェクトが入れるかどうかをboolean型で返します
	public boolean isOverLap(int x, int y, char c) {
		boolean isOverLap = false;
		switch (c){
		case 'c':
			if(!cl[x][y].getType().equals("")) {
				isOverLap = true;
			}
			break;
		case 'i':
			if(!cl[x][y].getType().equals("")) {
				isOverLap = true;
			}
			break;
		case 't':
			if(!tl[x][y].getType().equals("")) {
				isOverLap = true;
			}
			break;
		}
		return isOverLap;
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
					displayNameC[x][y] = "[" + this.cl[x][y].getType() + "] " + this.cl[x][y].getName();
				}else {
					displayNameC[x][y] =  "[" + this.cl[x][y].getType() + "] " + this.cl[x][y].getName() + " (" + this.cl[x][y].getTeam() + ")";
				}
				displayNameT[x][y] = "[" + this.tl[x][y].getType() + "] " + this.tl[x][y].getName();
				displayNameL[x][y] = "[" + this.ll[x][y].getType() + "] " + this.ll[x][y].getName();
				for(int stack = 0; stack < 100; stack++) {
					displayNameI[x][y][stack] = "[" + this.il[x][y][stack].getType() + "] " + this.il[x][y][stack].getName();
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

	public void createMapWindow() {
		this.window = new GuiMap(this);
	}

	public void updateMapWindow() {
		this.window.updateWindow(this);
	}
}
