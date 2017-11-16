from flask import Flask, url_for, redirect, request, make_response, send_file
from jinja2 import Template
import os
import zipfile

app = Flask(__name__)

TMP = "/tmp"


@app.route("/")
def index():
    return redirect(url_for("static", filename="index.html"))


@app.route("/generate")
def generate():
    project = get("project", "Demo")
    modules = get("modules", "Box")
    dir = create_project(project)
    ## render(dir)
    filename = project + "-" + str(hash(request)) + ".zip"
    target = make_zip(dir, filename)
    response = make_response(send_file(target))
    response.headers["Content-Disposition"] = "attachment; filename=" + filename + ";"
    return response


def get(param, value=""):
    p = request.args.get(param)
    if p is None or len(p) == 0:
        return value
    return p


def create_project(project, base=TMP):
    target = base + "/" + project
    if os.path.exists(base + "/" + project):
        os.removedirs(target)
    os.makedirs(target)
    return target


def render_project(context):
    mapping = {
        "App.java": "{{ project }}-api/src/main/java/com/movitech/{{ project }}/App.java",
        "AppConfig.java": "{{ project }}-api/src/main/java/com/movitech/{{ project }}/AppConfig.java",
        "AppUtils.java": "{{ project }}-api/src/main/java/com/movitech/{{ project }}/AppUtils.java",
        "application.properties": "{{ project }}-api/src/main/resources/application.properties",
        "application-dev.properties": "{{ project }}-api/src/main/resources/application-dev.properties",
        "application-prod.properties": "{{ project }}-api/src/main/resources/application-prod.properties",
        "Hello.java": "{{ project }}-service/src/main/java/com/movitech/{{ project }}/base/remote/Hello.java",
        "pom-api.xml": "{{ project }}-api/pom.xml",
        "pom-service.xml": "{{ project }}-service/pom.xml",
        "Dockerfile": "{{ project }}-api/Dockerfile",
        "README.md": "README.md",
        "module": render_module
    }

    for key, value in mapping:
        if isinstance(value, str):
            render_file(key, context, Template(value).render(context))
        elif isinstance(value, function):
            render_module(key, context)
        else:
            raise Exception("Illegal mapping key=" + key + ", value=" + value)

    return None


def render_module(module, context):
    mapping = {
        "ModuleController.java": "{{ project }}-api/src/main/java/com/movitech/{{ project }}/controller/{{ module }}Controller.java",
        "Module.java": "{{ project }}-service/src/main/java/com/movitech/{{ project }}/base/entity/{{ module }}.java",
        "ModuleDao.java": "{{ project }}-service/src/main/java/com/movitech/{{ project }}/dao/{{ module }}Dao.java",
        "ModuleService.java": "{{ project }}-service/src/main/java/com/movitech/{{ project }}/service/{{ module }}Service.java",
        "ModuleServiceTest.java": "{{ project }}-service/src/test/java/com/movitech/{{ project }}/service/{{ module }}ServiceTest.java"
    }
    return None


def render_file(template, context, target):
    with open(template, 'r') as input, open(target, 'w') as output:
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


if __name__ == '__main__':
    render_file("resource/project/module/ModuleService.java", {"module": "Box"}, "wa")
