package org.mik.zoo.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mik.zoo.livingbeing.AbstractLivingbeing;
import org.mik.zoo.livingbeing.Livingbeing;
import org.mik.zoo.livingbeing.animal.Animal;
import org.mik.zoo.livingbeing.animal.bird.Ostrich;
import org.mik.zoo.livingbeing.animal.bird.Penguin;
import org.mik.zoo.livingbeing.animal.fish.Barracuda;
import org.mik.zoo.livingbeing.animal.fish.WhiteShark;
import org.mik.zoo.livingbeing.animal.mammal.AfricanBuffalo;
import org.mik.zoo.livingbeing.animal.mammal.Elephant;
import org.mik.zoo.livingbeing.animal.mammal.Hyppopotamus;
import org.mik.zoo.livingbeing.animal.mammal.Mammal;
import org.mik.zoo.livingbeing.animal.mammal.Rhinoceros;

public class ZooDao implements Dao {

	private final static Logger LOGGER =  LogManager.getLogger(Dao.class);
	
	private static Dao instance = null;
	
	private Connection connection;
	
	private ZooDao() {
		
	}
	
	public synchronized static Dao getInstance() {
		if (instance==null)
			instance = new ZooDao();
		
		return instance;
	}
	
	@Override
	public Livingbeing getById(Integer id) {
		LOGGER.debug(String.format("Enter getById, id:%d", id)); //$NON-NLS-1$
		if (!open())
			return null;
		
		String sqlCommand = String.format("Select * from %s where id=%d", Livingbeing.TABLE_NAME, id); //$NON-NLS-1$
		try (Statement stmt = this.connection.createStatement()){
			try(ResultSet rSet = stmt.executeQuery(sqlCommand)) {
				String scientificName = rSet.getString(Livingbeing.COL_SCIENTIFIC_NAME);
				return createInstance(rSet, scientificName);
			}
		}
		catch (SQLException e) {
			LOGGER.error(String.format("GetById (%d) error:%s", id, e.getMessage())); //$NON-NLS-1$
			return null;
		}
	}

	@Override
	public void close() {
		LOGGER.debug("Enter close"); //$NON-NLS-1$
		try {
			this.connection.close();
			LOGGER.info("Database successfully closed"); //$NON-NLS-1$
		}
		catch (Exception e) {
			LOGGER.error(String.format("Close erorr: %s", e.getMessage())); //$NON-NLS-1$
		}
	}
	
