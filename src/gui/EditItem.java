package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.CustomerDAO;
import dao.ItemDAO;
import dao.ItemDetailDAO;
import model.Item;
import model.ItemDetail;

public class EditItem extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtNhaCungCap;
	private JTextField txtSoLuongTon;
	private JTextField txtGiaNhap;
	private JTextField txtGiaBinhQuan;
	private JTextField txtNgayNhap;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxImei;
	int count = 0;
	int index = 0;

	public JTextField getTxtMaSP() {
		return txtMaSP;
	}

	public void setTxtMaSP(JTextField txtMaSP) {
		this.txtMaSP = txtMaSP;
	}

	public JTextField getTxtTenSP() {
		return txtTenSP;
	}

	public void setTxtTenSP(JTextField txtTenSP) {
		this.txtTenSP = txtTenSP;
	}

	public JTextField getTxtNhaCungCap() {
		return txtNhaCungCap;
	}

	public void setTxtNhaCungCap(JTextField txtNhaCungCap) {
		this.txtNhaCungCap = txtNhaCungCap;
	}

	public JTextField getTxtSoLuongTon() {
		return txtSoLuongTon;
	}

	public void setTxtSoLuongTon(JTextField txtSoLuongTon) {
		this.txtSoLuongTon = txtSoLuongTon;
	}

	public JTextField getTxtGiaNhap() {
		return txtGiaNhap;
	}

	public void setTxtGiaNhap(JTextField txtGiaNhap) {
		this.txtGiaNhap = txtGiaNhap;
	}

	public JTextField getTxtGiaBinhQuan() {
		return txtGiaBinhQuan;
	}

	public void setTxtGiaBinhQuan(JTextField txtGiaBinhQuan) {
		this.txtGiaBinhQuan = txtGiaBinhQuan;
	}

	public JTextField getTxtNgayNhap() {
		return txtNgayNhap;
	}

	public void setTxtNgayNhap(JTextField txtNgayNhap) {
		this.txtNgayNhap = txtNgayNhap;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxImei() {
		return comboBoxImei;
	}

	@SuppressWarnings("rawtypes")
	public void setComboBoxImei(JComboBox comboBoxImei) {
		this.comboBoxImei = comboBoxImei;
	}

	/**
	 * Create the application.
	 */
	public EditItem(List<ItemDetail> listDetail) {
		initialize(listDetail);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(final List<ItemDetail> listDetail) {

		setBounds(500, 250, 600, 400);
		getContentPane().setLayout(null);
		setVisible(true);

		JLabel lblThongTinSP = new JLabel("Thông tin sản phẩm");
		lblThongTinSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThongTinSP.setBounds(220, 22, 134, 18);
		getContentPane().add(lblThongTinSP);

		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaSP.setBounds(20, 82, 80, 17);
		getContentPane().add(lblMaSP);

		txtMaSP = new JTextField();
		// txtMaSP.setEditable(false);
		txtMaSP.setBounds(110, 80, 150, 20);
		getContentPane().add(txtMaSP);
		txtMaSP.setColumns(10);

		JLabel lblTenSP = new JLabel("Tên sản phẩm");
		lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTenSP.setBounds(20, 142, 80, 17);
		getContentPane().add(lblTenSP);

		txtTenSP = new JTextField();
		txtTenSP.setEditable(false);
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(110, 140, 150, 20);
		getContentPane().add(txtTenSP);

		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNhaCungCap.setBounds(20, 200, 80, 20);
		getContentPane().add(lblNhaCungCap);

		txtNhaCungCap = new JTextField();
		txtNhaCungCap.setEditable(false);
		txtNhaCungCap.setColumns(10);
		txtNhaCungCap.setBounds(110, 200, 150, 20);
		getContentPane().add(txtNhaCungCap);

		JLabel lblSoLuongTon = new JLabel("Số lượng tồn");
		lblSoLuongTon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoLuongTon.setBounds(20, 260, 80, 20);
		getContentPane().add(lblSoLuongTon);

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setColumns(10);
		txtSoLuongTon.setBounds(110, 260, 150, 20);
		txtSoLuongTon.setEditable(false);
		getContentPane().add(txtSoLuongTon);

		JLabel lblGiaNhap = new JLabel("Giá nhập");
		lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGiaNhap.setBounds(310, 82, 80, 17);
		getContentPane().add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(400, 80, 150, 20);
		getContentPane().add(txtGiaNhap);

		JLabel lblGiaBinhQuan = new JLabel("Gía bình quân");
		lblGiaBinhQuan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGiaBinhQuan.setBounds(310, 142, 80, 17);
		getContentPane().add(lblGiaBinhQuan);

		txtGiaBinhQuan = new JTextField();
		txtGiaBinhQuan.setEditable(false);
		txtGiaBinhQuan.setColumns(10);
		txtGiaBinhQuan.setBounds(400, 140, 150, 20);
		getContentPane().add(txtGiaBinhQuan);

		JLabel lblNgayNhap = new JLabel("Ngày nhập");
		lblNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNgayNhap.setBounds(310, 202, 80, 17);
		getContentPane().add(lblNgayNhap);

		txtNgayNhap = new JTextField();
		txtNgayNhap.setColumns(10);
		txtNgayNhap.setBounds(400, 200, 150, 20);
		txtNgayNhap.setEditable(false);
		getContentPane().add(txtNgayNhap);

		JLabel lblSoImei = new JLabel("Số Imei");
		lblSoImei.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoImei.setBounds(310, 262, 80, 17);
		getContentPane().add(lblSoImei);

		comboBoxImei = new JComboBox();
		comboBoxImei.setEditable(true);
		comboBoxImei.setBounds(400, 260, 150, 20);
		getContentPane().add(comboBoxImei);
		
		if (!listDetail.isEmpty()) {
			txtNhaCungCap.setText(CustomerDAO.getCustomer(listDetail.get(0).getProvider()).getName());
			txtNgayNhap.setText(listDetail.get(0).getImportDate() + "");
			txtGiaNhap.setText(listDetail.get(0).getImportPrice());
			for (ItemDetail id : listDetail) {
				if (id.isStatus()) {
					comboBoxImei.addItem(id.getImei());
				}
			}
		}
		comboBoxImei.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxImei.getSelectedIndex() != -1){
					index = comboBoxImei.getSelectedIndex();
				}
				String imei = comboBoxImei.getEditor().getItem().toString();
				if (ItemDetailDAO.getItemDetail("From ItemDetail where imei ='" + imei + "'").isEmpty()) {
					return;
				} else {
					ItemDetail id = ItemDetailDAO.getItemDetail("From ItemDetail where imei = '" + imei + "'").get(0);
					txtNhaCungCap.setText("");
					txtGiaNhap.setText(id.getImportPrice());
					txtNgayNhap.setText(id.getImportDate() + "");
					txtNhaCungCap.setText(CustomerDAO.getCustomer(id.getProvider()).getName());
				}
			}
		});

		JButton btnLuu = new JButton("Lưu");
		btnLuu.setBounds(134, 310, 89, 23);
		getContentPane().add(btnLuu);
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 updateItem(listDetail, index);
			}
		});

		JButton btnDong = new JButton("Đóng");
		btnDong.setBounds(365, 310, 89, 23);
		btnDong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		getContentPane().add(btnDong);
	}

	private boolean updateItem(List<ItemDetail> listDetail, int index) {
		String imei = comboBoxImei.getEditor().getItem().toString();
		String price = txtGiaNhap.getText().trim();
		if (!imei.equals(listDetail.get(index).getImei()) || !price.equals(listDetail.get(index).getImportPrice())) {
			ItemDetail itemDetail = listDetail.get(index);
			itemDetail.setImei(imei);
			itemDetail.setImportPrice(price);
			ItemDetailDAO.update(itemDetail);
			Item item = ItemDAO.getItem(listDetail.get(0).getItemId());
			item.setPrice(String.valueOf(countAvgPrice(itemDetail, listDetail)));
			ItemDAO.update(item);
			JOptionPane.showMessageDialog(null, "Lưu thành công");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Lỗi! Kiểm tra lại dữ liệu");
		}

		return false;

	}

	private int countAvgPrice(ItemDetail itemDetail, List<ItemDetail> listDetail) {
		int totalPrice = 0;
		int avgPrice = 0;
		for (ItemDetail id : listDetail) {
			if (itemDetail.getId() != id.getId()) {
				totalPrice += Integer.parseInt(id.getImportPrice());
			}
		}
		avgPrice = (Integer.parseInt(itemDetail.getImportPrice()) + totalPrice) / listDetail.size();

		return avgPrice;

	}
}
