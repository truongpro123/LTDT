package model;

import java.util.ArrayList;

public class MatrixDistance {
	ArrayList<ArrayList<Integer>> mta;

	public MatrixDistance() {
		super();
		this.mta = new ArrayList<>();
	}

	public static void themTrongSo(ArrayList<ArrayList<Integer>> mtts) {
		if (mtts.size() == 0) {
			mtts.add(new ArrayList<Integer>());
			mtts.get(0).add(-1);
			return;
		}
		for (int i = 0; i < mtts.size(); i++) {
			mtts.get(i).add(-1);
		}
		ArrayList<Integer> dongmoi = new ArrayList<Integer>();
		for (int i = 0; i < mtts.size() + 1; i++)
			dongmoi.add(-1);
		mtts.add(dongmoi);
	}

	public static void thayDoiTrongSoCoHuong(ArrayList<ArrayList<Integer>> mtts, int diemDau1, int diemDau2,
			int trongSo) {
		mtts.get(diemDau1).set(diemDau2, trongSo);
	}

	public static void thayDoiTrongSoVoHuong(ArrayList<ArrayList<Integer>> mtts, int diemDau1, int diemDau2,
			int trongSo) {
		mtts.get(diemDau1).set(diemDau2, trongSo);
		mtts.get(diemDau2).set(diemDau1, trongSo);

	}

	public String toString() {
		String result = "  ";
		for (int d = 0; d < mta.size(); d++)
			result += d + " ";
		result += "\n" + "______________" + "\n";
		for (int i = 0; i < mta.size(); i++) {
			result += i + "|";
			for (int j = 0; j < mta.size(); j++) {
				result += mta.get(i).get(j) + " ";
			}
			result += "\n";
		}
		return result;
	}

	public static void inMtts(ArrayList<ArrayList<Integer>> mtts) {
		System.out.print("  ");
		for (int d = 0; d < mtts.size(); d++)
			System.out.print(d + "\t");
		System.out.println();
		System.out.println("____________________________________________________");
		for (int i = 0; i < mtts.size(); i++) {
			System.out.print(i + "|");
			for (int j = 0; j < mtts.size(); j++) {
//				System.out.print(j+" ");
				System.out.print(mtts.get(i).get(j) + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		MatrixDistance m = new MatrixDistance();
		System.out.println(m);

	}
}
