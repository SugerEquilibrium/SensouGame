package Sys;

import Obj.Item;

public class ItemStackCtrl {

	//アイテムスタック配列に入っているアイテムの個数を整数で返します
	public static int countItem(Item[] item) {
		int last = 0;
		if(item.length == 1) {
			if(!item[0].getID().equals("i0")) {
				last = 1;
			}
		}else {			
			for(int i = 0; i < item.length; i++) {
				if(item[i].getID().equals("i0")) {
					last = i;
					break;
				}
				if(i == item.length -1) {
					last = item.length;
				}
			}
		}
		return last;
	}

	//アイテムスタック配列を読み込み、引数のIDを検索し、スタック番号を返します
	//同じIDが２つ以上あった場合、スタック番号の大きい法の値を返します
	public static int stackNumber(Item[] item, String ID) {
		int stack = 99867678;
		for(int count = 0; count < countItem(item); count++) {
			if(item[count].getID().equals(ID)) {
				stack = count;
				break;
			}
		}
		return stack;
	}
	
	//アイテムスタック配列を読み込み、引数のIDを検索し、アイテム型変数を返します
	//同じIDが２つ以上あった場合、スタック番号の大きい方の値を返します
	//見つからなかった場合はからアイテムを返します
	public static Item findItemById(Item[] item, String ID) {
		Item i = new Item();
		for(int count = 0; count < countItem(item); count++) {
			if(item[count].getID().equals(ID)) {
				i = item[count];
			}
		}
		return i;
	}
	
	//アイテムスタック配列の内容を全て表示します
	public static void listItem(Item[] item) {
		for(int stack = 0; stack < countItem(item); stack++) {
			System.out.println(stack + " : " + item[stack].getName());
		}
	}
	
	//指定したIDのアイテムを一つだけ削除して上詰め
	public static void removeItemById(Item[] item, String ID) {
		for(int c = ItemStackCtrl.stackNumber(item, ID); c < ItemStackCtrl.countItem(item); c++) {
			item[c] = item[c + 1];
		}
	}
	
	//アイテム配列の空欄を上詰め
	public static void upShift(Item[] item) {
		for(int i = 0; i < countItem(item); i++) {
			removeItemById(item, "i0");
		}
	}
}
