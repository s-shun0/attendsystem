// footer.js
// 共通のフッターを各ページに動的に読み込むためのスクリプト

// DOMの読み込みが完了したら処理を実行
window.addEventListener('DOMContentLoaded', () => {
  // fetch関数で共通のfooter.htmlファイルを取得
  fetch('/attendsystem/main/common/footer.jsp')
    // レスポンスの本文（HTMLテキスト）を取得
    .then(res => res.text())
    // 取得したHTMLを、id="footer" の要素に挿入
    .then(data => {
      document.getElementById('footer').innerHTML = data;
    })
    // エラーが発生した場合は、コンソールにエラーメッセージを表示
    .catch(error => {
      console.error('Footer読み込みエラー:', error);
    });
});
