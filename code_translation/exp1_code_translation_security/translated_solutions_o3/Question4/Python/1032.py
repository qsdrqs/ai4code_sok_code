def add_student(name, age):
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    return sql

# add_student("XXXXX", 0)