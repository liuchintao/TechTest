package TechTest;

import java.io.IOException;

import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import TechTest.helper.JGitHelper;

/**
 *  @author   :   Magister
 *  @fileName :   TechTest.GetCommitMessage.java
 *  
 *  2018年3月1日	下午2:43:33  
*/

public class GetCommitMessage {
	public static void main(String[] args) throws IOException {
		try(Repository repo = JGitHelper.openExistingRepo("D:\\work\\GitMining\\Repos\\samples\\AlphaCat_Phase_II")){
			Ref head = repo.findRef("refs/heads/master");
			System.out.println("Found head: " + head);
//			int num = 0;
			try(RevWalk walk = new RevWalk(repo)){
				RevCommit commit = walk.parseCommit(head.getObjectId());
				System.out.println("\nCommit-Message: " + commit.getFullMessage());
				walk.dispose();
			}
		}
	}
}
