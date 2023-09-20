from dataclasses import dataclass
from turtle import RawTurtle
from typing import Any
import logging


@dataclass
class Note:
    name: str = None
    octave: int = None


@dataclass
class State:
    color: tuple[Any, Any] = None
    pos: tuple[float, float] = None
    angle: float = None


state: State = State()



def save_state(turtle: RawTurtle):
    global state

    if state.color or state.pos:
        logging.warning("A new state was saved while there was still an old one present. it was overwritten")

    logging.debug(f"Original save state: {state}")
    state.color = turtle.color()
    state.pos = turtle.pos()
    state.angle = turtle.heading()
    logging.debug(f"New save state: {state}")


def restore_state(turtle: RawTurtle):
    global state
    if not state.color and not state.pos and not state.angle:
        logging.warning("function restore_state() in canvasutils.py was called but there was no saved state.")
        return

    turtle.pencolor(state.color[0])
    turtle.fillcolor(state.color[1])
    turtle.goto(state.pos[0], state.pos[1])
    turtle.setheading(state.angle)

    state.color = None
    state.pos = None
    state.angle = None





def draw_note(turtle: RawTurtle, x: int, y: int):
    save_state(turtle)

    turtle.penup()
    turtle.goto(x, y)
    turtle.color("white")
    turtle.pendown()
    turtle.begin_fill()
    turtle.circle(23)
    turtle.end_fill()

    restore_state(turtle)


def draw_bg(turtle: RawTurtle, color: str):
    save_state(turtle)

    turtle.color(color)
    turtle.penup()
    turtle.goto(0, 0)
    turtle.begin_fill()
    turtle.goto(-750, -500)
    turtle.begin_fill()
    turtle.goto(-750, 500)
    turtle.goto(750, 500)
    turtle.goto(750, -500)
    turtle.end_fill()

    restore_state(turtle)


def draw_grand_staff(turtle: RawTurtle):

    save_state(turtle)

    turtle.color("white")

    turtle.penup()
    turtle.goto(-325, 100)
    turtle.pensize(5)

    turtle.pendown()
    turtle.right(90)
    turtle.forward(200)

    turtle.left(90)
    turtle.forward(650)
    turtle.left(90)
    turtle.forward(200)
    turtle.left(90)
    turtle.forward(650)

    turtle.left(90)

    for i in range(3):
        turtle.forward(50)
        turtle.left(90)
        turtle.forward(650)
        turtle.back(650)
        turtle.right(90)



    turtle.penup()

    restore_state(turtle)
