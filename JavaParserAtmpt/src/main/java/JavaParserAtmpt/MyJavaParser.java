package JavaParserAtmpt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MyJavaParser {

	//Printing the CompilationUnit to System output
	public static void cuPrint() throws Exception{
		//get compilation unit.
		CompilationUnit cu = getCU();
		
		//print the resulting compilation unit to default system output.
		System.out.println(cu.toString());
	}
    
	//Visiting class methods
	public static void methodPrint() throws Exception {
		CompilationUnit cu = getCU();
		cu.accept(new MethodVisitor(), null);
	}
	
	/**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this 
             CompilationUnit, including inner class methods */
            System.out.println(n.getName());
            super.visit(n, arg);
        }
    }
	
	private static CompilationUnit getCU() throws FileNotFoundException {
		//Create file input stream for the file to be parsed.
		FileInputStream in = new FileInputStream("D:\\work\\GitMining\\Repos\\TechTest\\JavaParserAtmpt"
						+ "\\src\\main\\java\\JavaParserAtmpt\\TestFile.java");
				
		//parse the file.
	    CompilationUnit cu = JavaParser.parse(in);
	    
	    return cu;
	}
}
