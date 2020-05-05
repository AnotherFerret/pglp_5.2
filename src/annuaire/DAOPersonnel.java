package annuaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOPersonnel extends DAO<Personnel> {
	
	private static final long serialVersionUID = 9116647166855351702L;

	public DAOPersonnel()
	{
		;
	}
	
	@Override
	public boolean create(Personnel p) throws FileNotFoundException, IOException
	{
		File f = new File("bdd/personnel/" + p.getID() + ".data");
		if(!f.exists())
		{
			ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(f)) ;
			oos.writeObject(p);
			oos.close();
			return true;
		}
		System.out.print("entry " + f.getName() + " already exists. Please use update()." );
		return false;
	}
	@Override
	public Personnel read(String id) throws IOException, ClassNotFoundException
	{
		FileInputStream f = new FileInputStream("bdd/personnel/" + id + ".data");
		ObjectInputStream in = new ObjectInputStream(f);
		Personnel p3 = (Personnel)in.readObject();
		in.close();
		f.close();
		return p3;
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
