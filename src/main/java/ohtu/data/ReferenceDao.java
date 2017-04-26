package ohtu.data;

import java.util.List;

import ohtu.models.Reference;

public interface ReferenceDao {
	
	List<Reference> getAll();

	void addReference(Reference toAdd);
}