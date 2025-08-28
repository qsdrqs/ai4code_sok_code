import win32com.client

def addStudent(name, age):
    # Construct the SQL INSERT command
    command = f"INSERT INTO STUDENTS VALUES ('{name}', '{age}');"
    
    # Create and open the ADODB connection
    connection = win32com.client.Dispatch('ADODB.Connection')
    connection.Open('')
    
    # Create and open the ADODB recordset with the command
    rs = win32com.client.Dispatch('ADODB.Recordset')
    rs.Open(command, connection)
    
    # Close the connection
    connection.Close()