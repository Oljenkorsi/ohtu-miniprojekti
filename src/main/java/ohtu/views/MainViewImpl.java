package ohtu.views;

import java.util.List;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import ohtu.models.Reference;

@SuppressWarnings("serial")
public class MainViewImpl extends VerticalLayout implements MainView {

	private Button addButton;
        private ExportButton exportButton;
	private VerticalLayout layout;
        private HorizontalLayout buttonLayout;
	private Grid<Reference> grid;
	private MainViewListener listener;

	public MainViewImpl() {
		addButton = new Button("Add references", event -> UI.getCurrent().getNavigator().navigateTo("addRefs"));
		addButton.setId("addButton");
                exportButton = new ExportButton();
		
		grid = new Grid<>();
                buttonLayout = new HorizontalLayout(addButton, exportButton);
		layout = new VerticalLayout(buttonLayout, grid);
		layout.setSizeFull();
		grid.setSizeFull();
		layout.setExpandRatio(grid, 1);

		grid.addColumn(Reference::getType).setCaption("Type");
		grid.addColumn(Reference::getAuthor).setCaption("Author");
		grid.addColumn(Reference::getTitle).setCaption("Title");
		grid.addColumn(Reference::getYear).setCaption("Year");
		grid.addColumn(Reference::getJournal).setCaption("Journal");
		grid.addColumn(Reference::getKey).setCaption("Key");

		addComponents(layout);
	}

	public void setReferences(List<Reference> refs) {
		grid.setItems(refs);
                exportButton.setReferences(refs);
	}

	@Override
	public void setListener(MainViewListener listener) {
		this.listener = listener;

	}

	@Override
	public void enter(ViewChangeEvent event) {
		listener.updateLists();
	}
}