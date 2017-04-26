
package ohtu.data;

import java.util.List;

import ohtu.models.Reference;

public class ReferenceDAOImpl implements ReferenceDao {
    private FakeDatabase database;
    
    public ReferenceDAOImpl(FakeDatabase database) {
    	this.database = database;
    }
    
    @Override
	public List<Reference> getAll() {
        return database.getAllRefs();
    } 
    
    @Override
	public void addReference(Reference toAdd) {
		database.addReference(toAdd);
    }
}
