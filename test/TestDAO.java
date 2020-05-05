package annuaire;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class TestDAO {

	private Personnel p1;
	private Personnel p2;
	@Before
	public void init()
	{
		LocalDate d = LocalDate.of(1975, 03, 19);
		HashSet<PhoneNumber> t = new HashSet<PhoneNumber>();
		t.add(new PhoneNumber("0123456789"));
		t.add(new PhoneNumber("9876543210"));
		
		
		p1 = new Personnel
		.Builder("name", "name2")
		.build();
		
		p2 = new Personnel
		.Builder("name3", "name4")
		.fonction("random task")
		.datenaissance(d)
		.telephone(t)
		.build();
	}
	
	
	@Test
	public void DAOCreateTest()
	{
		DAOPersonnel daop = new DAOPersonnel();
		try {
			daop.delete(p1);
			daop.delete(p2);
			daop.create(p1);
			daop.create(p2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Personnel p3 = daop.read("namename2");
			Personnel p4 = daop.read("name3name4");
			assertEquals(p1.ShowValues() + p2.ShowValues(), p3.ShowValues() + p4.ShowValues());
		} catch (ClassNotFoundException e) 
		{
		} 
		catch (IOException e) 
		{
		}
	}
}
