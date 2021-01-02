
public class Map {
	int Xsize;
	int Ysize;
	Object map[][];

	Map(int Xsize, int Ysize){
		this.Xsize = Xsize;
		this.Ysize = Ysize;
		map = new Object[Xsize][Ysize];
		
		//map初期化
		Empty e = new Empty();
		for(int x = 0; x < Xsize; x++) {
			for(int y = 0; y < Ysize; y++) {
				setObj(x, y, e);
			}
		}
	}

	public void print() {
		//列の横幅の最大を決定
		int widthMax[] = new int[Xsize];
		for(int x = 0; x < Xsize; x++) {
			widthMax[x] = 0;
			for(int y = 0; y < Ysize; y++) {
				if(widthMax[x] < map[x][y].name.length()) {
					widthMax[x] = map[x][y].name.length();
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
				System.out.print(map[x][y].name);
				for(int i = 0; i < widthMax[x] - map[x][y].name.length(); i++) {
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
			System.out.print("|\n");
		}
	}
	
	public void setObj(int x, int y, Object o) {
		map[x][y] = o;
	}
}
