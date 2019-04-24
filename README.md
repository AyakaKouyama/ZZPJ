[![Build Status](https://travis-ci.org/AyakaKouyama/ZZPJ.svg?branch=master)](https://travis-ci.org/AyakaKouyama/ZZPJ)

**Docker**  
Należy pobrać i zainstalować Docker ToolBox (zaznaczyć przy instalacji VirtualBox i Git jeśli nieposiadane). Następnie (z poziomu CMD):  
- znaleźć lokalizację GitBash na dysku (domyślnie `"C:\Program Files\Git\bin\bash.exe"`) i z tej lokalizacji wydać polecenie:  
  `bash -c "/c/Program Files/Docker Toolbox/start.sh\" \"%*\""`  
  (lub inna lokalizacja, jeśli nie wybrano domyślnej podczas instalacji)
- wydać polecenie:
`docker-machine restart default` (zamiast default może być dowolna inna nazwa)
- jeśli wszystko przebiegło pomyślnie docker powinien być już zdatny do użytku

**Uruchomienie aplikacji**  
Należy sprawdzić adres na jakim działa maszyna dockera wydając polecenie: `docker-machine ls`.  


![alt text](https://i.ibb.co/Q7ggjCW/2.png)  


Następnie w pliku `application.properties` należy wpisać odpowiedni adres w `datasource.url`:  


![alt text](https://i.ibb.co/dcnBHLZ/3.png)  


Aby uruchomić aplikację należy wydać serię poleceń:  
`mvn clean install & docker-compose up -d`  
lub  
`mvn clean install & docker-compose build & docker-compose up`  


`docker-compose up -d` uruchamia się w trybie cichym i nie widać szczegółowych informacji. Nawet jeśli aplikacja się nie uruchomi, bo po drodze wystąpił jakiś błąd, to nie poinformuje nas o tym, dlatego w razie wątpliwości lepiej użyć drugiego ciągu poleceń.  
Do zrestartowania aplikacji:  
`docker-compose down & mvn clean install & docker-compose build & docker-compose up`  

**Weryfikacja działania**  
Po wpisaniu w konsoli CMD polecenia `docker ps` powinniśmy otrzymać coś podobnego:


![alt text](https://i.ibb.co/27mWfNM/1.png)


W przeglądarce po wpisaniu adresu `http://<adres dockera>:8080/book/all` powinna wyświetlić się lista wszystkich książek w bazie (początkowo pusta, czyli pojawi się tylko `[]`).  
Aby dodać rekord należy pobrać wtyczkę do przeglądarki do obsługi RESTa lub korzystać z konsoli z narzędzia CURL. Należy wysłać żądanie na adres `http://<adres dockera>:8080/book/create` z metodą `POST`, dodać nagłówek `Content-Type: application/json`, a w ciele żądania umieścić obiekt JSON odpowiadający encji Book.  


Przykład przy użyciu wtyczki RestMan dla przeglądarki Opera: 
![alt text](https://i.ibb.co/V3MJLxG/4.png)  


Jeśli akcja się powiedzie w odpowiedzi powinniśmy dostać kod HTTP 201, w przeciwynym razie HTTP 500 (błąd serwera). W przyszłości należy obsłużyć wyjątki, tak aby sytuacja z HTTP 500 nie występowała.


Po odświeżeniu strony `http://<adres dockera>:8080/book/all` powinien pojawić się nowo dodany rekord:  


![alt text](https://i.ibb.co/3ytFD1v/5.png) 


**Pakiety**  
W aplikacji wydzielono kilka pakietów:  
- controllers
- services
- repositories
- entites
- dtos

Controllers - endpointy, czyli punkty "najbliższe użytkownika". Tam znajdują się wszystkie RESTowe metody. Nie ma w nich żadnej logiki biznesowej. Mogą występować tylko konwersje dto - encja, encja - dto i przekazanie sterowania do serwisów.  
Services - obługa logiki biznesowej. Serwisy korzystają z repozytoriów encji.  
Repositories - repozytoria encji, operacje na encjach. Rozszerzają klasę `CRUDRepository`, w której jest już zaimplementowany cały CRUD na encjach.  
Entities - klasy encyjne; odpowiadają tabelom w bazie.  
Dtos - obiekty pośrednie; przez RESTa przesyła się DTO a następnie konwertuje na obiekt właściwej encji.  
