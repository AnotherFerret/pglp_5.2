package annuaire;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public abstract class DAO<T> implements Serializable{
	   

	private static final long serialVersionUID = -7717251990065292246L;
	public abstract boolean create(T p) throws FileNotFoundException, IOException, SQLException;
	  public abstract T read(String nom, String prenom) throws IOException, ClassNotFoundException, SQLException;
	  public abstract boolean delete(T p);
	  public abstract boolean update(T p) throws FileNotFoundException, IOException;
	}