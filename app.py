from datetime import datetime

from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.test


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/detail/<idx>')
def detail(idx):
    # todo
    return


@app.route('/articleList', methods=['GET'])
def get_article_list():
    article_list = db.test.find({})

    for article in article_list:
        article['reg_date'] = article['reg_date'].strftime('%Y.%m.%d %H:%M:%S')

    return jsonify({"article_list": article_list})


# Create
@app.route('/article', methods=['POST'])
def create_article():
    title_receive = request.form['title']
    content_receive = request.form['content']
    pw_receive = request.form['pw']
    post_count = db.test.estimated_document_count({})

    if post_count == 0:
        max_value = 1
    else:
        max_value = db.test.find_one(sort=[("idx", -1)])['idx'] + 1

    doc = {
        'idx': max_value,
        'title': title_receive,
        'comment': content_receive,
        'pw' : pw_receive,
        'reg_date': datetime.now()
    }

    db.memos.insert_one(doc)

    return {"result": "success"}


# Read
@app.route('/article', methods=['GET'])
def read_article():
    article = list(db.memos.find({}, {'_id': False}).sort([("reg_date", -1)]))
    return jsonify({"articles_list": article})
    for time in posts:
        time['reg_date'] = time['reg_date'].strftime('%Y.%m.%d %H:%M:%S')

# Update
@app.route('/article', methods=['PUT'])
def update_article():
    # todo
    return {"result": "success"}


# Delete
@app.route('/article', methods=['DELETE'])
def delete_article():
    idx = request.args.get('idx')
    db.test.delete_one({'idx': int(idx)})
    return {"result": "success"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
