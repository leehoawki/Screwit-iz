FROM python:alpine
COPY iz /iz
COPY iz/resource /resource
RUN pip install flask
RUN pip install mysql-connector-python
ENV FLASK_APP /iz/app.py
ENV BASE /
EXPOSE 8080
ENTRYPOINT ["flask", "run", "--host=0.0.0.0", "-p", "8080"]