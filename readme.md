# TP Poo Spring Web
## quesion 5: explication des dépendences
# Spring Web
spring web permet de mettre en place des applications de type http et le support de transfert de fichier, le tout concentré à distance
# JPA
JPA permet de faire persister des objet au dela du processus qui les a crées
# Hibernate
Hibernate est une implémentation de cette idée d'objet persistant en représenant une base de donnée en objet java et vice-versa
# H2
H2 est un système de gestion des bases de données écrit en java, il est soit intégré à une application java ou fonctionne en mode client-serveur
# DevTools
donnes des fonctions qui sont très utiles pour le développement d'applications avec spring
# Thymeleaf
c'est un moteur pour les application web ou standalone, c'est surtout utilisé pour faire des templates.
---
# Reponses aux questions
## question 13
1. Avec quelle partie du code avons-nous paramétré l'url d'appel /greeting ?
   ```java
   @GetMapping("/greeting")
   ```
2. Avec quelle partie du code avons-nous choisi le fichier HTML à afficher ?
   ```java
   return "greeting"
   ```
3. Comment envoyons-nous le nom à qui nous disons bonjour avec le second lien ?
   nous utilisons une instruction qui va chercher la variable dans l'url
   ```java
    @RequestParam(name="name", required=false, defaultValue="World")
   ```
   demande la variable name dans l'URL remplacer "name" par "greetName" nécessitera ?greetName=yourname
## question 17: qu'il y a t-il de nouveau dans la console h2 depuis l'ajout de la classe addresse
On remarque que une table addresse a été ajouté dans la console
## question 18: expliquez l'aparition de la table
```java
@Entity
```
nous utilisons cette anotation pour indiquer que la classe est une table selon JPA
## question 20
réponse de la requête select
| id            | content                                  | creation  |
| ------------- |:-------------:                           |:---------:|
| 1             | 57 boulevard demorieux                   | 2023-01-18|
| 2             | 51 allee du gamay, 34080 montpellier     | 2023-01-18|
## question 22
```java
@Autowired
```
va directement injecter la classe dans le fichier, ici toutes les addresses disponibles
## question 30
tout d'abord j'ai ajouté bootstrap aux depedencys du projet dans le pom.xml
```xml
<dependency>
   <groupId>org.webjars</groupId>
   <artifactId>bootstrap</artifactId>
   <version>3.3.6</version>
</dependency>

<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap-datepicker</artifactId>
    <version>1.0.1</version>
</dependency>

<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>1.9.1</version>
</dependency>
```
ensuite il suffit de rajouter le lien vers le css de bootstrap dans le fragmene Thymeleaf pour que l'affichage soit effectif sur les deux pages
```html
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
```
# TP4
## Mettre en place l'API meteo
il faut placer un fichier token.txt dans la racine du dossier et y inscrire votre token, le programme le récupérera pour vous  
Allez ici pour obtenir votre propre token : https://api.meteo-concept.com/
## question 6
- Faut-il une clé API pour appeler MeteoConcept ?  
Meteo concept est une APi qui comprend des limites d'appel et des fonctions réservées aux utlisateurs prenium, une clé API existe donc pour séparer ceux qui ont payé les fonctions et les utilisateurs gratuits
- Quelle URL appeler ?  
  https://api.meteo-concept.com/api/
- Quelle méthode HTTP utiliser ?
l'API utilise uen méthode GET pour transférer les données
- Comment passer les paramètres d'appels ?  
on passe les paramètre par une méthode get (?argument = valeur ) exemple:
````html
https://api.meteo-concept.com/api/forecast/daily/0?token=Z&latlng=X,Y
````
- Où est l'information dont j'ai besoin dans la réponse :
- Pour afficher la température du lieu visé par les coordonnées GPS
Il faut regarder le champ tmax et tmin dans le fichier Json ou xml de resultat
- Pour afficher la prévision de météo du lieu visé par les coordonnées GPS
Il faut regarder le paramètre weather, qui donne un code lié à la météo du lieu

https://github.com/dinoclier72/4AINFO_TPOO_SPRING_WEB