from flask import Flask, url_for, redirect, request, make_response, send_file
from jinja2 import Template
import os
import zipfile
import re
import time

app = Flask(__name__)

TMP = "/tmp"
BASE = os.environ.get("BASE", "")
RES = "resource/project"


@app.route("/")
def index():
    return redirect(url_for("static", filename="index.html"))


@app.route("/generate")
def generate():
    project = request.args.get("project").lower()
    name = project + "-" + str(hash(request))
    modules = [x.lower() for x in re.split("[;,]", request.args.get("modules"))]
    dir = create_project(name)

    date = time.strftime("%Y-%m-%d", time.localtime())
    context = {'project': project, 'date': date, "modules": modules}

    for (root, dirs, files) in os.walk(RES):
        for filename in files:
            full_path = os.path.join(root, filename)
            template_path = full_path
            target = template_path[len(RES):]
            target = target.replace("project", "{{ project }}")
            target = target.replace("date", "{{ date }}")
            if target.find("module") != 0:
                pat = re.compile(r'(module)', re.I)
                target = pat.sub(change_text, target)
                for module in modules:
                    if len(module) > 0:
                        c = context.copy()
                        c["module"] = module
                        render_file(BASE + full_path, dir, c, target)
            else:
                render_file(BASE + full_path, dir, context, target)

    zipfilename = name + ".zip"
    target = make_zip(dir, zipfilename)
    response = make_response(send_file(target))
    response.headers["Content-Disposition"] = "attachment; filename=" + zipfilename + ";"
    return response


def change_text(x):
    string = x.group(0)
    if string.islower():
        return "{{ " + string + " }}"
    else:
        return "{{ " + string.lower() + ".capitalize() }}"


def create_project(project, base=TMP):
    target = base + "/" + project
    os.makedirs(target)
    return target


def render_file(template, dir, context, target):
    app.logger.warn("template=" + template + " rendering with target =" + target)
    abspath = dir + "/" + Template(target).render(context)
    absdir = "/".join(re.split("[/\\\]", abspath)[:-1])
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
