package tarea1;

public class Normalizator {
  protected Double hightdata;
  protected Double lowdata;
  protected Double hightrange;
  protected Double lowrange;
  /**
   * Clase para normlaizar datos.
   * @param hightdata : Mayor valor de los datos. 
   * @param lowdata : Menor valor de los datos.
   * @param hightrange : Mayor valor del rango a normalizar.
   * @param lowrange   : Menor valor del rango a normalizar.
   */
  public Normalizator (Double hightdata, Double lowdata, Double hightrange, Double lowrange){
	    this.hightdata=hightdata;
	    this.lowdata=lowdata;
	    this.hightrange= hightrange;
	    this.lowrange=lowrange;
	    		
  }
  /**
   * Método que normaliza un dato
   * @param dato : dato a normalizar
   * @return dato ya normalizado.
   */
  public Double getnormalization(Double dato){
	  Double resp = (dato-this.lowdata);
	  resp= resp*(this.hightrange-this.lowrange);
	  resp=resp/(this.hightdata-this.lowdata);
	  resp=resp+ this.lowrange;
	  return resp;
	  
  }
  
   }