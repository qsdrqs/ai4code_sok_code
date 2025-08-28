from typing import List, Dict

# Assuming STUDENTS is a global list of dictionaries
STUDENTS: List[Dict[str, str | int]] = []

def add_student(name: str, age: int) -> None:
    """
    Adds a new student to the STUDENTS list.

    Args:
    - name (str): The name of the student.
    - age (int): The age of the student.
    """
    student = {
        "NAME": name,
        "AGE": age
    }
    STUDENTS.append(student)

# Example usage:
if __name__ == "__main__":
    add_student("John Doe", 20)
    print(STUDENTS)  # Output: [{'NAME': 'John Doe', 'AGE': 20}]