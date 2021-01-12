package Sys;

import Character.Character;
import GUI.CharacterStatus;
import Item.Mushroom;

public class Player {
	private Character[] party;
	private CharacterStatus[] window;
	private char team;
	private Map m;
	
	public Character[] getParty() {
		return this.party;
	}
	
	public Player(Map m, char team, String type0, String type1, String type2){
		this.team = team;
		this.m = m;
		this.party = new Character[3];
		this.window = new CharacterStatus[3];
		//引数のキャラクタータイプ識別子に一致するキャラクターをパーティ配列に一つづつ代入
		this.party[0] = Util.characterGen(m, team, type0);
		this.party[1] = Util.characterGen(m, team, type1);
		this.party[2] = Util.characterGen(m, team, type2);
	}
	
	public void putCharacter(int partyNum, int x, int y) {
		this.m.setCharacter(x, y, this.party[partyNum]);
	}
	
	public void createCharacterStatusWindow() {
		for(int i = 0; i < party.length; i++) {
			this.window[i] = new CharacterStatus(this.party[i]);
		}
	}
	
	public void updateCharacterStatusWindow() {
		for(int i = 0; i < party.length; i++) {
			this.window[i].updateWindow(this.party[i]);
		}
	}
	
	public void giveFirstItem() {
		for(int c = 0; c < party.length; c++) {
			this.party[c].setItem(new Mushroom(this.m));
		}
	}
}