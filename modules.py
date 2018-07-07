from flask import Flask, jsonify, flash, redirect, request, session, abort
import os
import hashlib
from pymongo import MongoClient
from werkzeug.datastructures import ImmutableMultiDict
import jwt
from bson.objectid import ObjectId

UNAME = os.environ['m_lab_uname']
PSWD = os.environ['m_lab_pswd']
secret = os.environ['secret']
DB_NAME = 'cwf'

client = MongoClient('mongodb://%s:%s@ds113799.mlab.com:13799/cwf' %(UNAME, PSWD))
db = client[DB_NAME]
