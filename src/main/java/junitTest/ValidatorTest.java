package junitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import database.Validator;

class ValidatorTest {

	
	@Test
    public void NavnTest() {
        String navn1 = "Daniel Gunleiksrud";
        String navn2 = "Harald";
        String navn3 = "Harald-Hansen";
        
        String navn4 = "jhonny";
        String navn5 = "Harald-hansen";
        String navn6 = "Hans hansen";
        
        assertTrue(Validator.isGyldigName(navn1));
        assertTrue(Validator.isGyldigName(navn2));
        assertTrue(Validator.isGyldigName(navn3));
        
        assertFalse(Validator.isGyldigName(navn4));
        assertFalse(Validator.isGyldigName(navn5));
        assertFalse(Validator.isGyldigName(navn6));
    }
	
	@Test
	public void MobilTest() {
		String mobil1 = "12345678";
		String mobil2 = "123456789";
		String mobil3 = "1234567";
		
		assertTrue(Validator.isGyldigMobil(mobil1));
		assertFalse(Validator.isGyldigMobil(mobil2));
		assertFalse(Validator.isGyldigMobil(mobil3));
	}
	
	@Test
	public void PassordTest() {
		String passordTest1 = "%123Test";
		String passordTest2 = "321421413213";
		String passordTest3 = "Passord123";
		String passordTest4 = "%()HalloTest";
		String passordTest5 = "Passord";
		String passordTest6 = "pass";
		
		
		assertTrue(Validator.isGyldigPassord(passordTest1));
		assertFalse(Validator.isGyldigPassord(passordTest2));
		assertFalse(Validator.isGyldigPassord(passordTest3));
		assertFalse(Validator.isGyldigPassord(passordTest4));
		assertFalse(Validator.isGyldigPassord(passordTest5));
		assertFalse(Validator.isGyldigPassord(passordTest6));
		
	}

}
