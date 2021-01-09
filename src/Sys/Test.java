package Sys;
import Obj.Character;
import Obj.Item;

public class Test {
	public static void test() {
		Map map = new Map(6, 7);
		Item i1 = new Item("i1", "testItem1");
		Item i2 = new Item("i2", "testItem2");
		Item i3 = new Item("i3", "testItem3");
		Character c1 = new Character("c1", "testCharacter", 'A');
//		map.setCharacter(2,3,c1);
		map.setItem(1, 2, i1);
		map.setItem(1, 2, i2);
		map.setItem(1, 2, i1);
		map.setItem(1, 2, i3);
		map.setCharacter(1,2, c1);
		System.out.println(map.findItemById(1, 2, "i2"));
		System.out.println(map.countStackedItem(1, 2));
//		map.printCharacterLayer();
//		map.printItemLayer();
		
		
		map.removeItem(1, 2, map.findItemById(1, 2, "i1"));
//		map.moveObj(1, 2, 'i', 4, 1);
		map.printMap();
	}
}