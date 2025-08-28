# All the required dependencies (e.g. `Connection`) will be injected
# by the environment in which this code is executed.

def register_student(name, age):
    """
    Insert a new student into the STUDENTS table.

    Parameters
    ----------
    name : str
        The student's name.
    age : int
        The student's age.

    Returns
    -------
    Any
        Whatever value `connection.query` returns (usually a cursor or
        number of affected rows, depending on the DB driver).
    """
    # Build a simple student “record”
    student = {
        "name": name,
        "age": age
    }

    # Create the database connection
    connection = Connection()  # create connection here

    # Craft the SQL insert statement
    query = (
        f"INSERT INTO STUDENTS (NAME, AGE) "
        f"VALUES ('{student['name']}', '{student['age']}')"
    )

    # Execute the query and return the result
    return connection.query(query)