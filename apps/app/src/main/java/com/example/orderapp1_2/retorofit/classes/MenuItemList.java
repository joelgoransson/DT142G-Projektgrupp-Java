package com.example.orderapp1_2.retorofit.classes;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Skapar en elementlist av typen MenuItem
 */

@Root(name="menuitems", strict=false)
public class MenuItemList {
    @ElementList(name = "menuitem", inline=true)
    private List<MenuItem> menuList;

    public List<MenuItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuItem> menuList) {
        this.menuList = menuList;
    }
}
