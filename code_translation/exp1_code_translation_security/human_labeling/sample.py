from __future__ import annotations

import argparse
import json
import random
import sys
from collections import Counter, defaultdict
from pathlib import Path
from typing import Iterable

import config


def load_classification_records(path: Path, models_filter: set[str]) -> list[dict]:
    records = []
    with open(path) as f:
        for line in f:
            r = json.loads(line)
            if r["model"] not in models_filter:
                continue
            if r.get("classification_family") not in config.STRATIFIED_CLASSES:
                continue
            records.append(r)
    return records


def group_by_class(records: list[dict]) -> dict[str, list[dict]]:
    buckets: dict[str, list[dict]] = defaultdict(list)
    for r in records:
        buckets[r["classification_family"]].append(r)
    return buckets


def stratified_draw(
    records_by_class: dict[str, list[dict]],
    class_quotas: dict[str, int],
    per_combo_ceiling: int,
    rng: random.Random,
) -> list[dict]:
    drawn: list[dict] = []
    for cls, quota in class_quotas.items():
        pool = records_by_class.get(cls, [])
        if not pool:
            print(f"[WARN] class {cls} has 0 candidates; quota={quota} unfilled", file=sys.stderr)
            continue
        by_model: dict[str, list[dict]] = defaultdict(list)
        for r in pool:
            by_model[r["model"]].append(r)
        for model, recs in by_model.items():
            recs_sorted = sorted(recs, key=lambda r: (r["question"], r["language"], r["sample_id"]))
            rng.shuffle(recs_sorted)
            by_model[model] = recs_sorted
        model_cycle = sorted(by_model.keys())
        rng.shuffle(model_cycle)
        combo_count: dict[tuple[str, str], int] = defaultdict(int)
        picked: list[dict] = []
        cursors = {m: 0 for m in model_cycle}
        while len(picked) < quota:
            progressed = False
            for model in model_cycle:
                if len(picked) >= quota:
                    break
                recs = by_model[model]
                while cursors[model] < len(recs):
                    r = recs[cursors[model]]
                    cursors[model] += 1
                    combo = (r["model"], r["question"])
                    if combo_count[combo] >= per_combo_ceiling:
                        continue
                    picked.append(r)
                    combo_count[combo] += 1
                    progressed = True
                    break
            if not progressed:
                for model in model_cycle:
                    if len(picked) >= quota:
                        break
                    recs = by_model[model]
                    while cursors[model] < len(recs):
                        r = recs[cursors[model]]
                        cursors[model] += 1
                        picked.append(r)
                        progressed = True
                        break
                if not progressed:
                    break
        drawn.extend(picked)
    return drawn


def enforce_language_floor(
    drawn: list[dict],
    full_buckets: dict[str, list[dict]],
    floor: int,
    rng: random.Random,
) -> list[dict]:
    lang_counts = Counter(r["language"] for r in drawn)
    drawn_keys = {config.sample_key(r["model"], r["question"], r["language"], r["sample_id"]) for r in drawn}
    for lang in config.TARGET_LANGUAGES:
        while lang_counts[lang] < floor:
            candidate = None
            for cls in config.STRATIFIED_CLASSES:
                pool = [
                    r for r in full_buckets.get(cls, [])
                    if r["language"] == lang
                    and config.sample_key(r["model"], r["question"], r["language"], r["sample_id"]) not in drawn_keys
                ]
                if pool:
                    candidate = rng.choice(sorted(pool, key=lambda r: (r["model"], r["question"], r["sample_id"])))
                    break
            if candidate is None:
                print(f"[WARN] cannot raise {lang} to floor {floor}; stuck at {lang_counts[lang]}", file=sys.stderr)
                break
            drop_candidates = [
                r for r in drawn
                if r["language"] != lang
                and lang_counts[r["language"]] > floor
                and r["classification_family"] == candidate["classification_family"]
            ]
            if not drop_candidates:
                drop_candidates = [r for r in drawn if r["language"] != lang and lang_counts[r["language"]] > floor]
            if not drop_candidates:
                print(f"[WARN] cannot swap into {lang}; no over-represented language to drop from", file=sys.stderr)
                break
            to_drop = rng.choice(sorted(drop_candidates, key=lambda r: (r["model"], r["question"], r["language"], r["sample_id"])))
            drawn.remove(to_drop)
            drawn_keys.discard(config.sample_key(to_drop["model"], to_drop["question"], to_drop["language"], to_drop["sample_id"]))
            drawn.append(candidate)
            drawn_keys.add(config.sample_key(candidate["model"], candidate["question"], candidate["language"], candidate["sample_id"]))
            lang_counts[to_drop["language"]] -= 1
            lang_counts[lang] += 1
    return drawn


