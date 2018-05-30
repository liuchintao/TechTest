diff --git a/ImgAnnotator/src/main/java/cn/joker/service/Load/LoadImg.java b/ImgAnnotator/src/main/java/cn/joker/service/Load/LoadImg.java
new file mode 100644
index 0000000..a42ffe2
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/service/Load/LoadImg.java
@@ -0,0 +1,16 @@
+package cn.joker.service.Load;
+
+import java.util.List;
+
+/**
+ * @author: pis
+ * @description: good good study
+ * @date: create in 10:17 2018/3/20
+ */
+public interface LoadImg {
+    int loadImg(int imgNum, List<String> imgURLs, String path, int requireNum);
+
+    int loadImg(int imgNum, List<String> imgURLs, String path);
+
+    int loadImg(int imgNum, List<String> imgURLs, String path, int requireNum, int passNum);
+}
