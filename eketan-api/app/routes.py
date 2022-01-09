'''
import pymysql
from app import app
from config import mysql
from flask import jsonify
from flask import flash, request
		
@app.route('/add', methods=['POST','GET'])
def add_emp():
	try:
		_json = request.json
		_name = _json['name']
		_email = _json['email']
		_phone = _json['phone']
        _address = _json['address']		
		if _name and _email and _phone and _address and request.method == 'POST':			
			sqlQuery = "INSERT INTO rest_emp(name, email, phone, address) VALUES(%s, %s, %s, %s, %s)"
			bindData = (_name, _email, _phone, _address)
			conn = mysql.connect()
			cursor = conn.cursor()
			cursor.execute(sqlQuery, bindData)
			conn.commit()
			respone = jsonify('Employee added successfully!')
			respone.status_code = 200
			return respone
		else:
			return not_found()
	except Exception as e:
		print(e)
	finally:
		cursor.close() 
		conn.close()
		
@app.route('/emp')
def emp():
	try:
		conn = mysql.connect()
		cursor = conn.cursor(pymysql.cursors.DictCursor)
		cursor.execute("SELECT id, name, email, phone, address FROM rest_emp")
		empRows = cursor.fetchall()
		respone = jsonify(empRows)
		respone.status_code = 200
		return respone
	except Exception as e:
		print(e)
	finally:
		cursor.close() 
		conn.close()
		
@app.route('/emp/<int:id>')
def emp(id):
	try:
		conn = mysql.connect()
		cursor = conn.cursor(pymysql.cursors.DictCursor)
		cursor.execute("SELECT id, name, email, phone, address FROM rest_emp WHERE id =%s", id)
		empRow = cursor.fetchone()
		respone = jsonify(empRow)
		respone.status_code = 200
		return respone
	except Exception as e:
		print(e)
	finally:
		cursor.close() 
		conn.close()

@app.route('/update', methods=['PUT'])
def update_emp():
	try:
		_json = request.json
		_id = _json['id']
		_name = _json['name']
		_email = _json['email']
		_phone = _json['phone']
                _address = _json['address']
                if _name and _email and _phone and _address and _id and request.method == 'PUT':			
			sqlQuery = "UPDATE rest_emp SET name=%s, email=%s, phone=%s, address=%s WHERE id=%s"
			bindData = (_name, _email, _phone, _address, _id,)
			conn = mysql.connect()
			cursor = conn.cursor()
			cursor.execute(sqlQuery, bindData)
			conn.commit()
			respone = jsonify('Employee updated successfully!')
			respone.status_code = 200
			return respone
		else:
			return not_found()	
                except Exception as e:
		 print(e)
	        finally:
		 cursor.close() 
		 conn.close()
@app.route('/delete/<int:id>', methods=['DELETE'])
def delete_emp(id):
	try:
		conn = mysql.connect()
		cursor = conn.cursor()
		cursor.execute("DELETE FROM rest_emp WHERE id =%s", (id,))
		conn.commit()
		respone = jsonify('Employee deleted successfully!')
		respone.status_code = 200
		return respone
	except Exception as e:
		print(e)
	finally:
		cursor.close() 
		conn.close()
		
@app.errorhandler(404)
def not_found(error=None):
    message = {
        'status': 404,
        'message': 'Record not found: ' + request.url,
    }
    respone = jsonify(message)
    respone.status_code = 404
    return respone
# CRUD Operation - CREATE 
@app.route('/add', methods=['POST'])
def add_book():
	try:
		json = request.json
		Book_name = json['book_name']
		Author_name = json['author_name']
		Publisher_name = json['publisher_name']
		if Book_name and Author_name and Publisher_name and request.method == 'POST':
			SQL_Query = "INSERT INTO books(Book_name, Author_name, Publisher_name) VALUES(%s, %s, %s)"
			data = (Book_name, Author_name, Publisher_name,)
			connection =MySql.connect()
			Pointer = connection.cursor()
			Pointer.execute(SQL_Query, data)
			connection.commit()
			response = jsonify('Book added!')
			response.status_code = 200
			return response
		else:
			return not_found()
	except Exception as e:
		print(e)
	finally:
		Pointer.close() 
		connection.close()
		
#CRUD Operation - READ			
@app.route('/book/<int:id>', methods=['POST', 'GET'])
def book(id):
	try:
        if(request.method == 'POST'):
            username = request.form['username']
            password = request.form['password']
            curl = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
            curl.execute("SELECT * FROM dosen WHERE name=%s",(username,))
            user = curl.fetchone()
            curl.close()

		connection = mysql.connect()
		Pointer = connection.cursor(pymysql.cursors.DictCursor)
		Pointer.execute("SELECT * FROM dosen WHERE book_id=%s", id)
		record = Pointer.fetchone()
		response = jsonify(record)
		response.status_code = 200
		return response
	except Exception as e:
		print(e)
	finally:
		Pointer.close() 
		connection.close()

#CRUD Operation - UPDATE	
@app.route('/update', methods=['POST'])
def update_book():
	try:
		json = request.json
		id = json['id']
		Book_name = json['Book_name']
		Author_name = json['Author_name']
		Publisher_name = json['Publisher_name']		
		if Book_name and Author_name and Publisher_name and id and request.method == 'POST':
			SQL_Query = "UPDATE books SET Book_name=%s, Author_name=%s, Publisher_name=%s WHERE book_id=%s"
			data = (Book_name, Author_name, Publisher_name, id,)
			connection =MySql.connect()
			Pointer = connection.cursor()
			Pointer.execute(sql, data)
			connection.commit()
			response = jsonify('Book updated!')
			response.status_code = 200
			return response
		else:
			return not_found()
	except Exception as e:
		print(e)
	finally:
		Pointer.close() 
		connection.close()

#CRUD Operation - DELETE		
@app.route('/delete/<int:id>')
def delete_book(id):
	try:
		connection =MySql.connect()
		Pointer = connection.cursor()
		Pointer.execute("DELETE FROM books WHERE book_id=%s", (id,))
		connection.commit()
		response = jsonify('book deleted!')
		response.status_code = 200
		return response
	except Exception as e:
		print(e)
	finally:
		Pointer.close() 
		connection.close()
'''
from app import app
from flask import jsonify, request, json, Response
import array

