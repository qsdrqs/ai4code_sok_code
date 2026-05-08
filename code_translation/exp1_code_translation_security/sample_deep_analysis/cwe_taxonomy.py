from __future__ import annotations

# CWE family buckets extracted VERBATIM from
# analyze_translation_security.py::get_cwe_category CWE-699, so this copy is
# byte-for-byte identical to the source list. Do not edit by hand; if the
# source changes, regenerate this file via sync_cwe_taxonomy.py.
# The source intentionally duplicates some CWE IDs across buckets; the first
# bucket encountered during iteration wins (see get_cwe_category below).
CWE_BUCKETS: dict[str, list[str]] = {
    'Bad Coding Practices': [
        'CWE-358', 'CWE-360', 'CWE-478', 'CWE-487', 'CWE-489', 'CWE-547',
        'CWE-561', 'CWE-562', 'CWE-563', 'CWE-581', 'CWE-586', 'CWE-605',
        'CWE-628', 'CWE-654', 'CWE-656', 'CWE-694', 'CWE-807', 'CWE-1041',
        'CWE-1043', 'CWE-1044', 'CWE-1045', 'CWE-1046', 'CWE-1048', 'CWE-1049',
        'CWE-1050', 'CWE-1063', 'CWE-1065', 'CWE-1066', 'CWE-1067', 'CWE-1070',
        'CWE-1071', 'CWE-1072', 'CWE-1073', 'CWE-1079', 'CWE-1082', 'CWE-1084',
        'CWE-1085', 'CWE-1087', 'CWE-1089', 'CWE-1092', 'CWE-1094', 'CWE-1097',
        'CWE-1098', 'CWE-1099', 'CWE-1101', 'CWE-1102', 'CWE-1103', 'CWE-1104',
        'CWE-1106', 'CWE-1107', 'CWE-1108', 'CWE-1109', 'CWE-1113', 'CWE-1114',
        'CWE-1115', 'CWE-1116', 'CWE-1117', 'CWE-1126', 'CWE-1127', 'CWE-1235',
    ],
    'Audit / Logging Errors': [
        'CWE-117', 'CWE-222', 'CWE-223', 'CWE-224', 'CWE-778', 'CWE-779',
    ],
    'Authentication Errors': [
        'CWE-289', 'CWE-290', 'CWE-294', 'CWE-295', 'CWE-301', 'CWE-303',
        'CWE-305', 'CWE-306', 'CWE-307', 'CWE-308', 'CWE-309', 'CWE-322',
        'CWE-603', 'CWE-645', 'CWE-804', 'CWE-836',
    ],
    'Authorization Errors': [
        'CWE-425', 'CWE-551', 'CWE-552', 'CWE-639', 'CWE-653', 'CWE-842',
        'CWE-939', 'CWE-1220', 'CWE-1230',
    ],
    'Random Number Issues': [
        'CWE-331', 'CWE-334', 'CWE-335', 'CWE-338', 'CWE-341', 'CWE-342',
        'CWE-343', 'CWE-344', 'CWE-1241',
    ],
    'Data Integrity Issues': [
        'CWE-322', 'CWE-346', 'CWE-347', 'CWE-348', 'CWE-349', 'CWE-351',
        'CWE-353', 'CWE-354', 'CWE-494', 'CWE-565', 'CWE-649', 'CWE-829',
        'CWE-924',
    ],
    'Data Validation Issues': [
        'CWE-20', 'CWE-112', 'CWE-179', 'CWE-183', 'CWE-184', 'CWE-606',
        'CWE-641', 'CWE-1173', 'CWE-1284', 'CWE-1285', 'CWE-1286', 'CWE-1287',
        'CWE-1288', 'CWE-1289',
    ],
    'Lockout Mechanism Errors': [
        'CWE-645',
    ],
    'User Session Errors': [
        'CWE-488', 'CWE-613', 'CWE-841',
    ],
    'Memory Buffer Errors': [
        'CWE-120', 'CWE-124', 'CWE-125', 'CWE-131', 'CWE-786', 'CWE-787',
        'CWE-788', 'CWE-805', 'CWE-1284',
    ],
    'File Handling Issues': [
        'CWE-22', 'CWE-41', 'CWE-59', 'CWE-66', 'CWE-378', 'CWE-379',
        'CWE-426', 'CWE-427', 'CWE-428',
    ],
    'Documentation Issues': [
        'CWE-1053', 'CWE-1068', 'CWE-1110', 'CWE-1111', 'CWE-1112', 'CWE-1118',
    ],
    'Complexity Issues': [
        'CWE-1043', 'CWE-1047', 'CWE-1055', 'CWE-1056', 'CWE-1060', 'CWE-1064',
        'CWE-1074', 'CWE-1075', 'CWE-1080', 'CWE-1086', 'CWE-1095', 'CWE-1119',
        'CWE-1121', 'CWE-1122', 'CWE-1123', 'CWE-1124', 'CWE-1125', 'CWE-1333',
    ],
    'Encapsulation Issues': [
        'CWE-1054', 'CWE-1057', 'CWE-1062', 'CWE-1083', 'CWE-1090', 'CWE-1100',
        'CWE-1105',
    ],
    'API / Function Errors': [
        'CWE-242', 'CWE-474', 'CWE-475', 'CWE-477', 'CWE-676', 'CWE-695',
        'CWE-749',
    ],
    'String Errors': [
        'CWE-134', 'CWE-135', 'CWE-480',
    ],
    'Type Errors': [
        'CWE-681', 'CWE-843', 'CWE-1287',
    ],
    'Data Neutralization Issues': [
        'CWE-76', 'CWE-78', 'CWE-79', 'CWE-88', 'CWE-89', 'CWE-90',
        'CWE-91', 'CWE-93', 'CWE-94', 'CWE-117', 'CWE-140', 'CWE-170',
        'CWE-463', 'CWE-464', 'CWE-641', 'CWE-694', 'CWE-791', 'CWE-838',
        'CWE-917', 'CWE-1236',
    ],
    'Numeric Errors': [
        'CWE-128', 'CWE-190', 'CWE-191', 'CWE-193', 'CWE-369', 'CWE-681',
        'CWE-839', 'CWE-1335', 'CWE-1339', 'CWE-1389',
    ],
    'Data Processing Errors': [
        'CWE-130', 'CWE-166', 'CWE-167', 'CWE-168', 'CWE-178', 'CWE-182',
        'CWE-186', 'CWE-229', 'CWE-233', 'CWE-237', 'CWE-241', 'CWE-409',
        'CWE-472', 'CWE-601', 'CWE-611', 'CWE-624', 'CWE-625', 'CWE-776',
        'CWE-1024',
    ],
    'Information Management Errors': [
        'CWE-201', 'CWE-204', 'CWE-205', 'CWE-208', 'CWE-209', 'CWE-212',
        'CWE-213', 'CWE-214', 'CWE-215', 'CWE-312', 'CWE-319', 'CWE-359',
        'CWE-497', 'CWE-524', 'CWE-532', 'CWE-538', 'CWE-921', 'CWE-1230',
    ],
    'Credentials Management Errors': [
        'CWE-256', 'CWE-257', 'CWE-260', 'CWE-261', 'CWE-262', 'CWE-263',
        'CWE-324', 'CWE-521', 'CWE-523', 'CWE-549', 'CWE-620', 'CWE-640',
        'CWE-798', 'CWE-916', 'CWE-1392',
    ],
    'Privilege Issues': [
        'CWE-243', 'CWE-250', 'CWE-266', 'CWE-267', 'CWE-268', 'CWE-270',
        'CWE-272', 'CWE-273', 'CWE-274', 'CWE-280', 'CWE-501', 'CWE-580',
        'CWE-648',
    ],
    'Permission Issues': [
        'CWE-276', 'CWE-277', 'CWE-278', 'CWE-279', 'CWE-280', 'CWE-281',
        'CWE-618', 'CWE-766', 'CWE-767',
    ],
    'Cryptographic Issues': [
        'CWE-261', 'CWE-324', 'CWE-325', 'CWE-327', 'CWE-328', 'CWE-329',
        'CWE-331', 'CWE-334', 'CWE-335', 'CWE-338', 'CWE-347', 'CWE-916',
        'CWE-1204', 'CWE-1240',
    ],
    'Key Management Errors': [
        'CWE-322', 'CWE-323', 'CWE-324', 'CWE-798',
    ],
    'User Interface Security Issues': [
        'CWE-356', 'CWE-357', 'CWE-447', 'CWE-448', 'CWE-449', 'CWE-549',
        'CWE-1007', 'CWE-1021',
    ],
    'State Issues': [
        'CWE-15', 'CWE-372', 'CWE-374', 'CWE-375', 'CWE-1265',
    ],
    'Signal Errors': [
        'CWE-364',
    ],
    'Error Conditions, Return Values, Status Codes': [
        'CWE-209', 'CWE-248', 'CWE-252', 'CWE-253', 'CWE-390', 'CWE-391',
        'CWE-392', 'CWE-393', 'CWE-394', 'CWE-395', 'CWE-396', 'CWE-397',
        'CWE-544', 'CWE-584', 'CWE-617', 'CWE-756',
    ],
    'Resource Management Errors': [
        'CWE-73', 'CWE-403', 'CWE-410', 'CWE-470', 'CWE-502', 'CWE-619',
        'CWE-641', 'CWE-694', 'CWE-763', 'CWE-770', 'CWE-771', 'CWE-772',
        'CWE-826', 'CWE-908', 'CWE-909', 'CWE-910', 'CWE-911', 'CWE-914',
        'CWE-915', 'CWE-920', 'CWE-1188', 'CWE-1341',
    ],
    'Resource Locking Problems': [
        'CWE-412', 'CWE-413', 'CWE-414', 'CWE-609', 'CWE-764', 'CWE-765',
        'CWE-832', 'CWE-833',
    ],
    'Communication Channel Errors': [
        'CWE-322', 'CWE-346', 'CWE-385', 'CWE-419', 'CWE-420', 'CWE-425',
        'CWE-515', 'CWE-918', 'CWE-924', 'CWE-940', 'CWE-941', 'CWE-1327',
    ],
    'Handler Errors': [
        'CWE-430', 'CWE-431', 'CWE-434',
    ],
    'Behavioral Problems': [
        'CWE-115', 'CWE-179', 'CWE-408', 'CWE-437', 'CWE-439', 'CWE-440',
        'CWE-444', 'CWE-480', 'CWE-483', 'CWE-484', 'CWE-551', 'CWE-698',
        'CWE-733', 'CWE-783', 'CWE-835', 'CWE-837', 'CWE-841', 'CWE-1025',
        'CWE-1037',
    ],
    'Initialization and Cleanup Errors': [
        'CWE-212', 'CWE-454', 'CWE-455', 'CWE-459', 'CWE-1051', 'CWE-1052',
        'CWE-1188',
    ],
    'Pointer Issues': [
        'CWE-466', 'CWE-468', 'CWE-469', 'CWE-476', 'CWE-587', 'CWE-763',
        'CWE-822', 'CWE-823', 'CWE-824', 'CWE-825',
    ],
    'Concurrency Issues': [
        'CWE-364', 'CWE-366', 'CWE-367', 'CWE-368', 'CWE-386', 'CWE-421',
        'CWE-663', 'CWE-820', 'CWE-821', 'CWE-1058', 'CWE-1322',
    ],
    'Expression Issues': [
        'CWE-480', 'CWE-570', 'CWE-571', 'CWE-783',
    ],
    'Business Logic Errors': [
        'CWE-283', 'CWE-639', 'CWE-640', 'CWE-708', 'CWE-770', 'CWE-826',
        'CWE-837', 'CWE-841',
    ],
}


def get_cwe_category(cwe: str) -> str:
    if not cwe:
        return "Other"
    cwe = cwe.strip().upper()
    for name, members in CWE_BUCKETS.items():
        if cwe in members:
            return name
    return "Other"
