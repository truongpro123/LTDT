package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Temp implements Serializable{
	private static final long serialVersionUID = 1L;

	int sizeLiVertexOld;
	int sizeLiEdgeOld;
	private List<Vertex> listV;
	private List<Edge> listEdge;
	
	private MatrixAdjacency mta;

	
	public Temp() {
		super();
		this.sizeLiVertexOld = 0;
		this.sizeLiEdgeOld = 0;
		this.listV = new ArrayList<Vertex>();
		this.listEdge = new ArrayList<Edge>();
		mta = new MatrixAdjacency();
	}
	@Override
	public String toString() {
		return "Temp [sizeLiVertexOld=" + sizeLiVertexOld + ", sizeLiEdgeOld=" + sizeLiEdgeOld + ", listV=" + listV
				+ ", listEdge=" + listEdge + "]";
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
	public MatrixAdjacency getMta() {
		return mta;
	}
	public void setMta(MatrixAdjacency mta) {
		this.mta = mta;
	}
	
	
	
}
