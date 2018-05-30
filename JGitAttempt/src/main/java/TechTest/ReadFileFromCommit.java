package TechTest;

import java.io.IOException;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

import TechTest.helper.JGitHelper;

/**
 * @author : Magister
 * @fileName : TechTest.ReadFileFromCommit.java
 * 
 *           2018年3月8日 上午12:32:07
 */

public class ReadFileFromCommit {
	public static void main(String[] args) throws IOException {
		try (Repository repository = JGitHelper.openJGitCookbookRepository()) {
			// System.out.println(Constants.HEAD);
			ObjectId lastCommitId = repository.resolve("80bad13db072dd38cde507de4843979bf6556ebd");

			// a RevWalk allows to walk over commits based on some filtering that is defined
			try (RevWalk revWalk = new RevWalk(repository)) {
				RevCommit commit = revWalk.parseCommit(ObjectId.fromString("80bad13db072dd38cde507de4843979bf6556ebd"));
				// and using commit's tree find the path
				RevTree tree = commit.getTree();
				System.out.println("Origin Commit: " + lastCommitId);
				System.out.println("Having tree: " + tree);

				// now try to find a specific file
				try (TreeWalk treeWalk = new TreeWalk(repository)) {
					treeWalk.addTree(tree);
					treeWalk.setRecursive(true);
//					 treeWalk.setFilter(PathFilter.create("README.md"));
//					 if (!treeWalk.next()) {
//					 throw new IllegalStateException("Did not find expected file 'README.md'");
//					 }

					while (treeWalk.next()) {
						if (treeWalk.isSubtree()) {
							System.out.println("dir " + treeWalk.getPathString());
							treeWalk.enterSubtree();
						} else {
							System.out.println("file " + treeWalk.getPathString());
						}
						 System.out.println(treeWalk.getPathString());
						 ObjectId objectId = treeWalk.getObjectId(0);
						 ObjectLoader loader = repository.open(objectId);

						// and then one can the loader to read the file
						loader.copyTo(System.out);
					}

				}

				revWalk.dispose();
			}
		}
	}
}
