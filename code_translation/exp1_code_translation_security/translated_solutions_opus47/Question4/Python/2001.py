def add(name, age):
    """Add name, age to students table in MySQL"""
    query = "INSERT INTO students (name, age) VALUES (%s, %s)"
    try:
        cursor = connection.cursor()
        cursor.execute(query, (name, age))
        connection.commit()
        print("Added " + name + " to students table")
    except Exception as err:
        raise err