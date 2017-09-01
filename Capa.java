package tarea1;
import java.util.ArrayList;
public class Capa {
	protected Capa next;
	protected Capa last;
	protected ArrayList<Sigmoide> neuronas= new ArrayList<Sigmoide>(); 
	
	public Capa(Capa next, Capa last){
		this.next=next;
		this.last=last;
		
	}
	public Capa(){
		this.next=null;
		this.last=null;
	}
	/**
	 * Devuelve la capa siguiente a esta
	 * @return Capa siguiente
	 */
	public Capa getNext(){
		return this.next;
	}
	/**
	 * Devuelve la capa anterior a esta
	 * @return Capa anterior 
	 */
	public Capa getLast(){
		return this.last;
		
	}
	/**
	 * Devuelce un arreglo con las neuronas en esta capa
	 * @return Arreglo con las neuronas en esta capa
	 */
	public ArrayList<Sigmoide> getNeuros(){
		return this.neuronas;
		
	}
	/**
	 * Acepta la capa siguiente
	 * @param next :Capa siguiente
	 */
	public void setNext(Capa next){
		if(next.getNeuros().get(0).getpesos().size()==this.neuronas.size()){
		this.next=next;}
		else{System.out.println("Esta capa no es compatible por el número de neuronas");}
	}
	/**
	 * Acepta la capa anterior
	 * @param last :capa anterior
	 */
	public void setLast(Capa last){
		if(last.getNeuros().size()==this.neuronas.get(0).getpesos().size()){
			this.last=last;
		}else{System.out.println("Esta capa no es compatible por el número de neuronas");}
	}
	
	/**
	 * Este metodo crea los sigmoides de esta capa
	 * @param pesos : pesos de los sigmoides
	 * @param bais : bais de los sigmoides
	 * @param learningRate : learningRate de los sigmoides
	 * @param numdenueronas : numero de neuroanas
	 */
	
	public void llenar(ArrayList<Double> pesos,Double bais,Double learningRate, int numdenueronas){
		int x;
		for(x=0;x<numdenueronas;x++){
			this.neuronas.add(new Sigmoide(pesos,bais,learningRate));
			
		}
		
	}
	/**
	 * Agragar un arreglo con inputs
	 * @param input: Arreglo de inputs
	 */
	public void setinput(ArrayList<Double> input){
		for(Sigmoide sig:this.neuronas){
			sig.setinputs(input);
			}
		
	}
	/**
	 * Agrega una entrada a una nuerona especifica
	 * @param neurona : Número de la Neurona a agregar el valor
	 * @param in : valor a agregar
	 */
	  
	 
	public void addin(int neurona, double in){
		this.neuronas.get(neurona).addin(in);
		
	}
	/**
	 * Devuelve un arreglo con la respuesta con los pesos y datos acutales
	 * @return : salida de la red nueronal
	 */
	public ArrayList<Double> getoutput(){
		ArrayList<Double>resp= new ArrayList<Double>();
		for(Sigmoide sig: this.neuronas){
			resp.add(sig.getout());
			
		}
		
	   
		if(this.next==null){
			return resp;
		}else{
			this.next.setinput(resp);
			return this.next.getoutput();
			
		}	
		
		
			
	}
	/**
	 * Método que devueve con las salidas de la neuronas 
	 * de esta capa
	 * @return : Arreglo con las outputs de las sigmoides
	 */
	public ArrayList<Double> transfer(){
		ArrayList<Double>resp= new ArrayList<Double>();
		for(Sigmoide sig: this.neuronas){
			resp.add(sig.getout());
			
		}
		return resp;
		
	}
	/**
	 * Limpia el arreglo de entradas
	 */
	public void clearin(){
		for(Sigmoide sig:this.neuronas){
			sig.clear();
		}
	}
	/**
	 * Método para propagar el error
	 * @param resp : Valor que se espera que devuelva la red.
	 */
	public void backpropagation(ArrayList<Double> resp){
		if(this.next==null){
			int cont=0;
			for(Sigmoide sig: this.neuronas){
				sig.errorfinal(resp.get(cont));
				sig.ajustar();
				cont=cont+1;
			}	
			
			}else{
				this.next.setinput(this.transfer());
				this.next.backpropagation(resp);
				ArrayList<Double> pesosnext=new ArrayList<Double>();
				ArrayList<Double> delta=new ArrayList<Double>();
				int x,y;
				for(x=0;x<this.neuronas.size();x++){
					pesosnext.clear();
					delta.clear();
					for(y=0;y<this.next.getNeuros().size();y++){
						pesosnext.add(this.next.getNeuros().get(y).getpesos().get(x));
						delta.add(next.getNeuros().get(y).getdelta());
					}
					this.neuronas.get(x).calerror(pesosnext, delta);
					this.neuronas.get(x).ajustar();
				}
			}
			
		}
		
		
	}
	
	

