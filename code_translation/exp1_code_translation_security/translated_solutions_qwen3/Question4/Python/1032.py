def addStudent(name, age):
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    return sql

# addStudent("XXXXX", 0)