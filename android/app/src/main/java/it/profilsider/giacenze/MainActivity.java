package it.profilsider.giacenze;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/** App di sola consultazione: mostra la pagina delle giacenze e la aggiorna a ogni apertura. */
public class MainActivity extends Activity {
    private static final String PAGINA = "https://leodock.github.io/magazzino/consulta.html";
    private WebView web;
    private boolean primaApertura = true;

    @Override
    protected void onCreate(Bundle stato) {
        super.onCreate(stato);
        web = new WebView(this);
        setContentView(web);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);           // ricorda il codice di attivazione e gli ultimi dati
        s.setCacheMode(online() ? WebSettings.LOAD_DEFAULT : WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) {
                Uri u = r.getUrl();
                if (u.toString().startsWith("https://leodock.github.io/magazzino/")) return false;
                startActivity(new Intent(Intent.ACTION_VIEW, u));   // altri link nel browser
                return true;
            }
        });
        web.loadUrl(PAGINA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (primaApertura) { primaApertura = false; return; }
        // tornando sull'app: scarica di nuovo le giacenze
        web.getSettings().setCacheMode(online() ? WebSettings.LOAD_DEFAULT : WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web.evaluateJavascript("window.aggiornaDaApp && window.aggiornaDaApp()", null);
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) web.goBack(); else super.onBackPressed();
    }

    private boolean online() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return true;
        NetworkCapabilities c = cm.getNetworkCapabilities(cm.getActiveNetwork());
        return c != null && c.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }
}
