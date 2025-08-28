#include <windows.h>
#include <stdio.h>
#include <ole2.h>
#include <olectl.h>

// Helper function to convert char* to BSTR
BSTR ConvertStringToBSTR(const char* str) {
    int len = MultiByteToWideChar(CP_ACP, 0, str, -1, NULL, 0);
    BSTR bstr = SysAllocStringLen(NULL, len);
    MultiByteToWideChar(CP_ACP, 0, str, -1, bstr, len);
    return bstr;
}

// Helper function to release BSTR safely
void SafeSysFreeString(BSTR* bstr) {
    if (bstr && *bstr) {
        SysFreeString(*bstr);
        *bstr = NULL;
    }
}

void addStudent(char* name, char* age) {
    HRESULT hr;
    IDispatch* pConnection = NULL;
    IDispatch* pRecordset = NULL;
    CLSID clsid;
    BSTR bstrName = NULL;
    BSTR bstrAge = NULL;
    BSTR bstrCommand = NULL;

    // Initialize COM
    CoInitialize(NULL);

    // Convert name and age to BSTR
    bstrName = ConvertStringToBSTR(name);
    bstrAge = ConvertStringToBSTR(age);

    // Build SQL command
    {
        int len = 50 + SysStringLen(bstrName) + SysStringLen(bstrAge);
        bstrCommand = SysAllocStringLen(NULL, len);
        swprintf_s(bstrCommand, len + 1, L"INSERT INTO STUDENTS VALUES ('%s', '%s')", bstrName, bstrAge);
    }

    // Create ADODB.Connection object
    hr = CLSIDFromProgID(L"ADODB.Connection", &clsid);
    if (SUCCEEDED(hr)) {
        hr = CoCreateInstance(&clsid, NULL, CLSCTX_INPROC_SERVER, &IID_IDispatch, (void**)&pConnection);
    }

    // Call Connection.Open("")
    if (SUCCEEDED(hr) && pConnection) {
        DISPID dispid;
        OLECHAR* openMethod = L"Open";
        hr = pConnection->lpVtbl->GetIDsOfNames(pConnection, &IID_NULL, &openMethod, 1, LOCALE_SYSTEM_DEFAULT, &dispid);
        if (SUCCEEDED(hr)) {
            // Prepare parameters: Open("", "", "", "")
            DISPPARAMS params = {0};
            params.rgvarg = (VARIANTARG*)CoTaskMemAlloc(4 * sizeof(VARIANTARG));
            for (int i = 0; i < 4; i++) {
                params.rgvarg[i].vt = VT_BSTR;
                params.rgvarg[i].bstrVal = SysAllocString(L"");
            }
            params.cArgs = 4;
            params.cNamedArgs = 0;

            hr = pConnection->lpVtbl->Invoke(pConnection, dispid, &IID_NULL, LOCALE_SYSTEM_DEFAULT, DISPATCH_METHOD, &params, NULL, NULL, NULL);

            // Free parameters
            for (int i = 0; i < 4; i++) {
                SysFreeString(params.rgvarg[i].bstrVal);
            }
            CoTaskMemFree(params.rgvarg);
        }
    }

    // Create ADODB.Recordset object
    hr = CLSIDFromProgID(L"ADODB.Recordset", &clsid);
    if (SUCCEEDED(hr)) {
        hr = CoCreateInstance(&clsid, NULL, CLSCTX_INPROC_SERVER, &IID_IDispatch, (void**)&pRecordset);
    }

    // Call Recordset.Open(command, connection)
    if (SUCCEEDED(hr) && pRecordset) {
        DISPID dispid;
        OLECHAR* openMethod = L"Open";
        hr = pRecordset->lpVtbl->GetIDsOfNames(pRecordset, &IID_NULL, &openMethod, 1, LOCALE_SYSTEM_DEFAULT, &dispid);
        if (SUCCEEDED(hr)) {
            // Prepare parameters: Open(command, connection, adOpenUnspecified, adLockUnspecified, adCmdText)
            DISPPARAMS params = {0};
            params.rgvarg = (VARIANTARG*)CoTaskMemAlloc(5 * sizeof(VARIANTARG));

            // Param 4: adCmdText = 1
            params.rgvarg[4].vt = VT_I4;
            params.rgvarg[4].lVal = 1;

            // Param 3: adLockUnspecified = -1
            params.rgvarg[3].vt = VT_I4;
            params.rgvarg[3].lVal = -1;

            // Param 2: adOpenUnspecified = -1
            params.rgvarg[2].vt = VT_I4;
            params.rgvarg[2].lVal = -1;

            // Param 1: connection
            params.rgvarg[1].vt = VT_DISPATCH;
            params.rgvarg[1].pdispVal = pConnection;

            // Param 0: command
            params.rgvarg[0].vt = VT_BSTR;
            params.rgvarg[0].bstrVal = bstrCommand;

            params.cArgs = 5;
            params.cNamedArgs = 0;

            hr = pRecordset->lpVtbl->Invoke(pRecordset, dispid, &IID_NULL, LOCALE_SYSTEM_DEFAULT, DISPATCH_METHOD, &params, NULL, NULL, NULL);

            // Free parameters
            CoTaskMemFree(params.rgvarg);
        }
    }

    // Call Connection.Close()
    if (pConnection) {
        DISPID dispid;
        OLECHAR* closeMethod = L"Close";
        hr = pConnection->lpVtbl->GetIDsOfNames(pConnection, &IID_NULL, &closeMethod, 1, LOCALE_SYSTEM_DEFAULT, &dispid);
        if (SUCCEEDED(hr)) {
            DISPPARAMS noParams = {0};
            hr = pConnection->lpVtbl->Invoke(pConnection, dispid, &IID_NULL, LOCALE_SYSTEM_DEFAULT, DISPATCH_METHOD, &noParams, NULL, NULL, NULL);
        }

        pConnection->lpVtbl->Release(pConnection);
    }

    // Release recordset
    if (pRecordset) {
        pRecordset->lpVtbl->Release(pRecordset);
    }

    // Free BSTRs
    SafeSysFreeString(&bstrName);
    SafeSysFreeString(&bstrAge);
    SafeSysFreeString(&bstrCommand);

    // Uninitialize COM
    CoUninitialize();
}