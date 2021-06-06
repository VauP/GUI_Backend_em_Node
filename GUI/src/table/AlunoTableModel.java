package table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Dados;

public class AlunoTableModel extends AbstractTableModel {
	public static final int COL_RA   = 0;
	public static final int COL_COD  = 1;
	public static final int COL_NOTA = 2;
	public static final int COL_FREQ = 3;
	public ArrayList<Dados> lista;
	
	public AlunoTableModel(ArrayList<Dados>l) {
		lista = new ArrayList<Dados> (l);
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override
	public Object getValueAt(int linhas, int colunas) {
		Dados resultado = lista.get(linhas);
		
		if(colunas == COL_RA)   return resultado.getRA();
		if(colunas == COL_COD)  return resultado.getCod();
		if(colunas == COL_NOTA) return resultado.getNota();
		if(colunas == COL_FREQ) return resultado.getFreq();
		return "";
		
	}
	
	@Override
	public String getColumnName(int colunas) {
		
		if(colunas == COL_RA)   return "RA";
		if(colunas == COL_COD)  return "Código";
		if(colunas == COL_NOTA) return "Nota";
		if(colunas == COL_FREQ) return "Frequência";
		return "";
		
	}
}
