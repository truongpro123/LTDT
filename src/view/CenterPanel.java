package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import model.Edge;
import model.MatrixAdjacency;
import model.PathFinder;
import model.Temp;
import model.Vertex;

public class CenterPanel extends JPanel {
	private static final long serialVersionUID = -942621026371785463L;

	private String action = "";
	private Graphics g;
	private List<Vertex> listV;
	private List<Point> listP;
	private List<Edge> listEdge;
	Vertex vertex;
	CenterPanel centerPanel = this;
	public BoxConnect box;

	// undo
	Stack<String> stackAction = new Stack<>();
	List<Vertex> listVertexUndo = new ArrayList<>();
	List<Edge> listEdgeUndo = new ArrayList<>();
	Stack<Vertex> listRemoveUndo = new Stack<>();

	int sizeLiVertexOld;
	int sizeLiEdgeOld;
	public boolean isUndo = false;
	//
	public int indexV = 0;
	// remove
	Vertex vertexMove;
	// up
	private List<Vertex> list2VTemp = new ArrayList<>();
	// BFS
	ArrayList<Edge> listEdgeBFS = new ArrayList<>();
	private static MatrixAdjacency mta = new MatrixAdjacency();

	public CenterPanel() {
		// size(width:1280, height:5200)
		listV = new ArrayList<Vertex>();
		listP = new ArrayList<Point>();

		listEdge = new ArrayList<Edge>();

		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				switch (action) {
				case "Default":
					for (int i = 0; i < listEdge.size(); i++) {
						Edge edge = listEdge.get(i);
						Edge newEdge = new Edge(edge.getFirstVertex(), edge.getLastVertex(), 0);

						if (edge.getFirstVertex().equals(vertexMove) || edge.getLastVertex().equals(vertexMove)) {
							if (edge.getFirstVertex().equals(vertexMove)) {
								newEdge = new Edge(vertexMove, edge.getLastVertex(), (int) edge.getWeight());
							} else {
								newEdge = new Edge(edge.getFirstVertex(), vertexMove, (int) edge.getWeight());
							}
							newEdge.setDirected(edge.isDirected());
							listEdge.set(i, newEdge);
						}
					}
					vertexMove.setPoint(new Point(e.getX(), e.getY()));
					repaint();
					break;
				default:
					break;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				g = getGraphics();
				switch (action) {
				case "Add vertex":
//					indexV = listV.get(listV.size()).getNumber() + 1 ;
					for (int i = 0; i < listV.size() + 1; i++) {
						boolean isContainVertex = false;
						for (Vertex vertex : listV) {
							if (vertex.getNumber() == i) {
								isContainVertex = true;
							}
						}
						if (isContainVertex == false) {
							indexV = i;
							break;
						}
					}

					if (isUndo) {
						listV.removeAll(listVertexUndo);
						isUndo = false;
					}
					vertex = new Vertex(e.getX(), e.getY(), indexV);

					listV.add(vertex);
					getMta().createVerTex();
					stackAction.add(action);
					sizeLiVertexOld++;
					break;

				case "Connect vertices":
					if (isUndo) {
						listEdge.removeAll(listEdgeUndo);
						isUndo = false;
					}
					for (Vertex vertex : listV) {
						if (vertex.isContainPoint(e.getX(), e.getY())) {
							listP.add(vertex.getPoint());
							list2VTemp.add(vertex);
							if (vertex.equals(list2VTemp.get(0))) {
								vertex.setColor(Color.red);
								break;
							}
						}
					}
					if (listP.size() >= 2) {
						if (true) {
							box = new BoxConnect(centerPanel);
						}
						box.setVisible(true);
					}
					break;

				case "Remove object":
					for (Vertex vertex : listV) {
						if (vertex.isContainPoint(e.getX(), e.getY())) {
							listRemoveUndo.add(vertex);
							List<Edge> listRemoveEdges = new ArrayList<>();
							for (int i = 0; i < listEdge.size(); i++) {
								Edge edge = listEdge.get(i);
								if (edge.getFirstVertex().equals(vertex) || edge.getLastVertex().equals(vertex)) {
									listRemoveEdges.add(edge);
									sizeLiEdgeOld--;
									if (!edge.isDirected()) {
										edge.getFirstVertex().removeNeighbour(edge);
									} else {
										edge.getFirstVertex().removeNeighbour(edge);
										edge.getLastVertex().removeNeighbour(edge);
									}
								}
							}

							stackAction.add(action);

							listEdge.removeAll(listRemoveEdges);
							listV.remove(vertex);
							mta.removeVertex(vertex.getNumber());
							for (Vertex vertexOthers : listV) {
								if (vertexOthers.getListAdjacency().contains(vertex)) {
									vertexOthers.getListAdjacency().remove(vertex);
								}
							}

							sizeLiVertexOld--;
							break;
						}
					}
					break;
				case "Default":
					for (Vertex vertex : listV) {
						if (vertex.isContainPoint(e.getX(), e.getY())) {
							vertexMove = vertex;
							vertexMove.setColor(Color.GREEN);
							System.out.println(vertexMove.getListAdjacency());
							break;
						}
					}
					break;
				case "DFS":
					for (Vertex vertex : listV) {
						if (vertex.isContainPoint(e.getX(), e.getY())) {
							vertexMove = vertex;
							runMethod(DFS(vertex.getNumber()));
							again();
							break;
						}
					}
					break;
				case "BFS":
					for (Vertex vertex : listV) {
						if (vertex.isContainPoint(e.getX(), e.getY())) {
							vertexMove = vertex;
							runMethod(BFS(vertex.getNumber()));
							again();
							break;
						}
					}
					break;
				case "Dijkstra":
					for (Vertex vertex : listV) {
						if (vertex.isContainPoint(e.getX(), e.getY())) {
							list2VTemp.add(vertex);
							if (vertex.equals(list2VTemp.get(0))) {
								again();
								vertex.setColor(Color.red);
								break;
							}
						}
					}
					if (list2VTemp.size() >= 2) {
						if (true) {
							runMethod(Dijsktra(list2VTemp.get(0), list2VTemp.get(1)));
							list2VTemp.clear();
						}
					}
					break;
				default:
					break;
				}
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				switch (action) {
				case "Default":
					vertexMove.setColor(new Color(20, 95, 230));
					repaint();
					break;
				default:
					break;
				}
			}

		};
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		for (int i = 0; i < sizeLiEdgeOld; i++) {
			Edge edge = listEdge.get(i);
			edge.drawConnectVertices(g);
		}

