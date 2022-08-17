package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Temp;
	
public class SaveGraph  {
	public boolean isSave = false;
	
	public static void saveGraph(String path, String name, Temp temp) {
		File file = new File(path + "/" + name + ".graph");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(temp);

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
	
	public  void saveGraphAs(Temp temp) {
		JFileChooser choose = new  JFileChooser();
		int a = choose.showSaveDialog(null);
		if(a == JFileChooser.APPROVE_OPTION){
			String path = choose.getCurrentDirectory().getPath();
			String name = choose.getSelectedFile().getName();
			saveGraph(path, name, temp);
			isSave = true;
		}
	}
	
	public static Temp readGraph() {
		Temp result = null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("", "graph");
		chooser.setFileFilter(fileNameExtensionFilter);
		int a = chooser.showOpenDialog(null);
		if (a == JFileChooser.APPROVE_OPTION) {
			File file = new File(chooser.getSelectedFile().getPath());
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				System.out.println(1);
				result = (Temp) ois.readObject();
				String result2 =  (String) ois.readObject();
				System.out.println(3);
				System.out.println(result);
			} catch (Exception e) {
			} finally {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
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
