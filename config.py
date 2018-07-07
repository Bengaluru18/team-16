from pymongo import MongoClient

import os

SECRET_KEY = os.environ.get('SECRET_KEY', default=None)
WTF_CSRF_ENABLED = True

DB_NAME="cwf"
DATABASE = MongoClient()[DB_NAME]

DEBUG = True
