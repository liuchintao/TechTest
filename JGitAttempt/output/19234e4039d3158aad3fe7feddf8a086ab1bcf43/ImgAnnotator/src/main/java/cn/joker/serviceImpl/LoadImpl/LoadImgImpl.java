diff --git a/ImgAnnotator/src/main/java/cn/joker/serviceImpl/LoadImpl/LoadImgImpl.java b/ImgAnnotator/src/main/java/cn/joker/serviceImpl/LoadImpl/LoadImgImpl.java
new file mode 100644
index 0000000..a0ebbfa
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/serviceImpl/LoadImpl/LoadImgImpl.java
@@ -0,0 +1,82 @@
+package cn.joker.serviceImpl.LoadImpl;
+
+import cn.joker.service.Load.LoadImg;
+import cn.joker.utils.FilePathUtil;
+
+import java.io.File;
+import java.util.List;
+
+/**
+ * @author: pis
+ * @description: good good study
+ * @date: create in 10:16 2018/3/20
+ */
+public class LoadImgImpl implements LoadImg {
+    public int loadImg(int imgNum, List<String> imgURLs, String path, int requireNum) {
+        path = FilePathUtil.getRealFilePath(path);
+        File resources = new File(path);
+        File files[] = resources.listFiles();
+        if (imgNum >= requireNum) {
+            return imgNum;
+        }
+        assert files != null;
+        for (File file : files) {
+            if (imgNum >= requireNum) {
+                return imgNum;
+            }
+            if (file.isDirectory()) {
+                imgNum = loadImg(imgNum, imgURLs, file.toString(), requireNum);
+            }
+            if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) {
+                imgURLs.add(file.getPath().split("static")[1]);
+                imgNum++;
+            }
+        }
+        return imgNum;
+    }
+
+    public int loadImg(int imgNum, List<String> imgURLs, String path) {
+        path = FilePathUtil.getRealFilePath(path);
+        File resources = new File(path);
+        File files[] = resources.listFiles();
+        assert files != null;
+        for (File file : files) {
+            if (file.isDirectory()) {
+                imgNum = loadImg(imgNum, imgURLs, file.toString());
+            }
+            if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) {
+                imgURLs.add(file.getPath().split("static")[1]);
+                imgNum++;
+            }
+        }
+        return imgNum;
+    }
+
+    @Override
+    public int loadImg(int imgNum, List<String> imgURLs, String path, int requireNum, int passNum) {
+        path = FilePathUtil.getRealFilePath(path);
+        File resources = new File(path);
+        File files[] = resources.listFiles();
+        if (imgNum >= requireNum) {
+            return imgNum;
+        }
+        assert files != null;
+        for (File file : files) {
+            if (imgNum >= requireNum) {
+                return imgNum;
+            }
+            if (file.isDirectory()) {
+                imgNum = loadImg(imgNum, imgURLs, file.toString(), requireNum, passNum);
+            }
+            if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) {
+                if (passNum > 0) {
+                    passNum--;
+                } else {
+                    imgURLs.add(file.getPath().split("static")[1]);
+                    imgNum++;
+                }
+            }
+        }
+        return imgNum;
+    }
+}
