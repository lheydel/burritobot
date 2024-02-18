run: 
	sudo docker build . -t burritobot
	sudo docker run -d --name burritobot burritobot