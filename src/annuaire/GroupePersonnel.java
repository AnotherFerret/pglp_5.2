package annuaire;


import java.util.HashSet;

public class GroupePersonnel extends IsSerializable implements Groupe {
	

	private static final long serialVersionUID = 6802571326442599493L;
	private HashSet<Groupe> employe = new HashSet<Groupe>();
	
	@Override
	public String ShowValues()
	{
		String tmp = "";
		for(Groupe p : employe)
		{
			tmp += p.ShowValues();
			tmp += "\n";
		}
		return tmp;
		
	}
	
	public void AddPersonnel(Groupe p1)
	{
		employe.add(p1);
	}
	
	public void RemovePersonnel(Groupe p1)
	{
		employe.remove(p1);
	}

	public String getID() {
		
		return this.ShowValues();
	}

	
}
