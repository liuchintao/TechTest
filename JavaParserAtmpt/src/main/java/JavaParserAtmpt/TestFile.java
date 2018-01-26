package JavaParserAtmpt;

/**
 * This is a test file.
 * 
 * @author Magister
 *
 */
public class TestFile {

	/**
	 * @author Magister this is test parameters.
	 */
	private String testParam1;
	private int testParam2;
	private TestClass tc;

	// This is function1 without return value.
	public void testFunction1() {
		System.out.println("this is function1.");
	}

	// This is function2 with return value.
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
		 * @param test:
		 *            this is test parameter of testFunction3
		 */
		System.out.println("this is function3.");
	}

	// This is function4 that call function 2
	public void testFunction4() {
		int n = testFunction2();
		System.out.println("this is function4, and the value returned from function2 is " + Integer.toString(n));
	}

	// This is function5 that set member parameter tc's value and call TestClass arg's function.
	public void testFunction5(TestClass ct) {
		tc.setParam1("hello test!");
		ct.testClassFunction();
	}

	// This is function6 that contain local various t which is TestClass and if branch.
	public void testFunction6() {
		boolean flag = true;
		if(flag) {
			System.out.println("This is if branch.");
		}
		TestClass t = new TestClass();
	}
	
	//This is function7 that contain for, while and do-while cycle.
	public void testFunction7() {
		for(int i=0; i<10; i++) {
			System.out.print("This is FOR loop: " + Integer.toString(i));
		}
		int i = 0;
		while(++i < 10) {
			System.out.print("This is WHILE loop: " + Integer.toString(i));
			i++;
		}
		do {
			System.out.println("This is DO-WHILE loop: " + Integer.toString(i));
			i--;
		}while(i>0);
	}
	
	//This is function8 that contain SWITCH-CASE logic.
	public void testFunction8(int arg) {
		switch(arg) {
		case 1: System.out.println("SWITCH CASE parameter is: " + Integer.toString(arg));break;
		case 2: System.out.println("SWITCH CASE parameter is: " + Integer.toString(arg));break;
		default: System.out.println("SWITCH CASE parameter is default value.");break;
		}
	}
}