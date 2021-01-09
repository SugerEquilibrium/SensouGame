package Sys;
import Obj.Character;
import Obj.Item;
import Obj.Trap;

public class Test {
	public static void test() {
		Map map = new Map(6, 3);
		Item i1 = new Item("i1", "testItem1");
		Item i2 = new Item("i2", "testItem2");
		Item i3 = new Item("i3", "testItem3");
		Trap t1 = new Trap("t1", "testTrap1");
		Character c1 = new Character("c1", "testCharacter", 'A');
//		map.setCharacter(2,3,c1);
		map.setItem(1, 2, i1);
		map.setItem(1, 2, i2);
		map.setItem(1, 2, i1);
		map.setItem(1, 2, i3);
		map.setTrap(1, 2, t1);
		map.setCharacter(1,2, c1);
//		System.out.println(map.findItemById(1, 2, "i1"));
//		System.out.println(map.countStackedItem(1, 2));


//		map.removeItem(1, 2, map.findItemById(1, 2, "i1"));
		map.moveItem(1, 2, "i1", 2, 2);
		map.moveCharacter(1, 2, 4, 2);
		map.moveTrap(1, 2, 3, 0);
		
//		System.out.println(map.collision(12, 12, 'c'));
		
		c1.move(map, 0);
		
		System.out.println("c1の座標 : (" + map.getPosition("c1")[0] + ", " + map.getPosition("c1")[1] + ")");
		
//		map.printCharacterLayer();
//		map.printItemLayer();
		map.printMap();
	}
}