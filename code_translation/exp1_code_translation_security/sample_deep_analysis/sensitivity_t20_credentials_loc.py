"""
Sensitivity analysis for paper Table 20 (T20) claims:

A. CREDENTIALS MECHANISM: descriptive breakdown of class-C samples whose
   trans_cwe_category == 'Credentials Management Errors'. Reports counts by
   question and target language. Sanity expectation: 110 samples.

B. POINTER ISSUES MECHANISM: line count evidence that C-target translations
   are systematically longer than other targets. Reports per-target-language
   mean/median raw LOC (non-blank lines) over all translated files in the
   classified set, separately for the 33 class-C Pointer Issues samples,
   and a side-by-side ratio.

Convention notes:
  - "LOC" here = non-blank line count, no further normalization (matches the
    user's stated preference; semantic LOC removal of comments/braces is not
    needed for the order-of-magnitude argument T20 makes).
  - LOC analysis covers all 7 models present in
    classification_per_sample.jsonl (claude, gemini, gpt-4o, llama, o3,
    opus47, qwen3). This keeps the LOC story consistent with the class-C
    subset.
  - Source language for Q1-Q4 is Python; source for Q5 is C. The "original
    source LOC" is reported separately for these two source languages.
"""

import json
import statistics
from collections import Counter, defaultdict
from pathlib import Path

REPO = Path(__file__).resolve().parent.parent
DATA = REPO / "sample_deep_analysis/results/classification_per_sample.jsonl"

LANG_EXT = {"C": ".c", "Go": ".go", "Java": ".java", "Python": ".py", "Rust": ".rs"}
TARGET_LANGS = ["C", "Go", "Java", "Python", "Rust"]
QUESTIONS = ["Q1", "Q2", "Q3", "Q4", "Q5"]
CLASSIFIED_MODELS = ["claude", "gemini", "gpt-4o", "llama", "o3", "opus47", "qwen3"]
SOURCE_LANG_BY_Q = {"Q1": "Python", "Q2": "Python", "Q3": "Python", "Q4": "Python", "Q5": "C"}


def count_loc(path: Path) -> int | None:
    try:
        text = path.read_text(errors="replace")
    except OSError:
        return None
    return sum(1 for line in text.splitlines() if line.strip())


def load_records():
    return [json.loads(l) for l in DATA.read_text().splitlines() if l.strip()]


def banner(title: str, w: int = 78) -> None:
    print()
    print("=" * w)
    print(title)
    print("=" * w)


def fmt_stats(values):
    if not values:
        return "n=0"
    return (
        f"n={len(values):4d}  mean={statistics.mean(values):6.1f}  "
        f"median={statistics.median(values):6.1f}  "
        f"min={min(values):4d}  max={max(values):4d}"
    )


def part_A_credentials_breakdown(records):
    banner("A. Class-C samples with trans_cwe_category=='Credentials Management Errors'")
    classC = [r for r in records if r["classification_family"] == "C"]
    target = [r for r in classC if r["trans_cwe_category"] == "Credentials Management Errors"]
    n = len(target)
    print(f"n = {n}  (sanity expected ~110 for 6-translator, ~128 for 7-translator)")

    by_q = Counter(r["question"] for r in target)
    by_lang = Counter(r["language"] for r in target)
    by_model = Counter(r["model"] for r in target)

    print("\nBy question:")
    for q in QUESTIONS:
        c = by_q.get(q, 0)
        pct = c / n * 100 if n else 0.0
        print(f"  {q}: {c:3d} ({pct:5.1f}%)")

    print("\nBy target language:")
    for lang in TARGET_LANGS:
        c = by_lang.get(lang, 0)
        pct = c / n * 100 if n else 0.0
        print(f"  {lang:7s}: {c:3d} ({pct:5.1f}%)")

    print("\nBy model:")
    for model in CLASSIFIED_MODELS:
        c = by_model.get(model, 0)
        pct = c / n * 100 if n else 0.0
        print(f"  {model:8s}: {c:3d} ({pct:5.1f}%)")


