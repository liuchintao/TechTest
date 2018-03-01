package TechTest;

import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * @author : Magister
 * @fileName : TechTest.OpenExistingRepo.java
 * 
 *           2018年2月23日 下午6:24:01
 */

public class OpenExistingRepo {

	public static void main(String[] args) throws IOException, NoHeadException, GitAPIException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		try (Repository repository = builder.readEnvironment().findGitDir().build();) {
			try (Git git = new Git(repository)) {
				Iterable<RevCommit> logs = git.log().call();
				int count = 0;
				for (RevCommit rev : logs) {
					System.out.println("Commit: " + rev + ", name: " + rev.getName() + ", id: " + rev.getId().getName()
							+ "Full message: " + rev.getFullMessage() + "Author: " + rev.getAuthorIdent());
					count++;
				}
				System.out.println("Had " + count + " commits overall on current branch");

//				logs = git.log().add(repository.resolve("remotes/origin/testbranch")).call();
//				count = 0;
//				for (RevCommit rev : logs) {
//					System.out.println(
//							"Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
//					count++;
//				}
//				System.out.println("Had " + count + " commits overall on test-branch");

//				logs = git.log().not(repository.resolve("master")).add(repository.resolve("remotes/origin/testbranch"))
//						.call();
//				count = 0;
//				for (RevCommit rev : logs) {
//					System.out.println(
//							"Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
//					count++;
//				}
//				System.out.println("Had " + count + " commits only on test-branch");

				logs = git.log().all().call();
				count = 0;
				for (RevCommit rev : logs) {
					 System.out.println("Commit: " + rev +  rev.getTree());
					count++;
				}
				System.out.println("Had " + count + " commits overall in repository");

				logs = git.log()
						// for all log.all()
						.addPath("README.md").call();
				count = 0;
				for (RevCommit rev : logs) {
					// System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id:
					// " + rev.getId().getName() */);
					count++;
				}
				System.out.println("Had " + count + " commits on README.md");

				logs = git.log()
						// for all log.all()
						.addPath("JGitAttempt/.classpath").call();
				count = 0;
				for (RevCommit rev : logs) {
//					 System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id:
//					 " + rev.getId().getName() */);
					count++;
				}
				System.out.println("Had " + count + " commits on pom.xml");
			}
		}
	}

}
