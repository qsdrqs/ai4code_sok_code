# @requires variable name {connection} corresponding to a mysql db connection
def add_student(name, age):
    q = """INSERT INTO STUDENTS(NAME, AGE)
           VALUES(%s, %s)"""
    v = (name, age)
    cursor = connection.cursor()
    cursor.execute(q, v)
    connection.commit()
    cursor.close()