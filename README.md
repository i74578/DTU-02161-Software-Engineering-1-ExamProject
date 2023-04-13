# 02161 Software Engineering 1 Obligatorisk Opgave

### Project Resources
- [Project description](docs/projectDesc.md)
- [Trello kanban](https://trello.com/b/w3Dal5rF)
- [Miro board](https://miro.com/app/board/uXjVPg4gMsk=/)
- [Rapport](https://www.overleaf.com/project/6405fd846e665ed26e9029e9)

# Feedback

## Skal fixes
- [x] Forsiden er præget af manglende informationer. Eksempelvis er dato ikke angivet, herunder er hverken overskrift "Rapport 1" eller gruppenummer angivet, hvilket er et krav. 
- [x] Use case: Det udformede use case diagram er tekstpræget, hvilket afviger fra et korrekt use case diagram. Et eksempel herfor er sætningen "Se tidsforbrug per aktivitet for et projekt", hvilket er utrolig langt. Bemærkning: Pas på med dette!! 
- [x] I forlængelse af ovenstående kommentar, bliver der heller ikke skrevet noget om registrering af sygdom, ferie mv. for medarbejderens vedkommende jf. use case diagrammet. 
- [x] Der opstår ligeledes dobbeltkonfekt, da mange af informationerne angivet i glossary bliver gentaget under projektgruppens use case diagram. Bemærkning: Medtag kun glossary fremfor at opsumere alle punkter på use case diagrammet efterfølgende, da det ellers virker for gentagende. 
- [x] NB: Desuden kunne i godt have medtaget et afsnit om funktionelle- og ikke funktionelle krav. Opgaven virker pt. for abstrakt, og jeres fokus er ikke så tydeligt under afsnittet om kravsspecifikationer. 
- [ ] Korriger opgaven for ovenstående fejl, også kører det bare!!

-----------------------------

- [ ] De operationer, der er beskrevet i use case diagrammet, har de samme navne i de detaljerede use cases. Vi bemærkede dog, at nogle af operationerne mangler at blive beskrevet i de detaljerede use cases. Disse omfatter 'Fjern medarbejdere fra aktivitet', 'Se tidsforbrug per aktivitet for et projekt' og 'Lav rapport, der viser forventet restarbejde'. Vi går ud fra, at I kun har lavet otte detaljerede use cases baseret på antallet af gruppemedlemmer, og derfor er det vigtigt at fjerne nogle af operationerne fra use case diagrammet, så det hele hænger sammen. 
- [ ] Vi bemærkede endvidere, at nogle af de metoder, I har beskrevet i jeres sekvensdiagrammer, ikke eksisterer i jeres klassediagram. For eksempel i det første sekvensdiagram ser man, at en employee har metoden 'CreateProject(n,s,f,k)', men denne metode mangler i klassediagrammet. 
- [ ] Derudover ville det være godt at inkludere return messages i sekvensdiagrammerne, så de repræsenterer jeres system bedre. Dette gør sig gældende for 1.,3.,5.,6.,7.,8. og 9. sekvensdiagrammer. Eksempelvis i det første sekvensdiagram, kunne det have været tydeligere med en return message, som fortæller medarbejderen, at projektet er blevet oprettet. I det tredje sekvensdiagram viser I, at en employee har metoden 'assignProjectManager(p,e)', men ifølge jeres klassediagram, en employee har ingen metoder. 'assignProjectManager(p,e)' eksisterer under klassen Project, hvilket ikke giver mening, da det er klassen employee, der skal have denne metode og ikke klassen Project. 
- [ ] Generelt beskriver jeres sekvensdiagrammer jeres detaljerede use cases godt og stemmer derfor overens med dem, men der er behov for at få klassediagrammet til at hænge mere sammen med sekvensdiagrammerne. Desuden er der behov for at inkludere flere return messages for at gøre systemet mere omfattende.
- [ ] Mange af begreberne knyttet til use case diagrammet fremgår af glossary, herunder afsnittet under use case diagrammet, hvor alle punkter opsummeres (gentagelse). Dog kunne i godt have tilføjet flere begreber, såsom tilgængelighed. I skriver nemlig, at projektlederen skal kunne se, hvornår en medarbejder er tilgængelig. Men hvad dækker dette begreb helt nøjagtigt over? Hvornår er en medarbejder tilgængelig? 
- [ ] NB: Vi vil desuden godt påpege, at det virker lidt underligt, at kun en medarbejder kan oprette et projekt, og at projektlederen kun kan tilføje opgaver. Måske kunne man overveje at give projektlederen ansvaret for at også kunne oprette et projekt, da det giver mere mening i forhold til projektlederens rolle og ansvar jf. opgavebeskrivelsen. 
- [ ] På den anden side er det godt at se, at der er en god sammenhæng mellem forskellige funktioner, f.eks. at projektlederen kan se en medarbejders tidsregistrering og har evnen til at tilføje og fjerne medarbejdere. Dette er vigtige funktioner, som hjælper med at skabe en mere effektiv og strømlinet arbejdsproces.
- [ ] Umiddelbart, mangler der nogle ting på klassediagrammet, såsom hvad en ProjectManager skal kunne, altså forskel mellem employee og selve manager. 
- [ ] Man kan se at i assigner en projektmanager, ved at sætte en specifik employee til rollen, men selve klassen, samt metoder ift projektmanagerens funktionaliteter synes vi mangler. I forhold til sekvensdiagrammerne, ønskes der at give ros, da disse ser overskuelige ud, samt giver de en henholdsvis god forståelse for hvordan de behandles gennem systemmet. Dog skal nogle af metoderne lige dobbelttjekkes med klassediagrammet.


## Er i tvivl om #SpørgHjælpeLære
- [ ] Desuden er formen på diagrammet ikke ovalt, og der mangler ligeledes detaljer omkring projektlederen. Der bliver eksempelvis ikke skrevet noget om "uafsluttede aktiviteter", hvilket har en væsentlig betydning for selve opgaven. En projektleder skal kunne observere uafsluttede aktiviteter og bemande disse med tilgængelige medarbejdere ved opstået sygdom af en tidligere medarbejder. 

- [ ] Glossary: Derudover er projektgruppens glossary også præget af manglende informationer. Eksempelvis besidder en projektleder langt flere funktionaliteter end det der er beskrevet i glossary, såsom registrering af timer, ferie og bemanding af aktiviteter mv.. 
