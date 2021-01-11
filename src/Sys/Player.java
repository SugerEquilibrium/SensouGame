package Sys;

import Character.Character;
import GUI.CharacterStatus;

public class Player {
	private Character[] party;
	private CharacterStatus[] window;
	private char team;
	
	public Character[] getParty() {
		return this.party;
	}
	
	public Player(Map m, char team, String type0, String type1, String type2){
		this.team = team;
		this.party = new Character[3];
		this.window = new CharacterStatus[3];
		//引数のキャラクタータイプ識別子に一致するキャラクターをパーティ配列に一つづつ代入
		this.party[0] = Util.characterGen(m, team, type0);
		this.party[1] = Util.characterGen(m, team, type1);
		this.party[2] = Util.characterGen(m, team, type2);
	}
	
	public void putCharacter(Map m, int partyNum, int x, int y) {
		m.setCharacter(x, y, this.party[partyNum]);
	}
	
	public void createCharacterStatusWindow() {
		for(int i = 0; i < party.length; i++) {
			this.window[i] = new CharacterStatus(this.party[i]);
		}
	}
	
	public void updateCharacterStatusWindwo() {
		for(int i = 0; i < party.length; i++) {
			this.window[i].updateWindow(this.party[i]);
		}
	}

}