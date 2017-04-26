package ohtu.presenters;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ohtu.stubs.AddViewStub;
import ohtu.stubs.ReferenceDaoStub;

public class AddPresenterTest {
	private AddPresenter presenter;
	private AddViewStub stubView;
	private ReferenceDaoStub stubDao;

	public Map<String, String> getValidInput() {
		Map<String, String> input = new HashMap<>();
		input.put("type", "book");
		input.put("title", "Veljeni Leijonamieli");
		input.put("author", "Astrid Lindgren");
		input.put("year", "1973");
		input.put("publisher", "publisher");
		input.put("key", "SWEBOK");
		return input;
	}
	
	@Before
	public void init() {
		stubView = new AddViewStub();
		stubDao = new ReferenceDaoStub();
		presenter = new AddPresenter(stubView, stubDao);
	}

	@Test
	public void saveReferenceWithValidReference() {
		presenter.saveReference(getValidInput());
		assertTrue(stubDao.count == 1);
	}
	
	@Test
	public void saveReferenceWithInvalidReference() {
		Map<String, String> input = getValidInput();
		input.remove("title");
		presenter.saveReference(input);
		assertTrue(stubDao.count == 0);
	}
}
