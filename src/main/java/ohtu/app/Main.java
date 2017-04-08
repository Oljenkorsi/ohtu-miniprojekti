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
import ohtu.data.ReferenceDAO;
import ohtu.views.AddPresenter;
import ohtu.views.AddView;
import ohtu.views.AddViewImpl;
import ohtu.views.MainPresenter;
import ohtu.views.MainView;
import ohtu.views.MainViewImpl;

@SuppressWarnings("serial")
@Theme("mytheme")
@PreserveOnRefresh
public class Main extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		getPage().setTitle("Main page - Title");
		
		ReferenceDAO dao = new ReferenceDAO(new FakeDatabase());

		MainView mainView = new MainViewImpl();
		MainPresenter mainPresenter = new MainPresenter(mainView, dao);
		
		AddView addView = new AddViewImpl();
		AddPresenter addPresenter = new AddPresenter(addView, dao);
	
		Navigator navigator = new Navigator(this, this);
		navigator.addView("", mainView);
		navigator.addView("addRefs", addView);
	}

	@WebServlet(urlPatterns = "/*", asyncSupported = true)
	@VaadinServletConfiguration(ui = Main.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

}