package com.example.andres.proyectofinal;

/**
 * Created by Andrés on 05/02/2016.
 */
public class UserRecyclerView {
    private String icon;
    private String name;
    private int punt;

    UserRecyclerView(String icon, String name, Integer punt){
        this.icon = icon;
        this.name = name;
        this.punt = punt;

    }
    UserRecyclerView(){

    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPunt() {
        return punt;
    }

    public void setPunt(String phone) {
        this.punt = punt;
    }
}
