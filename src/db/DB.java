package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//essa classe ira estabelecer a conexao com nosso banco
public class DB {
	// criando objeto conection
	private static Connection conn = null;
	//metodo que estabelece conexão com banco de dados
	public static Connection getConnection() {
		//caso a conexao nao tenha sido iniciada antes
		if(conn == null ) {
			//tratando possiveis erros
			try {
				//objeto Properties serve para ler banco enderecos de banco de dados
				Properties props = loadProperties();
				//guardando o link do banco
				String url = props.getProperty("dburl");
				//criando a conexao com o url e dados
				conn = DriverManager.getConnection(url, props);
				//tratando erros
			} catch (SQLException e) {
				//lancando possiveis erros relacionado a bancos de dados
				throw new DbException(e.getMessage());
			}
			
		}
		//retorna uma conexao do tipo Connection
		return conn;
	}
	//fechando a conexao
	public static void closeConnection() {
		//caso nao seja nulo | entao esta iniciada
		if(conn != null) {
			//tratando possiveis erros
			try {
				//fechando conexao
				conn.close();
			//tratando erros
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//lendo dados do banco de dados
	private static Properties loadProperties() {
		//tratando dados
		//criando um objeto FileInputStream e lendo os dados do nosso banco
		try( FileInputStream fs = new FileInputStream("db.properties")){
			//props ira armazenar as propriedades do banco
			Properties props = new Properties();
			//lendo propriedades do file com os dados
			props.load(fs);
			//retornando o props
			return props;
		}
		//tratando erros
		catch(IOException e){
			throw new DbException(e.getMessage());
		}
	}
	//use para fechar os statement utilizados
	public static void closeStatement(Statement st) {
		if(st !=null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		
	}
	//use para fechar os ResultsSet utilizados
	public static void closeSResultSet(ResultSet rs) {
		if(rs !=null) {
			try {
			rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	
	}
	
	
	
}
