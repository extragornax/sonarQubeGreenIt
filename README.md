## Plugin SonarQube d'éco-conception

Les bonnes pratiques présentes dans le plugin sont celles de la 2ème édition du référentiel paru en septembre 2015. 
Pour connaitre l'intégralité des règle d'eco-conception actuel visiter le lien -> https://docs.google.com/spreadsheets/d/1nujR4EnajnR0NSXjvBW3GytOopDyTfvl3eTk2XGLh5Y/edit#gid=1386834576

## Liens

## Architecture du projet
Voici un aperçu de l'architecture du projet :
```

sonarQubeGreenIt        # Dossier racine du projet (non versionner) contient l'ensemble du projet en lui même
|
+--css-linter           # Repertoire du linter CSS 
|  |
+--js-linter            # Repertoire du linter JS
|  |
+--native-analyzer      # Répertoire du project maven des plugins dit "natif" saonarqube
|  |
|  +--java-plugin       # Contiens le module JAVA
|  |
|  +--php-plugin        # Contiens le module PHP
|  |
|  \--python-plugin     # Contient le module Python
|
\--docker-compose.yml   # Docker compose file qui installe automatiquement les plugins natifs si ces derniers ont bien été générer cf // TODO
```

Vous pouvez plus d'informations sur l'architecture des différents linters et plugins natifs dans leurs README respectifs.


## Docker compose 
web_1  | [1]: max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]

sudo sysctl -w vm.max_map_count=262144


/home/gael/IdeaProjects/sonar-scanner-cli-4.6.0.2311-linux/sonar-scanner-4.6.0.2311-linux/bin/sonar-scanner -X   -Dsonar.projectKey=sonar-custom-plugin-example   -Dsonar.sources=.   -Dsonar.host.url=http://localhost:9000   -Dsonar.login=238adee074ab6857357ceb35cf3ede6f3c07d881   -Dsonar.eslint.reportPaths=/home/gael/IdeaProjects/myCustomPlugin/js-linter/eslint-report.json


## Comment contribuer

## Auteur

Gaël Pellevoizin

## Licence