#from flaskext.mysql import MySQL
from flask_mysqldb import MySQL,MySQLdb

# MySQL configurations
app.secret_key = "rizks"
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'root'
app.config['MYSQL_DB'] = 'flaskdb'
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'
mysql = MySQL(app)

conn = MySQLdb.connect(host="localhost",
                           user = "root",
                           passwd = "root",
                           db = "api_flask_mobile")

#CRUD Operation - READ
@app.route("/")		
@app.route("/loginapi", methods=['POST', 'GET'])
def loginapi():
	if request.method == 'POST':
		username = request.form['username']
		password = request.form['password']
		curl = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
		curl.execute("SELECT * FROM dosen WHERE username=%s",(username,))
		record = curl.fetchone()
		curl.close() 
		if(record and password == record['password']):
				data ={
					'response': True,
					'payload': {
						'username' : record['username'],
						'password' : record['password']
					}
				}
		else :
				data = {
					'response': False,
					'payload': None
				}
		#sorting result in descending order by keys:
		sorted_string = json.dumps(data, sort_keys=False)
		#sorted_string = json.loads(sorted_string)
		#print(sorted_string)
		return sorted_string


@app.route('/hasilapi', methods=['POST', 'GET'])
def hasilapi():
	if request.method == 'POST':
		username = request.form['username']
		password = request.form['password']
		curl = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
		curl.execute("SELECT dosen.username, dosen.password, perkuliahan.nama_dosen, perkuliahan.mata_kuliah, hasil_deteksi.presentasi_bersemangat from perkuliahan inner join dosen on perkuliahan.nama_dosen = dosen.nama_dosen inner join hasil_deteksi on hasil_deteksi.id_perkuliahan = perkuliahan.id_perkuliahan where dosen.username = %s",(username,))
		record = curl.fetchall()
		curl.close()
		#record = jsonify(record)
		
		data_record = record
		data_1 = []
		data = {}
		for record in data_record :
			if(record and password == record['password']):
				data = {
					"response": True,
					"payload": {
						"username" : record['username'],
						"password" : record['password']
					},
					"payload_2":{
						"nama_dosen": record['nama_dosen'],
						"mata_kuliah": record['mata_kuliah'],
						"presentasi_bersemangat": record['presentasi_bersemangat']
					}
				}
				data_1.append(data)
				data = {}
			else :
				data = {
					"response": False,
					"payload": None,
					"payload_2":None
				}
				data_1.append(data)
				data = {}

		sorted_string = json.dumps(data_1, sort_keys=False)
		#sorted_string = json.loads(sorted_string)
		#print(sorted_string)
		return sorted_string

