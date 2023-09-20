from flask import Flask, render_template, request
from flask_wtf import FlaskForm
from steam.api import interface
from wtforms import StringField
from wtforms.validators import DataRequired
import requests
import steam
import json

steam_key = "3E419404A285B7E5CB6E98674D81ED6D" # Dont even try, its obv revoked. replace it with your own
steam.api.key.set(steam_key)


class UserSelectionForm(FlaskForm):
    first_user = StringField('first_user', validators=[DataRequired()])
    second_user = StringField('second_user', validators=[DataRequired()])


app = Flask(__name__)

app.config["WTF_CSRF_SECRET_KEY"] = 'a random string'
app.config["SECRET_KEY"] = 'a random string'


@app.route('/', methods=['GET', 'POST'])
def index():
    first_user = request.form.get('first_user')
    second_user = request.form.get('second_user')
    form = UserSelectionForm()

    if first_user is None or second_user is None:
        return render_template('index.jinja2', form=form)

    first_user_response_data = interface('IPlayerService').GetOwnedGames(steamid=first_user, include_appinfo=1)
    second_user_response_data = interface('IPlayerService').GetOwnedGames(steamid=second_user, include_appinfo=1)

    return render_template('index.jinja2', form=form, first_user_list=first_user_response_data['response'],
                           second_user_list=second_user_response_data['response'])


if __name__ == '__main__':
    app.run(debug=True)
