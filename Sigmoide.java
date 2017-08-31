package tarea1;

import java.util.ArrayList;
/**
 * Clase de una Nuerona Sigmoide
 * @author Marco Caballero
 *
 */
public class Sigmoide {
	protected ArrayList<Double> pesos= new ArrayList<Double>();
	protected Double bais;
	protected ArrayList<Double> inputs= new ArrayList<Double>();
	protected Double delta,error,learningRate;

	public Sigmoide(ArrayList<Double> pesos, Double bais, Double learningRate){
		this.pesos=pesos;
		this.bais=bais;
		delta=0.0;
		error=0.0;
		this.learningRate=learningRate;
	}
	/**
	 * Devuelve los pesos asigandos a las neuronas de la 
	 * siguiente capa
	 * @return Un arreglo con los pesos
	 */
	public ArrayList<Double> getpesos(){
		return pesos;
		
	}
	/**
	 * Devuleve el bais de la neurona
	 * @return Bais de la nuerona
	 */
	public  Double getbais(){
		return bais;
		
	}
	/**
	 * Devuelce el learningRate
	 * @return learningRate
	 */
	public Double getlearningRate(){
		return this.learningRate;
		
	}
	/**
	 * Recibe el learningRate
	 * @param learningRate : valor a cambiar.
	 */
	public void setlearningRate(Double learningRate){
		this.learningRate= learningRate;
		
	}
	/**
	 * Recibe los pesos a asignar a las nueroans 
	 * siguientes
	 * @param pesos : Arreglo con los pesos a asignar.
	 */
	public void setpesos(ArrayList<Double> pesos){
		this.pesos=pesos;
		
	}
	/**
	 * Reibe el bais a asignar a la neurona.
	 * @param bais : valor del bais a asignar.
	 */
	public void setbais(Double bais){
		this.bais=bais;
		
	}
	/**
	 * Recibe el valor de la entrada
	 * @param inputs : Arreglo que contiene el imput, debe tener el mismo orden que los pesos
	 */
	public void setinputs(ArrayList<Double> inputs){
		this.inputs=inputs;
	}
	/**
	 * Devuelve el erro de la neurona 
	 * @return erro de la neurona
	 */
	
	public Double geterror(){
		return this.error;
		
	}
	/**
	 * Debuelve el delta de la nuerona 
	 * @return el delta de la nuerona.
	 */
	public Double getdelta(){
		return this.delta;
		
	}
	/**
	 * Calcula la salidad segun los pesos y los inputs en
	 * la nueroa
	 * @return salida correspondiente a los pesos y inputs
	 */
	public double getout(){
		if(pesos.size()!=inputs.size()){System.out.println("Error en sigmoide pesos distintos a inputs");return 0;}
		double value=this.bais;
		int x;
		for(x=0;x<pesos.size();x++){
			value=value+ pesos.get(x)*inputs.get(x);
			
		}
		double resp= 1.0/(1.0+ Math.exp(-value));
		return resp;
		
	}
	/**
	 * Calculua el erro, si esta neurona está al final 
	 * y es la que devueve la salida.
	 * @param espectedoutpuy : Salida esperada de la nuerona.
	 */
	public void errorfinal(double espectedoutpuy){
		 this.error=espectedoutpuy- this.getout();
		 this.caldelta();
		}
		
	/**
	 * Calcula el delta de la nurona
	 */
	public void caldelta(){
		this.delta=this.error*this.transferDErivate();
		
	}
	/**
	 * Calcula el transferDerivate
	 * @return transderDerivate
	 */
	public Double transferDErivate() {
		
		return (this.getout()*(1.0-this.getout()));
	}
	/**
	 * Cacula el erro si esta es una hidden neurona.
	 * @param pesonext :pesos correspondientes de la siguiente neurona
	 * @param deltanext : delta de la neurona siguiente.
	 */
	public void calerror(ArrayList<Double>pesonext, ArrayList<Double> deltanext){
		this.error=0.0;
		int x=0;
		for(Double peso: pesonext){
			error= error + peso*deltanext.get(x);
			x=x+1;
		}
		this.caldelta();
	}
	/**
	 * Ajusta los pesos segun el error y el delta actual.
	 */
	public void ajustar(){
		
		if(this.pesos.size()!=this.inputs.size()){System.out.println("Error diferentes tamaños en pesos y inputs");;}
		int x;double temp;ArrayList<Double>temps=new ArrayList<Double>();
		for(x=0;x<this.pesos.size();x++){
			
			temp=pesos.get(x)+(this.learningRate*this.delta*this.inputs.get(x));
			temps.add(temp);
		}
		this.pesos=temps;
		this.bais=this.bais+(this.learningRate*this.delta);
	}
	
	public void addin(Double x){
		this.inputs.add(x);
		
	}
	public void clear() {
		this.inputs.clear();
		
	}
}
