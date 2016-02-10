package com.example.andres.proyectofinal;

/**
 * Created by Andrés on 05/02/2016.
 */
public class UserRecyclerView {
    private int icon;
    private String name;
    private int punt;

    UserRecyclerView(int icon, String name, Integer punt){
        this.icon = icon;
        this.name = name;
        this.punt = punt;

    }
    UserRecyclerView(){

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
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
