# -------------------------------------------------------------------------------
# Name:        sudokubrute
# Purpose:     Enumeration of all possible values and finally solving
#
# Author:      redcrafttv
#
# Created:     10/02/2021
# Copyright:   (c) redcrafttv 2021
# Licence:     MIT
# -------------------------------------------------------------------------------
import tkinter as tk
import tkinter.filedialog as fd
import math

root = tk.Tk()

ui_grid = []
debug_btn = None


def print_spacer():
    print("------------------------------------------------------------")


def get_value(row, col):
    return ui_grid[row - 1][col - 1].get()


def clear_value(row, col):
    ui_grid[row - 1][col - 1].delete(0, len(get_value(row, col)))


def set_value(row, col, value):
    clear_value(row, col)
    ui_grid[row - 1][col - 1].insert(0, value)


def print_values():
    print_spacer()
    for i in ui_grid:
        out_row = []
        for j in i:
            out_row.append(j.get())
        print(out_row)
    print_spacer()


def get_row(row_index):
    result = []
    for cell in ui_grid[row_index - 1]:
        result.append(cell.get())
    return result


def get_column(col_index):
    result = []
    for row in ui_grid:
        result.append(row[col_index - 1].get())
    return result


# def get_block_of_cell_coords(row_index, col_index):
#     return get_block_of_block_coords(
#         get_block_coords_of_cell(row_index, col_index)[0],
#         get_block_coords_of_cell(row_index, col_index)[1]
#     )
#
#
# def get_block_of_block_coords(block_y, block_x):
#     result = []
#     for y in range(3):
#         ui_grid[1][1].get()
#         for x in range(3):
#             pass
#
#     pass
#
#
# def get_block_coords_of_cell(row_index, col_index):
#     block_y = math.ceil(row_index / 3)
#     block_x = math.ceil(col_index / 3)
##     print("ROW: " + str(block_y) + " ;COL: " + str(block_x))
#     return block_y, block_x


def is_value_possible(row, col, value):
    if get_row(row).count(value) > 0:
        return False
    if get_column(col).count(value) > 0:
        return False
    return True


def calculate_possible_values():
    for i in range(9):
        for j in range(9):
            for k in range(9):
                if is_value_possible(i + 1, j + 1, str(k + 1)):
                    set_value()

def remove_double_possibilities():


def debug_cmd():
    calculate_possible_values()


def load_cmd():
    file = fd.askopenfile(mode="r")
    line = file.readline()

    grid = []
    while not line == '':
        line = line.replace("\n", "")

        current_row = []
        for c in line:
            current_row.append(c)
        grid.append(current_row)
        line = file.readline()

    x = 1
    y = 1
    for row in ui_grid:
        for cell in row:
            if str(grid[y - 1][x - 1]) != "x":
                set_value(y, x, str(grid[y - 1][x - 1]))
            else:
                set_value(y, x, "")
            x += 1
        x = 1

        y += 1


def save_cmd():
    file = open(file="save.txt", mode="w")
    for i in ui_grid:
        for j in i:
            if not j.get() == "":
                file.write(j.get())
            else:
                file.write("x")
        file.write("\n")
    file.close()
    pass


def create_ui(root: tk.Tk):
    for r in range(9):
        row = []
        for c in range(9):
            row.append(tk.Entry(root, width=3))
            row[c].grid(row=r, column=c)
        ui_grid.append(row)
    debug_btn = tk.Button(root, text="Debug", command=debug_cmd)
    debug_btn.place(x=200, y=3)

    load_btn = tk.Button(root, text="Load", command=load_cmd)
    load_btn.place(x=200, y=33)

    load_btn = tk.Button(root, text="Save", command=save_cmd)
    load_btn.place(x=200, y=63)


def main():
    create_ui(root=root)
    root.geometry("300x250")
    root.mainloop()


if __name__ == '__main__':
    main()
