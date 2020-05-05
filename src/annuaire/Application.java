package annuaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashSet;


enum Application {
	ENVIRONNEMENT;
	
	
	
	public static void main(String[] args) {

		LocalDate d = LocalDate.of(1975, 03, 19);
		HashSet<PhoneNumber> t = new HashSet<PhoneNumber>();
		t.add(new PhoneNumber("0123456789"));
		t.add(new PhoneNumber("9876543210"));
		
		
		Personnel p1 = new Personnel
					.Builder("name", "name2")
					.build();
		
		Personnel p2 = new Personnel
						.Builder("name3", "name4")
						.fonction("random task")
						.datenaissance(d)
						.telephone(t)
						.build();
		
		GroupePersonnel gp1 = new GroupePersonnel();
		GroupePersonnel gp2 = new GroupePersonnel();
		
		gp1.AddPersonnel(p1);
		gp1.AddPersonnel(p2);
		
		gp2.AddPersonnel(p1);
		
		DAOPersonnel daop = new DAOPersonnel();
		try {
			daop.create(p1);
			daop.create(p2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}					
}
