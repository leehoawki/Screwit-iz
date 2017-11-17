FROM python:alpine
COPY iz /iz
COPY resource /resource
RUN pip install flask
EXPOSE 8080
ENTRYPOINT ["export", "FLASK_APP=/iz/app.py", "&&", "flask", "run", "--host=0.0.0.0", "-p", "8080"]