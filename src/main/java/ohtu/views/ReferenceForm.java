
package ohtu.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ohtu.views.AddView.AddViewListener;

@SuppressWarnings("serial")
public class ReferenceForm extends VerticalLayout {

	private List<TextField> fields;
	private List<String> fieldNames = Arrays.asList("Key", "Title", "Author", "Journal", "Year", "Volume");
	private Button send = new Button("Add", e -> saveReference());
	private AddViewListener listener;

	public ReferenceForm() {
		fields = new ArrayList<>();
		fieldNames.forEach(s -> fields.add(new TextField(s)));
		fields.forEach(f -> addComponent(f));
                send.setId("addRef");
		addComponent(send);
	}

	private void saveReference() {
		if (listener == null) return;
		
		Map<String, String> input = new HashMap<>();
		
		fields.forEach(f -> {
			input.put(f.getCaption(), f.getValue());
		});

		listener.saveReference(input);
	}
	
	public void setListener(AddViewListener listener) {
		this.listener = listener;
	}
	
	public void empty() {
		fields.forEach(f -> f.clear());
	}
}
