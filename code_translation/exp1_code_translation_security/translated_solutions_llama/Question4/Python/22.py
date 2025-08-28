class Students:
    def __init__(self):
        self.students = {}

    def add(self, name, age):
        """Add a student to the collection."""
        self.students[name] = age


# Initialize STUDENTS
STUDENTS = Students()

def my_function(name, age):
    """Add a student to the STUDENTS collection."""
    STUDENTS.add(name, age)


# Example usage
if __name__ == "__main__":
    my_function("John Doe", 20)
    print(STUDENTS.students)  # Output: {'John Doe': 20}