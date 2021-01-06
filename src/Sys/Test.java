package Sys;
import Obj.Character;
import Obj.Item;

public class Test {
	public static void test() {
		Map map = new Map(6, 7);
		Item i1 = new Item("i1", "testItem1");
		Item i2 = new Item("i2", "testItem2");
		Character c1 = new Character("c1", "testCharacter", 'A');
		map.setObj(2,3,c1);
		System.out.println(map.countStackedItem(1, 2));
		System.out.println(map.getItemLayer()[1][2][0].getID());
		map.setObj(1, 2, i1);
		System.out.println(map.countStackedItem(1, 2));
		System.out.println(map.getItemLayer()[1][2][0].getID());
		map.setObj(1, 2, i2);
		System.out.println(map.countStackedItem(1, 2));
		System.out.println(map.getItemLayer()[1][2][0].getID());
		map.printCharacterLayer();
		map.printItemLayer();
		
		
		map.setObj(1,2, c1);
		map.printMap();
	}
}