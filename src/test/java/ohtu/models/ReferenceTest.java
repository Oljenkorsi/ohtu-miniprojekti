package ohtu.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ohtu.models.validators.ReferenceValidator;
import ohtu.utilities.Fields;

public class ReferenceTest {
	private Reference ref;

	@Before
	public void initialize() {
		new Fields();
		ref = new Reference().set("type", "book").set("title", "Veljeni Leijonamieli").set("author", "Astrid Lindgren")
				.set("year", "1973").set("publisher", "Raben & Sjögren").set("key", "SWEBOK");
	}

	@Test
	public void constructorInitializesPropertiesCorrectly() {
		assertEquals("Veljeni Leijonamieli", ref.getTitle());
		assertEquals("Astrid Lindgren", ref.getAuthor());
		assertEquals("1973", ref.getYear());
	}

	@Test
	public void constructorInitializesPropertiesFromMap() {
		Map<String, String> m = new HashMap<>();
		m.put("kEy", "bibtexKey");
		m.put("journal", "something");
		m.put("type", "Book");
		Reference ref = new Reference(m);
		assertEquals(ref.getKey(), "bibtexKey");
		assertEquals(ref.getJournal(), "something");
		assertEquals(ref.getType(), "Book");
	}

	@Test
	public void bookValidatesCorrectly() {
		assertTrue(ref.isValid());
		ref.set("title", "");
		assertFalse(ref.isValid());
	}

	@Test
	public void addingValidatorsWorks() {
		ref.addValidator(new ReferenceValidator() {
			@Override
			public boolean isValid(Reference reference) {
				return reference.get("year").equals("1973");
			}
		});
		assertTrue(ref.isValid());
	}

	@Test
	public void settersAreCaseInsensite() {
		ref.set("tiTLE", "title");
		assertEquals(ref.get("title"), "title");
		ref.set("TITLE", "second");
		assertEquals(ref.get("title"), "second");
	}
	
	@Test
	public void bibtexGenerationWorks() {
		assertTrue(ref.toBibTex().startsWith("@book{SWEBOK,"));
		assertFalse(ref.toBibTex().contains("type={"));
		assertFalse(ref.toBibTex().contains("key={"));
	}
}