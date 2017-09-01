package tarea1;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Red con 3 capas:
 * 		3 inputs
 * 		3 hidden
 * 		1 output
 * @author Marco Caballero
 *
 */
public class Red2 implements RedNeuronal {
	protected Capa in;
	protected Capa end;
	protected Capture cap;
	ArrayList<Double> maxs;
	ArrayList<Double> mins;
	Normalizator nor1,nor2,nor3;
	HashMap<Integer,ArrayList<Double>>Data ;
	ArrayList<Number>Hits;
	ArrayList<Number>datos;
	
	public Red2(){
		in= new Capa();
		end= new Capa();
		cap= new Capture();
		Hits= new ArrayList<Number>();
		datos= new ArrayList<Number>();
	}
	/* (non-Javadoc)
	 * @see tarea1.RedNeuronal#build()
	 */
	@Override
	public void build(){
		ArrayList<Double>pesos1= new ArrayList<Double>();
		pesos1.add(2.0);
		ArrayList<Double>pesos2= new ArrayList<Double>();
		pesos2.add(2.0);pesos2.add(3.0);pesos2.add(4.0);
		ArrayList<Double>pesos3= new ArrayList<Double>();
		pesos3.add(2.0);pesos3.add(3.0);pesos3.add(4.0);
		in.llenar(pesos1, -1.0, 0.1, 3);
		end.llenar(pesos3, -1.0, 0.1, 1);
		in.setNext(end);
		end.setLast(in);
		
	}
	/* (non-Javadoc)
	 * @see tarea1.RedNeuronal#training()
	 */
	@Override
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
			ArrayList<Double> correctdata= new ArrayList<Double>();
			correctdata.add(tupla.get(3)-1);
			in.backpropagation(correctdata);
			in.clearin();
		}
		
		
	}
	/* (non-Javadoc)
	 * @see tarea1.RedNeuronal#testing()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see tarea1.RedNeuronal#loadstatics(int)
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see tarea1.RedNeuronal#getHits()
	 */
	@Override
	public ArrayList<Number> getHits(){
		return this.Hits;
	}
	/* (non-Javadoc)
	 * @see tarea1.RedNeuronal#getdatos()
	 */
	@Override
	public ArrayList<Number> getdatos(){
		return this.datos;
		
	}
	
}
