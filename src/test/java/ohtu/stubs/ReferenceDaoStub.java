package ohtu.stubs;

import java.util.ArrayList;
import java.util.List;

import ohtu.data.ReferenceDao;
import ohtu.models.Reference;

public class ReferenceDaoStub implements ReferenceDao {
	public int count = 0;
	@Override
	public List<Reference> getAll() {
		return new ArrayList<>();
	}

	@Override
	public void addReference(Reference toAdd) {
		count++;		
	}
}