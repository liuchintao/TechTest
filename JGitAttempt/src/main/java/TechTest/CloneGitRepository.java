package TechTest;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * @author : Magister
 * @fileName : TechTest.CloneGitRepository.java
 * 
 *           2018年3月22日 下午4:33:32
 */

public class CloneGitRepository {

	private static final String REMOTE_URL = "http://114.215.188.21/ljt/GitStudy.git";
	private static final String BASE_FOLDER = "D:\\work\\GitMining\\Repos\\samples";

	public static void main(String[] args) throws IOException, GitAPIException {
		// prepare a new folder for the cloned repository
		String repo = REMOTE_URL;
		String regEx = "/[A-Za-z0-9_-]*/[A-Za-z0-9_-]*\\.git$";
		Pattern ptn = Pattern.compile(regEx);
		Matcher mtch = ptn.matcher(repo);
		String newFolder = "";
		if(mtch.find())
			newFolder = mtch.group(0);
		newFolder = newFolder.substring(0, newFolder.lastIndexOf("."));
		System.out.println(newFolder);
		File localPath = new File(BASE_FOLDER + newFolder);
		if(!localPath.exists()) {
    		if(localPath.mkdir())	System.out.println("Init output folder sucessfully.");
    		else {
    			System.out.println("Init output folder failed.");
    		}
    	}

		// then clone
		System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
		CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider("ljt", "git#ljt16");
		try (Git result = Git
				.cloneRepository()
				.setURI(REMOTE_URL)
				.setDirectory(localPath)
				.setCredentialsProvider(credentialsProvider)
				.call()) {
			// Note: the call() returns an opened repository already which needs to be
			// closed to avoid file handle leaks!
			System.out.println("Having repository: " + result.getRepository().getDirectory());
		}
	}
}
