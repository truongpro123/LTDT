package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import control.GraphListener;

public class TopPanel extends JPanel {
	ImageIcon[] imageIconG = { createImageIcon("graph.png"), createImageIcon("plus.png"), createImageIcon("save.png"),
			createImageIcon("mattrix.png") };
	String graph[] = { "Graph", "Open graph", "Save graph image", "Adjacency Matrix" };
	Integer arrayG[] = { 1, 2, 3, 4 };

	ImageIcon[] imageIconV = { createImageIcon("zoom.png"), createImageIcon("zoom.png"), createImageIcon("zoom.png"),
			createImageIcon("zoom.png") };
	String view[] = { "100%", "50%", "zoom in", "zoom out" };
	Integer arrayV[] = { 1, 2, 3, 4 };

	ImageIcon[] imageIconA = { createImageIcon("zoom.png"), createImageIcon("zoom.png"), createImageIcon("zoom.png"),
			createImageIcon("zoom.png") };
	String Algorithms[] = { "DFS", "BFS", "Dijkstra", "" };
	Integer arrayA[] = { 1, 2, 3, 4 };

	ImageIcon[] imageIconS = { createImageIcon(""), createImageIcon(""), createImageIcon(""), createImageIcon("") };
	String Setting[] = { "Common verticles", "Selected verticles", "Common edges", "Background color" };
	Integer arrayS[] = { 1, 2, 3, 4 };

	CenterPanel centerPanel;
	GraphListener graphListener;

	public TopPanel(CenterPanel centerPanel) {
		this.centerPanel = centerPanel;

		graphListener = new GraphListener(centerPanel);
		setBackground(Color.BLUE);
		init();

	}

	private void init() {
		addMenuButton(graph, imageIconG, arrayG, graphListener);
		addMenuButton(view, imageIconV, arrayV, graphListener);

		addButton(graphListener, "Default", "default.png");
		addButton(graphListener, "Add vertex", "plus.png");
		addButton(graphListener, "Connect vertices", "plus.png");

		addMenuButton(Algorithms, imageIconA, arrayA, graphListener);

		addButton(graphListener, "Remove object", "remove.png");

		addMenuButton(Setting, imageIconS, arrayS, graphListener);

		addButton(graphListener, "Undo", "undo.png");
	}

	private void addMenuButton(String[] items, ImageIcon[] imageIcon, Integer[] arr, ActionListener ac) {
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		JComboBox<Integer> box = new JComboBox<Integer>(arr);

		Object selected = box.getSelectedItem();
		box.addActionListener(ac);

		class ComboBoxRenderar extends JLabel implements ListCellRenderer {

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				int offset = ((Integer) value).intValue() - 1;
				ImageIcon icon = imageIcon[offset];
				String name = items[offset];
				//
//		        System.out.println(box.getSelectedIndex());
				if (box.getSelectedIndex() == 3 && items[3].equals("Adjacency Matrix")) {
					box.setActionCommand(items[box.getSelectedIndex()]);
				} else {
					box.setActionCommand(items[box.getSelectedIndex()]);
				}
				setIcon(icon);
				setText(name);
				return this;
			}
		}

		ComboBoxRenderar rendrar = new ComboBoxRenderar();
		rendrar.setPreferredSize(new Dimension(100, 20));
		rendrar.setMinimumSize(new Dimension(100, 20));

		box.setMaximumRowCount(4);
		box.setRenderer(rendrar);
//		box.addActionListener(box);
		box.getAccessibleContext().getAccessibleEditableText();
//        box.setActionCommand(""+selected);
		add(box);

	}

	private void addButton(ActionListener action, String name, String urlImage) {
		JButton button;
		JPanel box = new JPanel(new GridLayout(1, 0));
		button = new JButton(name);
		box.add(button);

		URL url = GraphOnline.class.getResource(urlImage);
		Image image = Toolkit.getDefaultToolkit().createImage(url);
		button.setIcon(new ImageIcon(image));

		button.addActionListener(action);
		add(box);
	}

	private ImageIcon createImageIcon(String urlImage) {
		ImageIcon imageIcon = null;
		try {
			URL url = GraphOnline.class.getResource(urlImage);
			Image image = Toolkit.getDefaultToolkit().createImage(url);
			imageIcon = new ImageIcon(image);
		} catch (Exception e) {
			throw new RuntimeException("123");
		}
		return imageIcon;
	}

}
