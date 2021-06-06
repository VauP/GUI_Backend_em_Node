package model;
import java.util.ArrayList;
import java.util.List;

import model.Fila;

public class Dados {

	List<Fila> oi = new ArrayList<>();
	
	private int RA;
	private int Cod;
	private int Nota;
	private int Freq;
	public int getRA() {
		return RA;
	}
	
	public void setRA(int rA) {
		RA = rA;
	}
	public int getCod() {
		return Cod;
	}
	public void setCod(int cod) {
		Cod = cod;
	}
	public int getNota() {
		return Nota;
	}
	public void setNota(int nota) {
		Nota = nota;
	}
	public int getFreq() {
		return Freq;
	}
	public void setFreq(int freq) {
		Freq = freq;
	}
	
}
