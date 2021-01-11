package Sys;
import Character.Character;
import Character.Fire;
import GUI.CharacterStatus;
import Item.Item;
import Land.Land;
import Trap.Flame;
import Trap.Trap;

public class Test {
	public static void test() {
		Map map = new Map(6, 3);
		Item i1 = new Item(map, "i1", "testItem1");
		Item i2 = new Item(map, "i2", "testItem2");
		Item i3 = new Item(map, "i3", "testItem3");
		Trap f1 = new Flame(map);
		Land l1 = new Land(map, "l1", "testLand1");
		Character c1 = new Character(map, "c1", "testCharacter", 'A');
		Character c2 = new Character(map, "c2", "testCharacter", 'B');
//		map.setCharacter(2,3,c1);
		map.setItem(1, 2, i1);
		map.setItem(1, 2, i2);
		map.setItem(1, 2, i1);
		map.setItem(1, 2, i3);
		map.setTrap(1, 2, f1);
		map.setLand(1, 2, l1);
		map.setCharacter(1,2, c1);
//		System.out.println(map.findItemById(1, 2, "i1"));
//		System.out.println(map.countStackedItem(1, 2));


//		map.removeItem(1, 2, map.findItemById(1, 2, "i1"));
//		map.moveItem(1, 2, "i1", 2, 2);
		map.moveCharacter(1, 2, 4, 2);
		map.moveTrap(1, 2, 3, 0);

//		map.listStackedItem(1, 2);

//		System.out.println(map.collision(12, 12, 'c'));

		c1.move(6);
		c1.move(6);
		c1.move(6);

//		ItemStackCtrl.listItem(map.getItemLayer()[1][2]);
//		System.out.println();

		c1.takeItem("i1", "i0", "i1");
		System.out.println(c1.getName() + " (ID : " + c1.getID() + ") の" + Util.countItemArr(c1.getItem()) + "個の所持品");
		Util.listItemArr(c1.getItem());

		System.out.println("\n" + c1.getID() + "の座標 : (" + map.getPosition("c1")[0] + ", " + map.getPosition("c1")[1] + ")");

		map.setCharacter(0, 1, c2);

		System.out.println("\n" + c1.getID() + "に隣接するキャラクター");
		Util.listCharacterArray(c1.getNextCharcter());

		c1.attack(7);
		System.out.println("c1のHP = " + c1.getHP());
		System.out.println("c2のHP = " + c2.getHP());


		System.out.println(Util.searchNewId(map, "c") + "\n");


		Fire cf1 = new Fire(map, 'A');
		map.setCharacter(0, 2, cf1);
		map.setCharacter(0, 0, new Fire(map, 'B'));
		map.setTrap(1, 2, new Flame(map));

//		System.out.println(map.getCharacterLayer()[1][2].getID().equals("c1"));

//		map.printCharacterLayer();
//		map.printItemLayer();
		map.printMap();


//		cf1.walk(100);

		map.getTrapLayer()[1][2].effect(map);

		System.out.println("c1のHP = " + c1.getHP());

		CharacterStatus w1 = new CharacterStatus(c1);
		CharacterStatus w2 = new CharacterStatus(cf1);
		CharacterStatus w3 = new CharacterStatus(c2);

//		System.out.println(map.collision(3, 0, 'c'));

	}
}