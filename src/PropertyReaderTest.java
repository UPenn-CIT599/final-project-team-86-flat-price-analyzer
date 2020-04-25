import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PropertyReaderTest {

	@Test
	void testReadFile() {
		PropertyData data = PropertyReader.readFileUrl("https://raw.githubusercontent.com/UPenn-CIT599/final-project-team-86-flat-price-analyzer/master/resale_test.csv?token=AOT7LCVE65WJZ4UWCQWVEV26VVO3I", true);
	}
	

}
