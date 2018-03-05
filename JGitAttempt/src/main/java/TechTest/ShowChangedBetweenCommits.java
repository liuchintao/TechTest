package TechTest;

import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import TechTest.helper.JGitHelper;

/**
 *  @author   :   Magister
 *  @fileName :   TechTest.ShowChangedBetweenCommits.java
 *  
 *  2018年3月1日	下午4:09:22  
*/

public class ShowChangedBetweenCommits {

	public static void main(String[] args) throws IOException, GitAPIException {
		Repository repo = JGitHelper.openJGitCookbookRepository();
		
		ObjectId oldHead = repo.resolve("HEAD^^^^{tree}");
		ObjectId head = repo.resolve("HEAD^{tree}");
		
		System.out.println("Printing diff between tree: " + oldHead + " and " + head);
		
		//prepare two iterator to compute diff between
		try(ObjectReader reader = repo.newObjectReader()){
			CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
			oldTreeIter.reset(reader, oldHead);
			CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
			newTreeIter.reset(reader, head);
			try(Git git = new Git(repo)){
				List<DiffEntry> diffs = git.diff().setNewTree(newTreeIter).setOldTree(oldTreeIter).call();
				for(DiffEntry entry: diffs) {
					System.out.println("Entry" + entry + ", from: " + entry.getOldId() + entry.getNewId());
					try(DiffFormatter formatter = new DiffFormatter(System.out)){
						formatter.setRepository(repo);
						formatter.format(entry);
					}
				}
			}
		}
	}
}
