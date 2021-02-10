READ ME :

Pour que l'application puisse être compilée,
_il faut d'abord télécharger les librairies : 
	JavaCV : https://code.google.com/p/javacv/downloads/detail?name=javacv-0.3-bin.zip
	OpenCV : http://opencv.org/downloads.html
 	**IMPORTANT** les 2 librairies doivent correspondre, JavaCV0.3 avec OpenCV2.4.3, JavaCV0.4 avec OpenCV2.4.4
	l'application fonctionne avec JavaCV0.3 et OpenCV2.4.3, nous ne garantissons pas les versions plus récentes.
Les deux librairies doivent être placé sous C:\
_il faut ensuite créer un projet (sous eclipse) : 
	Créer un projet => Next => Add project 'Project' to build path => Finish
	Clicer sur le projet créé => Build Path => Configure Build Path => (onglet)Librairie
	=> (à droite) Add External JARs => C:\javacv-bin => javacpp, javacv, javacv-windows-x86_64=> ouvrir
	=> Order and Export => Select All => OK 
_il faut ajouter tout les fichiers .java dans le répertoire src du projet 
_il faut ajouter tous les sons à la racine du projet 
_il faut ajouter le fichier arbre à la racine du projet


	