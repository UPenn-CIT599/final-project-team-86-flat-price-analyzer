import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PropertyTest {

	@Test
	void testToString() {
		Property p1 = new Property(1992, 3, "Serangoon", "3", 98.2, 94, 288800.0);
		assertEquals(p1.toString(), "[1992, 3, SERANGOON, 3, 98.2, 94, 288800.0]");
	}

	@Test
	void testProperty() {
		Property p1 = new Property(2001, 5, "Ang Mo Kio", "5", 65.3, 67, 600000.0);
	}

	@Test
	void testToFlat() {
		Property p2 = Property.toFlat("2015-01,ANG MO KIO,3 ROOM,109,ANG MO KIO AVE 4,01 TO 03,67,New Generation,1978,62,300000");		
		assertEquals(p2.toString(), "[2015, 1, ANG MO KIO, 3, 67.0, 62, 300000.0]");
	}
}