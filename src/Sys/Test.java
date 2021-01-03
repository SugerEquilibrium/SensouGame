package Sys;
import Obj.Character;
import Obj.Item;

public class Test {
	public static void test() {
		Map map = new Map(6, 7);
		Item i1 = new Item("i1", "testItem");
		Character c1 = new Character("c1", "testCharacter", 'A');
		map.setObj(2,3,c1);
		map.printCharacterLayer();
	}
}
