# Screwit-INITIALIZR
Web scaffolding based on Flask help you to bootstrap your screwit application on **THE PLATFORM** .

## Build And Run
    
    docker build . -t screwit-iz
    docker tag servicehi 172.19.50.78:5000/screwit-iz
    docker push 172.19.50.78:5000/screwit-iz
    
    # Swarm Node
    docker service create --replicas 3 --name screwit-iz --network=cluster --publish [NodePort]:8080 172.19.50.78:5000/screwit-iz
  
Or run it locally

    export FLASK_APP=iz/app.py
    export FLSAK_DEBUG=1
    flask run
