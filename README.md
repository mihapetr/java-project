# Ideje za projekt
**Napomena:** dokument nije napisan do kraja. Dovršim uskoro.

## Razmislite!

* Pogledajte dokument i pronađite što vam se sviđa i što bi bili voljni implementirati. Jedna moja vizija je da netko od vas uzme sekciju **[2.](#2-okruženje-administratora)**, a drugi sekciju **[3.](#3-okruženje-blagajnika)**
* Ja bih se bavio **[bazama podataka](#1-baza-podataka)** i povezivanjem funkcionalnih komponenata u **cjelinu**. Odradio sam dosta posla smišljajući ove upute.
* Mislim da je najlakše da svatko za svoj dio napiše **[unit testove](#4-unit-testovi)**.
* Volio bih da napravite svatko svoj **git branch** iz ovoga i u *README.md* fajl napišete svoje komentare o biločemu.
* Dijelovi označeni s (dodatno) mi se čine jako prikladni za dodatne bodove.

## Napravio sam

* U *idea-for-main-class* branch sam napisao prototip glavnog programa koji se brine za baratanje prozorima.
    * Ako vam se svidi mergeat ćemo ga u *main* branch.
* U source code sam stavio komentare koji opisuju pojedine blokove koda.
* *Main* klasa izgleda užasno komplicirano, ali je napravljena korektno. Malo sam istraživao *[Concurrency in Swing](https://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html)*.

## Planiram napraviti

* Želim implementirati do kraja *LoginFrame*. Točnije funkcionalnost upita u bazu podataka kod prijave korisnika.
* Korektan pristup je korištenjem *SwingWorker-a* i *[Worker Threads](https://docs.oracle.com/javase/tutorial/uiswing/concurrency/worker.html)*. Ne znam koliko vam se da istraživati pa mi je ideja da vidite na primjeru kako sam ja zamislio implementaciju koja se može iskoristiti kao model za razvoj ostalih prozora (*AdminWorkspace* i *WorkerWorkspace*).

## Treba napraviti

1. **Bazu podataka** za:
    1. [Studente](#1-i)
    2. [Radnike: administratori i blagajnici](#1-ii)
    3. [Hranu](#1-iii) 
    4. (dodatno) [Zapisnik o detaljima računa](#1-iv)
2. Radno okruženje za **administratora**:
    1. [Sučelje za menadžiranje baze podataka hrane](#2-i)
    2. (dodatno) [Sučelje za vizualnu analizu statistike baza podataka](#2-ii)
    3. [Sučelje za upravljanje bazom podataka zaposlenika](#2-iii)
3. Radno okruženje za **blagajnika**:
    1. [Sučelje za unos podataka studenta](#3-i)
    2. [Sučelje za dodavanje hrane na račun](#3-ii)
    3. [Ispis računa](#3-iii)
4. Napisati neke glavne **unit testove** za bitne klase i funkcionalnosti.
    
## Detalji zadataka iz *Treba napraviti* sekcije

### 1. Baza podataka

### 1. i.
Izgled tablice *Student*: <br>
JMBAG (primary key) &ensp;|&ensp; IME &ensp;|&ensp; PREZIME &ensp;|&ensp; SLIKA &ensp;|&ensp; SUBVENCIJA <br>
**Napomene:** 
* JMBAG: integer, SUBVENCIJA: float:EURO, IME i PREZIME: string
* JMBAG se u programu koristi za dohvat informacija o studentu i korištenje subvencije kod obračuna.
* SLIKA je spremljena kao *file path* u fajl sustavu aplikacije. Koristit ćemo neke template slike za studente.

### 1. ii.
Izgled tablice *Radnik*: <br>
KORISNICKO_IME (primary key) &ensp;|&ensp; LOZINKA &ensp;|&ensp; IME &ensp;|&ensp; PREZIME <br>
**Napomene:** 
* KORISNICKO_IME: string bez razmaka, LOZINKA: string bez razmakia, IME i PREZIME: stringovi
* Možda bismo mogli dodati neku funkcionalnost za praćenje radnog vremena radnika. To bi značilo da treba spremati u satima odrađeno vrijeme.
* KORISNIČKO_IME i LOZINKA služe prijavi u aplikaciju kroz početni prozor *LoginFrame*.
* Na račnu se ispisuje ime i prezime prijavljenog blagajnika. 
* (Aplikacija prati radno vrijeme blagajnika)

### 1. iii.
Izgled tablice *Hrana*: <br>
NAZIV  (primary key) &ensp;|&ensp; CIJENA &ensp;|&ensp; KRATKI_NAZIV &ensp;|&ensp; RAZINA_SUBVENCIJE &ensp;|&ensp; DOSTUPAN <br>
**Napomene:** <br>
* NAZIV: string: puni naziv proizvoda, CIJENA: float: EURO, KRATKI_NAZIV: string: služi za ispis na račun, RAZINA_SUBVENCIJE: float iz <0,1>: označava u kolikoj mjeri  subvencvija pokriva kupovinu proizvoda.
* DOSTUPAN: boolean: označava pojavljuje li se u radnom okruženju **blagajnika**. Vidi **[2. ii.](#2-i)**

### 1. iv.
Ne bi bilo loše smisliti **signature računa** (najbitniji detalji) koji bismo onda kod svakog ispisa spremali u bazu podataka i koristili u **[2. ii.](#2-ii)** za vizualnu analizu. Npr. može se napraviti graf koji prikazuje vjerojatnost dolaska, tj. gužvu u određenim intervalima radnog vremena i slično.

### 2. Okruženje administratora

### 2. i.
Administrator bi trebao biti u mogućnosti imati **pregled** baze podataka kroz sučelje. Treba omogućiti lako **dodavanje, editiranje i izbacivanje** artikala. Svaki dan se radi **selekcija** svih inače dostupnih artikala. Takav podskup definira što je dostupno **blagajniku** kod rada. U tablici baze podataka su dostupni artikli taj dan označeni s DOSTUPAN = TRUE. Vidi **[1. iii.](#1-iii)**

### 2. ii.
Profesor je spomenuo da bismo u projekt trebali dodati nekakve **vizualizacije**. Možda bi bilo zgodno imati neki prozor ili tab u kojem je moguće vidjeti osnovne statistike koje opisuju stanje baze podataka. Npr. **grafove opisne statistike** cijena proizvoda: stupčasti dijagram, piechart, boxplot...

### 2. iii.
Slično kao u **[2. i.](#2-i)** treba omogućiti dodavanje i izbacivanje blagajnika s radnog mjesta.

### 3. Okruženje blagajnika

### 3. i.
Potrebno **polje** za ručni unos JMBAG-a i **prozor** na kojem su ispisane informacije o studentu i prikazana fotografija.

### 3. ii.
Potrebno je smisliti uredni prikaz prehrambenih proizvoda i način interakcije za dodavanje na račun za trenutno prijavljenog studenta.

### 3. iii.
Po uzoru na format stvarnog računa iz studentskih restorana smisliti kako urediti ispis i pobrinuti se za rad s tom datotekom.

### 4. Unit testovi
Svatko piše *unit testove* za svoj dio projekta.
