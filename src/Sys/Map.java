package Sys;

import Obj.Character;
import Obj.Item;
import Obj.Trap;

public class Map {
	private int Xsize;
	private int Ysize;
	private Character cl[][];		//キャラクターレイヤー
	private Item il[][][];			//アイテムレイヤー (最大100アイテムまで保管可能)
	private Trap tl[][];			//トラップレイヤー

	public Character[][] getCharacterLayer(){
		return this.cl;
	}

	public Item[][][] getItemLayer(){
		return this.il;
	}

	public Trap[][] getTrapLayer(){
		return this.tl;
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
		Character ce = new Character();
		Item ie = new Item();
		Trap te = new Trap();

		for(int x1 = 0; x1 < Xsize; x1++) {
			for(int y1 = 0; y1 < Ysize; y1++) {
				cl[x1][y1] = ce;
				tl[x1][y1] = te;
				for(int stack = 0; stack < 100; stack++) {
					il[x1][y1][stack] = ie;
				}
			}
		}
	}

	//同じ座標に重なっているアイテムの個数を整数で返します
	public int countStackedItem(int x, int y) {
		int last = 0;
		for(int i = 0; i < 100; i++) {
			if(this.il[x][y][i].getID().equals("i0")) {
				last = i;
				break;
			}
		}
		return last;
	}

	//同じ座標に重なっているアイテムから引数のIDのアイテムを検索し、そのスタック番号をひとつだけ返します。
	//同じIDが２つ以上あった場合、スタック番号の大きい法の値を返します
	public int findItemById(int x, int y, String ID) {
		int stack = 99;
		for(int count = 0; count < countStackedItem(x, y); count++) {
			if(il[x][y][count].getID().equals(ID)) {
				stack = count;
			}
		}
		return stack;
	}

	//引数の座標にキャラクターをセット
	public void setCharacter(int x, int y, Character c) {
		this.cl[x][y] = c;
	}

	//アイテムを一番上にセット
	public void setItem(int x, int y, Item i) {
		this.il[x][y][countStackedItem(x, y)] = i;
	}

	//引数の座標にトラップをセット
	public void setTrap(int x, int y, Trap t) {
		this.tl[x][y] = t;
	}

	//引数の座標のキャラクターを消去
	public void removeCharacter(int x, int y) {
		this.cl[x][y] = new Character();
	}

	//指定した座標に重なっているアイテムから引数のスタック番号のアイテムを削除
	public void removeItem(int x, int y, int count) {
		for(int c = count; c < countStackedItem(x, y); c++) {
			this.il[x][y][c] = this.il[x][y][c + 1];
		}
	}

	//引数の座標のトラップを削除
	public void removeTrap(int x, int y) {
		this.tl[x][y] = new Trap();
	}

	//座標(x -> X, y -> Y)にキャラクターを移動
	public void moveCharacter(int x, int y, int X, int Y) {
		setCharacter(X, Y, this.cl[x][y]);
		removeCharacter(x, y);
	}

	//指定した座標に重なっているアイテムから引数のスタック番号のアイテムを移動させる
	//座標を(x -> X, y -> Y)に移動
	public void moveItem(int x, int y, int count, int X, int Y) {
		setItem(X, Y, this.il[x][y][count]);
		removeItem(x, y, count);
	}

	//座標(x -> X, y -> Y)にトラップを移動
	public void moveTrap(int x, int y, int X, int Y) {
		setTrap(X, Y, this.tl[x][y]);
		removeTrap(x, y);
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
				for(int stack = 0; stack < countStackedItem(x, y) + 1; stack++) {
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
				if(heightMax[y] < countStackedItem(x, y)) {
					heightMax[y] = countStackedItem(x, y);
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

		//列の横幅の最大を決定
		int widthMax[] = new int[Xsize];
		for(int x = 0; x < Xsize; x++) {
			widthMax[x] = 0;
			for(int y = 0; y < Ysize; y++) {
				if(widthMax[x] < this.cl[x][y].getName().length()) {
					widthMax[x] = this.cl[x][y].getName().length();
				}
				if(widthMax[x] < this.tl[x][y].getName().length()) {
					widthMax[x] = this.tl[x][y].getName().length();
				}
				for(int stack = 0; stack < countStackedItem(x, y) + 1; stack++) {
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
				if(heightMax[y] < countStackedItem(x, y)) {
					heightMax[y] = countStackedItem(x, y);
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
				System.out.print(this.cl[x][y].getName());
				for(int i = 0; i < widthMax[x] - this.cl[x][y].getName().length(); i++) {
					System.out.print(" ");
				}
			}
			System.out.println("    |");
			//アイテムレイヤー描画
			for(int stack = heightMax[y]; stack > 0; stack--) {
				for(int x = 0; x < Xsize; x++) {
					System.out.print("    |    ");
					System.out.print(this.il[x][y][stack -1].getName());
					for(int i = 0; i < widthMax[x] - this.il[x][y][stack -1].getName().length(); i++) {
						System.out.print(" ");
					}
				}
				System.out.println("    |");
			}
			//トラップレイヤー描画
			for(int x = 0; x < Xsize; x++) {
				System.out.print("    |    ");
				System.out.print(this.tl[x][y].getName());
				for(int i = 0; i < widthMax[x] - this.tl[x][y].getName().length(); i++) {
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
