package controller;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import lib.Convert;
import model.Fee;

public class FeeTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Vector colHeaders;
	@SuppressWarnings("rawtypes")
	private Vector tbData;
	String[] colsName = { "Mục thu chi", "Số tiền", "Loại", "Ngày" };

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FeeTableModel(List<Fee> list) {
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
				dataRow.addElement(list.get(i).getValue());
				if (list.get(i).isType()) {
					dataRow.addElement("Thu");
				} else {
					dataRow.addElement("Chi");
				}
				Date date = list.get(i).getDate();
				String s = "";
				if (date != null) {
					s = Convert.formatDate(date);
				}
				dataRow.addElement(s);
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

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		@SuppressWarnings("unused")
		boolean[] columnEditables = new boolean[] { false, false, false, false };
		return false;
	}

}
