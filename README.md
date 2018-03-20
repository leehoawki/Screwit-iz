# Screwit-INITIALIZR
Web scaffolding based on Flask help you to bootstrap your SpringBoot application on.

## Build And Run

Run it in dockers,
    
    docker build . -t screwit-iz
    docker tag screwit-iz $REGISTRY/screwit-iz
    docker push $REGISTRY/screwit-iz
    
    # Swarm Node
    docker service rm screwit-iz
    docker service create --replicas 3 --name screwit-iz --network=cluster --publish $NODEPORT:8080 $REGISTRY/screwit-iz
  
or run it locally.

    export FLASK_APP=iz/app.py
    export FLSAK_DEBUG=1
    flask run
