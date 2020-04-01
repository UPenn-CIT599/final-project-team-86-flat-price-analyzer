import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PropertyReaderTest {

	@Test
	void testReadFile() {
		PropertyData data = PropertyReader.readFile("resale_test.csv", true);
		
	}
	

}
