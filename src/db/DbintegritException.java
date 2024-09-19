package db;

public class DbintegritException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	//caso tente deletar campos que sao chaves estrangeiras em outras tabelas, lancara essa exception
	public DbintegritException(String msg) {
		super(msg);
		}

}
