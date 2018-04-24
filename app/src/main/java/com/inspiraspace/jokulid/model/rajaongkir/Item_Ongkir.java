package com.inspiraspace.jokulid.model.rajaongkir;

/**
 * Created by mursitaffandi on 4/19/18.
 */

public class Item_Ongkir {
    private String name;
    private boolean selected;

    public Item_Ongkir(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
