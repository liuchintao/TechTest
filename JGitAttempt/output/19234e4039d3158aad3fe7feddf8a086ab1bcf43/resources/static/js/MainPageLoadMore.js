diff --git a/resources/static/js/MainPageLoadMore.js b/resources/static/js/MainPageLoadMore.js
new file mode 100644
index 0000000..0466817
--- /dev/null
+++ b/resources/static/js/MainPageLoadMore.js
@@ -0,0 +1,25 @@
+var loaderCounter = 0, hasFinished = false;
+const loadSize = 20;
+
+$('#js-load-more').on('click', function () {
+    getRemoteData();
+});
+
+
+function getRemoteData() {
+    $.get('/Worker/loadImage?dataSetNum=1&offset=' + loaderCounter, function (data) {
+        if (data.ImgNum < loadSize)
+            $('#js-load-more').hide();
+
+        let result = "";
+        for (let i = 0; i < data.ImgNum; i++) {
+            result += "<a href=" + data.ImgURL[i] + "><h3>" + data.ImgURL[i] + "</h3><br><img src=" + data.ImgURL[i] + "></a>";
+        }
+
+        $('#js-load-more').before($(result));
+    });
+
+    loaderCounter++;
+}
+
+getRemoteData();
\ No newline at end of file
