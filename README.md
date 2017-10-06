# DRMS Prototyp
Der DRMS Prototyp ist ein Prototyp einer Verarbeitung von ODRL Lizenzen für ein Digitales Rechte Management für TOSCA.
Der Prototyp ist Teil der Bachelorarbeit "Digital Rights Management für TOSCA" an der Universität Stuttgart (Institut IAAS). 

Der gesamte Protoyp besteht aus drei einzelnen Services:

1. DRM Contentprovider (zur Erstellung von ODRL Lizenzen) 
2. OpenTOSCA Upload Mock (Mock zum Hochladen der Lizenzen)
3. DRM Controller (zur Verarbeitung der im OpenTOSCA Upload Mock hochgeladenen Lizenzen) (**aktuelles Repository**)

#####Für eine vollständige Funktion des Systems müssen alle drei genannten Services gleichzeitig ausgeführt sein! ####

### Nutzungshinweis
Für einen vollständigen Ablauf des Systems muss eine Lizenz im DRM Contentprovider erstellt und heruntergeladen werden. 
Diese Lizenz und seine entsprechende CSAR Datei muss dann über den OpenTOSCA Upload Mock hochgeladen werden. 
Der OpenTOSCA Upload Mock sendet beim Hochladen die Lizenz an den DRM Controller zur Verarbeitung und Auslesen der Rechte, welcher die ausgelesenen Lizenzen an den OpenTOSCA Upload Mock als JSON zurücksendet. 
Der OpenTOSCA Upload Mock zeigt dieses JSON zur Kontrolle des richtigen Mapping der Lizenz auf der Uploadseite an.

## DRM Controller
Der DRM Controller ermöglicht die Verarbeitung einer ODRL Lizenz, welche von dem OpenTOSCA Upload Mock empfangen wird.
Es wird eine ODRL Version 1.1 konforme XML-Datei ausgelesen und verarbeitet. Die Schnittstellen sind als REST-Schnittstellen entwickelt. 
Der DRM Controller basiert auf Spring Boot und wird auf einem integrierten Tomcat Server ausgeführt.


Zum Aufsetzen und Benutzen des DRM Controller werden die folgenden Schritte benötigt:

1. Checkout des Repository.
2. `gradle bootRun` ausführen (wenn Gradle nicht im PATH oder nicht installiert ist, Nutzung des gradle-wrapper `./gradlew bootRun`)
3. Die REST Schnittstellen werden über die Root [http://localhost:8082/v1/drmcontroller/](http://localhost:8082/v1/drmcontroller/) angesprochen

Angebotene Schnittstellen sind:

* [http://localhost:8082/v1/drmcontroller/postlicense](http://localhost:8082/v1/drmcontroller/postlicense) (POST Request zur Verarbeitung der ODRL Lizenz)

Genauere Informationen über die Schnittstellen finden sich unter der [Swagger-Api-Doc](http://localhost:8082/swagger-ui.html#/).

Weitere Informationen zu den Prototypen und der Architektur finden sich in der schriftlichen Ausarbeitung der Bachelorarbeit "Digital Rights Management für TOSCA" an der Universität Stuttgart. 

## License

Copyright (c) 2017 University of Stuttgart.

All rights reserved. This program and the accompanying materials
are made available under the terms of the [Eclipse Public License v2.0]
and the [Apache License v2.0] which both accompany this distribution,
and are available at http://www.eclipse.org/legal/epl-v20.html
and http://www.apache.org/licenses/LICENSE-2.0

[Apache License v2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
[Eclipse Public License v2.0]: http://www.eclipse.org/legal/epl-v20.html
