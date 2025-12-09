Citirea: Ca la tema 1, cu JsonNode, ObjNode, ArrayNode, etc. 
PACHETE:
1. PACHETUL TICKET
Contine clasele:
a) Ticket- clasa de baza cu componentele necesare pentru a crea un tichet. Singurul camp suplimentar este description.
b) UI - PRIMUL DESIGN PATTERN! BUILDER! Am folosit builder pentru ca tichetul poate avea mai multe campuri optionale. Este mai eficient si codul arata mai elegant daca am un singur constructor privat si cate un constructor pentru fiecare field optional.(fata de multi constructori la care difera doar parametri).
c) Feature - nu e de tip builder, campurile sunt obligatorii toate si nu ar fi avut sens.
d) BUG - din nou, BUILDER. Il folosesc cu acelasi scop ca la UI
e) Vezi Tichete - clasa in care am un singur camp, o lista de inventar tichete si mai multe atribute speciale pentru printare. Clasa contine multe metode care ma ajuta sa printez anumite detalii specifice pentru fiecare tip de tichet, in functie de rolul userului(developer/ manager).
2. PACHETUL USER
a) Users- clasa de baza cu atributele specifice
b) Developer - clasa care extinde users
c) Manager - clasa care extinde users
d) Reporter - clasa care extinde users
e) DeveloperFactory - Fabrica de developers
f) JuniorDeveloper - tip de developer
g) MidDeveloper - tip de developer
h) SeniorDeveloper - tip de developer
i) NextPriority/TransformCritical - clase specifice pentru interactiunea la 3 zile dupa crearea unui Milestone
j) interfata SpecialMention - pentru i).
CLASA MILESTONE- clasa de baza pt MIlestones
CLASA INFOMILESTONE- clasa helper pentru a apela metode pentru printarea detaliilor despre milestones.
CLASA NOTIFICATIONS- clasa de baza pentru notificari
CLASA SEARCH - pentru cautare
CLASELE TICKETSEARCH/DEVELOPERSSEARCH - pentru cautare detaliata
INTERAFATA OBSERVATOR - pentru NOtificari(folosesc observer pattern)

COMENZI:
1. REPORT TICKET-  crearea Tichetelor- folosesc BUILDER/nu, in functie de tipul tichetului. Primesc titlul, tipul, timestampul, restul atributelor obligatorii si optionale. Apelez constructorii din clasele facute anterior si folosesc builder pentru UI/BUG ca sa adaug campurile optionale.
2. VIEWTICKET - Ma folosesc de clasa viewtickets si printez toate tichetele(care sunt puse in inventar tichete- lista de tichete). Printez cu ObjNode, etc.
3. CREATEMILESTONE - Creez milestone-ul si citesc parametri. Pentru edgeCases, verific exceptiile date in cerinta.
DUpa crearea unui milestone, incep interactiunile cu tichete. Pentru asta, folosesc STRATEGY PATTERN. Am interfata specialmentiomn ce reprezinta diferitele schimbari pe care le pot avea tichetele din milestone-ul respectiv. Interfata e implementata de clasele nextpriority si transformcritical.(numele sunt destul de sugestive pentru ceea ce fac fiecare).
4. VIEWMILESTONES - Afiseaza toate milestone-urile create.
5. ASSIGNTICKET - Asigneaza tichete userilor. In clasa users este cate o lista de tichete asignate fiecaruia. Trebuie sa verific daca user-ul poate rezolva tichetul(daca are expertiseArea potrivita, seniority si daca milestone-ul din care face parte nu e blocat. Daca toate se respecta, atunci se adauga la lista de tichete tichetul cu id-ul dat.
Pentru verificarea rezolvarii ticetului, folosesc FACTORY METHOD Pentru a coda prioritatile si specializarile.
PENTRU JUNIOR - COD 2(POATE LOW/MEDIUM SI BUG + UI)
PENTRU MID - COD 3(POATE LOW/MEDIUM/HIGHT SI BUG + UI + FR)
PENTRU SENIOR - COD 4 SI 3(POATE LOW/MED/HIGH/CRITICAL SI BUG+ UI+ FR). Pentru asta clasa Developer devine abstracta si clasele JuniorDeveloper, MidDeveloper, SeniorDeveloper implementeaza metodele absracte gettickettype, accesspriority. Folosesc factory method pentru ca este un mod mai elegant de a crea developeri, in functie de tipul lor.
6. VIEWASSIGNEDTICKETS - Au acces doar developerii si printeaza lista de tichete asignata anterior.
7. UNDOASSIGNEDTICKETS - Sterg din lista de tichete tichetul cu id-ul repsectiv, DAR EU NU FAC ASTA! Creez o alta lista care se numeste gaveupTickets si adaug tichetul acolo, dar il elimin din lista de tichete initiala.(Am nevoie de fostele tichete in comanda pentru history).
8. ADDCOMMENT - Adauga comentariu la un tichet. Lista de comenatrii pentru fiecare tichet. Adaug acolo comentariul primit.
9. UNDOADDOCOMMENT -ELimina comentariul facut mai devreme si il sterge din lista de comentarii ale tichetului respectiv.
10. CHANGESTATUS - Trece tichetul la urmatorul status. Daca e low, trece mai departe la medium. Cand ajunge la resolved, atunci seteaza si campul "solved at" din clasa ticket. Cand ajunge la resolved/closed, seteaza si campul ultimultimestampCR(pentru calcul la eficienta).
11. UNDOCHANGESTATUS - Sterge statusul anterior si trece la cel precdent.
12. PRINTICKETHISTORY - Printeaza istoricul unui tichet. Daca userul este delveloper, atunci vede toate tichetele. Daca e manager, vede tichetele asignate developerilor din milestone-urile create.
13. SEARCH - Cauta in inventarTichete si in useri(lista de users) obiectele care dau match pe criteriile date.
14. VIEWNOTIFICATIONS - Printeaza notificarile tichetelor
Pentru notifications am folosit OBSERVER PATTERN. Am in clasa MIlestone 3 metode(milestonecreat, vineduedate, atrecutdue) care sunt apelate pentru a notifica developerul ca s-a intamplat o modificare. Observerii sunt developerii si milestone-urile sunt cele care genereaza notificari.
15. GENERATETECUSTOMERIMPACTREPORT
Calculez nr de tichete open/ in progress pentru fiecare tip de tichete si afisez si nr lor in functie de prioritate, apoi cu ajutorul metodelor date de enunt fac calculele necesare. 
Acelasi lucru se intampla si pentru
16. GENERATETICKETRUSKREPORT
17. GENERATERESOLUTIONEFFICIENCYREPORT
18. GENERATEPERFORMANCEREPORT

