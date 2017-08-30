package tarea1;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	protected static Capture cap1= new Capture();
	public static void main(String []args){
		cap1.readfile("/home/marco/table.csv");
		cap1.load();
		HashMap<Integer, ArrayList<Number>> prueba= cap1.getData();
		System.out.println(prueba.get(0).toString());
		System.out.println(prueba.get(178).toString());	
		System.out.println(prueba.get(305).toString());
		
	}
}
