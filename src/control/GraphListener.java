package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Temp;
import view.BoxAdjacencyMatrix;
import view.CenterPanel;

public class GraphListener implements ActionListener {
	CenterPanel centerPanel;
	BoxAdjacencyMatrix boxAdjacencyMatrix;
	Temp t3 = null;
	Temp temp2 = null;

	public GraphListener(CenterPanel centerPanel) {
		super();
		this.centerPanel = centerPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
		case "Adjacency Matrix":
			boxAdjacencyMatrix = new BoxAdjacencyMatrix(centerPanel);
			boxAdjacencyMatrix.setTxtArea(centerPanel.getMta().toString());
			break;
		case "Remove object":
			centerPanel.setAction(e.getActionCommand());
			centerPanel.again();
			break;
		case "Add vertex":
			centerPanel.setAction(e.getActionCommand());
			centerPanel.again();
			break;
		case "Connect vertices":
			centerPanel.resetListTemp();
			centerPanel.setAction(e.getActionCommand());
			centerPanel.again();
			break;
		case "Directed":
			centerPanel.addListConnect(true);
			break;
		case "Undirected":
			centerPanel.addListConnect(false);
			break;
		case "Default":
			centerPanel.setAction(e.getActionCommand());
			centerPanel.again();
			break;
		case "DFS":
			centerPanel.setAction(e.getActionCommand());
			centerPanel.again();
			break;
		case "BFS":
			centerPanel.setAction(e.getActionCommand());
			centerPanel.again();
			break;
		case "Dijkstra":
			centerPanel.resetListTemp();
			centerPanel.setAction(e.getActionCommand());
			centerPanel.again();
			break;
		case "Undo":
			centerPanel.setAction(e.getActionCommand());
			centerPanel.undo();
			centerPanel.again();
			break;
		case "Open graph":
			temp2 = SaveGraph.readGraph();
			if (temp2 != null) {
				centerPanel.resetGraph();
				centerPanel.drawTemp(temp2);
				centerPanel.indexV = temp2.getListV().size();
			}
			break;
		case "Save graph image":
			Temp temp = null;
			if (!centerPanel.getListV().isEmpty()) {
				temp = centerPanel.saveToTemp();
				SaveGraph saveGraph = new SaveGraph();
				saveGraph.saveGraphAs(temp);
				if (saveGraph.isSave == true) {
					centerPanel.resetGraph();
					centerPanel.repaint();
				}
			}
			break;
		default:
			break;
		}
	}

	public void resetGraph() {
		centerPanel.resetGraph();
	}
	
	
}
