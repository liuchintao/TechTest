package TechTest;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 *  @author   :   Magister
 *  @fileName :   TechTest.InitRepo.java
 *  
 *  2018年2月23日	下午6:00:36  
*/

public class InitRepo {

	public static void main(String[] args) throws IOException, IllegalStateException, GitAPIException {
		File dir = File.createTempFile("GitInit", ".test");
		if(!dir.delete()) {
			throw new IOException("Could not delete file " + dir);
		}
		
		// The Git-object has a static method to initialize a new repository.
		try(Git git = Git.init().setDirectory(dir).call()){
			 System.out.println("Created a new repository at " + git.getRepository().getDirectory());
		}
		
		dir = File.createTempFile("repoinit", ".test");
		if(!dir.delete()) {
			throw new IOException("Could not delete file " + dir);
		}
		try(Repository repo = FileRepositoryBuilder.create(new File(dir.getAbsolutePath(),".git"))){
			System.out.println("Created a new repository at " + repo.getDirectory());
		}
	}

}
