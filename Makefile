up: 
	sudo docker build . -t burritobot
	sudo docker run -d --name burritobot burritobot

down:
	sudo docker stop burritobot
	sudo docker rm burritobot