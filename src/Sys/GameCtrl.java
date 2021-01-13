package Sys;

public class GameCtrl {

	public static int dice() {
		return new java.util.Random().nextInt(6) + 1;
	}

	public static void StartGame() {

	}

	public static Player[] CreatePlayer(Map m) {
		Player player[] = new Player[2];
		player[0] = new Player(m, 'A', "ch", "ck", "cf");
		player[1] = new Player(m, 'B', "ch", "cm", "ci");
		return player;
	}

	public static void firstTurn(Map m, Player[] p) {
		m.createMapWindow();
		//GUI作成
		for(int i = 0; i < p.length; i++) {
			p[i].createCharacterStatusWindow();
			p[i].createPlayerStatusWindow();
		}
		//プレイヤーの持つパーティのループ
		for(int i = 0; i < 3; i++) {
			//プレイヤーのループ
			for(int j = 0; j < p.length; j++) {
				m.setCharacter(m.getStartPoint()[j][0], m.getStartPoint()[j][1], p[j].getParty()[i]);
				updateAllWindow(m, p);
				playerAction(m, p[j], i);
				updateAllWindow(m, p);
			}
		}
	}

	public static void turnCtrl(Map m, Player[] p) {
		//プレイヤーの持つパーティのループ
		for(int i = 0; i < 3; i++) {
			//プレイヤーのループ
			for(int j = 0; j < p.length; j++) {
				playerAction(m, p[j], i);
				updateAllWindow(m, p);
			}
		}
	}

	public static void playerAction(Map m, Player p, int partyNum) {
		System.out.println(p.getTeam() + " チーム " + p.getParty()[partyNum].getName() + " のターン");

		int walkMax = dice();
		//歩く歩数の最大値をセット
		p.getParty()[partyNum].setWalkCountMax(walkMax);
		System.out.println(walkMax + "歩あるいてください");
		//歩く
		p.getParty()[partyNum].walk(walkMax);

		System.out.println("アクションを選択してください");
		//アクションを全て表示
		for(int a = 0; a < p.getParty()[partyNum].listAction().length; a++) {
			System.out.println(a + " : " + p.getParty()[partyNum].listAction()[a]);
		}
		int selection = new java.util.Scanner(System.in).nextInt();
		System.out.print("方向を指定してください : ");
		int direction = new java.util.Scanner(System.in).nextInt();
		p.getParty()[partyNum].action(selection, direction);
	}

	//全てのwindowを更新します
	public static void updateAllWindow(Map m, Player[] p) {
		m.updateMapWindow();
		for(int i = 0; i < p.length; i++) {
			p[i].updatePlayerStatusWindow();
			p[i].updateCharacterStatusWindow();
		}
	}
}
