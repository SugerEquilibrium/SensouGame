
public class Test {
	public static void test() {
		Map m = new Map(6, 5);
		Character c1 = new Character("c1", "testCharacter", 'A');
		m.setObj(2,3,c1);
		m.print();
	}
}
