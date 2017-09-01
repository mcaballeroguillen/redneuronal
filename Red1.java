package tarea1;

import java.util.ArrayList;
import java.util.HashMap;

public class Red1 {
	protected Capa in;
	protected Capa mean;
	protected Capa end;
	protected Capture cap;
	ArrayList<Double> maxs;
	ArrayList<Double> mins;
	Normalizator nor1,nor2,nor3;
	HashMap<Integer,ArrayList<Double>>Data ;
	ArrayList<Number>Hits;
	ArrayList<Number>datos;
	public Red1(){
		in= new Capa();
		mean= new Capa();
		end= new Capa();
		cap= new Capture();
		Hits= new ArrayList<Number>();
		datos= new ArrayList<Number>();
	}
	/**
	 * Construye la red neuronal.
	 */
	public void build(){
		ArrayList<Double>pesos1= new ArrayList<Double>();
		pesos1.add(2.0);
		ArrayList<Double>pesos2= new ArrayList<Double>();
		pesos2.add(2.0);pesos2.add(3.0);pesos2.add(4.0);
		ArrayList<Double>pesos3= new ArrayList<Double>();
		pesos3.add(2.0);pesos3.add(3.0);pesos3.add(4.0);
		in.llenar(pesos1, -1.0, 0.1, 3);
		mean.llenar(pesos2, -1.0, 0.1, 3);
		end.llenar(pesos3, -1.0, 0.1, 1);
		in.setNext(mean);
		mean.setLast(in);
		mean.setNext(end);
		end.setLast(mean);
		
	}
	/**
	 * Entrena le red con los primero 206 datos.
	 */
	public void training(){
		cap.readfile("/home/marco/table.csv");
		cap.load();
		maxs= cap.getmaxs();
		mins= cap.getmins();
		nor1= new Normalizator(maxs.get(0),mins.get(0),1.0,0.0);
		nor2= new Normalizator(maxs.get(1),mins.get(1),1.0,0.0);
		nor3= new Normalizator(maxs.get(2),mins.get(2),1.0,0.0);
		Data = cap.getData();
		int x;
		for(x=0;x<206;x++){
			ArrayList<Double> tupla= Data.get(x);
			in.addin(0, nor1.getnormalization(tupla.get(0)));
			in.addin(1, nor2.getnormalization(tupla.get(1)));
			in.addin(2, nor3.getnormalization(tupla.get(2)));
			in.backpropagation(tupla.get(3)-1);
			in.clearin();
		}
		
		
	}
	/**
	 * Testea el aprendizaje de la red con los otros 100 dataos.
	 * @return : Aciertos de 100 intentos.
	 */
	public int testing(){
		int cont=0;int x;
		for(x=206;x<306;x++){
			ArrayList<Double> tupla= Data.get(x);
			in.addin(0, nor1.getnormalization(tupla.get(0)));
			in.addin(1, nor2.getnormalization(tupla.get(1)));
			in.addin(2, nor3.getnormalization(tupla.get(2)));
			ArrayList<Double> output = in.getoutput();
			double resp = output.get(0);
			in.clearin();
			if(resp>0.5){resp=1.0;}else{resp=0.0;}
			if(resp==tupla.get(3)-1){cont=cont+1;}
		}
		return cont;
		
	}
	/**
	 * Carga las estadisticas para luego gráficar
	 * @param cycles : cantidad de ciclos de entrenamiento a hacer
	 */
	public void loadstatics(int cycles){
		this.Hits.clear();
		this.datos.clear();
		int x; double temhits;
		for(x=1;x<cycles+1;x++){
			this.training();
			temhits= this.testing();
			datos.add((double) (x*206));
			this.Hits.add(temhits/100);
		}
		
	}
	/**
	 * Devuelve el arreglo con los aciertos 
	 * @return Arreglo que contiene los aciertos
	 */
	public ArrayList<Number> getHits(){
		return this.Hits;
	}
	/**
	 * Devuelce el arreglo con la cantidad de datos de
	 * entranimientos.
	 * @return Arreglo que contiene la cantidad de datos procesados. 
	 */
	public ArrayList<Number> getdatos(){
		return this.datos;
		
	}
	
}
