**Citire si Structura**

Citirea datelor se realizeaza utilizand JsonNode, ObjectNode, ArrayNode, etc.

**Pachete si Clase**

1. **Pachetul Ticket**
Acest pachet gestioneaza toate tipurile de tichete si operatiunile asociate.
a) **Ticket** – clasa de baza pentru tichete, continand campurile necesare pentru crearea unui tichet. Singurul camp suplimentar este description.
b) **UI** – implementare a Builder Pattern pentru tichete UI. Folosirea Builder-ului permite un singur constructor privat si metode pentru setarea campurilor optionale, eliminand nevoia de mai multi constructori cu parametri diferiti.
c) **Feature** – clasa pentru tichete de tip Feature; toate campurile sunt obligatorii, deci Builder-ul nu este necesar.
d) **Bug** – implementare similara cu UI, utilizand Builder pentru gestionarea campurilor optionale.
e) **ViewTickets** – clasa care contine o lista de tichete si metode speciale pentru printarea detaliilor, in functie de rolul utilizatorului (Developer / Manager).
1.2. **Pachetul ModifyTickets** 
a) **NextPriority / TransformCritical** – clase pentru interactiunile la 3 zile dupa crearea unui milestone.
b) **SpecialMention** – interfata utilizata pentru clasele de mai sus.


2. **Pachetul User**
Acest pachet gestioneaza utilizatorii si tipurile lor specifice.
a) **Users** – clasa de baza pentru utilizatori.
b) **Developer, Manager, Reporter** – clase care extind Users.
2.2. **Pachetul developerTypes**
a) **DeveloperFactory** – fabrica pentru crearea developerilor.
b) **JuniorDeveloper, MidDeveloper, SeniorDeveloper** – implementari specifice pentru diferite nivele de developer.

3. **Pachetul commands**
Fiecare comanda este implementata intr-un pachet separat in cadrul proiectului.
a) **Report Ticket** – creare tichete folosind Builder (UI / Bug) sau constructor normal (Feature). Se primesc titlul, tipul, timestamp-ul si atributele obligatorii si optionale.
b) **View ticket** – afisare tichete din inventar folosind ViewTickets si ObjectNode.
c) **Create milestone** – creare milestone si citire parametri; pentru edge cases se verifica exceptiile. Interactiile cu tichete se realizeaza prin Strategy Pattern (interfata SpecialMention implementata de NextPriority si TransformCritical).
d) **View milestones** – afiseaza toate milestone-urile create.
e) **Assign ticket** – asignarea tichetelor utilizatorilor. Se verifica compatibilitatea userului cu tichetul (expertiseArea, seniority, blocare milestone). Prioritatile sunt gestionate prin Factory Method pentru developerii Junior, Mid si Senior.
f) **View assigned tickets** – afiseaza tichetele asignate unui developer.
g) **Undo assigned tickets** – muta tichetul din lista principala in gaveupTickets pentru pastrarea istoricului.
h) **Add comment** – adauga comentariu la un tichet.
i) **Undo add comment** – sterge comentariul adaugat anterior.
j) **Change status** – trece tichetul la urmatorul status; la resolved se seteaza campurile solvedAt si ultimTimestampCR.
k) **Undo change status** – revine la statusul anterior.
l) **Print ticket history** – afiseaza istoricul tichetului, diferentiat dupa rol (developer / manager).
m) **Search** – cautare in inventar tichete si lista de users, pe baza criteriilor date.
n) **View notifications** – afiseaza notificarile tichetelor; implementare Observer Pattern (milestones sunt observabile, developers sunt observatori).
o) **Generate customer impact report** – calculeaza nr. de tichete open / in progress pe tip si prioritate.
p) **Generate ticket risk report** – similar cu raportul de impact.
q) **Generate resolution efficiency report** – calculeaza eficienta rezolvarii tichetelor.
r) **Generate performance report** – calculeaza performanta dezvoltatorilor.

4. **Pachetul helpers**
a) **CheckingHelpers** - clasa ce contine metode ajutatoare de verificare a unor conditii
b) **HelperMethods** - clasa ce contine metode ajutatoare
c) **PrintingHelpers** - clasa ce contine metode ajutatoare de printare
d) **ReturnHelpers** - clasa ce contine metode ajutatoare de returnare a unor entitati
e) **WorkingWithMilestones** - clasa ce contine metode ajutatoare pentru reactualizarea milestone-urilor inainte si dupa un timestamp.

5. **Pachetul magicNumbers**
a) **MagicNumbersDouble** - clasa ce contine numere de tip double
b) **MagicNumbersInt** - clasa ce contine numere de tip Integer

6. **Pachetul milestones**
a) **Milestone** – clasa de baza pentru milestones.
b) **InfoMilestone** – clasa helper pentru apelarea metodelor de printare a detaliilor despre milestones.

7. **Pachetul searching**
a) **DevelopersSearch** - clasa in care se cauta developeri dupa anumite filtre
b) **TicketSearch** - clasa in care se cauta tichete dupa anumite filtre

8. **Clase suplimentare**

a) **Notifications** – clasa de baza pentru notificari.
b) **PerformanceReport** - clasa de baza pentru raportul de performanta a useri-lor.


9. **Observatii suplimentare:**
a) Pentru t18 si t19 s-a abordat problema developerilor care nu mai pot rezolva un tichet (expertiseArea, seniority etc.).
b) Tichetele sunt deasignate si timpul de rezolvare recalculat.
