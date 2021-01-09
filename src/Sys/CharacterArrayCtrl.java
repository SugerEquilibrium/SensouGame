package Sys;

import Obj.Character;

public class CharacterArrayCtrl {
	
	public static void listCharacter(Character[] c) {
		for(int i = 0; i < c.length; i++) {
			System.out.println(i + " : " + c[i].getName());
		}
	}

}
