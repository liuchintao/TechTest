diff --git a/ImgAnnotator/src/main/resources/static/myJS/AnnotateStarter.js b/ImgAnnotator/src/main/resources/static/myJS/AnnotateStarter.js
new file mode 100644
index 0000000..0c6beb1
--- /dev/null
+++ b/ImgAnnotator/src/main/resources/static/myJS/AnnotateStarter.js
@@ -0,0 +1,16 @@
+// global -> 画法 -> 启动器
+
+function chooseSwitchOnBaseOnType() {
+    if(drawingType==="Total"){
+        total_actualSwitchOn();
+    }else if(drawingType==="Rectangle"){
+        rect_actualSwitchOn();
+    }else if(drawingType==="Polygon"){
+        poly_actualSwitchOn();
+    }else{
+        alert("回传的绘画方式不正确");
+    }
+}
+
+setGlobalParamAndUpdateJson();
+chooseSwitchOnBaseOnType();
\ No newline at end of file
