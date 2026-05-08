from __future__ import annotations

import re

from cwe_taxonomy import get_cwe_category

CWE_ID_RE = re.compile(r"^CWE-\d+$", re.IGNORECASE)


def is_valid_cwe(cwe: str) -> bool:
    if not cwe:
        return False
    return bool(CWE_ID_RE.match(cwe.strip()))


def cwe_family_match(a: str, b: str) -> bool:
    ca = get_cwe_category(a)
    cb = get_cwe_category(b)
    if ca == "Other" or cb == "Other":
        return False
    return ca == cb


def cwe_strict_match(a: str, b: str) -> bool:
    if not a or not b:
        return False
    return a.strip().upper() == b.strip().upper()


def classify(
    orig_entry: dict | None,
    trans_entry: dict | None,
) -> tuple[str, str, str | None]:
    # Step 0 (ordering critical): uncertainty gates must run BEFORE the
    # (o_vul, t_vul) combinations, otherwise a malformed CWE on a vulnerable
    # sample would silently pass as B or F instead of D.
    if orig_entry is None:
        return "D", "D", "missing_in_original"
    if trans_entry is None:
        return "D", "D", "missing_in_translated"

    o_vul = bool(orig_entry.get("is_vulnerable", False))
    t_vul = bool(trans_entry.get("is_vulnerable", False))
    o_cwe = (orig_entry.get("cwe") or "").strip()
    t_cwe = (trans_entry.get("cwe") or "").strip()

    if o_vul and not is_valid_cwe(o_cwe):
        return "D", "D", "malformed_cwe_original"
    if t_vul and not is_valid_cwe(t_cwe):
        return "D", "D", "malformed_cwe_translated"

    if not o_vul and not t_vul:
        return "E", "E", None
    if o_vul and not t_vul:
        return "A", "A", None
    if not o_vul and t_vul:
        return "C", "C", None

    class_family = "B" if cwe_family_match(o_cwe, t_cwe) else "F"
    class_strict = "B" if cwe_strict_match(o_cwe, t_cwe) else "F"
    return class_family, class_strict, None

