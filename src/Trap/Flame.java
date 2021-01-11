package Trap;

import Sys.Map;
import Sys.Util;

public class Flame extends Trap {
	
	public Flame(Map m) {
		super(m, Util.searchNewId(m, "tf"), "FlameTrap");
	}
	
	//トラップの上にいるキャラクターに2ダメージ
	//火の神にはダメージ無効
	public void effect(Map m) {
		int x = m.getPosition(this.getID())[0];
		int y = m.getPosition(this.getID())[1];
		if(!m.getCharacterLayer()[x][y].getType().equals("cf")) {
			m.getCharacterLayer()[x][y].damage(2);
		}
	}
}
