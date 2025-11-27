<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出席用QRコード</title>
<script>
  function updateQRCode() {
    const qr = document.getElementById("qr");
    qr.src = "QRcodeServlet?time=" + new Date().getTime(); // キャッシュ防止
  }
  setInterval(updateQRCode, 10000); // 10秒ごとに更新
</script>
</head>
<body>
  <h2>出席用QRコード（10秒ごとに更新）</h2>
  <img id="qr" src="QRcodeServlet" alt="QRコード">
</body>
</html>
