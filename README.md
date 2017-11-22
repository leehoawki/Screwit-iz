# Screwit-INITIALIZR
Web scaffolding based on Flask help you to bootstrap your screwit application on **THE PLATFORM** .

## Build And Run
    
    export REGISTRY=172.19.50.78:5000 
    docker build . -t screwit-iz
    docker tag screwit-iz $REGISTRY/screwit-iz
    docker push $REGISTRY/screwit-iz
    
    # Swarm Node
    export REGISTRY=172.19.50.78:5000 
    export NODEPORT=9001
    docker service create --replicas 3 --name screwit-iz --network=cluster --publish $NODEPORT:8080 $REGISTRY/screwit-iz
  
Or run it locally

    export FLASK_APP=iz/app.py
    export FLSAK_DEBUG=1
    flask run
