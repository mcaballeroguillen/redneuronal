package tarea1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Capture {
	protected Scanner scan;
	protected Scanner linescan;
	protected HashMap<Integer, ArrayList<Number>>Data;
	
	public Capture(){
		Data= new HashMap<Integer,ArrayList<Number>>();
		
	}
	/**
	 * Métedo que lee el archivo.
	 * @param file :Dirección del archivo.
	 */
	public void readfile(String file){
		try{
			this.scan=new Scanner(new FileReader(file));}
		catch(Exception e){System.out.println("Error al abrir el archivo");}
		finally{
			//scan.close();
		}
		
	}
	/**
	 * Carga la base de datos, ya con el archivo leido.
	 */
	public void  load(){
		int id=0;
		while(this.scan.hasNextLine()){
			this.Data.put(id, readline(this.scan.nextLine()));
			id=id+1;
			
		}
		
	}
	/**
	 * Lee la linea y extrae los parametros.
	 * @param nextLine : String de la linea a leer.
	 * @return Arreglo con los datos ya leido. 
	 */
	private ArrayList<Number> readline(String nextLine) {
		this.linescan=new Scanner(nextLine);
		linescan.useDelimiter(",");
		ArrayList<Number> resp = new ArrayList<Number>();
		while(linescan.hasNext()){
			String param=linescan.next();
			param.replaceAll(" ", "");
			try{
			resp.add(Float.valueOf(param).floatValue());}
			catch(Exception e){System.out.println("Erro al covertir datos");}
		}
		return resp;
	}
	
	public HashMap<Integer, ArrayList<Number>> getData(){
		return this.Data;
		
	}
}
