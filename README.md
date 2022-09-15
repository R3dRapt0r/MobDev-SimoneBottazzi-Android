# MobDev-SimoneBottazzi-Android

<-Note sull'esecuzione dell'app->

Ho sempre testato il progetto sul mio telefono al posto del simulatore, poiché spesso il geocoding non funzionava

L'app necessita del gps attivo per funzionare

Per tornare indietro su una schermata è sufficiente premere il pulsante indietro / gesture indietro


<- Funzionamento app ->


--Main activity--
all'avvio, vengono richesti i permessi di geolocalizzazione per mostrare come città di default quella
in cui ci si trova. Una volta ottenute le coordinate, esegue la chiamata API mediante una download task

Ho gestito il caricamento dei valori (temperatura, icona) mediante image view e text con dei tag predefiniti 
(da 0 a 31) all'interno di horizontal view, quindi mi basta usare un ciclio while e degli indici che incremento 
ogni volta dopo aver aggiornato una image o text view.
Ho scelto questo metodo perché conoscevo a priori su quanti elementi avrei lavorato e non mi serviva usare una lista

Le coordinate sono sempre passate mediante shared preferences. Potevo scegliere di passare gli elementi usando i bundle,
che però non sono persistenti, non salvando la lista dei preferiti, o anche un database, ma ho trovato più semplice e
immediato salvare e passare i valori con le shared, non dovendo creare query per interrogare un databese


--Lista Preferiti--
La lista dei preferiti, cui si accede premendo il pulsante +, permette di vedere l'elenco delle città salvate:
quando se ne cerca una questa viene salvata. Per eliminarne una, si deve tenere premuto sulla città che si vuole 
eliminare, poi una notifica toast confermerà l'effettiva eliminazione.

La lista è una array list di oggetti classe, i quali contengono le coordinate e il nome della città. Viene salvata
permanentemente usando le shared preferences, così da renderla accessibile da qualsiasi attività quando necessario.

Normalmente non potrei passare una intera lista di oggetti nelle shared preferences, quindi uso un object serializer
per trasformare la lista in una stringa, e poi quando mi serve uso il deserializer per riottenere la lista


--Mappa--

Quando aperta, la mappa fa uno zoom sull'ultima città selezionata, e toccando un marker è possibile vedere il nome
della città corrispondente

La mappa scorre la lista delle città, presente nelle shared preferences, e per ognuna ricava la posizione, il nome
e, mediante la classe jsonObjGetter, la condizione meteo per andare a scegliere l'icona corretta da impostare al
marker
