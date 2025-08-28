def add_student(name, age):
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + str(age) + ")"
    return sql

# add_student("XXXXX", 0)