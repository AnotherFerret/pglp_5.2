package annuaire;


public class DAOfactory {

	
	public static DAO<Personnel> getPersonnelDAO()
	{
		return new DAOPersonnel();
	}
	public static DAO<GroupePersonnel> getGroupeDAO()
	{
		return new DAOGroupePersonnel();
	}
	

}
