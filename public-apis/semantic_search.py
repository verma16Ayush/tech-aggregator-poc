import os
from dotenv import load_dotenv
import mysql.connector
from mysql.connector import Error
from haystack.document_stores import InMemoryDocumentStore
from haystack.nodes import DensePassageRetriever, FARMReader
from haystack.pipelines import ExtractiveQAPipeline
from flask import Flask, request, jsonify
from flask_cors import CORS, cross_origin

def fetch_data_from_mysql(host, user, password, database, table):
    """Fetch data from MySQL table"""
    try:
        connection = mysql.connector.connect(
            host=host,
            user=user,
            password=password,
            database=database
        )
        cursor = connection.cursor(dictionary=True)
        cursor.execute(f"SELECT * FROM {table}")
        data = cursor.fetchall()
        cursor.close()
        return data
    except Error as e:
        print(f"Error fetching data from MySQL: {e}")
        return []

# Load environment variables from .env file
load_dotenv()

# Retrieve MySQL credentials from environment variables
host = os.getenv("DB_HOST")
user = os.getenv("DB_USER")
password = os.getenv("DB_PASSWORD")
database = os.getenv("DB_DATABASE")
table = os.getenv("TABLE_NAME")

# Fetch data from MySQL table
data = fetch_data_from_mysql(host, user, password, database, table)

data_json = [{
    'content': _["Description"] + _['Category'] + _["Auth"],
    'meta': {
        'category': _['Category'],
        'API': _["API"],
        'URL': _["URL"],
        "Auth": _["Auth"],
        "HTTPS": _["HTTPS"],
        "CORS": _["CORS"],
        "Description": _["Description"]
    }
} for _ in data]

document_store = InMemoryDocumentStore()
document_store.write_documents(data_json)
reader = FARMReader(model_name_or_path="distilbert-base-uncased-distilled-squad", use_gpu=False)
retriever = DensePassageRetriever(document_store=document_store)
document_store.update_embeddings(retriever=retriever)
pipeline = ExtractiveQAPipeline(reader, retriever)

# QUERY = "authentication"
# prediction = pipeline.run(query=QUERY)
# print(prediction)

flask_app = Flask(__name__)
cors = CORS(flask_app)
flask_app.config['CORS_HEADERS'] = 'Content-Type'

@flask_app.route('/')
def hello_world():
    return 'Hello, World!'

@flask_app.route('/api/run_query/<query>', endpoint='run_query')
def run_query(query):
    prediction = pipeline.run(query=query)
    return jsonify(prediction)

@flask_app.route('/api/onboard_api', methods=['POST'], endpoint='onboard_api')
def onboard_api():
    data = request.json
    data_json = [{
        'content': data["Description"] + data['Category'] + data["Auth"],
        'meta': {
            'category': data['Category'],
            'API': data["API"],
            'URL': data["URL"],
            "Auth": data["Auth"],
            "HTTPS": data["HTTPS"],
            "CORS": data["CORS"],
            "Description": data["Description"]
        }
    },]
    document_store.write_documents(data_json)
    document_store.update_embeddings(retriever=retriever)





if __name__ == '__main__':
    flask_app.run(debug=True)