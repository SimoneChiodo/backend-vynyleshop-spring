# Progetto Java: Backend VynyleShop

### Repository frontend: [frontend-vynyleshop-vite](https://github.com/SimoneChiodo/frontend-vynyleshop-vite.git)

## Descrizione  

**VynyleShop** è un sito web dedicato alla vendita di dischi in vinile, con un backend progettato per gestire in modo sicuro i dati e le logiche applicative.  
È stato sviluppato in **Spring Boot**, con autenticazione e autorizzazione gestite tramite **Spring Security** e un database SQL per la persistenza dei dati.  

## ⚙️ Funzionalità principali  
- **Autenticazione e ruoli**  
  - Login protetto con **Spring Security**  
  - Accessi differenziati tra **admin** e **user**  
- **Homepage**  
  - Link di navigazione per le pagine: Vinyl list, Artist list e le pagine di creazione (riservate agli admin)  
- **Navbar**  
  - Barra di ricerca per trovare vinili per nome  
  - Sezione con dati utente, ruolo e pulsante di logout  
- **Vinyl list**  
  - Visualizzazione tabellare con tutti i vinili salvati  
  - Pulsanti per visualizzare dettagli, modificare o eliminare (con conferma via modal)  
  - Pulsante per aggiungere un nuovo vinile (solo admin)  
- **Artist list**  
  - Struttura analoga a quella dei vinili con CRUD completo  
- **Pagine dettaglio**  
  - **Vinile**: mostra tutti i dati con galleria immagini, pulsanti di modifica ed eliminazione  
  - **Artista**: dati completi con immagine e discografia collegata  
- **Pagine creazione e modifica**  
  - Form dedicati per inserire o aggiornare vinili e artisti  

## 🛠️ Tecnologie utilizzate  
- **Spring Boot**: scelto per gestire API REST e logica applicativa in modo scalabile.  
- **Spring Security**: scelto per implementare autenticazione sicura e autorizzazione basata sui ruoli.  
- **MySQL + SQL Workbench**: scelti per la gestione dei dati strutturati di vinili e artisti.  
- **Bootstrap**: scelto per mantenere coerenza visiva anche lato backend.  
