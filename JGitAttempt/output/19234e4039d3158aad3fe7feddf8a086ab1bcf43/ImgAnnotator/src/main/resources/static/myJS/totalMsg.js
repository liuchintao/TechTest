diff --git a/ImgAnnotator/src/main/resources/static/myJS/totalMsg.js b/ImgAnnotator/src/main/resources/static/myJS/totalMsg.js
new file mode 100644
index 0000000..0ddff5c
--- /dev/null
+++ b/ImgAnnotator/src/main/resources/static/myJS/totalMsg.js
@@ -0,0 +1,67 @@
+var total_inputMarkID = "markInput";
+
+var total_updateMarkBtnID = "updateMark";
+var total_getMarkMsgBtnID = "getMark";
+var total_commitBtn = "commit"
+
+//假设只有一个author,后面其实要根据author先删除并取出
+function total_updateTotalMsg(mark) {
+    globalImgMsg.noteTotal = [{author: "user", mark: mark, id: getIDByTime()}];      //这里是因为一个author所以才能这么做的
+}
+
+function total_sentJsonToServer() {
+    var keywordPostRect = firstTimeEdit? "postMark" : "Modify";
+
+    $.ajax({
+        type:'POST',
+        url:"http://localhost:8080/Worker/"+keywordPostRect,
+        data:JSON.stringify(globalImgMsg),
+        success:function(result){
+            console.log(result);
+        },
+        contentType:'application/json',
+        dataType:'json'
+    });
+}
+
+function total_setUpdateMarkBtn() {
+    $("#" + total_updateMarkBtnID).click(() => {
+        var mark = $("#" + total_inputMarkID).val();
+        total_updateTotalMsg(mark);
+    });
+}
+
+function total_setGetMarkMsgBtn() {
+    $("#" + total_getMarkMsgBtnID).click(() => {
+        var noteTotal = globalImgMsg.noteTotal;
+        if (noteTotal != null && noteTotal.length > 0) {
+            var mark = noteTotal.get(0).mark;
+            $("#" + total_inputMarkID).val(mark);
+        }
+    });
+}
+
+function total_setCommitBtn() {
+    $("#" + total_commitBtn).click(() => {
+        total_sentJsonToServer();
+    });
+}
+
+function total_getMsgFromJsonAndUpdateMark(imgURL, provider) {
+    if (!firstTimeEdit) {
+        $.getJSON("http://localhost:8080/Worker/Check", {imgURL: imgURL, provider: provider}, function (data) {
+            writeGlobalImgMsg(data);
+        });
+    }
+}
+
+function switchTotalOn() {
+    total_getMsgFromJsonAndUpdateMark();
+    total_setUpdateMarkBtn();
+    total_setGetMarkMsgBtn();
+    total_setCommitBtn();
+}
+
+function total_actualSwitchOn() {
+    setImgSizeAndSwitchTotalOn(global_imgURL,"canvasSaver",switchTotalOn);
+}
\ No newline at end of file
