package practica1;

public class Simbolo {
	char simbolo;
	int frec;
	float prob;
	public Simbolo(char simbolo, int frec, float prob) {
		this.simbolo = simbolo;
		this.frec = frec;
		this.prob = prob;
	}
	
	public char getSim() {
		return this.simbolo;
	}
	public int getFrec() {
		return this.frec;
	}
	public float getProb() {
		return this.prob;
	}
	
	public void setSim(char sim) {
		this.simbolo = sim;
	}
	public void setFrec(int frec) {
		this.frec = frec;
	}
	public void setProb(float prob) {
		this.prob = prob;
	}
}
