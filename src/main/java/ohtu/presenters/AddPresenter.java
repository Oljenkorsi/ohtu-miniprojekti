package ohtu.presenters;

import java.util.Map;

import ohtu.data.ReferenceDao;
import ohtu.models.Reference;
import ohtu.views.AddView;
import ohtu.views.AddView.AddViewListener;

public class AddPresenter implements AddViewListener {
	private AddView view;
	private ReferenceDao dao;

	public AddPresenter(AddView view, ReferenceDao dao) {
		this.view = view;
		this.dao = dao;

		view.setListener(this);
	}

	@Override
	public void saveReference(Map<String, String> input) {
		Reference ref = new Reference(input);
		if (ref.isValid()) {
			dao.addReference(ref);
			view.success();
		} else {
			view.failure();
		}
	}
}
