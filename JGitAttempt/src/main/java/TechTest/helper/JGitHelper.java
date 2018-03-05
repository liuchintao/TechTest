package TechTest.helper;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 *  @author   :   Magister
 *  @fileName :   TechTest.helper.JGitHelper.java
 *  
 *  2018年3月1日	下午2:44:24  
*/

public class JGitHelper {
    public static Repository openJGitCookbookRepository() throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
    }

    public static Repository createNewRepository() throws IOException {
        // prepare a new folder
        File localPath = File.createTempFile("TestGitRepository", "");
        if(!localPath.delete()) {
            throw new IOException("Could not delete temporary file " + localPath);
        }

        // create the directory
        Repository repository = FileRepositoryBuilder.create(new File(localPath, ".git"));
        repository.create();

        return repository;
    }
    
    public static boolean createNewFolder(String commit) {
    	/**
    	 * Create folder named by commit id under the project first level folder prjt/out/.
    	 * @param commit : certain commit name.
    	 */
    	File out = new File("output");
    	if(!out.exists()) {
    		if(out.mkdir())	System.out.println("Init output folder sucessfully.");
    		else {
    			System.out.println("Init output folder failed.");
    			return false;
    		}
    	}
    	File folder = new File("output/" + commit);
    	if(!folder.exists()) {
    		if(folder.mkdir())
        		System.out.println("Init commit folder sucessfully.");
        	else	return false;	
    	}
    	return true;
    }
    
    public static boolean createCommitFile(String commit, String f) throws IOException {
    	File file = new File("output/"+ commit + "/" +f);
    	File fp = file.getParentFile();
    	if(!fp.exists()) fp.mkdirs();
    	if(file.createNewFile()) 
    		System.out.println("Create commit file " + f + " successfully.");
    	return true;
    }
}
