diff --git a/ImgAnnotator/src/main/java/cn/joker/utils/copyFile.java b/ImgAnnotator/src/main/java/cn/joker/utils/copyFile.java
new file mode 100644
index 0000000..332a0ff
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/utils/copyFile.java
@@ -0,0 +1,30 @@
+package cn.joker.utils;
+
+import java.io.BufferedReader;
+import java.io.BufferedWriter;
+import java.io.InputStreamReader;
+import java.io.OutputStreamWriter;
+
+/**
+ * @author: pis
+ * @description: good good study
+ * @date: create in 8:59 2018/3/20
+ */
+public class copyFile {
+    public static boolean copy(InputStreamReader inputStreamReader, OutputStreamWriter outputStreamWriter) {
+        try {
+
+            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
+            BufferedReader reader = new BufferedReader(inputStreamReader);
+            String str;
+            while ((str = reader.readLine()) != null) {
+                writer.write(str);
+            }
+
+            return true;
+        } catch (Exception e) {
+            return false;
+        }
+
+    }
+}
