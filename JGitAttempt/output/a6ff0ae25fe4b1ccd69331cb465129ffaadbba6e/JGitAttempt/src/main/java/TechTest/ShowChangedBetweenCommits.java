diff --git a/JGitAttempt/src/main/java/TechTest/ShowChangedBetweenCommits.java b/JGitAttempt/src/main/java/TechTest/ShowChangedBetweenCommits.java
index c1ca497..af2226c 100644
--- a/JGitAttempt/src/main/java/TechTest/ShowChangedBetweenCommits.java
+++ b/JGitAttempt/src/main/java/TechTest/ShowChangedBetweenCommits.java
@@ -6,6 +6,7 @@
 import org.eclipse.jgit.api.Git;
 import org.eclipse.jgit.api.errors.GitAPIException;
 import org.eclipse.jgit.diff.DiffEntry;
+import org.eclipse.jgit.diff.DiffFormatter;
 import org.eclipse.jgit.lib.ObjectId;
 import org.eclipse.jgit.lib.ObjectReader;
 import org.eclipse.jgit.lib.Repository;
@@ -39,7 +40,11 @@
 			try(Git git = new Git(repo)){
 				List<DiffEntry> diffs = git.diff().setNewTree(newTreeIter).setOldTree(oldTreeIter).call();
 				for(DiffEntry entry: diffs) {
-					System.out.println("Entry" + entry);
+					System.out.println("Entry" + entry + ", from: " + entry.getOldId() + entry.getNewId());
+					try(DiffFormatter formatter = new DiffFormatter(System.out)){
+						formatter.setRepository(repo);
+						formatter.format(entry);
+					}
 				}
 			}
 		}
