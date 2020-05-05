package annuaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOGroupePersonnel extends DAO<GroupePersonnel> {


	private static final long serialVersionUID = -2994458279124412256L;

	@Override
	public boolean create(GroupePersonnel p) throws FileNotFoundException, IOException {
		File f = new File("bdd/gpersonnel/" + p.getID() + ".data");
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
	public GroupePersonnel read(String id) throws IOException, ClassNotFoundException {
		FileInputStream f = new FileInputStream("bdd/gpersonnel/" + id + ".data");
		ObjectInputStream in = new ObjectInputStream(f);
		GroupePersonnel gp = (GroupePersonnel)in.readObject();
		in.close();
		f.close();
		return gp;
	}

	@Override
	public boolean delete(GroupePersonnel p) {
		File f = new File("bdd/gpersonnel/" + p.getID() + ".data");
		if(f.exists())
		{
			f.delete();
			return true;
		}
		System.out.print("entry " + f.getName() + " doesn't exist." );
		return false;
	}

	@Override
	public boolean update(GroupePersonnel p) throws FileNotFoundException, IOException {
		File f = new File("bdd/gpersonnel/" + p.getID() + ".data");
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

}
