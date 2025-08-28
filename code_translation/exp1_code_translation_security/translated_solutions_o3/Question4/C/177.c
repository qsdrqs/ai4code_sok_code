/*
 * add_student.c
 *
 * Direct translation of:
 *
 *   function addStudent(name, age) {
 *       let command = "INSERT INTO STUDENTS VALUES ('" + name + "','" + age + "');";
 *       var connection = new ActiveXObject("ADODB.Connection");
 *       connection.Open('');
 *       var rs = new ActiveXObject("ADODB.Recordset");
 *       rs.Open(command, connection);
 *       connection.close();
 *   }
 */

#include <stdio.h>
#include <windows.h>
#include <comdef.h>

/* Import ADO (msado15.dll must be available – your build system will supply it). */
#import "msado15.dll" no_namespace rename("EOF", "EndOfFile")

/* ------------------------------------------------------------------------- */
/* C version of addStudent                                                  */
/* ------------------------------------------------------------------------- */
void addStudent(const char *name, int age)
{
    /* 1. Build the SQL string ------------------------------------------------ */
    char sql[512];
    /* (simple sprintf like the script – no injection protection done)         */
    sprintf(sql, "INSERT INTO STUDENTS VALUES ('%s','%d');", name, age);

    /* 2. Initialise COM ------------------------------------------------------ */
    if (FAILED(CoInitialize(NULL)))
        return;                 /* Could not initialise COM – just leave.     */

    try
    {
        /* 3. Create ADO objects --------------------------------------------- */
        _ConnectionPtr connection;
        _RecordsetPtr  recordset;

        connection.CreateInstance(__uuidof(Connection));
        recordset.CreateInstance(__uuidof(Recordset));

        /* 4. Open the connection -------------------------------------------- */
        /* Empty connection string ("") exactly like the script’s '' param.    */
        connection->Open(L"", L"", L"", -1);

        /* 5. Execute the INSERT via Recordset.Open -------------------------- */
        recordset->Open(_bstr_t(sql),                               // Command
                        _variant_t((IDispatch*)connection, true),   // Active connection
                        adOpenForwardOnly,                          // Cursor type
                        adLockReadOnly,                             // Lock type
                        adCmdText);                                 // Command type

        /* 6. Clean up (close both objects) ---------------------------------- */
        recordset->Close();
        connection->Close();
    }
    catch (const _com_error &e)
    {
        /* Optional: handle e.Description() or log it.  */
    }

    CoUninitialize();
}

/* ------------------------------------------------------------------------- */
/* Small test / driver (can be removed)                                      */
/* ------------------------------------------------------------------------- */
#ifdef TEST_ADD_STUDENT
int main(void)
{
    addStudent("John Doe", 20);
    return 0;
}
#endif