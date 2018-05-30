package TechTest;

import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

import TechTest.helper.JGitHelper;

/**
 *  @author   :   Magister
 *  @fileName :   TechTest.ListBranchCommits.java
 *  
 *  2018年5月10日	上午9:42:43  
*/

public class ListBranchCommits {

	public static void main(String[] args) {
		String repo = "D:\\work\\GitMining\\Repos\\samples\\161250102_AlphaCat\\AlphaCat_Phase_II";
		try(Repository repository = JGitHelper.openExistingRepo(repo)){
			try(Git git = new Git(repository)){
				List<Ref> call = git.branchList().call();
				for(Ref ref: call) {
					System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
				}
				System.out.println("Now include remote branches.");
				call = git.branchList().setListMode(ListMode.ALL).call();
				for(Ref ref: call) {
					System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
				}
			} catch (GitAPIException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
