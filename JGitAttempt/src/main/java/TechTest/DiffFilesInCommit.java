package TechTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import TechTest.helper.JGitHelper;

/**
 * @author : Magister
 * @fileName : TechTest.DiffFilesInCommit.java
 * 
 *           2018年3月8日 上午12:03:16
 */

public class DiffFilesInCommit {
	public static void main(String[] args) throws IOException, GitAPIException {
//		String repoName = "D:\\work\\GitMining\\Repos\\samples\\jgit-cookbook";
		String repoName = "D:\\work\\GitMining\\Repos\\Gitmining";
		try (Repository repo = JGitHelper.openExistingRepo(repoName)) {
			try (Git git = new Git(repo)) {
				listDiff(repo, git, "03589a029f21a16fc1d5d962a94f997221b06043", "89263b5a904b6a205e2627bd02e304622482331d");
//				listDiff(repo, git, "2e1d65e4cf6c5e267e109aa20fd68ae119fa5ec9", "a837a9c5563ddb01ebd121f85d9d64cf3c1823cb");
//				listDiff(repo, git, "a837a9c5563ddb01ebd121f85d9d64cf3c1823cb", "2e1d65e4cf6c5e267e109aa20fd68ae119fa5ec9");
			}
		}
	}
	private static void listDiff(Repository repo, Git git, String oldC, String newC) throws GitAPIException, IOException {
		final List<DiffEntry> diffs = git.diff().setOldTree(prepareTreeParser(repo, oldC))
				.setNewTree(prepareTreeParser(repo, newC)).call();
		System.out.println("Found: " + diffs.size() + " differences");
        for (DiffEntry diff : diffs) {
            System.out.println("Diff: " + diff.getChangeType() + ": " +
                    (diff.getOldPath().equals(diff.getNewPath()) ? diff.getNewPath() : diff.getOldPath() + " -> " + diff.getNewPath()));
        }
	}

	private static AbstractTreeIterator prepareTreeParser(Repository repo, String cId) throws IOException {
		try (RevWalk walk = new RevWalk(repo)) {
			RevCommit commit = walk.parseCommit(repo.resolve(cId));
			RevTree tree = walk.parseTree(commit.getTree().getId());
			CanonicalTreeParser treeParser = new CanonicalTreeParser();
			try (ObjectReader reader = repo.newObjectReader()) {
				treeParser.reset(reader, tree.getId());
			}
			walk.dispose();
			return treeParser;
		}
	}
}
