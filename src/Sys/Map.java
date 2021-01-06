package Sys;

import Obj.Character;
import Obj.Item;
import Obj.Trap;

public class Map {
	private int Xsize;
	private int Ysize;
	private Character cl[][];		//キャラクターレイヤー
	private Item il[][][];			//アイテムレイヤー
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
	
	//同じマスに重なっているアイテム数を整数で返します
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

	//引数のインスタンスを自動的に判別し、適当なレイヤーに配置します
	public void setObj(int x, int y, Object o) {
		if(o instanceof Character) {
			this.cl[x][y] = (Character)o;
		}else if(o instanceof Item) {
			this.il[x][y][countStackedItem(x, y)] = (Item)o;
		}else if(o instanceof Trap) {
			this.tl[x][y] = (Trap)o;
		}
	}
	
	public void removeObj(int x, int y, char target) {
		if(target == 'c') {
			this.cl[x][y] = new Character();
		}else if(target == 'i') {
			this.il[x][y][countStackedItem(x, y)-1] = new Item();
		}else if(target == 't') {
			this.tl[x][y] = new Trap();
		}else {
			System.out.println("削除対象は'c','i','t'のいずれかで指定してください");
		}
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
