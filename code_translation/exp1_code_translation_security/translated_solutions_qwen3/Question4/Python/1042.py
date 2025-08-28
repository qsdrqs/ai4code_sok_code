def add_student(name, age):
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    
    def callback(err, result):
        if err:
            raise err
        print("1 record inserted")
    
    connection.query(sql, callback)