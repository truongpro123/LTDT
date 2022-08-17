package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class SaveGraph implements Serializable {
	private static final long serialVersionUID = 2097130126996449315L;
	private ArrayList<ArrayList<Integer>> mta;
	public SaveGraph(ArrayList<ArrayList<Integer>> mta) {
		super();
		this.mta = mta;
	}
	public ArrayList<ArrayList<Integer>> getMta() {
		return mta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private void textFile(String path) {
		File file = new File(path);
		if(file.exists() == false) {
			file.mkdir();
		}
	}
	public void saveGraph(String path, String name) {
		File file = new File(path + "/" + name + ".graph");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
		} catch (Exception e) {
			
		}finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public void saveGraphAs() {
		JFileChooser choose = new  JFileChooser();
		int a = choose.showSaveDialog(null);
		if(a == JFileChooser.APPROVE_OPTION){
			String path = choose.getCurrentDirectory().getPath();
			String name = choose.getSelectedFile().getName();
			saveGraph(path, name);
		}else {
			
		}
	}
	
	public ArrayList<ArrayList<Integer>> readGraph() {
		ArrayList<ArrayList<Integer>> result = null;
		JFileChooser chooser = new JFileChooser();
		int a = chooser.showOpenDialog(null);
		if(a == JFileChooser.APPROVE_OPTION) {
			
			File file = new File(chooser.getSelectedFile().getPath());
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				SaveGraph g = (SaveGraph)ois.readObject();
				result = g.getMta();
			} catch (Exception e) {
				
			}finally {
				if(ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}
}
