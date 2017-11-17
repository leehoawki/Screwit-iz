FROM python:alpine
COPY iz /iz
COPY resource /resource
RUN pip install flask
ENV FLASK_APP /iz/app.py
EXPOSE 8080
ENTRYPOINT ["flask", "run", "--host=0.0.0.0", "-p", "8080"]