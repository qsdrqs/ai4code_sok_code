from __future__ import annotations

import json
import sys
import termios
import tty
from pathlib import Path

from rich.console import Console
from rich.panel import Panel
from rich.syntax import Syntax
from rich.text import Text

_SCRIPT_DIR = Path(__file__).resolve().parent
ITEMS_PATH = _SCRIPT_DIR.parent.parent / "results" / "attribution" / "f_calibration_items.jsonl"
RESULTS_PATH = _SCRIPT_DIR.parent.parent / "results" / "attribution" / "f_calibration_results.jsonl"

LABEL_MAP = {
    "y": "yes",
    "n": "no",
    "u": "uncertain",
}

LANG_TO_LEXER = {
    "Python": "python",
    "JavaScript": "javascript",
    "C": "c",
    "Go": "go",
    "Java": "java",
    "Rust": "rust",
}


def getch() -> str:
    fd = sys.stdin.fileno()
    old = termios.tcgetattr(fd)
    try:
        tty.setraw(fd)
        return sys.stdin.read(1)
    finally:
        termios.tcsetattr(fd, termios.TCSADRAIN, old)


def load_items() -> list[dict]:
    items = []
    with open(ITEMS_PATH) as f:
        for line in f:
            items.append(json.loads(line))
    return items


def load_done() -> dict[str, str]:
    done: dict[str, str] = {}
    if RESULTS_PATH.exists():
        with open(RESULTS_PATH) as f:
            for line in f:
                r = json.loads(line)
                done[r["key"]] = r["label"]
    return done


def item_key(item: dict) -> str:
    return f"{item['sample_id']}_{item['question']}_{item['cwe_category']}"


def save_result(item: dict, label: str) -> None:
    RESULTS_PATH.parent.mkdir(parents=True, exist_ok=True)
    with open(RESULTS_PATH, "a") as f:
        f.write(json.dumps({
            "key": item_key(item),
            "sample_id": item["sample_id"],
            "question": item["question"],
            "orig_lang": item["orig_lang"],
            "cwe_category": item["cwe_category"],
            "trans_cwes": item["trans_cwes"],
            "label": label,
        }) + "\n")


def show_section_transition(
    con: Console,
    prev_cwe: str | None,
    new_cwe: str,
    new_question: str,
) -> None:
    con.clear()

    if prev_cwe is None:
        title = "STARTING SECTION"
        body = (
            f"You are about to review category:\n\n"
            f"  [bold cyan]{new_cwe}[/bold cyan]\n\n"
            f"Question to ask of each ORIGINAL code:\n\n"
            f"  [bold white]{new_question}[/bold white]"
        )
    else:
        title = "!!! SECTION TRANSITION - QUESTION CHANGED !!!"
        body = (
            f"Leaving category:\n\n"
            f"  [dim]{prev_cwe}[/dim]\n\n"
            f"Entering category:\n\n"
            f"  [bold cyan]{new_cwe}[/bold cyan]\n\n"
            f"[bold red]NEW question - reset your mental context:[/bold red]\n\n"
            f"  [bold white]{new_question}[/bold white]"
        )

    con.print(Panel(
        body + "\n\n[dim]Press Enter to continue...[/dim]",
        title=f"[bold red]{title}[/bold red]",
        border_style="red",
        padding=(2, 4),
    ))

    while True:
        ch = getch()
        if ch in ("\r", "\n"):
            return


