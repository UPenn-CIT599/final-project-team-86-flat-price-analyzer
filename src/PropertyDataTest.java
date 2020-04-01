import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PropertyDataTest {

	@Test
	void testAddProperty() {
		Property p1 = new Property(1992, 3, "Serangoon", "3", 98.2, 94, 288800.0);
		Property p2 = new Property(2001, 5, "Ang Mo Kio", "5", 65.3, 67, 600000.0);
		PropertyData propertyData = new PropertyData();
		propertyData.addProperty(p1);
		propertyData.addProperty(p2);
	}

	@Test
	void testGetProperty() {
		Property p1 = new Property(1992, 3, "Serangoon", "3", 98.2, 94, 288800.0);
		Property p2 = new Property(2001, 5, "Ang Mo Kio", "5", 65.3, 67, 600000.0);
		PropertyData propertyData = new PropertyData();
		propertyData.addProperty(p1);
		propertyData.addProperty(p2);
		propertyData.getProperty(0);
	}

	@Test
	void testGetSize() {
		Property p1 = new Property(1992, 3, "Serangoon", "3", 98.2, 94, 288800.0);
		Property p2 = new Property(2001, 5, "Ang Mo Kio", "5", 65.3, 67, 600000.0);
		PropertyData propertyData = new PropertyData();
		propertyData.addProperty(p1);
		propertyData.addProperty(p2);
		assertEquals(propertyData.getSize(), 2);
	}

}
