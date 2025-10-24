package modelo;

import javax.sql.DataSource;

public class ClienteDao extends AbstractBean
{
	private DataSource ds;

	public ClienteDao() {
		super();
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}
	
}
