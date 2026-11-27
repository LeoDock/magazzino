# Magazzino Tubolari

App web per il carico e scarico dei pacchi di tubolari tramite QR code.

- **Scansiona**: inquadra il QR del pacco e registra carico o scarico (funziona anche senza rete: i movimenti partono appena torna la connessione)
- **Cruscotto**: profili più scaricati, carichi/scarichi per mese, giacenza, articoli sottoscorta e previsione di esaurimento
- **Giacenza**: elenco cercabile del magazzino
- **Etichette**: crea e stampa le etichette QR: adesive 70×37 mm (24 per A4) o cartellini da ritagliare con foro per il gancio 100×56 mm (10 per A4)

I dati stanno in un foglio Google collegato tramite Apps Script: indirizzo e chiave si inseriscono in **Opzioni** e restano salvati solo sul telefono.

## App Android "Giacenze Tubolari" (sola consultazione)

- Pagina: `consulta.html` (giacenze con lo schema del modulo cartaceo, si aggiorna a ogni apertura)
- Progetto Android in `android/` (WebView); l'APK viene compilato da GitHub Actions e pubblicato nella release **apk**:
  https://github.com/LeoDock/magazzino/releases/download/apk/GiacenzeTubolari.apk
- Al primo avvio chiede il codice di attivazione (MT1…), creato dall'app principale in Opzioni.
