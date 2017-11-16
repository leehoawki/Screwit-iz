FROM python:alpine
COPY src /src/
RUN pip install flask
RUN export FLASK_APP=/src/app.py
EXPOSE 8080
ENTRYPOINT ["flask", "run", "--host=0.0.0.0", "-p", "8080"]