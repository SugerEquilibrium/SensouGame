package Sys;

import Character.Character;

public class Test4 {
	public static void test4() {

		Map m = new Map(5, 4);
//		System.out.println(m.getItemLayer()[0][1][2].getType());
		
		Character c = new Character(m);
		System.out.println(m.getCharacterLayer()[0][1].getType());
	}

}
