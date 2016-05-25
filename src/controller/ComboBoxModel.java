package controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dao.ItemDAO;
import dao.ItemDetailDAO;
import model.Item;
import model.ItemDetail;

public class ComboBoxModel {
	
	MainController main = new MainController();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel getList(String name) {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		List<Item> listItem = ItemDAO.getItemes();
		List<Item> searchItem = main.searchItemByName(listItem, name);
		for (Item i : searchItem) {
			model.addElement(i.getName());
		}
		return model;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel getListByImei(String imei){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		List<ItemDetail> listItemDetail = ItemDetailDAO.getItemDetail("FROM ItemDetail WHERE imei LIKE '" + imei + "%'");
		for (ItemDetail i : listItemDetail) {
			model.addElement(i.getImei());
		}
		return model;
	}
	
	@SuppressWarnings("rawtypes")
	public DefaultComboBoxModel emptyList(){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		return model;
	}

}
