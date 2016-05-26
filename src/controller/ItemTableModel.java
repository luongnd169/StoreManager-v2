package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import lib.Convert;
import model.Item;

public class ItemTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Vector colHeaders;
	@SuppressWarnings("rawtypes")
	private Vector tbData;
	String[] colsName = { "Tên sản phẩm", "Loại", "Số lượng", "Đơn giá", "Thành tiền" };
	public static List<Item> listItem = new ArrayList<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemTableModel(List<Item> list) {
		int count = colsName.length;
		colHeaders = new Vector(count);
		tbData = new Vector();
		for (int i = 0; i < count; i++) {
			colHeaders.addElement(colsName[i]);
		}

		for (int i = 0; i < list.size(); i++) {
			Vector dataRow = new Vector(count);
			dataRow.addElement(list.get(i).getName());
			dataRow.addElement(list.get(i).getType());
			dataRow.addElement(list.get(i).getQuantity());
			dataRow.addElement(Convert.numberToString(list.get(i).getPrice()));
			dataRow.addElement(Convert.numberToString(
					Integer.parseInt(Convert.stringToNumber(list.get(i).getPrice())) * list.get(i).getQuantity() + ""));
			tbData.addElement(dataRow);
			listItem.addAll(list);
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
		return false;
	}

}
