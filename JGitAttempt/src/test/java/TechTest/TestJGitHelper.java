package TechTest;

import java.io.IOException;

import org.junit.Test;

import TechTest.helper.JGitHelper;

/**
 *  @author   :   Magister
 *  @fileName :   TechTest.TestJGitHelper.java
 *  
 *  2018年3月5日	下午4:36:00  
*/

public class TestJGitHelper {

	@Test
	public void testCreateNewFolderAndFile() throws IOException {
		JGitHelper.createNewFolder("test_folder");
		JGitHelper.createCommitFile("test_folder", "test1/test2/test.java");
		
	}
}