def record_to_sample(r: dict, phase: int, extension: bool = False) -> dict:
    model = r["model"]
    q = r["question"]
    lang = r["language"]
    sid = r["sample_id"]
    s = {
        "key": config.sample_key(model, q, lang, sid),
        "model": model,
        "question": q,
        "source_language": config.ORIGINAL_LANGUAGES[q],
        "language": lang,
        "sample_id": sid,
        "sonnet4_is_vulnerable": r["trans_is_vulnerable"],
        "sonnet4_cwe": r.get("trans_cwe", ""),
        "sonnet4_explanation": r.get("trans_explanation", ""),
        "sonnet4_cwe_category": r.get("trans_cwe_category", ""),
        "sonnet4_family_class": r["classification_family"],
        "orig_is_vulnerable": r["orig_is_vulnerable"],
        "orig_cwe": r.get("orig_cwe", ""),
        "orig_code_path": str(config.original_code_path(q, sid).relative_to(config.REPO_ROOT)),
        "trans_code_path": str(config.translated_code_path(model, q, lang, sid).relative_to(config.REPO_ROOT)),
        "phase": phase,
    }
    if extension:
        s["extension"] = True
    return s


def verify_code_files(samples: list[dict]) -> list[dict]:
    kept = []
    for s in samples:
        orig = config.REPO_ROOT / s["orig_code_path"]
        trans = config.REPO_ROOT / s["trans_code_path"]
        if not orig.exists():
            print(f"[SKIP] missing orig: {orig}", file=sys.stderr)
            continue
        if not trans.exists():
            print(f"[SKIP] missing trans: {trans}", file=sys.stderr)
            continue
        kept.append(s)
    return kept


def print_distribution(samples: list[dict], phase_label: str) -> None:
    print(f"\n=== {phase_label} distribution ===")
    print(f"Total: {len(samples)}")
    for key, title in [
        ("sonnet4_family_class", "class"),
        ("model", "model"),
        ("question", "question"),
        ("language", "target language"),
        ("sonnet4_is_vulnerable", "predicted is_vulnerable"),
    ]:
        c = Counter(s[key] for s in samples)
        ordered = sorted(c.items(), key=lambda kv: (-kv[1], str(kv[0])))
        inline = ", ".join(f"{k}={v}" for k, v in ordered)
        print(f"  by {title}: {inline}")


def phase1(out_path: Path, classification_jsonl: Path) -> int:
    rng = random.Random(config.PHASE1_SEED)
    records = load_classification_records(classification_jsonl, set(config.PHASE1_MODELS))
    print(f"Phase 1: {len(records)} candidate records from {len(config.PHASE1_MODELS)} models")
    buckets = group_by_class(records)
    drawn = stratified_draw(buckets, config.PHASE1_CLASS_QUOTAS, per_combo_ceiling=2, rng=rng)
    drawn = enforce_language_floor(drawn, buckets, config.PHASE1_LANG_FLOOR, rng)
    samples = [record_to_sample(r, phase=1) for r in drawn]
    samples = verify_code_files(samples)
    samples.sort(key=lambda s: (s["sonnet4_family_class"], s["model"], s["question"], s["language"], s["sample_id"]))
    out_path.parent.mkdir(parents=True, exist_ok=True)
    with open(out_path, "w") as f:
        for s in samples:
            f.write(json.dumps(s) + "\n")
    print_distribution(samples, "Phase 1")
    print(f"\nWrote {len(samples)} samples to {out_path}")
    return 0


