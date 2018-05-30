diff --git a/ImgAnnotator/src/main/java/cn/joker/Model/ImgNoteEntity.java b/ImgAnnotator/src/main/java/cn/joker/Model/ImgNoteEntity.java
new file mode 100644
index 0000000..33f8076
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/Model/ImgNoteEntity.java
@@ -0,0 +1,79 @@
+package cn.joker.Model;
+
+/**
+ * @author: pis
+ * @description: good good study
+ * @date: create in 18:59 2018/3/17
+ */
+public class ImgNoteEntity {
+    private String type;
+    private String isMarked;
+
+    public String getNoteRectangle() {
+        return noteRectangle;
+    }
+
+    public void setNoteRectangle(String noteRectangle) {
+        this.noteRectangle = noteRectangle;
+    }
+
+    public String getNotePolygon() {
+        return notePolygon;
+    }
+
+    public void setNotePolygon(String notePolygon) {
+        this.notePolygon = notePolygon;
+    }
+
+    public String getNoteTotal() {
+        return noteTotal;
+    }
+
+    public void setNoteTotal(String noteTotal) {
+        this.noteTotal = noteTotal;
+    }
+
+    private String noteRectangle;
+    private String notePolygon;
+    private String noteTotal;
+
+
+    public String getType() {
+        return type;
+    }
+
+    public void setType(String type) {
+        this.type = type;
+    }
+
+    public String getIsMarked() {
+        return isMarked;
+    }
+
+    public void setIsMarked(String isMarked) {
+        this.isMarked = isMarked;
+    }
+
+
+    public String getImgURL() {
+        return imgURL;
+    }
+
+    public void setImgURL(String imgURL) {
+        this.imgURL = imgURL;
+    }
+
+    public String getProvider() {
+        return provider;
+    }
+
+    public void setProvider(String provider) {
+        this.provider = provider;
+    }
+
+
+    private String imgURL;
+    private String provider;
+
+
+}
