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
- [ ] Mange af begreberne knyttet til use case diagrammet fremgår af glossary, herunder afsnittet under use case diagrammet, hvor alle punkter opsummeres (gentagelse). Dog kunne i godt have tilføjet flere begreber, såsom tilgængelighed. I skriver nemlig, at projektlederen skal kunne se, hvornår en medarbejder er tilgængelig. Men hvad dækker dette begreb helt nøjagtigt over? Hvornår er en medarbejder tilgængelig? 

-----------------------------

- [ ] Vi bemærkede endvidere, at nogle af de metoder, I har beskrevet i jeres sekvensdiagrammer, ikke eksisterer i jeres klassediagram. For eksempel i det første sekvensdiagram ser man, at en employee har metoden 'CreateProject(n,s,f,k)', men denne metode mangler i klassediagrammet. 
- [ ] Der er behov for at få klassediagrammet til at hænge mere sammen med sekvensdiagrammerne. Desuden er der behov for at inkludere flere return messages for at gøre systemet mere omfattende.


## Er i tvivl om #SpørgHjælpeLære
- [ ] Use case: Desuden er formen på diagrammet ikke ovalt, og der mangler ligeledes detaljer omkring projektlederen. Der bliver eksempelvis ikke skrevet noget om "uafsluttede aktiviteter", hvilket har en væsentlig betydning for selve opgaven. En projektleder skal kunne observere uafsluttede aktiviteter og bemande disse med tilgængelige medarbejdere ved opstået sygdom af en tidligere medarbejder. 

- [ ] Umiddelbart, mangler der nogle ting på klassediagrammet, såsom hvad en ProjectManager skal kunne, altså forskel mellem employee og selve manager.

- [ ] Derudover ville det være godt at inkludere return messages i sekvensdiagrammerne, så de repræsenterer jeres system bedre. Dette gør sig gældende for 1.,3.,5.,6.,7.,8. og 9. sekvensdiagrammer. Eksempelvis i det første sekvensdiagram, kunne det have været tydeligere med en return message, som fortæller medarbejderen, at projektet er blevet oprettet. I det tredje sekvensdiagram viser I, at en employee har metoden 'assignProjectManager(p,e)', men ifølge jeres klassediagram, en employee har ingen metoder. 'assignProjectManager(p,e)' eksisterer under klassen Project, hvilket ikke giver mening, da det er klassen employee, der skal have denne metode og ikke klassen Project. 

- [ ] De operationer, der er beskrevet i use case diagrammet, har de samme navne i de detaljerede use cases. Vi bemærkede dog, at nogle af operationerne mangler at blive beskrevet i de detaljerede use cases. Disse omfatter 'Fjern medarbejdere fra aktivitet', 'Se tidsforbrug per aktivitet for et projekt' og 'Lav rapport, der viser forventet restarbejde'. 
