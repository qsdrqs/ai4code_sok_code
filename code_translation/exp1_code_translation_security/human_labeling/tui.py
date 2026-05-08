from __future__ import annotations

import argparse
import json
import sys
import termios
import tty
from datetime import datetime, timezone
from pathlib import Path
from typing import Iterable

from rich.console import Console
from rich.layout import Layout
from rich.panel import Panel
from rich.syntax import Syntax
from rich.table import Table
from rich.text import Text

import config

LANG_TO_LEXER = {
    "Python": "python",
    "JavaScript": "javascript",
    "C": "c",
    "Go": "go",
    "Java": "java",
    "Rust": "rust",
}

VERDICT_KEYS = {"y": "agree", "n": "disagree", "u": "uncertain"}


def getch() -> str:
    fd = sys.stdin.fileno()
    old = termios.tcgetattr(fd)
    try:
        tty.setraw(fd)
        return sys.stdin.read(1)
    finally:
        termios.tcsetattr(fd, termios.TCSADRAIN, old)


def read_line_cooked(prompt: str, con: Console) -> str:
    con.print(prompt, end="")
    try:
        return input().strip()
    except (EOFError, KeyboardInterrupt):
        return ""


def load_samples(path: Path) -> list[dict]:
    with open(path) as f:
        return [json.loads(l) for l in f]


def load_labels(path: Path) -> dict[str, dict]:
    done: dict[str, dict] = {}
    if path.exists():
        with open(path) as f:
            for line in f:
                r = json.loads(line)
                done[r["key"]] = r
    return done


def save_label(path: Path, record: dict) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with open(path, "a") as f:
        f.write(json.dumps(record) + "\n")


def code_line_count(code_path: Path) -> int:
    if not code_path.exists():
        return 0
    return code_path.read_text(encoding="utf-8", errors="replace").count("\n") + 1


def render_code_panel(
    code_path: Path,
    lang: str,
    title: str,
    border: str,
    offset: int,
    visible_lines: int,
) -> Panel:
    lexer = LANG_TO_LEXER.get(lang, "text")
    if not code_path.exists():
        return Panel(
            Text(f"File not found: {code_path}", style="red"),
            title=title,
            border_style="red",
        )
    text = code_path.read_text(encoding="utf-8", errors="replace")
    total = text.count("\n") + 1
    start = max(1, offset + 1)
    end = min(total, start + visible_lines - 1)
    if start > total:
        start = max(1, total - visible_lines + 1)
        end = total
    syn = Syntax(
        text,
        lexer,
        line_numbers=True,
        word_wrap=True,
        theme="monokai",
        line_range=(start, end),
    )
    showing = f"lines {start}-{end} of {total}" if total > visible_lines else f"{total} lines"
    return Panel(
        syn,
        title=f"{title} ({code_path.name}, {lang}, {showing})",
        border_style=border,
    )


def render_verdict_panel(sample: dict) -> Panel:
    is_vuln = sample["sonnet4_is_vulnerable"]
    cwe = sample["sonnet4_cwe"] or "(none)"
    expl = sample["sonnet4_explanation"] or "(no explanation)"
    verdict_word = "INSECURE" if is_vuln else "SECURE"
    color = "red" if is_vuln else "green"
    tbl = Table.grid(padding=(0, 2))
    tbl.add_row(Text("Sonnet 4 verdict:", style="bold"), Text(verdict_word, style=f"bold {color}"))
    tbl.add_row(Text("CWE:", style="bold"), Text(cwe))
    tbl.add_row(Text("Explanation:", style="bold"), Text(expl))
    return Panel(tbl, title="LLM prediction (to verify)", border_style=color)


def render_header(sample: dict, idx: int, total: int, stats: dict[str, int]) -> Panel:
    banner = (
        f"[{idx}/{total}]  "
        f"{sample['model']}  |  "
        f"{sample['question']}  {sample['source_language']} -> {sample['language']}  |  "
        f"sid={sample['sample_id']}  |  class={sample['sonnet4_family_class']}  |  "
        f"phase={sample['phase']}"
    )
    tally = f"  agree={stats['agree']}  disagree={stats['disagree']}  uncertain={stats['uncertain']}"
    return Panel(Text(banner + tally, style="bold cyan"))


def render_keybinds(scroll_info: str) -> Text:
    parts = [
        ("[Y]agree", "bold green"),
        ("[N]disagree", "bold red"),
        ("[U]ncertain", "bold blue"),
        ("[J/K]scroll  [SPACE/B]page  [G/Shift+G]top/bot", "cyan"),
        ("[S]kip [P]rev [Q]uit", "dim"),
    ]
    t = Text()
    for i, (s, style) in enumerate(parts):
        if i:
            t.append("   ")
        t.append(s, style=style)
    t.append("    ")
    t.append(scroll_info, style="dim")
    return t


