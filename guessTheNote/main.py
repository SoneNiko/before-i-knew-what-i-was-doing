import random
import sys
import time
from tkinter import *
from canvasutils import *

root = Tk()
bg_color = "#ffffff"
note = "A#"
busy = False


def main(window):
    global bg_color
    debug = "--debug" in sys.argv

    logging.basicConfig(level=logging.DEBUG if debug else logging.WARN)
    bg_color = "#454545" if debug else "#2e2e2e"

    tkinter_version = Tcl().eval("info patchlevel")
    logging.debug(f"Using {tkinter_version}")

    canvas, turtle = initialize_ui(window=window, debug=debug)

    prepare(turtle)

    root.mainloop()


def initialize_ui(window, debug: bool):
    window.title("Guess The Note")
    window.configure(background="#2e2e2e")
    window.resizable(False, False)

    canvas = Canvas(
        window,
        height=500,
        width=750,
        bd=0,
        highlightthickness=0,
        relief="ridge"
    )
    canvas.grid(padx=10, pady=10)

    entry = Entry(
        width=15,
        background="#454545",
        foreground="#ffffff",
        bd=0,
        highlightthickness=0,
        relief="ridge"
    )
    entry.grid(padx=20, pady=10)

    feedback = Label(
        foreground="#ffffff",
        background="#2e2e2e",
        text=""
    )
    feedback.grid(pady=10)

    window.bind("<Return>", lambda event: submit_entry(event, feedback, entry, turtle))

    turtle = RawTurtle(canvas=canvas)

    turtle.speed(0)

    if not debug:
        turtle.hideturtle()

    draw_bg(turtle, bg_color)

    return canvas, turtle


def submit_entry(event: Event, feedback: Label, entry: Entry, turtle: RawTurtle):
    global note, busy
    if busy:
        return

    busy = True
    if len(entry.get()) == 0:
        logging.info("No Inṕut submitted")
        feedback.config(text="NO INPUT", foreground="#fcba03")
        busy = False
        return

    if entry.get() != note:
        logging.info(f"{entry.get()} is correct")
        feedback.config(text=f"WRONG, it was {note}", foreground="#ff0000")
    else:
        logging.info(f"{entry.get()} is incorrect")
        feedback.config(text="CORRECT", foreground="#00ff00")


    # TODO play sound

    entry.delete(0, 'end')
    prepare(turtle)
    busy = False


def prepare(turtle: RawTurtle):
    random_note()

    # reset canvas
    draw_bg(turtle, bg_color)
    draw_grand_staff(turtle)
    #   paint clefs
    draw_note(turtle, 0, 0)
    #   paint note

    pass


def random_note():
    global note
    # get random note
    base_note = random.choice(["C", "D", "E", "F", "G", "A", "B"])

    append = None
    match random.randint(1, 3):
        case 1:
            # Natural
            append = ""
        case 2:
            # Flat
            append = "b"
        case 3:
            # Sharp
            append = "#"

    note = base_note + append
    logging.debug(f"Final note: {note}")


if __name__ == '__main__':
    main(window=root)
