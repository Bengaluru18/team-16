from modules import *


app = Flask(__name__)


def tokenize(_id):

    """
    function to generate a tokenized id to track user
    """
    return str(jwt.encode({"id" : str(_id)}, secret, algorithm = 'HS256'))


def untokenize(token):

    """
    function to retrive id from a token
    """
    payload = jwt.decode(token, verify = False)
    return ObjectId(payload["id"])


@app.route('/', methods=['GET'])
def index():

    """
    health check function
    """
    return jsonify({"Status": 200, "Message": "Connected"})


@app.route('/signup', methods=['POST'])
def signup():

    """
    function to add a user
    """
    req = request.args.to_dict(flat=False)
    try:
        db['users'].insert_one({"name": req['name'][0],
                                "password": hashlib.md5(
                                    req['password'][0].encode()).hexdigest(),
                                "employee_id": req['employee_id'][0],
                                "email": req['email'][0]})
        status = 200
        message = "Successfully added user."
    except:
        status = 500
        message = "Unable to register user. Please try again later"
    return jsonify({'status': status, 'message': message})


@app.route('/login', methods=['POST'])
def login():

    """
    function to authenticate and login a user
    """
    req = request.args.to_dict(flat=False)
    emp_id = req['employee_id'][0]
    password = req['password'][0]
    hash_password = hashlib.md5(password.encode()).hexdigest()

    user_cred = db['users'].find_one({'employee_id': str(emp_id), 'password': hash_password})

    #if the user exists in the database and the password is correct
    if user_cred != None:
            token = tokenize(user_cred['_id'])
            db['logged_in_users'].insert_one({'employee_id': emp_id, 'token': token})
            status = 200
            message = "Successfully logged in."
    else:
        status = 404
        message = "Invalid credentials."
        token = None
    return jsonify({'status': status, 'message': message, 'token': token})


@app.route('/inputform', methods=['POST'])
def inputform():

    """
    function to accept form data from field
    """
    req = request.args.to_dict(flat=False)
    school_id = req['school_name'][0]
    token = req['token'][0]
    emp_id = untokenize(token)

    try:
        data = dict()
        for i in req.keys():
            if i != 'school_name' and i != 'token':
                data[i] = req[i][0]
        data['approval'] = False
        data['task'] = {}
        db['schools'].insert_one(data)
        status = 200
        message = "Successully added data."
    except:
        status = 500
        message = "Unable to add data. Please try again."
    return jsonify({"status": status, "message", message})


@app.route('/getschools', methods=['POST'])
def getschools():

    """
    function to return the name and id of all approved schools
    """
    try:
        schools = list(db['schools'].find({'approved': True}, {'school_name': 1, '_id': 1}))
        status = 200
        message = "Successfully retreived data about schools."
    except:
        status = 500
        message = "Unable to retreive data. Please try again"
    return jsonify({'status': status, 'message': message, 'data': schools})


@app.route('/addtask', methods=['POST'])
def addtask():

    """
    function to add task to the action plan
    """
    req = request.args.to_dict(flat=False)
    school_id = req['school_id'][0]
    token = req['token'][0]
    tasks = dict()
    for i in req.keys():
        if i != 'school_id' and i != 'token':
            tasks[i] = req[i][0]
    try:
        db['schools'].update({"_id": ObjectId(school_id)}, {'$set': {'task': tasks}})
        status = 200
        message = "Tasks were successfully added."
    except:
        status = 500
        message = "Unable to add task. Please try again later."
    return jsonify({'status': status, 'message': message})


@app.route(/logout, methods=['POST'])
def logout():

    """
    function to delete token from database and logout
    """
    token = request.args.to_dict(flat=False)['token'][0]
    try:
        db['logged_in_users'].remove({'token': token})
        status = 200
        message = "You have successfully logged out."
    except:
        status = 500
        message = "There was an error. Please try again later."
    return jsonify({'status': status, 'message': message})

if __name__ == "__main__":
      app.run(host='0.0.0.0', port=3000, debug=True)
