package Sys;

public class Test3 {
	public static void test3() {


		Map m = new Map(4, 5);
		Player[] p = GameCtrl.CreatePlayer(m);
		GameCtrl.firstTurn(m, p);
		for(int turn = 0; ; turn++) {
			GameCtrl.turnCtrl(m, p);
		}
	}

}
