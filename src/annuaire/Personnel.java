package annuaire;

import java.time.LocalDate;
import java.util.HashSet;

public class Personnel extends IsSerializable implements Groupe{


	private static final long serialVersionUID = -3602409468016637133L;
	private String nom;
	private String prenom;
	private String fonction;
	private java.time.LocalDate datenaissance;
	private HashSet<PhoneNumber> telephone;
	
	public static class Builder
	{
		private String nom;
		private String prenom;
		
		private String fonction = "employé";
		private java.time.LocalDate datenaissance = LocalDate.now();
		
		private HashSet<PhoneNumber> telephone = new HashSet<PhoneNumber>();
		//private PhoneNumber base = new PhoneNumber("0101010101");
		//telephone.add(new PhoneNumber("0101010101"));
		
		public Builder(String nom, String prenom)
		{
			this.nom = nom;
			this.prenom = prenom;
			telephone.add(new PhoneNumber("00 00 00 00 00"));			
		}
		
		public Builder fonction(String f)
		{
			this.fonction = f;
			return this;
		}
		
		public Builder datenaissance(LocalDate d)
		{
			this.datenaissance = d;
			return this;
		}
		
		public Builder telephone(HashSet<PhoneNumber> h)
		{
			this.telephone = h;
			return this;
		}
		
		public Personnel build()
		{
			return new Personnel(this);
		}
	}
	
	private Personnel(Builder builder)
	{
		nom = builder.nom;
		prenom = builder.prenom;
		fonction = builder.fonction;
		datenaissance = builder.datenaissance;
		telephone = builder.telephone;
	}
	
	@Override
	public String ShowValues()
	{
		String phones = "";
		
		for(PhoneNumber p : this.telephone)
		{
			phones += p.ShowValue();
			phones += ", ";
		}
		
		return this.nom + this.prenom + this.fonction + this.datenaissance + phones;
	}
	
	public String getID()
	{
		return this.nom + this.prenom;
	}
	


}
