package ohtu.app;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import ohtu.data.FakeDatabase;
import ohtu.data.ReferenceDAOImpl;
import ohtu.data.ReferenceDao;
import ohtu.presenters.AddPresenter;
import ohtu.presenters.MainPresenter;
import ohtu.utilities.Fields;
import ohtu.views.AddView;
import ohtu.views.AddViewImpl;
import ohtu.views.MainView;
import ohtu.views.MainViewImpl;

@SuppressWarnings("serial")
@PreserveOnRefresh
public class Main extends UI {
    
    Fields fields;
    ReferenceDao dao;
    MainView mainView;
    AddView addView;
    Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("Main page - Title");
        initValues();
        new MainPresenter(mainView, dao);
        new AddPresenter(addView, dao);
        navigator.addView("", mainView);
        navigator.addView("addRefs", addView);
    }

    private void initValues() {
        fields = new Fields();
        dao = new ReferenceDAOImpl(new FakeDatabase());
        mainView = new MainViewImpl();
        addView = new AddViewImpl();
        navigator = new Navigator(this, this);
    }

    @WebServlet(urlPatterns = "/*", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}