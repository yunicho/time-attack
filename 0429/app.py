from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient
from datetime import datetime

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.test


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/detail/<idx>')
def detail(idx):
    return {"result": "success"}


@app.route('/post', methods=['POST'])
def save_post():
    title_receive = request.form['title_give']

    today = datetime.now()
    timenow = today.strftime('%Y-%m-%d-%H-%M-%S')

    doc = {
        'title': title_receive,
        'image': timenow,
    }
    db.memo.insert_one(doc)

    return {"result": "success"}


@app.route('/post', methods=['GET'])
def get_post():
    memos = list(db.memo.find({}, {'_id': False}))
    return jsonify({'result': 'success', 'memos': memos})



@app.route('/post', methods=['DELETE'])
def delete_post():
    idx = request.args.get('idx')
    db.test.delete_one({'idx': int(idx)})
    return {"result": "success"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
