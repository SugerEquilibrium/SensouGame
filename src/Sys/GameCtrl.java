package Sys;
import Obj.Character;

public class GameCtrl {
	
	public static void resetGame(Map m) {
		
	}

	//引数のキャラクターをwalk回ユーザの方向を読み取り移動させます
	public static void walk(Map m, Character c, int walk) {
		int x;
		int y;
		int X;
		int Y;
		int direction;
		for(int i = 0; i < walk; i++) {
			do {
				System.out.println("歩く方向を指定してください");
				x = m.getPosition(c.getID())[0];
				y = m.getPosition(c.getID())[1];
				direction = new java.util.Scanner(System.in).nextInt();
				X = x + Util.directionVector(direction)[0];
				Y = y + Util.directionVector(direction)[1];
				if(m.collision(X, Y, 'c')) {
					System.out.println("その方向には進めません");
				}
			}while(m.collision(X, Y, 'c'));
			c.move(m, direction);
			m.printMap();
		}
	}
	
	public static void turnCtrl() {
		
	}
}
