package org.oop.app.appView;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.oop.app.Player_profile;
import org.oop.app.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.oop.app.appView.NavigableGridProfilesView;

import java.util.List;

@PageTitle("profiles")
@Route(value = "profiles", layout = MainView.class)
public class FormProfilesView extends VerticalLayout implements HasUrlParameter<Integer> {
    private static final long serialVersionUID = 1L;
    private EntityManager em;
    private Player_profile profile = null;
    private Binder<Player_profile> binder = new Binder<>(Player_profile.class);

    private VerticalLayout formLayoutToolbar;
    private H1 titluForm = new H1("Form Player");
    private IntegerField id = new IntegerField("ID player:");
    private TextField username = new TextField("Username: ");

    private Button cmdAdaugare = new Button("Adauga");
    private Button cmdSterge = new Button("Sterge");
    private Button cmdAbandon = new Button("Abandon");
    private Button cmdSalveaza = new Button("Salveaza");

    public FormProfilesView() {
        initDataModel();
        initViewLayout();
        initControllerActions();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer idplayer) {
        System.out.println("Profile ID: " + idplayer);
        if (idplayer != null) {
            this.profile = em.find(Player_profile.class, idplayer);
            System.out.println("Selected profile to edit:: " + profile);
            if (this.profile == null) {
                System.out.println("ADD profile:: " + profile);
                this.adaugaPlayer();
                this.profile.setIdplayer(idplayer);

                this.profile.setUsername("player NOU " + idplayer);
            }
        }
        this.refreshForm();
    }

    private void initDataModel() {
        System.out.println("DEBUG START FORM >>>  ");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proiectbun");
        this.em = emf.createEntityManager();
        List<Player_profile> profiles = em
                .createQuery("SELECT pp FROM Player_profile pp ORDER BY pp.idplayer", Player_profile.class)
                .getResultList();
        if (profiles != null && !profiles.isEmpty()) {
            this.profile = profiles.get(0);
        }
        binder.forField(id).bind("idplayer");
        binder.forField(username).bind("username");

        refreshForm();
    }

    private void initViewLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(id, username);
        formLayout.setResponsiveSteps(new ResponsiveStep("0", 1));
        formLayout.setMaxWidth("400px");
        HorizontalLayout actionToolbar =
                new HorizontalLayout(cmdAdaugare, cmdSterge, cmdAbandon, cmdSalveaza);

        this.add(titluForm, formLayout, actionToolbar);
    }

    private void initControllerActions() {
        cmdAdaugare.addClickListener(e -> {
            adaugaPlayer();
            refreshForm();
        });
        cmdSterge.addClickListener(e -> {
            stergePlayer();
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridProfilesView.class)
            );
        });
        cmdAbandon.addClickListener(e -> {
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridProfilesView.class)
            );
        });
        cmdSalveaza.addClickListener(e -> {
            salveazaPlayer();
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridProfilesView.class)
            );
        });
    }

    private void refreshForm() {
        System.out.println("Player curent: " + this.profile);
        if (this.profile != null) {
            binder.setBean(this.profile);
        }
    }

    private void adaugaPlayer() {
        this.profile = new Player_profile();
        this.profile.setIdplayer(222);
        this.profile.setUsername("Player Nou");

    }

    private void stergePlayer() {
        System.out.println("To remove: " + this.profile);
        if (this.em.contains(this.profile)) {
            try {
                this.em.getTransaction().begin();
                this.em.remove(this.profile);
                this.em.getTransaction().commit();
            } catch (Exception ex) {
                if (this.em.getTransaction().isActive())
                    this.em.getTransaction().rollback();
                System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

    private void salveazaPlayer() {
        try {
            this.em.getTransaction().begin();
            this.profile = this.em.merge(this.profile);
            this.em.getTransaction().commit();
            System.out.println("Player Salvat");
        } catch (Exception ex) {
            if (this.em.getTransaction().isActive())
                this.em.getTransaction().rollback();
            System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
