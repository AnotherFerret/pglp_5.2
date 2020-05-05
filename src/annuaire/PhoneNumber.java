package annuaire;


public class PhoneNumber extends IsSerializable {


	private static final long serialVersionUID = -9198194174273914699L;
	private String phone; 
	
	public PhoneNumber(String phone)
	{
		if(phone.length() < 10)
		{
			//exception à gérer
			this.phone = "0000000000";
		}
		else
		{
			this.phone = phone;
		}
	}
	
	public String ShowValue()
	{
		return this.phone;
	}


}
