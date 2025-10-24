package modelo;

import javax.sql.DataSource;

public class FacturaDao extends AbstractBean
{
	private DataSource ds;

	public FacturaDao() {
		super();
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}
	
}

