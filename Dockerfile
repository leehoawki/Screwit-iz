FROM python:alpine
COPY iz /app/iz
COPY resource /app/resource
RUN pip install flask
RUN export FLASK_APP=/app/iz/app.py
EXPOSE 8080
ENTRYPOINT ["flask", "run", "--host=0.0.0.0", "-p", "8080"]