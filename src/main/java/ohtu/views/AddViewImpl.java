package ohtu.views;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AddViewImpl extends VerticalLayout implements AddView {
	private Button previousButton;
        private ComboBox<String> referenceTypes;
	private ReferenceForm form;
        AddViewListener listener;
	
	public AddViewImpl() {
		setSizeUndefined();
		
		previousButton = new Button("Go Back", e -> UI.getCurrent().getNavigator().navigateTo(""));
		previousButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		previousButton.setHeightUndefined();
		previousButton.setId("previousButton");
                
                referenceTypes = new ComboBox<>("Select reference type");
                referenceTypes.setItems(refTypes());
                referenceTypes.setValue("article");
                form = new ReferenceForm("article");
                referenceTypes.addValueChangeListener(event -> updateForm());
                
		addComponents(previousButton, referenceTypes, form);
	}
        
        private void updateForm() {
            
                removeComponent(form);
                form = new ReferenceForm(referenceTypes.getValue());
                form.setListener(listener);
                addComponent(form);
        }
	
	public void setListener(AddViewListener listener) {
            
                //Saving listener to be updated when combobox value is changed
                this.listener = listener;
		form.setListener(listener);
	}

	@Override
	public void success() {
		Notification.show("Reference saved!");
		UI.getCurrent().getNavigator().navigateTo("");
		form.empty();
	}

	@Override
	public void failure() {
		Notification.show("Failed to save a reference");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}
        
        private ArrayList<String> refTypes() {
            ArrayList<String> refs = new ArrayList<>();
            refs.add("article");
            refs.add("book");
            refs.add("booklet");
            refs.add("conference");
            refs.add("inbook");
            refs.add("incollection");
            refs.add("inproceedings");
            refs.add("manual");
            refs.add("mastersthesis");
            refs.add("misc");
            refs.add("phdthesis");
            refs.add("proceedings");
            refs.add("techreport");
            refs.add("unpublished");
            
            return refs;
        }
}
