package TechTest;

import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.annotations.NonNull;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffConfig;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.FollowFilter;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import TechTest.helper.JGitHelper;

/**
 * @author : Magister
 * @fileName : TechTest.DiffRenamed.java
 * 
 *           2018年5月30日 下午2:09:08
 */

public class DiffRenamed {
	public static void main(String[] args) {
//		String repo = "D:\\work\\GitMining\\Repos\\samples\\jgit-cookbook";
		String repo = "D:\\work\\GitMining\\Repos\\Gitmining";
		try (Repository repository = JGitHelper.openExistingRepo(repo)) {
			String oldCommit = "03589a029f21a16fc1d5d962a94f997221b06043";
			String newCommit = "89263b5a904b6a205e2627bd02e304622482331d";
			String path = "src/main/java/cn/nju/iSE/GitMining/common/util/Constant.java";
//			runDiff(repository, "2e1d65e4cf6c5e267e109aa20fd68ae119fa5ec9", "5a10bd6ee431e362facb03cfe763b9a3d9dfd02d",
//					"README.md");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			// try the reverse as well
//			runDiff(repository, "5a10bd6ee431e362facb03cfe763b9a3d9dfd02d", "2e1d65e4cf6c5e267e109aa20fd68ae119fa5ec9",
//					"README.md");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			// caret allows to specify "the previous commit"
//			runDiff(repository, "7b2e6193a39726510ed9d0f66a779665d0e4ce23^", "7b2e6193a39726510ed9d0f66a779665d0e4ce23",
//					"build.gradle");
			runDiff(repository, oldCommit, newCommit, path);
		} catch (IOException | GitAPIException e) {
			e.printStackTrace();
		}
	}

	private static void runDiff(Repository repo, String oldCommit, String newCommit, String path)
			throws IOException, GitAPIException {
		DiffEntry diff = diffFile(repo, oldCommit, newCommit, path);
		System.out.println("Show diff of: " + path 
				+ " \nChange Type: " + diff.getChangeType()
				+ " \nnew path: " + diff.getNewPath()
				+ " \nold path: " + diff.getOldPath());
		try (DiffFormatter formatter = new DiffFormatter(System.out)) {
			formatter.setRepository(repo);
			formatter.format(diff);
		}
	}

	private static @NonNull DiffEntry diffFile(Repository repo, String oldCommit, String newCommit, String path)
			throws GitAPIException, RevisionSyntaxException, MissingObjectException, IncorrectObjectTypeException,
			AmbiguousObjectException, IOException {
		Config config = new Config();
		config.setBoolean("diff", null, "renames", true);
		DiffConfig diffConfig = config.get(DiffConfig.KEY);
		try (Git git = new Git(repo)) {
			List<DiffEntry> diffList = git.diff().setOldTree(prepareTreeParser(repo, oldCommit))
					.setNewTree(prepareTreeParser(repo, newCommit)).
					setPathFilter(FollowFilter.create(path, diffConfig)).call();
			if (diffList.size() == 0)
                return null;
            if (diffList.size() > 1)
                throw new RuntimeException("invalid diff");
            return diffList.get(0);
		}
	}

	private static AbstractTreeIterator prepareTreeParser(Repository repository, String objectId)
			throws RevisionSyntaxException, MissingObjectException, IncorrectObjectTypeException,
			AmbiguousObjectException, IOException {
		try (RevWalk walk = new RevWalk(repository)) {
			RevCommit commit = walk.parseCommit(repository.resolve(objectId));
			RevTree tree = walk.parseTree(commit.getTree().getId());

			CanonicalTreeParser treeParser = new CanonicalTreeParser();
			try (ObjectReader reader = repository.newObjectReader()) {
				treeParser.reset(reader, tree.getId());
			}
			walk.dispose();
			return treeParser;
		}
	}
}
