package org.oop.app;

import org.oop.app.appView.FormGamesView;
import org.oop.app.appView.FormProfilesView;
import org.oop.app.appView.NavigableGridProfilesView;
import org.oop.app.appView.NavigableGridGamesView;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Route @PageTitle("VAADIN Scrum")
public class MainView extends VerticalLayout implements RouterLayout {

    public MainView() { setMenuBar();}
    private void setMenuBar() {
        MenuBar mainMenu = new MenuBar();
        MenuItem homeMenu = mainMenu.addItem("Home");

        homeMenu.addClickListener(event -> UI.getCurrent().navigate(MainView.class));

        //
        MenuItem gridFormsPlayersMenu = mainMenu.addItem("Players");
        SubMenu gridFormsPlayersMenuBar = gridFormsPlayersMenu.getSubMenu();
        gridFormsPlayersMenuBar.addItem("Lista Players...",
                event -> UI.getCurrent().navigate(NavigableGridProfilesView.class));
        gridFormsPlayersMenuBar.addItem("Form Editare Player...",
                event -> UI.getCurrent().navigate(FormProfilesView.class));
        add(new HorizontalLayout(mainMenu));

        MenuItem gridFormsGameMenu = mainMenu.addItem("Games");
        SubMenu gridFormsGameMenuBar = gridFormsGameMenu.getSubMenu();
        gridFormsGameMenuBar.addItem("Games list...",
                event -> UI.getCurrent().navigate(NavigableGridGamesView.class));
        gridFormsGameMenuBar.addItem("Form Edit game...",
                event -> UI.getCurrent().navigate(FormGamesView.class));
        add(new HorizontalLayout(mainMenu));
    }
}

