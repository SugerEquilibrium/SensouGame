package Sys;

import Character.Character;
import Character.Fire;
import Character.Hayaashi;
import Character.Ice;
import Character.Kinnniku;
import Character.Mud;
import Item.Item;

public class Util {

	//方向番号を受け取り、それに対応する相対的な座標系のベクトルの配列を返します
	//方向番号は時計回りに0時方向から0で始まり7で終わる
	public static int[] directionVector(int direction) {
		int[] v = {0, 0};
		switch(direction) {
		case 0:
			v[1] -= 1;
			break;
		case 1:
			v[0] += 1;
			v[1] -= 1;
			break;
		case 2:
			v[0] +=1;
			break;
		case 3:
			v[0] += 1;
			v[1] += 1;
			break;
		case 4:
			v[1] += 1;
			break;
		case 5:
			v[0] -= 1;
			v[1] += 1;
			break;
		case 6:
			v[0] -= 1;
			break;
		case 7:
			v[0] -= 1;
			v[1] -= 1;
			break;
		}
		return v;
	}

	//渡されたキャラクター型配列を列挙します
	public static void listCharacterArray(Character[] c) {
		for(int i = 0; i < c.length; i++) {
			System.out.println(i + " : " + c[i].getName());
		}
	}

	//オブジェクト型配列に入っている実際のオブジェクトの個数を整数で返します
	public static int countObjArr(Obj[] obj) {
		int last = 0;
		if(obj.length == 1) {
			if(!obj[0].getType().equals("")) {
				last = 1;
			}
		}else {
			for(int i = 0; i < obj.length; i++) {
				if(obj[i].getType().equals("")) {
					last = i;
					break;
				}
				if(i == obj.length -1) {
					last = obj.length;
				}
			}
		}
		return last;
	}

	//オブジェクト型配列を読み込み、引数のIDを検索し、配列番号を返します
	//同じIDが２つ以上あった場合、配列番号の大きい法の値を返します
//	public static int findObjById(Obj[] obj, String type) {
//		int stack = 99867678;
//		for(int count = 0; count < countObjArr(obj); count++) {
//			if(obj[count].getType().equals(type)) {
//				stack = count;
//				break;
//			}
//		}
//		return stack;
//	}

	//アイテムスタック配列を読み込み、引数のIDを検索し、アイテム型変数を返します
	//同じIDが２つ以上あった場合、スタック番号の大きい方の値を返します
	//見つからなかった場合は空アイテムを返します
	public static Item findItemById(Map m, Item[] item, String type) {
		Item i = new Item(m);
		for(int count = 0; count < countObjArr(item); count++) {
			if(item[count].getType().equals(type)) {
				i = item[count];
			}
		}
		return i;
	}

	//渡されたアイテム配列の内容を全て表示します
	public static void listItemArr(Item[] item) {
		for(int stack = 0; stack < countObjArr(item); stack++) {
			System.out.println(stack + " : " + item[stack].getName());
		}
	}

	//アイテム配列の空欄を上詰め
//	public static void upShift(Item[] item) {
//		for(int i = 0; i < countObjArr(item); i++) {
//			removeItemById(item, "i0");
//		}
//	}

	//Map外にあるキャラクターのIDを考慮せずに新しいIDを振ってしまうため重複が発生する

	//フィールドからキャラクター以外の全てのオブジェクトを検索し、未使用のIDを返す
	//ID規則 : "オブジェクトタイプ" + "キャラクター種類" + "通し番号"
	//例 : 2番目の早足くん -> ch2
//	public static String searchNewId(Map m, String type) {
//		String ID = new String(type);
//		int i = 1;
//		boolean using;
//		do {
//			using = false;
//			ID = type + i + "";
//			for(int x = 0; x < m.getXsize(); x++) {
//				for(int y = 0; y < m.getYsize(); y++) {
//					if(!findItemById(m, m.getItemLayer()[x][y], ID.toString()).getType().equals("")) {
//						using = true;
//					}
//					else if(m.getTrapLayer()[x][y].getType().equals(ID.toString())) {
//						using = true;
//					}
//					else if(m.getLandLayer()[x][y].getType().equals(ID.toString())) {
//						using = true;
//					}
//				}
//			}
//			i += 1;
//		}while(using);
//		return ID.toString();
//	}
//	public static String serchNewCharacterId(Map m, Player[] p, String type) {
//		String ID = new String(type);
//		int i = 1;
//		boolean using;
//		do {
//			using = false;
//			ID = type + i + "";
//			for(int player = 0; player < p.length; player++) {
//				for(int party = 0; party < p[player].getParty().length; party++) {
//					if(p[player].getParty()[party].getID().equals(ID.toString())) {
//						using = true;
//					}
//				}
//			}
//			i += 1;
//		}while(using);
//		return ID.toString();
//	}

	//キャラクタータイプ識別子に応じたキャラクターを生成し、返します
	public static Character characterGen(Map m, char team, String type) {
		Character c;
		if(type.equals("cf")) {
			c = new Fire(m, team);
		}else if(type.equals("ch")) {
			c = new Hayaashi(m, team);
		}else if(type.equals("ci")) {
			c = new Ice(m, team);
		}else if(type.equals("ck")) {
			c = new Kinnniku(m, team);
		}else if(type.equals("cm")) {
			c = new Mud(m, team);
		}else {
			c = new Character(m);
		}
		return c;
	}
}