def render_sample(
    con: Console,
    sample: dict,
    idx: int,
    total: int,
    stats: dict[str, int],
    scroll: int = 0,
) -> int:
    con.clear()
    con.print(render_header(sample, idx, total, stats))
    term_height = con.size.height
    code_height = max(10, term_height - 14)
    visible_lines = max(4, code_height - 2)
    orig_path = config.REPO_ROOT / sample["orig_code_path"]
    trans_path = config.REPO_ROOT / sample["trans_code_path"]
    orig_total = code_line_count(orig_path)
    trans_total = code_line_count(trans_path)
    max_total = max(orig_total, trans_total)
    max_offset = max(0, max_total - visible_lines)
    scroll = max(0, min(scroll, max_offset))
    layout = Layout()
    layout.split_row(Layout(name="left"), Layout(name="right"))
    layout["left"].update(render_code_panel(orig_path, sample["source_language"], "Original", "green", scroll, visible_lines))
    layout["right"].update(render_code_panel(trans_path, sample["language"], "Translated", "magenta", scroll, visible_lines))
    con.print(layout, height=code_height)
    con.print(render_verdict_panel(sample))
    scroll_info = f"scroll={scroll} (max={max_offset})  orig={orig_total}L  trans={trans_total}L"
    con.print(render_keybinds(scroll_info))
    return max_offset


def compute_stats(done: dict[str, dict]) -> dict[str, int]:
    return {
        "agree": sum(1 for r in done.values() if r["user_verdict"] == "agree"),
        "disagree": sum(1 for r in done.values() if r["user_verdict"] == "disagree"),
        "uncertain": sum(1 for r in done.values() if r["user_verdict"] == "uncertain"),
    }


def commit_verdict(
    sample: dict,
    verdict: str,
    reason: str | None,
    labels_path: Path,
    done: dict[str, dict],
) -> None:
    record = {
        "key": sample["key"],
        "user_verdict": verdict,
        "disagreement_reason": reason,
        "labeled_at_utc": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
    }
    save_label(labels_path, record)
    done[sample["key"]] = record


def headless_loop(samples: list[dict], done: dict[str, dict], labels_path: Path) -> int:
    pending = [s for s in samples if s["key"] not in done]
    if not pending:
        print("[headless] nothing to label", file=sys.stderr)
        return 0
    for sample in pending:
        try:
            line = input()
        except EOFError:
            break
        line = line.rstrip("\n")
        if not line:
            continue
        verdict_char = line[0].lower()
        if verdict_char not in VERDICT_KEYS:
            print(f"[headless] skipping invalid input: {line!r}", file=sys.stderr)
            continue
        verdict = VERDICT_KEYS[verdict_char]
        reason = line[1:].strip() or None if verdict == "disagree" else None
        commit_verdict(sample, verdict, reason, labels_path, done)
        print(f"[headless] {sample['key']} -> {verdict}{' (reason: ' + reason + ')' if reason else ''}")
    return 0


SCROLL_STEP = 10


def interactive_loop(samples: list[dict], done: dict[str, dict], labels_path: Path) -> int:
    con = Console()
    pending_idxs = [i for i, s in enumerate(samples) if s["key"] not in done]
    if not pending_idxs:
        con.print("[green]All samples already labeled![/green]")
        con.print(f"Labels: {labels_path}")
        return 0
    con.print(f"Total: {len(samples)}, done: {len(done)}, remaining: {len(pending_idxs)}")
    con.print("Press any key to start (Q to quit)...")
    if getch().lower() == "q":
        return 0
    pos = 0
    scroll = 0
    stats = compute_stats(done)
    while 0 <= pos < len(pending_idxs):
        orig_idx = pending_idxs[pos]
        sample = samples[orig_idx]
        max_offset = render_sample(con, sample, orig_idx + 1, len(samples), stats, scroll)
        raw = getch()
        ch = raw.lower()
        if ch == "q":
            con.print("\n[yellow]Quit. Progress saved.[/yellow]")
            break
        if ch == "p":
            if pos > 0:
                pos -= 1
                scroll = 0
            continue
        if ch == "s":
            pos += 1
            scroll = 0
            continue
        if ch == "j":
            scroll = min(max_offset, scroll + SCROLL_STEP)
            continue
        if ch == "k":
            scroll = max(0, scroll - SCROLL_STEP)
            continue
        if ch == " ":
            scroll = min(max_offset, scroll + 2 * SCROLL_STEP)
            continue
        if ch == "b":
            scroll = max(0, scroll - 2 * SCROLL_STEP)
            continue
        if raw == "g":
            scroll = 0
            continue
        if raw == "G":
            scroll = max_offset
            continue
        if ch not in VERDICT_KEYS:
            continue
        verdict = VERDICT_KEYS[ch]
        reason = None
        if verdict == "disagree":
            con.print("\n[bold red]Disagree.[/bold red] Press [R] to type a reason, any other key to skip reason.")
            sub = getch().lower()
            if sub == "r":
                reason = read_line_cooked("Reason: ", con) or None
        commit_verdict(sample, verdict, reason, labels_path, done)
        stats = compute_stats(done)
        con.print(f"  -> [bold]{verdict}[/bold]   (agree={stats['agree']} disagree={stats['disagree']} uncertain={stats['uncertain']})")
        pos += 1
        scroll = 0
    total_done = len(done)
    con.print(f"\n[green]Done: {total_done}/{len(samples)} labeled[/green]")
    con.print(f"Labels saved to: {labels_path}")
    return 0


def main() -> int:
    p = argparse.ArgumentParser()
    p.add_argument("--samples", type=Path, default=config.SAMPLES_JSONL)
    p.add_argument("--labels", type=Path, default=config.LABELS_JSONL)
    p.add_argument("--headless", action="store_true", help="Read verdicts from stdin, one per line")
    args = p.parse_args()

    samples = load_samples(args.samples)
    done = load_labels(args.labels)

    if args.headless:
        return headless_loop(samples, done, args.labels)
    return interactive_loop(samples, done, args.labels)


if __name__ == "__main__":
    raise SystemExit(main())
