from flask import Flask

app = Flask("screwit-iz")


@app.route('/')
def hello_world():
    return 'Hello, World!'
