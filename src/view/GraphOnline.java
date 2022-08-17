package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class GraphOnline extends JFrame {
	private TopPanel topPanel;
	private CenterPanel centerPanel;

	public GraphOnline() {
		setSize(1300, 600);
		centerPanel = new CenterPanel();
		topPanel = new TopPanel(centerPanel);
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		lookAndFeel();
		setVisible(true);
	}
	private void lookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
	}
	public static void main(String[] args) {
		new GraphOnline();
	}
}
