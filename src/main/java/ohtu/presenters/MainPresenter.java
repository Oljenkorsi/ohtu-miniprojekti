package ohtu.presenters;

import ohtu.data.ReferenceDao;
import ohtu.views.MainView;
import ohtu.views.MainView.MainViewListener;

public class MainPresenter implements MainViewListener {
	private MainView view;
	private ReferenceDao dao;

	public MainPresenter(MainView view, ReferenceDao dao) {
		this.view = view;
		this.dao = dao;

		view.setListener(this);
		updateLists();
	}

	@Override
	public void updateLists() {
		view.setReferences(dao.getAll());
	}
}
