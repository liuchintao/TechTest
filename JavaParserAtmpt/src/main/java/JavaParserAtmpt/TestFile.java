package JavaParserAtmpt;

/**
 * This is a test file.
 * @author Magister
 *
 */
public class TestFile{
	
	/**
	 * @author Magister
	 * this is test parameters.
	 */
	private String testParam1;
	private int testParam2;
	
	//This is function1 without return value.
	public void testFunction1() {
		System.out.println("this is function1.");
	}
	
	//This is function2 with return value.
	public int testFunction2() {
		/**
		 * @return return value is 0.
		 */
		System.out.println("this is function2.");
		return 0;
	}
	
	// This is function3 with parameter.
	public void testFunction3(int test) {
		/**
		 * @param test: this is test parameter of testFunction3
		 */
		System.out.println("this is function3.");
	}
	
	//This is function4 that call function 2
	public void testFunction4() {
		int n = testFunction2();
		System.out.println("this is function4, and the value returned from function2 is " + Integer.toString(n));
	}
}