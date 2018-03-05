package TechTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import TechTest.helper.JGitHelper;

/**
 *  @author   :   Magister
 *  @fileName :   TechTest.ShowFileChanges.java
 *  
 *  2018年3月5日	下午3:10:25  
*/

public class ShowFileChanges {

	public static void main(String[] args) throws IOException, GitAPIException {
		String oldc = "7d242b852af9fd48b16229024887937aef677a09";
		String newc = "a6ff0ae25fe4b1ccd69331cb465129ffaadbba6e";
		try(Repository repo = JGitHelper.openJGitCookbookRepository()){
			AbstractTreeIterator oldTreeParser = prepareTreeParser(repo, oldc);
			AbstractTreeIterator newTreeParser = prepareTreeParser(repo, newc);
			JGitHelper.createNewFolder(newc);
			try(Git git = new Git(repo)){
				List<DiffEntry> diffs = git.diff().
						setOldTree(oldTreeParser).
						setNewTree(newTreeParser).
//						setPathFilter(PathSuffixFilter.create(".java")).
						call();
				for(DiffEntry entry: diffs) {
					String pth = entry.getNewPath();
					File out = JGitHelper.createCommitFile(newc, pth);
					FileOutputStream output = new FileOutputStream(out);
					System.out.println("Entry: " + entry + ", from: " + entry.getOldId() + ", to: " + entry.getNewId());
					try(DiffFormatter formatter = new DiffFormatter(output)){
						formatter.setRepository(repo);
						formatter.format(entry);
					}
				}
			}
		}
		System.out.println("End!");
	}
	
	private static AbstractTreeIterator prepareTreeParser(Repository repo, String cid) throws IOException {
		try(RevWalk walk = new RevWalk(repo)){
			RevCommit commit = walk.parseCommit(ObjectId.fromString(cid));
			RevTree tree = walk.parseTree(commit.getTree().getId());
			
			CanonicalTreeParser treeParser = new CanonicalTreeParser();
			try(ObjectReader reader = repo.newObjectReader()){
				treeParser.reset(reader, tree.getId());
			}
			walk.dispose();
			return treeParser;
		}
	}

}
