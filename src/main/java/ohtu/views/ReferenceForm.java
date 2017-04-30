
package ohtu.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ohtu.utilities.Fields;

import ohtu.views.AddView.AddViewListener;

@SuppressWarnings("serial")
public class ReferenceForm extends VerticalLayout {

	private List<TextField> fields;
	private List<String> fieldNames;
        
        private List<TextField> reqFields;
	private List<String> reqFieldNames;
        private List<TextField> optFields;
	private List<String> optFieldNames;
        
        
	private Button send = new Button("Add", e -> saveReference());
	private AddViewListener listener;
        private String type;

        
        public ReferenceForm(String referenceType) {
            
            type = referenceType;
         
            addComponent(new Label("Required"));
            reqFields = new ArrayList<>();
            Notification.show(referenceType);
            reqFieldNames = Arrays.asList(Fields.getRequired(type));
            reqFieldNames.forEach(s -> reqFields.add(new TextField(s)));
	    reqFields.forEach(f -> addComponent(f));
            
            // Add a couple of empty rows to make form easier to read
            addComponent(new Label(" "));
            addComponent(new Label(" "));
            
            addComponent(new Label("Optional"));
            optFields = new ArrayList<>();
            optFieldNames = Arrays.asList(Fields.getOptional(type));
            optFieldNames.forEach(s -> optFields.add(new TextField(s)));
	    optFields.forEach(f -> addComponent(f));
            
            // This initialization is done only for easý saving
            fields = new ArrayList<>();
            fields = reqFields;
            fields.addAll(optFields);
            
            send.setId("addRef");
            addComponent(send); 
        }
        
	public ReferenceForm() {
		fields = new ArrayList<>();
                type = "default";
                fieldNames = Arrays.asList("Key", "Title", "Author", "Journal", "Year", "Volume");
		fieldNames.forEach(s -> fields.add(new TextField(s)));
		fields.forEach(f -> addComponent(f));
                send.setId("addRef");
		addComponent(send);
	}

	private void saveReference() {
		if (listener == null) return;
		
		Map<String, String> input = new HashMap<>();
		input.put("TYPE", type);
		fields.forEach(f -> input.put(f.getCaption(), f.getValue()));

		listener.saveReference(input);
	}
	
	public void setListener(AddViewListener listener) {
		this.listener = listener;
	}
	
	public void empty() {
		fields.forEach(f -> f.clear());
	}
}
