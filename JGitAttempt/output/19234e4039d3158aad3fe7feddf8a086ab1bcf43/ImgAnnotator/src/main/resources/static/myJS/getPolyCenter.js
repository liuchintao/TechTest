diff --git a/ImgAnnotator/src/main/resources/static/myJS/getPolyCenter.js b/ImgAnnotator/src/main/resources/static/myJS/getPolyCenter.js
new file mode 100644
index 0000000..d1f2598
--- /dev/null
+++ b/ImgAnnotator/src/main/resources/static/myJS/getPolyCenter.js
@@ -0,0 +1,25 @@
+function polygonTempArea(p0, p1, p2)
+{
+    var area = p0.x * p1.y + p1.x * p2.y + p2.x * p0.y - p1.x * p0.y - p2.x * p1.y - p0.x * p2.y;
+    return area / 2 ;
+}
+
+//line 249 计算polygon的质心
+function getPolygonAreaCenter(points) {
+    var sum_x = 0;
+    var sum_y = 0;
+    var sum_area = 0;
+    var p1 = points[1];
+    debugger;
+    for (var i = 2; i < points.length; i++) {
+        var p2=points[i];
+        var area = polygonTempArea(points[0],p1,p2) ;
+        sum_area += area ;
+        sum_x += (points[0].x + p1.x + p2.x) * area;
+        sum_y += (points[0].y + p1.y + p2.y) * area;
+        p1 = p2 ;
+    }
+    var xx = sum_x / sum_area / 3;
+    var yy = sum_y / sum_area / 3;
+    return {x:xx, y:yy};
+}
