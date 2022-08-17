package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.ArrayList;

public class Edge implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vertex firstVertex;
	private Vertex lastVertex;
	private Point pointFirstVertex;
	private Point pointLastVertex;
	private int weight;
	private boolean isDirected = true;
	// up
	private ArrayList<Vertex> adjacencyList;
	Color color = Color.GRAY;
	public Edge(Vertex firstVertex, Vertex lastVertex, int weight) {
		super();
		this.firstVertex = firstVertex;
		this.lastVertex = lastVertex;
		this.pointFirstVertex = firstVertex.getPoint();
		this.pointLastVertex = lastVertex.getPoint();
		this.weight = weight;
	}

	public void drawConnectVertices(Graphics g) {
		int[] xs = { pointFirstVertex.x, pointLastVertex.x };
		int[] ys = { pointFirstVertex.y, pointLastVertex.y };


		Point a = new Point(xs[0], ys[0]);
		Point b = new Point(xs[1], ys[1]);
		Line2D.Double line = new Line2D.Double(a, b);

		Graphics2D g2d = (Graphics2D) g.create();

		int[] xPoint = { pointLastVertex.x, (pointLastVertex.x - (pointFirstVertex.x - pointLastVertex.x)),
				pointFirstVertex.x };
		int[] yPoint = { pointLastVertex.y, pointFirstVertex.y, pointFirstVertex.y };
		Polygon polygon = new Polygon(xPoint, yPoint, 3);

		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(5));
		if (isDirected) {
			drawArrow(g2d, pointFirstVertex.x, pointFirstVertex.y, pointLastVertex.x, pointLastVertex.y);
		} else {
			g2d.draw(line);
		}
		g.setFont(new Font("", Font.BOLD, 20));
		g.setColor(Color.RED);
		g.drawString(weight+"", -(b.x - a.x) / 2 + b.x, -(b.y - a.y) / 2 + b.y);
	}

	void drawArrow(Graphics g1, double x1, double y1, double x2, double y2) {
		// link tham khao:
		// https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
		Graphics2D ga = (Graphics2D) g1.create();
		ga.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

		double l = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));// line length
		double d = l / 10; // arrowhead distance from end of line. you can use your own value.

		double newX = ((x2 + (((x1 - x2) / (l) * d)))); // new x of arrowhead position on the line with d distance from
														// end of the line.
		double newY = ((y2 + (((y1 - y2) / (l) * d)))); // new y of arrowhead position on the line with d distance from
														// end of the line.

		double dx = x2 - x1, dy = y2 - y1;
		double angle = (Math.atan2(dy, dx)); // get angle (Radians) between ours line and x vectors line. (counter
												// clockwise)
		angle = (-1) * Math.toDegrees(angle);// cconvert to degree and reverse it to round clock for better understand
												// what we need to do.
		if (angle < 0) {
			angle = 360 + angle; // convert negative degrees to posative degree
		}
		angle = (-1) * angle; // convert to counter clockwise mode
		angle = Math.toRadians(angle);// convert Degree to Radians
		AffineTransform at = new AffineTransform();
		at.translate(newX, newY);// transport cursor to draw arrowhead position.
		at.rotate(angle);
		ga.transform(at);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(5, 0);
		arrowHead.addPoint(-5, 5);
		arrowHead.addPoint(-2, -0);
		arrowHead.addPoint(-5, -5);
		ga.fill(arrowHead);
		ga.drawPolygon(arrowHead);
	}

	@Override
	public String toString() {
		return firstVertex + " " + lastVertex + " " + weight;
	}

	public boolean isDirected() {
		return isDirected;
	}

	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public Vertex getFirstVertex() {
		return firstVertex;
	}

	public void setFirstVertex(Vertex firstVertex) {
		this.firstVertex = firstVertex;
	}

	public Vertex getLastVertex() {
		return lastVertex;
	}

	public void setLastVertex(Vertex lastVertex) {
		this.lastVertex = lastVertex;
	}

	public Point getPointFirstVertex() {
		return pointFirstVertex;
	}

	public void setPointFirstVertex(Point pointFirstVertex) {
		this.pointFirstVertex = pointFirstVertex;
	}

	public Point getPointLastVertex() {
		return pointLastVertex;
	}

	public void setPointLastVertex(Point pointLastVertex) {
		this.pointLastVertex = pointLastVertex;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
