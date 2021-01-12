package Sys;

public class GameCtrl {

	public static int dice() {
		return new java.util.Random().nextInt(6) + 1;
	}

	public static Player[] startGame(Map m) {
		Player player[] = new Player[2];
		player[0] = new Player(m, 'A', "ch", "ck", "cf");
		player[1] = new Player(m, 'B', "ch", "cm", "ca");
		return player;
	}

	public static void firstTrun(Player p) {

	}

	public static void turnCtrl(Player p1, Player p2) {
		for(int i = 0; i < 3; i++) {

		}
	}
}
