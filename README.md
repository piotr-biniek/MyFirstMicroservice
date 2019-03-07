# Project Title 
Pierwszy Microserwis rozbudowywany zgodnie z poszerzaniem umiejętności

## Getting Started 

Download, import each service in STS and run it

## Serwisy wchodzące w skład aplikacji i ich przeznaczenie 

###Servisy "Administrujące i systemowe"

####MyZuulFrontServer
Gateway wraz z routingiem części microservisów (dalej ms.)
W przypadku ms. wymagających autentykacji ma zdefiniowane zasady na stałe w pliku konfiguracyjnym (jest to wymagane) w pozostałych przypadkach routing jest w locie na podstawie Eureki (niżej)
Dodatkowo ustawiona jeest konfiguracja Hystrix - w przypadku routingów zdefiniowanych w pliku konfiguracyjnym, jeżeli servis leży otrzymamy o tym informację zamiast 404 lub cokolwiek innego.

####MyDiscoveryServerServiceEureka
Servis Eureka wykożystywany przez zuula przy routingu aplikacji niezarejestrowanych i zarejestrowanych, informacja o ilości instancji, adresach przekierowania dla ZUULA 

####MyAuth-cloud-server_withRefToke
Serwer odpowiadający tokenem w przypadku prawidłowych danych autentykacyjnych (polecam sprawdzić pliki postmana) i refreshTokenem

####MyAuth-cloud-server_withRefToke
Odpowiada tylko tokenem, brak refreshTokena!

###Proste ms.

####MyGreetingMicroservice
Prosty serwis, bez potrzeby autoryzacji odpowiadający na reście powitaniem lub powitaniem z przekazanym parametrem (od czegoś trzeba zacząć :))

####MyExampleResource-oauth-cloud-server
Pirwszy ms. oczekujący bearerTokena i w zamian udostępniający jakieś informacje o jakichś kontach (nie moim :))

####MyFamily_calendar_microservice
Kiedyś był to być kalendaż rodzinny w JavieEE potem przerabiałem na Springa, ale nie zrobiłem frontu w springu (ani w niczym innym), na potrzeby nauki ms. zostały z niego szczątki na potrzeby autoryzacji i przeprowadzania operacji na bazie (H2)

####MyFileUploadService
W ramach początkowej nauki springa udostępnia resta którym można przesłać plik na server

####MyServiceForHystrix 
Prosty servis z wykożystaniem hystrixa, generuje losowo błąd który jest wyłapywany rzez Hystrix

###Dalsze ciekawsze ms.y

####MyHelloService
Początkowo bardzo prosty servis - "Hello world", powoli przebudowywany, dodano autoryzację, dalej dodano endpońty które zwracają pełne Principal (które są automatycznie pobierane z servera), oraz GranthedAutority do dalszego wykożystania ....

####MyHystrixTestDependentService
Podservis, na potrzeby MyHystrixTestMasterService, ma jeden endpont restowy, udostępnijący dane o jakichś pojazdach, wymaga autoryzacji tokenem, losowo generuje błedy

####MyHystrixTestMasterService
Servis wołający ww ms. łączy się z MyHystrixTestDependentService przez RESTa przekazuje tokena żeby się zautoryzować,  i pozyskuje dane które nam zwraca, w przypadku wygenerowania błedu przez Dependent uruchamiana jest obsługa Hystrixa i dostajemy jsona komunikującego nam awarię, zamiast wywalenia się systemu.





(... in progress...)

## Built With 


* [Maven](https://maven.apache.org/) - Dependency Management 


## Authors 

* **Piotr Biniek**  

## License 

This project is licensed under the MIT License.