	@Override
	public List<Livingbeing> getAll() {
		LOGGER.debug("Enter getAll"); //$NON-NLS-1$
		List<Livingbeing> result = new ArrayList<>();
		if (!open())
			return result;

		String sqlCommand = String.format("Select * from %s", Livingbeing.TABLE_NAME); //$NON-NLS-1$
		try (Statement stmt = this.connection.createStatement()){
			try(ResultSet rSet = stmt.executeQuery(sqlCommand)) {
				addResultList(rSet, result);
				return result;
			}
		}
		catch (SQLException e) {
			LOGGER.error(String.format("GetAll error:%s", e.getMessage())); //$NON-NLS-1$
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public<T extends Animal> List<T> getAllAnimals() {
		LOGGER.debug("Enter getAllAnimals"); //$NON-NLS-1$
		List<Livingbeing> all = getAll();
		List<T> result = new ArrayList<>();
		for (Livingbeing lb : all) {
			if (lb.isAnimal())
				result.add((T)lb);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public<T extends Mammal> List<T> getAllMammals() {
		LOGGER.debug("Enter getAllMammals"); //$NON-NLS-1$
		List<Livingbeing> all = getAll();
		List<T> result = new ArrayList<>();
		for (Livingbeing lb : all) {
			if (lb.isMammal())
				result.add((T)lb);
		}
		return result;
	}
	
	@Override
	public List<Livingbeing> findByScientificName(String name) {
		LOGGER.debug(String.format("Enter findByScientificName, name:%s", name)); //$NON-NLS-1$
		return findByColumn(Livingbeing.COL_SCIENTIFIC_NAME, name);
	}
	
	@Override
	public List<Livingbeing> findByInstanceName(String name) {
		LOGGER.debug(String.format("Enter findByInstanceName, name:%s", name)); //$NON-NLS-1$
		return findByColumn(Livingbeing.COL_INSTANCE_NAME, name); 
	}

	
	@Override
	public boolean delete(Livingbeing lb) {
		LOGGER.debug(String.format("Enter delete, lb:%s", lb)); //$NON-NLS-1$
		if (!open())
			return false;
		
		String sqlCommand = String.format("delete from %s where id=%d", Livingbeing.TABLE_NAME, lb.getId()); //$NON-NLS-1$
		try (Statement stmt = this.connection.createStatement()){
			return stmt.executeUpdate(sqlCommand)==1;
		}
		catch (SQLException e) {
			LOGGER.error(String.format("GetAll error:%s", e.getMessage())); //$NON-NLS-1$
			return false;
		}		
	}

	@Override
	public Livingbeing persist(Livingbeing lb) {
		LOGGER.debug(String.format("Enter persist, lb:%s", lb)); //$NON-NLS-1$
		return lb.getId()==null ? insert(lb) : update(lb);
	}

	public Livingbeing insert(Livingbeing lb) {
		LOGGER.debug(String.format("Enter insert, lb:%s", lb)); //$NON-NLS-1$
		if (!open())
			return null;
		String sql = getInsertSql(lb);
		try(Statement stmt = this.connection.createStatement()) {
			if (stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)!=1)
				return null;
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
		        if (generatedKeys.next()) 
		                lb.setId(Integer.valueOf(generatedKeys.getInt(1)));
		        else
		        	return null;
		    }
			return lb;
		}
		catch (Exception e) {
			LOGGER.error(String.format("insert error:%s", e.getMessage())); //$NON-NLS-1$
			return null;
		}
	}
	
	public Livingbeing update(Livingbeing lb) {
		LOGGER.debug(String.format("Enter update, lb:%s", lb)); //$NON-NLS-1$
		if (!open())
			return null;
		
		String sql = getUpdateSql(lb);
		try (Statement stmt = this.connection.createStatement()) {
			return stmt.executeUpdate(sql)==1 ? lb : null;
		}
		catch (Exception e) {
			LOGGER.error(String.format("update error:%s", e.getMessage())); //$NON-NLS-1$
			return null;
		}
	}
	
	protected boolean open() {
		LOGGER.debug("Enter open"); //$NON-NLS-1$

		if (this.connection != null)
			return true;
			
		try {
			connect();
			checkTable();
			LOGGER.info("Database successfully opened"); //$NON-NLS-1$
			return true;
		}
		catch (Exception e) {
			LOGGER.error(String.format("Open error:%s", e.getMessage())); //$NON-NLS-1$
			return false;
		}
	}
	
	private void checkTable() throws SQLException {
		LOGGER.debug("Enter checkTable"); //$NON-NLS-1$

		DatabaseMetaData meta = this.connection.getMetaData();
		try(ResultSet tables = meta.getTables(null, null, Livingbeing.TABLE_NAME, null);) { 
			while (tables.next()) { 
	            String tName = tables.getString("TABLE_NAME"); //$NON-NLS-1$
	            if (tName != null && tName.equals(Livingbeing.TABLE_NAME)) 
	            	return;
	            
	        }
		}
		
		LOGGER.info("Database is empty"); //$NON-NLS-1$
		try(Statement stmt = this.connection.createStatement()) {
			String sql = String.format("create table %s ( %s )", //$NON-NLS-1$
					Livingbeing.TABLE_NAME, createColumnDefinitions());
			stmt.executeUpdate(sql);
		}
		
		initDb();
	}
	
	private static String createColumnDefinitions() {
		LOGGER.debug("Enter createColumnDefinitions"); //$NON-NLS-1$

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry:AbstractLivingbeing.getColumnDefinitions().entrySet()) {
			sb.append(entry.getKey()).append(' ').append(entry.getValue()).append(',');
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}
	
	private void initDb() {
		LOGGER.debug("Enter initDb"); //$NON-NLS-1$
		LOGGER.info("Creating init data for database"); //$NON-NLS-1$
		WhiteShark ws = new WhiteShark("Dolly"); //$NON-NLS-1$ 
		Ostrich ost = new Ostrich(); 
		Barracuda barr = new Barracuda();
		Elephant dumbo = new Elephant("Dumbo"); //$NON-NLS-1$
		Elephant mrsDumbo = new Elephant("Mrs. Dumbo"); //$NON-NLS-1$ 
		AfricanBuffalo buffalo = new AfricanBuffalo();
		Rhinoceros rhino = new Rhinoceros(); 
		Penguin penguin = new Penguin("Tux"); //$NON-NLS-1$ 
		insert(ws);
		insert(ost);
		insert(barr);
		insert(dumbo);
		insert(mrsDumbo);
		insert(buffalo);
		insert(rhino);
		insert(penguin);
	}
	
	private List<Livingbeing> findByColumn(String colName, Object ...args) {
		LOGGER.debug("Enter findByColumn"); //$NON-NLS-1$
		if (!open())
			return null;
		
		List<Livingbeing> result = new ArrayList<>();		
		String sqlCommand = String.format("Select * from %s where %s='%s'", Livingbeing.TABLE_NAME, colName, args); //$NON-NLS-1$
		
		try (Statement stmt = this.connection.createStatement()){
			try(ResultSet rSet = stmt.executeQuery(sqlCommand)) {		
				addResultList(rSet, result);
			}
			return result;
		}
		catch (SQLException e) {
			LOGGER.error(String.format("GetAll error:%s", e.getMessage())); //$NON-NLS-1$
			return result;
		}		
	}
		
	private void connect() throws ClassNotFoundException, SQLException {
		LOGGER.debug("Enter connect"); //$NON-NLS-1$

		Class.forName("org.hsqldb.jdbc.JDBCDriver"); //$NON-NLS-1$
		String url = "jdbc:hsqldb:mem:db/zoodb;hsqldb.log_data=false"; //$NON-NLS-1$
		this.connection = DriverManager.getConnection(url, "sa", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void addResultList(ResultSet rSet, List<Livingbeing> result) throws SQLException {
		while(rSet.next()) {
			String scientificName = rSet.getString(Livingbeing.COL_SCIENTIFIC_NAME);
			Livingbeing lb = createInstance(rSet, scientificName);
			if (lb != null)
				result.add(lb);
		}
	}

	@SuppressWarnings("static-method")
	protected  Livingbeing createInstance(ResultSet rSet, String scientificName) throws SQLException {
		switch(scientificName) {
		case Elephant.SCIENTIFIC_NAME : return Elephant.createFromResultSet(rSet);
		case WhiteShark.SCIENTIFIC_NAME : return WhiteShark.createFromResultSet(rSet);
		case Ostrich.SCIENTIFIC_NAME : return Ostrich.createFromResultSet(rSet);
		case Barracuda.SCIENTIFIC_NAME : return Barracuda.createFromResultSet(rSet);
		case Penguin.SCIENTIFIC_NAME : return Penguin.createFromResultSet(rSet);
		case AfricanBuffalo.SCIENTIFIC_NAME : return AfricanBuffalo.createFromResultSet(rSet);
		case Hyppopotamus.SCIENTIFIC_NAME : return Hyppopotamus.createFromResultSet(rSet);
		case Rhinoceros.SCIENTIFIC_NAME : return Rhinoceros.createFromResultSet(rSet);
		default:
			LOGGER.error(String.format("Unknown scientificName in createInstance:%s"+ scientificName)); //$NON-NLS-1$
			return null;
		}
	}

	@SuppressWarnings("static-method")
	protected String getInsertSql(Livingbeing lb) {
		StringBuilder fields = new StringBuilder();
		StringBuilder values = new StringBuilder();
		lb.getInsertSql(fields, values);
		return String.format("insert into %s (%s) values (%s)",  //$NON-NLS-1$
						     Livingbeing.TABLE_NAME, fields.toString(), values.toString());
	}
	
	@SuppressWarnings("static-method")
	protected String getUpdateSql(Livingbeing lb) {
		return String.format("update %s set %s where %s=%d",  //$NON-NLS-1$
				Livingbeing.TABLE_NAME, lb.getUpdateSql(), Livingbeing.COL_ID, lb.getId());
	}
}
