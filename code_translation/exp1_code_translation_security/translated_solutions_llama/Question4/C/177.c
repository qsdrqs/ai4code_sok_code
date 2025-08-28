#include <windows.h>
#include <adodb.h>
#include <stdio.h>

void addStudent(const char* name, int age) {
    // Initialize COM
    CoInitialize(NULL);

    // Create a new ADO connection
    IDBConnection* pConnection;
    HRESULT hr = CoCreateInstance(CLSID_CADOConnection, NULL, CLSCTX_INPROC_SERVER, IID_IDBConnection, (LPVOID*)&pConnection);
    if (FAILED(hr)) {
        printf("Failed to create ADO connection: 0x%x\n", hr);
        CoUninitialize();
        return;
    }

    // Open the connection
    BSTR bstrConnectionString = SysAllocString(L"");
    hr = pConnection->Open(bstrConnectionString, NULL, NULL, 0);
    if (FAILED(hr)) {
        printf("Failed to open ADO connection: 0x%x\n", hr);
        pConnection->Release();
        SysFreeString(bstrConnectionString);
        CoUninitialize();
        return;
    }

    // Create a new ADO command
    IDBCommand* pCommand;
    hr = CoCreateInstance(CLSID_CADOCommand, NULL, CLSCTX_INPROC_SERVER, IID_IDBCommand, (LPVOID*)&pCommand);
    if (FAILED(hr)) {
        printf("Failed to create ADO command: 0x%x\n", hr);
        pConnection->Close();
        pConnection->Release();
        SysFreeString(bstrConnectionString);
        CoUninitialize();
        return;
    }

    // Set the command text
    char command[256];
    sprintf(command, "INSERT INTO STUDENTS VALUES ('%s', %d);", name, age);
    BSTR bstrCommandText = SysAllocStringA(command);
    hr = pCommand->SetCommandText(bstrCommandText);
    if (FAILED(hr)) {
        printf("Failed to set command text: 0x%x\n", hr);
        pCommand->Release();
        pConnection->Close();
        pConnection->Release();
        SysFreeString(bstrConnectionString);
        SysFreeString(bstrCommandText);
        CoUninitialize();
        return;
    }

    // Set the active connection
    hr = pCommand->SetActiveConnection(pConnection);
    if (FAILED(hr)) {
        printf("Failed to set active connection: 0x%x\n", hr);
        pCommand->Release();
        pConnection->Close();
        pConnection->Release();
        SysFreeString(bstrConnectionString);
        SysFreeString(bstrCommandText);
        CoUninitialize();
        return;
    }

    // Execute the command
    hr = pCommand->Execute(NULL, NULL, 0);
    if (FAILED(hr)) {
        printf("Failed to execute command: 0x%x\n", hr);
    }

    // Clean up
    pCommand->Release();
    pConnection->Close();
    pConnection->Release();
    SysFreeString(bstrConnectionString);
    SysFreeString(bstrCommandText);
    CoUninitialize();
}

int main() {
    addStudent("John Doe", 20);
    return 0;
}