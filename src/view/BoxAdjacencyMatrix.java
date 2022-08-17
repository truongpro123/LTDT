package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.GraphListener;

public class BoxAdjacencyMatrix extends JFrame {
	CenterPanel centerPanel;
	GraphListener graphListener;
	JTextArea TxtArea;

	public BoxAdjacencyMatrix(CenterPanel centerPanel) {
		setTitle("adjacency matrix");
		graphListener = new GraphListener(centerPanel);
		getContentPane().setLayout(new BorderLayout());

		doShow();

	}

	private void doShow() {
		add(new TopBox(), BorderLayout.NORTH);
		add(new BottomBox(), BorderLayout.SOUTH);
		add(new CenterBox(), BorderLayout.CENTER);

		setSize(500, 600);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	class TopBox extends JPanel {
		public TopBox() {
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setBackground(new Color(0, 162, 181));
			JLabel addEdge_lb = new JLabel("     Add edge");
			addEdge_lb.setFont(new Font("", Font.BOLD, 17));
			addEdge_lb.setForeground(Color.WHITE);
			add(addEdge_lb);
		}
	}

	class CenterBox extends JPanel {
		public CenterBox() {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			comment("Setup adjacency matrix. Use comma \",\" as separator");
			comment("Multigraph matrix contains weight of minimum edges between vertices");

			boxText();
		}

		public void boxText() {
			JPanel panel = new JPanel();
			panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(25, 40, 27, 40),
					BorderFactory.createLineBorder(Color.black)));
			;
			TxtArea = new JTextArea("", 20, 33);
			TxtArea.setLineWrap(true);

			TxtArea.setFont(new Font("", Font.BOLD, 14));
			panel.add(TxtArea);
			add(panel);
		}

		public void comment(String title) {
			JPanel panel = new JPanel(new FlowLayout());
			JLabel edgeWeight = new JLabel(title);
			edgeWeight.setFont(new Font("", Font.BOLD, 13));
			panel.add(edgeWeight);

			add(panel);
		}

	}

	class BottomBox extends JPanel {
		public BottomBox() {
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			JButton Directed = new JButton("Save");
			Directed.addActionListener(graphListener);

			add(Directed);

			JButton Undirected = new JButton("Cancel");
			Undirected.addActionListener(graphListener);
			add(Undirected);
		}
	}

	public void setTxtArea(String txt) {
		TxtArea.setText(txt);
	}

	public static void main(String[] args) {
		CenterPanel centerPanel = null;
		BoxAdjacencyMatrix b = new BoxAdjacencyMatrix(centerPanel);
		b.setTxtArea(null);
	}
}
