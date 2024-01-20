# Testiranje-projektWeb

Projektni zadatak izrađen je za testiranje glavnih funkcionalnosti web aplikacije GetYourGuide.

## Preuzimanje projekta
1. Na računalu stvoriti mapu i unutar nje otvoriti git bash terminal.
2. Klonirati repozitorij.
   ```bash
   git clone https://github.com/ZemljakM/Testiranje-projektWeb
   ```
3. Ako već nije instaliran, instalirati IntelliJ alat.
4. Preuzeti drivere za Chrome preglednik i spremiti ga u mapu C:\drivers.
5. U IntelliJ alatu otvoriti mapu Testiranje-projekt.
6. Unutar RegistrationTest klase upisati svoj testni email, ime, prezime i lozinku. Iste podatke upisati i unutar LoginTest klase.
7. U terminal upisati naredbu te pritisnuti Ctrl+Enter kako bi se pokrenuli testovi.
   ```bash
   mvn test
   ```

## O projektu
Izrađen je okvir za automatsko testiranje programske podrške koristeći IntelliJ alat. Okvir se sastoji od 6 testnih slučajeva napisanih koristeći JAVA programski jezik. Testovi će se provoditi na Google Chrome pregledniku. Stvorene su dvije klase: RegistrationTest i LoginTest. 

Prva se izvršava klasa RegistrationTest koja sadrži testni slučaj za registraciju korisnika. Nakon otvaranja aplikacije, pritiskom na ikonu profila otvara se opcija za prijavu ili registraciju. Upisivanjem email adrese, aplikacija otvara formu za registraciju ako ne prepoznaje unesenu email adresu. Upisivanjem imena, prezimena i lozinka te klikom na tipku za prijavu, otvara se početna stranica. Provjera uspješna registracija obavljena je ponovnim klikom na ikonu profila i provjerom prikazivanja opcija za odjavu.

Nakon što se izvede prva klasa, pokreće se druga koja sadrži pet testnih slučajeva. Svakom je testnom slučaju pridružen prioritet kako bi se uvijek izvodili slijedno. Prvi testni slučaj zadužen je za prijavu korisnika. Postupak je sličan onom za registraciju, samo bez upisivanja imena i prezimena. Provjera se izvršava na isti način te zatvara forma kako bi se mogao izvršiti idući test. Drugi je test zadužen za pronalazak tražilice nakon što je dokument u potpunosti učitan. U tražilicu se upisuje pojam _Paris_ te uzima prvi link i pretražuje sadrži li on riječ paris. Ispisuje se rezultat usporedbe i nastavlja s trećim testom. Idući je test zadužen za pronalazak i dodavanje prvog linka na listu želja. Kreira se nova lista želja s predloženim imenom. Četvrti test zadužen je za dodavanje proizvoda u košaricu. Klikom na prvi link otvara se stranica u novoj kartici pa je potrebno promjeniti karticu na koju je driver fokusiran. Test je zadužen da pronađe ikonu za kalendar, pritisne na nju i odabere 29. veljače kao datum za željenu aktivnost. Aktivnost se dodaje u košaricu, a uspješnost test provjerava se porukom u skočnom prozoru. Zadnji test zadužen je da pritiskom na ikonu profila pronađe opciju za odjavu korisnika. BeforeTest i AfterTest anotacijom omogućeno je da se driver upali jednom prije prvog test i ugasi tek nakon posljednjeg. Za pronalaz elemenata korišten je xpath, a kada je to bilo nemoguće zbog prevelike količine elemenata na stranici, najčešće su korišteni CSS selektori na način da se označi jedan od glavnih elemenata na stranici pa onaj manji koji nam je potreban.


