# tp poo
explication des dépendences
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
   ```
   @GetMapping("/greeting")
   ```
2. Avec quelle partie du code avons-nous choisi le fichier HTML à afficher ?
   ```
   ```
3. Comment envoyons-nous le nom à qui nous disons bonjour avec le second lien ?
   nous utilisons une instruction qui va chercher la variable dans l'url
   ```
    @RequestParam(name="name", required=false, defaultValue="World")
   ```
   demande la variable name dans l'URL remplacer "name" par "greetName" nécessitera ?greetName=yourname
## question 17
On remarque que une table addresse a été ajouté dans la console
## question 18
```
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
```
@Autowired
```
va directement injecter la classe dans le fichier, ici toutes les addresses disponibles