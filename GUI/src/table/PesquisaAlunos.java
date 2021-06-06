package table;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.*;
import java.util.ArrayList;

import org.json.*;

import model.Dados;
import model.Fila;
import sun.net.www.http.HttpClient;


public class PesquisaAlunos {
	Fila objeto;

	public PesquisaAlunos(){
		
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public void sendHttpsPost(String urlToRead) throws Exception {

		URL url 		   = new URL(urlToRead);
		URLConnection conn = url.openConnection();

		conn.setDoOutput(true);

		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

		writer.flush();

		String line;
		BufferedReader reader     = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		while ((line = reader.readLine()) != null) {
		    System.out.println(line);
		}
		writer.close();
		reader.close();         
		

    }
	int i = 0;
	
	public final String getHttpGET(String urlToRead) throws Exception {
		 StringBuilder result = new StringBuilder();

         URL url = new URL(urlToRead);
         HttpURLConnection conn;

			conn = (HttpURLConnection) url.openConnection();
		
         conn.setRequestMethod("GET");

         BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         String line;
         while ((line = rd.readLine()) != null) {
             result.append(line);
         }
        
     
     return result.toString();
    }


	public void enviarParaBD(Fila x) throws Exception {
        // define o cep atual
        objeto = x;

        while(!objeto.isVazia()) {
        	 // define a url
        	System.out.println(objeto.toString());
        	
            String url = "http://localhost:3000/resultados/" + objeto.recupereRA() + "/" + 
            objeto.recupereCod() + "/" + objeto.recupereFreq() + "/" + objeto.recupereNota();

            // define os dados
            sendHttpsPost(url);
        	objeto.removaUmItem();
        }
       
        
//        return obj;
    }
	
	public ArrayList<Dados> listarTodos() throws Exception{
		String url = "http://localhost:3000/resultados";
		String obj =  getHttpGET(url);
		ArrayList <Dados> lista = new ArrayList<>();
		
		JSONArray array = new JSONArray(obj);  
		for(int i=0; i < array.length(); i++)   
		{  
			JSONObject object = array.getJSONObject(i);  
			Dados resultado = new Dados();
			
			resultado.setRA	   (object.getInt("RA"));
			resultado.setCod   (object.getInt("Cod"));
			resultado.setNota  (object.getInt("Nota"));
			resultado.setFreq  (object.getInt("Freq"));
			lista.add(resultado);
		}  
		
		return lista;
	}
	
	public void remover(int cod, int ra) throws Exception 
	{
		URL url = new URL("http://localhost/matriculas/" + cod + "/" + ra);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("DELETE");
		http.setRequestProperty("Accept", "*/*");

		System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
		http.disconnect();

		
	}
}
