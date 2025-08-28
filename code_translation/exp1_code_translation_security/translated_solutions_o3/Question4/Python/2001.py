def add(name, age):
    # Add name, age to students table in MySQL
    query = "INSERT INTO students (name, age) VALUES (%s, %s)"
    cursor = connection.cursor()
    try:
        cursor.execute(query, (name, age))
        connection.commit()
        print(f"Added {name} to students table")
    except Exception as err:
        connection.rollback()
        raise err
    finally:
        cursor.close()