package eu.brickpics.stairseat;

import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;

public class Seat {
    private final Block block;
    private final Arrow arrow;

    public Block getBlock() {
        return block;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public Seat(Block block, Arrow arrow) {
        this.block = block;
        this.arrow = arrow;
    }
}
