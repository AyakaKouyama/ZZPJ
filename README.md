[![Build Status](https://travis-ci.org/AyakaKouyama/ZZPJ.svg?branch=master)](https://travis-ci.org/AyakaKouyama/ZZPJ) 

**DEV Jest Domyślnym Branchem!**  
  
**API dla księgarni internetowej**  
Features: 
- dodawanie, usuwanie, edycja (w wybranych encjach) książek, użykowników, zamówień, metod dostawy, ról, kategorii, wydawców książek
- uwierzytelnianie i autoryzacja z wykorzystaniem JWT 
- tworzenie zamówień i dokonywanie płatności poprzez serwis PayU  
- filtrowanie i sortowanie książek i użytkowników 
  
Diagram ERD
![alt text]( https://i.ibb.co/ydv96wt/erd.png)  

**INSTRUKCJA OBSŁUGI**`

**Docker (instrukcja dla systemu Windows)**  
Należy pobrać i zainstalować Docker ToolBox (zaznaczyć przy instalacji VirtualBox i Git jeśli nieposiadane). Następnie (z poziomu CMD):  
- znaleźć lokalizację GitBash na dysku (domyślnie `"C:\Program Files\Git\bin\bash.exe"`) i z tej lokalizacji wydać polecenie:  
  `bash -c "/c/Program Files/Docker Toolbox/start.sh\" \"%*\""`  
    (lub inna lokalizacja, jeśli nie wybrano domyślnej podczas instalacji)  
  LUB    
- z konsoli git bash przejść do lokalizacji, gdzie został zaintalowany Docker ToolBox i wydać polecenie `./start.sh`
- wydać polecenie:
`docker-machine restart default` (zamiast default może być dowolna inna nazwa)
- jeśli wszystko przebiegło pomyślnie docker powinien być już zdatny do użytku

**Uruchomienie aplikacji**  
Należy sprawdzić adres na jakim działa maszyna dockera wydając polecenie: `docker-machine ls` (domyślnie 192.168.99.100).  


![alt text](https://i.ibb.co/Q7ggjCW/2.png)  


Następnie w pliku `application.properties` należy wpisać odpowiedni adres w `datasource.url` (numer portu ma zostać jak jest :) ):  


![alt text](https://i.ibb.co/dcnBHLZ/3.png)  


Aby uruchomić aplikację należy wydać serię poleceń:  
`mvn clean install & docker-compose up -d`  
lub  
`mvn clean install & docker-compose build & docker-compose up`  


`docker-compose up -d` uruchamia się w trybie cichym i nie widać szczegółowych informacji. Nawet jeśli aplikacja się nie uruchomi, bo po drodze wystąpił jakiś błąd, to nie poinformuje nas o tym, dlatego w razie wątpliwości lepiej użyć drugiego ciągu poleceń.  
Do zrestartowania aplikacji:  
`docker-compose down & mvn clean install & docker-compose build & docker-compose up`  
do zamknięcia aplikcaji uruchomionej poleceniem `docker-compose up` można użyć skrótu klawiszowego ctrl+c

**Weryfikacja działania**  
Po wpisaniu w konsoli CMD polecenia `docker ps` powinniśmy otrzymać coś podobnego:


![alt text](https://i.ibb.co/27mWfNM/1.png)


W przeglądarce po wpisaniu adresu `http://<adres dockera>:8080/books` powinna wyświetlić się lista wszystkich książek w bazie (początkowo pusta, czyli pojawi się tylko `[]`).  
Aby dodać rekord należy pobrać wtyczkę do przeglądarki do obsługi RESTa lub korzystać z konsoli z narzędzia CURL. Należy wysłać żądanie na adres `http://<adres dockera>:8080/books` z metodą `POST`, dodać nagłówek `Content-Type: application/json`, a w ciele żądania umieścić obiekt JSON odpowiadający encji Book (JSON na screenie nie jest aktualny, należy sprawdzić w kodzie jakie pola ma encja).  


Przykład przy użyciu wtyczki RestMan dla przeglądarki Opera (dane na screenach są nieaktualne, należy sugerować się tym co jest napisane): 
![alt text](https://i.ibb.co/V3MJLxG/4.png)  


Jeśli akcja się powiedzie, w odpowiedzi powinniśmy dostać kod HTTP 201, w przeciwynym razie HTTP 400 z kodem błędu lub HTTP 500 jeśli nastąpił jakiś nieobsłużony wyjątek.


Po odświeżeniu strony `http://<adres dockera>:8080/books` powinien pojawić się nowo dodany rekord:  


![alt text](https://i.ibb.co/3ytFD1v/5.png) 


**Dane testowe**  
Ze względu, że wystąpił problem z integracją docker'a z flyway'em, a z jakiegoś powodu import.sql nie działa i czas nie pozwolił znaleźć rozwiązania, zaproponowano alternatywny sposób wprowadzenia danych testowych. 
- w `application.properties` należy ustawić `spring.jpa.hibernate.ddl-auto=create-drop`
- uruchomić aplikację
- w konsoli wydać polecenie `docker ps` i sprawdzić hash kontenera, na którym znajduje się baza danych
- wydać polecenie docker exec -ti <hash> mysql -p db -u db
- podać hasło `db`
- wkleić dane testowe :) :  
 
 
```
INSERT INTO role(id, name, version) VALUES (1, 'CLIENT', 0);  
INSERT INTO role(id, name, version) VALUES (2, 'ADMINISTRATOR', 0); 
INSERT INTO user_details(id, street, street_number, flat_number, phone_number, city, country, first_name, last_name, version) VALUES (1, 'Polna', '12', '3', '96541', 'Warszawa', 'Polska', 'Jan', 'Testowy', 0); 
INSERT INTO user_details(id, street, street_number, flat_number, phone_number, city, country, first_name, last_name, version) VALUES (2, 'Kwiatowa', '32', '51', '1223252', 'Warszawa', 'Polska', 'Zofia', 'Testowa', 0); 
INSERT INTO user(id, login, email, password_hash, role_id, user_details_id, version) VALUES (1, 'client', 'client@client.client', '$2a$10$fVVMTviicRYjVnXGIbW7neBtDm5filacjZp6pBVXgAF3zAczmiq8K', 1, 1, 0); 
INSERT INTO user(id, login, email, password_hash, role_id, user_details_id, version) VALUES (2, 'admin', 'admin@admin.admin', '$2a$10$fVVMTviicRYjVnXGIbW7neBtDm5filacjZp6pBVXgAF3zAczmiq8K', 2, 2, 0); 
INSERT INTO category(id, name, version) VALUES (1, 'fantasy', 0); 
INSERT INTO category(id, name, version) VALUES (2, 'przygodowe', 0);  
INSERT INTO publisher(id, name, version) VALUES (1, 'Super wydawca', 0);  
INSERT INTO book(id, title, author, price, category_id, isbn, number_of_pages, description, publisher_id, version) VALUES (1, 'Książka testowa', 'Jakiś autor', 10.00, 1, 'asdff', 123, 'Bardzo fajna książka', 1, 0);  
INSERT INTO book(id, title, author, price, category_id, isbn, number_of_pages, description, publisher_id, version) VALUES (2, 'Kolejna książka', 'Znany autor', 23.50, 2, 'asdfguigiuf', 89, 'Ciekawa książka', 1, 0);  
INSERT INTO shipping_method(id, name, price, version) VALUES (1, 'Poczta', 12.50, 0); 
INSERT INTO shipping_method(id, name, price, version) VALUES (2, 'Kurier', 18.90, 0); 
INSERT INTO payment_status(id, name, version) VALUES (1, 'WAITING_FOR_PAYMENT', 0); 
INSERT INTO payment_status(id, name, version) VALUES (2, 'PAID', 0);  
INSERT INTO payment_status(id, name, version) VALUES (3, 'CANCELED', 0);  
INSERT INTO purchase(id, user_id, payment_status_id, total_price, order_id, shipping_method_id, version) VALUES (1, 1, 1, 46.0, null, 1, 0);  
INSERT INTO ordered_book(id, purchase_id, book_id, version) VALUES (1, 1, 1, 0);  
INSERT INTO ordered_book(id, purchase_id, book_id, version) VALUES (2, 1, 2, 0);  
```
- zmienić  w `application.properties` na `spring.jpa.hibernate.ddl-auto=none`, aby dane nie usuwały się po ponownym uruchomieniu aplikacji
 
- **WAŻNE** w danych testowych znajduje się dwóch użytkowników o loginach "client", oraz "admin" (o rolach odpowiednio CLIENT i ADMINISTRATOR), hasła do obu tych kont to **"test"**  

**Uwierztenianie**  
Zaimplementowano uwierzytelnianie JWT. Aby wykonać metody oznaczone adnotacją `@PreAuthorize` należy się uwierzytelnić, wysyłająć dane logowania w formacie JSON na adres `http://<adres dockera>:8080/aut/login`, metoda `POST` np: 
```
{ "login": "client",
  "password": "test"
}
```
W odpowiedzi zwrócony zostanie token, który należy dołączyć do nagłówka:  
`Authorization Bearer <token>`

**Pakiety**  
W aplikacji wydzielono kilka pakietów:  
- controllers
- services
- repositories
- entites
- dtos
- exceptions
- security
- utils

Controllers - endpointy, czyli punkty "najbliższe użytkownika". Tam znajdują się wszystkie RESTowe metody. Nie ma w nich żadnej logiki biznesowej. Mogą występować tylko konwersje dto - encja, encja - dto i przekazanie sterowania do serwisów.  
Services - obługa logiki biznesowej. Serwisy korzystają z repozytoriów encji.  
Repositories - repozytoria encji, operacje na encjach. Rozszerzają klasę `JpaRepository`, w której jest już zaimplementowany cały CRUD na encjach.  
Entities - klasy encyjne; odpowiadają tabelom w bazie.    
Dtos - obiekty pośrednie; przez RESTa przesyła się DTO a następnie konwertuje na obiekt właściwej encji.   
Exceptions - jak sama nazwa wskazuje - klasy wyjątków  
Security - implementacja mechanizmu uwierzytelniania  
Utils - pozostałe, pomocnicze klasy    

**Testy** 
Stworzono testy trzech rodzajów:  
- testy repozytoriów  
- testy serwisów  
- test integracyjne 


