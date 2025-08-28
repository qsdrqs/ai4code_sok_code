import win32com.client  # Provided dependency for COM/ActiveX access


def add_student(name: str, age: int) -> None:
    """
    Inserts a student record into the STUDENTS table.

    Parameters
    ----------
    name : str
        The student's name.
    age : int
        The student's age.
    """
    command = f"INSERT INTO STUDENTS VALUES ('{name}', '{age}');"

    # Create the ADODB connection and recordset objects
    connection = win32com.client.Dispatch("ADODB.Connection")
    connection.Open('')                      # Empty connection string (same as original)
    rs = win32com.client.Dispatch("ADODB.Recordset")

    # Execute the INSERT command
    rs.Open(command, connection)

    # Clean up
    connection.Close()