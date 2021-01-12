package GUI;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Sys.Player;

public class PlayerStatus extends JFrame{

	Container contentPane;

	public PlayerStatus(Player p) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 100);
		this.contentPane = getContentPane();
		this.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		updateWindow(p);

	}

	public void updateWindow(Player p) {
		this.contentPane.removeAll();

		JLabel name = new JLabel("Player-" + p.getTeam());
		this.contentPane.add(name);

		String charName[] = new String[p.getParty().length];
		JLabel character[] = new JLabel[p.getParty().length];
		for(int i = 0; i < p.getParty().length; i++) {
			if(p.getParty()[i].isDead()) {
				charName[i] = "Character" + i + " : " + p.getParty()[i].getName() + " [DEAD]";
			}else {
				charName[i] = "Character" + i + " : " + p.getParty()[i].getName();
			}
			character[i] = new JLabel(charName[i]);
			this.contentPane.add(character[i]);
		}

	    this.setVisible(true);
	}

}
