package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MatrixAdjacency implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<Integer>> mta;

	public MatrixAdjacency() {
		super();
		this.mta = new ArrayList<>();
	}

	public void createVerTex() {
		if (mta.size() == 0) {
			mta.add(new ArrayList<Integer>());
			mta.get(0).add(0);
			return;
		}
		for (int i = 0; i < mta.size(); i++) {
			mta.get(i).add(0);
		}
		ArrayList<Integer> dongmoi = new ArrayList<Integer>();
		for (int i = 0; i < mta.size() + 1; i++)
			dongmoi.add(0);
		mta.add(dongmoi);
	}

	public void removeVertex(int v) {
		if (v >= mta.size()) return;
		mta.get(v).remove(v);
		mta.remove(v);
	}

	public void createUnDirected(int v1, int v2) {

		Integer soCanhBanDau = mta.get(v1).get(v2);
		mta.get(v2).set(v1, soCanhBanDau + 1);
		mta.get(v1).set(v2, soCanhBanDau + 1);
	}

	public void createDirected(int v1, int v2) {
		int soCanhBanDau = mta.get(v1).get(v2);
		mta.get(v1).set(v2, soCanhBanDau + 1);
	}

	public void removeUnDirected(int v1, int v2) {

		Integer soCanhBanDau = mta.get(v1).get(v2);
		mta.get(v2).set(v1, soCanhBanDau - 1);
		mta.get(v1).set(v2, soCanhBanDau - 1);
	}

	public void removeDirected(int v1, int v2) {
		int soCanhBanDau = mta.get(v1).get(v2);
		mta.get(v1).set(v2, soCanhBanDau - 1);
	}

	public int soCanh() {
		int result = 0;
		for (int i = 0; i < mta.size(); i++) {
			for (int j = 0; j < mta.size(); j++) {
				if ((mta.get(i).get(j) > 0)) {
					int tru = mta.get(i).get(j) - mta.get(j).get(i);
					result += tru;
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < mta.size(); i++) {
			for (int j = 0; j < mta.size(); j++) {
				result += mta.get(i).get(j) + " ";
			}
			result += "\n";
		}
		return result;
	}

	public ArrayList<ArrayList<Integer>> getMta() {
		return mta;
	}

}
