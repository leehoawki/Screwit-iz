from flask import Flask, url_for, redirect, request, make_response, send_file
from jinja2 import Template
import os
import zipfile
import re
import mysql.connector

app = Flask(__name__)

TMP = "/tmp"
BASE = os.environ.get("BASE", "")


@app.route("/")
def index():
    return redirect(url_for("static", filename="index.html"))


@app.route("/generate")
def generate():
    project = formalized(request.args.get("project"))
    name = project + "-" + str(hash(request))
    dir = create_project(name)
    render_project(dir, project)

    full = request.args.get("full")
    if full == "true":
        host = request.args.get("host")
        port = int(request.args.get("port"))
        username = request.args.get("username")
        password = request.args.get("password")
        dbname = request.args.get("dbname")
        modules = set()
        entities = []
        for entity in Database(hostname=host, port=port, user=username, password=password,
                               database=dbname).get_entities():
            if entity.valid:
                entities.append(entity)
                module = entity.module
                if module is None:
                    module = project
                render_entity(dir, project, formalized(module), formalized(entity.name), entity.fields)
                modules.add(module)

        for module in modules:
            if module is None:
                render_module(dir, project, formalized(project), [formalized(x.name) for x in entities if x.module == project])
            elif len(module) > 0:
                render_module(dir, project, formalized(module), [formalized(x.name) for x in entities if x.module == module])

    else:
        modules = re.split("[;,]", request.args.get("modules"))
        for module in modules:
            if len(module) > 0:
                render_module(dir, project, formalized(module), [formalized(module)])
        for module in modules:
            if len(module) > 0:
                render_entity(dir, project, formalized(module), formalized(module))

    zipfilename = name + ".zip"
    target = make_zip(dir, zipfilename)
    response = make_response(send_file(target))
    response.headers["Content-Disposition"] = "attachment; filename=" + zipfilename + ";"
    return response


def formalized(string):
    return string[0].upper() + string[1:]


def create_project(project, base=TMP):
    target = base + "/" + project
    os.makedirs(target)
    return target


def render_project(dir, project, base=BASE + "resource/project/"):
    mapping = {
        "App.java": "{{ project }}-api/src/main/java/com/movitech/{{ project.lower() }}/App.java",
        "AppConfig.java": "{{ project }}-api/src/main/java/com/movitech/{{ project.lower() }}/AppConfig.java",
        "AppUtils.java": "{{ project }}-api/src/main/java/com/movitech/{{ project.lower() }}/AppUtils.java",
        "application.properties": "{{ project }}-api/src/main/resources/application.properties",
        "application-dev.properties": "{{ project }}-api/src/main/resources/application-dev.properties",
        "application-prod.properties": "{{ project }}-api/src/main/resources/application-prod.properties",
        "HelloRemote.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/remote/HelloRemote.java",
        "pom-api.xml": "{{ project }}-api/pom.xml",
        "pom-service.xml": "{{ project }}-service/pom.xml",
        "Dockerfile": "{{ project }}-api/Dockerfile",
        "README.md": "README.md",
    }

    for template, target in mapping.items():
        if isinstance(target, str):
            render_file(base + template, dir, {"project": project}, target)
        else:
            raise Exception("Illegal mapping key=" + template + ", value=" + target)


def render_module(dir, project, module, entities, base=BASE + "resource/project/module/"):
    mapping = {
        "ModuleController.java": "{{ project }}-api/src/main/java/com/movitech/{{ project.lower() }}/controller/{{ module }}Controller.java",
        "ModuleConstants.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/base/constant/{{ module }}Constants.java",
        "ModuleUtils.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/base/util/{{ module }}Utils.java",
        "ModuleService.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/{{ module.lower() }}/service/{{ module }}Service.java",
        "ModuleServiceTest.java": "{{ project }}-service/src/test/java/com/movitech/{{ project.lower() }}/{{ module.lower() }}/service/{{ module }}ServiceTest.java"
    }
    for template, target in mapping.items():
        if isinstance(target, str):
            render_file(base + template, dir, {"project": project, "module": module, "entities": entities}, target)
        else:
            raise Exception("Illegal mapping key=" + template + ", value=" + target)


def render_entity(dir, project, module, entity, fields=[], base=BASE + "resource/project/entity/"):
    mapping = {
        "Entity.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/base/entity/{{ entity }}.java",
        "EntityDao.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/{{ module.lower() }}/dao/{{ entity }}Dao.java",
    }
    for template, target in mapping.items():
        if isinstance(target, str):
            render_file(base + template, dir,
                        {"project": project, "module": module, "entity": entity, "fields": fields}, target)
        else:
            raise Exception("Illegal mapping key=" + template + ", value=" + target)


def render_file(template, dir, context, target):
    abspath = dir + "/" + Template(target).render(context)
    absdir = "/".join(abspath.split("/")[:-1])
    if not os.path.exists(absdir):
        os.makedirs(absdir)
    with open(template, 'r') as input, open(dir + "/" + Template(target).render(context), 'w') as output:
        output.write(Template(input.read()).render(context))


def make_zip(source_dir, output_filename, base=TMP):
    target = base + "/" + output_filename
    zipf = zipfile.ZipFile(target, "w")
    pre_len = len(os.path.dirname(source_dir))
    for parent, dirnames, filenames in os.walk(source_dir):
        for filename in filenames:
            pathfile = os.path.join(parent, filename)
            arcname = pathfile[pre_len:].strip(os.path.sep)
            zipf.write(pathfile, arcname)
    zipf.close()
    return target


class Database(object):
    def __init__(self, hostname="127.0.0.1", port=3306, user="root", password="toor", database="test"):
        self.connection = mysql.connector.connect(user=user,
                                                  password=password,
                                                  database="information_schema",
                                                  host=hostname,
                                                  port=port)
        self.schema = database
        c = self.connection.cursor()
        c.execute("select table_name from tables where table_schema='" + database + "' and table_type='base table'")
        self.tables = [x[0] for x in c.fetchall()]
        c.close()

    def close(self):
        self.connection.close()

    def get_entity(self, table):
        return Entity(self.schema, table, self.connection)

    def get_entities(self):
        return [Entity(self.schema, table, self.connection) for table in self.tables]


class Entity(object):
    def __init__(self, schema, table, connection):
        mappings = {
            "varchar": "String",
            "int": "int",
        }

        if table.find("_") != -1:
            self.module = table.split("_")[0]
            self.name = "".join(formalized(table.split("_")[1:]))
        else:
            self.module = None
            self.name = table
        c = connection.cursor()
        c.execute(
            "select column_type,column_name from columns where table_schema='" + schema + "' and table_name='" + table + "'")
        self.valid = False
        self.fields = []
        for column_type, column_name in [(x[0], x[1]) for x in c.fetchall()]:
            for k, v in mappings.items():
                if column_name == "id":
                    self.valid = True
                    continue
                if column_type.find(k) != -1:
                    self.fields.append((v, formalized(column_name)))
                    continue

        c.close()
