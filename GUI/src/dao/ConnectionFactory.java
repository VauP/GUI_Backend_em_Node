//package dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionFactory {
//		
//	Connection connection;
//	public Connection getConexao() {
//		try {
//			String connectionUrl =
//	                "jdbc:sqlserver://regulus.academico.cotuca.unicamp.br:1433;"
//	                        + "database=BD20351;"
//	                        + "user=BD20351;"
//	                        + "password=BD20351;"
//	                        + "encrypt=true;"
//	                        + "trustServerCertificate=false;"
//	                        + "loginTimeout=30;";
//			
//			return connection = DriverManager.getConnection(connectionUrl); 
//		}catch(Exception erro) {
//			throw new RuntimeException ("Erro 1 : " + erro);
//		}
//	}
//				
////        String connectionUrl =
////                "jdbc:sqlserver://regulus.academico.cotuca.unicamp.br:1433;"
////                        + "database=BD20351;"
////                        + "user=BD20351@yourserver;"
////                        + "password=BD20351;"
////                        + "encrypt=true;"
////                        + "trustServerCertificate=false;"
////                        + "loginTimeout=30;";
////        
//
////        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
////            // Code here.
////        }
////        // Handle any errors that may have occurred.
////        catch (SQLException e) {
////            e.printStackTrace();
////        }
//
//}