def render_item(con: Console, item: dict, idx: int, total: int) -> None:
    con.clear()

    header = (
        f"[{idx + 1}/{total}]  "
        f"{item['cwe_category']}  |  "
        f"{item['question']} ({item['orig_lang']})  |  "
        f"sample_id={item['sample_id']}  |  "
        f"F-class samples: {item['f_sample_count']}"
    )
    con.print(Panel(header, style="bold cyan"))

    orig_path = Path(item["orig_file"])
    if orig_path.exists():
        code = orig_path.read_text(encoding="utf-8", errors="replace")
        lexer = LANG_TO_LEXER.get(item["orig_lang"], "text")
        con.print(Panel(
            Syntax(code, lexer, line_numbers=True, word_wrap=True),
            title=f"Original: {orig_path.name}",
            border_style="green",
        ))
    else:
        con.print(f"[red]File not found: {orig_path}[/red]")

    trans_files = item.get("trans_files", [])
    if trans_files:
        summary_lines = []
        for tf in trans_files:
            summary_lines.append(
                f"  [bold magenta]{tf['model']}/{tf['language']:10s}[/bold magenta]  "
                f"[yellow]{tf['trans_cwe']}[/yellow]  -  "
                f"{tf['trans_explanation']}"
            )
        con.print()
        con.print(Panel(
            "\n".join(summary_lines),
            title=f"Translations flagged ({len(trans_files)} F-class samples)",
            border_style="magenta",
        ))

        sample_tf = next(
            (tf for tf in trans_files if Path(tf["path"]).exists()),
            trans_files[0],
        )
        sample_path = Path(sample_tf["path"])
        more = len(trans_files) - 1
        suffix = f"   (+{more} more in summary above)" if more > 0 else ""
        panel_title = (
            f"Sample translation {sample_tf['model']}/{sample_tf['language']} "
            f"flagged {sample_tf['trans_cwe']}: {sample_path.name}{suffix}"
        )
        if sample_path.exists():
            tcode = sample_path.read_text(encoding="utf-8", errors="replace")
            tlexer = LANG_TO_LEXER.get(sample_tf["language"], "text")
            con.print(Panel(
                Syntax(tcode, tlexer, line_numbers=True, word_wrap=True),
                title=panel_title,
                border_style="blue",
            ))
        else:
            con.print(Panel(
                f"[red]File not found: {sample_path}[/red]",
                title=panel_title,
                border_style="red",
            ))

    cwe_list = ", ".join(item["trans_cwes"])
    con.print()
    con.print(Panel(
        f"Translated code was flagged with: [bold yellow]{cwe_list}[/bold yellow]\n\n"
        f"[bold white]{item['review_question']}[/bold white]",
        title="Question",
        border_style="yellow",
    ))

    con.print()
    con.print(
        "[bold green][Y]es[/]  "
        "[bold red][N]o[/]  "
        "[bold blue][U]ncertain[/]  "
        "[dim][S]kip  [P]rev  [Q]uit[/]"
    )


def main() -> int:
    con = Console()
    items = load_items()
    done = load_done()

    pending = [(i, item) for i, item in enumerate(items)
               if item_key(item) not in done]

    if not pending:
        con.print("[green]All items already reviewed![/green]")
        con.print(f"Results: {RESULTS_PATH}")
        return 0

    con.print(f"Total: {len(items)}, already done: {len(done)}, remaining: {len(pending)}")
    con.print("Press any key to start...")
    getch()

    pos = 0
    last_shown_cwe: str | None = None
    while 0 <= pos < len(pending):
        orig_idx, item = pending[pos]

        if item["cwe_category"] != last_shown_cwe:
            show_section_transition(
                con,
                last_shown_cwe,
                item["cwe_category"],
                item["review_question"],
            )

        render_item(con, item, orig_idx, len(items))
        last_shown_cwe = item["cwe_category"]

        ch = getch().lower()

        if ch == "q":
            con.print("\n[yellow]Quit. Progress saved.[/yellow]")
            break
        elif ch == "p":
            if pos > 0:
                pos -= 1
            continue
        elif ch == "s":
            pos += 1
            continue
        elif ch in LABEL_MAP:
            label = LABEL_MAP[ch]
            save_result(item, label)
            done[item_key(item)] = label

            yes_n = sum(1 for v in done.values() if v == "yes")
            no_n = sum(1 for v in done.values() if v == "no")
            unc_n = sum(1 for v in done.values() if v == "uncertain")
            con.print(
                f"\n  -> [bold]{label}[/bold]  "
                f"(running: yes={yes_n} no={no_n} uncertain={unc_n})"
            )

            pos += 1
        

    total_done = len(done)
    con.print(f"\n[green]Done: {total_done}/{len(items)} reviewed[/green]")
    con.print(f"Results saved to: {RESULTS_PATH}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
