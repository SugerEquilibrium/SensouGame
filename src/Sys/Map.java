package Sys;

import Obj.Character;
import Obj.Item;
import Obj.Trap;

public class Map {
	private int Xsize;
	private int Ysize;
	private Character cl[][];		//キャラクターレイヤー
	private Item il[][];			//アイテムレイヤー
	private Trap tl[][];			//トラップレイヤー

	public Character[][] getCharacterLayer(){
		return this.cl;
	}

	public Item[][] getItemLayer(){
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
		this.il = new Item[x][y];
		this.tl = new Trap[x][y];
		Character ce = new Character();
		Item ie = new Item();
		Trap te = new Trap();

		for(int x1 = 0; x1 < Xsize; x1++) {
			for(int y1 = 0; y1 < Ysize; y1++) {
				cl[x1][y1] = ce;
				il[x1][y1] = ie;
				tl[x1][y1] = te;
			}
		}
	}

	public void setObj(int x, int y, Object o) {
		if(o instanceof Character) {
			this.cl[x][y] = (Character)o;
		}else if(o instanceof Item) {
			this.il[x][y] = (Item)o;
		}else if(o instanceof Trap) {
			this.tl[x][y] = (Trap)o;
		}
	}

	public void printCharacterLayer() {
		//列の横幅の最大を決定
		int widthMax[] = new int[Xsize];
		for(int x = 0; x < Xsize; x++) {
			widthMax[x] = 0;
			for(int y = 0; y < Ysize; y++) {
				if(widthMax[x] < cl[x][y].getName().length()) {
					widthMax[x] = cl[x][y].getName().length();
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
				System.out.print(cl[x][y].getName());
				for(int i = 0; i < widthMax[x] - cl[x][y].getName().length(); i++) {
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

}
