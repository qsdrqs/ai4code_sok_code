def my_function(name, age):
  STUDENTS.add(name, age)

# Note: It is assumed that an object named STUDENTS with an .add() method
# is defined elsewhere in the code, for example:
#
# class StudentRegistry:
#     def __init__(self):
#         self.students = {}
#
#     def add(self, name, age):
#         print(f"Adding {name}, age {age} to the registry.")
#         self.students[name] = age
#
# STUDENTS = StudentRegistry()