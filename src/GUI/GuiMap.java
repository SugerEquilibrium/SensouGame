package GUI;

import java.awt.Container;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Sys.Map;
import Sys.Util;

public class GuiMap extends JFrame{
	Container contentPane;
	StringBuilder sb;
	
	public GuiMap(Map m) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 500);
		this.contentPane = getContentPane();
		this.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		this.updateWindow(m);
	}

	public void updateWindow(Map m) {
		
		sb = new StringBuilder("<html>");
		
		//表示名を決定
		//displayNameの文字列を変更することで簡単に表示名のフォーマットを変更できる
		String displayNameC[][] = new String[m.getXsize()][m.getYsize()];
		String displayNameI[][][] = new String[m.getXsize()][m.getYsize()][100];
		String displayNameT[][] = new String[m.getXsize()][m.getYsize()];
		String displayNameL[][] = new String[m.getXsize()][m.getYsize()];
		for(int x = 0; x < m.getXsize(); x++) {
			for(int y = 0; y < m.getYsize(); y++) {
				if(m.getCharacterLayer()[x][y].getTeam() == 0) {
					displayNameC[x][y] = "[" + m.getCharacterLayer()[x][y].getID() + "] " + m.getCharacterLayer()[x][y].getName();
				}else {
					displayNameC[x][y] =  "[" + m.getCharacterLayer()[x][y].getID() + "] " + m.getCharacterLayer()[x][y].getName() + " (" + m.getCharacterLayer()[x][y].getTeam() + ")";
				}
				displayNameT[x][y] = "[" + m.getTrapLayer()[x][y].getID() + "] " + m.getTrapLayer()[x][y].getName();
				displayNameL[x][y] = "[" + m.getLandLayer()[x][y].getID() + "] " + m.getLandLayer()[x][y].getName();
				for(int stack = 0; stack < 100; stack++) {
					displayNameI[x][y][stack] = "[" + m.getItemLayer()[x][y][stack].getID() + "] " + m.getItemLayer()[x][y][stack].getName();
				}
			}
		}

		//列の横幅の最大を決定
		int widthMax[] = new int[m.getXsize()];
		for(int x = 0; x < m.getXsize(); x++) {
			widthMax[x] = 0;
			for(int y = 0; y < m.getYsize(); y++) {
				if(widthMax[x] < displayNameC[x][y].length()) {
					widthMax[x] = displayNameC[x][y].length();
				}
				if(widthMax[x] < displayNameT[x][y].length()) {
					widthMax[x] = displayNameT[x][y].length();
				}
				if(widthMax[x] < displayNameL[x][y].length()) {
					widthMax[x] = displayNameL[x][y].length();
				}
				for(int stack = 0; stack < Util.countObjArr(m.getItemLayer()[x][y]) + 1; stack++) {
					if(widthMax[x] < displayNameI[x][y][stack].length()) {
						widthMax[x] = displayNameI[x][y][stack].length();
					}
				}
			}
		}

		//行の高さの最大を決定
		int heightMax[] = new int[m.getYsize()];
		for(int y = 0; y < m.getYsize(); y++) {
			heightMax[y] = 1;
			for(int x = 0; x < m.getXsize(); x++) {
				if(heightMax[y] < Util.countObjArr(m.getItemLayer()[x][y])) {
					heightMax[y] = Util.countObjArr(m.getItemLayer()[x][y]);
				}
			}
		}

		//一番上の線を描画
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		for(int i = 0; i < m.getXsize(); i++) {
			sb.append("+");
			for(int j = 0; j < widthMax[i] + 8; j++) {
				sb.append("-");
			}
		}
		sb.append("+<br/>");

		//マップ内容描画
		for(int y = 0; y < m.getYsize(); y++) {
			//キャラクターレイヤー描画
			for(int x = 0; x < m.getXsize(); x++) {
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append(displayNameC[x][y]);
				for(int i = 0; i < widthMax[x] - displayNameC[x][y].length(); i++) {
					sb.append("&nbsp;");
				}
			}
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;|<br/>");
			//アイテムレイヤー描画
			for(int stack = heightMax[y]; stack > 0; stack--) {
				for(int x = 0; x < m.getXsize(); x++) {
					sb.append("&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;");
					sb.append(displayNameI[x][y][stack -1]);
					for(int i = 0; i < widthMax[x] - displayNameI[x][y][stack -1].length(); i++) {
						sb.append("&nbsp;");
					}
				}
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;|<br/>");
			}
			//トラップレイヤー描画
			for(int x = 0; x < m.getXsize(); x++) {
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append(displayNameT[x][y]);
				for(int i = 0; i < widthMax[x] - displayNameT[x][y].length(); i++) {
					sb.append("&nbsp;");
				}
			}
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;|<br/>");
			//地形レイヤー描画
			for(int x = 0; x < m.getXsize(); x++) {
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append(displayNameL[x][y]);
				for(int i = 0; i < widthMax[x] - displayNameL[x][y].length(); i++) {
					sb.append("&nbsp;");
				}
			}
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;|<br/>");

			//横棒描画
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			for(int i = 0; i < m.getXsize(); i++) {
				sb.append("+");
				for(int j = 0; j < widthMax[i] + 8; j++) {
					sb.append("-");
				}
			}
			sb.append("+<br/>");
		}
		sb.append("</html>");
		
		JLabel map = new JLabel(sb.toString());
		map.setFont(new Font("Ricty Diminished", Font.PLAIN, 14));
		this.contentPane.add(map);
		
		this.setVisible(true);
	}
}
