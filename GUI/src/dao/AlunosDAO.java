//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//import model.Dados;
//
//public class AlunosDAO {
//
//	private Connection conn;
//	private PreparedStatement stmt;
//	private Statement st;
//	private ResultSet rs;
//	private ArrayList <Dados> lista = new ArrayList<>();
//		
//	public AlunosDAO() {
//		conn = new ConnectionFactory().getConexao();
//	}
//		
//	public ArrayList<Dados> listarTodos(){
//		String sql = "SELECT * FROM Dados";
//		try {
//			st = conn.createStatement();
//			rs = st.executeQuery(sql);
//			
//			while(rs.next()) {
//				Dados resultado = new Dados();
//				
//				resultado.setRA	   (rs.getInt("RA"));
//				resultado.setCod   (rs.getInt("Cod"));
//				resultado.setNota  (rs.getInt("Nota"));
//				resultado.setFreq  (rs.getInt("Freq"));
//				lista.add(resultado);
//			}
//		}catch(Exception erro) {
//			throw new RuntimeException("Erro 6: " + erro);
//		}
//		return lista;
//	}
//	
//}
