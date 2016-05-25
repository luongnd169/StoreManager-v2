package controller;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Customer;

public class CustomerTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Vector colHeaders;
	@SuppressWarnings("rawtypes")
	private Vector tbData;
	String[] colsName = { "Tên", "Số điện thoại", "Địa chỉ", "Nhà cung cấp" };

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomerTableModel(List<Customer> list) {
		int count = colsName.length;
		colHeaders = new Vector(count);
		tbData = new Vector();
		for (int i = 0; i < count; i++) {
			colHeaders.addElement(colsName[i]);
		}
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Vector dataRow = new Vector(count);
				dataRow.addElement(list.get(i).getName());
				dataRow.addElement(list.get(i).getPhone());
				dataRow.addElement(list.get(i).getAddress());
				dataRow.addElement(list.get(i).isProvider());
				tbData.addElement(dataRow);

			}
		}
	}

	@Override
	public int getColumnCount() {
		return colsName.length;
	}

	@Override
	public int getRowCount() {
		return tbData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		@SuppressWarnings("rawtypes")
		Vector rowData = (Vector) (tbData.elementAt(row));
		return rowData.elementAt(col);
	}

	@Override
	public String getColumnName(int column) {
		return colsName[column];
	}

}
