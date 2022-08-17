package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vertex implements Serializable, Comparable<Vertex>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// model
	private int number;
	private boolean visited;
	private double dist = Double.MAX_VALUE;
	private Vertex pr;
	public  List<Edge> listEdgeNeighbor;

	// draw
	private Point point;
	double radius = 35;
	Ellipse2D.Double e;
	private Color color = new Color(20, 95, 230);

	public ArrayList<Vertex> listAdjacency = new ArrayList<>();

	public Vertex(int x, int y, int number) {
		super();
		point = new Point(x, y);
		this.listEdgeNeighbor = new ArrayList<>();
		this.setNumber(number);
	}

	public void drawPoint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		e = new Ellipse2D.Double(point.x - (radius / 2), point.y - (radius / 2), radius, radius);
		g2d.setColor(color);
		g2d.fill(e);

		String numS = getNumber() + "";

		g.setFont(new Font("", Font.BOLD, 20));
		g.setColor(Color.YELLOW);
		if (getNumber() >= 100) {
			g.drawString(numS, point.x - 13, point.y + 8);
		} else if (getNumber() >= 10) {
			g.drawString(numS, point.x - 9, point.y + 8);
		} else {
			g.drawString(numS, point.x - 5, point.y + 8);
		}

	}

	public boolean isContainPoint(int x, int y) {
		return e.contains(new Point(x, y));
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getX() {
		return (int) point.getX();
	}

	public int getY() {
		return (int) point.getY();
	}

	@Override
	public String toString() {
		return getNumber() + "";
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void addListAdjacency(Vertex lV) {
		if (listAdjacency.contains(lV))
			return;
		listAdjacency.add(lV);
	}

	public ArrayList<Vertex> getListAdjacency() {
		return listAdjacency;
	}

	public void addNeighbour(Edge edge) {
		this.listEdgeNeighbor.add(edge);
	}
	public void removeNeighbour(Edge edge) {
		this.listEdgeNeighbor.remove(edge);
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public Vertex getPr() {
		return pr;
	}

	public void setPr(Vertex pr) {
		this.pr = pr;
	}

	public List<Edge> getListEdgeNeighbor() {
		return listEdgeNeighbor;
	}

	public void setListEdgeNeibor(List<Edge> list) {
		listEdgeNeighbor = list;
	}

	@Override
	public int compareTo(Vertex otherV) {
		return Double.compare(this.dist, otherV.getDist());
	}
}
