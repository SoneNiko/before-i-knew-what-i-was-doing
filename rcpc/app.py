import os

from flask import Flask

app = Flask(__name__)


@app.route('/')
def hello_world():  # put application's code here
    return 'Hello World!'


@app.route('/turn-off')
def turn_off():
    os.system("shutdown now")
    return "Done"


if __name__ == '__main__':
    app.run()