def walk_all_translated_loc(models):
    by_lang = defaultdict(list)
    by_lang_per_question = defaultdict(lambda: defaultdict(list))
    files_seen = 0
    files_unreadable = 0
    for model in models:
        base = REPO / f"translated_solutions_{model}"
        if not base.is_dir():
            continue
        for q in QUESTIONS:
            qdir = base / f"Question{q[1]}"
            if not qdir.is_dir():
                continue
            for lang in TARGET_LANGS:
                ldir = qdir / lang
                if not ldir.is_dir():
                    continue
                for f in ldir.iterdir():
                    if not f.is_file():
                        continue
                    if f.suffix != LANG_EXT[lang]:
                        continue
                    loc = count_loc(f)
                    if loc is None:
                        files_unreadable += 1
                        continue
                    by_lang[lang].append(loc)
                    by_lang_per_question[q][lang].append(loc)
                    files_seen += 1
    return by_lang, by_lang_per_question, files_seen, files_unreadable


def walk_original_loc():
    by_q = defaultdict(list)
    for q in QUESTIONS:
        qdir = REPO / "correct_solutions" / f"Question{q[1]}"
        if not qdir.is_dir():
            continue
        src_lang = SOURCE_LANG_BY_Q[q]
        ext = LANG_EXT[src_lang]
        for f in qdir.iterdir():
            if f.is_file() and f.suffix == ext:
                loc = count_loc(f)
                if loc is not None:
                    by_q[q].append(loc)
    return by_q


def lookup_loc_for_record(r):
    model = r["model"]
    q = r["question"]
    lang = r["language"]
    sid = r["sample_id"]
    ext = LANG_EXT[lang]
    path = REPO / f"translated_solutions_{model}" / f"Question{q[1]}" / lang / f"{sid}{ext}"
    if not path.is_file():
        return None, str(path)
    return count_loc(path), str(path)


