/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/16/26
 * Class : RaceCars
 * Purpose : Generated code by SQLHelper to perform SQL action to sqlite database
 * */

package DBHelper;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class RaceCars extends DBHelper {
	private final String TABLE_NAME = "RaceCars";
	public static final String VehicleID = "VehicleID";
	public static final String Make = "Make";
	public static final String Model = "Model";
	public static final String Year = "Year";
	public static final String TopSpeed = "TopSpeed";
	public static final String LaunchStatus = "LaunchStatus";

	private String prepareSQL(String fields, String whatField, String whatValue, String sortField, String sort) {
		String query = "SELECT ";
		query += fields == null ? " * FROM " + TABLE_NAME : fields + " FROM " + TABLE_NAME;
		query += whatField != null && whatValue != null ? " WHERE " + whatField + " = \"" + whatValue + "\"" : "";
		query += sort != null && sortField != null ? " order by " + sortField + " " + sort : "";
		return query;
	}

	public void insert(Integer VehicleID, String Make, String Model, Integer Year, Double TopSpeed, Integer LaunchStatus) {
		Make = Make != null ? "\"" + Make + "\"" : null;
		Model = Model != null ? "\"" + Model + "\"" : null;
		
		Object[] values_ar = {VehicleID, Make, Model, Year, TopSpeed, LaunchStatus};
		String[] fields_ar = {RaceCars.VehicleID, RaceCars.Make, RaceCars.Model, RaceCars.Year, RaceCars.TopSpeed, RaceCars.LaunchStatus};
		String values = "", fields = "";
		for (int i = 0; i < values_ar.length; i++) {
			if (values_ar[i] != null) {
				values += values_ar[i] + ", ";
				fields += fields_ar[i] + ", ";
			}
		}
		if (!values.isEmpty()) {
			values = values.substring(0, values.length() - 2);
			fields = fields.substring(0, fields.length() - 2);
			super.execute("INSERT INTO " + TABLE_NAME + "(" + fields + ") values(" + values + ");");
		}
	}

	public void delete(String whatField, String whatValue) {
		super.execute("DELETE from " + TABLE_NAME + " where " + whatField + " = " + whatValue + ";");
	}

	public void update(String whatField, String whatValue, String whereField, String whereValue) {
		super.execute("UPDATE " + TABLE_NAME + " set " + whatField + " = \"" + whatValue + "\" where " + whereField + " = \"" + whereValue + "\";");
	}

	public ArrayList<ArrayList<Object>> select(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQuery(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

	public ArrayList<ArrayList<Object>> getExecuteResult(String query) {
		return super.executeQuery(query);
	}

	public void execute(String query) {
		super.execute(query);
	}

	public DefaultTableModel selectToTable(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQueryToTable(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

}