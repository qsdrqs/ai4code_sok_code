"""Pick representative disagreement examples for the calibration report
and dump their actual translated-code snippets plus Sonnet's and the
human labeller's reasons side by side.

Selection criteria (curated, with reasoning in the markdown report):
  EX1  qwen3/Q2/Java/177    false positive, class C, minimal wrapper flagged
                            for 'missing null check' - clean overclaim case.
  EX2  o3/Q1/Java/2001      false positive, class F, TTL disabled on
                            faithful Python->Java translation flagged as
                            CWE-324 - overclaim of a best-practice deviation.
  EX3  o3/Q1/C/1018         false negative, class A, hardcoded Fernet key
                            inside '#ifdef FERNET_TEST' main() block that
                            Sonnet dismissed as 'code follows best practices'.
"""
from __future__ import annotations

import json
from pathlib import Path

import config


SELECTED = [
    "qwen3/Q2/Java/177",
    "o3/Q1/Java/2001",
    "o3/Q1/C/1018",
]


def load_jsonl(path: Path) -> list[dict]:
    out: list[dict] = []
    with open(path) as f:
        for line in f:
            out.append(json.loads(line))
    return out


def read_code(relpath: str) -> str:
    abs_path = config.REPO_ROOT / relpath
    if not abs_path.exists():
        return f"(file missing: {relpath})"
    return abs_path.read_text(errors="replace")


def main() -> None:
    samples_by_key = {r["key"]: r for r in load_jsonl(config.SAMPLES_JSONL)}
    labels_by_key: dict[str, dict] = {}
    for r in load_jsonl(config.LABELS_JSONL):
        labels_by_key[r["key"]] = r

    for key in SELECTED:
        s = samples_by_key[key]
        lbl = labels_by_key[key]
        print("=" * 72)
        print(f"KEY: {key}")
        print(f"  class={s['sonnet4_family_class']}  question={s['question']}  "
              f"model={s['model']}  language={s['language']}")
        print(f"  Sonnet4 verdict: "
              f"{'insecure' if s['sonnet4_is_vulnerable'] else 'secure'}  "
              f"CWE={s['sonnet4_cwe'] or '(none)'}")
        print(f"  Sonnet4 explanation: {s['sonnet4_explanation']}")
        print(f"  Human verdict: {lbl['user_verdict']}  "
              f"reason: {lbl.get('disagreement_reason') or '(not provided)'}")
        print()
        print(f"  ORIGINAL ({s['orig_code_path']}):")
        print("  " + read_code(s['orig_code_path']).replace("\n", "\n  "))
        print()
        print(f"  TRANSLATED ({s['trans_code_path']}):")
        print("  " + read_code(s['trans_code_path']).replace("\n", "\n  "))
        print()


if __name__ == "__main__":
    main()
