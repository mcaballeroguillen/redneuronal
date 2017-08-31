package tarea1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Capture {
	protected Scanner scan;
	protected Scanner linescan;
	protected HashMap<Integer, ArrayList<Double>>Data;
	
	public Capture(){
		Data= new HashMap<Integer,ArrayList<Double>>();
		
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
	private ArrayList<Double> readline(String nextLine) {
		this.linescan=new Scanner(nextLine);
		linescan.useDelimiter(",");
		ArrayList<Double> resp = new ArrayList<Double>();
		while(linescan.hasNext()){
			String param=linescan.next();
			param.replaceAll(" ", "");
			try{
			resp.add(Double.valueOf(param).doubleValue());}
			catch(Exception e){System.out.println("Erro al covertir datos");}
		}
		return resp;
	}
	
	public HashMap<Integer, ArrayList<Double>> getData(){
		return this.Data;
		
	}
	
	public ArrayList<Double> getmaxs(){
		ArrayList<Double> resp= new ArrayList<Double>();
		int numofparam= this.Data.get(0).size();
		int z,y;
		for(z=0;z<numofparam;z++){
			Double max= this.Data.get(0).get(z);
			for(y=0;y<this.Data.size();y++){
				 Double tempmax= this.Data.get(y).get(z);
				 if(max<tempmax){max=tempmax;}
				
			}
			resp.add(max);
		}
		
		return resp;
	}
	
	public ArrayList<Double> getmins(){
		ArrayList<Double> resp= new ArrayList<Double>();
		int numofparam= this.Data.get(0).size();
		int x,y;
		for(x=0;x<numofparam;x++){
			Double min= this.Data.get(0).get(x);
			for(y=0;y<this.Data.size();y++){
				Double tempmin= this.Data.get(y).get(x);
				if(min>tempmin){min=tempmin;}
				
			}
			resp.add(min);
		}
		return resp;
	}
}
