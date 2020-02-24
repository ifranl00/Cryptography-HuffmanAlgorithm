package practica1;

public class Simbolo {
	String simbolo;
	int frec;
	float prob;
	public Simbolo(String simbolo, int frec, float prob) {
		this.simbolo = simbolo;
		this.frec = frec;
		this.prob = prob;
	}
	
	public String getSim() {
		return this.simbolo;
	}
	public int getFrec() {
		return this.frec;
	}
	public float getProb() {
		return this.prob;
	}
}
