package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import control.GraphListener;

public class BoxConnect extends JFrame {
	CenterPanel centerPanel;
	GraphListener graphListener;
	BoxConnect box = this;

	//
	JTextField edgeWeightValue;

	JRadioButton replaceRadio;
	JRadioButton addRadio;
	public BoxConnect(CenterPanel centerPanel) {
		setTitle("box");
		graphListener = new GraphListener(centerPanel);
		getContentPane().setLayout(new BorderLayout());

		doShow();
	}

	private void doShow() {
		add(new TopBox(), BorderLayout.NORTH);
		add(new BottomBox(), BorderLayout.SOUTH);
		add(new CenterBox(), BorderLayout.CENTER);
//		pack();
		setSize(400, 300);

//		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

//			JButton color = new JButton("color");
//			color.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					JColorChooser.showDialog(null, "Color", Color.black);
//				}
//			});
//			add(color);
		}
	}

	class CenterBox extends JPanel {
		public CenterBox() {
			setLayout(new FlowLayout());

			edgeWeight();

			jradio();

			above();

			JTextField aboveEdge_Txt = new JTextField("", 25);
			aboveEdge_Txt.setFont(new Font("", Font.BOLD, 14));
			add(aboveEdge_Txt);
		}

		private void jradio() {
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());

			replaceRadio = new JRadioButton("replace current", true);
			replaceRadio.setFont(new Font("", Font.BOLD, 14));
			add(replaceRadio);

			addRadio = new JRadioButton("add (multigraph)");
			addRadio.setFont(new Font("", Font.BOLD, 14));
			panel.add(addRadio);
			add(panel);
			ButtonGroup group = new ButtonGroup();
			group.add(replaceRadio);
			group.add(addRadio);
			
		}

		private void edgeWeight() {
			JPanel panel = new JPanel(new FlowLayout());
			JLabel edgeWeight = new JLabel("  Edge weight");
			edgeWeight.setFont(new Font("", Font.BOLD, 17));
			add(edgeWeight);

			edgeWeightValue = new JTextField("5", 13);
			edgeWeightValue.setFont(new Font("", Font.BOLD, 14));
			panel.add(edgeWeightValue);
			add(panel);
		}

		private void above() {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel aboveEdge_Lb = new JLabel("Text above edge ", JLabel.LEFT);
			aboveEdge_Lb.setFont(new Font("", Font.BOLD, 17));
			aboveEdge_Lb.setVerticalAlignment(JLabel.BOTTOM);
//			aboveEdge_Lb.setLocation(10, 200);
			panel.add(aboveEdge_Lb);
			add(panel);
		}
	}

	class BottomBox extends JPanel {
		public BottomBox() {
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			JButton Directed = new JButton("Directed");
			Directed.addActionListener(graphListener);

			add(Directed);

			JButton Undirected = new JButton("Undirected");
			Undirected.addActionListener(graphListener);
			add(Undirected);
		}
	}

	public static void main(String[] args) {
		CenterPanel centerPanel = null;

		new BoxConnect(centerPanel);
	}

	public JRadioButton getReplaceRadio() {
		return replaceRadio;
	}

	public void setReplaceRadio(JRadioButton replaceRadio) {
		this.replaceRadio = replaceRadio;
	}

	public JRadioButton getAddRadio() {
		return addRadio;
	}

	public void setAddRadio(JRadioButton addRadio) {
		this.addRadio = addRadio;
	}

	public JTextField getEdgeWeightValue() {
		return edgeWeightValue;
	}
}