def part_B_loc_analysis(records):
    banner("B. Mean LOC by target language (all classified models, all questions)")
    by_lang, by_lang_q, n_files, n_bad = walk_all_translated_loc(CLASSIFIED_MODELS)
    orig_by_q = walk_original_loc()

    py_orig = []
    c_orig = []
    for q, vs in orig_by_q.items():
        if SOURCE_LANG_BY_Q[q] == "Python":
            py_orig.extend(vs)
        else:
            c_orig.extend(vs)

    print(f"Translated files scanned: {n_files} (unreadable: {n_bad})")
    print(f"Models included: {CLASSIFIED_MODELS}")

    py_mean = statistics.mean(py_orig) if py_orig else 0.0
    py_median = statistics.median(py_orig) if py_orig else 0.0
    print(
        f"\nOriginal Python source (Q1-Q4):  "
        f"n={len(py_orig)}  mean={py_mean:.1f}  median={py_median:.1f}"
    )
    if c_orig:
        print(
            f"Original C source (Q5):          "
            f"n={len(c_orig)}  mean={statistics.mean(c_orig):.1f}  "
            f"median={statistics.median(c_orig):.1f}"
        )

    print("\nTarget language LOC (all classified-model translations):")
    for lang in TARGET_LANGS:
        vs = by_lang[lang]
        if not vs:
            print(f"  {lang:7s}: n=0")
            continue
        m = statistics.mean(vs)
        med = statistics.median(vs)
        ratio = m / py_mean if py_mean > 0 else float("nan")
        print(
            f"  {lang:7s}: n={len(vs):4d}  mean={m:6.1f}  median={med:6.1f}  "
            f"ratio_to_python_orig={ratio:4.2f}x"
        )

    means = {lang: statistics.mean(by_lang[lang]) for lang in TARGET_LANGS if by_lang[lang]}
    if "C" in means:
        c_mean = means["C"]
        non_c = [m for lang, m in means.items() if lang != "C"]
        if non_c:
            non_c_avg = statistics.mean(non_c)
            print(
                f"\nC-target mean LOC vs mean of (Go, Java, Python, Rust) means: "
                f"{c_mean:.1f} / {non_c_avg:.1f} = {c_mean/non_c_avg:.2f}x"
            )

    banner("B-2. Per-question LOC by target language (all classified-model translations)")
    print(f"{'Q':>3}  " + " ".join(f"{lang:>10s}" for lang in TARGET_LANGS))
    for q in QUESTIONS:
        cells = []
        for lang in TARGET_LANGS:
            vs = by_lang_q[q][lang]
            cell = f"{statistics.mean(vs):.1f}(n={len(vs)})" if vs else "-"
            cells.append(cell)
        print(f"{q:>3}  " + " ".join(f"{c:>10s}" for c in cells))

    banner("B-3. Class-C Pointer Issues subset: per-target-language LOC")
    classC_ptr = [
        r
        for r in records
        if r["classification_family"] == "C"
        and r["trans_cwe_category"] == "Pointer Issues"
    ]
    print(f"Subset n = {len(classC_ptr)}  (sanity expected ~33 for 6-translator, ~38 for 7-translator)")
    print(f"By target language: {dict(Counter(r['language'] for r in classC_ptr))}")
    print(f"By model:           {dict(Counter(r['model'] for r in classC_ptr))}")
    print(f"By question:        {dict(Counter(r['question'] for r in classC_ptr))}")

    sub_by_lang = defaultdict(list)
    missing = 0
    for r in classC_ptr:
        loc, _path = lookup_loc_for_record(r)
        if loc is None:
            missing += 1
            continue
        sub_by_lang[r["language"]].append(loc)
    print(f"Files looked up: {len(classC_ptr) - missing} found, {missing} missing")

    print("\nSubset LOC by target language (with comparison to all-class mean):")
    for lang in TARGET_LANGS:
        vs = sub_by_lang.get(lang, [])
        all_vs = by_lang[lang]
        all_mean = statistics.mean(all_vs) if all_vs else float("nan")
        if not vs:
            print(f"  {lang:7s}: subset n=0  (all-class mean={all_mean:.1f})")
            continue
        m = statistics.mean(vs)
        med = statistics.median(vs)
        delta = m - all_mean
        print(
            f"  {lang:7s}: subset n={len(vs):3d}  mean={m:6.1f}  median={med:6.1f}  "
            f"all-class mean={all_mean:6.1f}  delta={delta:+5.1f}"
        )

    banner("B-4. Headline numbers for paper")
    if "C" in means and len([m for l, m in means.items() if l != "C"]) > 0:
        non_c = {l: m for l, m in means.items() if l != "C"}
        print(
            f"C-target translations are on average {means['C']:.1f} non-blank lines, "
            f"vs {statistics.mean(list(non_c.values())):.1f} for the average non-C target."
        )
        print(
            f"C is {means['C'] / statistics.mean(list(non_c.values())):.2f}x longer than the "
            f"non-C-target average."
        )
        ratios = {l: means["C"] / non_c[l] for l in non_c}
        for l, r in sorted(ratios.items(), key=lambda kv: kv[0]):
            print(f"  C / {l} = {r:.2f}x")
    sub_means = {lang: statistics.mean(vs) for lang, vs in sub_by_lang.items() if vs}
    if "C" in sub_means and len(sub_means) >= 2:
        print(
            f"\nWithin the 33 class-C Pointer Issues samples, "
            f"C-target mean LOC = {sub_means['C']:.1f}."
        )
        for l, m in sub_means.items():
            if l != "C":
                print(f"  Same subset, {l}-target: mean LOC = {m:.1f}")


def main() -> None:
    records = load_records()
    print(f"Loaded {len(records)} classification records")
    part_A_credentials_breakdown(records)
    part_B_loc_analysis(records)


if __name__ == "__main__":
    main()
