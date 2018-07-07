from flask import Flask, jsonify, flash, redirect, request, session, abort
import os
import hashlib
from pymongo import MongoClient
app = Flask(__name__)


client = MongoClient()
db = client['cwf']

@app.route('/signup', methods=['POST'])
def signup():
    print (request.json)
    try:
        db['users'].insert_one({"name": request.json['name'],
                                "password": hashlib.md5(
                                    request.json['password'].encode()).hexdigest(),
                                "employee_id": request.json['emp_id'],
                                "email": request.json['email']})
        status = 200
        message = "Successfully added user."
    except:
        status = "500"
        message = "Unable to add the user."

    return jsonify({status: message})


@app.route('/login', method=[POST])
def login():
    username =

if __name__ == "__main__":
    app.run(debug=True)
