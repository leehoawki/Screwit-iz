from flask import Flask, url_for, redirect, request, make_response, send_file
from jinja2 import Template
import os
import zipfile
import re

app = Flask(__name__)

TMP = "/tmp"
BASE = os.environ.get("BASE", "")


@app.route("/")
def index():
    return redirect(url_for("static", filename="index.html"))


@app.route("/generate")
def generate():
    project = formalized(get("project", "Demo"))
    name = project + "-" + str(hash(request))
    dir = create_project(name)
    render_project(dir, project)

    modules = get("modules", "Box")
    for module in re.split("[;,]", modules):
        if len(module) > 0:
            render_module(dir, project, formalized(module))
    zipfilename = name + ".zip"
    target = make_zip(dir, zipfilename)
    response = make_response(send_file(target))
    response.headers["Content-Disposition"] = "attachment; filename=" + zipfilename + ";"
    return response


def formalized(string):
    return string[0].upper() + string[1:]


def get(param, value=""):
    p = request.args.get(param)
    if p is None or len(p) == 0:
        return value
    return p


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
        "Hello.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/base/remote/Hello.java",
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


def render_module(dir, project, module, base=BASE + "resource/project/module/"):
    mapping = {
        "ModuleController.java": "{{ project }}-api/src/main/java/com/movitech/{{ project.lower() }}/controller/{{ module }}Controller.java",
        "Module.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/base/entity/{{ module }}.java",
        "ModuleDao.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/{{ module.lower() }}/dao/{{ module }}Dao.java",
        "ModuleService.java": "{{ project }}-service/src/main/java/com/movitech/{{ project.lower() }}/{{ module.lower() }}/service/{{ module }}Service.java",
        "ModuleServiceTest.java": "{{ project }}-service/src/test/java/com/movitech/{{ project.lower() }}/{{ module.lower() }}/service/{{ module }}ServiceTest.java"
    }
    for template, target in mapping.items():
        if isinstance(target, str):
            render_file(base + template, dir, {"project": project, "module": module}, target)
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