@app.route('/hasilapi1', methods=['POST', 'GET'])
def hasilapi1():
	if request.method == 'POST':
		username = request.form['username']
		password = request.form['password']
		curl = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
		curl.execute("SELECT dosen.username, dosen.password, perkuliahan.nama_dosen, perkuliahan.mata_kuliah, hasil_deteksi.presentasi_bersemangat from perkuliahan inner join dosen on perkuliahan.nama_dosen = dosen.nama_dosen inner join hasil_deteksi on hasil_deteksi.id_perkuliahan = perkuliahan.id_perkuliahan where dosen.username = %s",(username,))
		record = curl.fetchone()
		record2 = curl.fetchall()
		curl.close()
		
		data_record = record2
		data_payload_2 = []
		data = {}
		for record in data_record :
			if(record and password == record['password']):
				data = {
						"nama_dosen": record['nama_dosen'],
						"mata_kuliah": record['mata_kuliah'],
						"presentasi_bersemangat": record['presentasi_bersemangat']
					}
				data_payload_2.append(data)
				data = {}
			else :
				data = {}
				data_payload_2.append(data)
				data = {}

		if(record and password == record['password']):
				data_bundle = {
					'response': True,
					'payload': {
						'username' : record['username'],
						'password' : record['password']
					},
					'payload_2': data_payload_2
				}
		else :
				data_bundle = {
					'response': False,
					'payload': None,
					'payload_2' : None
				}
		

		sorted_string = json.dumps(data_bundle, sort_keys=False)
		#sorted_string = json.loads(sorted_string)
		#print(sorted_string)
		return sorted_string

@app.route("/loginapi1", methods=["GET", "POST"])
def hasilapi2():        
	if request.method == 'POST':
		username = request.form['username']
		password = request.form['password']
		cur = conn.cursor(MySQLdb.cursors.DictCursor)
		cur.execute("SELECT * FROM users WHERE username=%s",(username,))
		record = cur.fetchone()
		cur.close() 
		if(record and password == record['password']):
				data ={
					'response': True,
					'payload': {
						'username' : record['username'],
						'fullname' : record['fullname']
					}
				}
		else :
				data = {
					'response': False,
					'payload': None
				}
		#sorting result in descending order by keys:
		sorted_string = json.dumps(data, sort_keys=False)
		#sorted_string = json.loads(sorted_string)
		#print(sorted_string)
		return sorted_string

'''@app.route("/hasilapi2", methods=["GET", "POST"])
def hasilapi2():
	if request.method == 'POST':
		username = request.form['username']
		password = request.form['password']
		curl = conn.cursor(MySQLdb.cursors.DictCursor)
		cur = conn.cursor(MySQLdb.cursors.DictCursor)
		curl.execute("SELECT * FROM users WHERE username=%s",(username,))
		cur.execute("SELECT * FROM traditions")
		record = curl.fetchone()
		record2 = cur.fetchall()
		curl.close()
		cur.close()

		data_record = record
		data_record2 = record2
		data_payload_2 = []
		data = {}
		for record2 in data_record2 :
			data = {
						"nama_dosen": record2['nama'],
						"mata_kuliah": record2['daerah'],
						"presentasi_bersemangat": record2['keterangan']
					}
			data_payload_2.append(data)
			data = {}

		
		if(record and password == record['password']):
					data_bundle = {
						'response': True,
						'payload': {
							'username' : record['username'],
							'password' : record['password']
						},
						'payload_2': data_payload_2
					}
		else :
					data_bundle = {
						'response': False,
						'payload': None,
						'payload_2' : None
					}
		

		sorted_string = json.dumps(data_bundle, sort_keys=False)
		#sorted_string = json.loads(sorted_string)
		#print(sorted_string)
		return sorted_string'''