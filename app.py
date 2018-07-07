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


@app.route('/login', methods=['POST'])
def login():
    emp_id = request.json['employee_id']
    print (emp_id)
    password = request.json['password']
    hash_password = hashlib.md5(password.encode()).hexdigest()

    user_cred = db['users'].find_one({"employee_id": str(emp_id)})
    print (user_cred)
    #if the user exists in the database
    if user_cred != None:

        # authenticate user
        if hash_password == user_cred['password']:
            # create a token of 16 bits to identify a session
            token = os.urandom(16).hex()
            db['logged_in_users'].insert_one({'employee_id': emp_id, 'token': token})
            status = 200
            message = "Successfully logged in."
        else:
            status = 401
            message = "Invalid password"
            token = None
    else:

        status = 404
        message = "Employee id was not found."
        token = None
    return jsonify({'status': status, 'message': message, 'token': token})


if __name__ == "__main__":
    app.run(debug=True)
