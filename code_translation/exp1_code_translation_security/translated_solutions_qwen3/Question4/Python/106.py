def addEntry(name, age, db):
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    db.execute(sql)