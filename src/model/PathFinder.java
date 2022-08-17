package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class PathFinder {
	ArrayList<Vertex> path;
	Vertex v = null;
	public void ShortestP(Vertex sourceV) {
		sourceV.setDist(0);
		PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceV);
		sourceV.setVisited(true);
		while (!priorityQueue.isEmpty()) {
			Vertex actualVertex = priorityQueue.poll();

			for (Edge edge : actualVertex.getListEdgeNeighbor()) {
				if (actualVertex.getNumber() != edge.getFirstVertex().getNumber()) {
					v = edge.getFirstVertex();
				}else {
					v = edge.getLastVertex();

				}
				if (!v.isVisited()) {
					double newDistance = actualVertex.getDist() + edge.getWeight();
					if (newDistance < v.getDist()) {
						priorityQueue.remove(v);
						v.setDist(newDistance);
						System.out.println(v + ": " + v.getDist());
						v.setPr(actualVertex);
						priorityQueue.add(v);

					}
				}
			}
			actualVertex.setVisited(true);
		}
	}

	public ArrayList<Vertex> getShortestP(Vertex targetVertex) {
		
		path = new ArrayList<>();
		for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPr()) {
			path.add(vertex);
		}
		Collections.reverse(path);
		return path;
	}

	public ArrayList<Vertex> getPath() {
		return path;
	}

	public void setPath(ArrayList<Vertex> path) {
		this.path = path;
	}

	public Vertex getV() {
		return v;
	}

	public void setV(Vertex v) {
		this.v = v;
	}

}