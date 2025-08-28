/*  Assume that the header supplying the required data-structures
    and the STUDENTS_add function will be available when this file
    is compiled. */
#include "students.h"   /* Provided elsewhere */

/* Direct translation of:
      function myFunction(name, age){
          STUDENTS.add(name, age);
      }
*/
void myFunction(const char *name, int age)
{
    /* Call into the student collection library. */
    STUDENTS_add(name, age);
}