package org.davidsadowsky.firemaking.data;

import org.rspeer.runetek.api.movement.position.Position;

import java.util.Comparator;

public class TileComparator implements Comparator<Position> {

    public int compare(final Position a, final Position b) {
        return a.getX() > b.getX() ? 1 : a.getX() == b.getX() ? 1 : 0;
    }
}
