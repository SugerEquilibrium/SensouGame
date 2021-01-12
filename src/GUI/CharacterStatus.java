package GUI;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Character.Character;

public class CharacterStatus extends JFrame{

	Container contentPane;

	public CharacterStatus(Character c) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 200);
		this.contentPane = getContentPane();
		this.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		updateWindow(c);
		
	    this.setVisible(true);
	}
	
	public void updateWindow(Character c) {
		this.contentPane.removeAll();
		JLabel name = new JLabel("NAME : " + c.getName());
		JLabel ID = new JLabel("ID : " + c.getID());
		JLabel team = new JLabel("team : " + c.getTeam());
		JLabel HP = new JLabel("HP : " + c.getHP() + "/5");
		JLabel dead = new JLabel("dead : " + c.isDead());
		JLabel item0 = new JLabel("item0 : " + c.getItem()[0].getName());
		JLabel item1 = new JLabel("item1 : " + c.getItem()[1].getName());
		JLabel item2 = new JLabel("item2 : " + c.getItem()[2].getName());
		
		this.contentPane.add(name);
		this.contentPane.add(ID);
		this.contentPane.add(team);
		this.contentPane.add(HP);
		this.contentPane.add(dead);
		this.contentPane.add(item0);
		this.contentPane.add(item1);
		this.contentPane.add(item2);
		
	    this.setVisible(true);
	}
}