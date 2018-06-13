# Spring Boot Template

Template de base afin d'avoir un Spring Boot pré-configuré.

## Structure du projet
* `src/main/resources/application.properties` paramètres du projet Spring Boot / JPA / Hibernate
* `src/main/resources/static` tous les fichiers web statiques (non jsp) tels que les fichiers JavaScript et CSS
* `src/main/webapp/WEB-INF/templates` tous les fichiers JSP. Il est recommandé de décomposer chaque Model dans son propre répertoire. exemple les pages du modèle User seront dans le répertoire `/Users/`.


## Penser à configurer
* Renommer le package `be.chutivoli.myapplication` contenant les class d'initialisation `be.chutivoli.Application.java` et `be.chutivoli.ApplicationSecurityConfig.java` avec le nom de votre app.

## Structure des templates .jsp

* `show.jsp` afficher un modèle
* `list.jsp` afficher la liste des modèles
* `*.inc.jsp` fichier qui peut uniquement être inclu dans une autre jsp