def phase1_extension(append_path: Path, classification_jsonl: Path) -> int:
    if not append_path.exists():
        print(f"[ERROR] {append_path} does not exist. Run phase 1 first.", file=sys.stderr)
        return 1
    existing_keys = set()
    with open(append_path) as f:
        for line in f:
            r = json.loads(line)
            if r.get("phase") == 1:
                existing_keys.add(r["key"])
    print(f"Phase 1 extension: excluding {len(existing_keys)} already-drawn Phase 1 keys")

    rng = random.Random(config.PHASE1_SEED)
    records = load_classification_records(classification_jsonl, set(config.PHASE1_MODELS))
    records = [
        r for r in records
        if config.sample_key(r["model"], r["question"], r["language"], r["sample_id"]) not in existing_keys
    ]
    print(f"  remaining candidate pool: {len(records)}")

    buckets = group_by_class(records)
    drawn = stratified_draw(buckets, config.PHASE1_EXTENSION_QUOTAS, per_combo_ceiling=2, rng=rng)
    new_samples = [record_to_sample(r, phase=1, extension=True) for r in drawn]
    new_samples = verify_code_files(new_samples)
    new_samples.sort(key=lambda s: (s["sonnet4_family_class"], s["model"], s["question"], s["language"], s["sample_id"]))

    with open(append_path, "a") as f:
        for s in new_samples:
            f.write(json.dumps(s) + "\n")
    print_distribution(new_samples, "Phase 1 extension (appended)")
    with open(append_path) as f:
        total = sum(1 for _ in f)
    print(f"\nAppended {len(new_samples)} extension samples to {append_path} (total lines: {total})")
    return 0


def phase2(append_path: Path, classification_jsonl: Path) -> int:
    if not append_path.exists():
        print(f"[ERROR] {append_path} does not exist. Run phase 1 first.", file=sys.stderr)
        return 1
    rng = random.Random(config.PHASE2_SEED)
    records = load_classification_records(classification_jsonl, set(config.PHASE2_MODELS))
    if not records:
        print(f"[ERROR] No opus47 records found in {classification_jsonl}. Did you run update_classification.py after Sonnet 4 opus47 eval?", file=sys.stderr)
        return 1
    print(f"Phase 2: {len(records)} candidate opus47 records")
    buckets = group_by_class(records)
    drawn = stratified_draw(buckets, config.PHASE2_CLASS_QUOTAS, per_combo_ceiling=3, rng=rng)
    drawn = enforce_language_floor(drawn, buckets, config.PHASE2_LANG_FLOOR, rng)
    existing_keys = set()
    with open(append_path) as f:
        for line in f:
            r = json.loads(line)
            existing_keys.add(r["key"])
    new_samples = [record_to_sample(r, phase=2) for r in drawn]
    new_samples = [s for s in new_samples if s["key"] not in existing_keys]
    new_samples = verify_code_files(new_samples)
    new_samples.sort(key=lambda s: (s["sonnet4_family_class"], s["question"], s["language"], s["sample_id"]))
    with open(append_path, "a") as f:
        for s in new_samples:
            f.write(json.dumps(s) + "\n")
    print_distribution(new_samples, "Phase 2 (appended)")
    with open(append_path) as f:
        total = sum(1 for _ in f)
    print(f"\nAppended {len(new_samples)} samples to {append_path} (total lines: {total})")
    return 0


def main() -> int:
    p = argparse.ArgumentParser()
    p.add_argument("--phase", choices=["1", "1-ext", "2"], required=True)
    p.add_argument("--out", type=Path, default=config.SAMPLES_JSONL)
    p.add_argument("--append", type=Path, help="Required for --phase 1-ext / 2: existing samples.jsonl to append to")
    p.add_argument("--classification-jsonl", type=Path,
                   default=config.CLASSIFICATION_PER_SAMPLE,
                   help="Path to classification records. For phase 2 / 1-ext use the augmented file from update_classification.py")
    args = p.parse_args()

    if args.phase == "1":
        return phase1(args.out, args.classification_jsonl)
    target = args.append if args.append else args.out
    if args.phase == "1-ext":
        return phase1_extension(target, args.classification_jsonl)
    return phase2(target, args.classification_jsonl)


if __name__ == "__main__":
    raise SystemExit(main())
