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
		String cid1 = "19d9044cee6ad39a3e2f93fda056189d7447ac04";
		String oldc = "904340848c4749421302e7b9bdfaa37fc5c6e951";
		String newc = "19234e4039d3158aad3fe7feddf8a086ab1bcf43";
//		String origin="01b46611df4a32e90e39c4a0b5a3880ab82e35dc";
//		String oriold="01b46611df4a32e90e39c4a0b5a3880ab82e35dc^";
		try(Repository repo = JGitHelper.openExistingRepo("D:\\work\\GitMining\\Repos\\samples\\161250004_Joker\\Project_Phase_I")){
			AbstractTreeIterator oldTreeParser = prepareTreeParser(repo, oldc);
			AbstractTreeIterator newTreeParser = prepareTreeParser(repo, newc);
			AbstractTreeIterator c1TreeParser = prepareTreeParser(repo, cid1);
//			AbstractTreeIterator oriTreeParser = prepareTreeParser(repo, origin);
//			AbstractTreeIterator ordTreeParser = prepareTreeParser(repo, origin);
//			JGitHelper.createNewFolder(origin);
//			try(Git git = new Git(repo)){
//				List<DiffEntry> diffs = git.diff().setNewTree(oriTreeParser).call();
//				for(DiffEntry entry: diffs) {
//					String pth = entry.getNewPath();
//					File out = JGitHelper.createCommitFile(newc, pth);
//					FileOutputStream output = new FileOutputStream(out);
//					System.out.println("Entry: " + entry + ", from: " + entry.getOldId() + ", to: " + entry.getNewId());
//					try(DiffFormatter formatter = new DiffFormatter(output)){
//						formatter.setRepository(repo);
//						formatter.format(entry);
//					}
//				}
//			}
//			JGitHelper.createNewFolder(newc);
			try(Git git = new Git(repo)){
				List<DiffEntry> diffs = git.diff().
						setOldTree(oldTreeParser).
						setNewTree(newTreeParser).
//						setPathFilter(PathSuffixFilter.create(".java")).
						call();
				List<DiffEntry> diffs1 = git.diff().
						setOldTree(newTreeParser).
						setNewTree(c1TreeParser).
//						setPathFilter(PathSuffixFilter.create(".java")).
						call();
				int count = 0;
				for(DiffEntry entry: diffs) {
//					String pth = entry.getNewPath();
//					File out = JGitHelper.createCommitFile(newc, pth);
//					FileOutputStream output = new FileOutputStream(out);
					System.out.println("Entry: " + entry + ", from: " + entry.getOldId() + ", to: " + entry.getNewId());
//					try(DiffFormatter formatter = new DiffFormatter(output)){
//						formatter.setRepository(repo);
//						formatter.format(entry);
//					}
					count++;
				}
				System.out.println(count);
			}
		}
		System.out.println(" End!");
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
