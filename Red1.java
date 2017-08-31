package tarea1;

import java.util.ArrayList;
import java.util.HashMap;

public class Red1 {
	protected Capa in;
	protected Capa mean;
	protected Capa end;
	protected Capture cap;
	public Red1(){
		in= new Capa();
		mean= new Capa();
		end= new Capa();
		cap= new Capture();
	}
	
	public void build(){
		ArrayList<Double>pesos1= new ArrayList<Double>();
		pesos1.add(2.0);
		ArrayList<Double>pesos2= new ArrayList<Double>();
		pesos2.add(2.0);pesos2.add(3.0);pesos2.add(4.0);
		ArrayList<Double>pesos3= new ArrayList<Double>();
		pesos3.add(2.0);pesos3.add(3.0);pesos3.add(4.0);
		in.llenar(pesos1, -2.0, 0.1, 3);
		mean.llenar(pesos2, -2.0, 0.1, 3);
		end.llenar(pesos3, -2.0, 0.1, 1);
		in.setNext(mean);
		mean.setLast(in);
		mean.setNext(end);
		end.setLast(mean);
		
	}
	
	public void training(){
		cap.readfile("/home/marco/table.csv");
		cap.load();
		ArrayList<Double> maxs= cap.getmaxs();
		ArrayList<Double> mins= cap.getmins();
		Normalizator nor1= new Normalizator(maxs.get(0),mins.get(0),1.0,0.0);
		Normalizator nor2= new Normalizator(maxs.get(1),mins.get(1),1.0,0.0);
		Normalizator nor3= new Normalizator(maxs.get(2),mins.get(2),1.0,0.0);
		HashMap<Integer,ArrayList<Double>>Data = cap.getData();
		int x;
		for(x=0;x<206;x++){
			ArrayList<Double> tupla= Data.get(x);
			in.addin(0, nor1.getnormalization(tupla.get(0)));
			in.addin(1, nor2.getnormalization(tupla.get(1)));
			in.addin(2, nor3.getnormalization(tupla.get(2)));
			in.backpropagation(tupla.get(3)-1);
			in.clearin();
		}
		int cont=0;
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
		System.out.println(cont);
		
	}
}
