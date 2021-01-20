package Trap;

import Sys.Map;

public class Flame extends Trap {

	public Flame(Map m) {
		super(m, "tf", "FlameTrap");
	}

	//トラップの上にいるキャラクターに2ダメージ
	//火の神にはダメージ無効
	public void effect(Map m) {
		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		if(!m.getCharacterLayer()[x][y].getType().equals("cf")) {
			m.getCharacterLayer()[x][y].damage(2);
		}
	}
}
