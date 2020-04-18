import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class MathsAnalysisTest {


	@Test
	void testAnswersToInsightQuestions() {
		
		PropertyData data = PropertyReader.readFile("resale_test.csv", true);
		HashMap<String, String> userInputs = new HashMap<String, String>();
		userInputs.put("flatType", "3");
		userInputs.put("town", "ANG MO KIO");
		
		MathsAnalysis m = new MathsAnalysis(data, userInputs);
		HashMap<String, HashMap<String, Double>> answers = m.answersToInsightQuestions();
		
		// test for question 1
		HashMap<String, Double> answerToQ1 = answers.get("q1"); 
		assertEquals(302184, Math.round(answerToQ1.get("3")));
		
		// test for question 2
		HashMap<String, Double> answerToQ2 = answers.get("q2");
		assertEquals(302184, Math.round(answerToQ2.get("ANG MO KIO")));
		
		// test for question 3
		HashMap<String, Double> answerToQ3 = answers.get("q3");
		assertEquals(307500, Math.round(answerToQ3.get("q3")));
		
		// test for question 4
		HashMap<String, Double> answerToQ4 = answers.get("q4");
		assertEquals(70, Math.round(answerToQ4.get("3")));
		
	}

}