		for (int i = 0; i < sizeLiVertexOld; i++) {
			Vertex vertex = listV.get(i);
			vertex.drawPoint(g);
		}
	}

//undo
	public void undoVertex() {
		isUndo = true;
		indexV--;
		listVertexUndo.add(listV.get(--sizeLiVertexOld));
		repaint();
	}

	public void undoEdge() {
		isUndo = true;
		listEdgeUndo.add(listEdge.get(--sizeLiEdgeOld));
		repaint();
	}

	public void undoRemove() {
		isUndo = true;
		listV.add(listRemoveUndo.pop());
		sizeLiVertexOld++;

		repaint();
	}

	public void undoLocation() {
		isUndo = true;
		listV.add(listRemoveUndo.pop());
		sizeLiVertexOld++;

		repaint();
	}

	public void undo() {

		if (stackAction.size() > 0)
			switch (stackAction.pop()) {
			case "Add vertex":
				undoVertex();
				break;
			case "Connect vertices":
				undoEdge();
				break;
			case "Default":
				undoLocation();
				break;
			case "Remove object":
				undoRemove();
				break;
			default:
				break;
			}

		System.out.println(stackAction);
		repaint();
	}

//
	public void setAction(String ac) {
		action = ac;
	}

	// tat ca dinh tra ve mau ban dau
	public void again() {
		for (Vertex v : listV) {
			v.setVisited(false);
			v.setDist(Double.MAX_VALUE);
			v.setColor(new Color(20, 95, 230));
			v.setPr(null);
		}
		for (Edge edge : listEdge) {
			edge.setColor(Color.GRAY);
		}
		repaint();
	}

	public void addListConnect(boolean isDirected) {
		int distance = Integer.parseInt(box.getEdgeWeightValue().getText());
		Edge newEdge = new Edge(list2VTemp.get(0), list2VTemp.get(listP.size() - 1), distance);
		newEdge.setDirected(isDirected);
		if (box.getReplaceRadio().isSelected()) {
			for (Edge oldEdge : listEdge) {
				if (((oldEdge.getFirstVertex().equals(newEdge.getFirstVertex())
						|| oldEdge.getFirstVertex().equals(newEdge.getLastVertex()))
						&& (oldEdge.getLastVertex().equals(newEdge.getLastVertex())
								|| oldEdge.getLastVertex().equals(newEdge.getFirstVertex())))
						&& !(oldEdge.equals(newEdge))) {
					for (Vertex v : listV) {
						v.removeNeighbour(oldEdge);
					}

					again();
					listEdge.remove(oldEdge);
					if (isDirected) {
						mta.removeDirected(list2VTemp.get(0).getNumber(),
								list2VTemp.get(list2VTemp.size() - 1).getNumber());
					} else {
						mta.removeUnDirected(list2VTemp.get(0).getNumber(),
								list2VTemp.get(list2VTemp.size() - 1).getNumber());
					}
					sizeLiEdgeOld--;
					System.out.println("replaceEdge");
					break;
				}
			}
		}

		listEdge.add(newEdge);
		if (isDirected == true) {
			mta.createDirected(list2VTemp.get(0).getNumber(), list2VTemp.get(list2VTemp.size() - 1).getNumber());
			list2VTemp.get(0).addListAdjacency(list2VTemp.get(list2VTemp.size() - 1));
			list2VTemp.get(0).addNeighbour(newEdge);

		} else {
			mta.createUnDirected(list2VTemp.get(0).getNumber(), list2VTemp.get(list2VTemp.size() - 1).getNumber());
			list2VTemp.get(0).addListAdjacency(list2VTemp.get(list2VTemp.size() - 1));
			list2VTemp.get(list2VTemp.size() - 1).addListAdjacency(list2VTemp.get(0));
			list2VTemp.get(0).addNeighbour(newEdge);
			list2VTemp.get(1).addNeighbour(newEdge);
		}
		sizeLiEdgeOld++;
		stackAction.add(action);

		list2VTemp.get(0).setColor(new Color(20, 95, 230));
		repaint();
		box.setVisible(false);
		list2VTemp.clear();
		listP.clear();
	}

	public MatrixAdjacency getMta() {
		return mta;
	}

	public String getAction() {
		return action;
	}

	// DFS
	public static ArrayList<Integer> danhSachKeCua1Dinh(int i) {
		ArrayList<ArrayList<Integer>> mtk = mta.getMta();
		ArrayList<Integer> result = new ArrayList<>();
		for (int j = 0; j < mtk.size(); j++) {
			if (mtk.get(i).get(j) > 0) {
				if (!result.contains(j)) {
					result.add(j);
				}
			}
		}
		return result;
	}

	public static ArrayList<ArrayList<Integer>> danhSachKeCuaDoThi() {
		ArrayList<ArrayList<Integer>> mtk = mta.getMta();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < mtk.size(); i++) {
			result.add(new ArrayList<Integer>());
			result.get(i).addAll(danhSachKeCua1Dinh(i));
		}
		return result;
	}

	public static ArrayList<Integer> DFS(int startPoint) {
		ArrayList<Integer> result = new ArrayList<>();
		ArrayList<Boolean> chuaXet = new ArrayList<Boolean>();
		ArrayList<ArrayList<Integer>> mtk = mta.getMta();

		for (int i = 0; i < mtk.size(); i++)
			chuaXet.add(true);
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<ArrayList<Integer>> ke = danhSachKeCuaDoThi();
		stack.push(startPoint);
		while (!stack.isEmpty()) {
			int dinhHienTai = stack.pop();
			if (chuaXet.get(dinhHienTai) == true) {
				result.add(dinhHienTai);
			}
			chuaXet.set(dinhHienTai, false);

			for (int i = ke.get(dinhHienTai).size() - 1; i >= 0; i--) {
				Integer cacDinhKeCuaDinhHienTai = ke.get(dinhHienTai).get(i);
				if (mtk.get(dinhHienTai).get(cacDinhKeCuaDinhHienTai) > 0) {
					if (chuaXet.get(cacDinhKeCuaDinhHienTai) == true) {
						stack.push(cacDinhKeCuaDinhHienTai);
					}
				}
			}
		}
		return result;
	}

	public void runMethod(ArrayList<Integer> result) {
		Timer t = new Timer();
		List<Edge> EdgesChangeColor = new ArrayList<Edge>();
		if (!action.equals("BFS")) {
			edgesChangeColor(result, EdgesChangeColor);
		} else {
			EdgesChangeColor.addAll(listEdgeBFS);
		}
		System.out.println(result);
		TimerTask task = new TimerTask() {
			int i = 0;

			public void run() {
				for (Vertex v : listV) {
					if (v.getNumber() == result.get(i)) {
						v.setColor(Color.GREEN);
						v.setVisited(true);
					}
				}
				if (i > 0 && i <= EdgesChangeColor.size())
					for (Edge edge : listEdge) {
						if (edge.equals(EdgesChangeColor.get(i - 1))) {
							edge.setColor(Color.MAGENTA);
						}

					}
				repaint();
				if (i == result.size() - 1) {
					t.cancel();
					coutinueBrowsing();
				}
				i++;
			}

		};
		t.schedule(task, 750, 750);
		listEdgeBFS.clear();

	}

	public void coutinueBrowsing() {
		for (Vertex vertex : listV) {
			if (!vertex.isVisited()) {
				if (action.equals("DFS")) {
					runMethod(DFS(vertex.getNumber()));
				} else if (action.equals("BFS")) {
					runMethod(BFS(vertex.getNumber()));
				}
				break;
			}
		}
	}

	private void edgesChangeColor(ArrayList<Integer> result, List<Edge> EdgesChangeColor) {
		ArrayList<Vertex> resultV = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) {
			resultV.add(getVertex(result.get(i)));
		}
		for (int i = 1; i < resultV.size(); i++) {
			for (Edge edge : listEdge) {
				int vertexParent = i;
				for (int j = vertexParent; j > 0; j--) {
					if (resultV.get(vertexParent) != null)
						if (resultV.get(vertexParent).getListAdjacency().contains(resultV.get(i))) {
							System.out.println(resultV.get(vertexParent).getListAdjacency());
							vertexParent = j;
							break;
						} else {
							vertexParent--;
						}
				}
				if ((edge.getFirstVertex().getNumber() == result.get(vertexParent)
						&& edge.getLastVertex().getNumber() == result.get(i))
						|| (edge.getLastVertex().getNumber() == result.get(vertexParent)
								&& edge.getFirstVertex().getNumber() == result.get(i))) {
					EdgesChangeColor.add(edge);
				}

			}
		}
	}

	private Vertex getVertex(int vertexNumber) {
		for (Vertex vertex : listV) {
			if ((vertex.getNumber() == vertexNumber)) {
				return vertex;
			}
		}
		return null;
	}

	// BFS
	public ArrayList<Integer> BFS(int startVertex) {
		ArrayList<ArrayList<Integer>> mtk = mta.getMta();
		ArrayList<Integer> result = new ArrayList<>();
		ArrayList<Boolean> chuaXet = new ArrayList<Boolean>();
		for (int i = 0; i < mtk.size(); i++)
			chuaXet.add(true);
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<ArrayList<Integer>> ke = danhSachKeCuaDoThi();
		queue.add(startVertex);
		chuaXet.set(startVertex, false);
		while (!queue.isEmpty()) {
			int dinhHienTai = queue.poll();
			result.add(dinhHienTai);
			for (int i = 0; i < ke.get(dinhHienTai).size(); i++) {
				Integer cacDinhKeCuaDinhHienTai = ke.get(dinhHienTai).get(i);
				if (mtk.get(dinhHienTai).get(cacDinhKeCuaDinhHienTai) > 0) {
					if (chuaXet.get(cacDinhKeCuaDinhHienTai) == true) {
						queue.add(cacDinhKeCuaDinhHienTai);
						for (Edge edge : listEdge) {
							if ((edge.getFirstVertex().getNumber() == dinhHienTai
									&& edge.getLastVertex().getNumber() == cacDinhKeCuaDinhHienTai)
									|| (edge.getFirstVertex().getNumber() == cacDinhKeCuaDinhHienTai
											&& edge.getLastVertex().getNumber() == dinhHienTai)) {
								listEdgeBFS.add(edge);
							}
						}
						chuaXet.set(cacDinhKeCuaDinhHienTai, false);
					}
				}
			}
		}
		return result;
	}

	// Dijsktra
	public static ArrayList<Integer> Dijsktra(Vertex sourceV, Vertex targetVertex) {
		PathFinder p = new PathFinder();
		p.ShortestP(sourceV);
		ArrayList<Vertex> resultVertex = p.getShortestP(targetVertex);
		ArrayList<Integer> resultInteger = new ArrayList<>();
		for (int i = 0; i < resultVertex.size(); i++) {
			resultInteger.add(resultVertex.get(i).getNumber());
		}
		System.out.println(resultVertex + " " + targetVertex.getDist());
		if (resultInteger.size() == 1)
			System.out.println("Khong co duong di");
		System.out.println(p.getPath());
		return resultInteger;
	}

	public Temp saveToTemp() {
		Temp temp = new Temp();
		temp.setListV(listV);
		temp.setListEdge(listEdge);
		temp.setSizeLiEdgeOld(sizeLiEdgeOld);
		temp.setSizeLiVertexOld(sizeLiVertexOld);
		temp.setMta(mta);
		return temp;
	}

	public void resetGraph() {
		listV = new ArrayList<>();
		listEdge = new ArrayList<>();
		sizeLiEdgeOld = 0;
		sizeLiVertexOld = 0;
		indexV = 0;
	}

	public boolean te = true;

	public void drawTemp(Temp temp) {
		setListV(temp.getListV());
		setListEdge(temp.getListEdge());
		sizeLiEdgeOld = temp.getSizeLiEdgeOld();
		sizeLiVertexOld = temp.getSizeLiVertexOld();
		mta = temp.getMta();
		repaint();
	}

	public List<Vertex> getListV() {
		return listV;
	}

	public void setListV(List<Vertex> listV) {
		this.listV = listV;
	}

	public List<Edge> getListEdge() {
		return listEdge;
	}

	public void setListEdge(List<Edge> listEdge) {
		this.listEdge = listEdge;
	}

	public int getSizeLiVertexOld() {
		return sizeLiVertexOld;
	}

	public void setSizeLiVertexOld(int sizeLiVertexOld) {
		this.sizeLiVertexOld = sizeLiVertexOld;
	}

	public int getSizeLiEdgeOld() {
		return sizeLiEdgeOld;
	}

	public void setSizeLiEdgeOld(int sizeLiEdgeOld) {
		this.sizeLiEdgeOld = sizeLiEdgeOld;
	}

	public void resetListTemp() {
		listP.clear();
		list2VTemp.clear();
	}

}