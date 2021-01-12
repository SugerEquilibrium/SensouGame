package Sys;

public class Test2 {

	public static void test2() {
		Map m = new Map(5, 3);
		Player p1 = new Player(m, 'A', "ck", "ci", "cm");
		p1.putCharacter(0, 0, 1);
		p1.putCharacter(1, 1, 1);
		p1.getParty()[0].attack(2);	//虚無を殴ろうとするとクラッシュする問題
//		p1.getParty()[0].walk(193);
		p1.giveFirstItem();
		p1.getParty()[0].die();
		p1.createCharacterStatusWindow();
		p1.createPlayerStatusWindow();
		m.printMap();
		
		
	}
}
