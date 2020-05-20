package annuaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Properties;

public class DAOPersonnel extends DAO<Personnel> {
	
	private static final long serialVersionUID = 9116647166855351702L;

	public DAOPersonnel()
	{
		;
	}
	
	@Override
	public boolean create(Personnel p) throws SQLException
	{
		String dburl = "jdbc:derby:test;create=true";
		Connection conn = null;
		Properties connectionProps = new Properties();
		
		connectionProps.put("user", "user");
		connectionProps.put("password", "pass");
		try
		{
			conn = DriverManager.getConnection(dburl, connectionProps);
			PreparedStatement prepare = conn.prepareStatement(
					"INSERT INTO personnel (nom, prenom, fonction, date, telephone) VALUES(?, ?, ?, ?, ?)");
			prepare.setString(1, p.getName());
			prepare.setString(2, p.getPrenom());
			prepare.setString(3, p.getFonction());
			prepare.setString(4, p.getDate());
			prepare.setString(5, p.getTelephone());
			int result = prepare.executeUpdate();
			if(result == 0)
			{
				System.out.print("Create failed\n");
				conn.close();
				return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			conn.close();
			return false;
		}
		conn.close();
		return true;
	}
	@Override
	public Personnel read(String nom, String prenom) throws SQLException 
	{
		String dburl = "jdbc:derby:test;create=true";
		Connection conn = null;
		Properties connectionProps = new Properties();
		
		connectionProps.put("user", "user");
		connectionProps.put("password", "pass");
		try
		{
			conn = DriverManager.getConnection(dburl, connectionProps);
			PreparedStatement prepare = conn.prepareStatement(
					"SELECT * FROM personnel WHERE nom = ? AND prenom = ?");
			prepare.setString(1, nom);
			prepare.setString(2,  prenom);
			ResultSet result = prepare.executeQuery();
			if(result.first())
			{
				HashSet<PhoneNumber> t = new HashSet<PhoneNumber>();
				String phones = result.getString("telephone");
				
				for(int i = 0; i < phones.length(); i+=10)
				{
					t.add(new PhoneNumber(phones.substring(i, i+9)));
				}
				
				Personnel p = new Personnel
								.Builder(result.getString("nom"), result.getString("prenom"))
								.fonction(result.getString("fonction"))
								.datenaissance(LocalDate.parse(result.getString("date")))
								.telephone(t)
								.build();
								
				conn.close();
				return p;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			conn.close();
		}
		conn.close();
		return null;
	}
	@Override
	public boolean update(Personnel p) throws FileNotFoundException, IOException
	{
		File f = new File("bdd/personnel/" + p.getID() + ".data");
		if(f.exists())
		{
			ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(f)) ;
			oos.writeObject(p);
			oos.close();
			return true;
		}
		System.out.print("entry " + f.getName() + " doesn't exist. Please use create() first." );
		return false;
	}
	
	@Override
	public boolean delete(Personnel p)
	{
		File f = new File("bdd/personnel/" + p.getID() + ".data");
		if(f.exists())
		{
			f.delete();
			return true;
		}
		System.out.print("entry " + f.getName() + " doesn't exist." );
		return false;
	}
}
