package com.example.webviewtest;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	private WebView webView;
	private ProgressDialog dialog;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		// �������ȼ��ػ���
		webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		// �O�ô��_��ʽ
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// ���ݴ���Ĳ�����ȥ����ͦ�µ���ҳ
				view.loadUrl(url);
				// ��ʾ��ǰwebView���Դ��������ҳ�����󣬲��ý���ϵͳ�����
				return true;
			}
		});
		// ������ҳ��תʱ��Ľ���
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				if (newProgress == 100) {
					// �������
					closeProgress();
				} else {
					// ���ڼ���
					openDialog(newProgress);
				}
			}

			private void closeProgress() {
				// TODO Auto-generated method stub
				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}

			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if (dialog == null) {
					dialog = new ProgressDialog(MainActivity.this);
					dialog.setTitle("���ڼ���");
					dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					dialog.setProgress(newProgress);
					dialog.show();
				} else {
					dialog.setProgress(newProgress);
				}
			}

		});
		webView.loadUrl("http://www.baidu.com");
	}

	// �������߼�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView.canGoBack()) {
				webView.goBack();
				return true;
			} else {
				// �˳�
				System.exit(0);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
