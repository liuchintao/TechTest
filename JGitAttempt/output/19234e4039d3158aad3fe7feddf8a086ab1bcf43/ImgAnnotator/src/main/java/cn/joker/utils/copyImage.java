diff --git a/ImgAnnotator/src/main/java/cn/joker/utils/copyImage.java b/ImgAnnotator/src/main/java/cn/joker/utils/copyImage.java
new file mode 100644
index 0000000..86530e0
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/utils/copyImage.java
@@ -0,0 +1,32 @@
+package cn.joker.utils;
+
+import java.io.File;
+import java.io.FileInputStream;
+import java.io.FileOutputStream;
+
+/**
+ * @author: pis
+ * @description: good good study
+ * @date: create in 9:19 2018/3/20
+ */
+public class copyImage {
+    public static boolean copy(File imgFrom, File imgTo) {
+        try {
+
+            FileInputStream fileInputStream = new FileInputStream(imgFrom);
+            FileOutputStream fileOutputStream = new FileOutputStream(imgTo);
+            byte[] read = new byte[1024];
+            int len;
+            while ((len = fileInputStream.read(read)) != -1) {
+                fileOutputStream.write(read, 0, len);
+            }
+            fileInputStream.close();
+            fileOutputStream.flush();
+            fileOutputStream.close();
+
+        } catch (Exception e) {
+            return false;
+        }
+        return true;
+    }
+}
