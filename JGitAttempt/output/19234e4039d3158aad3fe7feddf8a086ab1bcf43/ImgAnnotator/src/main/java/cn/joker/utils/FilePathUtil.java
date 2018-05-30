diff --git a/ImgAnnotator/src/main/java/cn/joker/utils/FilePathUtil.java b/ImgAnnotator/src/main/java/cn/joker/utils/FilePathUtil.java
new file mode 100644
index 0000000..fb5409e
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/utils/FilePathUtil.java
@@ -0,0 +1,18 @@
+package cn.joker.utils;
+
+/**
+ * @author: pis
+ * @description: 解决路径问题
+ * @date: create in 12:16 2018/3/18
+ */
+public class FilePathUtil {
+    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
+
+    public static String getRealFilePath(String path) {
+        return path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
+    }
+
+    public static String getHttpURLPath(String path) {
+        return path.replace("\\", "/");
+    }
+}
\ No newline at end of file
