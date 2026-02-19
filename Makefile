up:
	sudo docker build . -t burritobot
	sudo docker run --restart=always -d --env-file .env --name burritobot burritobot

down:
	sudo docker stop burritobot
	sudo docker rm burritobot

restart: down up

logs:
	sudo docker logs -f burritobot
