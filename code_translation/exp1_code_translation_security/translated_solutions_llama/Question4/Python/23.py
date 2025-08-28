import psycopg2

def insert_student(name: str, age: int) -> None:
    """
    Inserts a new student record into the PostgreSQL database.

    Args:
    - name (str): The name of the student.
    - age (int): The age of the student.
    """
    try:
        # Connect to the PostgreSQL database
        conn = psycopg2.connect(
            dbname="your_database",
            user="your_username",
            password="your_password",
            host="your_host",
            port="your_port"
        )
        cursor = conn.cursor()

        # Create the STUDENTS table if it doesn't exist
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS STUDENTS
            (NAME VARCHAR(255), AGE INTEGER)
        ''')

        # Insert the new student record
        cursor.execute('INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)', (name, age))
        conn.commit()

        print("Student record inserted successfully.")
    except psycopg2.Error as e:
        print(f"An error occurred: {e}")
    finally:
        if conn:
            conn.close()

# Example usage
insert_student('XXXXX', 0)