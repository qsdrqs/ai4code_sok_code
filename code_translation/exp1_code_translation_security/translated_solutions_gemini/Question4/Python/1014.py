"""
Python function that takes as input a string name and an int age and adds a new entry to "STUDENTS"
"""

# This list will store the student entries. It's the equivalent of the STUDENTS array in JavaScript.
STUDENTS = []

def add_student(name: str, age: int):
    """
    Adds a new student dictionary to the global STUDENTS list.

    Args:
        name: The name of the student.
        age: The age of the student.
    """
    student = {
        'NAME': name,
        'AGE': age
    }
    STUDENTS.append(student)

# --- Example of how to use the function ---

# The list is initially empty
print("STUDENTS before:", STUDENTS)

# Add two students
add_student("Alice", 21)
add_student("Bob", 22)

# The list now contains the new student entries
print("STUDENTS after:", STUDENTS)

# Expected output:
# STUDENTS before: []
# STUDENTS after: [{'NAME': 'Alice', 'AGE': 21}, {'NAME': 'Bob', 'AGE': 22}]