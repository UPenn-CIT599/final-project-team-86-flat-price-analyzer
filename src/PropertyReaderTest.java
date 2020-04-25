import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PropertyReaderTest {

	@Test
	void testReadFile() {
		PropertyData data = PropertyReader.readFileLocal("resale_test.csv", true);
		
	}
	

}
