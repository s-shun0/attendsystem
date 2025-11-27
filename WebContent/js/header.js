// header.js
// 共通のヘッダーを各ページに動的に読み込むためのスクリプト

// DOMの読み込みが完了したら処理を実行
window.addEventListener('DOMContentLoaded', () => {
  // fetch関数で共通のheader.htmlファイルを取得
   fetch('/attendsystem/main/common/header.jsp')
    // レスポンスの本文（HTMLテキスト）を取得
    .then(res => res.text())
    // 取得したHTMLを、id="header" の要素に挿入
    .then(data => {
      document.getElementById('header').innerHTML = data;

      // ハンバーガーメニューのトグル処理
      // メニューアイコン（.hamburger）がクリックされた時に実行
      $('.hamburger').click(function () {
        // アイコンの見た目を切り替え
        $(this).toggleClass('active');
        // メニューの開閉を切り替え
        $('.menu').toggleClass('open');
      });
    })
    .catch(err => console.error('ヘッダー読み込みエラー:', err));
});